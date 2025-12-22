package org.springframework.cglib.core;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/cglib/core/GeneratorStrategy.class */
public interface GeneratorStrategy {
    byte[] generate(ClassGenerator classGenerator) throws Exception;

    boolean equals(Object obj);
}
