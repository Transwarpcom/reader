package org.springframework.boot.web.client;

import org.springframework.web.client.RestTemplate;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/client/RestTemplateCustomizer.class */
public interface RestTemplateCustomizer {
    void customize(RestTemplate restTemplate);
}
