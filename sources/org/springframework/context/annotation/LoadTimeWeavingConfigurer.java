package org.springframework.context.annotation;

import org.springframework.instrument.classloading.LoadTimeWeaver;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/annotation/LoadTimeWeavingConfigurer.class */
public interface LoadTimeWeavingConfigurer {
    LoadTimeWeaver getLoadTimeWeaver();
}
