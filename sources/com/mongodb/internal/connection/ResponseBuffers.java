package com.mongodb.internal.connection;

import java.io.Closeable;
import org.bson.BsonDocument;
import org.bson.ByteBuf;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ResponseBuffers.class */
public class ResponseBuffers implements Closeable {
    private final ReplyHeader replyHeader;
    private final ByteBuf bodyByteBuffer;
    private final int bodyByteBufferStartPosition;
    private volatile boolean isClosed;

    ResponseBuffers(ReplyHeader replyHeader, ByteBuf bodyByteBuffer) {
        this.replyHeader = replyHeader;
        this.bodyByteBuffer = bodyByteBuffer;
        this.bodyByteBufferStartPosition = bodyByteBuffer == null ? 0 : bodyByteBuffer.position();
    }

    public ReplyHeader getReplyHeader() {
        return this.replyHeader;
    }

    <T extends BsonDocument> T getResponseDocument(int i, Decoder<T> decoder) {
        ReplyMessage replyMessage = new ReplyMessage(this, decoder, i);
        reset();
        return (T) ((BsonDocument) replyMessage.getDocuments().get(0));
    }

    public ByteBuf getBodyByteBuffer() {
        return this.bodyByteBuffer.asReadOnly();
    }

    public void reset() {
        this.bodyByteBuffer.position(this.bodyByteBufferStartPosition);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.isClosed) {
            if (this.bodyByteBuffer != null) {
                this.bodyByteBuffer.release();
            }
            this.isClosed = true;
        }
    }
}
