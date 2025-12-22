package org.springframework.beans.factory;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/BeanCreationNotAllowedException.class */
public class BeanCreationNotAllowedException extends BeanCreationException {
    public BeanCreationNotAllowedException(String beanName, String msg) {
        super(beanName, msg);
    }
}
