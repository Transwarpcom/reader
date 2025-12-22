package org.springframework.scheduling;

import java.util.Date;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/scheduling/TriggerContext.class */
public interface TriggerContext {
    @Nullable
    Date lastScheduledExecutionTime();

    @Nullable
    Date lastActualExecutionTime();

    @Nullable
    Date lastCompletionTime();
}
