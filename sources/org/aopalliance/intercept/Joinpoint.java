package org.aopalliance.intercept;

import java.lang.reflect.AccessibleObject;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/aopalliance/intercept/Joinpoint.class */
public interface Joinpoint {
    Object proceed() throws Throwable;

    Object getThis();

    AccessibleObject getStaticPart();
}
