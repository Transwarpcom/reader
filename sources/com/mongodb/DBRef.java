package com.mongodb;

import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import java.io.Serializable;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/DBRef.class */
public class DBRef implements Serializable {
    private static final long serialVersionUID = -849581217713362618L;
    private final Object id;
    private final String collectionName;
    private final String databaseName;

    public DBRef(String collectionName, Object id) {
        this(null, collectionName, id);
    }

    public DBRef(@Nullable String databaseName, String collectionName, Object id) {
        this.id = Assertions.notNull("id", id);
        this.collectionName = (String) Assertions.notNull("collectionName", collectionName);
        this.databaseName = databaseName;
    }

    public Object getId() {
        return this.id;
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    @Nullable
    public String getDatabaseName() {
        return this.databaseName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DBRef dbRef = (DBRef) o;
        if (!this.id.equals(dbRef.id) || !this.collectionName.equals(dbRef.collectionName)) {
            return false;
        }
        if (this.databaseName != null) {
            if (!this.databaseName.equals(dbRef.databaseName)) {
                return false;
            }
            return true;
        }
        if (dbRef.databaseName != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.id.hashCode();
        return (31 * ((31 * result) + this.collectionName.hashCode())) + (this.databaseName != null ? this.databaseName.hashCode() : 0);
    }

    public String toString() {
        return "{ \"$ref\" : \"" + this.collectionName + "\", \"$id\" : \"" + this.id + OperatorName.SHOW_TEXT_LINE_AND_SPACE + (this.databaseName == null ? "" : ", \"$db\" : \"" + this.databaseName + OperatorName.SHOW_TEXT_LINE_AND_SPACE) + " }";
    }
}
