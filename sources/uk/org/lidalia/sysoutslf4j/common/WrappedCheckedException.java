package uk.org.lidalia.sysoutslf4j.common;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/common/WrappedCheckedException.class */
public class WrappedCheckedException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public WrappedCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrappedCheckedException(Throwable cause) {
        super(cause);
    }
}
