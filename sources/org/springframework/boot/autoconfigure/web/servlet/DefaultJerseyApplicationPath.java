package org.springframework.boot.autoconfigure.web.servlet;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/servlet/DefaultJerseyApplicationPath.class */
public class DefaultJerseyApplicationPath implements JerseyApplicationPath {
    private final String applicationPath;
    private final ResourceConfig config;

    public DefaultJerseyApplicationPath(String applicationPath, ResourceConfig config) {
        this.applicationPath = applicationPath;
        this.config = config;
    }

    @Override // org.springframework.boot.autoconfigure.web.servlet.JerseyApplicationPath
    public String getPath() {
        return resolveApplicationPath();
    }

    private String resolveApplicationPath() {
        if (StringUtils.hasLength(this.applicationPath)) {
            return this.applicationPath;
        }
        return findApplicationPath(AnnotationUtils.findAnnotation(this.config.getApplication().getClass(), ApplicationPath.class));
    }

    private static String findApplicationPath(ApplicationPath annotation) {
        if (annotation == null) {
            return "/*";
        }
        return annotation.value();
    }
}
