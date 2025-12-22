package org.springframework.aop.framework;

import org.springframework.core.NestedRuntimeException;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/framework/AopConfigException.class */
public class AopConfigException extends NestedRuntimeException {
    public AopConfigException(String msg) {
        super(msg);
    }

    public AopConfigException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
