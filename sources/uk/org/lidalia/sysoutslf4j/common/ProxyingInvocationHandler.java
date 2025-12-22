package uk.org.lidalia.sysoutslf4j.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/common/ProxyingInvocationHandler.class */
public class ProxyingInvocationHandler implements InvocationHandler {
    private final Object target;
    private final Map<Method, Method> methods = new HashMap();

    ProxyingInvocationHandler(Object target, Class<?> interfaceClass) throws NoSuchMethodException, SecurityException {
        this.target = target;
        for (Method method : interfaceClass.getMethods()) {
            try {
                Method methodOnTarget = target.getClass().getMethod(method.getName(), method.getParameterTypes());
                this.methods.put(method, methodOnTarget);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Target " + target + " does not have methods to match all method signatures on class " + interfaceClass, e);
            }
        }
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method methodOnTarget = this.methods.get(method);
        return methodOnTarget.invoke(this.target, args);
    }

    public Object getTarget() {
        return this.target;
    }
}
