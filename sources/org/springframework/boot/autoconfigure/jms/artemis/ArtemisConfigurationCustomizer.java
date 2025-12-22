package org.springframework.boot.autoconfigure.jms.artemis;

import org.apache.activemq.artemis.core.config.Configuration;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/jms/artemis/ArtemisConfigurationCustomizer.class */
public interface ArtemisConfigurationCustomizer {
    void customize(Configuration configuration);
}
