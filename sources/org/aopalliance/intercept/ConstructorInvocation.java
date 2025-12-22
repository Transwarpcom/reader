package org.aopalliance.intercept;

import java.lang.reflect.Constructor;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/aopalliance/intercept/ConstructorInvocation.class */
public interface ConstructorInvocation extends Invocation {
    Constructor<?> getConstructor();
}
