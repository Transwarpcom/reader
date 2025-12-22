package org.springframework.cglib.core;

import org.springframework.asm.Type;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/cglib/core/HashCodeCustomizer.class */
public interface HashCodeCustomizer extends KeyFactoryCustomizer {
    boolean customize(CodeEmitter codeEmitter, Type type);
}
