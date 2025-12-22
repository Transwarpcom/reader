package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.binding.ReadBinding;
import com.mongodb.connection.Connection;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.operation.OperationHelper;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.codecs.BsonDocumentCodec;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CurrentOpOperation.class */
public class CurrentOpOperation implements ReadOperation<BsonDocument> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.ReadOperation
    public BsonDocument execute(final ReadBinding binding) {
        return (BsonDocument) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnection<BsonDocument>() { // from class: com.mongodb.operation.CurrentOpOperation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnection
            public BsonDocument call(Connection connection) {
                if (ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connection.getDescription())) {
                    return CommandOperationHelper.executeWrappedCommandProtocol(binding, "admin", new BsonDocument("currentOp", new BsonInt32(1)), connection);
                }
                return (BsonDocument) connection.query(new MongoNamespace("admin", "$cmd.sys.inprog"), new BsonDocument(), null, 0, 1, 0, binding.getReadPreference().isSlaveOk(), false, false, false, false, false, new BsonDocumentCodec()).getResults().get(0);
            }
        });
    }
}
