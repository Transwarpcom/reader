package org.springframework.boot.autoconfigure.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableConfigurationProperties({HazelcastProperties.class})
@Configuration
@ConditionalOnClass({HazelcastInstance.class})
@Import({HazelcastClientConfiguration.class, HazelcastServerConfiguration.class})
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/hazelcast/HazelcastAutoConfiguration.class */
public class HazelcastAutoConfiguration {
}
