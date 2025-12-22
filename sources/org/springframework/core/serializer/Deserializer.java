package org.springframework.core.serializer;

import java.io.IOException;
import java.io.InputStream;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/serializer/Deserializer.class */
public interface Deserializer<T> {
    T deserialize(InputStream inputStream) throws IOException;
}
