package org.springframework.scheduling.concurrent;

import java.util.concurrent.ThreadFactory;
import org.springframework.util.CustomizableThreadCreator;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/scheduling/concurrent/CustomizableThreadFactory.class */
public class CustomizableThreadFactory extends CustomizableThreadCreator implements ThreadFactory {
    public CustomizableThreadFactory() {
    }

    public CustomizableThreadFactory(String threadNamePrefix) {
        super(threadNamePrefix);
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return createThread(runnable);
    }
}
