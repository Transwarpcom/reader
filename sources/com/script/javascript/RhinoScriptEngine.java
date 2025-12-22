package com.script.javascript;

import ch.qos.logback.core.CoreConstants;
import com.script.AbstractScriptEngine;
import com.script.Bindings;
import com.script.Compilable;
import com.script.CompiledScript;
import com.script.Invocable;
import com.script.ScriptContext;
import com.script.ScriptEngine;
import com.script.ScriptException;
import com.script.SimpleBindings;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.security.AccessControlContext;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.AllPermission;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.pdmodel.interactive.action.PDWindowsLaunchParams;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/javascript/RhinoScriptEngine.class */
public class RhinoScriptEngine extends AbstractScriptEngine implements Invocable, Compilable {
    private static final boolean DEBUG = false;
    private AccessControlContext accCtxt;
    private final RhinoTopLevel topLevel;
    private final Map<Object, Object> indexedProps;
    private final InterfaceImplementor implementor;
    private static final String printSource = "function print(str, newline) {                \n    if (typeof(str) == 'undefined') {         \n        str = 'undefined';                    \n    } else if (str == null) {                 \n        str = 'null';                         \n    }                                         \n    var out = context.getWriter();            \n    if (!(out instanceof java.io.PrintWriter))\n        out = new java.io.PrintWriter(out);   \n    out.print(String(str));                   \n    if (newline) out.print('\\n');            \n    out.flush();                              \n}\nfunction println(str) {                       \n    print(str, true);                         \n}";

    static {
        ContextFactory.initGlobal(new ContextFactory() { // from class: com.script.javascript.RhinoScriptEngine.1
            @Override // org.mozilla.javascript.ContextFactory
            protected Context makeContext() {
                Context cx = super.makeContext();
                cx.setLanguageVersion(200);
                cx.setOptimizationLevel(-1);
                cx.setClassShutter(RhinoClassShutter.getInstance());
                cx.setWrapFactory(RhinoWrapFactory.getInstance());
                return cx;
            }

            @Override // org.mozilla.javascript.ContextFactory
            protected Object doTopCall(Callable callable, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
                AccessControlContext accCtxt = null;
                Scriptable global = ScriptableObject.getTopLevelScope(scope);
                Scriptable globalProto = global.getPrototype();
                if (globalProto instanceof RhinoTopLevel) {
                    accCtxt = ((RhinoTopLevel) globalProto).getAccessContext();
                }
                if (accCtxt != null) {
                    return AccessController.doPrivileged(() -> {
                        return superDoTopCall(callable, cx, scope, thisObj, args);
                    }, accCtxt);
                }
                return superDoTopCall(callable, cx, scope, thisObj, args);
            }

            private Object superDoTopCall(Callable callable, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
                return super.doTopCall(callable, cx, scope, thisObj, args);
            }
        });
    }

    public RhinoScriptEngine() throws RuntimeException {
        if (System.getSecurityManager() != null) {
            try {
                AccessController.checkPermission(new AllPermission());
            } catch (AccessControlException e) {
                this.accCtxt = AccessController.getContext();
            }
        }
        Context cx = Context.enter();
        try {
            this.topLevel = new RhinoTopLevel(cx, this);
            Context.exit();
            this.indexedProps = new HashMap();
            this.implementor = new InterfaceImplementor(this) { // from class: com.script.javascript.RhinoScriptEngine.2
                @Override // com.script.javascript.InterfaceImplementor
                protected boolean isImplemented(Object thiz, Class<?> iface) {
                    if (thiz != null) {
                        try {
                            if (!(thiz instanceof Scriptable)) {
                                thiz = Context.toObject(thiz, RhinoScriptEngine.this.topLevel);
                            }
                        } catch (Throwable th) {
                            Context.exit();
                            throw th;
                        }
                    }
                    Scriptable engineScope = RhinoScriptEngine.this.getRuntimeScope(RhinoScriptEngine.this.context);
                    Scriptable localScope = thiz != null ? (Scriptable) thiz : engineScope;
                    for (Method method : iface.getMethods()) {
                        if (method.getDeclaringClass() != Object.class) {
                            Object obj = ScriptableObject.getProperty(localScope, method.getName());
                            if (!(obj instanceof Function)) {
                                Context.exit();
                                return false;
                            }
                        }
                    }
                    Context.exit();
                    return true;
                }

                @Override // com.script.javascript.InterfaceImplementor
                protected Object convertResult(Method method, Object res) {
                    Class<?> desiredType = method.getReturnType();
                    if (desiredType == Void.TYPE) {
                        return null;
                    }
                    return Context.jsToJava(res, desiredType);
                }
            };
        } catch (Throwable th) {
            Context.exit();
            throw th;
        }
    }

    @Override // com.script.ScriptEngine
    public Object eval(Reader reader, ScriptContext ctxt) throws ScriptException {
        String msg;
        Context cx = Context.enter();
        try {
            try {
                Scriptable scope = getRuntimeScope(ctxt);
                String filename = (String) get(ScriptEngine.FILENAME);
                Object ret = cx.evaluateReader(scope, reader, filename == null ? "<Unknown source>" : filename, 1, null);
                Context.exit();
                return unwrapReturnValue(ret);
            } catch (IOException ee) {
                throw new ScriptException(ee);
            } catch (RhinoException re) {
                int line = re.lineNumber();
                int line2 = line == 0 ? -1 : line;
                if (re instanceof JavaScriptException) {
                    msg = String.valueOf(((JavaScriptException) re).getValue());
                } else {
                    msg = re.toString();
                }
                ScriptException se = new ScriptException(msg, re.sourceName(), line2);
                se.initCause(re);
                throw se;
            }
        } catch (Throwable th) {
            Context.exit();
            throw th;
        }
    }

    @Override // com.script.ScriptEngine
    public Object eval(String script, ScriptContext ctxt) throws ScriptException {
        if (script == null) {
            throw new NullPointerException("null script");
        }
        return eval(new StringReader(script), ctxt);
    }

    @Override // com.script.ScriptEngine
    public Bindings createBindings() {
        return new SimpleBindings();
    }

    @Override // com.script.Invocable
    public Object invokeFunction(String name, Object... args) throws NoSuchMethodException, ScriptException {
        return invoke(null, name, args);
    }

    @Override // com.script.Invocable
    public Object invokeMethod(Object thiz, String name, Object... args) throws NoSuchMethodException, ScriptException {
        if (thiz == null) {
            throw new IllegalArgumentException("脚本对象不能为空");
        }
        return invoke(thiz, name, args);
    }

    private Object invoke(Object thiz, String name, Object... args) throws NoSuchMethodException, ScriptException {
        Context cx = Context.enter();
        try {
            try {
                if (name == null) {
                    throw new NullPointerException("方法名为空");
                }
                if (thiz != null && !(thiz instanceof Scriptable)) {
                    thiz = Context.toObject(thiz, this.topLevel);
                }
                Scriptable engineScope = getRuntimeScope(this.context);
                Scriptable localScope = thiz != null ? (Scriptable) thiz : engineScope;
                Object obj = ScriptableObject.getProperty(localScope, name);
                if (!(obj instanceof Function)) {
                    throw new NoSuchMethodException("no such method: " + name);
                }
                Function func = (Function) obj;
                Scriptable scope = func.getParentScope();
                if (scope == null) {
                    scope = engineScope;
                }
                Object result = func.call(cx, scope, localScope, wrapArguments(args));
                Object objUnwrapReturnValue = unwrapReturnValue(result);
                Context.exit();
                return objUnwrapReturnValue;
            } catch (RhinoException re) {
                int line = re.lineNumber();
                ScriptException se = new ScriptException(re.toString(), re.sourceName(), line == 0 ? -1 : line);
                se.initCause(re);
                throw se;
            }
        } catch (Throwable th) {
            Context.exit();
            throw th;
        }
    }

    @Override // com.script.Invocable
    public <T> T getInterface(Class<T> cls) {
        try {
            return (T) this.implementor.getInterface(null, cls);
        } catch (ScriptException e) {
            return null;
        }
    }

    @Override // com.script.Invocable
    public <T> T getInterface(Object obj, Class<T> cls) {
        if (obj == null) {
            throw new IllegalArgumentException("脚本对象不能为空");
        }
        try {
            return (T) this.implementor.getInterface(obj, cls);
        } catch (ScriptException e) {
            return null;
        }
    }

    Scriptable getRuntimeScope(ScriptContext ctxt) {
        if (ctxt == null) {
            throw new NullPointerException("脚本context为空");
        }
        Scriptable newScope = new ExternalScriptable(ctxt, this.indexedProps);
        newScope.setPrototype(this.topLevel);
        newScope.put(CoreConstants.CONTEXT_SCOPE_VALUE, newScope, ctxt);
        Context cx = Context.enter();
        try {
            cx.evaluateString(newScope, printSource, PDWindowsLaunchParams.OPERATION_PRINT, 1, null);
            Context.exit();
            return newScope;
        } catch (Throwable th) {
            Context.exit();
            throw th;
        }
    }

    @Override // com.script.Compilable
    public CompiledScript compile(String script) throws ScriptException {
        return compile(new StringReader(script));
    }

    @Override // com.script.Compilable
    public CompiledScript compile(Reader script) throws ScriptException {
        Context cx = Context.enter();
        try {
            try {
                String fileName = (String) get(ScriptEngine.FILENAME);
                if (fileName == null) {
                    fileName = "<Unknown Source>";
                }
                Script scr = cx.compileReader(script, fileName, 1, null);
                CompiledScript ret = new RhinoCompiledScript(this, scr);
                Context.exit();
                return ret;
            } catch (Exception e) {
                throw new ScriptException(e);
            }
        } catch (Throwable th) {
            Context.exit();
            throw th;
        }
    }

    AccessControlContext getAccessContext() {
        return this.accCtxt;
    }

    Object[] wrapArguments(Object[] args) {
        if (args == null) {
            return Context.emptyArgs;
        }
        Object[] res = new Object[args.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = Context.javaToJS(args[i], this.topLevel);
        }
        return res;
    }

    Object unwrapReturnValue(Object result) {
        if (result instanceof Wrapper) {
            result = ((Wrapper) result).unwrap();
        }
        if (result instanceof Undefined) {
            return null;
        }
        return result;
    }
}
