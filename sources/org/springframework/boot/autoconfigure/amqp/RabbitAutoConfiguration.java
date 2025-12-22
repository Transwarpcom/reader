package org.springframework.boot.autoconfigure.amqp;

import com.rabbitmq.client.Channel;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableConfigurationProperties({RabbitProperties.class})
@Configuration
@ConditionalOnClass({RabbitTemplate.class, Channel.class})
@Import({RabbitAnnotationDrivenConfiguration.class})
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/amqp/RabbitAutoConfiguration.class */
public class RabbitAutoConfiguration {

    @ConditionalOnMissingBean({ConnectionFactory.class})
    @Configuration
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/amqp/RabbitAutoConfiguration$RabbitConnectionFactoryCreator.class */
    protected static class RabbitConnectionFactoryCreator {
        protected RabbitConnectionFactoryCreator() {
        }

        @Bean
        public CachingConnectionFactory rabbitConnectionFactory(RabbitProperties properties, ObjectProvider<ConnectionNameStrategy> connectionNameStrategy) throws Exception {
            PropertyMapper map = PropertyMapper.get();
            CachingConnectionFactory factory = new CachingConnectionFactory((com.rabbitmq.client.ConnectionFactory) getRabbitConnectionFactoryBean(properties).getObject());
            properties.getClass();
            PropertyMapper.Source sourceFrom = map.from(properties::determineAddresses);
            factory.getClass();
            sourceFrom.to(factory::setAddresses);
            properties.getClass();
            PropertyMapper.Source sourceFrom2 = map.from(properties::isPublisherConfirms);
            factory.getClass();
            sourceFrom2.to((v1) -> {
                r1.setPublisherConfirms(v1);
            });
            properties.getClass();
            PropertyMapper.Source sourceFrom3 = map.from(properties::isPublisherReturns);
            factory.getClass();
            sourceFrom3.to((v1) -> {
                r1.setPublisherReturns(v1);
            });
            RabbitProperties.Cache.Channel channel = properties.getCache().getChannel();
            channel.getClass();
            PropertyMapper.Source sourceWhenNonNull = map.from(channel::getSize).whenNonNull();
            factory.getClass();
            sourceWhenNonNull.to((v1) -> {
                r1.setChannelCacheSize(v1);
            });
            channel.getClass();
            PropertyMapper.Source sourceAs = map.from(channel::getCheckoutTimeout).whenNonNull().as((v0) -> {
                return v0.toMillis();
            });
            factory.getClass();
            sourceAs.to((v1) -> {
                r1.setChannelCheckoutTimeout(v1);
            });
            RabbitProperties.Cache.Connection connection = properties.getCache().getConnection();
            connection.getClass();
            PropertyMapper.Source sourceWhenNonNull2 = map.from(connection::getMode).whenNonNull();
            factory.getClass();
            sourceWhenNonNull2.to(factory::setCacheMode);
            connection.getClass();
            PropertyMapper.Source sourceWhenNonNull3 = map.from(connection::getSize).whenNonNull();
            factory.getClass();
            sourceWhenNonNull3.to((v1) -> {
                r1.setConnectionCacheSize(v1);
            });
            connectionNameStrategy.getClass();
            PropertyMapper.Source sourceWhenNonNull4 = map.from(connectionNameStrategy::getIfUnique).whenNonNull();
            factory.getClass();
            sourceWhenNonNull4.to(factory::setConnectionNameStrategy);
            return factory;
        }

        private RabbitConnectionFactoryBean getRabbitConnectionFactoryBean(RabbitProperties properties) throws Exception {
            PropertyMapper map = PropertyMapper.get();
            RabbitConnectionFactoryBean factory = new RabbitConnectionFactoryBean();
            properties.getClass();
            PropertyMapper.Source sourceWhenNonNull = map.from(properties::determineHost).whenNonNull();
            factory.getClass();
            sourceWhenNonNull.to(factory::setHost);
            properties.getClass();
            PropertyMapper.Source sourceFrom = map.from(properties::determinePort);
            factory.getClass();
            sourceFrom.to((v1) -> {
                r1.setPort(v1);
            });
            properties.getClass();
            PropertyMapper.Source sourceWhenNonNull2 = map.from(properties::determineUsername).whenNonNull();
            factory.getClass();
            sourceWhenNonNull2.to(factory::setUsername);
            properties.getClass();
            PropertyMapper.Source sourceWhenNonNull3 = map.from(properties::determinePassword).whenNonNull();
            factory.getClass();
            sourceWhenNonNull3.to(factory::setPassword);
            properties.getClass();
            PropertyMapper.Source sourceWhenNonNull4 = map.from(properties::determineVirtualHost).whenNonNull();
            factory.getClass();
            sourceWhenNonNull4.to(factory::setVirtualHost);
            properties.getClass();
            PropertyMapper.Source<Integer> sourceAsInt = map.from(properties::getRequestedHeartbeat).whenNonNull().asInt((v0) -> {
                return v0.getSeconds();
            });
            factory.getClass();
            sourceAsInt.to((v1) -> {
                r1.setRequestedHeartbeat(v1);
            });
            RabbitProperties.Ssl ssl = properties.getSsl();
            if (ssl.isEnabled()) {
                factory.setUseSSL(true);
                ssl.getClass();
                PropertyMapper.Source sourceWhenNonNull5 = map.from(ssl::getAlgorithm).whenNonNull();
                factory.getClass();
                sourceWhenNonNull5.to(factory::setSslAlgorithm);
                ssl.getClass();
                PropertyMapper.Source sourceFrom2 = map.from(ssl::getKeyStoreType);
                factory.getClass();
                sourceFrom2.to(factory::setKeyStoreType);
                ssl.getClass();
                PropertyMapper.Source sourceFrom3 = map.from(ssl::getKeyStore);
                factory.getClass();
                sourceFrom3.to(factory::setKeyStore);
                ssl.getClass();
                PropertyMapper.Source sourceFrom4 = map.from(ssl::getKeyStorePassword);
                factory.getClass();
                sourceFrom4.to(factory::setKeyStorePassphrase);
                ssl.getClass();
                PropertyMapper.Source sourceFrom5 = map.from(ssl::getTrustStoreType);
                factory.getClass();
                sourceFrom5.to(factory::setTrustStoreType);
                ssl.getClass();
                PropertyMapper.Source sourceFrom6 = map.from(ssl::getTrustStore);
                factory.getClass();
                sourceFrom6.to(factory::setTrustStore);
                ssl.getClass();
                PropertyMapper.Source sourceFrom7 = map.from(ssl::getTrustStorePassword);
                factory.getClass();
                sourceFrom7.to(factory::setTrustStorePassphrase);
                ssl.getClass();
                map.from(ssl::isValidateServerCertificate).to(validate -> {
                    factory.setSkipServerCertificateValidation(!validate.booleanValue());
                });
                ssl.getClass();
                PropertyMapper.Source sourceFrom8 = map.from(ssl::getVerifyHostname);
                factory.getClass();
                sourceFrom8.to((v1) -> {
                    r1.setEnableHostnameVerification(v1);
                });
            }
            properties.getClass();
            PropertyMapper.Source<Integer> sourceAsInt2 = map.from(properties::getConnectionTimeout).whenNonNull().asInt((v0) -> {
                return v0.toMillis();
            });
            factory.getClass();
            sourceAsInt2.to((v1) -> {
                r1.setConnectionTimeout(v1);
            });
            factory.afterPropertiesSet();
            return factory;
        }
    }

    @Configuration
    @Import({RabbitConnectionFactoryCreator.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/amqp/RabbitAutoConfiguration$RabbitTemplateConfiguration.class */
    protected static class RabbitTemplateConfiguration {
        private final RabbitProperties properties;
        private final ObjectProvider<MessageConverter> messageConverter;
        private final ObjectProvider<RabbitRetryTemplateCustomizer> retryTemplateCustomizers;

        public RabbitTemplateConfiguration(RabbitProperties properties, ObjectProvider<MessageConverter> messageConverter, ObjectProvider<RabbitRetryTemplateCustomizer> retryTemplateCustomizers) {
            this.properties = properties;
            this.messageConverter = messageConverter;
            this.retryTemplateCustomizers = retryTemplateCustomizers;
        }

        @ConditionalOnMissingBean
        @Bean
        @ConditionalOnSingleCandidate(ConnectionFactory.class)
        public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) throws BeansException {
            PropertyMapper map = PropertyMapper.get();
            RabbitTemplate template = new RabbitTemplate(connectionFactory);
            MessageConverter messageConverter = this.messageConverter.getIfUnique();
            if (messageConverter != null) {
                template.setMessageConverter(messageConverter);
            }
            template.setMandatory(determineMandatoryFlag());
            RabbitProperties.Template properties = this.properties.getTemplate();
            if (properties.getRetry().isEnabled()) {
                template.setRetryTemplate(new RetryTemplateFactory((List) this.retryTemplateCustomizers.orderedStream().collect(Collectors.toList())).createRetryTemplate(properties.getRetry(), RabbitRetryTemplateCustomizer.Target.SENDER));
            }
            properties.getClass();
            PropertyMapper.Source sourceAs = map.from(properties::getReceiveTimeout).whenNonNull().as((v0) -> {
                return v0.toMillis();
            });
            template.getClass();
            sourceAs.to((v1) -> {
                r1.setReceiveTimeout(v1);
            });
            properties.getClass();
            PropertyMapper.Source sourceAs2 = map.from(properties::getReplyTimeout).whenNonNull().as((v0) -> {
                return v0.toMillis();
            });
            template.getClass();
            sourceAs2.to((v1) -> {
                r1.setReplyTimeout(v1);
            });
            properties.getClass();
            PropertyMapper.Source sourceFrom = map.from(properties::getExchange);
            template.getClass();
            sourceFrom.to(template::setExchange);
            properties.getClass();
            PropertyMapper.Source sourceFrom2 = map.from(properties::getRoutingKey);
            template.getClass();
            sourceFrom2.to(template::setRoutingKey);
            properties.getClass();
            PropertyMapper.Source sourceWhenNonNull = map.from(properties::getDefaultReceiveQueue).whenNonNull();
            template.getClass();
            sourceWhenNonNull.to(template::setDefaultReceiveQueue);
            return template;
        }

        private boolean determineMandatoryFlag() {
            Boolean mandatory = this.properties.getTemplate().getMandatory();
            return mandatory != null ? mandatory.booleanValue() : this.properties.isPublisherReturns();
        }

        @ConditionalOnSingleCandidate(ConnectionFactory.class)
        @ConditionalOnMissingBean
        @ConditionalOnProperty(prefix = "spring.rabbitmq", name = {"dynamic"}, matchIfMissing = true)
        @Bean
        public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
            return new RabbitAdmin(connectionFactory);
        }
    }

    @Configuration
    @ConditionalOnClass({RabbitMessagingTemplate.class})
    @ConditionalOnMissingBean({RabbitMessagingTemplate.class})
    @Import({RabbitTemplateConfiguration.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/amqp/RabbitAutoConfiguration$MessagingTemplateConfiguration.class */
    protected static class MessagingTemplateConfiguration {
        protected MessagingTemplateConfiguration() {
        }

        @Bean
        @ConditionalOnSingleCandidate(RabbitTemplate.class)
        public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
            return new RabbitMessagingTemplate(rabbitTemplate);
        }
    }
}
