package org.springframework.util;

import java.util.UUID;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/util/JdkIdGenerator.class */
public class JdkIdGenerator implements IdGenerator {
    @Override // org.springframework.util.IdGenerator
    public UUID generateId() {
        return UUID.randomUUID();
    }
}
