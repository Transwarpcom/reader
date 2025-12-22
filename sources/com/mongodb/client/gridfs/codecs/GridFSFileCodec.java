package com.mongodb.client.gridfs.codecs;

import com.jayway.jsonpath.internal.function.text.Length;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.lang.Nullable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonDocumentReader;
import org.bson.BsonDocumentWrapper;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/gridfs/codecs/GridFSFileCodec.class */
public final class GridFSFileCodec implements Codec<GridFSFile> {
    private static final List<String> VALID_FIELDS = Arrays.asList("_id", "filename", Length.TOKEN_NAME, "chunkSize", "uploadDate", "md5", PackageDocumentBase.OPFTags.metadata);
    private final Codec<Document> documentCodec;
    private final Codec<BsonDocument> bsonDocumentCodec;

    public GridFSFileCodec(CodecRegistry registry) {
        this.documentCodec = (Codec) Assertions.notNull("DocumentCodec", ((CodecRegistry) Assertions.notNull("registry", registry)).get(Document.class));
        this.bsonDocumentCodec = (Codec) Assertions.notNull("BsonDocumentCodec", registry.get(BsonDocument.class));
    }

    @Override // org.bson.codecs.Decoder
    public GridFSFile decode(BsonReader reader, DecoderContext decoderContext) {
        BsonDocument bsonDocument = this.bsonDocumentCodec.decode(reader, decoderContext);
        BsonValue id = bsonDocument.get("_id");
        String filename = bsonDocument.get("filename", new BsonString("")).asString().getValue();
        long length = bsonDocument.getNumber(Length.TOKEN_NAME).longValue();
        int chunkSize = bsonDocument.getNumber("chunkSize").intValue();
        Date uploadDate = new Date(bsonDocument.getDateTime("uploadDate").getValue());
        String md5 = bsonDocument.containsKey("md5") ? bsonDocument.getString("md5").getValue() : null;
        BsonDocument metadataBsonDocument = bsonDocument.getDocument(PackageDocumentBase.OPFTags.metadata, new BsonDocument());
        Document optionalMetadata = asDocumentOrNull(metadataBsonDocument);
        for (String key : VALID_FIELDS) {
            bsonDocument.remove((Object) key);
        }
        Document deprecatedExtraElements = asDocumentOrNull(bsonDocument);
        return new GridFSFile(id, filename, length, chunkSize, uploadDate, md5, optionalMetadata, deprecatedExtraElements);
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, GridFSFile value, EncoderContext encoderContext) {
        BsonDocument bsonDocument = new BsonDocument();
        bsonDocument.put("_id", value.getId());
        bsonDocument.put("filename", (BsonValue) new BsonString(value.getFilename()));
        bsonDocument.put(Length.TOKEN_NAME, (BsonValue) new BsonInt64(value.getLength()));
        bsonDocument.put("chunkSize", (BsonValue) new BsonInt32(value.getChunkSize()));
        bsonDocument.put("uploadDate", (BsonValue) new BsonDateTime(value.getUploadDate().getTime()));
        if (value.getMD5() != null) {
            bsonDocument.put("md5", (BsonValue) new BsonString(value.getMD5()));
        }
        Document metadata = value.getMetadata();
        if (metadata != null) {
            bsonDocument.put(PackageDocumentBase.OPFTags.metadata, (BsonValue) new BsonDocumentWrapper(metadata, this.documentCodec));
        }
        Document extraElements = value.getExtraElements();
        if (extraElements != null) {
            bsonDocument.putAll(new BsonDocumentWrapper(extraElements, this.documentCodec));
        }
        this.bsonDocumentCodec.encode(writer, bsonDocument, encoderContext);
    }

    @Override // org.bson.codecs.Encoder
    public Class<GridFSFile> getEncoderClass() {
        return GridFSFile.class;
    }

    @Nullable
    private Document asDocumentOrNull(BsonDocument bsonDocument) {
        if (bsonDocument.isEmpty()) {
            return null;
        }
        BsonDocumentReader reader = new BsonDocumentReader(bsonDocument);
        return this.documentCodec.decode(reader, DecoderContext.builder().build());
    }
}
