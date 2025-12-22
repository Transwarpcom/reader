package okio;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.io.IOException;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: Options.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n��\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018�� \u00152\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004:\u0001\u0015B\u001f\b\u0002\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u000eH\u0096\u0002R\u001e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lokio/Options;", "Lkotlin/collections/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "byteStrings", "", "trie", "", "([Lokio/ByteString;[I)V", "getByteStrings$okio", "()[Lokio/ByteString;", "[Lokio/ByteString;", "size", "", "getSize", "()I", "getTrie$okio", "()[I", BeanUtil.PREFIX_GETTER_GET, "index", "Companion", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/Options.class */
public final class Options extends AbstractList<ByteString> implements RandomAccess {

    @NotNull
    private final ByteString[] byteStrings;

    @NotNull
    private final int[] trie;
    public static final Companion Companion = new Companion(null);

    @JvmStatic
    @NotNull
    public static final Options of(@NotNull ByteString... byteStrings) {
        return Companion.of(byteStrings);
    }

    private Options(ByteString[] byteStrings, int[] trie) {
        this.byteStrings = byteStrings;
        this.trie = trie;
    }

    public /* bridge */ boolean contains(ByteString byteString) {
        return super.contains((Object) byteString);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return contains((ByteString) obj);
        }
        return false;
    }

    public /* bridge */ int indexOf(ByteString byteString) {
        return super.indexOf((Object) byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return indexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(ByteString byteString) {
        return super.lastIndexOf((Object) byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return lastIndexOf((ByteString) obj);
        }
        return -1;
    }

    public /* synthetic */ Options(ByteString[] byteStrings, int[] trie, DefaultConstructorMarker $constructor_marker) {
        this(byteStrings, trie);
    }

    @NotNull
    public final ByteString[] getByteStrings$okio() {
        return this.byteStrings;
    }

    @NotNull
    public final int[] getTrie$okio() {
        return this.trie;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.byteStrings.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public ByteString get(int index) {
        return this.byteStrings[index];
    }

    /* compiled from: Options.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002J!\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0016\"\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0017R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lokio/Options$Companion;", "", "()V", "intCount", "", "Lokio/Buffer;", "getIntCount", "(Lokio/Buffer;)J", "buildTrieRecursive", "", "nodeOffset", "node", "byteStringOffset", "", "byteStrings", "", "Lokio/ByteString;", "fromIndex", "toIndex", "indexes", "of", "Lokio/Options;", "", "([Lokio/ByteString;)Lokio/Options;", "okio"})
    /* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/Options$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX WARN: Code restructure failed: missing block: B:63:0x0220, code lost:
        
            continue;
         */
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final okio.Options of(@org.jetbrains.annotations.NotNull okio.ByteString... r13) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 652
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.Options.Companion.of(okio.ByteString[]):okio.Options");
        }

        static /* synthetic */ void buildTrieRecursive$default(Companion companion, long j, Buffer buffer, int i, List list, int i2, int i3, List list2, int i4, Object obj) throws IOException {
            if ((i4 & 1) != 0) {
                j = 0;
            }
            if ((i4 & 4) != 0) {
                i = 0;
            }
            if ((i4 & 16) != 0) {
                i2 = 0;
            }
            if ((i4 & 32) != 0) {
                i3 = list.size();
            }
            companion.buildTrieRecursive(j, buffer, i, list, i2, i3, list2);
        }

        private final void buildTrieRecursive(long nodeOffset, Buffer node, int byteStringOffset, List<? extends ByteString> list, int fromIndex, int toIndex, List<Integer> list2) throws IOException {
            if (!(fromIndex < toIndex)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            for (int i = fromIndex; i < toIndex; i++) {
                if (!(list.get(i).size() >= byteStringOffset)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            }
            int fromIndex2 = fromIndex;
            ByteString from = list.get(fromIndex2);
            ByteString to = list.get(toIndex - 1);
            int prefixIndex = -1;
            if (byteStringOffset == from.size()) {
                prefixIndex = list2.get(fromIndex2).intValue();
                fromIndex2++;
                from = list.get(fromIndex2);
            }
            if (from.getByte(byteStringOffset) != to.getByte(byteStringOffset)) {
                int selectChoiceCount = 1;
                for (int i2 = fromIndex2 + 1; i2 < toIndex; i2++) {
                    if (list.get(i2 - 1).getByte(byteStringOffset) != list.get(i2).getByte(byteStringOffset)) {
                        selectChoiceCount++;
                    }
                }
                long childNodesOffset = nodeOffset + getIntCount(node) + 2 + (selectChoiceCount * 2);
                node.writeInt(selectChoiceCount);
                node.writeInt(prefixIndex);
                for (int i3 = fromIndex2; i3 < toIndex; i3++) {
                    byte rangeByte = list.get(i3).getByte(byteStringOffset);
                    if (i3 == fromIndex2 || rangeByte != list.get(i3 - 1).getByte(byteStringOffset)) {
                        node.writeInt(rangeByte & 255);
                    }
                }
                Buffer childNodes = new Buffer();
                int i4 = fromIndex2;
                while (true) {
                    int rangeStart = i4;
                    if (rangeStart < toIndex) {
                        byte rangeByte2 = list.get(rangeStart).getByte(byteStringOffset);
                        int rangeEnd = toIndex;
                        int i5 = rangeStart + 1;
                        while (true) {
                            if (i5 >= toIndex) {
                                break;
                            }
                            if (rangeByte2 == list.get(i5).getByte(byteStringOffset)) {
                                i5++;
                            } else {
                                rangeEnd = i5;
                                break;
                            }
                        }
                        if (rangeStart + 1 == rangeEnd && byteStringOffset + 1 == list.get(rangeStart).size()) {
                            node.writeInt(list2.get(rangeStart).intValue());
                        } else {
                            node.writeInt((-1) * ((int) (childNodesOffset + getIntCount(childNodes))));
                            buildTrieRecursive(childNodesOffset, childNodes, byteStringOffset + 1, list, rangeStart, rangeEnd, list2);
                        }
                        i4 = rangeEnd;
                    } else {
                        node.writeAll(childNodes);
                        return;
                    }
                }
            } else {
                int scanByteCount = 0;
                int iMin = Math.min(from.size(), to.size());
                for (int i6 = byteStringOffset; i6 < iMin && from.getByte(i6) == to.getByte(i6); i6++) {
                    scanByteCount++;
                }
                long childNodesOffset2 = nodeOffset + getIntCount(node) + 2 + scanByteCount + 1;
                node.writeInt(-scanByteCount);
                node.writeInt(prefixIndex);
                int i7 = byteStringOffset + scanByteCount;
                for (int i8 = byteStringOffset; i8 < i7; i8++) {
                    byte $this$and$iv = from.getByte(i8);
                    node.writeInt($this$and$iv & 255);
                }
                if (fromIndex2 + 1 == toIndex) {
                    if (!(byteStringOffset + scanByteCount == list.get(fromIndex2).size())) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    node.writeInt(list2.get(fromIndex2).intValue());
                } else {
                    Buffer childNodes2 = new Buffer();
                    node.writeInt((-1) * ((int) (childNodesOffset2 + getIntCount(childNodes2))));
                    buildTrieRecursive(childNodesOffset2, childNodes2, byteStringOffset + scanByteCount, list, fromIndex2, toIndex, list2);
                    node.writeAll(childNodes2);
                }
            }
        }

        private final long getIntCount(Buffer $this$intCount) {
            return $this$intCount.size() / 4;
        }
    }
}
