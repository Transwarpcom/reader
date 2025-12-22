package org.bson.codecs;

import java.util.UUID;
import org.bson.BSONException;
import org.bson.BsonBinary;
import org.bson.BsonBinarySubType;
import org.bson.BsonReader;
import org.bson.BsonSerializationException;
import org.bson.BsonWriter;
import org.bson.UuidRepresentation;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/UuidCodec.class */
public class UuidCodec implements Codec<UUID> {
    private final UuidRepresentation encoderUuidRepresentation;
    private final UuidRepresentation decoderUuidRepresentation;

    public UuidCodec(UuidRepresentation uuidRepresentation) {
        this.encoderUuidRepresentation = uuidRepresentation;
        this.decoderUuidRepresentation = uuidRepresentation;
    }

    public UuidCodec() {
        this.encoderUuidRepresentation = UuidRepresentation.JAVA_LEGACY;
        this.decoderUuidRepresentation = UuidRepresentation.JAVA_LEGACY;
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, UUID value, EncoderContext encoderContext) {
        byte[] binaryData = new byte[16];
        writeLongToArrayBigEndian(binaryData, 0, value.getMostSignificantBits());
        writeLongToArrayBigEndian(binaryData, 8, value.getLeastSignificantBits());
        switch (this.encoderUuidRepresentation) {
            case C_SHARP_LEGACY:
                UuidCodecHelper.reverseByteArray(binaryData, 0, 4);
                UuidCodecHelper.reverseByteArray(binaryData, 4, 2);
                UuidCodecHelper.reverseByteArray(binaryData, 6, 2);
                break;
            case JAVA_LEGACY:
                UuidCodecHelper.reverseByteArray(binaryData, 0, 8);
                UuidCodecHelper.reverseByteArray(binaryData, 8, 8);
                break;
            case PYTHON_LEGACY:
            case STANDARD:
                break;
            default:
                throw new BSONException("Unexpected UUID representation");
        }
        if (this.encoderUuidRepresentation == UuidRepresentation.STANDARD) {
            writer.writeBinaryData(new BsonBinary(BsonBinarySubType.UUID_STANDARD, binaryData));
        } else {
            writer.writeBinaryData(new BsonBinary(BsonBinarySubType.UUID_LEGACY, binaryData));
        }
    }

    @Override // org.bson.codecs.Decoder
    public UUID decode(BsonReader reader, DecoderContext decoderContext) {
        byte subType = reader.peekBinarySubType();
        if (subType != BsonBinarySubType.UUID_LEGACY.getValue() && subType != BsonBinarySubType.UUID_STANDARD.getValue()) {
            throw new BSONException("Unexpected BsonBinarySubType");
        }
        byte[] bytes = reader.readBinaryData().getData();
        if (bytes.length != 16) {
            throw new BsonSerializationException(String.format("Expected length to be 16, not %d.", Integer.valueOf(bytes.length)));
        }
        if (subType == BsonBinarySubType.UUID_LEGACY.getValue()) {
            switch (this.decoderUuidRepresentation) {
                case C_SHARP_LEGACY:
                    UuidCodecHelper.reverseByteArray(bytes, 0, 4);
                    UuidCodecHelper.reverseByteArray(bytes, 4, 2);
                    UuidCodecHelper.reverseByteArray(bytes, 6, 2);
                    break;
                case JAVA_LEGACY:
                    UuidCodecHelper.reverseByteArray(bytes, 0, 8);
                    UuidCodecHelper.reverseByteArray(bytes, 8, 8);
                    break;
                case PYTHON_LEGACY:
                case STANDARD:
                    break;
                default:
                    throw new BSONException("Unexpected UUID representation");
            }
        }
        return new UUID(readLongFromArrayBigEndian(bytes, 0), readLongFromArrayBigEndian(bytes, 8));
    }

    @Override // org.bson.codecs.Encoder
    public Class<UUID> getEncoderClass() {
        return UUID.class;
    }

    private static void writeLongToArrayBigEndian(byte[] bytes, int offset, long x) {
        bytes[offset + 7] = (byte) (255 & x);
        bytes[offset + 6] = (byte) (255 & (x >> 8));
        bytes[offset + 5] = (byte) (255 & (x >> 16));
        bytes[offset + 4] = (byte) (255 & (x >> 24));
        bytes[offset + 3] = (byte) (255 & (x >> 32));
        bytes[offset + 2] = (byte) (255 & (x >> 40));
        bytes[offset + 1] = (byte) (255 & (x >> 48));
        bytes[offset] = (byte) (255 & (x >> 56));
    }

    private static long readLongFromArrayBigEndian(byte[] bytes, int offset) {
        long x = 0 | (255 & bytes[offset + 7]);
        return x | ((255 & bytes[offset + 6]) << 8) | ((255 & bytes[offset + 5]) << 16) | ((255 & bytes[offset + 4]) << 24) | ((255 & bytes[offset + 3]) << 32) | ((255 & bytes[offset + 2]) << 40) | ((255 & bytes[offset + 1]) << 48) | ((255 & bytes[offset]) << 56);
    }
}
