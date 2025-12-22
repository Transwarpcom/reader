package io.vertx.core.shareddata.impl;

import io.vertx.core.shareddata.Shareable;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/Checker.class */
public class Checker {
    static void checkType(Object obj) {
        if (!(obj instanceof String) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Boolean) && !(obj instanceof Double) && !(obj instanceof Float) && !(obj instanceof Short) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof byte[]) && !(obj instanceof Shareable)) {
            throw new IllegalArgumentException("Invalid type for shareddata data structure: " + obj.getClass().getName());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T> T copyIfRequired(T t) {
        if (t instanceof byte[]) {
            byte[] bArr = (byte[]) t;
            T t2 = (T) new byte[bArr.length];
            System.arraycopy(bArr, 0, t2, 0, bArr.length);
            return t2;
        }
        if (t instanceof Shareable) {
            return (T) ((Shareable) t).copy();
        }
        return t;
    }
}
