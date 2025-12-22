package org.springframework.boot.context.annotation;

import java.util.Set;
import org.springframework.core.type.AnnotationMetadata;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/annotation/DeterminableImports.class */
public interface DeterminableImports {
    Set<Object> determineImports(AnnotationMetadata metadata);
}
