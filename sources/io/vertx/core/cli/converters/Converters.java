package io.vertx.core.cli.converters;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/converters/Converters.class */
public class Converters {
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER_TYPE;
    private static final Map<Class<?>, Converter<?>> WELL_KNOWN_CONVERTERS;

    static {
        Map<Class<?>, Class<?>> primToWrap = new HashMap<>(16);
        primToWrap.put(Boolean.TYPE, Boolean.class);
        primToWrap.put(Byte.TYPE, Byte.class);
        primToWrap.put(Character.TYPE, Character.class);
        primToWrap.put(Double.TYPE, Double.class);
        primToWrap.put(Float.TYPE, Float.class);
        primToWrap.put(Integer.TYPE, Integer.class);
        primToWrap.put(Long.TYPE, Long.class);
        primToWrap.put(Short.TYPE, Short.class);
        primToWrap.put(Void.TYPE, Void.class);
        PRIMITIVE_TO_WRAPPER_TYPE = Collections.unmodifiableMap(primToWrap);
        Map<Class<?>, Converter<?>> wellKnown = new HashMap<>(16);
        wellKnown.put(Boolean.class, BooleanConverter.INSTANCE);
        wellKnown.put(Byte.class, Byte::parseByte);
        wellKnown.put(Character.class, CharacterConverter.INSTANCE);
        wellKnown.put(Double.class, Double::parseDouble);
        wellKnown.put(Float.class, Float::parseFloat);
        wellKnown.put(Integer.class, Integer::parseInt);
        wellKnown.put(Long.class, Long::parseLong);
        wellKnown.put(Short.class, Short::parseShort);
        wellKnown.put(String.class, value -> {
            return value;
        });
        WELL_KNOWN_CONVERTERS = Collections.unmodifiableMap(wellKnown);
    }

    public static <T> T create(Class<T> cls, String str) {
        if (cls.isPrimitive()) {
            cls = wrap(cls);
        }
        return (T) getConverter(cls).fromString(str);
    }

    public static <T> T create(String value, Converter<T> converter) {
        return converter.fromString(value);
    }

    private static <T> Class<T> wrap(Class<T> type) {
        Class<T> wrapped = (Class) PRIMITIVE_TO_WRAPPER_TYPE.get(type);
        return wrapped == null ? type : wrapped;
    }

    private static <T> Converter<T> getConverter(Class<T> type) throws NoSuchMethodException, SecurityException {
        if (WELL_KNOWN_CONVERTERS.containsKey(type)) {
            return (Converter) WELL_KNOWN_CONVERTERS.get(type);
        }
        Converter<T> converter = ConstructorBasedConverter.getIfEligible(type);
        if (converter != null) {
            return converter;
        }
        Converter<T> converter2 = ValueOfBasedConverter.getIfEligible(type);
        if (converter2 != null) {
            return converter2;
        }
        Converter<T> converter3 = FromBasedConverter.getIfEligible(type);
        if (converter3 != null) {
            return converter3;
        }
        Converter<T> converter4 = FromStringBasedConverter.getIfEligible(type);
        if (converter4 != null) {
            return converter4;
        }
        throw new NoSuchElementException("Cannot find a converter able to create instance of " + type.getName());
    }

    public static <T> Converter<T> newInstance(Class<? extends Converter<T>> type) throws IllegalArgumentException {
        try {
            return type.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException("Cannot create a new instance of " + type.getName() + " - it requires an public constructor without argument", e);
        }
    }
}
