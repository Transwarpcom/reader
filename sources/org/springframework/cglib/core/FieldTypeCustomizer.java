package org.springframework.cglib.core;

import org.springframework.asm.Type;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/cglib/core/FieldTypeCustomizer.class */
public interface FieldTypeCustomizer extends KeyFactoryCustomizer {
    void customize(CodeEmitter codeEmitter, int i, Type type);

    Type getOutType(int i, Type type);
}
