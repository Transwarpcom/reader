package com.script.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeJavaArray;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/javascript/JSAdapter.class */
public final class JSAdapter implements Scriptable, Function {
    private Scriptable prototype;
    private Scriptable parent;
    private Scriptable adaptee;
    private boolean isPrototype;
    private static final String GET_PROP = "__get__";
    private static final String HAS_PROP = "__has__";
    private static final String PUT_PROP = "__put__";
    private static final String DEL_PROP = "__delete__";
    private static final String GET_PROPIDS = "__getIds__";

    private JSAdapter(Scriptable obj) {
        setAdaptee(obj);
    }

    public static void init(Context cx, Scriptable scope, boolean sealed) throws RhinoException {
        JSAdapter obj = new JSAdapter(cx.newObject(scope));
        obj.setParentScope(scope);
        obj.setPrototype(getFunctionPrototype(scope));
        obj.isPrototype = true;
        ScriptableObject.defineProperty(scope, "JSAdapter", obj, 2);
    }

    @Override // org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "JSAdapter";
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object get(String name, Scriptable start) {
        Function func = getAdapteeFunction(GET_PROP);
        if (func != null) {
            return call(func, new Object[]{name});
        }
        Scriptable start2 = getAdaptee();
        return start2.get(name, start2);
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object get(int index, Scriptable start) {
        Function func = getAdapteeFunction(GET_PROP);
        if (func != null) {
            return call(func, new Object[]{Integer.valueOf(index)});
        }
        Scriptable start2 = getAdaptee();
        return start2.get(index, start2);
    }

    @Override // org.mozilla.javascript.Scriptable
    public boolean has(String name, Scriptable start) {
        Function func = getAdapteeFunction(HAS_PROP);
        if (func != null) {
            Object res = call(func, new Object[]{name});
            return Context.toBoolean(res);
        }
        Scriptable start2 = getAdaptee();
        return start2.has(name, start2);
    }

    @Override // org.mozilla.javascript.Scriptable
    public boolean has(int index, Scriptable start) {
        Function func = getAdapteeFunction(HAS_PROP);
        if (func != null) {
            Object res = call(func, new Object[]{Integer.valueOf(index)});
            return Context.toBoolean(res);
        }
        Scriptable start2 = getAdaptee();
        return start2.has(index, start2);
    }

    @Override // org.mozilla.javascript.Scriptable
    public void put(String name, Scriptable start, Object value) {
        if (start == this) {
            Function func = getAdapteeFunction(PUT_PROP);
            if (func != null) {
                call(func, new Object[]{name, value});
                return;
            } else {
                Scriptable start2 = getAdaptee();
                start2.put(name, start2, value);
                return;
            }
        }
        start.put(name, start, value);
    }

    @Override // org.mozilla.javascript.Scriptable
    public void put(int index, Scriptable start, Object value) {
        if (start == this) {
            Function func = getAdapteeFunction(PUT_PROP);
            if (func != null) {
                call(func, new Object[]{Integer.valueOf(index), value});
                return;
            } else {
                Scriptable start2 = getAdaptee();
                start2.put(index, start2, value);
                return;
            }
        }
        start.put(index, start, value);
    }

    @Override // org.mozilla.javascript.Scriptable
    public void delete(String name) {
        Function func = getAdapteeFunction(DEL_PROP);
        if (func != null) {
            call(func, new Object[]{name});
        } else {
            getAdaptee().delete(name);
        }
    }

    @Override // org.mozilla.javascript.Scriptable
    public void delete(int index) {
        Function func = getAdapteeFunction(DEL_PROP);
        if (func != null) {
            call(func, new Object[]{Integer.valueOf(index)});
        } else {
            getAdaptee().delete(index);
        }
    }

    @Override // org.mozilla.javascript.Scriptable
    public Scriptable getPrototype() {
        return this.prototype;
    }

    @Override // org.mozilla.javascript.Scriptable
    public void setPrototype(Scriptable prototype) {
        this.prototype = prototype;
    }

    @Override // org.mozilla.javascript.Scriptable
    public Scriptable getParentScope() {
        return this.parent;
    }

    @Override // org.mozilla.javascript.Scriptable
    public void setParentScope(Scriptable parent) {
        this.parent = parent;
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object[] getIds() {
        Object[] res;
        Function func = getAdapteeFunction(GET_PROPIDS);
        if (func != null) {
            Object val = call(func, new Object[0]);
            if (val instanceof NativeArray) {
                NativeArray nativeArray = (NativeArray) val;
                Object[] res2 = new Object[(int) nativeArray.getLength()];
                for (int index = 0; index < res2.length; index++) {
                    res2[index] = mapToId(nativeArray.get(index, nativeArray));
                }
                return res2;
            }
            if (val instanceof NativeJavaArray) {
                Object tmp = ((NativeJavaArray) val).unwrap();
                if (tmp.getClass() == Object[].class) {
                    Object[] array = (Object[]) tmp;
                    res = new Object[array.length];
                    for (int index2 = 0; index2 < array.length; index2++) {
                        res[index2] = mapToId(array[index2]);
                    }
                } else {
                    res = Context.emptyArgs;
                }
                return res;
            }
            return Context.emptyArgs;
        }
        return getAdaptee().getIds();
    }

    @Override // org.mozilla.javascript.Scriptable
    public boolean hasInstance(Scriptable scriptable) {
        if (scriptable instanceof JSAdapter) {
            return true;
        }
        Scriptable prototype = scriptable.getPrototype();
        while (true) {
            Scriptable proto = prototype;
            if (proto != null) {
                if (proto.equals(this)) {
                    return true;
                }
                prototype = proto.getPrototype();
            } else {
                return false;
            }
        }
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class hint) {
        return getAdaptee().getDefaultValue(hint);
    }

    @Override // org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) throws RhinoException {
        if (this.isPrototype) {
            return construct(cx, scope, args);
        }
        Scriptable tmp = getAdaptee();
        if (tmp instanceof Function) {
            return ((Function) tmp).call(cx, scope, tmp, args);
        }
        throw Context.reportRuntimeError("TypeError: not a function");
    }

    @Override // org.mozilla.javascript.Function
    public Scriptable construct(Context cx, Scriptable scope, Object[] args) throws RhinoException {
        if (this.isPrototype) {
            Scriptable topLevel = ScriptableObject.getTopLevelScope(scope);
            if (args.length > 0) {
                JSAdapter newObj = new JSAdapter(Context.toObject(args[0], topLevel));
                return newObj;
            }
            throw Context.reportRuntimeError("JSAdapter requires adaptee");
        }
        Scriptable tmp = getAdaptee();
        if (tmp instanceof Function) {
            return ((Function) tmp).construct(cx, scope, args);
        }
        throw Context.reportRuntimeError("TypeError: not a constructor");
    }

    public Scriptable getAdaptee() {
        return this.adaptee;
    }

    public void setAdaptee(Scriptable adaptee) {
        if (adaptee == null) {
            throw new NullPointerException("adaptee can not be null");
        }
        this.adaptee = adaptee;
    }

    private Object mapToId(Object tmp) {
        if (tmp instanceof Double) {
            return Integer.valueOf(((Double) tmp).intValue());
        }
        return Context.toString(tmp);
    }

    private static Scriptable getFunctionPrototype(Scriptable scope) {
        return ScriptableObject.getFunctionPrototype(scope);
    }

    private Function getAdapteeFunction(String name) {
        Object o = ScriptableObject.getProperty(getAdaptee(), name);
        if (o instanceof Function) {
            return (Function) o;
        }
        return null;
    }

    private Object call(Function func, Object[] args) {
        Context cx = Context.getCurrentContext();
        Scriptable thisObj = getAdaptee();
        Scriptable scope = func.getParentScope();
        try {
            return func.call(cx, scope, thisObj, args);
        } catch (RhinoException re) {
            throw Context.reportRuntimeError(re.getMessage());
        }
    }
}
