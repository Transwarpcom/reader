package io.vertx.core.http.impl.headers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.netty.util.HashingStrategy;
import io.vertx.core.MultiMap;
import io.vertx.core.http.impl.HttpUtils;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/headers/VertxHttpHeaders.class */
public final class VertxHttpHeaders extends HttpHeaders implements MultiMap {
    private final MapEntry[] entries = new MapEntry[16];
    private final MapEntry head = new MapEntry();
    private static final int COLON_AND_SPACE_SHORT = 14880;
    static final int CRLF_SHORT = 3338;

    @Override // io.vertx.core.MultiMap
    public MultiMap setAll(MultiMap headers) {
        return set0(headers);
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap setAll(Map<String, String> headers) {
        return set0(headers.entrySet());
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public int size() {
        return names().size();
    }

    public VertxHttpHeaders() {
        MapEntry mapEntry = this.head;
        MapEntry mapEntry2 = this.head;
        MapEntry mapEntry3 = this.head;
        mapEntry2.after = mapEntry3;
        mapEntry.before = mapEntry3;
    }

    @Override // io.vertx.core.MultiMap
    public VertxHttpHeaders add(CharSequence name, CharSequence value) {
        int h = AsciiString.hashCode(name);
        int i = h & 15;
        add0(h, i, name, value);
        return this;
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public VertxHttpHeaders add(CharSequence name, Object value) {
        return add(name, (CharSequence) value);
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public HttpHeaders add(String name, Object value) {
        return add((CharSequence) name, (CharSequence) value);
    }

    @Override // io.vertx.core.MultiMap
    public VertxHttpHeaders add(String name, String strVal) {
        return add((CharSequence) name, (CharSequence) strVal);
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: add, reason: merged with bridge method [inline-methods] */
    public VertxHttpHeaders mo1937add(CharSequence name, Iterable values) {
        int h = AsciiString.hashCode(name);
        int i = h & 15;
        for (Object vstr : values) {
            add0(h, i, name, (String) vstr);
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: add, reason: merged with bridge method [inline-methods] */
    public VertxHttpHeaders mo1938add(String name, Iterable values) {
        return mo1937add((CharSequence) name, values);
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap addAll(MultiMap headers) {
        return addAll(headers.entries());
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap addAll(Map<String, String> map) {
        return addAll(map.entrySet());
    }

    private MultiMap addAll(Iterable<Map.Entry<String, String>> headers) {
        for (Map.Entry<String, String> entry : headers) {
            add(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: remove, reason: merged with bridge method [inline-methods] */
    public VertxHttpHeaders mo1933remove(CharSequence name) {
        Objects.requireNonNull(name, "name");
        int h = AsciiString.hashCode(name);
        int i = h & 15;
        remove0(h, i, name);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: remove, reason: merged with bridge method [inline-methods] */
    public VertxHttpHeaders mo1934remove(String name) {
        return mo1933remove((CharSequence) name);
    }

    @Override // io.vertx.core.MultiMap
    public VertxHttpHeaders set(CharSequence name, CharSequence value) {
        return set0(name, value);
    }

    @Override // io.vertx.core.MultiMap
    public VertxHttpHeaders set(String name, String value) {
        return set((CharSequence) name, (CharSequence) value);
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public VertxHttpHeaders set(String name, Object value) {
        return set((CharSequence) name, (CharSequence) value);
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public VertxHttpHeaders set(CharSequence name, Object value) {
        return set(name, (CharSequence) value);
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: set, reason: merged with bridge method [inline-methods] */
    public VertxHttpHeaders mo1935set(CharSequence name, Iterable values) {
        Object v;
        Objects.requireNonNull(values, "values");
        int h = AsciiString.hashCode(name);
        int i = h & 15;
        remove0(h, i, name);
        Iterator it = values.iterator();
        while (it.hasNext() && (v = it.next()) != null) {
            add0(h, i, name, (CharSequence) v);
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: set, reason: merged with bridge method [inline-methods] */
    public VertxHttpHeaders mo1936set(String name, Iterable values) {
        return mo1935set((CharSequence) name, values);
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public boolean contains(CharSequence name, CharSequence value, boolean ignoreCase) {
        int h = AsciiString.hashCode(name);
        int i = h & 15;
        HashingStrategy<CharSequence> strategy = ignoreCase ? AsciiString.CASE_INSENSITIVE_HASHER : AsciiString.CASE_SENSITIVE_HASHER;
        for (MapEntry e = this.entries[i]; e != null; e = e.next) {
            CharSequence key = e.key;
            if (e.hash == h && ((name == key || AsciiString.contentEqualsIgnoreCase(name, key)) && strategy.equals(value, e.getValue()))) {
                return true;
            }
        }
        return false;
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public boolean contains(String name, String value, boolean ignoreCase) {
        return contains((CharSequence) name, (CharSequence) value, ignoreCase);
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public boolean contains(CharSequence name) {
        return get0(name) != null;
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public boolean contains(String name) {
        return contains((CharSequence) name);
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public String get(CharSequence name) {
        Objects.requireNonNull(name, "name");
        CharSequence ret = get0(name);
        if (ret != null) {
            return ret.toString();
        }
        return null;
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public String get(String name) {
        return get((CharSequence) name);
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public List<String> getAll(CharSequence name) {
        Objects.requireNonNull(name, "name");
        LinkedList<String> values = new LinkedList<>();
        int h = AsciiString.hashCode(name);
        int i = h & 15;
        MapEntry mapEntry = this.entries[i];
        while (true) {
            MapEntry e = mapEntry;
            if (e != null) {
                CharSequence key = e.key;
                if (e.hash == h && (name == key || AsciiString.contentEqualsIgnoreCase(name, key))) {
                    values.addFirst(e.getValue().toString());
                }
                mapEntry = e.next;
            } else {
                return values;
            }
        }
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public List<String> getAll(String name) {
        return getAll((CharSequence) name);
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super Map.Entry<String, String>> action) {
        MapEntry mapEntry = this.head.after;
        while (true) {
            MapEntry e = mapEntry;
            if (e != this.head) {
                action.accept(new AbstractMap.SimpleEntry(e.key.toString(), e.value.toString()));
                mapEntry = e.after;
            } else {
                return;
            }
        }
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public List<Map.Entry<String, String>> entries() {
        List<Map.Entry<String, String>> all = new ArrayList<>(size());
        MapEntry mapEntry = this.head.after;
        while (true) {
            final MapEntry e = mapEntry;
            if (e != this.head) {
                all.add(new Map.Entry<String, String>() { // from class: io.vertx.core.http.impl.headers.VertxHttpHeaders.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Map.Entry
                    public String getKey() {
                        return e.key.toString();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Map.Entry
                    public String getValue() {
                        return e.value.toString();
                    }

                    @Override // java.util.Map.Entry
                    public String setValue(String value) {
                        return e.setValue((CharSequence) value).toString();
                    }

                    public String toString() {
                        return getKey() + ": " + getValue();
                    }
                });
                mapEntry = e.after;
            } else {
                return all;
            }
        }
    }

    @Override // io.netty.handler.codec.http.HttpHeaders, java.lang.Iterable
    public Iterator<Map.Entry<String, String>> iterator() {
        return entries().iterator();
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public boolean isEmpty() {
        return this.head == this.head.after;
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public Set<String> names() {
        Set<String> names = new TreeSet<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
        MapEntry mapEntry = this.head.after;
        while (true) {
            MapEntry e = mapEntry;
            if (e != this.head) {
                names.add(e.getKey().toString());
                mapEntry = e.after;
            } else {
                return names;
            }
        }
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: clear, reason: merged with bridge method [inline-methods] */
    public VertxHttpHeaders mo1932clear() {
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

    @Override // io.netty.handler.codec.http.HttpHeaders
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            sb.append(entry).append('\n');
        }
        return sb.toString();
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public Integer getInt(CharSequence name) {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public int getInt(CharSequence name, int defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public Short getShort(CharSequence name) {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public short getShort(CharSequence name, short defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public Long getTimeMillis(CharSequence name) {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public long getTimeMillis(CharSequence name, long defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public Iterator<Map.Entry<CharSequence, CharSequence>> iteratorCharSequence() {
        return new Iterator<Map.Entry<CharSequence, CharSequence>>() { // from class: io.vertx.core.http.impl.headers.VertxHttpHeaders.2
            MapEntry current;

            {
                this.current = VertxHttpHeaders.this.head.after;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.current != VertxHttpHeaders.this.head;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Map.Entry<CharSequence, CharSequence> next() {
                Map.Entry<CharSequence, CharSequence> next = this.current;
                this.current = this.current.after;
                return next;
            }
        };
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public HttpHeaders addInt(CharSequence name, int value) {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public HttpHeaders addShort(CharSequence name, short value) {
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public HttpHeaders setInt(CharSequence name, int value) {
        return set(name, (CharSequence) Integer.toString(value));
    }

    @Override // io.netty.handler.codec.http.HttpHeaders
    public HttpHeaders setShort(CharSequence name, short value) {
        throw new UnsupportedOperationException();
    }

    public void encode(ByteBuf buf) {
        MapEntry mapEntry = this.head.after;
        while (true) {
            MapEntry current = mapEntry;
            if (current != this.head) {
                encoderHeader(current.key, current.value, buf);
                mapEntry = current.after;
            } else {
                return;
            }
        }
    }

    static void encoderHeader(CharSequence name, CharSequence value, ByteBuf buf) {
        int nameLen = name.length();
        int valueLen = value.length();
        int entryLen = nameLen + valueLen + 4;
        buf.ensureWritable(entryLen);
        int offset = buf.writerIndex();
        writeAscii(buf, offset, name);
        int offset2 = offset + nameLen;
        ByteBufUtil.setShortBE(buf, offset2, COLON_AND_SPACE_SHORT);
        int offset3 = offset2 + 2;
        writeAscii(buf, offset3, value);
        int offset4 = offset3 + valueLen;
        ByteBufUtil.setShortBE(buf, offset4, CRLF_SHORT);
        buf.writerIndex(offset4 + 2);
    }

    private static void writeAscii(ByteBuf buf, int offset, CharSequence value) {
        if (value instanceof AsciiString) {
            ByteBufUtil.copy((AsciiString) value, 0, buf, offset, value.length());
        } else {
            buf.setCharSequence(offset, value, CharsetUtil.US_ASCII);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/headers/VertxHttpHeaders$MapEntry.class */
    private static final class MapEntry implements Map.Entry<CharSequence, CharSequence> {
        final int hash;
        final CharSequence key;
        CharSequence value;
        MapEntry next;
        MapEntry before;
        MapEntry after;

        MapEntry() {
            this.hash = -1;
            this.key = null;
            this.value = null;
        }

        MapEntry(int hash, CharSequence key, CharSequence value) {
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
        public CharSequence getKey() {
            return this.key;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        public CharSequence getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public CharSequence setValue(CharSequence value) {
            Objects.requireNonNull(value, "value");
            if (!io.vertx.core.http.HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
                HttpUtils.validateHeaderValue(value);
            }
            CharSequence oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public String toString() {
            return ((Object) getKey()) + ": " + ((Object) getValue());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0058, code lost:
    
        r0 = r8.next;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0061, code lost:
    
        if (r0 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0067, code lost:
    
        r0 = r0.key;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0074, code lost:
    
        if (r0.hash != r5) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007a, code lost:
    
        if (r7 == r0) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0083, code lost:
    
        if (io.netty.util.AsciiString.contentEqualsIgnoreCase(r7, r0) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0086, code lost:
    
        r8.next = r0.next;
        r0.remove();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0098, code lost:
    
        r8 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009f, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void remove0(int r5, int r6, java.lang.CharSequence r7) {
        /*
            r4 = this;
            r0 = r4
            io.vertx.core.http.impl.headers.VertxHttpHeaders$MapEntry[] r0 = r0.entries
            r1 = r6
            r0 = r0[r1]
            r8 = r0
            r0 = r8
            if (r0 != 0) goto Le
            return
        Le:
            r0 = r8
            java.lang.CharSequence r0 = r0.key
            r9 = r0
            r0 = r8
            int r0 = r0.hash
            r1 = r5
            if (r0 != r1) goto L58
            r0 = r7
            r1 = r9
            if (r0 == r1) goto L2d
            r0 = r7
            r1 = r9
            boolean r0 = io.netty.util.AsciiString.contentEqualsIgnoreCase(r0, r1)
            if (r0 == 0) goto L58
        L2d:
            r0 = r8
            r0.remove()
            r0 = r8
            io.vertx.core.http.impl.headers.VertxHttpHeaders$MapEntry r0 = r0.next
            r10 = r0
            r0 = r10
            if (r0 == 0) goto L4d
            r0 = r4
            io.vertx.core.http.impl.headers.VertxHttpHeaders$MapEntry[] r0 = r0.entries
            r1 = r6
            r2 = r10
            r0[r1] = r2
            r0 = r10
            r8 = r0
            goto L55
        L4d:
            r0 = r4
            io.vertx.core.http.impl.headers.VertxHttpHeaders$MapEntry[] r0 = r0.entries
            r1 = r6
            r2 = 0
            r0[r1] = r2
            return
        L55:
            goto Le
        L58:
            r0 = r8
            io.vertx.core.http.impl.headers.VertxHttpHeaders$MapEntry r0 = r0.next
            r9 = r0
            r0 = r9
            if (r0 != 0) goto L67
            goto L9f
        L67:
            r0 = r9
            java.lang.CharSequence r0 = r0.key
            r10 = r0
            r0 = r9
            int r0 = r0.hash
            r1 = r5
            if (r0 != r1) goto L98
            r0 = r7
            r1 = r10
            if (r0 == r1) goto L86
            r0 = r7
            r1 = r10
            boolean r0 = io.netty.util.AsciiString.contentEqualsIgnoreCase(r0, r1)
            if (r0 == 0) goto L98
        L86:
            r0 = r8
            r1 = r9
            io.vertx.core.http.impl.headers.VertxHttpHeaders$MapEntry r1 = r1.next
            r0.next = r1
            r0 = r9
            r0.remove()
            goto L9c
        L98:
            r0 = r9
            r8 = r0
        L9c:
            goto L58
        L9f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.vertx.core.http.impl.headers.VertxHttpHeaders.remove0(int, int, java.lang.CharSequence):void");
    }

    private void add0(int h, int i, CharSequence name, CharSequence value) {
        if (!io.vertx.core.http.HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
            HttpUtils.validateHeader(name, value);
        }
        MapEntry e = this.entries[i];
        MapEntry[] mapEntryArr = this.entries;
        MapEntry newEntry = new MapEntry(h, name, value);
        mapEntryArr[i] = newEntry;
        newEntry.next = e;
        newEntry.addBefore(this.head);
    }

    private VertxHttpHeaders set0(CharSequence name, CharSequence strVal) {
        int h = AsciiString.hashCode(name);
        int i = h & 15;
        remove0(h, i, name);
        if (strVal != null) {
            add0(h, i, name, strVal);
        }
        return this;
    }

    private CharSequence get0(CharSequence name) {
        int h = AsciiString.hashCode(name);
        int i = h & 15;
        CharSequence value = null;
        for (MapEntry e = this.entries[i]; e != null; e = e.next) {
            CharSequence key = e.key;
            if (e.hash == h && (name == key || AsciiString.contentEqualsIgnoreCase(name, key))) {
                value = e.getValue();
            }
        }
        return value;
    }

    private MultiMap set0(Iterable<Map.Entry<String, String>> map) {
        mo1932clear();
        for (Map.Entry<String, String> entry : map) {
            add(entry.getKey(), entry.getValue());
        }
        return this;
    }
}
