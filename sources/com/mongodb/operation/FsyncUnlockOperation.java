package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.binding.ReadBinding;
import com.mongodb.binding.WriteBinding;
import com.mongodb.connection.Connection;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.operation.OperationHelper;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.codecs.BsonDocumentCodec;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/FsyncUnlockOperation.class */
public class FsyncUnlockOperation implements WriteOperation<BsonDocument>, ReadOperation<BsonDocument> {
    private static final BsonDocument FSYNC_UNLOCK_COMMAND = new BsonDocument("fsyncUnlock", new BsonInt32(1));

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.WriteOperation
    @Deprecated
    public BsonDocument execute(final WriteBinding binding) {
        return (BsonDocument) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnection<BsonDocument>() { // from class: com.mongodb.operation.FsyncUnlockOperation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnection
            public BsonDocument call(Connection connection) {
                return ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription()) ? CommandOperationHelper.executeWrappedCommandProtocol(binding, "admin", FsyncUnlockOperation.FSYNC_UNLOCK_COMMAND, connection) : FsyncUnlockOperation.this.queryUnlock(connection);
            }
        });
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.ReadOperation
    public BsonDocument execute(final ReadBinding binding) {
        return (BsonDocument) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnection<BsonDocument>() { // from class: com.mongodb.operation.FsyncUnlockOperation.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnection
            public BsonDocument call(Connection connection) {
                return ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription()) ? CommandOperationHelper.executeWrappedCommandProtocol(binding, "admin", FsyncUnlockOperation.FSYNC_UNLOCK_COMMAND, connection) : FsyncUnlockOperation.this.queryUnlock(connection);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument queryUnlock(Connection connection) {
        return (BsonDocument) connection.query(new MongoNamespace("admin", "$cmd.sys.unlock"), new BsonDocument(), null, 0, 1, 0, false, false, false, false, false, false, new BsonDocumentCodec()).getResults().get(0);
    }
}
