package io.vertx.core.cli.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/impl/ReflectionUtils.class */
public class ReflectionUtils {
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot instantiate " + clazz.getName(), e);
        }
    }

    public static boolean isSetter(Method method) {
        return method.getName().startsWith("set") && method.getParameterTypes().length == 1;
    }

    public static List<Method> getSetterMethods(Class<?> clazz) {
        return (List) Arrays.stream(clazz.getMethods()).filter(ReflectionUtils::isSetter).collect(Collectors.toList());
    }

    public static boolean isMultiple(Method setter) {
        Class<?> type = setter.getParameterTypes()[0];
        return type.isArray() || List.class.isAssignableFrom(type) || Set.class.isAssignableFrom(type) || Collection.class.isAssignableFrom(type);
    }

    public static Class getComponentType(Parameter parameter) {
        Class<?> type = parameter.getType();
        if (type.isArray()) {
            return type.getComponentType();
        }
        if (parameter.getParameterizedType() != null) {
            return (Class) ((ParameterizedType) parameter.getParameterizedType()).getActualTypeArguments()[0];
        }
        if (parameter.getType().getGenericSuperclass() instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) parameter.getType().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return null;
    }
}
