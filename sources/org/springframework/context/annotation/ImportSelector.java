package org.springframework.context.annotation;

import org.springframework.core.type.AnnotationMetadata;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/annotation/ImportSelector.class */
public interface ImportSelector {
    String[] selectImports(AnnotationMetadata annotationMetadata);
}
