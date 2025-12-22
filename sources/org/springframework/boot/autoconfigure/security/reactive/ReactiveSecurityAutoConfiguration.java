package org.springframework.boot.autoconfigure.security.reactive;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.server.WebFilterChainProxy;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import reactor.core.publisher.Flux;

@EnableConfigurationProperties({SecurityProperties.class})
@Configuration
@ConditionalOnClass({Flux.class, EnableWebFluxSecurity.class, WebFilterChainProxy.class, WebFluxConfigurer.class})
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/security/reactive/ReactiveSecurityAutoConfiguration.class */
public class ReactiveSecurityAutoConfiguration {

    @Configuration
    @EnableWebFluxSecurity
    @ConditionalOnMissingBean({WebFilterChainProxy.class})
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/security/reactive/ReactiveSecurityAutoConfiguration$EnableWebFluxSecurityConfiguration.class */
    static class EnableWebFluxSecurityConfiguration {
        EnableWebFluxSecurityConfiguration() {
        }
    }
}
