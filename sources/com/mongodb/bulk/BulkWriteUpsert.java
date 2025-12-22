package com.mongodb.bulk;

import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/bulk/BulkWriteUpsert.class */
public class BulkWriteUpsert {
    private final int index;
    private final BsonValue id;

    public BulkWriteUpsert(int index, BsonValue id) {
        this.index = index;
        this.id = id;
    }

    public int getIndex() {
        return this.index;
    }

    public BsonValue getId() {
        return this.id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BulkWriteUpsert that = (BulkWriteUpsert) o;
        if (this.index != that.index || !this.id.equals(that.id)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.index;
        return (31 * result) + this.id.hashCode();
    }

    public String toString() {
        return "BulkWriteUpsert{index=" + this.index + ", id=" + this.id + '}';
    }
}
