package org.springframework.boot.web.servlet.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/servlet/support/ErrorPageFilterConfiguration.class */
class ErrorPageFilterConfiguration {
    ErrorPageFilterConfiguration() {
    }

    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }
}
