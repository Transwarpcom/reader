package org.springframework.context.weaving;

import org.springframework.beans.factory.Aware;
import org.springframework.instrument.classloading.LoadTimeWeaver;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/context/weaving/LoadTimeWeaverAware.class */
public interface LoadTimeWeaverAware extends Aware {
    void setLoadTimeWeaver(LoadTimeWeaver loadTimeWeaver);
}
