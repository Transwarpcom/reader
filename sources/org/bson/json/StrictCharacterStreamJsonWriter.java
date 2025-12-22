package org.bson.json;

import cn.hutool.core.text.StrPool;
import java.io.IOException;
import java.io.Writer;
import org.bson.BSONException;
import org.bson.BsonInvalidOperationException;
import org.bson.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/StrictCharacterStreamJsonWriter.class */
public final class StrictCharacterStreamJsonWriter implements StrictJsonWriter {
    private final Writer writer;
    private final StrictCharacterStreamJsonWriterSettings settings;
    private StrictJsonContext context = new StrictJsonContext(null, JsonContextType.TOP_LEVEL, "");
    private State state = State.INITIAL;
    private int curLength;
    private boolean isTruncated;

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/StrictCharacterStreamJsonWriter$JsonContextType.class */
    private enum JsonContextType {
        TOP_LEVEL,
        DOCUMENT,
        ARRAY
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/StrictCharacterStreamJsonWriter$State.class */
    private enum State {
        INITIAL,
        NAME,
        VALUE,
        DONE
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/StrictCharacterStreamJsonWriter$StrictJsonContext.class */
    private static class StrictJsonContext {
        private final StrictJsonContext parentContext;
        private final JsonContextType contextType;
        private final String indentation;
        private boolean hasElements;

        StrictJsonContext(StrictJsonContext parentContext, JsonContextType contextType, String indentChars) {
            this.parentContext = parentContext;
            this.contextType = contextType;
            this.indentation = parentContext == null ? indentChars : parentContext.indentation + indentChars;
        }
    }

    public StrictCharacterStreamJsonWriter(Writer writer, StrictCharacterStreamJsonWriterSettings settings) {
        this.writer = writer;
        this.settings = settings;
    }

    public int getCurrentLength() {
        return this.curLength;
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeStartObject(String name) throws IOException {
        writeName(name);
        writeStartObject();
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeStartArray(String name) throws IOException {
        writeName(name);
        writeStartArray();
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeBoolean(String name, boolean value) throws IOException {
        Assertions.notNull("name", name);
        Assertions.notNull("value", Boolean.valueOf(value));
        writeName(name);
        writeBoolean(value);
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeNumber(String name, String value) throws IOException {
        Assertions.notNull("name", name);
        Assertions.notNull("value", value);
        writeName(name);
        writeNumber(value);
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeString(String name, String value) throws IOException {
        Assertions.notNull("name", name);
        Assertions.notNull("value", value);
        writeName(name);
        writeString(value);
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeRaw(String name, String value) throws IOException {
        Assertions.notNull("name", name);
        Assertions.notNull("value", value);
        writeName(name);
        writeRaw(value);
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeNull(String name) throws IOException {
        writeName(name);
        writeNull();
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeName(String name) throws IOException {
        Assertions.notNull("name", name);
        checkPreconditions(State.NAME);
        if (this.context.hasElements) {
            write(",");
        }
        if (this.settings.isIndent()) {
            write(this.settings.getNewLineCharacters());
            write(this.context.indentation);
        } else {
            write(" ");
        }
        writeStringHelper(name);
        write(" : ");
        this.state = State.VALUE;
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeBoolean(boolean value) throws IOException {
        checkPreconditions(State.VALUE);
        preWriteValue();
        write(value ? "true" : "false");
        setNextState();
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeNumber(String value) throws IOException {
        Assertions.notNull("value", value);
        checkPreconditions(State.VALUE);
        preWriteValue();
        write(value);
        setNextState();
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeString(String value) throws IOException {
        Assertions.notNull("value", value);
        checkPreconditions(State.VALUE);
        preWriteValue();
        writeStringHelper(value);
        setNextState();
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeRaw(String value) throws IOException {
        Assertions.notNull("value", value);
        checkPreconditions(State.VALUE);
        preWriteValue();
        write(value);
        setNextState();
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeNull() throws IOException {
        checkPreconditions(State.VALUE);
        preWriteValue();
        write("null");
        setNextState();
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeStartObject() throws IOException {
        checkPreconditions(State.INITIAL, State.VALUE);
        preWriteValue();
        write(StrPool.DELIM_START);
        this.context = new StrictJsonContext(this.context, JsonContextType.DOCUMENT, this.settings.getIndentCharacters());
        this.state = State.NAME;
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeStartArray() throws IOException {
        preWriteValue();
        write("[");
        this.context = new StrictJsonContext(this.context, JsonContextType.ARRAY, this.settings.getIndentCharacters());
        this.state = State.VALUE;
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeEndObject() throws IOException {
        checkPreconditions(State.NAME);
        if (this.settings.isIndent() && this.context.hasElements) {
            write(this.settings.getNewLineCharacters());
            write(this.context.parentContext.indentation);
        } else {
            write(" ");
        }
        write("}");
        this.context = this.context.parentContext;
        if (this.context.contextType == JsonContextType.TOP_LEVEL) {
            this.state = State.DONE;
        } else {
            setNextState();
        }
    }

    @Override // org.bson.json.StrictJsonWriter
    public void writeEndArray() throws IOException {
        checkPreconditions(State.VALUE);
        if (this.context.contextType != JsonContextType.ARRAY) {
            throw new BsonInvalidOperationException("Can't end an array if not in an array");
        }
        write("]");
        this.context = this.context.parentContext;
        if (this.context.contextType == JsonContextType.TOP_LEVEL) {
            this.state = State.DONE;
        } else {
            setNextState();
        }
    }

    @Override // org.bson.json.StrictJsonWriter
    public boolean isTruncated() {
        return this.isTruncated;
    }

    void flush() throws IOException {
        try {
            this.writer.flush();
        } catch (IOException e) {
            throwBSONException(e);
        }
    }

    Writer getWriter() {
        return this.writer;
    }

    private void preWriteValue() throws IOException {
        if (this.context.contextType == JsonContextType.ARRAY && this.context.hasElements) {
            write(", ");
        }
        this.context.hasElements = true;
    }

    private void setNextState() {
        if (this.context.contextType == JsonContextType.ARRAY) {
            this.state = State.VALUE;
        } else {
            this.state = State.NAME;
        }
    }

    private void writeStringHelper(String str) throws IOException {
        write('\"');
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '\b':
                    write("\\b");
                    break;
                case '\t':
                    write("\\t");
                    break;
                case '\n':
                    write("\\n");
                    break;
                case '\f':
                    write("\\f");
                    break;
                case '\r':
                    write("\\r");
                    break;
                case '\"':
                    write("\\\"");
                    break;
                case '\\':
                    write("\\\\");
                    break;
                default:
                    switch (Character.getType(c)) {
                        case 1:
                        case 2:
                        case 3:
                        case 5:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                            write(c);
                            break;
                        case 4:
                        case 6:
                        case 7:
                        case 8:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        default:
                            write("\\u");
                            write(Integer.toHexString((c & 61440) >> 12));
                            write(Integer.toHexString((c & 3840) >> 8));
                            write(Integer.toHexString((c & 240) >> 4));
                            write(Integer.toHexString(c & 15));
                            break;
                    }
            }
        }
        write('\"');
    }

    private void write(String str) throws IOException {
        try {
            if (this.settings.getMaxLength() == 0 || str.length() + this.curLength < this.settings.getMaxLength()) {
                this.writer.write(str);
                this.curLength += str.length();
            } else {
                this.writer.write(str.substring(0, this.settings.getMaxLength() - this.curLength));
                this.curLength = this.settings.getMaxLength();
                this.isTruncated = true;
            }
        } catch (IOException e) {
            throwBSONException(e);
        }
    }

    private void write(char c) throws IOException {
        try {
            if (this.settings.getMaxLength() == 0 || this.curLength < this.settings.getMaxLength()) {
                this.writer.write(c);
                this.curLength++;
            } else {
                this.isTruncated = true;
            }
        } catch (IOException e) {
            throwBSONException(e);
        }
    }

    private void checkPreconditions(State... validStates) {
        if (!checkState(validStates)) {
            throw new BsonInvalidOperationException("Invalid state " + this.state);
        }
    }

    private boolean checkState(State... validStates) {
        for (State cur : validStates) {
            if (cur == this.state) {
                return true;
            }
        }
        return false;
    }

    private void throwBSONException(IOException e) {
        throw new BSONException("Wrapping IOException", e);
    }
}
