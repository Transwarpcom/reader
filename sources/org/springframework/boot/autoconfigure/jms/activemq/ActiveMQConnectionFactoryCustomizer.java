package org.springframework.boot.autoconfigure.jms.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/jms/activemq/ActiveMQConnectionFactoryCustomizer.class */
public interface ActiveMQConnectionFactoryCustomizer {
    void customize(ActiveMQConnectionFactory factory);
}
