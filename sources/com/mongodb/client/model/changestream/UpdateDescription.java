package com.mongodb.client.model.changestream;

import com.mongodb.lang.Nullable;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/changestream/UpdateDescription.class */
public final class UpdateDescription {
    private final List<String> removedFields;
    private final BsonDocument updatedFields;

    @BsonCreator
    public UpdateDescription(@Nullable @BsonProperty("removedFields") List<String> removedFields, @Nullable @BsonProperty("updatedFields") BsonDocument updatedFields) {
        this.removedFields = removedFields;
        this.updatedFields = updatedFields;
    }

    @Nullable
    public List<String> getRemovedFields() {
        return this.removedFields;
    }

    @Nullable
    public BsonDocument getUpdatedFields() {
        return this.updatedFields;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateDescription that = (UpdateDescription) o;
        if (this.removedFields != null) {
            if (!this.removedFields.equals(that.getRemovedFields())) {
                return false;
            }
        } else if (that.getRemovedFields() != null) {
            return false;
        }
        if (this.updatedFields != null) {
            if (!this.updatedFields.equals(that.getUpdatedFields())) {
                return false;
            }
            return true;
        }
        if (that.getUpdatedFields() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.removedFields != null ? this.removedFields.hashCode() : 0;
        return (31 * result) + (this.updatedFields != null ? this.updatedFields.hashCode() : 0);
    }

    public String toString() {
        return "UpdateDescription{removedFields=" + this.removedFields + ", updatedFields=" + this.updatedFields + "}";
    }
}
