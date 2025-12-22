package org.springframework.boot.web.servlet.error;

import java.util.Map;
import org.springframework.web.context.request.WebRequest;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/servlet/error/ErrorAttributes.class */
public interface ErrorAttributes {
    Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace);

    Throwable getError(WebRequest webRequest);
}
