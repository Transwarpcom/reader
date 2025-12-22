package org.springframework.boot.autoconfigure.data.redis;

import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/data/redis/JedisClientConfigurationBuilderCustomizer.class */
public interface JedisClientConfigurationBuilderCustomizer {
    void customize(JedisClientConfiguration.JedisClientConfigurationBuilder clientConfigurationBuilder);
}
