package io.vertx.core.cli.converters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/converters/ConstructorBasedConverter.class */
public final class ConstructorBasedConverter<T> implements Converter<T> {
    private final Constructor<T> constructor;

    private ConstructorBasedConverter(Constructor<T> constructor) {
        this.constructor = constructor;
    }

    public static <T> ConstructorBasedConverter<T> getIfEligible(Class<T> clazz) throws NoSuchMethodException, SecurityException {
        try {
            Constructor<T> constructor = clazz.getConstructor(String.class);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return new ConstructorBasedConverter<>(constructor);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Override // io.vertx.core.cli.converters.Converter
    public T fromString(String input) throws IllegalArgumentException {
        try {
            return this.constructor.newInstance(input);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            if (e.getCause() != null) {
                throw new IllegalArgumentException(e.getCause());
            }
            throw new IllegalArgumentException(e);
        }
    }
}
