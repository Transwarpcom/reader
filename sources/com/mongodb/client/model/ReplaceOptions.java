package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import java.util.List;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/ReplaceOptions.class */
public class ReplaceOptions {
    private boolean upsert;
    private Boolean bypassDocumentValidation;
    private Collation collation;

    public static ReplaceOptions createReplaceOptions(UpdateOptions updateOptions) {
        Assertions.notNull("updateOptions", updateOptions);
        List<? extends Bson> arrayFilters = updateOptions.getArrayFilters();
        Assertions.isTrue("ArrayFilters should be empty.", arrayFilters == null || arrayFilters.isEmpty());
        return new ReplaceOptions().bypassDocumentValidation(updateOptions.getBypassDocumentValidation()).collation(updateOptions.getCollation()).upsert(updateOptions.isUpsert());
    }

    public boolean isUpsert() {
        return this.upsert;
    }

    public ReplaceOptions upsert(boolean upsert) {
        this.upsert = upsert;
        return this;
    }

    @Nullable
    public Boolean getBypassDocumentValidation() {
        return this.bypassDocumentValidation;
    }

    public ReplaceOptions bypassDocumentValidation(@Nullable Boolean bypassDocumentValidation) {
        this.bypassDocumentValidation = bypassDocumentValidation;
        return this;
    }

    @Nullable
    public Collation getCollation() {
        return this.collation;
    }

    public ReplaceOptions collation(@Nullable Collation collation) {
        this.collation = collation;
        return this;
    }

    public String toString() {
        return "ReplaceOptions{upsert=" + this.upsert + ", bypassDocumentValidation=" + this.bypassDocumentValidation + ", collation=" + this.collation + '}';
    }
}
