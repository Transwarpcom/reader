package org.yaml.snakeyaml.error;

/* loaded from: reader.jar:BOOT-INF/lib/snakeyaml-1.23.jar:org/yaml/snakeyaml/error/YAMLException.class */
public class YAMLException extends RuntimeException {
    private static final long serialVersionUID = -4738336175050337570L;

    public YAMLException(String message) {
        super(message);
    }

    public YAMLException(Throwable cause) {
        super(cause);
    }

    public YAMLException(String message, Throwable cause) {
        super(message, cause);
    }
}
