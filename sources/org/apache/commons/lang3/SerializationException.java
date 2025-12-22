package org.apache.commons.lang3;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/SerializationException.class */
public class SerializationException extends RuntimeException {
    private static final long serialVersionUID = 4029025366392702726L;

    public SerializationException() {
    }

    public SerializationException(String msg) {
        super(msg);
    }

    public SerializationException(Throwable cause) {
        super(cause);
    }

    public SerializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
