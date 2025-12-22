package com.mongodb.internal.connection;

import com.mongodb.assertions.Assertions;
import com.mongodb.internal.connection.RequestMessage;
import java.util.List;
import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/KillCursorsMessage.class */
class KillCursorsMessage extends LegacyMessage {
    private final List<Long> cursors;

    KillCursorsMessage(List<Long> cursors) {
        super(OpCode.OP_KILL_CURSORS, MessageSettings.builder().build());
        this.cursors = (List) Assertions.notNull("cursors", cursors);
    }

    @Override // com.mongodb.internal.connection.LegacyMessage
    protected RequestMessage.EncodingMetadata encodeMessageBodyWithMetadata(BsonOutput bsonOutput) {
        writeKillCursorsPrologue(this.cursors.size(), bsonOutput);
        for (Long cur : this.cursors) {
            bsonOutput.writeInt64(cur.longValue());
        }
        return new RequestMessage.EncodingMetadata(bsonOutput.getPosition());
    }

    private void writeKillCursorsPrologue(int numCursors, BsonOutput bsonOutput) {
        bsonOutput.writeInt32(0);
        bsonOutput.writeInt32(numCursors);
    }
}
