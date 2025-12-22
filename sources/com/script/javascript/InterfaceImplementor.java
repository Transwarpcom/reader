package com.script.javascript;

import com.script.Invocable;
import com.script.ScriptException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/javascript/InterfaceImplementor.class */
public class InterfaceImplementor {
    private Invocable engine;

    public InterfaceImplementor(Invocable engine) {
        this.engine = engine;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/javascript/InterfaceImplementor$InterfaceImplementorInvocationHandler.class */
    private final class InterfaceImplementorInvocationHandler implements InvocationHandler {
        private Object thiz;
        private AccessControlContext accCtxt;

        public InterfaceImplementorInvocationHandler(Object thiz, AccessControlContext accCtxt) {
            this.thiz = thiz;
            this.accCtxt = accCtxt;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object[] args2 = InterfaceImplementor.this.convertArguments(method, args);
            Object result = AccessController.doPrivileged((PrivilegedExceptionAction<Object>) () -> {
                return this.thiz == null ? InterfaceImplementor.this.engine.invokeFunction(method.getName(), args2) : InterfaceImplementor.this.engine.invokeMethod(this.thiz, method.getName(), args2);
            }, this.accCtxt);
            return InterfaceImplementor.this.convertResult(method, result);
        }
    }

    public <T> T getInterface(Object thiz, Class<T> iface) throws ScriptException {
        if (iface == null || !iface.isInterface()) {
            throw new IllegalArgumentException("interface Class expected");
        }
        if (!isImplemented(thiz, iface)) {
            return null;
        }
        AccessControlContext accCtxt = AccessController.getContext();
        return iface.cast(Proxy.newProxyInstance(iface.getClassLoader(), new Class[]{iface}, new InterfaceImplementorInvocationHandler(thiz, accCtxt)));
    }

    protected boolean isImplemented(Object thiz, Class<?> iface) {
        return true;
    }

    protected Object convertResult(Method method, Object res) throws ScriptException {
        return res;
    }

    protected Object[] convertArguments(Method method, Object[] args) throws ScriptException {
        return args;
    }
}
