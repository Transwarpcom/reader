package io.vertx.core.cli.converters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/converters/ValueOfBasedConverter.class */
public final class ValueOfBasedConverter<T> implements Converter<T> {
    public static final String VALUE_OF = "valueOf";
    private final Method method;
    private final Class<T> clazz;

    private ValueOfBasedConverter(Class<T> clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
    }

    public static <T> ValueOfBasedConverter<T> getIfEligible(Class<T> clazz) throws NoSuchMethodException, SecurityException {
        try {
            Method method = clazz.getMethod("valueOf", String.class);
            if (Modifier.isStatic(method.getModifiers())) {
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                return new ValueOfBasedConverter<>(clazz, method);
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
