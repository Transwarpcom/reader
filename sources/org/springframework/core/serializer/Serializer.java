package org.springframework.core.serializer;

import java.io.IOException;
import java.io.OutputStream;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/serializer/Serializer.class */
public interface Serializer<T> {
    void serialize(T t, OutputStream outputStream) throws IOException;
}
