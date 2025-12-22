package org.springframework.beans.factory;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/BeanIsNotAFactoryException.class */
public class BeanIsNotAFactoryException extends BeanNotOfRequiredTypeException {
    public BeanIsNotAFactoryException(String name, Class<?> actualType) {
        super(name, FactoryBean.class, actualType);
    }
}
