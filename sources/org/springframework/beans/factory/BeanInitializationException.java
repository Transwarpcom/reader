package org.springframework.beans.factory;

import org.springframework.beans.FatalBeanException;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/BeanInitializationException.class */
public class BeanInitializationException extends FatalBeanException {
    public BeanInitializationException(String msg) {
        super(msg);
    }

    public BeanInitializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
