package com.mongodb.client.gridfs;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoGridFSException;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.model.GridFSDownloadByNameOptions;
import com.mongodb.client.gridfs.model.GridFSDownloadOptions;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.lang.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.bson.BsonDocument;
import org.bson.BsonObjectId;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/gridfs/GridFSBucketImpl.class */
final class GridFSBucketImpl implements GridFSBucket {
    private static final int DEFAULT_CHUNKSIZE_BYTES = 261120;
    private final String bucketName;
    private final int chunkSizeBytes;
    private final MongoCollection<GridFSFile> filesCollection;
    private final MongoCollection<Document> chunksCollection;
    private final boolean disableMD5;
    private volatile boolean checkedIndexes;

    GridFSBucketImpl(MongoDatabase database) {
        this(database, "fs");
    }

    GridFSBucketImpl(MongoDatabase database, String bucketName) {
        this((String) Assertions.notNull("bucketName", bucketName), DEFAULT_CHUNKSIZE_BYTES, getFilesCollection((MongoDatabase) Assertions.notNull("database", database), bucketName), getChunksCollection(database, bucketName), false);
    }

    GridFSBucketImpl(String bucketName, int chunkSizeBytes, MongoCollection<GridFSFile> filesCollection, MongoCollection<Document> chunksCollection, boolean disableMD5) {
        this.bucketName = (String) Assertions.notNull("bucketName", bucketName);
        this.chunkSizeBytes = chunkSizeBytes;
        this.filesCollection = (MongoCollection) Assertions.notNull("filesCollection", filesCollection);
        this.chunksCollection = (MongoCollection) Assertions.notNull("chunksCollection", chunksCollection);
        this.disableMD5 = disableMD5;
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public String getBucketName() {
        return this.bucketName;
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public int getChunkSizeBytes() {
        return this.chunkSizeBytes;
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public ReadPreference getReadPreference() {
        return this.filesCollection.getReadPreference();
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public WriteConcern getWriteConcern() {
        return this.filesCollection.getWriteConcern();
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public ReadConcern getReadConcern() {
        return this.filesCollection.getReadConcern();
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public boolean getDisableMD5() {
        return this.disableMD5;
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSBucket withChunkSizeBytes(int chunkSizeBytes) {
        return new GridFSBucketImpl(this.bucketName, chunkSizeBytes, this.filesCollection, this.chunksCollection, this.disableMD5);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSBucket withReadPreference(ReadPreference readPreference) {
        return new GridFSBucketImpl(this.bucketName, this.chunkSizeBytes, this.filesCollection.withReadPreference(readPreference), this.chunksCollection.withReadPreference(readPreference), this.disableMD5);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSBucket withWriteConcern(WriteConcern writeConcern) {
        return new GridFSBucketImpl(this.bucketName, this.chunkSizeBytes, this.filesCollection.withWriteConcern(writeConcern), this.chunksCollection.withWriteConcern(writeConcern), this.disableMD5);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSBucket withReadConcern(ReadConcern readConcern) {
        return new GridFSBucketImpl(this.bucketName, this.chunkSizeBytes, this.filesCollection.withReadConcern(readConcern), this.chunksCollection.withReadConcern(readConcern), this.disableMD5);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSBucket withDisableMD5(boolean disableMD5) {
        return new GridFSBucketImpl(this.bucketName, this.chunkSizeBytes, this.filesCollection, this.chunksCollection, disableMD5);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSUploadStream openUploadStream(String filename) {
        return openUploadStream(new BsonObjectId(), filename);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSUploadStream openUploadStream(String filename, GridFSUploadOptions options) {
        return openUploadStream(new BsonObjectId(), filename, options);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSUploadStream openUploadStream(BsonValue id, String filename) {
        return openUploadStream(id, filename, new GridFSUploadOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSUploadStream openUploadStream(BsonValue id, String filename, GridFSUploadOptions options) {
        return createGridFSUploadStream(null, id, filename, options);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSUploadStream openUploadStream(ClientSession clientSession, String filename) {
        return openUploadStream(clientSession, new BsonObjectId(), filename);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSUploadStream openUploadStream(ClientSession clientSession, String filename, GridFSUploadOptions options) {
        return openUploadStream(clientSession, new BsonObjectId(), filename, options);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSUploadStream openUploadStream(ClientSession clientSession, ObjectId id, String filename) {
        return openUploadStream(clientSession, new BsonObjectId(id), filename);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSUploadStream openUploadStream(ClientSession clientSession, BsonValue id, String filename) {
        return openUploadStream(clientSession, id, filename, new GridFSUploadOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSUploadStream openUploadStream(ClientSession clientSession, BsonValue id, String filename, GridFSUploadOptions options) {
        Assertions.notNull("clientSession", clientSession);
        return createGridFSUploadStream(clientSession, id, filename, options);
    }

    private GridFSUploadStream createGridFSUploadStream(@Nullable ClientSession clientSession, BsonValue id, String filename, GridFSUploadOptions options) {
        Assertions.notNull("options", options);
        Integer chunkSizeBytes = options.getChunkSizeBytes();
        int chunkSize = chunkSizeBytes == null ? this.chunkSizeBytes : chunkSizeBytes.intValue();
        checkCreateIndex(clientSession);
        return new GridFSUploadStreamImpl(clientSession, this.filesCollection, this.chunksCollection, id, filename, chunkSize, this.disableMD5, options.getMetadata());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public ObjectId uploadFromStream(String filename, InputStream source) {
        return uploadFromStream(filename, source, new GridFSUploadOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public ObjectId uploadFromStream(String filename, InputStream source, GridFSUploadOptions options) throws IOException {
        ObjectId id = new ObjectId();
        uploadFromStream(new BsonObjectId(id), filename, source, options);
        return id;
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void uploadFromStream(BsonValue id, String filename, InputStream source) throws IOException {
        uploadFromStream(id, filename, source, new GridFSUploadOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void uploadFromStream(BsonValue id, String filename, InputStream source, GridFSUploadOptions options) throws IOException {
        executeUploadFromStream(null, id, filename, source, options);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public ObjectId uploadFromStream(ClientSession clientSession, String filename, InputStream source) {
        return uploadFromStream(clientSession, filename, source, new GridFSUploadOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public ObjectId uploadFromStream(ClientSession clientSession, String filename, InputStream source, GridFSUploadOptions options) throws IOException {
        ObjectId id = new ObjectId();
        uploadFromStream(clientSession, new BsonObjectId(id), filename, source, options);
        return id;
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void uploadFromStream(ClientSession clientSession, BsonValue id, String filename, InputStream source) throws IOException {
        uploadFromStream(clientSession, id, filename, source, new GridFSUploadOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void uploadFromStream(ClientSession clientSession, BsonValue id, String filename, InputStream source, GridFSUploadOptions options) throws IOException {
        Assertions.notNull("clientSession", clientSession);
        executeUploadFromStream(clientSession, id, filename, source, options);
    }

    private void executeUploadFromStream(@Nullable ClientSession clientSession, BsonValue id, String filename, InputStream source, GridFSUploadOptions options) throws IOException {
        GridFSUploadStream uploadStream = createGridFSUploadStream(clientSession, id, filename, options);
        Integer chunkSizeBytes = options.getChunkSizeBytes();
        int chunkSize = chunkSizeBytes == null ? this.chunkSizeBytes : chunkSizeBytes.intValue();
        byte[] buffer = new byte[chunkSize];
        while (true) {
            try {
                int len = source.read(buffer);
                if (len != -1) {
                    uploadStream.write(buffer, 0, len);
                } else {
                    uploadStream.close();
                    return;
                }
            } catch (IOException e) {
                uploadStream.abort();
                throw new MongoGridFSException("IOException when reading from the InputStream", e);
            }
        }
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSDownloadStream openDownloadStream(ObjectId id) {
        return openDownloadStream(new BsonObjectId(id));
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSDownloadStream openDownloadStream(BsonValue id) {
        return createGridFSDownloadStream(null, getFileInfoById(null, id));
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSDownloadStream openDownloadStream(String filename) {
        return openDownloadStream(filename, new GridFSDownloadOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSDownloadStream openDownloadStream(String filename, GridFSDownloadOptions options) {
        return createGridFSDownloadStream(null, getFileByName(null, filename, options));
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSDownloadStream openDownloadStream(ClientSession clientSession, ObjectId id) {
        return openDownloadStream(clientSession, new BsonObjectId(id));
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSDownloadStream openDownloadStream(ClientSession clientSession, BsonValue id) {
        Assertions.notNull("clientSession", clientSession);
        return createGridFSDownloadStream(clientSession, getFileInfoById(clientSession, id));
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSDownloadStream openDownloadStream(ClientSession clientSession, String filename) {
        return openDownloadStream(clientSession, filename, new GridFSDownloadOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSDownloadStream openDownloadStream(ClientSession clientSession, String filename, GridFSDownloadOptions options) {
        Assertions.notNull("clientSession", clientSession);
        return createGridFSDownloadStream(clientSession, getFileByName(clientSession, filename, options));
    }

    private GridFSDownloadStream createGridFSDownloadStream(@Nullable ClientSession clientSession, GridFSFile gridFSFile) {
        return new GridFSDownloadStreamImpl(clientSession, gridFSFile, this.chunksCollection);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void downloadToStream(ObjectId id, OutputStream destination) {
        downloadToStream(new BsonObjectId(id), destination);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void downloadToStream(BsonValue id, OutputStream destination) {
        downloadToStream(openDownloadStream(id), destination);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void downloadToStream(String filename, OutputStream destination) {
        downloadToStream(filename, destination, new GridFSDownloadOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void downloadToStream(String filename, OutputStream destination, GridFSDownloadOptions options) {
        downloadToStream(openDownloadStream(filename, options), destination);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void downloadToStream(ClientSession clientSession, ObjectId id, OutputStream destination) {
        downloadToStream(clientSession, new BsonObjectId(id), destination);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void downloadToStream(ClientSession clientSession, BsonValue id, OutputStream destination) {
        Assertions.notNull("clientSession", clientSession);
        downloadToStream(openDownloadStream(clientSession, id), destination);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void downloadToStream(ClientSession clientSession, String filename, OutputStream destination) {
        downloadToStream(clientSession, filename, destination, new GridFSDownloadOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void downloadToStream(ClientSession clientSession, String filename, OutputStream destination, GridFSDownloadOptions options) {
        Assertions.notNull("clientSession", clientSession);
        downloadToStream(openDownloadStream(clientSession, filename, options), destination);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSFindIterable find() {
        return createGridFSFindIterable(null, null);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSFindIterable find(Bson filter) {
        Assertions.notNull("filter", filter);
        return createGridFSFindIterable(null, filter);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSFindIterable find(ClientSession clientSession) {
        Assertions.notNull("clientSession", clientSession);
        return createGridFSFindIterable(clientSession, null);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public GridFSFindIterable find(ClientSession clientSession, Bson filter) {
        Assertions.notNull("clientSession", clientSession);
        Assertions.notNull("filter", filter);
        return createGridFSFindIterable(clientSession, filter);
    }

    private GridFSFindIterable createGridFSFindIterable(@Nullable ClientSession clientSession, @Nullable Bson filter) {
        return new GridFSFindIterableImpl(createFindIterable(clientSession, filter));
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void delete(ObjectId id) {
        delete(new BsonObjectId(id));
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void delete(BsonValue id) {
        executeDelete(null, id);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void delete(ClientSession clientSession, ObjectId id) {
        delete(clientSession, new BsonObjectId(id));
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void delete(ClientSession clientSession, BsonValue id) {
        Assertions.notNull("clientSession", clientSession);
        executeDelete(clientSession, id);
    }

    private void executeDelete(@Nullable ClientSession clientSession, BsonValue id) {
        DeleteResult result;
        if (clientSession != null) {
            result = this.filesCollection.deleteOne(clientSession, new BsonDocument("_id", id));
            this.chunksCollection.deleteMany(clientSession, new BsonDocument("files_id", id));
        } else {
            result = this.filesCollection.deleteOne(new BsonDocument("_id", id));
            this.chunksCollection.deleteMany(new BsonDocument("files_id", id));
        }
        if (result.wasAcknowledged() && result.getDeletedCount() == 0) {
            throw new MongoGridFSException(String.format("No file found with the id: %s", id));
        }
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void rename(ObjectId id, String newFilename) {
        rename(new BsonObjectId(id), newFilename);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void rename(BsonValue id, String newFilename) {
        executeRename(null, id, newFilename);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void rename(ClientSession clientSession, ObjectId id, String newFilename) {
        rename(clientSession, new BsonObjectId(id), newFilename);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void rename(ClientSession clientSession, BsonValue id, String newFilename) {
        Assertions.notNull("clientSession", clientSession);
        executeRename(clientSession, id, newFilename);
    }

    private void executeRename(@Nullable ClientSession clientSession, BsonValue id, String newFilename) {
        UpdateResult updateResult;
        if (clientSession != null) {
            updateResult = this.filesCollection.updateOne(clientSession, new BsonDocument("_id", id), new BsonDocument("$set", new BsonDocument("filename", new BsonString(newFilename))));
        } else {
            updateResult = this.filesCollection.updateOne(new BsonDocument("_id", id), new BsonDocument("$set", new BsonDocument("filename", new BsonString(newFilename))));
        }
        if (updateResult.wasAcknowledged() && updateResult.getMatchedCount() == 0) {
            throw new MongoGridFSException(String.format("No file found with the id: %s", id));
        }
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void drop() {
        this.filesCollection.drop();
        this.chunksCollection.drop();
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    public void drop(ClientSession clientSession) {
        Assertions.notNull("clientSession", clientSession);
        this.filesCollection.drop(clientSession);
        this.chunksCollection.drop(clientSession);
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    @Deprecated
    public GridFSDownloadStream openDownloadStreamByName(String filename) {
        return openDownloadStreamByName(filename, new GridFSDownloadByNameOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    @Deprecated
    public GridFSDownloadStream openDownloadStreamByName(String filename, GridFSDownloadByNameOptions options) {
        return openDownloadStream(filename, new GridFSDownloadOptions().revision(options.getRevision()));
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    @Deprecated
    public void downloadToStreamByName(String filename, OutputStream destination) {
        downloadToStreamByName(filename, destination, new GridFSDownloadByNameOptions());
    }

    @Override // com.mongodb.client.gridfs.GridFSBucket
    @Deprecated
    public void downloadToStreamByName(String filename, OutputStream destination, GridFSDownloadByNameOptions options) {
        downloadToStream(filename, destination, new GridFSDownloadOptions().revision(options.getRevision()));
    }

    private static MongoCollection<GridFSFile> getFilesCollection(MongoDatabase database, String bucketName) {
        return database.getCollection(bucketName + ".files", GridFSFile.class).withCodecRegistry(CodecRegistries.fromRegistries(database.getCodecRegistry(), MongoClientSettings.getDefaultCodecRegistry()));
    }

    private static MongoCollection<Document> getChunksCollection(MongoDatabase database, String bucketName) {
        return database.getCollection(bucketName + ".chunks").withCodecRegistry(MongoClientSettings.getDefaultCodecRegistry());
    }

    private void checkCreateIndex(@Nullable ClientSession clientSession) {
        if (!this.checkedIndexes) {
            if (collectionIsEmpty(clientSession, this.filesCollection.withDocumentClass(Document.class).withReadPreference(ReadPreference.primary()))) {
                Document filesIndex = new Document("filename", 1).append("uploadDate", 1);
                if (!hasIndex(clientSession, this.filesCollection.withReadPreference(ReadPreference.primary()), filesIndex)) {
                    createIndex(clientSession, this.filesCollection, filesIndex, new IndexOptions());
                }
                Document chunksIndex = new Document("files_id", 1).append(OperatorName.ENDPATH, 1);
                if (!hasIndex(clientSession, this.chunksCollection.withReadPreference(ReadPreference.primary()), chunksIndex)) {
                    createIndex(clientSession, this.chunksCollection, chunksIndex, new IndexOptions().unique(true));
                }
            }
            this.checkedIndexes = true;
        }
    }

    private <T> boolean collectionIsEmpty(@Nullable ClientSession clientSession, MongoCollection<T> collection) {
        return clientSession != null ? collection.find(clientSession).projection(new Document("_id", 1)).first() == null : collection.find().projection(new Document("_id", 1)).first() == null;
    }

    private <T> boolean hasIndex(@Nullable ClientSession clientSession, MongoCollection<T> collection, Document index) {
        ListIndexesIterable<Document> listIndexesIterable;
        boolean hasIndex = false;
        if (clientSession != null) {
            listIndexesIterable = collection.listIndexes(clientSession);
        } else {
            listIndexesIterable = collection.listIndexes();
        }
        ArrayList<Document> indexes = (ArrayList) listIndexesIterable.into(new ArrayList());
        Iterator<Document> it = indexes.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Document indexDoc = it.next();
            if (((Document) indexDoc.get((Object) "key", (Class) Document.class)).equals(index)) {
                hasIndex = true;
                break;
            }
        }
        return hasIndex;
    }

    private <T> void createIndex(@Nullable ClientSession clientSession, MongoCollection<T> collection, Document index, IndexOptions indexOptions) {
        if (clientSession != null) {
            collection.createIndex(clientSession, index, indexOptions);
        } else {
            collection.createIndex(index, indexOptions);
        }
    }

    private GridFSFile getFileByName(@Nullable ClientSession clientSession, String filename, GridFSDownloadOptions options) {
        int skip;
        int sort;
        int revision = options.getRevision();
        if (revision >= 0) {
            skip = revision;
            sort = 1;
        } else {
            skip = (-revision) - 1;
            sort = -1;
        }
        GridFSFile fileInfo = createGridFSFindIterable(clientSession, new Document("filename", filename)).skip(skip).sort(new Document("uploadDate", Integer.valueOf(sort))).first();
        if (fileInfo == null) {
            throw new MongoGridFSException(String.format("No file found with the filename: %s and revision: %s", filename, Integer.valueOf(revision)));
        }
        return fileInfo;
    }

    private GridFSFile getFileInfoById(@Nullable ClientSession clientSession, BsonValue id) {
        Assertions.notNull("id", id);
        GridFSFile fileInfo = createFindIterable(clientSession, new Document("_id", id)).first();
        if (fileInfo == null) {
            throw new MongoGridFSException(String.format("No file found with the id: %s", id));
        }
        return fileInfo;
    }

    private FindIterable<GridFSFile> createFindIterable(@Nullable ClientSession clientSession, @Nullable Bson filter) {
        FindIterable<GridFSFile> findIterable;
        if (clientSession != null) {
            findIterable = this.filesCollection.find(clientSession);
        } else {
            findIterable = this.filesCollection.find();
        }
        if (filter != null) {
            findIterable = findIterable.filter(filter);
        }
        return findIterable;
    }

    private void downloadToStream(GridFSDownloadStream downloadStream, OutputStream destination) {
        byte[] buffer = new byte[downloadStream.getGridFSFile().getChunkSize()];
        while (true) {
            try {
                try {
                    int len = downloadStream.read(buffer);
                    if (len == -1) {
                        break;
                    } else {
                        destination.write(buffer, 0, len);
                    }
                } catch (IOException e) {
                    MongoGridFSException savedThrowable = new MongoGridFSException("IOException when reading from the OutputStream", e);
                    try {
                        downloadStream.close();
                    } catch (Exception e2) {
                    }
                    if (savedThrowable != null) {
                        throw savedThrowable;
                    }
                    return;
                } catch (Exception e3) {
                    MongoGridFSException savedThrowable2 = new MongoGridFSException("Unexpected Exception when reading GridFS and writing to the Stream", e3);
                    try {
                        downloadStream.close();
                    } catch (Exception e4) {
                    }
                    if (savedThrowable2 != null) {
                        throw savedThrowable2;
                    }
                    return;
                }
            } catch (Throwable th) {
                try {
                    downloadStream.close();
                } catch (Exception e5) {
                }
                if (0 == 0) {
                    throw th;
                }
                throw null;
            }
        }
        try {
            downloadStream.close();
        } catch (Exception e6) {
        }
        if (0 != 0) {
            throw null;
        }
    }
}
