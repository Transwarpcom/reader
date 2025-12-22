package org.springframework.boot.autoconfigure.couchbase;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.OnPropertyListCondition;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/couchbase/OnBootstrapHostsCondition.class */
class OnBootstrapHostsCondition extends OnPropertyListCondition {
    OnBootstrapHostsCondition() {
        super("spring.couchbase.bootstrap-hosts", () -> {
            return ConditionMessage.forCondition("Couchbase Bootstrap Hosts", new Object[0]);
        });
    }
}
