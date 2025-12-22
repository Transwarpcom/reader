package org.springframework.context;

import org.springframework.lang.Nullable;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/MessageSourceResolvable.class */
public interface MessageSourceResolvable {
    @Nullable
    String[] getCodes();

    @Nullable
    default Object[] getArguments() {
        return null;
    }

    @Nullable
    default String getDefaultMessage() {
        return null;
    }
}
