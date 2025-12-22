package org.springframework.cglib.transform.impl;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/cglib/transform/impl/InterceptFieldEnabled.class */
public interface InterceptFieldEnabled {
    void setInterceptFieldCallback(InterceptFieldCallback interceptFieldCallback);

    InterceptFieldCallback getInterceptFieldCallback();
}
