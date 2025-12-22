package com.mongodb.internal.connection;

import java.util.ArrayList;
import java.util.List;
import org.bson.BsonBinaryReader;
import org.bson.codecs.Decoder;
import org.bson.codecs.DecoderContext;
import org.bson.io.BsonInput;
import org.bson.io.ByteBufferBsonInput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ReplyMessage.class */
public class ReplyMessage<T> {
    private final ReplyHeader replyHeader;
    private final List<T> documents;

    public ReplyMessage(ResponseBuffers responseBuffers, Decoder<T> decoder, long requestId) {
        this(responseBuffers.getReplyHeader(), requestId);
        if (this.replyHeader.getNumberReturned() > 0) {
            BsonInput bsonInput = new ByteBufferBsonInput(responseBuffers.getBodyByteBuffer().duplicate());
            while (this.documents.size() < this.replyHeader.getNumberReturned()) {
                try {
                    BsonBinaryReader reader = new BsonBinaryReader(bsonInput);
                    try {
                        this.documents.add(decoder.decode(reader, DecoderContext.builder().build()));
                        reader.close();
                    } catch (Throwable th) {
                        reader.close();
                        throw th;
                    }
                } finally {
                    bsonInput.close();
                    responseBuffers.reset();
                }
            }
        }
    }

    ReplyMessage(ReplyHeader replyHeader, long requestId) {
        this.replyHeader = replyHeader;
        this.documents = new ArrayList(replyHeader.getNumberReturned());
    }

    public ReplyHeader getReplyHeader() {
        return this.replyHeader;
    }

    public List<T> getDocuments() {
        return this.documents;
    }
}
