package org.bson;

import java.util.Arrays;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/AbstractBsonReader.class */
public abstract class AbstractBsonReader implements BsonReader {
    private State state = State.INITIAL;
    private Context context;
    private BsonType currentBsonType;
    private String currentName;
    private boolean closed;

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/AbstractBsonReader$State.class */
    public enum State {
        INITIAL,
        TYPE,
        NAME,
        VALUE,
        SCOPE_DOCUMENT,
        END_OF_DOCUMENT,
        END_OF_ARRAY,
        DONE,
        CLOSED
    }

    protected abstract BsonBinary doReadBinaryData();

    protected abstract byte doPeekBinarySubType();

    protected abstract int doPeekBinarySize();

    protected abstract boolean doReadBoolean();

    protected abstract long doReadDateTime();

    protected abstract double doReadDouble();

    protected abstract void doReadEndArray();

    protected abstract void doReadEndDocument();

    protected abstract int doReadInt32();

    protected abstract long doReadInt64();

    protected abstract Decimal128 doReadDecimal128();

    protected abstract String doReadJavaScript();

    protected abstract String doReadJavaScriptWithScope();

    protected abstract void doReadMaxKey();

    protected abstract void doReadMinKey();

    protected abstract void doReadNull();

    protected abstract ObjectId doReadObjectId();

    protected abstract BsonRegularExpression doReadRegularExpression();

    protected abstract BsonDbPointer doReadDBPointer();

    protected abstract void doReadStartArray();

    protected abstract void doReadStartDocument();

    protected abstract String doReadString();

    protected abstract String doReadSymbol();

    protected abstract BsonTimestamp doReadTimestamp();

    protected abstract void doReadUndefined();

    protected abstract void doSkipName();

    protected abstract void doSkipValue();

    @Override // org.bson.BsonReader
    public abstract BsonType readBsonType();

    protected AbstractBsonReader() {
    }

    @Override // org.bson.BsonReader
    public BsonType getCurrentBsonType() {
        return this.currentBsonType;
    }

    @Override // org.bson.BsonReader
    public String getCurrentName() {
        if (this.state != State.VALUE) {
            throwInvalidState("getCurrentName", State.VALUE);
        }
        return this.currentName;
    }

    protected void setCurrentBsonType(BsonType newType) {
        this.currentBsonType = newType;
    }

    public State getState() {
        return this.state;
    }

    protected void setState(State newState) {
        this.state = newState;
    }

    protected void setCurrentName(String newName) {
        this.currentName = newName;
    }

    @Override // org.bson.BsonReader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
    }

    protected boolean isClosed() {
        return this.closed;
    }

    @Override // org.bson.BsonReader
    public BsonBinary readBinaryData() {
        checkPreconditions("readBinaryData", BsonType.BINARY);
        setState(getNextState());
        return doReadBinaryData();
    }

    @Override // org.bson.BsonReader
    public byte peekBinarySubType() {
        checkPreconditions("readBinaryData", BsonType.BINARY);
        return doPeekBinarySubType();
    }

    @Override // org.bson.BsonReader
    public int peekBinarySize() {
        checkPreconditions("readBinaryData", BsonType.BINARY);
        return doPeekBinarySize();
    }

    @Override // org.bson.BsonReader
    public boolean readBoolean() {
        checkPreconditions("readBoolean", BsonType.BOOLEAN);
        setState(getNextState());
        return doReadBoolean();
    }

    @Override // org.bson.BsonReader
    public long readDateTime() {
        checkPreconditions("readDateTime", BsonType.DATE_TIME);
        setState(getNextState());
        return doReadDateTime();
    }

    @Override // org.bson.BsonReader
    public double readDouble() {
        checkPreconditions("readDouble", BsonType.DOUBLE);
        setState(getNextState());
        return doReadDouble();
    }

    @Override // org.bson.BsonReader
    public void readEndArray() {
        if (isClosed()) {
            throw new IllegalStateException("BSONBinaryWriter");
        }
        if (getContext().getContextType() != BsonContextType.ARRAY) {
            throwInvalidContextType("readEndArray", getContext().getContextType(), BsonContextType.ARRAY);
        }
        if (getState() == State.TYPE) {
            readBsonType();
        }
        if (getState() != State.END_OF_ARRAY) {
            throwInvalidState("ReadEndArray", State.END_OF_ARRAY);
        }
        doReadEndArray();
        setStateOnEnd();
    }

    @Override // org.bson.BsonReader
    public void readEndDocument() {
        if (isClosed()) {
            throw new IllegalStateException("BSONBinaryWriter");
        }
        if (getContext().getContextType() != BsonContextType.DOCUMENT && getContext().getContextType() != BsonContextType.SCOPE_DOCUMENT) {
            throwInvalidContextType("readEndDocument", getContext().getContextType(), BsonContextType.DOCUMENT, BsonContextType.SCOPE_DOCUMENT);
        }
        if (getState() == State.TYPE) {
            readBsonType();
        }
        if (getState() != State.END_OF_DOCUMENT) {
            throwInvalidState("readEndDocument", State.END_OF_DOCUMENT);
        }
        doReadEndDocument();
        setStateOnEnd();
    }

    @Override // org.bson.BsonReader
    public int readInt32() {
        checkPreconditions("readInt32", BsonType.INT32);
        setState(getNextState());
        return doReadInt32();
    }

    @Override // org.bson.BsonReader
    public long readInt64() {
        checkPreconditions("readInt64", BsonType.INT64);
        setState(getNextState());
        return doReadInt64();
    }

    @Override // org.bson.BsonReader
    public Decimal128 readDecimal128() {
        checkPreconditions("readDecimal", BsonType.DECIMAL128);
        setState(getNextState());
        return doReadDecimal128();
    }

    @Override // org.bson.BsonReader
    public String readJavaScript() {
        checkPreconditions("readJavaScript", BsonType.JAVASCRIPT);
        setState(getNextState());
        return doReadJavaScript();
    }

    @Override // org.bson.BsonReader
    public String readJavaScriptWithScope() {
        checkPreconditions("readJavaScriptWithScope", BsonType.JAVASCRIPT_WITH_SCOPE);
        setState(State.SCOPE_DOCUMENT);
        return doReadJavaScriptWithScope();
    }

    @Override // org.bson.BsonReader
    public void readMaxKey() {
        checkPreconditions("readMaxKey", BsonType.MAX_KEY);
        setState(getNextState());
        doReadMaxKey();
    }

    @Override // org.bson.BsonReader
    public void readMinKey() {
        checkPreconditions("readMinKey", BsonType.MIN_KEY);
        setState(getNextState());
        doReadMinKey();
    }

    @Override // org.bson.BsonReader
    public void readNull() {
        checkPreconditions("readNull", BsonType.NULL);
        setState(getNextState());
        doReadNull();
    }

    @Override // org.bson.BsonReader
    public ObjectId readObjectId() {
        checkPreconditions("readObjectId", BsonType.OBJECT_ID);
        setState(getNextState());
        return doReadObjectId();
    }

    @Override // org.bson.BsonReader
    public BsonRegularExpression readRegularExpression() {
        checkPreconditions("readRegularExpression", BsonType.REGULAR_EXPRESSION);
        setState(getNextState());
        return doReadRegularExpression();
    }

    @Override // org.bson.BsonReader
    public BsonDbPointer readDBPointer() {
        checkPreconditions("readDBPointer", BsonType.DB_POINTER);
        setState(getNextState());
        return doReadDBPointer();
    }

    @Override // org.bson.BsonReader
    public void readStartArray() {
        checkPreconditions("readStartArray", BsonType.ARRAY);
        doReadStartArray();
        setState(State.TYPE);
    }

    @Override // org.bson.BsonReader
    public void readStartDocument() {
        checkPreconditions("readStartDocument", BsonType.DOCUMENT);
        doReadStartDocument();
        setState(State.TYPE);
    }

    @Override // org.bson.BsonReader
    public String readString() {
        checkPreconditions("readString", BsonType.STRING);
        setState(getNextState());
        return doReadString();
    }

    @Override // org.bson.BsonReader
    public String readSymbol() {
        checkPreconditions("readSymbol", BsonType.SYMBOL);
        setState(getNextState());
        return doReadSymbol();
    }

    @Override // org.bson.BsonReader
    public BsonTimestamp readTimestamp() {
        checkPreconditions("readTimestamp", BsonType.TIMESTAMP);
        setState(getNextState());
        return doReadTimestamp();
    }

    @Override // org.bson.BsonReader
    public void readUndefined() {
        checkPreconditions("readUndefined", BsonType.UNDEFINED);
        setState(getNextState());
        doReadUndefined();
    }

    @Override // org.bson.BsonReader
    public void skipName() {
        if (isClosed()) {
            throw new IllegalStateException("This instance has been closed");
        }
        if (getState() != State.NAME) {
            throwInvalidState("skipName", State.NAME);
        }
        setState(State.VALUE);
        doSkipName();
    }

    @Override // org.bson.BsonReader
    public void skipValue() {
        if (isClosed()) {
            throw new IllegalStateException("BSONBinaryWriter");
        }
        if (getState() != State.VALUE) {
            throwInvalidState("skipValue", State.VALUE);
        }
        doSkipValue();
        setState(State.TYPE);
    }

    @Override // org.bson.BsonReader
    public BsonBinary readBinaryData(String name) {
        verifyName(name);
        return readBinaryData();
    }

    @Override // org.bson.BsonReader
    public boolean readBoolean(String name) {
        verifyName(name);
        return readBoolean();
    }

    @Override // org.bson.BsonReader
    public long readDateTime(String name) {
        verifyName(name);
        return readDateTime();
    }

    @Override // org.bson.BsonReader
    public double readDouble(String name) {
        verifyName(name);
        return readDouble();
    }

    @Override // org.bson.BsonReader
    public int readInt32(String name) {
        verifyName(name);
        return readInt32();
    }

    @Override // org.bson.BsonReader
    public long readInt64(String name) {
        verifyName(name);
        return readInt64();
    }

    @Override // org.bson.BsonReader
    public Decimal128 readDecimal128(String name) {
        verifyName(name);
        return readDecimal128();
    }

    @Override // org.bson.BsonReader
    public String readJavaScript(String name) {
        verifyName(name);
        return readJavaScript();
    }

    @Override // org.bson.BsonReader
    public String readJavaScriptWithScope(String name) {
        verifyName(name);
        return readJavaScriptWithScope();
    }

    @Override // org.bson.BsonReader
    public void readMaxKey(String name) {
        verifyName(name);
        readMaxKey();
    }

    @Override // org.bson.BsonReader
    public void readMinKey(String name) {
        verifyName(name);
        readMinKey();
    }

    @Override // org.bson.BsonReader
    public String readName() {
        if (this.state == State.TYPE) {
            readBsonType();
        }
        if (this.state != State.NAME) {
            throwInvalidState("readName", State.NAME);
        }
        this.state = State.VALUE;
        return this.currentName;
    }

    @Override // org.bson.BsonReader
    public void readName(String name) {
        verifyName(name);
    }

    @Override // org.bson.BsonReader
    public void readNull(String name) {
        verifyName(name);
        readNull();
    }

    @Override // org.bson.BsonReader
    public ObjectId readObjectId(String name) {
        verifyName(name);
        return readObjectId();
    }

    @Override // org.bson.BsonReader
    public BsonRegularExpression readRegularExpression(String name) {
        verifyName(name);
        return readRegularExpression();
    }

    @Override // org.bson.BsonReader
    public BsonDbPointer readDBPointer(String name) {
        verifyName(name);
        return readDBPointer();
    }

    @Override // org.bson.BsonReader
    public String readString(String name) {
        verifyName(name);
        return readString();
    }

    @Override // org.bson.BsonReader
    public String readSymbol(String name) {
        verifyName(name);
        return readSymbol();
    }

    @Override // org.bson.BsonReader
    public BsonTimestamp readTimestamp(String name) {
        verifyName(name);
        return readTimestamp();
    }

    @Override // org.bson.BsonReader
    public void readUndefined(String name) {
        verifyName(name);
        readUndefined();
    }

    protected void throwInvalidContextType(String methodName, BsonContextType actualContextType, BsonContextType... validContextTypes) {
        String validContextTypesString = StringUtils.join(" or ", Arrays.asList(validContextTypes));
        String message = String.format("%s can only be called when ContextType is %s, not when ContextType is %s.", methodName, validContextTypesString, actualContextType);
        throw new BsonInvalidOperationException(message);
    }

    protected void throwInvalidState(String methodName, State... validStates) {
        String validStatesString = StringUtils.join(" or ", Arrays.asList(validStates));
        String message = String.format("%s can only be called when State is %s, not when State is %s.", methodName, validStatesString, this.state);
        throw new BsonInvalidOperationException(message);
    }

    protected void verifyBSONType(String methodName, BsonType requiredBsonType) {
        if (this.state == State.INITIAL || this.state == State.SCOPE_DOCUMENT || this.state == State.TYPE) {
            readBsonType();
        }
        if (this.state == State.NAME) {
            skipName();
        }
        if (this.state != State.VALUE) {
            throwInvalidState(methodName, State.VALUE);
        }
        if (this.currentBsonType != requiredBsonType) {
            throw new BsonInvalidOperationException(String.format("%s can only be called when CurrentBSONType is %s, not when CurrentBSONType is %s.", methodName, requiredBsonType, this.currentBsonType));
        }
    }

    protected void verifyName(String expectedName) {
        readBsonType();
        String actualName = readName();
        if (!actualName.equals(expectedName)) {
            throw new BsonSerializationException(String.format("Expected element name to be '%s', not '%s'.", expectedName, actualName));
        }
    }

    protected void checkPreconditions(String methodName, BsonType type) {
        if (isClosed()) {
            throw new IllegalStateException("BsonWriter is closed");
        }
        verifyBSONType(methodName, type);
    }

    protected Context getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setContext(Context context) {
        this.context = context;
    }

    protected State getNextState() {
        switch (this.context.getContextType()) {
            case ARRAY:
            case DOCUMENT:
            case SCOPE_DOCUMENT:
                return State.TYPE;
            case TOP_LEVEL:
                return State.DONE;
            default:
                throw new BSONException(String.format("Unexpected ContextType %s.", this.context.getContextType()));
        }
    }

    private void setStateOnEnd() {
        switch (getContext().getContextType()) {
            case ARRAY:
            case DOCUMENT:
                setState(State.TYPE);
                return;
            case SCOPE_DOCUMENT:
            default:
                throw new BSONException(String.format("Unexpected ContextType %s.", getContext().getContextType()));
            case TOP_LEVEL:
                setState(State.DONE);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/AbstractBsonReader$Mark.class */
    public class Mark implements BsonReaderMark {
        private final State state;
        private final Context parentContext;
        private final BsonContextType contextType;
        private final BsonType currentBsonType;
        private final String currentName;

        protected Context getParentContext() {
            return this.parentContext;
        }

        protected BsonContextType getContextType() {
            return this.contextType;
        }

        protected Mark() {
            this.state = AbstractBsonReader.this.state;
            this.parentContext = AbstractBsonReader.this.context.parentContext;
            this.contextType = AbstractBsonReader.this.context.contextType;
            this.currentBsonType = AbstractBsonReader.this.currentBsonType;
            this.currentName = AbstractBsonReader.this.currentName;
        }

        @Override // org.bson.BsonReaderMark
        public void reset() {
            AbstractBsonReader.this.state = this.state;
            AbstractBsonReader.this.currentBsonType = this.currentBsonType;
            AbstractBsonReader.this.currentName = this.currentName;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/AbstractBsonReader$Context.class */
    public abstract class Context {
        private final Context parentContext;
        private final BsonContextType contextType;

        protected Context(Context parentContext, BsonContextType contextType) {
            this.parentContext = parentContext;
            this.contextType = contextType;
        }

        protected Context getParentContext() {
            return this.parentContext;
        }

        protected BsonContextType getContextType() {
            return this.contextType;
        }
    }
}
