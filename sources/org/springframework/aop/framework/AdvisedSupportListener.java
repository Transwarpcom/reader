package org.springframework.aop.framework;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/framework/AdvisedSupportListener.class */
public interface AdvisedSupportListener {
    void activated(AdvisedSupport advisedSupport);

    void adviceChanged(AdvisedSupport advisedSupport);
}
