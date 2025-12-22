package org.bson;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonWriterSettings.class */
public class BsonWriterSettings {
    private final int maxSerializationDepth;

    public BsonWriterSettings(int maxSerializationDepth) {
        this.maxSerializationDepth = maxSerializationDepth;
    }

    public BsonWriterSettings() {
        this(1024);
    }

    public int getMaxSerializationDepth() {
        return this.maxSerializationDepth;
    }
}
