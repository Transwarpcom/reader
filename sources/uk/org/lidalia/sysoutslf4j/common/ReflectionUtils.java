package uk.org.lidalia.sysoutslf4j.common;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/common/ReflectionUtils.class */
public final class ReflectionUtils {
    public static Object invokeMethod(String methodName, Object target) {
        Method method = getMethod(methodName, target.getClass(), new Class[0]);
        return invokeMethod(method, target, new Object[0]);
    }

    public static Object invokeMethod(String methodName, Object target, Class<?> argType, Object arg) {
        Method method = getMethod(methodName, target.getClass(), argType);
        return invokeMethod(method, target, arg);
    }

    public static Object invokeStaticMethod(String methodName, Class<?> targetClass) {
        Method method = getMethod(methodName, targetClass, new Class[0]);
        return invokeMethod(method, targetClass, new Object[0]);
    }

    public static Object invokeStaticMethod(String methodName, Class<?> targetClass, Class<?> argType, Object arg) {
        Method method = getMethod(methodName, targetClass, argType);
        return invokeMethod(method, targetClass, arg);
    }

    private static Method getMethod(String methodName, Class<?> classWithMethod, Class<?>... clsArr) {
        try {
            return getMethod(methodName, classWithMethod, clsArr, null);
        } catch (NoSuchMethodException noSuchMethodException) {
            throw new WrappedCheckedException(noSuchMethodException);
        }
    }

    private static Method getMethod(String methodName, Class<?> classWithMethod, Class<?>[] clsArr, NoSuchMethodException originalNoSuchMethodException) throws NoSuchMethodException, SecurityException {
        Method foundMethod;
        try {
            foundMethod = classWithMethod.getDeclaredMethod(methodName, clsArr);
        } catch (NoSuchMethodException noSuchMethodException) {
            Class<?> superclass = classWithMethod.getSuperclass();
            if (superclass == null) {
                throw originalNoSuchMethodException;
            }
            NoSuchMethodException firstNoSuchMethodException = (NoSuchMethodException) getDefault(originalNoSuchMethodException, noSuchMethodException);
            foundMethod = getMethod(methodName, superclass, clsArr, firstNoSuchMethodException);
        }
        return foundMethod;
    }

    private static <T> T getDefault(T mightBeNull, T useIfNull) {
        T result;
        if (mightBeNull == null) {
            result = useIfNull;
        } else {
            result = mightBeNull;
        }
        return result;
    }

    public static Object invokeMethod(final Method method, Object target, Object... args) {
        try {
            if (!method.isAccessible()) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() { // from class: uk.org.lidalia.sysoutslf4j.common.ReflectionUtils.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.security.PrivilegedAction
                    public Void run() {
                        method.setAccessible(true);
                        return null;
                    }
                });
            }
            return method.invoke(target, args);
        } catch (Exception exception) {
            throw ExceptionUtils.asRuntimeException(exception);
        }
    }

    private ReflectionUtils() {
        throw new UnsupportedOperationException("Not instantiable");
    }

    public static <TypeInThisClassLoader> TypeInThisClassLoader wrap(Object obj, Class<TypeInThisClassLoader> cls) throws IllegalArgumentException {
        Object objNewProxyInstance;
        if (cls.isAssignableFrom(obj.getClass())) {
            objNewProxyInstance = obj;
        } else {
            objNewProxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, new ProxyingInvocationHandler(obj, cls));
        }
        return (TypeInThisClassLoader) objNewProxyInstance;
    }
}
