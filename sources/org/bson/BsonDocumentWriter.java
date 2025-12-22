package org.bson;

import org.bson.AbstractBsonWriter;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDocumentWriter.class */
public class BsonDocumentWriter extends AbstractBsonWriter {
    private final BsonDocument document;

    public BsonDocumentWriter(BsonDocument document) {
        super(new BsonWriterSettings());
        this.document = document;
        setContext(new Context());
    }

    public BsonDocument getDocument() {
        return this.document;
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteStartDocument() {
        switch (getState()) {
            case INITIAL:
                setContext(new Context(this.document, BsonContextType.DOCUMENT, getContext()));
                return;
            case VALUE:
                setContext(new Context(new BsonDocument(), BsonContextType.DOCUMENT, getContext()));
                return;
            case SCOPE_DOCUMENT:
                setContext(new Context(new BsonDocument(), BsonContextType.SCOPE_DOCUMENT, getContext()));
                return;
            default:
                throw new BsonInvalidOperationException("Unexpected state " + getState());
        }
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteEndDocument() {
        BsonValue value = getContext().container;
        setContext(getContext().getParentContext());
        if (getContext().getContextType() != BsonContextType.JAVASCRIPT_WITH_SCOPE) {
            if (getContext().getContextType() != BsonContextType.TOP_LEVEL) {
                write(value);
            }
        } else {
            BsonDocument scope = (BsonDocument) value;
            BsonString code = (BsonString) getContext().container;
            setContext(getContext().getParentContext());
            write(new BsonJavaScriptWithScope(code.getValue(), scope));
        }
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteStartArray() {
        setContext(new Context(new BsonArray(), BsonContextType.ARRAY, getContext()));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteEndArray() {
        BsonValue array = getContext().container;
        setContext(getContext().getParentContext());
        write(array);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteBinaryData(BsonBinary value) {
        write(value);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteBoolean(boolean value) {
        write(BsonBoolean.valueOf(value));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDateTime(long value) {
        write(new BsonDateTime(value));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDBPointer(BsonDbPointer value) {
        write(value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDouble(double value) {
        write(new BsonDouble(value));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteInt32(int value) {
        write(new BsonInt32(value));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteInt64(long value) {
        write(new BsonInt64(value));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDecimal128(Decimal128 value) {
        write(new BsonDecimal128(value));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteJavaScript(String value) {
        write(new BsonJavaScript(value));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteJavaScriptWithScope(String value) {
        setContext(new Context(new BsonString(value), BsonContextType.JAVASCRIPT_WITH_SCOPE, getContext()));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteMaxKey() {
        write(new BsonMaxKey());
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteMinKey() {
        write(new BsonMinKey());
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteNull() {
        write(BsonNull.VALUE);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteObjectId(ObjectId value) {
        write(new BsonObjectId(value));
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteRegularExpression(BsonRegularExpression value) {
        write(value);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteString(String value) {
        write(new BsonString(value));
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteSymbol(String value) {
        write(new BsonSymbol(value));
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteTimestamp(BsonTimestamp value) {
        write(value);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteUndefined() {
        write(new BsonUndefined());
    }

    @Override // org.bson.BsonWriter
    public void flush() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bson.AbstractBsonWriter
    public Context getContext() {
        return (Context) super.getContext();
    }

    private void write(BsonValue value) {
        getContext().add(value);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDocumentWriter$Context.class */
    private class Context extends AbstractBsonWriter.Context {
        private BsonValue container;

        Context(BsonValue container, BsonContextType contextType, Context parent) {
            super(parent, contextType);
            this.container = container;
        }

        Context() {
            super(null, BsonContextType.TOP_LEVEL);
        }

        void add(BsonValue value) {
            if (this.container instanceof BsonArray) {
                ((BsonArray) this.container).add(value);
            } else {
                ((BsonDocument) this.container).put(BsonDocumentWriter.this.getName(), value);
            }
        }
    }
}
