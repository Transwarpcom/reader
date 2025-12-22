package org.springframework.boot.autoconfigure.hazelcast;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ResourceCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.Assert;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/hazelcast/HazelcastConfigResourceCondition.class */
public abstract class HazelcastConfigResourceCondition extends ResourceCondition {
    private final String configSystemProperty;

    protected HazelcastConfigResourceCondition(String configSystemProperty, String... resourceLocations) {
        super("Hazelcast", "spring.hazelcast.config", resourceLocations);
        Assert.notNull(configSystemProperty, "ConfigSystemProperty must not be null");
        this.configSystemProperty = configSystemProperty;
    }

    @Override // org.springframework.boot.autoconfigure.condition.ResourceCondition
    protected ConditionOutcome getResourceOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (System.getProperty(this.configSystemProperty) != null) {
            return ConditionOutcome.match(startConditionMessage().because("System property '" + this.configSystemProperty + "' is set."));
        }
        return super.getResourceOutcome(context, metadata);
    }
}
