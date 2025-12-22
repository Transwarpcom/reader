package org.bson.json;

import java.io.IOException;
import java.io.Writer;
import org.bson.AbstractBsonWriter;
import org.bson.BsonBinary;
import org.bson.BsonContextType;
import org.bson.BsonDbPointer;
import org.bson.BsonRegularExpression;
import org.bson.BsonTimestamp;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonWriter.class */
public class JsonWriter extends AbstractBsonWriter {
    private final JsonWriterSettings settings;
    private final StrictCharacterStreamJsonWriter strictJsonWriter;

    public JsonWriter(Writer writer) {
        this(writer, new JsonWriterSettings());
    }

    public JsonWriter(Writer writer, JsonWriterSettings settings) {
        super(settings);
        this.settings = settings;
        setContext(new Context(null, BsonContextType.TOP_LEVEL));
        this.strictJsonWriter = new StrictCharacterStreamJsonWriter(writer, StrictCharacterStreamJsonWriterSettings.builder().indent(settings.isIndent()).newLineCharacters(settings.getNewLineCharacters()).indentCharacters(settings.getIndentCharacters()).maxLength(settings.getMaxLength()).build());
    }

    public Writer getWriter() {
        return this.strictJsonWriter.getWriter();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bson.AbstractBsonWriter
    public Context getContext() {
        return (Context) super.getContext();
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteName(String name) throws IOException {
        this.strictJsonWriter.writeName(name);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteStartDocument() throws IOException {
        this.strictJsonWriter.writeStartObject();
        BsonContextType contextType = getState() == AbstractBsonWriter.State.SCOPE_DOCUMENT ? BsonContextType.SCOPE_DOCUMENT : BsonContextType.DOCUMENT;
        setContext(new Context(getContext(), contextType));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteEndDocument() throws IOException {
        this.strictJsonWriter.writeEndObject();
        if (getContext().getContextType() == BsonContextType.SCOPE_DOCUMENT) {
            setContext(getContext().getParentContext());
            writeEndDocument();
        } else {
            setContext(getContext().getParentContext());
        }
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteStartArray() throws IOException {
        this.strictJsonWriter.writeStartArray();
        setContext(new Context(getContext(), BsonContextType.ARRAY));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteEndArray() throws IOException {
        this.strictJsonWriter.writeEndArray();
        setContext(getContext().getParentContext());
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteBinaryData(BsonBinary binary) {
        this.settings.getBinaryConverter().convert(binary, this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteBoolean(boolean value) {
        this.settings.getBooleanConverter().convert(Boolean.valueOf(value), this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDateTime(long value) {
        this.settings.getDateTimeConverter().convert(Long.valueOf(value), this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDBPointer(BsonDbPointer value) {
        if (this.settings.getOutputMode() == JsonMode.EXTENDED) {
            new Converter<BsonDbPointer>() { // from class: org.bson.json.JsonWriter.1
                @Override // org.bson.json.Converter
                public void convert(BsonDbPointer value1, StrictJsonWriter writer) {
                    writer.writeStartObject();
                    writer.writeStartObject("$dbPointer");
                    writer.writeString("$ref", value1.getNamespace());
                    writer.writeName("$id");
                    JsonWriter.this.doWriteObjectId(value1.getId());
                    writer.writeEndObject();
                    writer.writeEndObject();
                }
            }.convert(value, (StrictJsonWriter) this.strictJsonWriter);
        } else {
            new Converter<BsonDbPointer>() { // from class: org.bson.json.JsonWriter.2
                @Override // org.bson.json.Converter
                public void convert(BsonDbPointer value1, StrictJsonWriter writer) {
                    writer.writeStartObject();
                    writer.writeString("$ref", value1.getNamespace());
                    writer.writeName("$id");
                    JsonWriter.this.doWriteObjectId(value1.getId());
                    writer.writeEndObject();
                }
            }.convert(value, (StrictJsonWriter) this.strictJsonWriter);
        }
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDouble(double value) {
        this.settings.getDoubleConverter().convert(Double.valueOf(value), this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteInt32(int value) {
        this.settings.getInt32Converter().convert(Integer.valueOf(value), this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteInt64(long value) {
        this.settings.getInt64Converter().convert(Long.valueOf(value), this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDecimal128(Decimal128 value) {
        this.settings.getDecimal128Converter().convert(value, this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteJavaScript(String code) {
        this.settings.getJavaScriptConverter().convert(code, this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteJavaScriptWithScope(String code) {
        writeStartDocument();
        writeString("$code", code);
        writeName("$scope");
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteMaxKey() {
        this.settings.getMaxKeyConverter().convert(null, this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteMinKey() {
        this.settings.getMinKeyConverter().convert(null, this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteNull() {
        this.settings.getNullConverter().convert(null, this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteObjectId(ObjectId objectId) {
        this.settings.getObjectIdConverter().convert(objectId, this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteRegularExpression(BsonRegularExpression regularExpression) {
        this.settings.getRegularExpressionConverter().convert(regularExpression, this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteString(String value) {
        this.settings.getStringConverter().convert(value, this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteSymbol(String value) {
        this.settings.getSymbolConverter().convert(value, this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteTimestamp(BsonTimestamp value) {
        this.settings.getTimestampConverter().convert(value, this.strictJsonWriter);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteUndefined() {
        this.settings.getUndefinedConverter().convert(null, this.strictJsonWriter);
    }

    @Override // org.bson.BsonWriter
    public void flush() throws IOException {
        this.strictJsonWriter.flush();
    }

    public boolean isTruncated() {
        return this.strictJsonWriter.isTruncated();
    }

    @Override // org.bson.AbstractBsonWriter
    protected boolean abortPipe() {
        return this.strictJsonWriter.isTruncated();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonWriter$Context.class */
    public class Context extends AbstractBsonWriter.Context {
        @Deprecated
        public Context(JsonWriter this$0, Context parentContext, BsonContextType contextType, String indentChars) {
            this(parentContext, contextType);
        }

        public Context(Context parentContext, BsonContextType contextType) {
            super(parentContext, contextType);
        }

        @Override // org.bson.AbstractBsonWriter.Context
        public Context getParentContext() {
            return (Context) super.getParentContext();
        }
    }
}
