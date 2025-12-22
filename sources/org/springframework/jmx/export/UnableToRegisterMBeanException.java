package org.springframework.jmx.export;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/jmx/export/UnableToRegisterMBeanException.class */
public class UnableToRegisterMBeanException extends MBeanExportException {
    public UnableToRegisterMBeanException(String msg) {
        super(msg);
    }

    public UnableToRegisterMBeanException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
