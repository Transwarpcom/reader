package org.springframework.boot.autoconfigure.security.servlet;

import org.springframework.boot.autoconfigure.web.servlet.JerseyApplicationPath;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/security/servlet/JerseyRequestMatcherProvider.class */
public class JerseyRequestMatcherProvider implements RequestMatcherProvider {
    private final JerseyApplicationPath jerseyApplicationPath;

    public JerseyRequestMatcherProvider(JerseyApplicationPath jerseyApplicationPath) {
        this.jerseyApplicationPath = jerseyApplicationPath;
    }

    @Override // org.springframework.boot.autoconfigure.security.servlet.RequestMatcherProvider
    public RequestMatcher getRequestMatcher(String pattern) {
        return new AntPathRequestMatcher(this.jerseyApplicationPath.getRelativePath(pattern));
    }
}
