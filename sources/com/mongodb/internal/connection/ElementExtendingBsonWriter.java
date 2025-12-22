package com.mongodb.internal.connection;

import java.util.List;
import org.bson.BsonBinaryWriter;
import org.bson.BsonElement;
import org.bson.BsonReader;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ElementExtendingBsonWriter.class */
class ElementExtendingBsonWriter extends LevelCountingBsonWriter {
    private final List<BsonElement> extraElements;

    ElementExtendingBsonWriter(BsonBinaryWriter writer, List<BsonElement> extraElements) {
        super(writer);
        this.extraElements = extraElements;
    }

    @Override // com.mongodb.internal.connection.LevelCountingBsonWriter, org.bson.BsonWriter
    public void writeEndDocument() {
        if (getCurrentLevel() == 0) {
            BsonWriterHelper.writeElements(getBsonBinaryWriter(), this.extraElements);
        }
        super.writeEndDocument();
    }

    @Override // com.mongodb.internal.connection.LevelCountingBsonWriter, org.bson.BsonWriter
    public void pipe(BsonReader reader) {
        if (getCurrentLevel() == -1) {
            getBsonBinaryWriter().pipe(reader, this.extraElements);
        } else {
            getBsonBinaryWriter().pipe(reader);
        }
    }
}
