package org.springframework.jmx;

import org.springframework.core.NestedRuntimeException;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/jmx/JmxException.class */
public class JmxException extends NestedRuntimeException {
    public JmxException(String msg) {
        super(msg);
    }

    public JmxException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
