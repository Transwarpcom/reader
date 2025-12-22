package com.mongodb.connection;

import com.mongodb.MongoNamespace;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcernResult;
import com.mongodb.annotations.ThreadSafe;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.ReferenceCounted;
import com.mongodb.bulk.DeleteRequest;
import com.mongodb.bulk.InsertRequest;
import com.mongodb.bulk.UpdateRequest;
import com.mongodb.session.SessionContext;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.FieldNameValidator;
import org.bson.codecs.Decoder;

@ThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/AsyncConnection.class */
public interface AsyncConnection extends ReferenceCounted {
    @Override // com.mongodb.binding.ReferenceCounted
    AsyncConnection retain();

    ConnectionDescription getDescription();

    void insertAsync(MongoNamespace mongoNamespace, boolean z, InsertRequest insertRequest, SingleResultCallback<WriteConcernResult> singleResultCallback);

    void updateAsync(MongoNamespace mongoNamespace, boolean z, UpdateRequest updateRequest, SingleResultCallback<WriteConcernResult> singleResultCallback);

    void deleteAsync(MongoNamespace mongoNamespace, boolean z, DeleteRequest deleteRequest, SingleResultCallback<WriteConcernResult> singleResultCallback);

    @Deprecated
    <T> void commandAsync(String str, BsonDocument bsonDocument, boolean z, FieldNameValidator fieldNameValidator, Decoder<T> decoder, SingleResultCallback<T> singleResultCallback);

    <T> void commandAsync(String str, BsonDocument bsonDocument, FieldNameValidator fieldNameValidator, ReadPreference readPreference, Decoder<T> decoder, SessionContext sessionContext, SingleResultCallback<T> singleResultCallback);

    <T> void commandAsync(String str, BsonDocument bsonDocument, FieldNameValidator fieldNameValidator, ReadPreference readPreference, Decoder<T> decoder, SessionContext sessionContext, boolean z, SplittablePayload splittablePayload, FieldNameValidator fieldNameValidator2, SingleResultCallback<T> singleResultCallback);

    @Deprecated
    <T> void queryAsync(MongoNamespace mongoNamespace, BsonDocument bsonDocument, BsonDocument bsonDocument2, int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, Decoder<T> decoder, SingleResultCallback<QueryResult<T>> singleResultCallback);

    <T> void queryAsync(MongoNamespace mongoNamespace, BsonDocument bsonDocument, BsonDocument bsonDocument2, int i, int i2, int i3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, Decoder<T> decoder, SingleResultCallback<QueryResult<T>> singleResultCallback);

    <T> void getMoreAsync(MongoNamespace mongoNamespace, long j, int i, Decoder<T> decoder, SingleResultCallback<QueryResult<T>> singleResultCallback);

    @Deprecated
    void killCursorAsync(List<Long> list, SingleResultCallback<Void> singleResultCallback);

    void killCursorAsync(MongoNamespace mongoNamespace, List<Long> list, SingleResultCallback<Void> singleResultCallback);
}
