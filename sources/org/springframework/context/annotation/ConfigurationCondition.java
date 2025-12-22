package org.springframework.context.annotation;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/annotation/ConfigurationCondition.class */
public interface ConfigurationCondition extends Condition {

    /* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/annotation/ConfigurationCondition$ConfigurationPhase.class */
    public enum ConfigurationPhase {
        PARSE_CONFIGURATION,
        REGISTER_BEAN
    }

    ConfigurationPhase getConfigurationPhase();
}
