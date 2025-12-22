package com.script.javascript;

import com.script.Bindings;
import com.script.ScriptContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaClass;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Wrapper;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/javascript/ExternalScriptable.class */
final class ExternalScriptable implements Scriptable {
    private ScriptContext context;
    private Map<Object, Object> indexedProps;
    private Scriptable prototype;
    private Scriptable parent;

    ExternalScriptable(ScriptContext context) {
        this(context, new HashMap());
    }

    ExternalScriptable(ScriptContext context, Map<Object, Object> indexedProps) {
        if (context == null) {
            throw new NullPointerException("context is null");
        }
        this.context = context;
        this.indexedProps = indexedProps;
    }

    ScriptContext getContext() {
        return this.context;
    }

    private boolean isEmpty(String name) {
        return name.equals("");
    }

    @Override // org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Global";
    }

    @Override // org.mozilla.javascript.Scriptable
    public synchronized Object get(String name, Scriptable start) {
        if (isEmpty(name)) {
            return this.indexedProps.getOrDefault(name, NOT_FOUND);
        }
        synchronized (this.context) {
            int scope = this.context.getAttributesScope(name);
            if (scope != -1) {
                Object value = this.context.getAttribute(name, scope);
                return Context.javaToJS(value, this);
            }
            return NOT_FOUND;
        }
    }

    @Override // org.mozilla.javascript.Scriptable
    public synchronized Object get(int index, Scriptable start) {
        Integer key = Integer.valueOf(index);
        if (this.indexedProps.containsKey(Integer.valueOf(index))) {
            return this.indexedProps.get(key);
        }
        return NOT_FOUND;
    }

    @Override // org.mozilla.javascript.Scriptable
    public synchronized boolean has(String name, Scriptable start) {
        boolean z;
        if (isEmpty(name)) {
            return this.indexedProps.containsKey(name);
        }
        synchronized (this.context) {
            z = this.context.getAttributesScope(name) != -1;
        }
        return z;
    }

    @Override // org.mozilla.javascript.Scriptable
    public synchronized boolean has(int index, Scriptable start) {
        Integer key = Integer.valueOf(index);
        return this.indexedProps.containsKey(key);
    }

    @Override // org.mozilla.javascript.Scriptable
    public void put(String name, Scriptable start, Object value) {
        if (start == this) {
            synchronized (this) {
                if (isEmpty(name)) {
                    this.indexedProps.put(name, value);
                } else {
                    synchronized (this.context) {
                        int scope = this.context.getAttributesScope(name);
                        if (scope == -1) {
                            scope = 100;
                        }
                        this.context.setAttribute(name, jsToJava(value), scope);
                    }
                }
            }
            return;
        }
        start.put(name, start, value);
    }

    @Override // org.mozilla.javascript.Scriptable
    public void put(int index, Scriptable start, Object value) {
        if (start == this) {
            synchronized (this) {
                this.indexedProps.put(Integer.valueOf(index), value);
            }
        } else {
            start.put(index, start, value);
        }
    }

    @Override // org.mozilla.javascript.Scriptable
    public synchronized void delete(String name) {
        if (isEmpty(name)) {
            this.indexedProps.remove(name);
            return;
        }
        synchronized (this.context) {
            int scope = this.context.getAttributesScope(name);
            if (scope != -1) {
                this.context.removeAttribute(name, scope);
            }
        }
    }

    @Override // org.mozilla.javascript.Scriptable
    public void delete(int index) {
        this.indexedProps.remove(Integer.valueOf(index));
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
    public synchronized Object[] getIds() {
        String[] keys = getAllKeys();
        int size = keys.length + this.indexedProps.size();
        Object[] res = new Object[size];
        System.arraycopy(keys, 0, res, 0, keys.length);
        int i = keys.length;
        for (Object index : this.indexedProps.keySet()) {
            int i2 = i;
            i++;
            res[i2] = index;
        }
        return res;
    }

    @Override // org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class typeHint) {
        boolean tryToString;
        String methodName;
        Object[] args;
        String hint;
        int i = 0;
        while (i < 2) {
            if (typeHint == ScriptRuntime.StringClass) {
                tryToString = i == 0;
            } else {
                tryToString = i == 1;
            }
            if (tryToString) {
                methodName = "toString";
                args = ScriptRuntime.emptyArgs;
            } else {
                methodName = "valueOf";
                args = new Object[1];
                if (typeHint == null) {
                    hint = "undefined";
                } else if (typeHint == ScriptRuntime.StringClass) {
                    hint = "string";
                } else if (typeHint == ScriptRuntime.ScriptableClass) {
                    hint = "object";
                } else if (typeHint == ScriptRuntime.FunctionClass) {
                    hint = "function";
                } else if (typeHint == ScriptRuntime.BooleanClass || typeHint == Boolean.TYPE) {
                    hint = "boolean";
                } else if (typeHint == ScriptRuntime.NumberClass || typeHint == ScriptRuntime.ByteClass || typeHint == Byte.TYPE || typeHint == ScriptRuntime.ShortClass || typeHint == Short.TYPE || typeHint == ScriptRuntime.IntegerClass || typeHint == Integer.TYPE || typeHint == ScriptRuntime.FloatClass || typeHint == Float.TYPE || typeHint == ScriptRuntime.DoubleClass || typeHint == Double.TYPE) {
                    hint = "number";
                } else {
                    throw Context.reportRuntimeError("Invalid JavaScript value of type " + typeHint.toString());
                }
                args[0] = hint;
            }
            Object v = ScriptableObject.getProperty(this, methodName);
            if (v instanceof Function) {
                Function fun = (Function) v;
                Context cx = Context.enter();
                try {
                    Object v2 = fun.call(cx, fun.getParentScope(), this, args);
                    Context.exit();
                    if (v2 == null) {
                        continue;
                    } else {
                        if (!(v2 instanceof Scriptable)) {
                            return v2;
                        }
                        if (typeHint == ScriptRuntime.ScriptableClass || typeHint == ScriptRuntime.FunctionClass) {
                            return v2;
                        }
                        if (tryToString && (v2 instanceof Wrapper)) {
                            Object u = ((Wrapper) v2).unwrap();
                            if (u instanceof String) {
                                return u;
                            }
                        }
                    }
                } catch (Throwable th) {
                    Context.exit();
                    throw th;
                }
            }
            i++;
        }
        String arg = typeHint == null ? "undefined" : typeHint.getName();
        throw Context.reportRuntimeError("找不到对象的默认值 " + arg);
    }

    @Override // org.mozilla.javascript.Scriptable
    public boolean hasInstance(Scriptable instance) {
        Scriptable prototype = instance.getPrototype();
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

    private String[] getAllKeys() {
        ArrayList<String> list = new ArrayList<>();
        synchronized (this.context) {
            Iterator<Integer> it = this.context.getScopes().iterator();
            while (it.hasNext()) {
                int scope = it.next().intValue();
                Bindings bindings = this.context.getBindings(scope);
                if (bindings != null) {
                    list.ensureCapacity(bindings.size());
                    for (String key : bindings.keySet()) {
                        list.add(key);
                    }
                }
            }
        }
        String[] res = new String[list.size()];
        list.toArray(res);
        return res;
    }

    private Object jsToJava(Object jsObj) {
        if (jsObj instanceof Wrapper) {
            Wrapper njb = (Wrapper) jsObj;
            if (njb instanceof NativeJavaClass) {
                return njb;
            }
            Object obj = njb.unwrap();
            if ((obj instanceof Number) || (obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Character)) {
                return njb;
            }
            return obj;
        }
        return jsObj;
    }
}
