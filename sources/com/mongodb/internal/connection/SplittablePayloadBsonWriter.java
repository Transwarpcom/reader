package com.mongodb.internal.connection;

import com.mongodb.connection.SplittablePayload;
import org.bson.BsonBinaryWriter;
import org.bson.BsonWriter;
import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/SplittablePayloadBsonWriter.class */
class SplittablePayloadBsonWriter extends LevelCountingBsonWriter {
    private final BsonWriter writer;
    private final BsonOutput bsonOutput;
    private final SplittablePayload payload;
    private final MessageSettings settings;
    private final int messageStartPosition;

    SplittablePayloadBsonWriter(BsonBinaryWriter writer, BsonOutput bsonOutput, int messageStartPosition, MessageSettings settings, SplittablePayload payload) {
        super(writer);
        this.writer = writer;
        this.bsonOutput = bsonOutput;
        this.messageStartPosition = messageStartPosition;
        this.settings = settings;
        this.payload = payload;
    }

    @Override // com.mongodb.internal.connection.LevelCountingBsonWriter, org.bson.BsonWriter
    public void writeStartDocument() {
        super.writeStartDocument();
    }

    @Override // com.mongodb.internal.connection.LevelCountingBsonWriter, org.bson.BsonWriter
    public void writeEndDocument() {
        if (getCurrentLevel() == 0 && this.payload.getPayload().size() > 0) {
            BsonWriterHelper.writePayloadArray(this.writer, this.bsonOutput, this.settings, this.messageStartPosition, this.payload);
        }
        super.writeEndDocument();
    }
}
