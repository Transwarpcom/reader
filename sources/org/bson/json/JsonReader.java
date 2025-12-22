package org.bson.json;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import me.ag2s.epublib.domain.Identifier;
import org.bson.AbstractBsonReader;
import org.bson.BSONException;
import org.bson.BsonBinary;
import org.bson.BsonBinarySubType;
import org.bson.BsonContextType;
import org.bson.BsonDbPointer;
import org.bson.BsonInvalidOperationException;
import org.bson.BsonReaderMark;
import org.bson.BsonRegularExpression;
import org.bson.BsonTimestamp;
import org.bson.BsonType;
import org.bson.BsonUndefined;
import org.bson.internal.Base64;
import org.bson.types.Decimal128;
import org.bson.types.MaxKey;
import org.bson.types.MinKey;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonReader.class */
public class JsonReader extends AbstractBsonReader {
    private final JsonScanner scanner;
    private JsonToken pushedToken;
    private Object currentValue;
    private Mark mark;

    public JsonReader(String json) {
        this.scanner = new JsonScanner(json);
        setContext(new Context(null, BsonContextType.TOP_LEVEL));
    }

    @Override // org.bson.AbstractBsonReader
    protected BsonBinary doReadBinaryData() {
        return (BsonBinary) this.currentValue;
    }

    @Override // org.bson.AbstractBsonReader
    protected byte doPeekBinarySubType() {
        return doReadBinaryData().getType();
    }

    @Override // org.bson.AbstractBsonReader
    protected int doPeekBinarySize() {
        return doReadBinaryData().getData().length;
    }

    @Override // org.bson.AbstractBsonReader
    protected boolean doReadBoolean() {
        return ((Boolean) this.currentValue).booleanValue();
    }

    @Override // org.bson.AbstractBsonReader, org.bson.BsonReader
    public BsonType readBsonType() {
        if (isClosed()) {
            throw new IllegalStateException("This instance has been closed");
        }
        if (getState() == AbstractBsonReader.State.INITIAL || getState() == AbstractBsonReader.State.DONE || getState() == AbstractBsonReader.State.SCOPE_DOCUMENT) {
            setState(AbstractBsonReader.State.TYPE);
        }
        if (getState() != AbstractBsonReader.State.TYPE) {
            throwInvalidState("readBSONType", AbstractBsonReader.State.TYPE);
        }
        if (getContext().getContextType() == BsonContextType.DOCUMENT) {
            JsonToken nameToken = popToken();
            switch (nameToken.getType()) {
                case STRING:
                case UNQUOTED_STRING:
                    setCurrentName((String) nameToken.getValue(String.class));
                    JsonToken colonToken = popToken();
                    if (colonToken.getType() != JsonTokenType.COLON) {
                        throw new JsonParseException("JSON reader was expecting ':' but found '%s'.", colonToken.getValue());
                    }
                    break;
                case END_OBJECT:
                    setState(AbstractBsonReader.State.END_OF_DOCUMENT);
                    return BsonType.END_OF_DOCUMENT;
                default:
                    throw new JsonParseException("JSON reader was expecting a name but found '%s'.", nameToken.getValue());
            }
        }
        JsonToken token = popToken();
        if (getContext().getContextType() == BsonContextType.ARRAY && token.getType() == JsonTokenType.END_ARRAY) {
            setState(AbstractBsonReader.State.END_OF_ARRAY);
            return BsonType.END_OF_DOCUMENT;
        }
        boolean noValueFound = false;
        switch (token.getType()) {
            case STRING:
                setCurrentBsonType(BsonType.STRING);
                this.currentValue = token.getValue();
                break;
            case UNQUOTED_STRING:
                String value = (String) token.getValue(String.class);
                if ("false".equals(value) || "true".equals(value)) {
                    setCurrentBsonType(BsonType.BOOLEAN);
                    this.currentValue = Boolean.valueOf(Boolean.parseBoolean(value));
                    break;
                } else if ("Infinity".equals(value)) {
                    setCurrentBsonType(BsonType.DOUBLE);
                    this.currentValue = Double.valueOf(Double.POSITIVE_INFINITY);
                    break;
                } else if ("NaN".equals(value)) {
                    setCurrentBsonType(BsonType.DOUBLE);
                    this.currentValue = Double.valueOf(Double.NaN);
                    break;
                } else if ("null".equals(value)) {
                    setCurrentBsonType(BsonType.NULL);
                    break;
                } else if ("undefined".equals(value)) {
                    setCurrentBsonType(BsonType.UNDEFINED);
                    break;
                } else if ("MinKey".equals(value)) {
                    visitEmptyConstructor();
                    setCurrentBsonType(BsonType.MIN_KEY);
                    this.currentValue = new MinKey();
                    break;
                } else if ("MaxKey".equals(value)) {
                    visitEmptyConstructor();
                    setCurrentBsonType(BsonType.MAX_KEY);
                    this.currentValue = new MaxKey();
                    break;
                } else if ("BinData".equals(value)) {
                    setCurrentBsonType(BsonType.BINARY);
                    this.currentValue = visitBinDataConstructor();
                    break;
                } else if ("Date".equals(value)) {
                    this.currentValue = visitDateTimeConstructorWithOutNew();
                    setCurrentBsonType(BsonType.STRING);
                    break;
                } else if ("HexData".equals(value)) {
                    setCurrentBsonType(BsonType.BINARY);
                    this.currentValue = visitHexDataConstructor();
                    break;
                } else if ("ISODate".equals(value)) {
                    setCurrentBsonType(BsonType.DATE_TIME);
                    this.currentValue = Long.valueOf(visitISODateTimeConstructor());
                    break;
                } else if ("NumberInt".equals(value)) {
                    setCurrentBsonType(BsonType.INT32);
                    this.currentValue = Integer.valueOf(visitNumberIntConstructor());
                    break;
                } else if ("NumberLong".equals(value)) {
                    setCurrentBsonType(BsonType.INT64);
                    this.currentValue = Long.valueOf(visitNumberLongConstructor());
                    break;
                } else if ("NumberDecimal".equals(value)) {
                    setCurrentBsonType(BsonType.DECIMAL128);
                    this.currentValue = visitNumberDecimalConstructor();
                    break;
                } else if ("ObjectId".equals(value)) {
                    setCurrentBsonType(BsonType.OBJECT_ID);
                    this.currentValue = visitObjectIdConstructor();
                    break;
                } else if (RtspHeaders.Names.TIMESTAMP.equals(value)) {
                    setCurrentBsonType(BsonType.TIMESTAMP);
                    this.currentValue = visitTimestampConstructor();
                    break;
                } else if ("RegExp".equals(value)) {
                    setCurrentBsonType(BsonType.REGULAR_EXPRESSION);
                    this.currentValue = visitRegularExpressionConstructor();
                    break;
                } else if ("DBPointer".equals(value)) {
                    setCurrentBsonType(BsonType.DB_POINTER);
                    this.currentValue = visitDBPointerConstructor();
                    break;
                } else if (Identifier.Scheme.UUID.equals(value) || "GUID".equals(value) || "CSUUID".equals(value) || "CSGUID".equals(value) || "JUUID".equals(value) || "JGUID".equals(value) || "PYUUID".equals(value) || "PYGUID".equals(value)) {
                    setCurrentBsonType(BsonType.BINARY);
                    this.currentValue = visitUUIDConstructor(value);
                    break;
                } else if ("new".equals(value)) {
                    visitNew();
                    break;
                } else {
                    noValueFound = true;
                    break;
                }
                break;
            case END_OBJECT:
            default:
                noValueFound = true;
                break;
            case BEGIN_ARRAY:
                setCurrentBsonType(BsonType.ARRAY);
                break;
            case BEGIN_OBJECT:
                visitExtendedJSON();
                break;
            case DOUBLE:
                setCurrentBsonType(BsonType.DOUBLE);
                this.currentValue = token.getValue();
                break;
            case END_OF_FILE:
                setCurrentBsonType(BsonType.END_OF_DOCUMENT);
                break;
            case INT32:
                setCurrentBsonType(BsonType.INT32);
                this.currentValue = token.getValue();
                break;
            case INT64:
                setCurrentBsonType(BsonType.INT64);
                this.currentValue = token.getValue();
                break;
            case REGULAR_EXPRESSION:
                setCurrentBsonType(BsonType.REGULAR_EXPRESSION);
                this.currentValue = token.getValue();
                break;
        }
        if (noValueFound) {
            throw new JsonParseException("JSON reader was expecting a value but found '%s'.", token.getValue());
        }
        if (getContext().getContextType() == BsonContextType.ARRAY || getContext().getContextType() == BsonContextType.DOCUMENT) {
            JsonToken commaToken = popToken();
            if (commaToken.getType() != JsonTokenType.COMMA) {
                pushToken(commaToken);
            }
        }
        switch (getContext().getContextType()) {
            case DOCUMENT:
            case SCOPE_DOCUMENT:
            default:
                setState(AbstractBsonReader.State.NAME);
                break;
            case ARRAY:
            case JAVASCRIPT_WITH_SCOPE:
            case TOP_LEVEL:
                setState(AbstractBsonReader.State.VALUE);
                break;
        }
        return getCurrentBsonType();
    }

    @Override // org.bson.AbstractBsonReader
    public Decimal128 doReadDecimal128() {
        return (Decimal128) this.currentValue;
    }

    @Override // org.bson.AbstractBsonReader
    protected long doReadDateTime() {
        return ((Long) this.currentValue).longValue();
    }

    @Override // org.bson.AbstractBsonReader
    protected double doReadDouble() {
        return ((Double) this.currentValue).doubleValue();
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadEndArray() {
        setContext(getContext().getParentContext());
        if (getContext().getContextType() == BsonContextType.ARRAY || getContext().getContextType() == BsonContextType.DOCUMENT) {
            JsonToken commaToken = popToken();
            if (commaToken.getType() != JsonTokenType.COMMA) {
                pushToken(commaToken);
            }
        }
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadEndDocument() {
        setContext(getContext().getParentContext());
        if (getContext() != null && getContext().getContextType() == BsonContextType.SCOPE_DOCUMENT) {
            setContext(getContext().getParentContext());
            verifyToken(JsonTokenType.END_OBJECT);
        }
        if (getContext() == null) {
            throw new JsonParseException("Unexpected end of document.");
        }
        if (getContext().getContextType() == BsonContextType.ARRAY || getContext().getContextType() == BsonContextType.DOCUMENT) {
            JsonToken commaToken = popToken();
            if (commaToken.getType() != JsonTokenType.COMMA) {
                pushToken(commaToken);
            }
        }
    }

    @Override // org.bson.AbstractBsonReader
    protected int doReadInt32() {
        return ((Integer) this.currentValue).intValue();
    }

    @Override // org.bson.AbstractBsonReader
    protected long doReadInt64() {
        return ((Long) this.currentValue).longValue();
    }

    @Override // org.bson.AbstractBsonReader
    protected String doReadJavaScript() {
        return (String) this.currentValue;
    }

    @Override // org.bson.AbstractBsonReader
    protected String doReadJavaScriptWithScope() {
        return (String) this.currentValue;
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
        return (ObjectId) this.currentValue;
    }

    @Override // org.bson.AbstractBsonReader
    protected BsonRegularExpression doReadRegularExpression() {
        return (BsonRegularExpression) this.currentValue;
    }

    @Override // org.bson.AbstractBsonReader
    protected BsonDbPointer doReadDBPointer() {
        return (BsonDbPointer) this.currentValue;
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadStartArray() {
        setContext(new Context(getContext(), BsonContextType.ARRAY));
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadStartDocument() {
        setContext(new Context(getContext(), BsonContextType.DOCUMENT));
    }

    @Override // org.bson.AbstractBsonReader
    protected String doReadString() {
        return (String) this.currentValue;
    }

    @Override // org.bson.AbstractBsonReader
    protected String doReadSymbol() {
        return (String) this.currentValue;
    }

    @Override // org.bson.AbstractBsonReader
    protected BsonTimestamp doReadTimestamp() {
        return (BsonTimestamp) this.currentValue;
    }

    @Override // org.bson.AbstractBsonReader
    protected void doReadUndefined() {
    }

    @Override // org.bson.AbstractBsonReader
    protected void doSkipName() {
    }

    @Override // org.bson.AbstractBsonReader
    protected void doSkipValue() {
        switch (getCurrentBsonType()) {
            case ARRAY:
                readStartArray();
                while (readBsonType() != BsonType.END_OF_DOCUMENT) {
                    skipValue();
                }
                readEndArray();
                break;
            case BINARY:
                readBinaryData();
                break;
            case BOOLEAN:
                readBoolean();
                break;
            case DATE_TIME:
                readDateTime();
                break;
            case DOCUMENT:
                readStartDocument();
                while (readBsonType() != BsonType.END_OF_DOCUMENT) {
                    skipName();
                    skipValue();
                }
                readEndDocument();
                break;
            case DOUBLE:
                readDouble();
                break;
            case INT32:
                readInt32();
                break;
            case INT64:
                readInt64();
                break;
            case DECIMAL128:
                readDecimal128();
                break;
            case JAVASCRIPT:
                readJavaScript();
                break;
            case JAVASCRIPT_WITH_SCOPE:
                readJavaScriptWithScope();
                readStartDocument();
                while (readBsonType() != BsonType.END_OF_DOCUMENT) {
                    skipName();
                    skipValue();
                }
                readEndDocument();
                break;
            case MAX_KEY:
                readMaxKey();
                break;
            case MIN_KEY:
                readMinKey();
                break;
            case NULL:
                readNull();
                break;
            case OBJECT_ID:
                readObjectId();
                break;
            case REGULAR_EXPRESSION:
                readRegularExpression();
                break;
            case STRING:
                readString();
                break;
            case SYMBOL:
                readSymbol();
                break;
            case TIMESTAMP:
                readTimestamp();
                break;
            case UNDEFINED:
                readUndefined();
                break;
        }
    }

    private JsonToken popToken() {
        if (this.pushedToken != null) {
            JsonToken token = this.pushedToken;
            this.pushedToken = null;
            return token;
        }
        return this.scanner.nextToken();
    }

    private void pushToken(JsonToken token) {
        if (this.pushedToken == null) {
            this.pushedToken = token;
            return;
        }
        throw new BsonInvalidOperationException("There is already a pending token.");
    }

    private void verifyToken(JsonTokenType expectedType) {
        JsonToken token = popToken();
        if (expectedType != token.getType()) {
            throw new JsonParseException("JSON reader expected token type '%s' but found '%s'.", expectedType, token.getValue());
        }
    }

    private void verifyToken(JsonTokenType expectedType, Object expectedValue) {
        JsonToken token = popToken();
        if (expectedType != token.getType()) {
            throw new JsonParseException("JSON reader expected token type '%s' but found '%s'.", expectedType, token.getValue());
        }
        if (!expectedValue.equals(token.getValue())) {
            throw new JsonParseException("JSON reader expected '%s' but found '%s'.", expectedValue, token.getValue());
        }
    }

    private void verifyString(String expected) {
        if (expected == null) {
            throw new IllegalArgumentException("Can't be null");
        }
        JsonToken token = popToken();
        JsonTokenType type = token.getType();
        if ((type != JsonTokenType.STRING && type != JsonTokenType.UNQUOTED_STRING) || !expected.equals(token.getValue())) {
            throw new JsonParseException("JSON reader expected '%s' but found '%s'.", expected, token.getValue());
        }
    }

    private void visitNew() {
        JsonToken typeToken = popToken();
        if (typeToken.getType() != JsonTokenType.UNQUOTED_STRING) {
            throw new JsonParseException("JSON reader expected a type name but found '%s'.", typeToken.getValue());
        }
        String value = (String) typeToken.getValue(String.class);
        if ("MinKey".equals(value)) {
            visitEmptyConstructor();
            setCurrentBsonType(BsonType.MIN_KEY);
            this.currentValue = new MinKey();
            return;
        }
        if ("MaxKey".equals(value)) {
            visitEmptyConstructor();
            setCurrentBsonType(BsonType.MAX_KEY);
            this.currentValue = new MaxKey();
            return;
        }
        if ("BinData".equals(value)) {
            this.currentValue = visitBinDataConstructor();
            setCurrentBsonType(BsonType.BINARY);
            return;
        }
        if ("Date".equals(value)) {
            this.currentValue = Long.valueOf(visitDateTimeConstructor());
            setCurrentBsonType(BsonType.DATE_TIME);
            return;
        }
        if ("HexData".equals(value)) {
            this.currentValue = visitHexDataConstructor();
            setCurrentBsonType(BsonType.BINARY);
            return;
        }
        if ("ISODate".equals(value)) {
            this.currentValue = Long.valueOf(visitISODateTimeConstructor());
            setCurrentBsonType(BsonType.DATE_TIME);
            return;
        }
        if ("NumberInt".equals(value)) {
            this.currentValue = Integer.valueOf(visitNumberIntConstructor());
            setCurrentBsonType(BsonType.INT32);
            return;
        }
        if ("NumberLong".equals(value)) {
            this.currentValue = Long.valueOf(visitNumberLongConstructor());
            setCurrentBsonType(BsonType.INT64);
            return;
        }
        if ("NumberDecimal".equals(value)) {
            this.currentValue = visitNumberDecimalConstructor();
            setCurrentBsonType(BsonType.DECIMAL128);
            return;
        }
        if ("ObjectId".equals(value)) {
            this.currentValue = visitObjectIdConstructor();
            setCurrentBsonType(BsonType.OBJECT_ID);
            return;
        }
        if ("RegExp".equals(value)) {
            this.currentValue = visitRegularExpressionConstructor();
            setCurrentBsonType(BsonType.REGULAR_EXPRESSION);
            return;
        }
        if ("DBPointer".equals(value)) {
            this.currentValue = visitDBPointerConstructor();
            setCurrentBsonType(BsonType.DB_POINTER);
            return;
        }
        if (Identifier.Scheme.UUID.equals(value) || "GUID".equals(value) || "CSUUID".equals(value) || "CSGUID".equals(value) || "JUUID".equals(value) || "JGUID".equals(value) || "PYUUID".equals(value) || "PYGUID".equals(value)) {
            this.currentValue = visitUUIDConstructor(value);
            setCurrentBsonType(BsonType.BINARY);
            return;
        }
        throw new JsonParseException("JSON reader expected a type name but found '%s'.", value);
    }

    private void visitExtendedJSON() {
        JsonToken nameToken = popToken();
        String value = (String) nameToken.getValue(String.class);
        JsonTokenType type = nameToken.getType();
        if (type == JsonTokenType.STRING || type == JsonTokenType.UNQUOTED_STRING) {
            if ("$binary".equals(value) || "$type".equals(value)) {
                this.currentValue = visitBinDataExtendedJson(value);
                if (this.currentValue != null) {
                    setCurrentBsonType(BsonType.BINARY);
                    return;
                }
            } else if ("$regex".equals(value) || "$options".equals(value)) {
                this.currentValue = visitRegularExpressionExtendedJson(value);
                if (this.currentValue != null) {
                    setCurrentBsonType(BsonType.REGULAR_EXPRESSION);
                    return;
                }
            } else {
                if ("$code".equals(value)) {
                    visitJavaScriptExtendedJson();
                    return;
                }
                if ("$date".equals(value)) {
                    this.currentValue = Long.valueOf(visitDateTimeExtendedJson());
                    setCurrentBsonType(BsonType.DATE_TIME);
                    return;
                }
                if ("$maxKey".equals(value)) {
                    this.currentValue = visitMaxKeyExtendedJson();
                    setCurrentBsonType(BsonType.MAX_KEY);
                    return;
                }
                if ("$minKey".equals(value)) {
                    this.currentValue = visitMinKeyExtendedJson();
                    setCurrentBsonType(BsonType.MIN_KEY);
                    return;
                }
                if ("$oid".equals(value)) {
                    this.currentValue = visitObjectIdExtendedJson();
                    setCurrentBsonType(BsonType.OBJECT_ID);
                    return;
                }
                if ("$regularExpression".equals(value)) {
                    this.currentValue = visitNewRegularExpressionExtendedJson();
                    setCurrentBsonType(BsonType.REGULAR_EXPRESSION);
                    return;
                }
                if ("$symbol".equals(value)) {
                    this.currentValue = visitSymbolExtendedJson();
                    setCurrentBsonType(BsonType.SYMBOL);
                    return;
                }
                if ("$timestamp".equals(value)) {
                    this.currentValue = visitTimestampExtendedJson();
                    setCurrentBsonType(BsonType.TIMESTAMP);
                    return;
                }
                if ("$undefined".equals(value)) {
                    this.currentValue = visitUndefinedExtendedJson();
                    setCurrentBsonType(BsonType.UNDEFINED);
                    return;
                }
                if ("$numberLong".equals(value)) {
                    this.currentValue = visitNumberLongExtendedJson();
                    setCurrentBsonType(BsonType.INT64);
                    return;
                }
                if ("$numberInt".equals(value)) {
                    this.currentValue = visitNumberIntExtendedJson();
                    setCurrentBsonType(BsonType.INT32);
                    return;
                }
                if ("$numberDouble".equals(value)) {
                    this.currentValue = visitNumberDoubleExtendedJson();
                    setCurrentBsonType(BsonType.DOUBLE);
                    return;
                } else if ("$numberDecimal".equals(value)) {
                    this.currentValue = visitNumberDecimalExtendedJson();
                    setCurrentBsonType(BsonType.DECIMAL128);
                    return;
                } else if ("$dbPointer".equals(value)) {
                    this.currentValue = visitDbPointerExtendedJson();
                    setCurrentBsonType(BsonType.DB_POINTER);
                    return;
                }
            }
        }
        pushToken(nameToken);
        setCurrentBsonType(BsonType.DOCUMENT);
    }

    private void visitEmptyConstructor() {
        JsonToken nextToken = popToken();
        if (nextToken.getType() == JsonTokenType.LEFT_PAREN) {
            verifyToken(JsonTokenType.RIGHT_PAREN);
        } else {
            pushToken(nextToken);
        }
    }

    private BsonBinary visitBinDataConstructor() {
        verifyToken(JsonTokenType.LEFT_PAREN);
        JsonToken subTypeToken = popToken();
        if (subTypeToken.getType() != JsonTokenType.INT32) {
            throw new JsonParseException("JSON reader expected a binary subtype but found '%s'.", subTypeToken.getValue());
        }
        verifyToken(JsonTokenType.COMMA);
        JsonToken bytesToken = popToken();
        if (bytesToken.getType() != JsonTokenType.UNQUOTED_STRING && bytesToken.getType() != JsonTokenType.STRING) {
            throw new JsonParseException("JSON reader expected a string but found '%s'.", bytesToken.getValue());
        }
        verifyToken(JsonTokenType.RIGHT_PAREN);
        byte[] bytes = Base64.decode((String) bytesToken.getValue(String.class));
        return new BsonBinary(((Integer) subTypeToken.getValue(Integer.class)).byteValue(), bytes);
    }

    private BsonBinary visitUUIDConstructor(String uuidConstructorName) {
        verifyToken(JsonTokenType.LEFT_PAREN);
        String hexString = readStringFromExtendedJson().replaceAll("\\{", "").replaceAll("}", "").replaceAll("-", "");
        verifyToken(JsonTokenType.RIGHT_PAREN);
        byte[] bytes = decodeHex(hexString);
        BsonBinarySubType subType = BsonBinarySubType.UUID_STANDARD;
        if (!Identifier.Scheme.UUID.equals(uuidConstructorName) || !"GUID".equals(uuidConstructorName)) {
            subType = BsonBinarySubType.UUID_LEGACY;
        }
        return new BsonBinary(subType, bytes);
    }

    private BsonRegularExpression visitRegularExpressionConstructor() {
        verifyToken(JsonTokenType.LEFT_PAREN);
        String pattern = readStringFromExtendedJson();
        String options = "";
        JsonToken commaToken = popToken();
        if (commaToken.getType() == JsonTokenType.COMMA) {
            options = readStringFromExtendedJson();
        } else {
            pushToken(commaToken);
        }
        verifyToken(JsonTokenType.RIGHT_PAREN);
        return new BsonRegularExpression(pattern, options);
    }

    private ObjectId visitObjectIdConstructor() {
        verifyToken(JsonTokenType.LEFT_PAREN);
        ObjectId objectId = new ObjectId(readStringFromExtendedJson());
        verifyToken(JsonTokenType.RIGHT_PAREN);
        return objectId;
    }

    private BsonTimestamp visitTimestampConstructor() {
        verifyToken(JsonTokenType.LEFT_PAREN);
        JsonToken timeToken = popToken();
        if (timeToken.getType() != JsonTokenType.INT32) {
            throw new JsonParseException("JSON reader expected an integer but found '%s'.", timeToken.getValue());
        }
        int time = ((Integer) timeToken.getValue(Integer.class)).intValue();
        verifyToken(JsonTokenType.COMMA);
        JsonToken incrementToken = popToken();
        if (incrementToken.getType() != JsonTokenType.INT32) {
            throw new JsonParseException("JSON reader expected an integer but found '%s'.", timeToken.getValue());
        }
        int increment = ((Integer) incrementToken.getValue(Integer.class)).intValue();
        verifyToken(JsonTokenType.RIGHT_PAREN);
        return new BsonTimestamp(time, increment);
    }

    private BsonDbPointer visitDBPointerConstructor() {
        verifyToken(JsonTokenType.LEFT_PAREN);
        String namespace = readStringFromExtendedJson();
        verifyToken(JsonTokenType.COMMA);
        ObjectId id = new ObjectId(readStringFromExtendedJson());
        verifyToken(JsonTokenType.RIGHT_PAREN);
        return new BsonDbPointer(namespace, id);
    }

    private int visitNumberIntConstructor() throws NumberFormatException {
        int value;
        verifyToken(JsonTokenType.LEFT_PAREN);
        JsonToken valueToken = popToken();
        if (valueToken.getType() == JsonTokenType.INT32) {
            value = ((Integer) valueToken.getValue(Integer.class)).intValue();
        } else if (valueToken.getType() == JsonTokenType.STRING) {
            value = Integer.parseInt((String) valueToken.getValue(String.class));
        } else {
            throw new JsonParseException("JSON reader expected an integer or a string but found '%s'.", valueToken.getValue());
        }
        verifyToken(JsonTokenType.RIGHT_PAREN);
        return value;
    }

    private long visitNumberLongConstructor() throws NumberFormatException {
        long value;
        verifyToken(JsonTokenType.LEFT_PAREN);
        JsonToken valueToken = popToken();
        if (valueToken.getType() == JsonTokenType.INT32 || valueToken.getType() == JsonTokenType.INT64) {
            value = ((Long) valueToken.getValue(Long.class)).longValue();
        } else if (valueToken.getType() == JsonTokenType.STRING) {
            value = Long.parseLong((String) valueToken.getValue(String.class));
        } else {
            throw new JsonParseException("JSON reader expected an integer or a string but found '%s'.", valueToken.getValue());
        }
        verifyToken(JsonTokenType.RIGHT_PAREN);
        return value;
    }

    private Decimal128 visitNumberDecimalConstructor() {
        Decimal128 value;
        verifyToken(JsonTokenType.LEFT_PAREN);
        JsonToken valueToken = popToken();
        if (valueToken.getType() == JsonTokenType.INT32 || valueToken.getType() == JsonTokenType.INT64 || valueToken.getType() == JsonTokenType.DOUBLE) {
            value = (Decimal128) valueToken.getValue(Decimal128.class);
        } else if (valueToken.getType() == JsonTokenType.STRING) {
            value = Decimal128.parse((String) valueToken.getValue(String.class));
        } else {
            throw new JsonParseException("JSON reader expected a number or a string but found '%s'.", valueToken.getValue());
        }
        verifyToken(JsonTokenType.RIGHT_PAREN);
        return value;
    }

    private long visitISODateTimeConstructor() {
        verifyToken(JsonTokenType.LEFT_PAREN);
        JsonToken token = popToken();
        if (token.getType() == JsonTokenType.RIGHT_PAREN) {
            return new Date().getTime();
        }
        if (token.getType() != JsonTokenType.STRING) {
            throw new JsonParseException("JSON reader expected a string but found '%s'.", token.getValue());
        }
        verifyToken(JsonTokenType.RIGHT_PAREN);
        String[] patterns = {"yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd'T'HH:mm:ss.SSSz"};
        SimpleDateFormat format = new SimpleDateFormat(patterns[0], Locale.ENGLISH);
        ParsePosition pos = new ParsePosition(0);
        String s = (String) token.getValue(String.class);
        if (s.endsWith("Z")) {
            s = s.substring(0, s.length() - 1) + "GMT-00:00";
        }
        for (String pattern : patterns) {
            format.applyPattern(pattern);
            format.setLenient(true);
            pos.setIndex(0);
            Date date = format.parse(s, pos);
            if (date != null && pos.getIndex() == s.length()) {
                return date.getTime();
            }
        }
        throw new JsonParseException("Invalid date format.");
    }

    private BsonBinary visitHexDataConstructor() {
        verifyToken(JsonTokenType.LEFT_PAREN);
        JsonToken subTypeToken = popToken();
        if (subTypeToken.getType() != JsonTokenType.INT32) {
            throw new JsonParseException("JSON reader expected a binary subtype but found '%s'.", subTypeToken.getValue());
        }
        verifyToken(JsonTokenType.COMMA);
        String hex = readStringFromExtendedJson();
        verifyToken(JsonTokenType.RIGHT_PAREN);
        if ((hex.length() & 1) != 0) {
            hex = "0" + hex;
        }
        for (BsonBinarySubType subType : BsonBinarySubType.values()) {
            if (subType.getValue() == ((Integer) subTypeToken.getValue(Integer.class)).intValue()) {
                return new BsonBinary(subType, decodeHex(hex));
            }
        }
        return new BsonBinary(decodeHex(hex));
    }

    private long visitDateTimeConstructor() {
        DateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        verifyToken(JsonTokenType.LEFT_PAREN);
        JsonToken token = popToken();
        if (token.getType() == JsonTokenType.RIGHT_PAREN) {
            return new Date().getTime();
        }
        if (token.getType() == JsonTokenType.STRING) {
            verifyToken(JsonTokenType.RIGHT_PAREN);
            String s = (String) token.getValue(String.class);
            ParsePosition pos = new ParsePosition(0);
            Date dateTime = format.parse(s, pos);
            if (dateTime != null && pos.getIndex() == s.length()) {
                return dateTime.getTime();
            }
            throw new JsonParseException("JSON reader expected a date in 'EEE MMM dd yyyy HH:mm:ss z' format but found '%s'.", s);
        }
        if (token.getType() == JsonTokenType.INT32 || token.getType() == JsonTokenType.INT64) {
            long[] values = new long[7];
            int pos2 = 0;
            while (true) {
                if (pos2 < values.length) {
                    int i = pos2;
                    pos2++;
                    values[i] = ((Long) token.getValue(Long.class)).longValue();
                }
                JsonToken token2 = popToken();
                if (token2.getType() != JsonTokenType.RIGHT_PAREN) {
                    if (token2.getType() != JsonTokenType.COMMA) {
                        throw new JsonParseException("JSON reader expected a ',' or a ')' but found '%s'.", token2.getValue());
                    }
                    token = popToken();
                    if (token.getType() != JsonTokenType.INT32 && token.getType() != JsonTokenType.INT64) {
                        throw new JsonParseException("JSON reader expected an integer but found '%s'.", token.getValue());
                    }
                } else {
                    if (pos2 == 1) {
                        return values[0];
                    }
                    if (pos2 < 3 || pos2 > 7) {
                        throw new JsonParseException("JSON reader expected 1 or 3-7 integers but found %d.", Integer.valueOf(pos2));
                    }
                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                    calendar.set(1, (int) values[0]);
                    calendar.set(2, (int) values[1]);
                    calendar.set(5, (int) values[2]);
                    calendar.set(11, (int) values[3]);
                    calendar.set(12, (int) values[4]);
                    calendar.set(13, (int) values[5]);
                    calendar.set(14, (int) values[6]);
                    return calendar.getTimeInMillis();
                }
            }
        } else {
            throw new JsonParseException("JSON reader expected an integer or a string but found '%s'.", token.getValue());
        }
    }

    private String visitDateTimeConstructorWithOutNew() {
        verifyToken(JsonTokenType.LEFT_PAREN);
        JsonToken token = popToken();
        if (token.getType() != JsonTokenType.RIGHT_PAREN) {
            while (token.getType() != JsonTokenType.END_OF_FILE) {
                token = popToken();
                if (token.getType() == JsonTokenType.RIGHT_PAREN) {
                    break;
                }
            }
            if (token.getType() != JsonTokenType.RIGHT_PAREN) {
                throw new JsonParseException("JSON reader expected a ')' but found '%s'.", token.getValue());
            }
        }
        DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        return df.format(new Date());
    }

    private BsonBinary visitBinDataExtendedJson(String firstKey) {
        byte type;
        byte[] data;
        Mark mark = new Mark();
        verifyToken(JsonTokenType.COLON);
        if (firstKey.equals("$binary")) {
            JsonToken nextToken = popToken();
            if (nextToken.getType() == JsonTokenType.BEGIN_OBJECT) {
                JsonToken nameToken = popToken();
                String firstNestedKey = (String) nameToken.getValue(String.class);
                if (firstNestedKey.equals(HttpHeaders.Values.BASE64)) {
                    verifyToken(JsonTokenType.COLON);
                    data = Base64.decode(readStringFromExtendedJson());
                    verifyToken(JsonTokenType.COMMA);
                    verifyString("subType");
                    verifyToken(JsonTokenType.COLON);
                    type = readBinarySubtypeFromExtendedJson();
                } else if (firstNestedKey.equals("subType")) {
                    verifyToken(JsonTokenType.COLON);
                    type = readBinarySubtypeFromExtendedJson();
                    verifyToken(JsonTokenType.COMMA);
                    verifyString(HttpHeaders.Values.BASE64);
                    verifyToken(JsonTokenType.COLON);
                    data = Base64.decode(readStringFromExtendedJson());
                } else {
                    throw new JsonParseException("Unexpected key for $binary: " + firstNestedKey);
                }
                verifyToken(JsonTokenType.END_OBJECT);
                verifyToken(JsonTokenType.END_OBJECT);
                return new BsonBinary(type, data);
            }
            mark.reset();
            return visitLegacyBinaryExtendedJson(firstKey);
        }
        mark.reset();
        return visitLegacyBinaryExtendedJson(firstKey);
    }

    private BsonBinary visitLegacyBinaryExtendedJson(String firstKey) {
        byte type;
        byte[] data;
        Mark mark = new Mark();
        try {
            verifyToken(JsonTokenType.COLON);
            if (firstKey.equals("$binary")) {
                data = Base64.decode(readStringFromExtendedJson());
                verifyToken(JsonTokenType.COMMA);
                verifyString("$type");
                verifyToken(JsonTokenType.COLON);
                type = readBinarySubtypeFromExtendedJson();
            } else {
                type = readBinarySubtypeFromExtendedJson();
                verifyToken(JsonTokenType.COMMA);
                verifyString("$binary");
                verifyToken(JsonTokenType.COLON);
                data = Base64.decode(readStringFromExtendedJson());
            }
            verifyToken(JsonTokenType.END_OBJECT);
            return new BsonBinary(type, data);
        } catch (NumberFormatException e) {
            mark.reset();
            return null;
        } catch (JsonParseException e2) {
            mark.reset();
            return null;
        }
    }

    private byte readBinarySubtypeFromExtendedJson() {
        JsonToken subTypeToken = popToken();
        if (subTypeToken.getType() != JsonTokenType.STRING && subTypeToken.getType() != JsonTokenType.INT32) {
            throw new JsonParseException("JSON reader expected a string or number but found '%s'.", subTypeToken.getValue());
        }
        if (subTypeToken.getType() == JsonTokenType.STRING) {
            return (byte) Integer.parseInt((String) subTypeToken.getValue(String.class), 16);
        }
        return ((Integer) subTypeToken.getValue(Integer.class)).byteValue();
    }

    private long visitDateTimeExtendedJson() {
        long value;
        verifyToken(JsonTokenType.COLON);
        JsonToken valueToken = popToken();
        if (valueToken.getType() == JsonTokenType.BEGIN_OBJECT) {
            JsonToken nameToken = popToken();
            String name = (String) nameToken.getValue(String.class);
            if (!name.equals("$numberLong")) {
                throw new JsonParseException(String.format("JSON reader expected $numberLong within $date, but found %s", name));
            }
            value = visitNumberLongExtendedJson().longValue();
            verifyToken(JsonTokenType.END_OBJECT);
        } else {
            if (valueToken.getType() == JsonTokenType.INT32 || valueToken.getType() == JsonTokenType.INT64) {
                value = ((Long) valueToken.getValue(Long.class)).longValue();
            } else if (valueToken.getType() == JsonTokenType.STRING) {
                String dateTimeString = (String) valueToken.getValue(String.class);
                try {
                    value = DateTimeFormatter.parse(dateTimeString);
                } catch (IllegalArgumentException e) {
                    throw new JsonParseException("Failed to parse string as a date", e);
                }
            } else {
                throw new JsonParseException("JSON reader expected an integer or string but found '%s'.", valueToken.getValue());
            }
            verifyToken(JsonTokenType.END_OBJECT);
        }
        return value;
    }

    private MaxKey visitMaxKeyExtendedJson() {
        verifyToken(JsonTokenType.COLON);
        verifyToken(JsonTokenType.INT32, 1);
        verifyToken(JsonTokenType.END_OBJECT);
        return new MaxKey();
    }

    private MinKey visitMinKeyExtendedJson() {
        verifyToken(JsonTokenType.COLON);
        verifyToken(JsonTokenType.INT32, 1);
        verifyToken(JsonTokenType.END_OBJECT);
        return new MinKey();
    }

    private ObjectId visitObjectIdExtendedJson() {
        verifyToken(JsonTokenType.COLON);
        ObjectId objectId = new ObjectId(readStringFromExtendedJson());
        verifyToken(JsonTokenType.END_OBJECT);
        return objectId;
    }

    private BsonRegularExpression visitNewRegularExpressionExtendedJson() {
        String options;
        String pattern;
        verifyToken(JsonTokenType.COLON);
        verifyToken(JsonTokenType.BEGIN_OBJECT);
        String firstKey = readStringFromExtendedJson();
        if (firstKey.equals("pattern")) {
            verifyToken(JsonTokenType.COLON);
            pattern = readStringFromExtendedJson();
            verifyToken(JsonTokenType.COMMA);
            verifyString("options");
            verifyToken(JsonTokenType.COLON);
            options = readStringFromExtendedJson();
        } else if (firstKey.equals("options")) {
            verifyToken(JsonTokenType.COLON);
            options = readStringFromExtendedJson();
            verifyToken(JsonTokenType.COMMA);
            verifyString("pattern");
            verifyToken(JsonTokenType.COLON);
            pattern = readStringFromExtendedJson();
        } else {
            throw new JsonParseException("Expected 't' and 'i' fields in $timestamp document but found " + firstKey);
        }
        verifyToken(JsonTokenType.END_OBJECT);
        verifyToken(JsonTokenType.END_OBJECT);
        return new BsonRegularExpression(pattern, options);
    }

    private BsonRegularExpression visitRegularExpressionExtendedJson(String firstKey) {
        String options;
        String pattern;
        Mark extendedJsonMark = new Mark();
        try {
            verifyToken(JsonTokenType.COLON);
            if (firstKey.equals("$regex")) {
                pattern = readStringFromExtendedJson();
                verifyToken(JsonTokenType.COMMA);
                verifyString("$options");
                verifyToken(JsonTokenType.COLON);
                options = readStringFromExtendedJson();
            } else {
                options = readStringFromExtendedJson();
                verifyToken(JsonTokenType.COMMA);
                verifyString("$regex");
                verifyToken(JsonTokenType.COLON);
                pattern = readStringFromExtendedJson();
            }
            verifyToken(JsonTokenType.END_OBJECT);
            return new BsonRegularExpression(pattern, options);
        } catch (JsonParseException e) {
            extendedJsonMark.reset();
            return null;
        }
    }

    private String readStringFromExtendedJson() {
        JsonToken patternToken = popToken();
        if (patternToken.getType() != JsonTokenType.STRING) {
            throw new JsonParseException("JSON reader expected a string but found '%s'.", patternToken.getValue());
        }
        return (String) patternToken.getValue(String.class);
    }

    private String visitSymbolExtendedJson() {
        verifyToken(JsonTokenType.COLON);
        String symbol = readStringFromExtendedJson();
        verifyToken(JsonTokenType.END_OBJECT);
        return symbol;
    }

    private BsonTimestamp visitTimestampExtendedJson() {
        int increment;
        int time;
        verifyToken(JsonTokenType.COLON);
        verifyToken(JsonTokenType.BEGIN_OBJECT);
        String firstKey = readStringFromExtendedJson();
        if (firstKey.equals("t")) {
            verifyToken(JsonTokenType.COLON);
            time = readIntFromExtendedJson();
            verifyToken(JsonTokenType.COMMA);
            verifyString("i");
            verifyToken(JsonTokenType.COLON);
            increment = readIntFromExtendedJson();
        } else if (firstKey.equals("i")) {
            verifyToken(JsonTokenType.COLON);
            increment = readIntFromExtendedJson();
            verifyToken(JsonTokenType.COMMA);
            verifyString("t");
            verifyToken(JsonTokenType.COLON);
            time = readIntFromExtendedJson();
        } else {
            throw new JsonParseException("Expected 't' and 'i' fields in $timestamp document but found " + firstKey);
        }
        verifyToken(JsonTokenType.END_OBJECT);
        verifyToken(JsonTokenType.END_OBJECT);
        return new BsonTimestamp(time, increment);
    }

    private int readIntFromExtendedJson() {
        int value;
        JsonToken nextToken = popToken();
        if (nextToken.getType() == JsonTokenType.INT32) {
            value = ((Integer) nextToken.getValue(Integer.class)).intValue();
        } else if (nextToken.getType() == JsonTokenType.INT64) {
            value = ((Long) nextToken.getValue(Long.class)).intValue();
        } else {
            throw new JsonParseException("JSON reader expected an integer but found '%s'.", nextToken.getValue());
        }
        return value;
    }

    private void visitJavaScriptExtendedJson() {
        verifyToken(JsonTokenType.COLON);
        String code = readStringFromExtendedJson();
        JsonToken nextToken = popToken();
        switch (nextToken.getType()) {
            case END_OBJECT:
                this.currentValue = code;
                setCurrentBsonType(BsonType.JAVASCRIPT);
                return;
            case COMMA:
                verifyString("$scope");
                verifyToken(JsonTokenType.COLON);
                setState(AbstractBsonReader.State.VALUE);
                this.currentValue = code;
                setCurrentBsonType(BsonType.JAVASCRIPT_WITH_SCOPE);
                setContext(new Context(getContext(), BsonContextType.SCOPE_DOCUMENT));
                return;
            default:
                throw new JsonParseException("JSON reader expected ',' or '}' but found '%s'.", nextToken);
        }
    }

    private BsonUndefined visitUndefinedExtendedJson() {
        verifyToken(JsonTokenType.COLON);
        JsonToken valueToken = popToken();
        if (!((String) valueToken.getValue(String.class)).equals("true")) {
            throw new JsonParseException("JSON reader requires $undefined to have the value of true but found '%s'.", valueToken.getValue());
        }
        verifyToken(JsonTokenType.END_OBJECT);
        return new BsonUndefined();
    }

    private Long visitNumberLongExtendedJson() throws NumberFormatException {
        verifyToken(JsonTokenType.COLON);
        String longAsString = readStringFromExtendedJson();
        try {
            Long value = Long.valueOf(longAsString);
            verifyToken(JsonTokenType.END_OBJECT);
            return value;
        } catch (NumberFormatException e) {
            throw new JsonParseException(String.format("Exception converting value '%s' to type %s", longAsString, Long.class.getName()), e);
        }
    }

    private Integer visitNumberIntExtendedJson() throws NumberFormatException {
        verifyToken(JsonTokenType.COLON);
        String intAsString = readStringFromExtendedJson();
        try {
            Integer value = Integer.valueOf(intAsString);
            verifyToken(JsonTokenType.END_OBJECT);
            return value;
        } catch (NumberFormatException e) {
            throw new JsonParseException(String.format("Exception converting value '%s' to type %s", intAsString, Integer.class.getName()), e);
        }
    }

    private Double visitNumberDoubleExtendedJson() throws NumberFormatException {
        verifyToken(JsonTokenType.COLON);
        String doubleAsString = readStringFromExtendedJson();
        try {
            Double value = Double.valueOf(doubleAsString);
            verifyToken(JsonTokenType.END_OBJECT);
            return value;
        } catch (NumberFormatException e) {
            throw new JsonParseException(String.format("Exception converting value '%s' to type %s", doubleAsString, Double.class.getName()), e);
        }
    }

    private Decimal128 visitNumberDecimalExtendedJson() {
        verifyToken(JsonTokenType.COLON);
        String decimal128AsString = readStringFromExtendedJson();
        try {
            Decimal128 value = Decimal128.parse(decimal128AsString);
            verifyToken(JsonTokenType.END_OBJECT);
            return value;
        } catch (NumberFormatException e) {
            throw new JsonParseException(String.format("Exception converting value '%s' to type %s", decimal128AsString, Decimal128.class.getName()), e);
        }
    }

    private BsonDbPointer visitDbPointerExtendedJson() {
        ObjectId oid;
        String ref;
        verifyToken(JsonTokenType.COLON);
        verifyToken(JsonTokenType.BEGIN_OBJECT);
        String firstKey = readStringFromExtendedJson();
        if (firstKey.equals("$ref")) {
            verifyToken(JsonTokenType.COLON);
            ref = readStringFromExtendedJson();
            verifyToken(JsonTokenType.COMMA);
            verifyString("$id");
            oid = readDbPointerIdFromExtendedJson();
            verifyToken(JsonTokenType.END_OBJECT);
        } else if (firstKey.equals("$id")) {
            oid = readDbPointerIdFromExtendedJson();
            verifyToken(JsonTokenType.COMMA);
            verifyString("$ref");
            verifyToken(JsonTokenType.COLON);
            ref = readStringFromExtendedJson();
        } else {
            throw new JsonParseException("Expected $ref and $id fields in $dbPointer document but found " + firstKey);
        }
        verifyToken(JsonTokenType.END_OBJECT);
        return new BsonDbPointer(ref, oid);
    }

    private ObjectId readDbPointerIdFromExtendedJson() {
        verifyToken(JsonTokenType.COLON);
        verifyToken(JsonTokenType.BEGIN_OBJECT);
        verifyToken(JsonTokenType.STRING, "$oid");
        ObjectId oid = visitObjectIdExtendedJson();
        return oid;
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

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonReader$Mark.class */
    protected class Mark extends AbstractBsonReader.Mark {
        private final JsonToken pushedToken;
        private final Object currentValue;
        private final int position;

        protected Mark() {
            super();
            this.pushedToken = JsonReader.this.pushedToken;
            this.currentValue = JsonReader.this.currentValue;
            this.position = JsonReader.this.scanner.getBufferPosition();
        }

        @Override // org.bson.AbstractBsonReader.Mark, org.bson.BsonReaderMark
        public void reset() {
            super.reset();
            JsonReader.this.pushedToken = this.pushedToken;
            JsonReader.this.currentValue = this.currentValue;
            JsonReader.this.scanner.setBufferPosition(this.position);
            JsonReader.this.setContext(JsonReader.this.new Context(getParentContext(), getContextType()));
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonReader$Context.class */
    protected class Context extends AbstractBsonReader.Context {
        protected Context(AbstractBsonReader.Context parentContext, BsonContextType contextType) {
            super(parentContext, contextType);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.bson.AbstractBsonReader.Context
        public Context getParentContext() {
            return (Context) super.getParentContext();
        }

        @Override // org.bson.AbstractBsonReader.Context
        protected BsonContextType getContextType() {
            return super.getContextType();
        }
    }

    private static byte[] decodeHex(String hex) {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("A hex string must contain an even number of characters: " + hex);
        }
        byte[] out = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            int high = Character.digit(hex.charAt(i), 16);
            int low = Character.digit(hex.charAt(i + 1), 16);
            if (high == -1 || low == -1) {
                throw new IllegalArgumentException("A hex string can only contain the characters 0-9, A-F, a-f: " + hex);
            }
            out[i / 2] = (byte) ((high * 16) + low);
        }
        return out;
    }
}
