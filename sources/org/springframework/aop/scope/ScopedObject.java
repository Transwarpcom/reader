package org.springframework.aop.scope;

import org.springframework.aop.RawTargetAccess;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/scope/ScopedObject.class */
public interface ScopedObject extends RawTargetAccess {
    Object getTargetObject();

    void removeFromScope();
}
