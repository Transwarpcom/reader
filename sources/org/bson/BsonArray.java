package org.bson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.bson.codecs.BsonArrayCodec;
import org.bson.codecs.DecoderContext;
import org.bson.json.JsonReader;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonArray.class */
public class BsonArray extends BsonValue implements List<BsonValue>, Cloneable {
    private final List<BsonValue> values;

    public BsonArray(List<? extends BsonValue> values) {
        this(values, true);
    }

    public BsonArray() {
        this(new ArrayList(), false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    BsonArray(List<? extends BsonValue> list, boolean copy) {
        if (copy) {
            this.values = new ArrayList(list);
        } else {
            this.values = list;
        }
    }

    public static BsonArray parse(String json) {
        return new BsonArrayCodec().decode((BsonReader) new JsonReader(json), DecoderContext.builder().build());
    }

    public List<BsonValue> getValues() {
        return Collections.unmodifiableList(this.values);
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.ARRAY;
    }

    public int size() {
        return this.values.size();
    }

    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    public boolean contains(Object o) {
        return this.values.contains(o);
    }

    public Iterator<BsonValue> iterator() {
        return this.values.iterator();
    }

    public Object[] toArray() {
        return this.values.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.values.toArray(tArr);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(BsonValue bsonValue) {
        return this.values.add(bsonValue);
    }

    public boolean remove(Object o) {
        return this.values.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return this.values.containsAll(c);
    }

    public boolean addAll(Collection<? extends BsonValue> c) {
        return this.values.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends BsonValue> c) {
        return this.values.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return this.values.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return this.values.retainAll(c);
    }

    public void clear() {
        this.values.clear();
    }

    @Override // java.util.List
    public BsonValue get(int index) {
        return this.values.get(index);
    }

    @Override // java.util.List
    public BsonValue set(int index, BsonValue element) {
        return this.values.set(index, element);
    }

    @Override // java.util.List
    public void add(int index, BsonValue element) {
        this.values.add(index, element);
    }

    @Override // java.util.List
    public BsonValue remove(int index) {
        return this.values.remove(index);
    }

    public int indexOf(Object o) {
        return this.values.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return this.values.lastIndexOf(o);
    }

    public ListIterator<BsonValue> listIterator() {
        return this.values.listIterator();
    }

    public ListIterator<BsonValue> listIterator(int index) {
        return this.values.listIterator(index);
    }

    public List<BsonValue> subList(int fromIndex, int toIndex) {
        return this.values.subList(fromIndex, toIndex);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BsonArray)) {
            return false;
        }
        BsonArray that = (BsonArray) o;
        return getValues().equals(that.getValues());
    }

    public int hashCode() {
        return this.values.hashCode();
    }

    public String toString() {
        return "BsonArray{values=" + this.values + '}';
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BsonArray mo1046clone() {
        BsonArray to = new BsonArray();
        Iterator<BsonValue> it = iterator();
        while (it.hasNext()) {
            BsonValue cur = it.next();
            switch (cur.getBsonType()) {
                case DOCUMENT:
                    to.add((BsonValue) cur.asDocument().mo1002clone());
                    break;
                case ARRAY:
                    to.add((BsonValue) cur.asArray().mo1046clone());
                    break;
                case BINARY:
                    to.add((BsonValue) BsonBinary.clone(cur.asBinary()));
                    break;
                case JAVASCRIPT_WITH_SCOPE:
                    to.add((BsonValue) BsonJavaScriptWithScope.clone(cur.asJavaScriptWithScope()));
                    break;
                default:
                    to.add(cur);
                    break;
            }
        }
        return to;
    }
}
