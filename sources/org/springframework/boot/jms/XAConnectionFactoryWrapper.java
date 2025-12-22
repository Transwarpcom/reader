package org.springframework.boot.jms;

import javax.jms.ConnectionFactory;
import javax.jms.XAConnectionFactory;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/jms/XAConnectionFactoryWrapper.class */
public interface XAConnectionFactoryWrapper {
    ConnectionFactory wrapConnectionFactory(XAConnectionFactory connectionFactory) throws Exception;
}
