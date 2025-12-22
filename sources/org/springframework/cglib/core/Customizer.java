package org.springframework.cglib.core;

import org.springframework.asm.Type;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/cglib/core/Customizer.class */
public interface Customizer extends KeyFactoryCustomizer {
    void customize(CodeEmitter codeEmitter, Type type);
}
