package org.springframework.context;

import org.springframework.beans.factory.Aware;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/MessageSourceAware.class */
public interface MessageSourceAware extends Aware {
    void setMessageSource(MessageSource messageSource);
}
