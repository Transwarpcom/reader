package org.bson;

import org.bson.AbstractBsonWriter;
import org.bson.io.Bits;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BSONCallbackAdapter.class */
class BSONCallbackAdapter extends AbstractBsonWriter {
    private BSONCallback bsonCallback;

    protected BSONCallbackAdapter(BsonWriterSettings settings, BSONCallback bsonCallback) {
        super(settings);
        this.bsonCallback = bsonCallback;
    }

    @Override // org.bson.BsonWriter
    public void flush() {
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteStartDocument() {
        BsonContextType bsonContextType;
        if (getState() == AbstractBsonWriter.State.SCOPE_DOCUMENT) {
            bsonContextType = BsonContextType.SCOPE_DOCUMENT;
        } else {
            bsonContextType = BsonContextType.DOCUMENT;
        }
        BsonContextType contextType = bsonContextType;
        if (getContext() == null || contextType == BsonContextType.SCOPE_DOCUMENT) {
            this.bsonCallback.objectStart();
        } else {
            this.bsonCallback.objectStart(getName());
        }
        setContext(new Context(getContext(), contextType));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteEndDocument() {
        BsonContextType contextType = getContext().getContextType();
        setContext(getContext().getParentContext());
        this.bsonCallback.objectDone();
        if (contextType == BsonContextType.SCOPE_DOCUMENT) {
            Object scope = this.bsonCallback.get();
            this.bsonCallback = getContext().callback;
            this.bsonCallback.gotCodeWScope(getContext().name, getContext().code, scope);
        }
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteStartArray() {
        this.bsonCallback.arrayStart(getName());
        setContext(new Context(getContext(), BsonContextType.ARRAY));
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteEndArray() {
        setContext(getContext().getParentContext());
        this.bsonCallback.arrayDone();
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteBinaryData(BsonBinary value) {
        if (value.getType() == BsonBinarySubType.UUID_LEGACY.getValue()) {
            this.bsonCallback.gotUUID(getName(), Bits.readLong(value.getData(), 0), Bits.readLong(value.getData(), 8));
        } else {
            this.bsonCallback.gotBinary(getName(), value.getType(), value.getData());
        }
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteBoolean(boolean value) {
        this.bsonCallback.gotBoolean(getName(), value);
        setState(getNextState());
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDateTime(long value) {
        this.bsonCallback.gotDate(getName(), value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDBPointer(BsonDbPointer value) {
        this.bsonCallback.gotDBRef(getName(), value.getNamespace(), value.getId());
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDouble(double value) {
        this.bsonCallback.gotDouble(getName(), value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteInt32(int value) {
        this.bsonCallback.gotInt(getName(), value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteInt64(long value) {
        this.bsonCallback.gotLong(getName(), value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDecimal128(Decimal128 value) {
        this.bsonCallback.gotDecimal128(getName(), value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteJavaScript(String value) {
        this.bsonCallback.gotCode(getName(), value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteJavaScriptWithScope(String value) {
        getContext().callback = this.bsonCallback;
        getContext().code = value;
        getContext().name = getName();
        this.bsonCallback = this.bsonCallback.createBSONCallback();
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteMaxKey() {
        this.bsonCallback.gotMaxKey(getName());
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteMinKey() {
        this.bsonCallback.gotMinKey(getName());
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteNull() {
        this.bsonCallback.gotNull(getName());
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteObjectId(ObjectId value) {
        this.bsonCallback.gotObjectId(getName(), value);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteRegularExpression(BsonRegularExpression value) {
        this.bsonCallback.gotRegex(getName(), value.getPattern(), value.getOptions());
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteString(String value) {
        this.bsonCallback.gotString(getName(), value);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteSymbol(String value) {
        this.bsonCallback.gotSymbol(getName(), value);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteTimestamp(BsonTimestamp value) {
        this.bsonCallback.gotTimestamp(getName(), value.getTime(), value.getInc());
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteUndefined() {
        this.bsonCallback.gotUndefined(getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bson.AbstractBsonWriter
    public Context getContext() {
        return (Context) super.getContext();
    }

    @Override // org.bson.AbstractBsonWriter
    protected String getName() {
        if (getContext().getContextType() == BsonContextType.ARRAY) {
            return Integer.toString(Context.access$308(getContext()));
        }
        return super.getName();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BSONCallbackAdapter$Context.class */
    public class Context extends AbstractBsonWriter.Context {
        private int index;
        private BSONCallback callback;
        private String code;
        private String name;

        static /* synthetic */ int access$308(Context x0) {
            int i = x0.index;
            x0.index = i + 1;
            return i;
        }

        Context(Context parentContext, BsonContextType contextType) {
            super(parentContext, contextType);
        }

        @Override // org.bson.AbstractBsonWriter.Context
        public Context getParentContext() {
            return (Context) super.getParentContext();
        }
    }
}
