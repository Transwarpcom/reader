package com.mongodb.internal.connection;

import com.mongodb.connection.ByteBufferBsonOutput;
import com.mongodb.internal.connection.AbstractByteBufBsonDocument;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.StringWriter;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import org.bson.BsonBinaryReader;
import org.bson.BsonDocument;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.ByteBuf;
import org.bson.ByteBufNIO;
import org.bson.RawBsonDocument;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.DecoderContext;
import org.bson.io.ByteBufferBsonInput;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ByteBufBsonDocument.class */
class ByteBufBsonDocument extends AbstractByteBufBsonDocument {
    private static final long serialVersionUID = 2;
    private final transient ByteBuf byteBuf;

    static List<ByteBufBsonDocument> createList(ResponseBuffers responseBuffers) {
        int numDocuments = responseBuffers.getReplyHeader().getNumberReturned();
        ByteBuf documentsBuffer = responseBuffers.getBodyByteBuffer();
        documentsBuffer.order(ByteOrder.LITTLE_ENDIAN);
        List<ByteBufBsonDocument> documents = new ArrayList<>(numDocuments);
        while (documents.size() < numDocuments) {
            int documentSizeInBytes = documentsBuffer.getInt();
            documentsBuffer.position(documentsBuffer.position() - 4);
            ByteBuf documentBuffer = documentsBuffer.duplicate();
            documentBuffer.limit(documentBuffer.position() + documentSizeInBytes);
            documents.add(new ByteBufBsonDocument(new ByteBufNIO(documentBuffer.asNIO())));
            documentBuffer.release();
            documentsBuffer.position(documentsBuffer.position() + documentSizeInBytes);
        }
        return documents;
    }

    static List<ByteBufBsonDocument> createList(ByteBufferBsonOutput bsonOutput, int startPosition) {
        List<ByteBuf> duplicateByteBuffers = bsonOutput.getByteBuffers();
        CompositeByteBuf outputByteBuf = new CompositeByteBuf(duplicateByteBuffers);
        outputByteBuf.position(startPosition);
        List<ByteBufBsonDocument> documents = new ArrayList<>();
        int curDocumentStartPosition = startPosition;
        while (outputByteBuf.hasRemaining()) {
            int documentSizeInBytes = outputByteBuf.getInt();
            ByteBuf slice = outputByteBuf.duplicate();
            slice.position(curDocumentStartPosition);
            slice.limit(curDocumentStartPosition + documentSizeInBytes);
            documents.add(new ByteBufBsonDocument(slice));
            curDocumentStartPosition += documentSizeInBytes;
            outputByteBuf.position((outputByteBuf.position() + documentSizeInBytes) - 4);
        }
        for (ByteBuf byteBuffer : duplicateByteBuffers) {
            byteBuffer.release();
        }
        return documents;
    }

    static ByteBufBsonDocument createOne(ByteBufferBsonOutput bsonOutput, int startPosition) {
        List<ByteBuf> duplicateByteBuffers = bsonOutput.getByteBuffers();
        CompositeByteBuf outputByteBuf = new CompositeByteBuf(duplicateByteBuffers);
        outputByteBuf.position(startPosition);
        int documentSizeInBytes = outputByteBuf.getInt();
        ByteBuf slice = outputByteBuf.duplicate();
        slice.position(startPosition);
        slice.limit(startPosition + documentSizeInBytes);
        for (ByteBuf byteBuffer : duplicateByteBuffers) {
            byteBuffer.release();
        }
        return new ByteBufBsonDocument(slice);
    }

    @Override // org.bson.BsonDocument
    public String toJson() {
        return toJson(new JsonWriterSettings());
    }

    @Override // org.bson.BsonDocument
    public String toJson(JsonWriterSettings settings) {
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter, settings);
        ByteBuf duplicate = this.byteBuf.duplicate();
        BsonBinaryReader reader = new BsonBinaryReader(new ByteBufferBsonInput(duplicate));
        try {
            jsonWriter.pipe(reader);
            String string = stringWriter.toString();
            duplicate.release();
            reader.close();
            return string;
        } catch (Throwable th) {
            duplicate.release();
            reader.close();
            throw th;
        }
    }

    @Override // org.bson.BsonDocument
    public BsonReader asBsonReader() {
        return new BsonBinaryReader(new ByteBufferBsonInput(this.byteBuf.duplicate()));
    }

    @Override // com.mongodb.internal.connection.AbstractByteBufBsonDocument
    <T> T findInDocument(AbstractByteBufBsonDocument.Finder<T> finder) {
        ByteBuf duplicateByteBuf = this.byteBuf.duplicate();
        BsonBinaryReader bsonReader = new BsonBinaryReader(new ByteBufferBsonInput(this.byteBuf.duplicate()));
        try {
            bsonReader.readStartDocument();
            while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                T found = finder.find(bsonReader);
                if (found != null) {
                    return found;
                }
            }
            bsonReader.readEndDocument();
            duplicateByteBuf.release();
            bsonReader.close();
            return finder.notFound();
        } finally {
            duplicateByteBuf.release();
            bsonReader.close();
        }
    }

    @Override // org.bson.BsonDocument
    /* renamed from: clone */
    public BsonDocument mo1002clone() {
        byte[] clonedBytes = new byte[this.byteBuf.remaining()];
        this.byteBuf.get(this.byteBuf.position(), clonedBytes);
        return new RawBsonDocument(clonedBytes);
    }

    int getSizeInBytes() {
        return this.byteBuf.getInt(this.byteBuf.position());
    }

    @Override // com.mongodb.internal.connection.AbstractByteBufBsonDocument
    BsonDocument toBsonDocument() {
        ByteBuf duplicateByteBuf = this.byteBuf.duplicate();
        BsonBinaryReader bsonReader = new BsonBinaryReader(new ByteBufferBsonInput(duplicateByteBuf));
        try {
            BsonDocument bsonDocumentDecode = new BsonDocumentCodec().decode((BsonReader) bsonReader, DecoderContext.builder().build());
            duplicateByteBuf.release();
            bsonReader.close();
            return bsonDocumentDecode;
        } catch (Throwable th) {
            duplicateByteBuf.release();
            bsonReader.close();
            throw th;
        }
    }

    ByteBufBsonDocument(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
}
