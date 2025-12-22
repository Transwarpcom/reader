package org.bson;

import java.util.List;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/LazyBSONCallback.class */
public class LazyBSONCallback extends EmptyBSONCallback {
    private Object root;

    @Override // org.bson.EmptyBSONCallback, org.bson.BSONCallback
    public void reset() {
        this.root = null;
    }

    @Override // org.bson.EmptyBSONCallback, org.bson.BSONCallback
    public Object get() {
        return getRoot();
    }

    @Override // org.bson.EmptyBSONCallback, org.bson.BSONCallback
    public void gotBinary(String name, byte type, byte[] data) {
        setRoot(createObject(data, 0));
    }

    public Object createObject(byte[] bytes, int offset) {
        return new LazyBSONObject(bytes, offset, this);
    }

    public List createArray(byte[] bytes, int offset) {
        return new LazyBSONList(bytes, offset, this);
    }

    public Object createDBRef(String ns, ObjectId id) {
        return new BasicBSONObject("$ns", ns).append("$id", id);
    }

    private Object getRoot() {
        return this.root;
    }

    private void setRoot(Object root) {
        this.root = root;
    }
}
