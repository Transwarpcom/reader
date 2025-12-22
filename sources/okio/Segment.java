package okio;

import io.legado.app.constant.Action;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Segment.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u0012\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\b��\u0018�� \u00192\u00020\u0001:\u0001\u0019B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B/\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u0004\u0018\u00010��J\u000e\u0010\u0011\u001a\u00020��2\u0006\u0010\u0012\u001a\u00020��J\u0006\u0010\u0013\u001a\u00020��J\u000e\u0010\u0014\u001a\u00020��2\u0006\u0010\u0015\u001a\u00020\u0006J\u0006\u0010\u0016\u001a\u00020��J\u0016\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020��2\u0006\u0010\u0015\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n��R\u0012\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��R\u0014\u0010\f\u001a\u0004\u0018\u00010��8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��R\u0012\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��R\u0014\u0010\r\u001a\u0004\u0018\u00010��8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��¨\u0006\u001a"}, d2 = {"Lokio/Segment;", "", "()V", "data", "", "pos", "", "limit", "shared", "", "owner", "([BIIZZ)V", "next", Action.prev, "compact", "", "pop", "push", "segment", "sharedCopy", "split", "byteCount", "unsharedCopy", "writeTo", "sink", "Companion", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/Segment.class */
public final class Segment {

    @JvmField
    @NotNull
    public final byte[] data;

    @JvmField
    public int pos;

    @JvmField
    public int limit;

    @JvmField
    public boolean shared;

    @JvmField
    public boolean owner;

    @JvmField
    @Nullable
    public Segment next;

    @JvmField
    @Nullable
    public Segment prev;
    public static final int SIZE = 8192;
    public static final int SHARE_MINIMUM = 1024;
    public static final Companion Companion = new Companion(null);

    public Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.shared = false;
    }

    public Segment(@NotNull byte[] data, int pos, int limit, boolean shared, boolean owner) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.pos = pos;
        this.limit = limit;
        this.shared = shared;
        this.owner = owner;
    }

    @NotNull
    public final Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true, false);
    }

    @NotNull
    public final Segment unsharedCopy() {
        byte[] bArr = this.data;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        return new Segment(bArrCopyOf, this.pos, this.limit, false, true);
    }

    @Nullable
    public final Segment pop() {
        Segment result = this.next != this ? this.next : null;
        Segment segment = this.prev;
        Intrinsics.checkNotNull(segment);
        segment.next = this.next;
        Segment segment2 = this.next;
        Intrinsics.checkNotNull(segment2);
        segment2.prev = this.prev;
        this.next = (Segment) null;
        this.prev = (Segment) null;
        return result;
    }

    @NotNull
    public final Segment push(@NotNull Segment segment) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        segment.prev = this;
        segment.next = this.next;
        Segment segment2 = this.next;
        Intrinsics.checkNotNull(segment2);
        segment2.prev = segment;
        this.next = segment;
        return segment;
    }

    @NotNull
    public final Segment split(int byteCount) {
        Segment prefix;
        if (!(byteCount > 0 && byteCount <= this.limit - this.pos)) {
            throw new IllegalArgumentException("byteCount out of range".toString());
        }
        if (byteCount >= 1024) {
            prefix = sharedCopy();
        } else {
            prefix = SegmentPool.take();
            ArraysKt.copyInto$default(this.data, prefix.data, 0, this.pos, this.pos + byteCount, 2, (Object) null);
        }
        prefix.limit = prefix.pos + byteCount;
        this.pos += byteCount;
        Segment segment = this.prev;
        Intrinsics.checkNotNull(segment);
        segment.push(prefix);
        return prefix;
    }

    public final void compact() {
        int i;
        if (!(this.prev != this)) {
            throw new IllegalStateException("cannot compact".toString());
        }
        Segment segment = this.prev;
        Intrinsics.checkNotNull(segment);
        if (segment.owner) {
            int byteCount = this.limit - this.pos;
            Segment segment2 = this.prev;
            Intrinsics.checkNotNull(segment2);
            int i2 = 8192 - segment2.limit;
            Segment segment3 = this.prev;
            Intrinsics.checkNotNull(segment3);
            if (segment3.shared) {
                i = 0;
            } else {
                Segment segment4 = this.prev;
                Intrinsics.checkNotNull(segment4);
                i = segment4.pos;
            }
            int availableByteCount = i2 + i;
            if (byteCount > availableByteCount) {
                return;
            }
            Segment segment5 = this.prev;
            Intrinsics.checkNotNull(segment5);
            writeTo(segment5, byteCount);
            pop();
            SegmentPool.recycle(this);
        }
    }

    public final void writeTo(@NotNull Segment sink, int byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (!sink.owner) {
            throw new IllegalStateException("only owner can write".toString());
        }
        if (sink.limit + byteCount > 8192) {
            if (sink.shared) {
                throw new IllegalArgumentException();
            }
            if ((sink.limit + byteCount) - sink.pos > 8192) {
                throw new IllegalArgumentException();
            }
            ArraysKt.copyInto$default(sink.data, sink.data, 0, sink.pos, sink.limit, 2, (Object) null);
            sink.limit -= sink.pos;
            sink.pos = 0;
        }
        ArraysKt.copyInto(this.data, sink.data, sink.limit, this.pos, this.pos + byteCount);
        sink.limit += byteCount;
        this.pos += byteCount;
    }

    /* compiled from: Segment.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lokio/Segment$Companion;", "", "()V", "SHARE_MINIMUM", "", "SIZE", "okio"})
    /* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/Segment$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
