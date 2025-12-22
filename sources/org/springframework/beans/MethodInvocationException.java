package org.springframework.beans;

import java.beans.PropertyChangeEvent;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/MethodInvocationException.class */
public class MethodInvocationException extends PropertyAccessException {
    public static final String ERROR_CODE = "methodInvocation";

    public MethodInvocationException(PropertyChangeEvent propertyChangeEvent, Throwable cause) {
        super(propertyChangeEvent, "Property '" + propertyChangeEvent.getPropertyName() + "' threw exception", cause);
    }

    @Override // org.springframework.beans.PropertyAccessException
    public String getErrorCode() {
        return ERROR_CODE;
    }
}
