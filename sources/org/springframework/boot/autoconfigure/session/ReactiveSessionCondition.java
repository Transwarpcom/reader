package org.springframework.boot.autoconfigure.session;

import org.springframework.boot.WebApplicationType;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/session/ReactiveSessionCondition.class */
class ReactiveSessionCondition extends AbstractSessionCondition {
    ReactiveSessionCondition() {
        super(WebApplicationType.REACTIVE);
    }
}
