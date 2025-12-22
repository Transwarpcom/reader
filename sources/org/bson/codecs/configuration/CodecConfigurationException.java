package org.bson.codecs.configuration;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/configuration/CodecConfigurationException.class */
public class CodecConfigurationException extends RuntimeException {
    private static final long serialVersionUID = -5656763889202800056L;

    public CodecConfigurationException(String msg) {
        super(msg);
    }

    public CodecConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
