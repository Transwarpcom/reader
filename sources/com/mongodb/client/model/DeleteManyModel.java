package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/DeleteManyModel.class */
public final class DeleteManyModel<T> extends WriteModel<T> {
    private final Bson filter;
    private final DeleteOptions options;

    public DeleteManyModel(Bson filter) {
        this(filter, new DeleteOptions());
    }

    public DeleteManyModel(Bson filter, DeleteOptions options) {
        this.filter = (Bson) Assertions.notNull("filter", filter);
        this.options = (DeleteOptions) Assertions.notNull("options", options);
    }

    public Bson getFilter() {
        return this.filter;
    }

    public DeleteOptions getOptions() {
        return this.options;
    }

    public String toString() {
        return "DeleteManyModel{filter=" + this.filter + ", options=" + this.options + '}';
    }
}
