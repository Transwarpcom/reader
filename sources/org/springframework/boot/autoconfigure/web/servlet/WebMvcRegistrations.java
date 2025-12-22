package org.springframework.boot.autoconfigure.web.servlet;

import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/servlet/WebMvcRegistrations.class */
public interface WebMvcRegistrations {
    default RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return null;
    }

    default RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return null;
    }

    default ExceptionHandlerExceptionResolver getExceptionHandlerExceptionResolver() {
        return null;
    }
}
