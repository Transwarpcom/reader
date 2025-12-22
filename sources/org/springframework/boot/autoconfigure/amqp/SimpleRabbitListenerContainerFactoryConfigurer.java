package org.springframework.boot.autoconfigure.amqp;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.PropertyMapper;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/amqp/SimpleRabbitListenerContainerFactoryConfigurer.class */
public final class SimpleRabbitListenerContainerFactoryConfigurer extends AbstractRabbitListenerContainerFactoryConfigurer<SimpleRabbitListenerContainerFactory> {
    @Override // org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer
    public void configure(SimpleRabbitListenerContainerFactory factory, ConnectionFactory connectionFactory) {
        PropertyMapper map = PropertyMapper.get();
        RabbitProperties.SimpleContainer config = getRabbitProperties().getListener().getSimple();
        configure(factory, connectionFactory, config);
        config.getClass();
        PropertyMapper.Source sourceWhenNonNull = map.from(config::getConcurrency).whenNonNull();
        factory.getClass();
        sourceWhenNonNull.to(factory::setConcurrentConsumers);
        config.getClass();
        PropertyMapper.Source sourceWhenNonNull2 = map.from(config::getMaxConcurrency).whenNonNull();
        factory.getClass();
        sourceWhenNonNull2.to(factory::setMaxConcurrentConsumers);
        config.getClass();
        PropertyMapper.Source sourceWhenNonNull3 = map.from(config::getTransactionSize).whenNonNull();
        factory.getClass();
        sourceWhenNonNull3.to(factory::setTxSize);
    }
}
