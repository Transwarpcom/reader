package org.bson;

import java.util.List;
import java.util.Stack;
import org.bson.AbstractBsonReader;
import org.bson.AbstractBsonWriter;
import org.bson.assertions.Assertions;
import org.bson.io.BsonInput;
import org.bson.io.BsonOutput;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonBinaryWriter.class */
public class BsonBinaryWriter extends AbstractBsonWriter {
    private final BsonBinaryWriterSettings binaryWriterSettings;
    private final BsonOutput bsonOutput;
    private final Stack<Integer> maxDocumentSizeStack;
    private Mark mark;

    public BsonBinaryWriter(BsonOutput bsonOutput, FieldNameValidator validator) {
        this(new BsonWriterSettings(), new BsonBinaryWriterSettings(), bsonOutput, validator);
    }

    public BsonBinaryWriter(BsonOutput bsonOutput) {
        this(new BsonWriterSettings(), new BsonBinaryWriterSettings(), bsonOutput);
    }

    public BsonBinaryWriter(BsonWriterSettings settings, BsonBinaryWriterSettings binaryWriterSettings, BsonOutput bsonOutput) {
        this(settings, binaryWriterSettings, bsonOutput, new NoOpFieldNameValidator());
    }

    public BsonBinaryWriter(BsonWriterSettings settings, BsonBinaryWriterSettings binaryWriterSettings, BsonOutput bsonOutput, FieldNameValidator validator) {
        super(settings, validator);
        this.maxDocumentSizeStack = new Stack<>();
        this.binaryWriterSettings = binaryWriterSettings;
        this.bsonOutput = bsonOutput;
        this.maxDocumentSizeStack.push(Integer.valueOf(binaryWriterSettings.getMaxDocumentSize()));
    }

    @Override // org.bson.AbstractBsonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
    }

    public BsonOutput getBsonOutput() {
        return this.bsonOutput;
    }

    public BsonBinaryWriterSettings getBinaryWriterSettings() {
        return this.binaryWriterSettings;
    }

    @Override // org.bson.BsonWriter
    public void flush() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bson.AbstractBsonWriter
    public Context getContext() {
        return (Context) super.getContext();
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteStartDocument() {
        if (getState() == AbstractBsonWriter.State.VALUE) {
            this.bsonOutput.writeByte(BsonType.DOCUMENT.getValue());
            writeCurrentName();
        }
        setContext(new Context(getContext(), BsonContextType.DOCUMENT, this.bsonOutput.getPosition()));
        this.bsonOutput.writeInt32(0);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteEndDocument() {
        this.bsonOutput.writeByte(0);
        backpatchSize();
        setContext(getContext().getParentContext());
        if (getContext() != null && getContext().getContextType() == BsonContextType.JAVASCRIPT_WITH_SCOPE) {
            backpatchSize();
            setContext(getContext().getParentContext());
        }
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteStartArray() {
        this.bsonOutput.writeByte(BsonType.ARRAY.getValue());
        writeCurrentName();
        setContext(new Context(getContext(), BsonContextType.ARRAY, this.bsonOutput.getPosition()));
        this.bsonOutput.writeInt32(0);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteEndArray() {
        this.bsonOutput.writeByte(0);
        backpatchSize();
        setContext(getContext().getParentContext());
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteBinaryData(BsonBinary value) {
        this.bsonOutput.writeByte(BsonType.BINARY.getValue());
        writeCurrentName();
        int totalLen = value.getData().length;
        if (value.getType() == BsonBinarySubType.OLD_BINARY.getValue()) {
            totalLen += 4;
        }
        this.bsonOutput.writeInt32(totalLen);
        this.bsonOutput.writeByte(value.getType());
        if (value.getType() == BsonBinarySubType.OLD_BINARY.getValue()) {
            this.bsonOutput.writeInt32(totalLen - 4);
        }
        this.bsonOutput.writeBytes(value.getData());
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteBoolean(boolean value) {
        this.bsonOutput.writeByte(BsonType.BOOLEAN.getValue());
        writeCurrentName();
        this.bsonOutput.writeByte(value ? 1 : 0);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDateTime(long value) {
        this.bsonOutput.writeByte(BsonType.DATE_TIME.getValue());
        writeCurrentName();
        this.bsonOutput.writeInt64(value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDBPointer(BsonDbPointer value) {
        this.bsonOutput.writeByte(BsonType.DB_POINTER.getValue());
        writeCurrentName();
        this.bsonOutput.writeString(value.getNamespace());
        this.bsonOutput.writeBytes(value.getId().toByteArray());
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDouble(double value) {
        this.bsonOutput.writeByte(BsonType.DOUBLE.getValue());
        writeCurrentName();
        this.bsonOutput.writeDouble(value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteInt32(int value) {
        this.bsonOutput.writeByte(BsonType.INT32.getValue());
        writeCurrentName();
        this.bsonOutput.writeInt32(value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteInt64(long value) {
        this.bsonOutput.writeByte(BsonType.INT64.getValue());
        writeCurrentName();
        this.bsonOutput.writeInt64(value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteDecimal128(Decimal128 value) {
        this.bsonOutput.writeByte(BsonType.DECIMAL128.getValue());
        writeCurrentName();
        this.bsonOutput.writeInt64(value.getLow());
        this.bsonOutput.writeInt64(value.getHigh());
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteJavaScript(String value) {
        this.bsonOutput.writeByte(BsonType.JAVASCRIPT.getValue());
        writeCurrentName();
        this.bsonOutput.writeString(value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteJavaScriptWithScope(String value) {
        this.bsonOutput.writeByte(BsonType.JAVASCRIPT_WITH_SCOPE.getValue());
        writeCurrentName();
        setContext(new Context(getContext(), BsonContextType.JAVASCRIPT_WITH_SCOPE, this.bsonOutput.getPosition()));
        this.bsonOutput.writeInt32(0);
        this.bsonOutput.writeString(value);
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteMaxKey() {
        this.bsonOutput.writeByte(BsonType.MAX_KEY.getValue());
        writeCurrentName();
    }

    @Override // org.bson.AbstractBsonWriter
    protected void doWriteMinKey() {
        this.bsonOutput.writeByte(BsonType.MIN_KEY.getValue());
        writeCurrentName();
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteNull() {
        this.bsonOutput.writeByte(BsonType.NULL.getValue());
        writeCurrentName();
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteObjectId(ObjectId value) {
        this.bsonOutput.writeByte(BsonType.OBJECT_ID.getValue());
        writeCurrentName();
        this.bsonOutput.writeBytes(value.toByteArray());
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteRegularExpression(BsonRegularExpression value) {
        this.bsonOutput.writeByte(BsonType.REGULAR_EXPRESSION.getValue());
        writeCurrentName();
        this.bsonOutput.writeCString(value.getPattern());
        this.bsonOutput.writeCString(value.getOptions());
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteString(String value) {
        this.bsonOutput.writeByte(BsonType.STRING.getValue());
        writeCurrentName();
        this.bsonOutput.writeString(value);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteSymbol(String value) {
        this.bsonOutput.writeByte(BsonType.SYMBOL.getValue());
        writeCurrentName();
        this.bsonOutput.writeString(value);
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteTimestamp(BsonTimestamp value) {
        this.bsonOutput.writeByte(BsonType.TIMESTAMP.getValue());
        writeCurrentName();
        this.bsonOutput.writeInt64(value.getValue());
    }

    @Override // org.bson.AbstractBsonWriter
    public void doWriteUndefined() {
        this.bsonOutput.writeByte(BsonType.UNDEFINED.getValue());
        writeCurrentName();
    }

    @Override // org.bson.AbstractBsonWriter, org.bson.BsonWriter
    public void pipe(BsonReader reader) {
        Assertions.notNull("reader", reader);
        pipeDocument(reader, null);
    }

    @Override // org.bson.AbstractBsonWriter
    public void pipe(BsonReader reader, List<BsonElement> extraElements) {
        Assertions.notNull("reader", reader);
        Assertions.notNull("extraElements", extraElements);
        pipeDocument(reader, extraElements);
    }

    private void pipeDocument(BsonReader reader, List<BsonElement> extraElements) {
        if (!(reader instanceof BsonBinaryReader)) {
            if (extraElements != null) {
                super.pipe(reader, extraElements);
                return;
            } else {
                super.pipe(reader);
                return;
            }
        }
        BsonBinaryReader binaryReader = (BsonBinaryReader) reader;
        if (getState() == AbstractBsonWriter.State.VALUE) {
            this.bsonOutput.writeByte(BsonType.DOCUMENT.getValue());
            writeCurrentName();
        }
        BsonInput bsonInput = binaryReader.getBsonInput();
        int size = bsonInput.readInt32();
        if (size < 5) {
            throw new BsonSerializationException("Document size must be at least 5");
        }
        int pipedDocumentStartPosition = this.bsonOutput.getPosition();
        this.bsonOutput.writeInt32(size);
        byte[] bytes = new byte[size - 4];
        bsonInput.readBytes(bytes);
        this.bsonOutput.writeBytes(bytes);
        binaryReader.setState(AbstractBsonReader.State.TYPE);
        if (extraElements != null) {
            this.bsonOutput.truncateToPosition(this.bsonOutput.getPosition() - 1);
            setContext(new Context(getContext(), BsonContextType.DOCUMENT, pipedDocumentStartPosition));
            setState(AbstractBsonWriter.State.NAME);
            pipeExtraElements(extraElements);
            this.bsonOutput.writeByte(0);
            this.bsonOutput.writeInt32(pipedDocumentStartPosition, this.bsonOutput.getPosition() - pipedDocumentStartPosition);
            setContext(getContext().getParentContext());
        }
        if (getContext() == null) {
            setState(AbstractBsonWriter.State.DONE);
        } else {
            if (getContext().getContextType() == BsonContextType.JAVASCRIPT_WITH_SCOPE) {
                backpatchSize();
                setContext(getContext().getParentContext());
            }
            setState(getNextState());
        }
        validateSize(this.bsonOutput.getPosition() - pipedDocumentStartPosition);
    }

    public void pushMaxDocumentSize(int maxDocumentSize) {
        this.maxDocumentSizeStack.push(Integer.valueOf(maxDocumentSize));
    }

    public void popMaxDocumentSize() {
        this.maxDocumentSizeStack.pop();
    }

    public void mark() {
        this.mark = new Mark();
    }

    public void reset() {
        if (this.mark == null) {
            throw new IllegalStateException("Can not reset without first marking");
        }
        this.mark.reset();
        this.mark = null;
    }

    private void writeCurrentName() {
        if (getContext().getContextType() == BsonContextType.ARRAY) {
            this.bsonOutput.writeCString(Integer.toString(Context.access$008(getContext())));
        } else {
            this.bsonOutput.writeCString(getName());
        }
    }

    private void backpatchSize() {
        int size = this.bsonOutput.getPosition() - getContext().startPosition;
        validateSize(size);
        this.bsonOutput.writeInt32(this.bsonOutput.getPosition() - size, size);
    }

    private void validateSize(int size) {
        if (size > this.maxDocumentSizeStack.peek().intValue()) {
            throw new BsonMaximumSizeExceededException(String.format("Document size of %d is larger than maximum of %d.", Integer.valueOf(size), this.maxDocumentSizeStack.peek()));
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonBinaryWriter$Context.class */
    protected class Context extends AbstractBsonWriter.Context {
        private final int startPosition;
        private int index;

        static /* synthetic */ int access$008(Context x0) {
            int i = x0.index;
            x0.index = i + 1;
            return i;
        }

        public Context(Context parentContext, BsonContextType contextType, int startPosition) {
            super(parentContext, contextType);
            this.startPosition = startPosition;
        }

        public Context(Context from) {
            super(from);
            this.startPosition = from.startPosition;
            this.index = from.index;
        }

        @Override // org.bson.AbstractBsonWriter.Context
        public Context getParentContext() {
            return (Context) super.getParentContext();
        }

        @Override // org.bson.AbstractBsonWriter.Context
        public Context copy() {
            return BsonBinaryWriter.this.new Context(this);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonBinaryWriter$Mark.class */
    protected class Mark extends AbstractBsonWriter.Mark {
        private final int position;

        protected Mark() {
            super();
            this.position = BsonBinaryWriter.this.bsonOutput.getPosition();
        }

        @Override // org.bson.AbstractBsonWriter.Mark
        protected void reset() {
            super.reset();
            BsonBinaryWriter.this.bsonOutput.truncateToPosition(BsonBinaryWriter.this.mark.position);
        }
    }
}
