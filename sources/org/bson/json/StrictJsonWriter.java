package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/StrictJsonWriter.class */
public interface StrictJsonWriter {
    void writeName(String str);

    void writeBoolean(boolean z);

    void writeBoolean(String str, boolean z);

    void writeNumber(String str);

    void writeNumber(String str, String str2);

    void writeString(String str);

    void writeString(String str, String str2);

    void writeRaw(String str);

    void writeRaw(String str, String str2);

    void writeNull();

    void writeNull(String str);

    void writeStartArray();

    void writeStartArray(String str);

    void writeStartObject();

    void writeStartObject(String str);

    void writeEndArray();

    void writeEndObject();

    boolean isTruncated();
}
