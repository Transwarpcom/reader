package com.mongodb.client.gridfs.model;

import com.jayway.jsonpath.internal.function.text.Length;
import com.mongodb.MongoGridFSException;
import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import java.util.Date;
import java.util.List;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/gridfs/model/GridFSFile.class */
public final class GridFSFile {
    private final BsonValue id;
    private final String filename;
    private final long length;
    private final int chunkSize;
    private final Date uploadDate;
    private final String md5;
    private final Document metadata;
    private final Document extraElements;

    public GridFSFile(BsonValue id, String filename, long length, int chunkSize, Date uploadDate, @Nullable String md5, Document metadata) {
        this(id, filename, length, chunkSize, uploadDate, md5, metadata, null);
    }

    public GridFSFile(BsonValue id, String filename, long length, int chunkSize, Date uploadDate, @Nullable String md5, @Nullable Document metadata, @Nullable Document extraElements) {
        this.id = (BsonValue) Assertions.notNull("id", id);
        this.filename = (String) Assertions.notNull("filename", filename);
        this.length = ((Long) Assertions.notNull(Length.TOKEN_NAME, Long.valueOf(length))).longValue();
        this.chunkSize = ((Integer) Assertions.notNull("chunkSize", Integer.valueOf(chunkSize))).intValue();
        this.uploadDate = (Date) Assertions.notNull("uploadDate", uploadDate);
        this.md5 = md5;
        this.metadata = (metadata == null || !metadata.isEmpty()) ? metadata : null;
        this.extraElements = extraElements;
    }

    public ObjectId getObjectId() {
        if (!this.id.isObjectId()) {
            throw new MongoGridFSException("Custom id type used for this GridFS file");
        }
        return this.id.asObjectId().getValue();
    }

    public BsonValue getId() {
        return this.id;
    }

    public String getFilename() {
        return this.filename;
    }

    public long getLength() {
        return this.length;
    }

    public int getChunkSize() {
        return this.chunkSize;
    }

    public Date getUploadDate() {
        return this.uploadDate;
    }

    @Nullable
    @Deprecated
    public String getMD5() {
        return this.md5;
    }

    @Nullable
    public Document getMetadata() {
        return this.metadata;
    }

    @Nullable
    @Deprecated
    public Document getExtraElements() {
        return this.extraElements;
    }

    @Deprecated
    public String getContentType() {
        if (this.extraElements != null && this.extraElements.containsKey("contentType")) {
            return this.extraElements.getString("contentType");
        }
        throw new MongoGridFSException("No contentType data for this GridFS file");
    }

    @Deprecated
    public List<String> getAliases() {
        if (this.extraElements != null && this.extraElements.containsKey("aliases")) {
            return (List) this.extraElements.get("aliases");
        }
        throw new MongoGridFSException("No aliases data for this GridFS file");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GridFSFile that = (GridFSFile) o;
        if (this.id != null) {
            if (!this.id.equals(that.id)) {
                return false;
            }
        } else if (that.id != null) {
            return false;
        }
        if (!this.filename.equals(that.filename) || this.length != that.length || this.chunkSize != that.chunkSize || !this.uploadDate.equals(that.uploadDate)) {
            return false;
        }
        if (this.md5 != null) {
            if (!this.md5.equals(that.md5)) {
                return false;
            }
        } else if (that.md5 != null) {
            return false;
        }
        if (this.metadata != null) {
            if (!this.metadata.equals(that.metadata)) {
                return false;
            }
        } else if (that.metadata != null) {
            return false;
        }
        if (this.extraElements != null) {
            if (!this.extraElements.equals(that.extraElements)) {
                return false;
            }
            return true;
        }
        if (that.extraElements != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + this.filename.hashCode())) + ((int) (this.length ^ (this.length >>> 32))))) + this.chunkSize)) + this.uploadDate.hashCode())) + (this.md5 != null ? this.md5.hashCode() : 0))) + (this.metadata != null ? this.metadata.hashCode() : 0))) + (this.extraElements != null ? this.extraElements.hashCode() : 0);
    }

    public String toString() {
        return "GridFSFile{id=" + this.id + ", filename='" + this.filename + "', length=" + this.length + ", chunkSize=" + this.chunkSize + ", uploadDate=" + this.uploadDate + ", md5='" + this.md5 + "', metadata=" + this.metadata + ", extraElements='" + this.extraElements + "'}";
    }
}
