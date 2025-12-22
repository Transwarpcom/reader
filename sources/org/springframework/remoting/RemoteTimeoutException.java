package org.springframework.remoting;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/remoting/RemoteTimeoutException.class */
public class RemoteTimeoutException extends RemoteAccessException {
    public RemoteTimeoutException(String msg) {
        super(msg);
    }

    public RemoteTimeoutException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
