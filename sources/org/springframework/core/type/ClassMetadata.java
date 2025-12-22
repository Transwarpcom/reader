package org.springframework.core.type;

import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/type/ClassMetadata.class */
public interface ClassMetadata {
    String getClassName();

    boolean isInterface();

    boolean isAnnotation();

    boolean isAbstract();

    boolean isConcrete();

    boolean isFinal();

    boolean isIndependent();

    boolean hasEnclosingClass();

    @Nullable
    String getEnclosingClassName();

    boolean hasSuperClass();

    @Nullable
    String getSuperClassName();

    String[] getInterfaceNames();

    String[] getMemberClassNames();
}
