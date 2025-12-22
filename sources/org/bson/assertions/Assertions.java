package org.bson.assertions;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/assertions/Assertions.class */
public final class Assertions {
    public static <T> T notNull(String name, T value) {
        if (value == null) {
            throw new IllegalArgumentException(name + " can not be null");
        }
        return value;
    }

    public static void isTrue(String name, boolean condition) {
        if (!condition) {
            throw new IllegalStateException("state should be: " + name);
        }
    }

    public static void isTrueArgument(String name, boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException("state should be: " + name);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T convertToType(Class<T> clazz, Object obj, String errorMessage) {
        if (!clazz.isAssignableFrom(obj.getClass())) {
            throw new IllegalArgumentException(errorMessage);
        }
        return obj;
    }

    private Assertions() {
    }
}
