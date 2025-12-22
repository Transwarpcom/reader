package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/ReplaceOneModel.class */
public final class ReplaceOneModel<T> extends WriteModel<T> {
    private final Bson filter;
    private final T replacement;
    private final ReplaceOptions options;

    public ReplaceOneModel(Bson filter, T replacement) {
        this(filter, replacement, new ReplaceOptions());
    }

    @Deprecated
    public ReplaceOneModel(Bson filter, T replacement, UpdateOptions options) {
        this(filter, replacement, ReplaceOptions.createReplaceOptions(options));
    }

    public ReplaceOneModel(Bson bson, T t, ReplaceOptions replaceOptions) {
        this.filter = (Bson) Assertions.notNull("filter", bson);
        this.options = (ReplaceOptions) Assertions.notNull("options", replaceOptions);
        this.replacement = (T) Assertions.notNull("replacement", t);
    }

    public Bson getFilter() {
        return this.filter;
    }

    public T getReplacement() {
        return this.replacement;
    }

    @Deprecated
    public UpdateOptions getOptions() {
        return new UpdateOptions().bypassDocumentValidation(this.options.getBypassDocumentValidation()).collation(this.options.getCollation()).upsert(this.options.isUpsert());
    }

    public ReplaceOptions getReplaceOptions() {
        return this.options;
    }

    public String toString() {
        return "ReplaceOneModel{filter=" + this.filter + ", replacement=" + this.replacement + ", options=" + this.options + '}';
    }
}
