package com.mongodb.client.gridfs;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.annotations.ThreadSafe;
import com.mongodb.client.ClientSession;
import com.mongodb.client.gridfs.model.GridFSDownloadByNameOptions;
import com.mongodb.client.gridfs.model.GridFSDownloadOptions;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import java.io.InputStream;
import java.io.OutputStream;
import org.bson.BsonValue;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

@ThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/gridfs/GridFSBucket.class */
public interface GridFSBucket {
    String getBucketName();

    int getChunkSizeBytes();

    WriteConcern getWriteConcern();

    ReadPreference getReadPreference();

    ReadConcern getReadConcern();

    boolean getDisableMD5();

    GridFSBucket withChunkSizeBytes(int i);

    GridFSBucket withReadPreference(ReadPreference readPreference);

    GridFSBucket withWriteConcern(WriteConcern writeConcern);

    GridFSBucket withReadConcern(ReadConcern readConcern);

    GridFSBucket withDisableMD5(boolean z);

    GridFSUploadStream openUploadStream(String str);

    GridFSUploadStream openUploadStream(String str, GridFSUploadOptions gridFSUploadOptions);

    GridFSUploadStream openUploadStream(BsonValue bsonValue, String str);

    GridFSUploadStream openUploadStream(BsonValue bsonValue, String str, GridFSUploadOptions gridFSUploadOptions);

    GridFSUploadStream openUploadStream(ClientSession clientSession, String str);

    GridFSUploadStream openUploadStream(ClientSession clientSession, String str, GridFSUploadOptions gridFSUploadOptions);

    GridFSUploadStream openUploadStream(ClientSession clientSession, BsonValue bsonValue, String str);

    GridFSUploadStream openUploadStream(ClientSession clientSession, ObjectId objectId, String str);

    GridFSUploadStream openUploadStream(ClientSession clientSession, BsonValue bsonValue, String str, GridFSUploadOptions gridFSUploadOptions);

    ObjectId uploadFromStream(String str, InputStream inputStream);

    ObjectId uploadFromStream(String str, InputStream inputStream, GridFSUploadOptions gridFSUploadOptions);

    void uploadFromStream(BsonValue bsonValue, String str, InputStream inputStream);

    void uploadFromStream(BsonValue bsonValue, String str, InputStream inputStream, GridFSUploadOptions gridFSUploadOptions);

    ObjectId uploadFromStream(ClientSession clientSession, String str, InputStream inputStream);

    ObjectId uploadFromStream(ClientSession clientSession, String str, InputStream inputStream, GridFSUploadOptions gridFSUploadOptions);

    void uploadFromStream(ClientSession clientSession, BsonValue bsonValue, String str, InputStream inputStream);

    void uploadFromStream(ClientSession clientSession, BsonValue bsonValue, String str, InputStream inputStream, GridFSUploadOptions gridFSUploadOptions);

    GridFSDownloadStream openDownloadStream(ObjectId objectId);

    GridFSDownloadStream openDownloadStream(BsonValue bsonValue);

    GridFSDownloadStream openDownloadStream(String str);

    GridFSDownloadStream openDownloadStream(String str, GridFSDownloadOptions gridFSDownloadOptions);

    GridFSDownloadStream openDownloadStream(ClientSession clientSession, ObjectId objectId);

    GridFSDownloadStream openDownloadStream(ClientSession clientSession, BsonValue bsonValue);

    GridFSDownloadStream openDownloadStream(ClientSession clientSession, String str);

    GridFSDownloadStream openDownloadStream(ClientSession clientSession, String str, GridFSDownloadOptions gridFSDownloadOptions);

    void downloadToStream(ObjectId objectId, OutputStream outputStream);

    void downloadToStream(BsonValue bsonValue, OutputStream outputStream);

    void downloadToStream(String str, OutputStream outputStream);

    void downloadToStream(String str, OutputStream outputStream, GridFSDownloadOptions gridFSDownloadOptions);

    void downloadToStream(ClientSession clientSession, ObjectId objectId, OutputStream outputStream);

    void downloadToStream(ClientSession clientSession, BsonValue bsonValue, OutputStream outputStream);

    void downloadToStream(ClientSession clientSession, String str, OutputStream outputStream);

    void downloadToStream(ClientSession clientSession, String str, OutputStream outputStream, GridFSDownloadOptions gridFSDownloadOptions);

    GridFSFindIterable find();

    GridFSFindIterable find(Bson bson);

    GridFSFindIterable find(ClientSession clientSession);

    GridFSFindIterable find(ClientSession clientSession, Bson bson);

    void delete(ObjectId objectId);

    void delete(BsonValue bsonValue);

    void delete(ClientSession clientSession, ObjectId objectId);

    void delete(ClientSession clientSession, BsonValue bsonValue);

    void rename(ObjectId objectId, String str);

    void rename(BsonValue bsonValue, String str);

    void rename(ClientSession clientSession, ObjectId objectId, String str);

    void rename(ClientSession clientSession, BsonValue bsonValue, String str);

    void drop();

    void drop(ClientSession clientSession);

    @Deprecated
    GridFSDownloadStream openDownloadStreamByName(String str);

    @Deprecated
    GridFSDownloadStream openDownloadStreamByName(String str, GridFSDownloadByNameOptions gridFSDownloadByNameOptions);

    @Deprecated
    void downloadToStreamByName(String str, OutputStream outputStream);

    @Deprecated
    void downloadToStreamByName(String str, OutputStream outputStream, GridFSDownloadByNameOptions gridFSDownloadByNameOptions);
}
