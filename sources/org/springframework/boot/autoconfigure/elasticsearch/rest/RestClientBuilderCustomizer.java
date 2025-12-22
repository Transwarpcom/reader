package org.springframework.boot.autoconfigure.elasticsearch.rest;

import org.elasticsearch.client.RestClientBuilder;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/elasticsearch/rest/RestClientBuilderCustomizer.class */
public interface RestClientBuilderCustomizer {
    void customize(RestClientBuilder builder);
}
