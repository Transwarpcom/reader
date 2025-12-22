package org.springframework.util;

import org.springframework.lang.Nullable;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/util/StringValueResolver.class */
public interface StringValueResolver {
    @Nullable
    String resolveStringValue(String str);
}
