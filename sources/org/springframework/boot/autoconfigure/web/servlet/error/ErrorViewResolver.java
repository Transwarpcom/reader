package org.springframework.boot.autoconfigure.web.servlet.error;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/servlet/error/ErrorViewResolver.class */
public interface ErrorViewResolver {
    ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model);
}
