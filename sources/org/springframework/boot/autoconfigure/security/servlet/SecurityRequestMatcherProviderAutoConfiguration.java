package org.springframework.boot.autoconfigure.security.servlet;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.JerseyApplicationPath;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@ConditionalOnClass({RequestMatcher.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/security/servlet/SecurityRequestMatcherProviderAutoConfiguration.class */
public class SecurityRequestMatcherProviderAutoConfiguration {

    @Configuration
    @ConditionalOnClass({DispatcherServlet.class})
    @ConditionalOnBean({HandlerMappingIntrospector.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/security/servlet/SecurityRequestMatcherProviderAutoConfiguration$MvcRequestMatcherConfiguration.class */
    public static class MvcRequestMatcherConfiguration {
        @ConditionalOnClass({DispatcherServlet.class})
        @Bean
        public RequestMatcherProvider requestMatcherProvider(HandlerMappingIntrospector introspector) {
            return new MvcRequestMatcherProvider(introspector);
        }
    }

    @ConditionalOnMissingClass({"org.springframework.web.servlet.DispatcherServlet"})
    @Configuration
    @ConditionalOnClass({ResourceConfig.class})
    @ConditionalOnBean({JerseyApplicationPath.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/security/servlet/SecurityRequestMatcherProviderAutoConfiguration$JerseyRequestMatcherConfiguration.class */
    public static class JerseyRequestMatcherConfiguration {
        @Bean
        public RequestMatcherProvider requestMatcherProvider(JerseyApplicationPath applicationPath) {
            return new JerseyRequestMatcherProvider(applicationPath);
        }
    }
}
