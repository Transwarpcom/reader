package org.springframework.boot.autoconfigure.amqp;

import org.springframework.retry.support.RetryTemplate;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/amqp/RabbitRetryTemplateCustomizer.class */
public interface RabbitRetryTemplateCustomizer {

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/amqp/RabbitRetryTemplateCustomizer$Target.class */
    public enum Target {
        SENDER,
        LISTENER
    }

    void customize(Target target, RetryTemplate retryTemplate);
}
