package kotlin.reflect.jvm.internal.impl.metadata.builtins;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import org.jetbrains.annotations.NotNull;

/* compiled from: BuiltInsBinaryVersion.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/builtins/BuiltInsBinaryVersion.class */
public final class BuiltInsBinaryVersion extends BinaryVersion {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    public static final BuiltInsBinaryVersion INSTANCE = new BuiltInsBinaryVersion(1, 0, 7);

    @JvmField
    @NotNull
    public static final BuiltInsBinaryVersion INVALID_VERSION = new BuiltInsBinaryVersion(new int[0]);

    /* JADX WARN: Illegal instructions before constructor call */
    public BuiltInsBinaryVersion(@NotNull int... numbers) {
        Intrinsics.checkNotNullParameter(numbers, "numbers");
        int[] iArr = new int[numbers.length];
        System.arraycopy(numbers, 0, iArr, 0, numbers.length);
        super(iArr);
    }

    public boolean isCompatible() {
        return isCompatibleTo(INSTANCE);
    }

    /* compiled from: BuiltInsBinaryVersion.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/builtins/BuiltInsBinaryVersion$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final BuiltInsBinaryVersion readFrom(@NotNull InputStream stream) {
            Intrinsics.checkNotNullParameter(stream, "stream");
            DataInputStream dataInput = new DataInputStream(stream);
            Iterable $this$map$iv = new IntRange(1, dataInput.readInt());
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            Iterator<Integer> it = $this$map$iv.iterator();
            while (it.hasNext()) {
                ((IntIterator) it).nextInt();
                destination$iv$iv.add(Integer.valueOf(dataInput.readInt()));
            }
            int[] intArray = CollectionsKt.toIntArray((List) destination$iv$iv);
            int[] iArr = new int[intArray.length];
            System.arraycopy(intArray, 0, iArr, 0, intArray.length);
            return new BuiltInsBinaryVersion(iArr);
        }
    }
}
