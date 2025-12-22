package cn.hutool.core.lang.reflect;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.util.TypeUtil;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/reflect/ActualTypeMapperPool.class */
public class ActualTypeMapperPool {
    private static final SimpleCache<Type, Map<Type, Type>> CACHE = new SimpleCache<>();

    private static /* synthetic */ Object $deserializeLambda$(SerializedLambda lambda) {
        switch (lambda.getImplMethodName()) {
            case "lambda$get$2c794883$1":
                if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("cn/hutool/core/lang/func/Func0") && lambda.getFunctionalInterfaceMethodName().equals("call") && lambda.getFunctionalInterfaceMethodSignature().equals("()Ljava/lang/Object;") && lambda.getImplClass().equals("cn/hutool/core/lang/reflect/ActualTypeMapperPool") && lambda.getImplMethodSignature().equals("(Ljava/lang/reflect/Type;)Ljava/util/Map;")) {
                    Type type = (Type) lambda.getCapturedArg(0);
                    return () -> {
                        return createTypeMap(type);
                    };
                }
                break;
        }
        throw new IllegalArgumentException("Invalid lambda deserialization");
    }

    public static Map<Type, Type> get(Type type) {
        return CACHE.get(type, () -> {
            return createTypeMap(type);
        });
    }

    public static Map<String, Type> getStrKeyMap(Type type) {
        return Convert.toMap(String.class, Type.class, get(type));
    }

    public static Type getActualType(Type type, TypeVariable<?> typeVariable) {
        Map<Type, Type> typeTypeMap = get(type);
        Type type2 = typeTypeMap.get(typeVariable);
        while (true) {
            Type result = type2;
            if (result instanceof TypeVariable) {
                type2 = typeTypeMap.get(result);
            } else {
                return result;
            }
        }
    }

    public static Type[] getActualTypes(Type type, Type... typeVariables) {
        Type[] result = new Type[typeVariables.length];
        for (int i = 0; i < typeVariables.length; i++) {
            result[i] = typeVariables[i] instanceof TypeVariable ? getActualType(type, (TypeVariable) typeVariables[i]) : typeVariables[i];
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<Type, Type> createTypeMap(Type type) {
        ParameterizedType parameterizedType;
        Map<Type, Type> typeMap = new HashMap<>();
        while (null != type && null != (parameterizedType = TypeUtil.toParameterizedType(type))) {
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            Class<?> rawType = (Class) parameterizedType.getRawType();
            Type[] typeParameters = rawType.getTypeParameters();
            for (int i = 0; i < typeParameters.length; i++) {
                Type value = typeArguments[i];
                if (false == (value instanceof TypeVariable)) {
                    typeMap.put(typeParameters[i], value);
                }
            }
            type = rawType;
        }
        return typeMap;
    }
}
