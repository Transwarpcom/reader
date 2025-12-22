package cn.hutool.core.lang;

import cn.hutool.core.lang.func.Func0;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import java.lang.invoke.SerializedLambda;
import java.util.HashMap;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/Singleton.class */
public final class Singleton {
    private static final SimpleCache<String, Object> POOL = new SimpleCache<>(new HashMap());

    private static /* synthetic */ Object $deserializeLambda$(SerializedLambda lambda) {
        switch (lambda.getImplMethodName()) {
            case "call":
                if (lambda.getImplMethodKind() == 9 && lambda.getFunctionalInterfaceClass().equals("cn/hutool/core/lang/func/Func0") && lambda.getFunctionalInterfaceMethodName().equals("call") && lambda.getFunctionalInterfaceMethodSignature().equals("()Ljava/lang/Object;") && lambda.getImplClass().equals("cn/hutool/core/lang/func/Func0") && lambda.getImplMethodSignature().equals("()Ljava/lang/Object;")) {
                    Func0 func0 = (Func0) lambda.getCapturedArg(0);
                    return func0::call;
                }
                break;
            case "lambda$get$3f3ed817$1":
                if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("cn/hutool/core/lang/func/Func0") && lambda.getFunctionalInterfaceMethodName().equals("call") && lambda.getFunctionalInterfaceMethodSignature().equals("()Ljava/lang/Object;") && lambda.getImplClass().equals("cn/hutool/core/lang/Singleton") && lambda.getImplMethodSignature().equals("(Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;")) {
                    Class cls = (Class) lambda.getCapturedArg(0);
                    Object[] objArr = (Object[]) lambda.getCapturedArg(1);
                    return () -> {
                        return ReflectUtil.newInstance(cls, objArr);
                    };
                }
                break;
        }
        throw new IllegalArgumentException("Invalid lambda deserialization");
    }

    private Singleton() {
    }

    public static <T> T get(Class<T> cls, Object... objArr) throws IllegalArgumentException {
        Assert.notNull(cls, "Class must be not null !", new Object[0]);
        return (T) get(buildKey(cls.getName(), objArr), () -> {
            return ReflectUtil.newInstance(cls, objArr);
        });
    }

    public static <T> T get(String str, Func0<T> func0) {
        SimpleCache<String, Object> simpleCache = POOL;
        func0.getClass();
        return (T) simpleCache.get(str, func0::call);
    }

    public static <T> T get(String str, Object... objArr) throws IllegalArgumentException {
        Assert.notBlank(str, "Class name must be not blank !", new Object[0]);
        return (T) get(ClassUtil.loadClass(str), objArr);
    }

    public static void put(Object obj) throws IllegalArgumentException {
        Assert.notNull(obj, "Bean object must be not null !", new Object[0]);
        put(obj.getClass().getName(), obj);
    }

    public static void put(String key, Object obj) {
        POOL.put(key, obj);
    }

    public static void remove(Class<?> clazz) {
        if (null != clazz) {
            remove(clazz.getName());
        }
    }

    public static void remove(String key) {
        POOL.remove(key);
    }

    public static void destroy() {
        POOL.clear();
    }

    private static String buildKey(String className, Object... params) {
        if (ArrayUtil.isEmpty(params)) {
            return className;
        }
        return StrUtil.format("{}#{}", className, ArrayUtil.join(params, (CharSequence) "_"));
    }
}
