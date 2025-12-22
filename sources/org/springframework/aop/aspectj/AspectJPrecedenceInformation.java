package org.springframework.aop.aspectj;

import org.springframework.core.Ordered;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/aspectj/AspectJPrecedenceInformation.class */
public interface AspectJPrecedenceInformation extends Ordered {
    String getAspectName();

    int getDeclarationOrder();

    boolean isBeforeAdvice();

    boolean isAfterAdvice();
}
