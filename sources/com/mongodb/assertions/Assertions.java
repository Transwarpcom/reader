package com.mongodb.assertions;

import com.mongodb.async.SingleResultCallback;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/assertions/Assertions.class */
public final class Assertions {
    public static <T> T notNull(String name, T value) {
        if (value == null) {
            throw new IllegalArgumentException(name + " can not be null");
        }
        return value;
    }

    public static <T> T notNull(String name, T value, SingleResultCallback<?> callback) {
        if (value == null) {
            IllegalArgumentException exception = new IllegalArgumentException(name + " can not be null");
            callback.onResult(null, exception);
            throw exception;
        }
        return value;
    }

    public static void isTrue(String name, boolean condition) {
        if (!condition) {
            throw new IllegalStateException("state should be: " + name);
        }
    }

    public static void isTrue(String name, boolean condition, SingleResultCallback<?> callback) {
        if (!condition) {
            IllegalStateException exception = new IllegalStateException("state should be: " + name);
            callback.onResult(null, exception);
            throw exception;
        }
    }

    public static void isTrueArgument(String name, boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException("state should be: " + name);
        }
    }

    private Assertions() {
    }
}
