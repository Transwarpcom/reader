package org.bson;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import org.bson.types.BSONTimestamp;
import org.bson.types.BasicBSONList;
import org.bson.types.Binary;
import org.bson.types.Code;
import org.bson.types.CodeWScope;
import org.bson.types.Decimal128;
import org.bson.types.MaxKey;
import org.bson.types.MinKey;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BasicBSONCallback.class */
public class BasicBSONCallback implements BSONCallback {
    private Object root;
    private final LinkedList<BSONObject> stack = new LinkedList<>();
    private final LinkedList<String> nameStack = new LinkedList<>();

    public BasicBSONCallback() {
        reset();
    }

    @Override // org.bson.BSONCallback
    public Object get() {
        return this.root;
    }

    public BSONObject create() {
        return new BasicBSONObject();
    }

    protected BSONObject createList() {
        return new BasicBSONList();
    }

    @Override // org.bson.BSONCallback
    public BSONCallback createBSONCallback() {
        return new BasicBSONCallback();
    }

    public BSONObject create(boolean array, List<String> path) {
        return array ? createList() : create();
    }

    @Override // org.bson.BSONCallback
    public void objectStart() {
        if (this.stack.size() > 0) {
            throw new IllegalStateException("Illegal object beginning in current context.");
        }
        this.root = create(false, null);
        this.stack.add((BSONObject) this.root);
    }

    @Override // org.bson.BSONCallback
    public void objectStart(String name) {
        this.nameStack.addLast(name);
        BSONObject o = create(false, this.nameStack);
        this.stack.getLast().put(name, o);
        this.stack.addLast(o);
    }

    @Override // org.bson.BSONCallback
    public Object objectDone() {
        BSONObject o = this.stack.removeLast();
        if (this.nameStack.size() > 0) {
            this.nameStack.removeLast();
        } else if (this.stack.size() > 0) {
            throw new IllegalStateException("Illegal object end in current context.");
        }
        return !BSON.hasDecodeHooks() ? o : (BSONObject) BSON.applyDecodingHooks(o);
    }

    @Override // org.bson.BSONCallback
    public void arrayStart() {
        this.root = create(true, null);
        this.stack.add((BSONObject) this.root);
    }

    @Override // org.bson.BSONCallback
    public void arrayStart(String name) {
        this.nameStack.addLast(name);
        BSONObject o = create(true, this.nameStack);
        this.stack.getLast().put(name, o);
        this.stack.addLast(o);
    }

    @Override // org.bson.BSONCallback
    public Object arrayDone() {
        return objectDone();
    }

    @Override // org.bson.BSONCallback
    public void gotNull(String name) {
        cur().put(name, null);
    }

    @Override // org.bson.BSONCallback
    public void gotUndefined(String name) {
    }

    @Override // org.bson.BSONCallback
    public void gotMinKey(String name) {
        cur().put(name, new MinKey());
    }

    @Override // org.bson.BSONCallback
    public void gotMaxKey(String name) {
        cur().put(name, new MaxKey());
    }

    @Override // org.bson.BSONCallback
    public void gotBoolean(String name, boolean value) {
        _put(name, Boolean.valueOf(value));
    }

    @Override // org.bson.BSONCallback
    public void gotDouble(String name, double value) {
        _put(name, Double.valueOf(value));
    }

    @Override // org.bson.BSONCallback
    public void gotInt(String name, int value) {
        _put(name, Integer.valueOf(value));
    }

    @Override // org.bson.BSONCallback
    public void gotLong(String name, long value) {
        _put(name, Long.valueOf(value));
    }

    @Override // org.bson.BSONCallback
    public void gotDecimal128(String name, Decimal128 value) {
        _put(name, value);
    }

    @Override // org.bson.BSONCallback
    public void gotDate(String name, long millis) {
        _put(name, new Date(millis));
    }

    @Override // org.bson.BSONCallback
    public void gotRegex(String name, String pattern, String flags) {
        _put(name, Pattern.compile(pattern, BSON.regexFlags(flags)));
    }

    @Override // org.bson.BSONCallback
    public void gotString(String name, String value) {
        _put(name, value);
    }

    @Override // org.bson.BSONCallback
    public void gotSymbol(String name, String value) {
        _put(name, value);
    }

    @Override // org.bson.BSONCallback
    public void gotTimestamp(String name, int time, int increment) {
        _put(name, new BSONTimestamp(time, increment));
    }

    @Override // org.bson.BSONCallback
    public void gotObjectId(String name, ObjectId id) {
        _put(name, id);
    }

    @Override // org.bson.BSONCallback
    public void gotDBRef(String name, String namespace, ObjectId id) {
        _put(name, new BasicBSONObject("$ns", namespace).append("$id", id));
    }

    @Override // org.bson.BSONCallback
    @Deprecated
    public void gotBinaryArray(String name, byte[] data) {
        gotBinary(name, (byte) 0, data);
    }

    @Override // org.bson.BSONCallback
    public void gotBinary(String name, byte type, byte[] data) {
        if (type == 0 || type == 2) {
            _put(name, data);
        } else {
            _put(name, new Binary(type, data));
        }
    }

    @Override // org.bson.BSONCallback
    public void gotUUID(String name, long part1, long part2) {
        _put(name, new UUID(part1, part2));
    }

    @Override // org.bson.BSONCallback
    public void gotCode(String name, String code) {
        _put(name, new Code(code));
    }

    @Override // org.bson.BSONCallback
    public void gotCodeWScope(String name, String code, Object scope) {
        _put(name, new CodeWScope(code, (BSONObject) scope));
    }

    protected void _put(String name, Object value) {
        cur().put(name, !BSON.hasDecodeHooks() ? value : BSON.applyDecodingHooks(value));
    }

    protected BSONObject cur() {
        return this.stack.getLast();
    }

    protected String curName() {
        return this.nameStack.peekLast();
    }

    protected void setRoot(Object root) {
        this.root = root;
    }

    protected boolean isStackEmpty() {
        return this.stack.size() < 1;
    }

    @Override // org.bson.BSONCallback
    public void reset() {
        this.root = null;
        this.stack.clear();
        this.nameStack.clear();
    }
}
