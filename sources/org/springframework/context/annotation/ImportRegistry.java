package org.springframework.context.annotation;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/annotation/ImportRegistry.class */
interface ImportRegistry {
    @Nullable
    AnnotationMetadata getImportingClassFor(String str);

    void removeImportingClass(String str);
}
