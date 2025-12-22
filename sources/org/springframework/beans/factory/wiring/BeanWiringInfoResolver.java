package org.springframework.beans.factory.wiring;

import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/wiring/BeanWiringInfoResolver.class */
public interface BeanWiringInfoResolver {
    @Nullable
    BeanWiringInfo resolveWiringInfo(Object obj);
}
