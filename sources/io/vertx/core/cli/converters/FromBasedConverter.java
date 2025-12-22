package io.vertx.core.cli.converters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/converters/FromBasedConverter.class */
public final class FromBasedConverter<T> implements Converter<T> {
    public static final String FROM = "from";
    private final Method method;
    private final Class<T> clazz;

    private FromBasedConverter(Class<T> clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
    }

    public static <T> FromBasedConverter<T> getIfEligible(Class<T> clazz) throws NoSuchMethodException, SecurityException {
        try {
            Method method = clazz.getMethod(FROM, String.class);
            if (Modifier.isStatic(method.getModifiers())) {
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                return new FromBasedConverter<>(clazz, method);
            }
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Override // io.vertx.core.cli.converters.Converter
    public T fromString(String input) throws IllegalArgumentException {
        try {
            return this.clazz.cast(this.method.invoke(null, input));
        } catch (IllegalAccessException | InvocationTargetException e) {
            if (e.getCause() != null) {
                throw new IllegalArgumentException(e.getCause());
            }
            throw new IllegalArgumentException(e);
        }
    }
}
