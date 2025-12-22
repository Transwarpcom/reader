package org.springframework.boot.autoconfigure.web.reactive;

import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/reactive/WebFluxRegistrations.class */
public interface WebFluxRegistrations {
    default RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return null;
    }

    default RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return null;
    }
}
