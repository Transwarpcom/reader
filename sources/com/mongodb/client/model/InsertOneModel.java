package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/InsertOneModel.class */
public final class InsertOneModel<T> extends WriteModel<T> {
    private final T document;

    public InsertOneModel(T t) {
        this.document = (T) Assertions.notNull("document", t);
    }

    public T getDocument() {
        return this.document;
    }

    public String toString() {
        return "InsertOneModel{document=" + this.document + '}';
    }
}
