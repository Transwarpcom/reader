package org.springframework.boot.web.reactive.error;

import java.util.Map;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/reactive/error/ErrorAttributes.class */
public interface ErrorAttributes {
    Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace);

    Throwable getError(ServerRequest request);

    void storeErrorInformation(Throwable error, ServerWebExchange exchange);
}
