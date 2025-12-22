package com.mongodb;

import org.bson.Document;
import org.bson.Transformer;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/DocumentToDBRefTransformer.class */
public final class DocumentToDBRefTransformer implements Transformer {
    @Override // org.bson.Transformer
    public Object transform(Object value) {
        if (value instanceof Document) {
            Document document = (Document) value;
            if (document.containsKey("$id") && document.containsKey("$ref")) {
                return new DBRef((String) document.get("$db"), (String) document.get("$ref"), document.get("$id"));
            }
        }
        return value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }
}
