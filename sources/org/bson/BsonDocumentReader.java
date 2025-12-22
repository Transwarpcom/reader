package org.bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bson.AbstractBsonReader;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDocumentReader.class */
public class BsonDocumentReader extends AbstractBsonReader {
    private BsonValue currentValue;
    private Mark mark;

    public BsonDocumentReader(BsonDocument document) {
        setContext(new Context((Context) null, BsonContextType.TOP_LEVEL, document));
        this.currentValue = document;
    }

    @Override // org.bson.AbstractBsonReader
    protected BsonBinary doReadBinaryData() {
        return this.currentValue.asBinary();
    }

    @Override // org.bson.AbstractBsonReader
    protected byte doPeekBinarySubType() {
        return this.currentValue.asBinary().getType();
    }

    @Override // org.bson.AbstractBsonReader
    protected int doPeekBinarySize() {
        return this.currentValue.asBinary().getData().length;
    }

    @Override // org.bson.AbstractBsonReader
    protected boolean doReadBoolean() {
        return this.currentValue.asBoolean().getValue();
    }

    @Override // org.bson.AbstractBsonReader
    protected long doReadDateTime() {
        return this.currentValue.asDateTime().getValue();
    }

    @Override // org.bson.AbstractBsonReader
    protected double doReadDouble() {
        return this.currentValue.asDouble().getValue();
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadEndArray() {
        setContext(getContext().getParentContext());
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadEndDocument() {
        setContext(getContext().getParentContext());
        switch (getContext().getContextType()) {
            case ARRAY:
            case DOCUMENT:
                setState(AbstractBsonReader.State.TYPE);
                return;
            case TOP_LEVEL:
                setState(AbstractBsonReader.State.DONE);
                return;
            default:
                throw new BSONException("Unexpected ContextType.");
        }
    }

    @Override // org.bson.AbstractBsonReader
    protected int doReadInt32() {
        return this.currentValue.asInt32().getValue();
    }

    @Override // org.bson.AbstractBsonReader
    protected long doReadInt64() {
        return this.currentValue.asInt64().getValue();
    }

    @Override // org.bson.AbstractBsonReader
    public Decimal128 doReadDecimal128() {
        return this.currentValue.asDecimal128().getValue();
    }

    @Override // org.bson.AbstractBsonReader
    protected String doReadJavaScript() {
        return this.currentValue.asJavaScript().getCode();
    }

    @Override // org.bson.AbstractBsonReader
    protected String doReadJavaScriptWithScope() {
        return this.currentValue.asJavaScriptWithScope().getCode();
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadMaxKey() {
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadMinKey() {
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadNull() {
    }

    @Override // org.bson.AbstractBsonReader
    protected ObjectId doReadObjectId() {
        return this.currentValue.asObjectId().getValue();
    }

    @Override // org.bson.AbstractBsonReader
    protected BsonRegularExpression doReadRegularExpression() {
        return this.currentValue.asRegularExpression();
    }

    @Override // org.bson.AbstractBsonReader
    protected BsonDbPointer doReadDBPointer() {
        return this.currentValue.asDBPointer();
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadStartArray() {
        BsonArray array = this.currentValue.asArray();
        setContext(new Context(getContext(), BsonContextType.ARRAY, array));
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadStartDocument() {
        BsonDocument document;
        if (this.currentValue.getBsonType() == BsonType.JAVASCRIPT_WITH_SCOPE) {
            document = this.currentValue.asJavaScriptWithScope().getScope();
        } else {
            document = this.currentValue.asDocument();
        }
        setContext(new Context(getContext(), BsonContextType.DOCUMENT, document));
    }

    @Override // org.bson.AbstractBsonReader
    protected String doReadString() {
        return this.currentValue.asString().getValue();
    }

    @Override // org.bson.AbstractBsonReader
    protected String doReadSymbol() {
        return this.currentValue.asSymbol().getSymbol();
    }

    @Override // org.bson.AbstractBsonReader
    protected BsonTimestamp doReadTimestamp() {
        return this.currentValue.asTimestamp();
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadUndefined() {
    }

    @Override // org.bson.AbstractBsonReader
    protected void doSkipName() {
    }

    @Override // org.bson.AbstractBsonReader
    protected void doSkipValue() {
    }

    @Override // org.bson.AbstractBsonReader, org.bson.BsonReader
    public BsonType readBsonType() {
        if (getState() == AbstractBsonReader.State.INITIAL || getState() == AbstractBsonReader.State.SCOPE_DOCUMENT) {
            setCurrentBsonType(BsonType.DOCUMENT);
            setState(AbstractBsonReader.State.VALUE);
            return getCurrentBsonType();
        }
        if (getState() != AbstractBsonReader.State.TYPE) {
            throwInvalidState("ReadBSONType", AbstractBsonReader.State.TYPE);
        }
        switch (getContext().getContextType()) {
            case ARRAY:
                this.currentValue = getContext().getNextValue();
                if (this.currentValue == null) {
                    setState(AbstractBsonReader.State.END_OF_ARRAY);
                    return BsonType.END_OF_DOCUMENT;
                }
                setState(AbstractBsonReader.State.VALUE);
                break;
            case DOCUMENT:
                Map.Entry<String, BsonValue> currentElement = getContext().getNextElement();
                if (currentElement == null) {
                    setState(AbstractBsonReader.State.END_OF_DOCUMENT);
                    return BsonType.END_OF_DOCUMENT;
                }
                setCurrentName(currentElement.getKey());
                this.currentValue = currentElement.getValue();
                setState(AbstractBsonReader.State.NAME);
                break;
            default:
                throw new BSONException("Invalid ContextType.");
        }
        setCurrentBsonType(this.currentValue.getBsonType());
        return getCurrentBsonType();
    }

    @Override // org.bson.BsonReader
    @Deprecated
    public void mark() {
        if (this.mark != null) {
            throw new BSONException("A mark already exists; it needs to be reset before creating a new one");
        }
        this.mark = new Mark();
    }

    @Override // org.bson.BsonReader
    public BsonReaderMark getMark() {
        return new Mark();
    }

    @Override // org.bson.BsonReader
    public void reset() {
        if (this.mark == null) {
            throw new BSONException("trying to reset a mark before creating it");
        }
        this.mark.reset();
        this.mark = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bson.AbstractBsonReader
    public Context getContext() {
        return (Context) super.getContext();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDocumentReader$Mark.class */
    protected class Mark extends AbstractBsonReader.Mark {
        private final BsonValue currentValue;
        private final Context context;

        protected Mark() {
            super();
            this.currentValue = BsonDocumentReader.this.currentValue;
            this.context = BsonDocumentReader.this.getContext();
            this.context.mark();
        }

        @Override // org.bson.AbstractBsonReader.Mark, org.bson.BsonReaderMark
        public void reset() {
            super.reset();
            BsonDocumentReader.this.currentValue = this.currentValue;
            BsonDocumentReader.this.setContext(this.context);
            this.context.reset();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDocumentReader$BsonDocumentMarkableIterator.class */
    private static class BsonDocumentMarkableIterator<T> implements Iterator<T> {
        private Iterator<T> baseIterator;
        private List<T> markIterator = new ArrayList();
        private int curIndex = 0;
        private boolean marking = false;

        protected BsonDocumentMarkableIterator(Iterator<T> baseIterator) {
            this.baseIterator = baseIterator;
        }

        protected void mark() {
            this.marking = true;
        }

        protected void reset() {
            this.curIndex = 0;
            this.marking = false;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.baseIterator.hasNext() || this.curIndex < this.markIterator.size();
        }

        @Override // java.util.Iterator
        public T next() {
            T value;
            if (this.curIndex < this.markIterator.size()) {
                value = this.markIterator.get(this.curIndex);
                if (this.marking) {
                    this.curIndex++;
                } else {
                    this.markIterator.remove(0);
                }
            } else {
                value = this.baseIterator.next();
                if (this.marking) {
                    this.markIterator.add(value);
                    this.curIndex++;
                }
            }
            return value;
        }

        @Override // java.util.Iterator
        public void remove() {
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonDocumentReader$Context.class */
    protected class Context extends AbstractBsonReader.Context {
        private BsonDocumentMarkableIterator<Map.Entry<String, BsonValue>> documentIterator;
        private BsonDocumentMarkableIterator<BsonValue> arrayIterator;

        protected Context(Context parentContext, BsonContextType contextType, BsonArray array) {
            super(parentContext, contextType);
            this.arrayIterator = new BsonDocumentMarkableIterator<>(array.iterator());
        }

        protected Context(Context parentContext, BsonContextType contextType, BsonDocument document) {
            super(parentContext, contextType);
            this.documentIterator = new BsonDocumentMarkableIterator<>(document.entrySet().iterator());
        }

        public Map.Entry<String, BsonValue> getNextElement() {
            if (this.documentIterator.hasNext()) {
                return this.documentIterator.next();
            }
            return null;
        }

        protected void mark() {
            if (this.documentIterator != null) {
                this.documentIterator.mark();
            } else {
                this.arrayIterator.mark();
            }
            if (getParentContext() != null) {
                ((Context) getParentContext()).mark();
            }
        }

        protected void reset() {
            if (this.documentIterator != null) {
                this.documentIterator.reset();
            } else {
                this.arrayIterator.reset();
            }
            if (getParentContext() != null) {
                ((Context) getParentContext()).reset();
            }
        }

        public BsonValue getNextValue() {
            if (this.arrayIterator.hasNext()) {
                return this.arrayIterator.next();
            }
            return null;
        }
    }
}
