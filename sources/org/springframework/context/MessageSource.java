package org.springframework.context;

import java.util.Locale;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/MessageSource.class */
public interface MessageSource {
    @Nullable
    String getMessage(String str, @Nullable Object[] objArr, @Nullable String str2, Locale locale);

    String getMessage(String str, @Nullable Object[] objArr, Locale locale) throws NoSuchMessageException;

    String getMessage(MessageSourceResolvable messageSourceResolvable, Locale locale) throws NoSuchMessageException;
}
