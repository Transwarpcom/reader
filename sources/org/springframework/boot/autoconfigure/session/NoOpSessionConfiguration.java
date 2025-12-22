package org.springframework.boot.autoconfigure.session;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.SessionRepository;

@ConditionalOnMissingBean({SessionRepository.class})
@Configuration
@Conditional({ServletSessionCondition.class})
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/session/NoOpSessionConfiguration.class */
class NoOpSessionConfiguration {
    NoOpSessionConfiguration() {
    }
}
