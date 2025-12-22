package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BinaryVersion.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/BinaryVersion.class */
public abstract class BinaryVersion {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final int[] numbers;
    private final int major;
    private final int minor;
    private final int patch;

    @NotNull
    private final List<Integer> rest;

    public BinaryVersion(@NotNull int... numbers) {
        Intrinsics.checkNotNullParameter(numbers, "numbers");
        this.numbers = numbers;
        Integer orNull = ArraysKt.getOrNull(this.numbers, 0);
        this.major = orNull == null ? -1 : orNull.intValue();
        Integer orNull2 = ArraysKt.getOrNull(this.numbers, 1);
        this.minor = orNull2 == null ? -1 : orNull2.intValue();
        Integer orNull3 = ArraysKt.getOrNull(this.numbers, 2);
        this.patch = orNull3 == null ? -1 : orNull3.intValue();
        this.rest = this.numbers.length > 3 ? CollectionsKt.toList(ArraysKt.asList(this.numbers).subList(3, this.numbers.length)) : CollectionsKt.emptyList();
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    @NotNull
    public final int[] toArray() {
        return this.numbers;
    }

    protected final boolean isCompatibleTo(@NotNull BinaryVersion ourVersion) {
        Intrinsics.checkNotNullParameter(ourVersion, "ourVersion");
        return this.major == 0 ? ourVersion.major == 0 && this.minor == ourVersion.minor : this.major == ourVersion.major && this.minor <= ourVersion.minor;
    }

    public final boolean isAtLeast(@NotNull BinaryVersion version) {
        Intrinsics.checkNotNullParameter(version, "version");
        return isAtLeast(version.major, version.minor, version.patch);
    }

    public final boolean isAtLeast(int major, int minor, int patch) {
        if (this.major > major) {
            return true;
        }
        if (this.major < major) {
            return false;
        }
        if (this.minor > minor) {
            return true;
        }
        return this.minor >= minor && this.patch >= patch;
    }

    public final boolean isAtMost(int major, int minor, int patch) {
        if (this.major < major) {
            return true;
        }
        if (this.major > major) {
            return false;
        }
        if (this.minor < minor) {
            return true;
        }
        return this.minor <= minor && this.patch <= patch;
    }

    @NotNull
    public String toString() {
        int[] $this$takeWhile$iv = toArray();
        ArrayList list$iv = new ArrayList();
        int length = $this$takeWhile$iv.length;
        for (int i = 0; i < length; i++) {
            int item$iv = $this$takeWhile$iv[i];
            if (!(item$iv != -1)) {
                break;
            }
            list$iv.add(Integer.valueOf(item$iv));
        }
        ArrayList versions = list$iv;
        return versions.isEmpty() ? "unknown" : CollectionsKt.joinToString$default(versions, ".", null, null, 0, null, null, 62, null);
    }

    public boolean equals(@Nullable Object other) {
        return other != null && Intrinsics.areEqual(getClass(), other.getClass()) && this.major == ((BinaryVersion) other).major && this.minor == ((BinaryVersion) other).minor && this.patch == ((BinaryVersion) other).patch && Intrinsics.areEqual(this.rest, ((BinaryVersion) other).rest);
    }

    public int hashCode() {
        int result = this.major;
        int result2 = result + (31 * result) + this.minor;
        int result3 = result2 + (31 * result2) + this.patch;
        return result3 + (31 * result3) + this.rest.hashCode();
    }

    /* compiled from: BinaryVersion.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/BinaryVersion$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }
}
