package net.minidev.json.writer;

import java.io.IOException;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/writer/CompessorMapper.class */
public class CompessorMapper extends JsonReaderI<CompessorMapper> {
    private Appendable out;
    private JSONStyle compression;
    private Boolean _isObj;
    private boolean needSep;
    private boolean isOpen;
    private boolean isClosed;

    private boolean isArray() {
        return this._isObj == Boolean.FALSE;
    }

    private boolean isObject() {
        return this._isObj == Boolean.TRUE;
    }

    private boolean isCompressor(Object obj) {
        return obj instanceof CompessorMapper;
    }

    public CompessorMapper(JsonReader base, Appendable out, JSONStyle compression) {
        this(base, out, compression, null);
    }

    public CompessorMapper(JsonReader base, Appendable out, JSONStyle compression, Boolean isObj) {
        super(base);
        this.needSep = false;
        this.isOpen = false;
        this.isClosed = false;
        this.out = out;
        this.compression = compression;
        this._isObj = isObj;
    }

    @Override // net.minidev.json.writer.JsonReaderI
    public JsonReaderI<?> startObject(String key) throws IOException {
        open(this);
        startKey(key);
        CompessorMapper r = new CompessorMapper(this.base, this.out, this.compression, true);
        open(r);
        return r;
    }

    @Override // net.minidev.json.writer.JsonReaderI
    public JsonReaderI<?> startArray(String key) throws IOException {
        open(this);
        startKey(key);
        CompessorMapper r = new CompessorMapper(this.base, this.out, this.compression, false);
        open(r);
        return r;
    }

    private void startKey(String key) throws IOException {
        addComma();
        if (isArray()) {
            return;
        }
        if (!this.compression.mustProtectKey(key)) {
            this.out.append(key);
        } else {
            this.out.append('\"');
            JSONValue.escape(key, this.out, this.compression);
            this.out.append('\"');
        }
        this.out.append(':');
    }

    @Override // net.minidev.json.writer.JsonReaderI
    public void setValue(Object current, String key, Object value) throws IOException {
        if (isCompressor(value)) {
            addComma();
        } else {
            startKey(key);
            writeValue(value);
        }
    }

    @Override // net.minidev.json.writer.JsonReaderI
    public void addValue(Object current, Object value) throws IOException {
        addComma();
        writeValue(value);
    }

    private void addComma() throws IOException {
        if (this.needSep) {
            this.out.append(',');
        } else {
            this.needSep = true;
        }
    }

    private void writeValue(Object value) throws IOException {
        if (value instanceof String) {
            this.compression.writeString(this.out, (String) value);
        } else if (isCompressor(value)) {
            close(value);
        } else {
            JSONValue.writeJSONString(value, this.out, this.compression);
        }
    }

    @Override // net.minidev.json.writer.JsonReaderI
    public Object createObject() {
        this._isObj = true;
        try {
            open(this);
        } catch (Exception e) {
        }
        return this;
    }

    @Override // net.minidev.json.writer.JsonReaderI
    public Object createArray() {
        this._isObj = false;
        try {
            open(this);
        } catch (Exception e) {
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // net.minidev.json.writer.JsonReaderI
    public CompessorMapper convert(Object current) {
        try {
            close(current);
            return this;
        } catch (Exception e) {
            return this;
        }
    }

    private void close(Object obj) throws IOException {
        if (!isCompressor(obj) || ((CompessorMapper) obj).isClosed) {
            return;
        }
        ((CompessorMapper) obj).isClosed = true;
        if (((CompessorMapper) obj).isObject()) {
            this.out.append('}');
            this.needSep = true;
        } else if (((CompessorMapper) obj).isArray()) {
            this.out.append(']');
            this.needSep = true;
        }
    }

    private void open(Object obj) throws IOException {
        if (!isCompressor(obj) || ((CompessorMapper) obj).isOpen) {
            return;
        }
        ((CompessorMapper) obj).isOpen = true;
        if (((CompessorMapper) obj).isObject()) {
            this.out.append('{');
            this.needSep = false;
        } else if (((CompessorMapper) obj).isArray()) {
            this.out.append('[');
            this.needSep = false;
        }
    }
}
