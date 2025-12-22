package io.vertx.core.http;

import io.vertx.core.MultiMap;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/CaseInsensitiveHeaders.class */
public final class CaseInsensitiveHeaders implements MultiMap {
    private static final int BUCKET_SIZE = 17;
    private final MapEntry[] entries = new MapEntry[17];
    private final MapEntry head = new MapEntry(-1, null, null);

    private static int hash(String name) {
        int h = 0;
        for (int i = name.length() - 1; i >= 0; i--) {
            char c = name.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c + ' ');
            }
            h = (31 * h) + c;
        }
        if (h > 0) {
            return h;
        }
        if (h == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return -h;
    }

    private MultiMap set0(Iterable<Map.Entry<String, String>> map) {
        mo1932clear();
        for (Map.Entry<String, String> entry : map) {
            add(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap setAll(MultiMap headers) {
        return set0(headers);
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap setAll(Map<String, String> headers) {
        return set0(headers.entrySet());
    }

    @Override // io.vertx.core.MultiMap
    public int size() {
        return names().size();
    }

    private static boolean eq(String name1, String name2) {
        int nameLen = name1.length();
        if (nameLen != name2.length()) {
            return false;
        }
        for (int i = nameLen - 1; i >= 0; i--) {
            char c1 = name1.charAt(i);
            char c2 = name2.charAt(i);
            if (c1 != c2) {
                if (c1 >= 'A' && c1 <= 'Z') {
                    c1 = (char) (c1 + ' ');
                }
                if (c2 >= 'A' && c2 <= 'Z') {
                    c2 = (char) (c2 + ' ');
                }
                if (c1 != c2) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int index(int hash) {
        return hash % 17;
    }

    public CaseInsensitiveHeaders() {
        MapEntry mapEntry = this.head;
        MapEntry mapEntry2 = this.head;
        MapEntry mapEntry3 = this.head;
        mapEntry2.after = mapEntry3;
        mapEntry.before = mapEntry3;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap add(String name, String strVal) {
        int h = hash(name);
        int i = index(h);
        add0(h, i, name, strVal);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: add */
    public MultiMap mo1938add(String name, Iterable<String> values) {
        int h = hash(name);
        int i = index(h);
        for (String vstr : values) {
            add0(h, i, name, vstr);
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap addAll(MultiMap headers) {
        for (Map.Entry<String, String> entry : headers.entries()) {
            add(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap addAll(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
        return this;
    }

    private void add0(int h, int i, String name, String value) {
        MapEntry e = this.entries[i];
        MapEntry[] mapEntryArr = this.entries;
        MapEntry newEntry = new MapEntry(h, name, value);
        mapEntryArr[i] = newEntry;
        newEntry.next = e;
        newEntry.addBefore(this.head);
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: remove */
    public MultiMap mo1934remove(String name) {
        Objects.requireNonNull(name, "name");
        int h = hash(name);
        int i = index(h);
        remove0(h, i, name);
        return this;
    }

    private void remove0(int h, int i, String name) {
        MapEntry e = this.entries[i];
        if (e == null) {
            return;
        }
        while (e.hash == h && eq(name, e.key)) {
            e.remove();
            MapEntry next = e.next;
            if (next != null) {
                this.entries[i] = next;
                e = next;
            } else {
                this.entries[i] = null;
                return;
            }
        }
        while (true) {
            MapEntry next2 = e.next;
            if (next2 != null) {
                if (next2.hash == h && eq(name, next2.key)) {
                    e.next = next2.next;
                    next2.remove();
                } else {
                    e = next2;
                }
            } else {
                return;
            }
        }
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap set(String name, String strVal) {
        int h = hash(name);
        int i = index(h);
        remove0(h, i, name);
        add0(h, i, name, strVal);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: set */
    public MultiMap mo1936set(String name, Iterable<String> values) {
        String v;
        Objects.requireNonNull(values, "values");
        int h = hash(name);
        int i = index(h);
        remove0(h, i, name);
        Iterator<String> it = values.iterator();
        while (it.hasNext() && (v = it.next()) != null) {
            add0(h, i, name, v);
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: clear */
    public MultiMap mo1932clear() {
        for (int i = 0; i < this.entries.length; i++) {
            this.entries[i] = null;
        }
        MapEntry mapEntry = this.head;
        MapEntry mapEntry2 = this.head;
        MapEntry mapEntry3 = this.head;
        mapEntry2.after = mapEntry3;
        mapEntry.before = mapEntry3;
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public String get(String name) {
        Objects.requireNonNull(name, "name");
        int h = hash(name);
        int i = index(h);
        String value = null;
        for (MapEntry e = this.entries[i]; e != null; e = e.next) {
            if (e.hash == h && eq(name, e.key)) {
                value = e.getValue();
            }
        }
        return value;
    }

    @Override // io.vertx.core.MultiMap
    public List<String> getAll(String name) {
        Objects.requireNonNull(name, "name");
        LinkedList<String> values = new LinkedList<>();
        int h = hash(name);
        int i = index(h);
        MapEntry mapEntry = this.entries[i];
        while (true) {
            MapEntry e = mapEntry;
            if (e != null) {
                if (e.hash == h && eq(name, e.key)) {
                    values.addFirst(e.getValue());
                }
                mapEntry = e.next;
            } else {
                return values;
            }
        }
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super Map.Entry<String, String>> action) {
        MapEntry mapEntry = this.head.after;
        while (true) {
            MapEntry e = mapEntry;
            if (e != this.head) {
                action.accept(e);
                mapEntry = e.after;
            } else {
                return;
            }
        }
    }

    @Override // io.vertx.core.MultiMap
    public List<Map.Entry<String, String>> entries() {
        List<Map.Entry<String, String>> all = new LinkedList<>();
        MapEntry mapEntry = this.head.after;
        while (true) {
            MapEntry e = mapEntry;
            if (e != this.head) {
                all.add(e);
                mapEntry = e.after;
            } else {
                return all;
            }
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<String, String>> iterator() {
        return entries().iterator();
    }

    @Override // io.vertx.core.MultiMap
    public boolean contains(String name) {
        return get(name) != null;
    }

    @Override // io.vertx.core.MultiMap
    public boolean isEmpty() {
        return this.head == this.head.after;
    }

    @Override // io.vertx.core.MultiMap
    public Set<String> names() {
        Set<String> names = new TreeSet<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
        MapEntry mapEntry = this.head.after;
        while (true) {
            MapEntry e = mapEntry;
            if (e != this.head) {
                names.add(e.getKey());
                mapEntry = e.after;
            } else {
                return names;
            }
        }
    }

    @Override // io.vertx.core.MultiMap
    public String get(CharSequence name) {
        return get(name.toString());
    }

    @Override // io.vertx.core.MultiMap
    public List<String> getAll(CharSequence name) {
        return getAll(name.toString());
    }

    @Override // io.vertx.core.MultiMap
    public boolean contains(CharSequence name) {
        return contains(name.toString());
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap add(CharSequence name, CharSequence value) {
        return add(name.toString(), value.toString());
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: add */
    public MultiMap mo1937add(CharSequence name, Iterable<CharSequence> values) {
        String n = name.toString();
        for (CharSequence seq : values) {
            add(n, seq.toString());
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap set(CharSequence name, CharSequence value) {
        return set(name.toString(), value.toString());
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: set */
    public MultiMap mo1935set(CharSequence name, Iterable<CharSequence> values) {
        mo1933remove(name);
        String n = name.toString();
        for (CharSequence seq : values) {
            add(n, seq.toString());
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: remove */
    public MultiMap mo1933remove(CharSequence name) {
        return mo1934remove(name.toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            sb.append(entry).append('\n');
        }
        return sb.toString();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/CaseInsensitiveHeaders$MapEntry.class */
    private static final class MapEntry implements Map.Entry<String, String> {
        final int hash;
        final String key;
        String value;
        MapEntry next;
        MapEntry before;
        MapEntry after;

        MapEntry(int hash, String key, String value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        void remove() {
            this.before.after = this.after;
            this.after.before = this.before;
        }

        void addBefore(MapEntry e) {
            this.after = e;
            this.before = e.before;
            this.before.after = this;
            this.after.before = this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        public String getKey() {
            return this.key;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        public String getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public String setValue(String value) {
            Objects.requireNonNull(value, "value");
            String oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public String toString() {
            return getKey() + ": " + getValue();
        }
    }
}
