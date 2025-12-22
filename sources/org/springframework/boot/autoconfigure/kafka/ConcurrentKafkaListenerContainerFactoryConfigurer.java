package org.springframework.boot.autoconfigure.kafka;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.AfterRollbackProcessor;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.transaction.KafkaAwareTransactionManager;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/kafka/ConcurrentKafkaListenerContainerFactoryConfigurer.class */
public class ConcurrentKafkaListenerContainerFactoryConfigurer {
    private KafkaProperties properties;
    private RecordMessageConverter messageConverter;
    private KafkaTemplate<Object, Object> replyTemplate;
    private KafkaAwareTransactionManager<Object, Object> transactionManager;
    private ErrorHandler errorHandler;
    private AfterRollbackProcessor<Object, Object> afterRollbackProcessor;

    void setKafkaProperties(KafkaProperties properties) {
        this.properties = properties;
    }

    void setMessageConverter(RecordMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    void setReplyTemplate(KafkaTemplate<Object, Object> replyTemplate) {
        this.replyTemplate = replyTemplate;
    }

    void setTransactionManager(KafkaAwareTransactionManager<Object, Object> transactionManager) {
        this.transactionManager = transactionManager;
    }

    void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    void setAfterRollbackProcessor(AfterRollbackProcessor<Object, Object> afterRollbackProcessor) {
        this.afterRollbackProcessor = afterRollbackProcessor;
    }

    public void configure(ConcurrentKafkaListenerContainerFactory<Object, Object> listenerFactory, ConsumerFactory<Object, Object> consumerFactory) {
        listenerFactory.setConsumerFactory(consumerFactory);
        configureListenerFactory(listenerFactory);
        configureContainer(listenerFactory.getContainerProperties());
    }

    private void configureListenerFactory(ConcurrentKafkaListenerContainerFactory<Object, Object> factory) {
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        KafkaProperties.Listener properties = this.properties.getListener();
        properties.getClass();
        PropertyMapper.Source sourceFrom = map.from(properties::getConcurrency);
        factory.getClass();
        sourceFrom.to(factory::setConcurrency);
        PropertyMapper.Source sourceFrom2 = map.from((PropertyMapper) this.messageConverter);
        factory.getClass();
        sourceFrom2.to((v1) -> {
            r1.setMessageConverter(v1);
        });
        PropertyMapper.Source sourceFrom3 = map.from((PropertyMapper) this.replyTemplate);
        factory.getClass();
        sourceFrom3.to(factory::setReplyTemplate);
        properties.getClass();
        map.from(properties::getType).whenEqualTo(KafkaProperties.Listener.Type.BATCH).toCall(() -> {
            factory.setBatchListener(true);
        });
        PropertyMapper.Source sourceFrom4 = map.from((PropertyMapper) this.errorHandler);
        factory.getClass();
        sourceFrom4.to(factory::setErrorHandler);
        PropertyMapper.Source sourceFrom5 = map.from((PropertyMapper) this.afterRollbackProcessor);
        factory.getClass();
        sourceFrom5.to(factory::setAfterRollbackProcessor);
    }

    private void configureContainer(ContainerProperties container) {
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        KafkaProperties.Listener properties = this.properties.getListener();
        properties.getClass();
        PropertyMapper.Source sourceFrom = map.from(properties::getAckMode);
        container.getClass();
        sourceFrom.to(container::setAckMode);
        properties.getClass();
        PropertyMapper.Source sourceFrom2 = map.from(properties::getClientId);
        container.getClass();
        sourceFrom2.to(container::setClientId);
        properties.getClass();
        PropertyMapper.Source sourceFrom3 = map.from(properties::getAckCount);
        container.getClass();
        sourceFrom3.to((v1) -> {
            r1.setAckCount(v1);
        });
        properties.getClass();
        PropertyMapper.Source sourceAs = map.from(properties::getAckTime).as((v0) -> {
            return v0.toMillis();
        });
        container.getClass();
        sourceAs.to((v1) -> {
            r1.setAckTime(v1);
        });
        properties.getClass();
        PropertyMapper.Source sourceAs2 = map.from(properties::getPollTimeout).as((v0) -> {
            return v0.toMillis();
        });
        container.getClass();
        sourceAs2.to((v1) -> {
            r1.setPollTimeout(v1);
        });
        properties.getClass();
        PropertyMapper.Source sourceFrom4 = map.from(properties::getNoPollThreshold);
        container.getClass();
        sourceFrom4.to((v1) -> {
            r1.setNoPollThreshold(v1);
        });
        properties.getClass();
        PropertyMapper.Source sourceAs3 = map.from(properties::getIdleEventInterval).as((v0) -> {
            return v0.toMillis();
        });
        container.getClass();
        sourceAs3.to(container::setIdleEventInterval);
        properties.getClass();
        PropertyMapper.Source sourceAs4 = map.from(properties::getMonitorInterval).as((v0) -> {
            return v0.getSeconds();
        }).as((v0) -> {
            return v0.intValue();
        });
        container.getClass();
        sourceAs4.to((v1) -> {
            r1.setMonitorInterval(v1);
        });
        properties.getClass();
        PropertyMapper.Source sourceFrom5 = map.from(properties::getLogContainerConfig);
        container.getClass();
        sourceFrom5.to((v1) -> {
            r1.setLogContainerConfig(v1);
        });
        PropertyMapper.Source sourceFrom6 = map.from((PropertyMapper) this.transactionManager);
        container.getClass();
        sourceFrom6.to((v1) -> {
            r1.setTransactionManager(v1);
        });
    }
}
