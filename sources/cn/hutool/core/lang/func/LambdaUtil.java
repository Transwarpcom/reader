package cn.hutool.core.lang.func;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/func/LambdaUtil.class */
public class LambdaUtil {
    private static final SimpleCache<String, SerializedLambda> cache = new SimpleCache<>();

    private static /* synthetic */ Object $deserializeLambda$(SerializedLambda lambda) {
        switch (lambda.getImplMethodName()) {
            case "lambda$_resolve$9255c75$1":
                if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("cn/hutool/core/lang/func/Func0") && lambda.getFunctionalInterfaceMethodName().equals("call") && lambda.getFunctionalInterfaceMethodSignature().equals("()Ljava/lang/Object;") && lambda.getImplClass().equals("cn/hutool/core/lang/func/LambdaUtil") && lambda.getImplMethodSignature().equals("(Ljava/io/Serializable;)Ljava/lang/invoke/SerializedLambda;")) {
                    Serializable serializable = (Serializable) lambda.getCapturedArg(0);
                    return () -> {
                        return (SerializedLambda) ReflectUtil.invoke(serializable, "writeReplace", new Object[0]);
                    };
                }
                break;
        }
        throw new IllegalArgumentException("Invalid lambda deserialization");
    }

    public static <R> Class<R> getRealClass(Func0<?> func) {
        SerializedLambda lambda = resolve(func);
        checkLambdaTypeCanGetClass(lambda.getImplMethodKind());
        return ClassUtil.loadClass(lambda.getImplClass());
    }

    public static <T> SerializedLambda resolve(Func1<T, ?> func) {
        return _resolve(func);
    }

    public static <R> SerializedLambda resolve(Func0<R> func) {
        return _resolve(func);
    }

    public static <P> String getMethodName(Func1<P, ?> func) {
        return resolve(func).getImplMethodName();
    }

    public static <R> String getMethodName(Func0<R> func) {
        return resolve(func).getImplMethodName();
    }

    public static <P, R> Class<P> getRealClass(Func1<P, R> func) {
        SerializedLambda lambda = resolve(func);
        checkLambdaTypeCanGetClass(lambda.getImplMethodKind());
        String instantiatedMethodType = lambda.getInstantiatedMethodType();
        return ClassUtil.loadClass(StrUtil.sub(instantiatedMethodType, 2, StrUtil.indexOf(instantiatedMethodType, ';')));
    }

    public static <T> String getFieldName(Func1<T, ?> func) throws IllegalArgumentException {
        return BeanUtil.getFieldName(getMethodName(func));
    }

    public static <T> String getFieldName(Func0<T> func) throws IllegalArgumentException {
        return BeanUtil.getFieldName(getMethodName(func));
    }

    private static void checkLambdaTypeCanGetClass(int implMethodKind) {
        if (implMethodKind != 5 && implMethodKind != 6) {
            throw new IllegalArgumentException("该lambda不是合适的方法引用");
        }
    }

    private static SerializedLambda _resolve(Serializable func) {
        return cache.get(func.getClass().getName(), () -> {
            return (SerializedLambda) ReflectUtil.invoke(func, "writeReplace", new Object[0]);
        });
    }
}
