package org.springframework.boot.autoconfigure.elasticsearch.jest;

import io.searchbox.client.config.HttpClientConfig;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/elasticsearch/jest/HttpClientConfigBuilderCustomizer.class */
public interface HttpClientConfigBuilderCustomizer {
    void customize(HttpClientConfig.Builder builder);
}
