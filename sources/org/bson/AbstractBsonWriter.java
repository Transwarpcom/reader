package org.bson;

import java.io.Closeable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.bson.assertions.Assertions;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/AbstractBsonWriter.class */
public abstract class AbstractBsonWriter implements BsonWriter, Closeable {
    private final BsonWriterSettings settings;
    private final Stack<FieldNameValidator> fieldNameValidatorStack;
    private State state;
    private Context context;
    private int serializationDepth;
    private boolean closed;

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/AbstractBsonWriter$State.class */
    public enum State {
        INITIAL,
        NAME,
        VALUE,
        SCOPE_DOCUMENT,
        DONE,
        CLOSED
    }

    protected abstract void doWriteStartDocument();

    protected abstract void doWriteEndDocument();

    protected abstract void doWriteStartArray();

    protected abstract void doWriteEndArray();

    protected abstract void doWriteBinaryData(BsonBinary bsonBinary);

    protected abstract void doWriteBoolean(boolean z);

    protected abstract void doWriteDateTime(long j);

    protected abstract void doWriteDBPointer(BsonDbPointer bsonDbPointer);

    protected abstract void doWriteDouble(double d);

    protected abstract void doWriteInt32(int i);

    protected abstract void doWriteInt64(long j);

    protected abstract void doWriteDecimal128(Decimal128 decimal128);

    protected abstract void doWriteJavaScript(String str);

    protected abstract void doWriteJavaScriptWithScope(String str);

    protected abstract void doWriteMaxKey();

    protected abstract void doWriteMinKey();

    protected abstract void doWriteNull();

    protected abstract void doWriteObjectId(ObjectId objectId);

    protected abstract void doWriteRegularExpression(BsonRegularExpression bsonRegularExpression);

    protected abstract void doWriteString(String str);

    protected abstract void doWriteSymbol(String str);

    protected abstract void doWriteTimestamp(BsonTimestamp bsonTimestamp);

    protected abstract void doWriteUndefined();

    protected AbstractBsonWriter(BsonWriterSettings settings) {
        this(settings, new NoOpFieldNameValidator());
    }

    protected AbstractBsonWriter(BsonWriterSettings settings, FieldNameValidator validator) {
        this.fieldNameValidatorStack = new Stack<>();
        if (validator == null) {
            throw new IllegalArgumentException("Validator can not be null");
        }
        this.settings = settings;
        this.fieldNameValidatorStack.push(validator);
        this.state = State.INITIAL;
    }

    protected String getName() {
        return this.context.name;
    }

    protected boolean isClosed() {
        return this.closed;
    }

    protected void setState(State state) {
        this.state = state;
    }

    protected State getState() {
        return this.state;
    }

    protected Context getContext() {
        return this.context;
    }

    protected void setContext(Context context) {
        this.context = context;
    }

    @Override // org.bson.BsonWriter
    public void writeStartDocument(String name) {
        writeName(name);
        writeStartDocument();
    }

    @Override // org.bson.BsonWriter
    public void writeStartDocument() {
        checkPreconditions("writeStartDocument", State.INITIAL, State.VALUE, State.SCOPE_DOCUMENT, State.DONE);
        if (this.context != null && this.context.name != null) {
            this.fieldNameValidatorStack.push(this.fieldNameValidatorStack.peek().getValidatorForField(getName()));
        }
        this.serializationDepth++;
        if (this.serializationDepth > this.settings.getMaxSerializationDepth()) {
            throw new BsonSerializationException("Maximum serialization depth exceeded (does the object being serialized have a circular reference?).");
        }
        doWriteStartDocument();
        setState(State.NAME);
    }

    @Override // org.bson.BsonWriter
    public void writeEndDocument() {
        checkPreconditions("writeEndDocument", State.NAME);
        BsonContextType contextType = getContext().getContextType();
        if (contextType != BsonContextType.DOCUMENT && contextType != BsonContextType.SCOPE_DOCUMENT) {
            throwInvalidContextType("WriteEndDocument", contextType, BsonContextType.DOCUMENT, BsonContextType.SCOPE_DOCUMENT);
        }
        if (this.context.getParentContext() != null && this.context.getParentContext().name != null) {
            this.fieldNameValidatorStack.pop();
        }
        this.serializationDepth--;
        doWriteEndDocument();
        if (getContext() == null || getContext().getContextType() == BsonContextType.TOP_LEVEL) {
            setState(State.DONE);
        } else {
            setState(getNextState());
        }
    }

    @Override // org.bson.BsonWriter
    public void writeStartArray(String name) {
        writeName(name);
        writeStartArray();
    }

    @Override // org.bson.BsonWriter
    public void writeStartArray() {
        checkPreconditions("writeStartArray", State.VALUE);
        if (this.context != null && this.context.name != null) {
            this.fieldNameValidatorStack.push(this.fieldNameValidatorStack.peek().getValidatorForField(getName()));
        }
        this.serializationDepth++;
        if (this.serializationDepth > this.settings.getMaxSerializationDepth()) {
            throw new BsonSerializationException("Maximum serialization depth exceeded (does the object being serialized have a circular reference?).");
        }
        doWriteStartArray();
        setState(State.VALUE);
    }

    @Override // org.bson.BsonWriter
    public void writeEndArray() {
        checkPreconditions("writeEndArray", State.VALUE);
        if (getContext().getContextType() != BsonContextType.ARRAY) {
            throwInvalidContextType("WriteEndArray", getContext().getContextType(), BsonContextType.ARRAY);
        }
        if (this.context.getParentContext() != null && this.context.getParentContext().name != null) {
            this.fieldNameValidatorStack.pop();
        }
        this.serializationDepth--;
        doWriteEndArray();
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeBinaryData(String name, BsonBinary binary) {
        Assertions.notNull("name", name);
        Assertions.notNull("value", binary);
        writeName(name);
        writeBinaryData(binary);
    }

    @Override // org.bson.BsonWriter
    public void writeBinaryData(BsonBinary binary) {
        Assertions.notNull("value", binary);
        checkPreconditions("writeBinaryData", State.VALUE, State.INITIAL);
        doWriteBinaryData(binary);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeBoolean(String name, boolean value) {
        writeName(name);
        writeBoolean(value);
    }

    @Override // org.bson.BsonWriter
    public void writeBoolean(boolean value) {
        checkPreconditions("writeBoolean", State.VALUE, State.INITIAL);
        doWriteBoolean(value);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeDateTime(String name, long value) {
        writeName(name);
        writeDateTime(value);
    }

    @Override // org.bson.BsonWriter
    public void writeDateTime(long value) {
        checkPreconditions("writeDateTime", State.VALUE, State.INITIAL);
        doWriteDateTime(value);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeDBPointer(String name, BsonDbPointer value) {
        Assertions.notNull("name", name);
        Assertions.notNull("value", value);
        writeName(name);
        writeDBPointer(value);
    }

    @Override // org.bson.BsonWriter
    public void writeDBPointer(BsonDbPointer value) {
        Assertions.notNull("value", value);
        checkPreconditions("writeDBPointer", State.VALUE, State.INITIAL);
        doWriteDBPointer(value);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeDouble(String name, double value) {
        writeName(name);
        writeDouble(value);
    }

    @Override // org.bson.BsonWriter
    public void writeDouble(double value) {
        checkPreconditions("writeDBPointer", State.VALUE, State.INITIAL);
        doWriteDouble(value);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeInt32(String name, int value) {
        writeName(name);
        writeInt32(value);
    }

    @Override // org.bson.BsonWriter
    public void writeInt32(int value) {
        checkPreconditions("writeInt32", State.VALUE);
        doWriteInt32(value);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeInt64(String name, long value) {
        writeName(name);
        writeInt64(value);
    }

    @Override // org.bson.BsonWriter
    public void writeInt64(long value) {
        checkPreconditions("writeInt64", State.VALUE);
        doWriteInt64(value);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeDecimal128(Decimal128 value) {
        Assertions.notNull("value", value);
        checkPreconditions("writeInt64", State.VALUE);
        doWriteDecimal128(value);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeDecimal128(String name, Decimal128 value) {
        Assertions.notNull("name", name);
        Assertions.notNull("value", value);
        writeName(name);
        writeDecimal128(value);
    }

    @Override // org.bson.BsonWriter
    public void writeJavaScript(String name, String code) {
        Assertions.notNull("name", name);
        Assertions.notNull("value", code);
        writeName(name);
        writeJavaScript(code);
    }

    @Override // org.bson.BsonWriter
    public void writeJavaScript(String code) {
        Assertions.notNull("value", code);
        checkPreconditions("writeJavaScript", State.VALUE);
        doWriteJavaScript(code);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeJavaScriptWithScope(String name, String code) {
        Assertions.notNull("name", name);
        Assertions.notNull("value", code);
        writeName(name);
        writeJavaScriptWithScope(code);
    }

    @Override // org.bson.BsonWriter
    public void writeJavaScriptWithScope(String code) {
        Assertions.notNull("value", code);
        checkPreconditions("writeJavaScriptWithScope", State.VALUE);
        doWriteJavaScriptWithScope(code);
        setState(State.SCOPE_DOCUMENT);
    }

    @Override // org.bson.BsonWriter
    public void writeMaxKey(String name) {
        writeName(name);
        writeMaxKey();
    }

    @Override // org.bson.BsonWriter
    public void writeMaxKey() {
        checkPreconditions("writeMaxKey", State.VALUE);
        doWriteMaxKey();
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeMinKey(String name) {
        writeName(name);
        writeMinKey();
    }

    @Override // org.bson.BsonWriter
    public void writeMinKey() {
        checkPreconditions("writeMinKey", State.VALUE);
        doWriteMinKey();
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeName(String name) {
        Assertions.notNull("name", name);
        if (this.state != State.NAME) {
            throwInvalidState("WriteName", State.NAME);
        }
        if (!this.fieldNameValidatorStack.peek().validate(name)) {
            throw new IllegalArgumentException(String.format("Invalid BSON field name %s", name));
        }
        doWriteName(name);
        this.context.name = name;
        this.state = State.VALUE;
    }

    protected void doWriteName(String name) {
    }

    @Override // org.bson.BsonWriter
    public void writeNull(String name) {
        writeName(name);
        writeNull();
    }

    @Override // org.bson.BsonWriter
    public void writeNull() {
        checkPreconditions("writeNull", State.VALUE);
        doWriteNull();
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeObjectId(String name, ObjectId objectId) {
        Assertions.notNull("name", name);
        Assertions.notNull("value", objectId);
        writeName(name);
        writeObjectId(objectId);
    }

    @Override // org.bson.BsonWriter
    public void writeObjectId(ObjectId objectId) {
        Assertions.notNull("value", objectId);
        checkPreconditions("writeObjectId", State.VALUE);
        doWriteObjectId(objectId);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeRegularExpression(String name, BsonRegularExpression regularExpression) {
        Assertions.notNull("name", name);
        Assertions.notNull("value", regularExpression);
        writeName(name);
        writeRegularExpression(regularExpression);
    }

    @Override // org.bson.BsonWriter
    public void writeRegularExpression(BsonRegularExpression regularExpression) {
        Assertions.notNull("value", regularExpression);
        checkPreconditions("writeRegularExpression", State.VALUE);
        doWriteRegularExpression(regularExpression);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeString(String name, String value) {
        Assertions.notNull("name", name);
        Assertions.notNull("value", value);
        writeName(name);
        writeString(value);
    }

    @Override // org.bson.BsonWriter
    public void writeString(String value) {
        Assertions.notNull("value", value);
        checkPreconditions("writeString", State.VALUE);
        doWriteString(value);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeSymbol(String name, String value) {
        Assertions.notNull("name", name);
        Assertions.notNull("value", value);
        writeName(name);
        writeSymbol(value);
    }

    @Override // org.bson.BsonWriter
    public void writeSymbol(String value) {
        Assertions.notNull("value", value);
        checkPreconditions("writeSymbol", State.VALUE);
        doWriteSymbol(value);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeTimestamp(String name, BsonTimestamp value) {
        Assertions.notNull("name", name);
        Assertions.notNull("value", value);
        writeName(name);
        writeTimestamp(value);
    }

    @Override // org.bson.BsonWriter
    public void writeTimestamp(BsonTimestamp value) {
        Assertions.notNull("value", value);
        checkPreconditions("writeTimestamp", State.VALUE);
        doWriteTimestamp(value);
        setState(getNextState());
    }

    @Override // org.bson.BsonWriter
    public void writeUndefined(String name) {
        writeName(name);
        writeUndefined();
    }

    @Override // org.bson.BsonWriter
    public void writeUndefined() {
        checkPreconditions("writeUndefined", State.VALUE);
        doWriteUndefined();
        setState(getNextState());
    }

    protected State getNextState() {
        if (getContext().getContextType() == BsonContextType.ARRAY) {
            return State.VALUE;
        }
        return State.NAME;
    }

    protected boolean checkState(State[] validStates) {
        for (State cur : validStates) {
            if (cur == getState()) {
                return true;
            }
        }
        return false;
    }

    protected void checkPreconditions(String methodName, State... validStates) {
        if (isClosed()) {
            throw new IllegalStateException("BsonWriter is closed");
        }
        if (!checkState(validStates)) {
            throwInvalidState(methodName, validStates);
        }
    }

    protected void throwInvalidContextType(String methodName, BsonContextType actualContextType, BsonContextType... validContextTypes) {
        String validContextTypesString = StringUtils.join(" or ", Arrays.asList(validContextTypes));
        throw new BsonInvalidOperationException(String.format("%s can only be called when ContextType is %s, not when ContextType is %s.", methodName, validContextTypesString, actualContextType));
    }

    protected void throwInvalidState(String methodName, State... validStates) {
        if ((this.state == State.INITIAL || this.state == State.SCOPE_DOCUMENT || this.state == State.DONE) && !methodName.startsWith("end") && !methodName.equals("writeName")) {
            String typeName = methodName.substring(5);
            if (typeName.startsWith("start")) {
                typeName = typeName.substring(5);
            }
            String article = "A";
            if (Arrays.asList('A', 'E', 'I', 'O', 'U').contains(Character.valueOf(typeName.charAt(0)))) {
                article = "An";
            }
            throw new BsonInvalidOperationException(String.format("%s %s value cannot be written to the root level of a BSON document.", article, typeName));
        }
        String validStatesString = StringUtils.join(" or ", Arrays.asList(validStates));
        throw new BsonInvalidOperationException(String.format("%s can only be called when State is %s, not when State is %s", methodName, validStatesString, this.state));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
    }

    @Override // org.bson.BsonWriter
    public void pipe(BsonReader reader) {
        Assertions.notNull("reader", reader);
        pipeDocument(reader, null);
    }

    public void pipe(BsonReader reader, List<BsonElement> extraElements) {
        Assertions.notNull("reader", reader);
        Assertions.notNull("extraElements", extraElements);
        pipeDocument(reader, extraElements);
    }

    protected void pipeExtraElements(List<BsonElement> extraElements) {
        Assertions.notNull("extraElements", extraElements);
        for (BsonElement cur : extraElements) {
            writeName(cur.getName());
            pipeValue(cur.getValue());
        }
    }

    protected boolean abortPipe() {
        return false;
    }

    private void pipeDocument(BsonReader reader, List<BsonElement> extraElements) {
        reader.readStartDocument();
        writeStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            writeName(reader.readName());
            pipeValue(reader);
            if (abortPipe()) {
                return;
            }
        }
        reader.readEndDocument();
        if (extraElements != null) {
            pipeExtraElements(extraElements);
        }
        writeEndDocument();
    }

    private void pipeJavascriptWithScope(BsonReader reader) {
        writeJavaScriptWithScope(reader.readJavaScriptWithScope());
        pipeDocument(reader, null);
    }

    private void pipeValue(BsonReader reader) {
        switch (reader.getCurrentBsonType()) {
            case DOCUMENT:
                pipeDocument(reader, null);
                return;
            case ARRAY:
                pipeArray(reader);
                return;
            case DOUBLE:
                writeDouble(reader.readDouble());
                return;
            case STRING:
                writeString(reader.readString());
                return;
            case BINARY:
                writeBinaryData(reader.readBinaryData());
                return;
            case UNDEFINED:
                reader.readUndefined();
                writeUndefined();
                return;
            case OBJECT_ID:
                writeObjectId(reader.readObjectId());
                return;
            case BOOLEAN:
                writeBoolean(reader.readBoolean());
                return;
            case DATE_TIME:
                writeDateTime(reader.readDateTime());
                return;
            case NULL:
                reader.readNull();
                writeNull();
                return;
            case REGULAR_EXPRESSION:
                writeRegularExpression(reader.readRegularExpression());
                return;
            case JAVASCRIPT:
                writeJavaScript(reader.readJavaScript());
                return;
            case SYMBOL:
                writeSymbol(reader.readSymbol());
                return;
            case JAVASCRIPT_WITH_SCOPE:
                pipeJavascriptWithScope(reader);
                return;
            case INT32:
                writeInt32(reader.readInt32());
                return;
            case TIMESTAMP:
                writeTimestamp(reader.readTimestamp());
                return;
            case INT64:
                writeInt64(reader.readInt64());
                return;
            case DECIMAL128:
                writeDecimal128(reader.readDecimal128());
                return;
            case MIN_KEY:
                reader.readMinKey();
                writeMinKey();
                return;
            case DB_POINTER:
                writeDBPointer(reader.readDBPointer());
                return;
            case MAX_KEY:
                reader.readMaxKey();
                writeMaxKey();
                return;
            default:
                throw new IllegalArgumentException("unhandled BSON type: " + reader.getCurrentBsonType());
        }
    }

    private void pipeDocument(BsonDocument value) {
        writeStartDocument();
        for (Map.Entry<String, BsonValue> cur : value.entrySet()) {
            writeName(cur.getKey());
            pipeValue(cur.getValue());
        }
        writeEndDocument();
    }

    private void pipeArray(BsonReader reader) {
        reader.readStartArray();
        writeStartArray();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            pipeValue(reader);
            if (abortPipe()) {
                return;
            }
        }
        reader.readEndArray();
        writeEndArray();
    }

    private void pipeArray(BsonArray array) {
        writeStartArray();
        Iterator<BsonValue> it = array.iterator();
        while (it.hasNext()) {
            BsonValue cur = it.next();
            pipeValue(cur);
        }
        writeEndArray();
    }

    private void pipeJavascriptWithScope(BsonJavaScriptWithScope javaScriptWithScope) {
        writeJavaScriptWithScope(javaScriptWithScope.getCode());
        pipeDocument(javaScriptWithScope.getScope());
    }

    private void pipeValue(BsonValue value) {
        switch (value.getBsonType()) {
            case DOCUMENT:
                pipeDocument(value.asDocument());
                return;
            case ARRAY:
                pipeArray(value.asArray());
                return;
            case DOUBLE:
                writeDouble(value.asDouble().getValue());
                return;
            case STRING:
                writeString(value.asString().getValue());
                return;
            case BINARY:
                writeBinaryData(value.asBinary());
                return;
            case UNDEFINED:
                writeUndefined();
                return;
            case OBJECT_ID:
                writeObjectId(value.asObjectId().getValue());
                return;
            case BOOLEAN:
                writeBoolean(value.asBoolean().getValue());
                return;
            case DATE_TIME:
                writeDateTime(value.asDateTime().getValue());
                return;
            case NULL:
                writeNull();
                return;
            case REGULAR_EXPRESSION:
                writeRegularExpression(value.asRegularExpression());
                return;
            case JAVASCRIPT:
                writeJavaScript(value.asJavaScript().getCode());
                return;
            case SYMBOL:
                writeSymbol(value.asSymbol().getSymbol());
                return;
            case JAVASCRIPT_WITH_SCOPE:
                pipeJavascriptWithScope(value.asJavaScriptWithScope());
                return;
            case INT32:
                writeInt32(value.asInt32().getValue());
                return;
            case TIMESTAMP:
                writeTimestamp(value.asTimestamp());
                return;
            case INT64:
                writeInt64(value.asInt64().getValue());
                return;
            case DECIMAL128:
                writeDecimal128(value.asDecimal128().getValue());
                return;
            case MIN_KEY:
                writeMinKey();
                return;
            case DB_POINTER:
                writeDBPointer(value.asDBPointer());
                return;
            case MAX_KEY:
                writeMaxKey();
                return;
            default:
                throw new IllegalArgumentException("unhandled BSON type: " + value.getBsonType());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/AbstractBsonWriter$Context.class */
    public class Context {
        private final Context parentContext;
        private final BsonContextType contextType;
        private String name;

        public Context(Context from) {
            this.parentContext = from.parentContext;
            this.contextType = from.contextType;
        }

        public Context(Context parentContext, BsonContextType contextType) {
            this.parentContext = parentContext;
            this.contextType = contextType;
        }

        public Context getParentContext() {
            return this.parentContext;
        }

        public BsonContextType getContextType() {
            return this.contextType;
        }

        public Context copy() {
            return AbstractBsonWriter.this.new Context(this);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/AbstractBsonWriter$Mark.class */
    protected class Mark {
        private final Context markedContext;
        private final State markedState;
        private final String currentName;
        private final int serializationDepth;

        protected Mark() {
            this.markedContext = AbstractBsonWriter.this.context.copy();
            this.markedState = AbstractBsonWriter.this.state;
            this.currentName = AbstractBsonWriter.this.context.name;
            this.serializationDepth = AbstractBsonWriter.this.serializationDepth;
        }

        protected void reset() {
            AbstractBsonWriter.this.setContext(this.markedContext);
            AbstractBsonWriter.this.setState(this.markedState);
            AbstractBsonWriter.this.context.name = this.currentName;
            AbstractBsonWriter.this.serializationDepth = this.serializationDepth;
        }
    }
}
