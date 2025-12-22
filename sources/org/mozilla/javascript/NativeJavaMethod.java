package org.mozilla.javascript;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeJavaMethod.class */
public class NativeJavaMethod extends BaseFunction {
    private static final long serialVersionUID = -3440381785576412928L;
    private static final int PREFERENCE_EQUAL = 0;
    private static final int PREFERENCE_FIRST_ARG = 1;
    private static final int PREFERENCE_SECOND_ARG = 2;
    private static final int PREFERENCE_AMBIGUOUS = 3;
    private static final boolean debug = false;
    MemberBox[] methods;
    private String functionName;
    private final transient CopyOnWriteArrayList<ResolvedOverload> overloadCache;

    NativeJavaMethod(MemberBox[] methods) {
        this.overloadCache = new CopyOnWriteArrayList<>();
        this.functionName = methods[0].getName();
        this.methods = methods;
    }

    NativeJavaMethod(MemberBox[] methods, String name) {
        this.overloadCache = new CopyOnWriteArrayList<>();
        this.functionName = name;
        this.methods = methods;
    }

    NativeJavaMethod(MemberBox method, String name) {
        this.overloadCache = new CopyOnWriteArrayList<>();
        this.functionName = name;
        this.methods = new MemberBox[]{method};
    }

    public NativeJavaMethod(Method method, String name) {
        this(new MemberBox(method), name);
    }

    @Override // org.mozilla.javascript.BaseFunction
    public String getFunctionName() {
        return this.functionName;
    }

    static String scriptSignature(Object[] values) {
        String s;
        StringBuilder sig = new StringBuilder();
        for (int i = 0; i != values.length; i++) {
            Object value = values[i];
            if (value == null) {
                s = "null";
            } else if (value instanceof Boolean) {
                s = "boolean";
            } else if (value instanceof String) {
                s = "string";
            } else if (value instanceof Number) {
                s = "number";
            } else if (value instanceof Scriptable) {
                if (value instanceof Undefined) {
                    s = "undefined";
                } else if (value instanceof Wrapper) {
                    Object wrapped = ((Wrapper) value).unwrap();
                    s = wrapped.getClass().getName();
                } else if (value instanceof Function) {
                    s = "function";
                } else {
                    s = "object";
                }
            } else {
                s = JavaMembers.javaSignature(value.getClass());
            }
            if (i != 0) {
                sig.append(',');
            }
            sig.append(s);
        }
        return sig.toString();
    }

    @Override // org.mozilla.javascript.BaseFunction
    String decompile(int indent, int flags) {
        StringBuilder sb = new StringBuilder();
        boolean justbody = 0 != (flags & 1);
        if (!justbody) {
            sb.append("function ");
            sb.append(getFunctionName());
            sb.append("() {");
        }
        sb.append("/*\n");
        sb.append(toString());
        sb.append(justbody ? "*/\n" : "*/}\n");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int N = this.methods.length;
        for (int i = 0; i != N; i++) {
            if (this.methods[i].isMethod()) {
                Method method = this.methods[i].method();
                sb.append(JavaMembers.javaSignature(method.getReturnType()));
                sb.append(' ');
                sb.append(method.getName());
            } else {
                sb.append(this.methods[i].getName());
            }
            sb.append(JavaMembers.liveConnectSignature(this.methods[i].argTypes));
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Object javaObject;
        Object varArgs;
        if (this.methods.length == 0) {
            throw new RuntimeException("No methods defined for call");
        }
        int index = findCachedFunction(cx, args);
        if (index < 0) {
            String sig = this.methods[0].method().getDeclaringClass().getName() + '.' + getFunctionName() + '(' + scriptSignature(args) + ')';
            throw Context.reportRuntimeError1("msg.java.no_such_method", sig);
        }
        MemberBox meth = this.methods[index];
        Class<?>[] argTypes = meth.argTypes;
        if (meth.vararg) {
            Object[] newArgs = new Object[argTypes.length];
            for (int i = 0; i < argTypes.length - 1; i++) {
                newArgs[i] = Context.jsToJava(args[i], argTypes[i]);
            }
            if (args.length == argTypes.length && (args[args.length - 1] == null || (args[args.length - 1] instanceof NativeArray) || (args[args.length - 1] instanceof NativeJavaArray))) {
                varArgs = Context.jsToJava(args[args.length - 1], argTypes[argTypes.length - 1]);
            } else {
                Class<?> componentType = argTypes[argTypes.length - 1].getComponentType();
                varArgs = Array.newInstance(componentType, (args.length - argTypes.length) + 1);
                for (int i2 = 0; i2 < Array.getLength(varArgs); i2++) {
                    Object value = Context.jsToJava(args[(argTypes.length - 1) + i2], componentType);
                    Array.set(varArgs, i2, value);
                }
            }
            newArgs[argTypes.length - 1] = varArgs;
            args = newArgs;
        } else {
            for (int i3 = 0; i3 < args.length; i3++) {
                Object arg = args[i3];
                Object coerced = Context.jsToJava(arg, argTypes[i3]);
                if (coerced != arg) {
                    if (args == args) {
                        args = (Object[]) args.clone();
                    }
                    args[i3] = coerced;
                }
            }
        }
        if (meth.isStatic()) {
            javaObject = null;
        } else {
            Class<?> c = meth.getDeclaringClass();
            for (Scriptable o = thisObj; o != null; o = o.getPrototype()) {
                if (o instanceof Wrapper) {
                    javaObject = ((Wrapper) o).unwrap();
                    if (c.isInstance(javaObject)) {
                    }
                }
            }
            throw Context.reportRuntimeError3("msg.nonjava.method", getFunctionName(), ScriptRuntime.toString(thisObj), c.getName());
        }
        Object retval = meth.invoke(javaObject, args);
        Class<?> staticType = meth.method().getReturnType();
        Object wrapped = cx.getWrapFactory().wrap(cx, scope, retval, staticType);
        if (wrapped == null && staticType == Void.TYPE) {
            wrapped = Undefined.instance;
        }
        return wrapped;
    }

    int findCachedFunction(Context cx, Object[] args) throws RuntimeException {
        if (this.methods.length > 1) {
            Iterator<ResolvedOverload> it = this.overloadCache.iterator();
            while (it.hasNext()) {
                ResolvedOverload ovl = it.next();
                if (ovl.matches(args)) {
                    return ovl.index;
                }
            }
            int index = findFunction(cx, this.methods, args);
            if (this.overloadCache.size() < this.methods.length * 2) {
                this.overloadCache.addIfAbsent(new ResolvedOverload(args, index));
            }
            return index;
        }
        return findFunction(cx, this.methods, args);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x009e A[PHI: r15
  0x009e: PHI (r15v2 'alength' int) = (r15v1 'alength' int), (r15v3 'alength' int) binds: [B:37:0x0098, B:34:0x008e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static int findFunction(org.mozilla.javascript.Context r6, org.mozilla.javascript.MemberBox[] r7, java.lang.Object[] r8) throws java.lang.RuntimeException {
        /*
            Method dump skipped, instructions count: 606
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeJavaMethod.findFunction(org.mozilla.javascript.Context, org.mozilla.javascript.MemberBox[], java.lang.Object[]):int");
    }

    private static int preferSignature(Object[] args, Class<?>[] sig1, boolean vararg1, Class<?>[] sig2, boolean vararg2) {
        int preference;
        int totalPreference = 0;
        int j = 0;
        while (j < args.length) {
            Class<?> type1 = (!vararg1 || j < sig1.length) ? sig1[j] : sig1[sig1.length - 1];
            Class<?> type2 = (!vararg2 || j < sig2.length) ? sig2[j] : sig2[sig2.length - 1];
            if (type1 != type2) {
                Object arg = args[j];
                int rank1 = NativeJavaObject.getConversionWeight(arg, type1);
                int rank2 = NativeJavaObject.getConversionWeight(arg, type2);
                if (rank1 < rank2) {
                    preference = 1;
                } else if (rank1 > rank2) {
                    preference = 2;
                } else if (rank1 == 0) {
                    if (type1.isAssignableFrom(type2)) {
                        preference = 2;
                    } else if (type2.isAssignableFrom(type1)) {
                        preference = 1;
                    } else {
                        preference = 3;
                    }
                } else {
                    preference = 3;
                }
                totalPreference |= preference;
                if (totalPreference == 3) {
                    break;
                }
            }
            j++;
        }
        return totalPreference;
    }

    private static void printDebug(String msg, MemberBox member, Object[] args) {
    }
}
