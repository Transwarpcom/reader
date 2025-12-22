package org.springframework.validation;

import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/validation/MessageCodesResolver.class */
public interface MessageCodesResolver {
    String[] resolveMessageCodes(String str, String str2);

    String[] resolveMessageCodes(String str, String str2, String str3, @Nullable Class<?> cls);
}
