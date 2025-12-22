package org.yaml.snakeyaml.constructor;

import org.yaml.snakeyaml.error.Mark;

/* loaded from: reader.jar:BOOT-INF/lib/snakeyaml-1.23.jar:org/yaml/snakeyaml/constructor/DuplicateKeyException.class */
public class DuplicateKeyException extends ConstructorException {
    protected DuplicateKeyException(Mark contextMark, Object key, Mark problemMark) {
        super("while constructing a mapping", contextMark, "found duplicate key " + key.toString(), problemMark);
    }
}
