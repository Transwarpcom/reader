package org.jsoup.nodes;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseSettings;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Attributes.class */
public class Attributes implements Iterable<Attribute>, Cloneable {
    protected static final String dataPrefix = "data-";
    static final char InternalPrefix = '/';
    private static final int InitialCapacity = 3;
    private static final int GrowthFactor = 2;
    static final int NotFound = -1;
    private static final String EmptyString = "";
    private int size = 0;
    String[] keys = new String[3];
    String[] vals = new String[3];

    private void checkCapacity(int minNewSize) {
        Validate.isTrue(minNewSize >= this.size);
        int curCap = this.keys.length;
        if (curCap >= minNewSize) {
            return;
        }
        int newCap = curCap >= 3 ? this.size * 2 : 3;
        if (minNewSize > newCap) {
            newCap = minNewSize;
        }
        this.keys = (String[]) Arrays.copyOf(this.keys, newCap);
        this.vals = (String[]) Arrays.copyOf(this.vals, newCap);
    }

    int indexOfKey(String key) {
        Validate.notNull(key);
        for (int i = 0; i < this.size; i++) {
            if (key.equals(this.keys[i])) {
                return i;
            }
        }
        return -1;
    }

    private int indexOfKeyIgnoreCase(String key) {
        Validate.notNull(key);
        for (int i = 0; i < this.size; i++) {
            if (key.equalsIgnoreCase(this.keys[i])) {
                return i;
            }
        }
        return -1;
    }

    static String checkNotNull(@Nullable String val) {
        return val == null ? "" : val;
    }

    public String get(String key) {
        int i = indexOfKey(key);
        return i == -1 ? "" : checkNotNull(this.vals[i]);
    }

    public String getIgnoreCase(String key) {
        int i = indexOfKeyIgnoreCase(key);
        return i == -1 ? "" : checkNotNull(this.vals[i]);
    }

    public Attributes add(String key, @Nullable String value) {
        checkCapacity(this.size + 1);
        this.keys[this.size] = key;
        this.vals[this.size] = value;
        this.size++;
        return this;
    }

    public Attributes put(String key, String value) {
        Validate.notNull(key);
        int i = indexOfKey(key);
        if (i != -1) {
            this.vals[i] = value;
        } else {
            add(key, value);
        }
        return this;
    }

    void putIgnoreCase(String key, @Nullable String value) {
        int i = indexOfKeyIgnoreCase(key);
        if (i != -1) {
            this.vals[i] = value;
            if (!this.keys[i].equals(key)) {
                this.keys[i] = key;
                return;
            }
            return;
        }
        add(key, value);
    }

    public Attributes put(String key, boolean value) {
        if (value) {
            putIgnoreCase(key, null);
        } else {
            remove(key);
        }
        return this;
    }

    public Attributes put(Attribute attribute) {
        Validate.notNull(attribute);
        put(attribute.getKey(), attribute.getValue());
        attribute.parent = this;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void remove(int index) {
        Validate.isFalse(index >= this.size);
        int shifted = (this.size - index) - 1;
        if (shifted > 0) {
            System.arraycopy(this.keys, index + 1, this.keys, index, shifted);
            System.arraycopy(this.vals, index + 1, this.vals, index, shifted);
        }
        this.size--;
        this.keys[this.size] = null;
        this.vals[this.size] = null;
    }

    public void remove(String key) {
        int i = indexOfKey(key);
        if (i != -1) {
            remove(i);
        }
    }

    public void removeIgnoreCase(String key) {
        int i = indexOfKeyIgnoreCase(key);
        if (i != -1) {
            remove(i);
        }
    }

    public boolean hasKey(String key) {
        return indexOfKey(key) != -1;
    }

    public boolean hasKeyIgnoreCase(String key) {
        return indexOfKeyIgnoreCase(key) != -1;
    }

    public boolean hasDeclaredValueForKey(String key) {
        int i = indexOfKey(key);
        return (i == -1 || this.vals[i] == null) ? false : true;
    }

    public boolean hasDeclaredValueForKeyIgnoreCase(String key) {
        int i = indexOfKeyIgnoreCase(key);
        return (i == -1 || this.vals[i] == null) ? false : true;
    }

    public int size() {
        int s = 0;
        for (int i = 0; i < this.size; i++) {
            if (!isInternalKey(this.keys[i])) {
                s++;
            }
        }
        return s;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void addAll(Attributes incoming) {
        if (incoming.size() == 0) {
            return;
        }
        checkCapacity(this.size + incoming.size);
        Iterator<Attribute> it = incoming.iterator();
        while (it.hasNext()) {
            Attribute attr = it.next();
            put(attr);
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Attribute> iterator() {
        return new Iterator<Attribute>() { // from class: org.jsoup.nodes.Attributes.1
            int i = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                while (this.i < Attributes.this.size && Attributes.this.isInternalKey(Attributes.this.keys[this.i])) {
                    this.i++;
                }
                return this.i < Attributes.this.size;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Attribute next() {
                Attribute attr = new Attribute(Attributes.this.keys[this.i], Attributes.this.vals[this.i], Attributes.this);
                this.i++;
                return attr;
            }

            @Override // java.util.Iterator
            public void remove() {
                Attributes attributes = Attributes.this;
                int i = this.i - 1;
                this.i = i;
                attributes.remove(i);
            }
        };
    }

    public List<Attribute> asList() {
        ArrayList<Attribute> list = new ArrayList<>(this.size);
        for (int i = 0; i < this.size; i++) {
            if (!isInternalKey(this.keys[i])) {
                Attribute attr = new Attribute(this.keys[i], this.vals[i], this);
                list.add(attr);
            }
        }
        return Collections.unmodifiableList(list);
    }

    public Map<String, String> dataset() {
        return new Dataset();
    }

    public String html() {
        StringBuilder sb = StringUtil.borrowBuilder();
        try {
            html(sb, new Document("").outputSettings());
            return StringUtil.releaseBuilder(sb);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    final void html(Appendable accum, Document.OutputSettings out) throws IOException {
        int sz = this.size;
        for (int i = 0; i < sz; i++) {
            if (!isInternalKey(this.keys[i])) {
                String key = this.keys[i];
                String val = this.vals[i];
                accum.append(' ').append(key);
                if (!Attribute.shouldCollapseAttribute(key, val, out)) {
                    accum.append("=\"");
                    Entities.escape(accum, val == null ? "" : val, out, true, false, false);
                    accum.append('\"');
                }
            }
        }
    }

    public String toString() {
        return html();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attributes that = (Attributes) o;
        if (this.size == that.size && Arrays.equals(this.keys, that.keys)) {
            return Arrays.equals(this.vals, that.vals);
        }
        return false;
    }

    public int hashCode() {
        int result = this.size;
        return (31 * ((31 * result) + Arrays.hashCode(this.keys))) + Arrays.hashCode(this.vals);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Attributes m5202clone() {
        try {
            Attributes clone = (Attributes) super.clone();
            clone.size = this.size;
            this.keys = (String[]) Arrays.copyOf(this.keys, this.size);
            this.vals = (String[]) Arrays.copyOf(this.vals, this.size);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void normalize() {
        for (int i = 0; i < this.size; i++) {
            this.keys[i] = Normalizer.lowerCase(this.keys[i]);
        }
    }

    public int deduplicate(ParseSettings settings) {
        if (isEmpty()) {
            return 0;
        }
        boolean preserve = settings.preserveAttributeCase();
        int dupes = 0;
        for (int i = 0; i < this.keys.length; i++) {
            int j = i + 1;
            while (j < this.keys.length && this.keys[j] != null) {
                if ((preserve && this.keys[i].equals(this.keys[j])) || (!preserve && this.keys[i].equalsIgnoreCase(this.keys[j]))) {
                    dupes++;
                    remove(j);
                    j--;
                }
                j++;
            }
        }
        return dupes;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Attributes$Dataset.class */
    private static class Dataset extends AbstractMap<String, String> {
        private final Attributes attributes;

        private Dataset(Attributes attributes) {
            this.attributes = attributes;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<String, String>> entrySet() {
            return new EntrySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public String put(String key, String value) {
            String dataKey = Attributes.dataKey(key);
            String oldValue = this.attributes.hasKey(dataKey) ? this.attributes.get(dataKey) : null;
            this.attributes.put(dataKey, value);
            return oldValue;
        }

        /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Attributes$Dataset$EntrySet.class */
        private class EntrySet extends AbstractSet<Map.Entry<String, String>> {
            private EntrySet() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<String, String>> iterator() {
                return new DatasetIterator();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                int count = 0;
                Iterator iter = new DatasetIterator();
                while (iter.hasNext()) {
                    count++;
                }
                return count;
            }
        }

        /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Attributes$Dataset$DatasetIterator.class */
        private class DatasetIterator implements Iterator<Map.Entry<String, String>> {
            private Iterator<Attribute> attrIter;
            private Attribute attr;

            private DatasetIterator() {
                this.attrIter = Dataset.this.attributes.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                while (this.attrIter.hasNext()) {
                    this.attr = this.attrIter.next();
                    if (this.attr.isDataAttribute()) {
                        return true;
                    }
                }
                return false;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Map.Entry<String, String> next() {
                return new Attribute(this.attr.getKey().substring(Attributes.dataPrefix.length()), this.attr.getValue());
            }

            @Override // java.util.Iterator
            public void remove() {
                Dataset.this.attributes.remove(this.attr.getKey());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String dataKey(String key) {
        return dataPrefix + key;
    }

    static String internalKey(String key) {
        return '/' + key;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInternalKey(String key) {
        return key != null && key.length() > 1 && key.charAt(0) == '/';
    }
}
