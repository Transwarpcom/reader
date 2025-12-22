package org.springframework.beans.factory.parsing;

import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/parsing/SourceExtractor.class */
public interface SourceExtractor {
    @Nullable
    Object extractSource(Object obj, @Nullable Resource resource);
}
