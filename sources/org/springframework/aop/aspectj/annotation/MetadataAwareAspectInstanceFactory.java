package org.springframework.aop.aspectj.annotation;

import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/aspectj/annotation/MetadataAwareAspectInstanceFactory.class */
public interface MetadataAwareAspectInstanceFactory extends AspectInstanceFactory {
    AspectMetadata getAspectMetadata();

    @Nullable
    Object getAspectCreationMutex();
}
