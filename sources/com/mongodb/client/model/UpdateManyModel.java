package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/UpdateManyModel.class */
public final class UpdateManyModel<T> extends WriteModel<T> {
    private final Bson filter;
    private final Bson update;
    private final UpdateOptions options;

    public UpdateManyModel(Bson filter, Bson update) {
        this(filter, update, new UpdateOptions());
    }

    public UpdateManyModel(Bson filter, Bson update, UpdateOptions options) {
        this.filter = (Bson) Assertions.notNull("filter", filter);
        this.update = (Bson) Assertions.notNull("update", update);
        this.options = (UpdateOptions) Assertions.notNull("options", options);
    }

    public Bson getFilter() {
        return this.filter;
    }

    public Bson getUpdate() {
        return this.update;
    }

    public UpdateOptions getOptions() {
        return this.options;
    }

    public String toString() {
        return "UpdateManyModel{filter=" + this.filter + ", update=" + this.update + ", options=" + this.options + '}';
    }
}
