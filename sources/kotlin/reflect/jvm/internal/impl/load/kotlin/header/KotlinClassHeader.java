package kotlin.reflect.jvm.internal.impl.load.kotlin.header;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinClassHeader.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/header/KotlinClassHeader.class */
public final class KotlinClassHeader {

    @NotNull
    private final Kind kind;

    @NotNull
    private final JvmMetadataVersion metadataVersion;

    @Nullable
    private final String[] data;

    @Nullable
    private final String[] incompatibleData;

    @Nullable
    private final String[] strings;

    @Nullable
    private final String extraString;
    private final int extraInt;

    @Nullable
    private final String packageName;

    public KotlinClassHeader(@NotNull Kind kind, @NotNull JvmMetadataVersion metadataVersion, @Nullable String[] data, @Nullable String[] incompatibleData, @Nullable String[] strings, @Nullable String extraString, int extraInt, @Nullable String packageName) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(metadataVersion, "metadataVersion");
        this.kind = kind;
        this.metadataVersion = metadataVersion;
        this.data = data;
        this.incompatibleData = incompatibleData;
        this.strings = strings;
        this.extraString = extraString;
        this.extraInt = extraInt;
        this.packageName = packageName;
    }

    @NotNull
    public final Kind getKind() {
        return this.kind;
    }

    @NotNull
    public final JvmMetadataVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    @Nullable
    public final String[] getData() {
        return this.data;
    }

    @Nullable
    public final String[] getIncompatibleData() {
        return this.incompatibleData;
    }

    @Nullable
    public final String[] getStrings() {
        return this.strings;
    }

    /* compiled from: KotlinClassHeader.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/header/KotlinClassHeader$Kind.class */
    public enum Kind {
        UNKNOWN(0),
        CLASS(1),
        FILE_FACADE(2),
        SYNTHETIC_CLASS(3),
        MULTIFILE_CLASS(4),
        MULTIFILE_CLASS_PART(5);


        @NotNull
        public static final Companion Companion = new Companion(null);
        private final int id;

        @NotNull
        private static final Map<Integer, Kind> entryById;

        @JvmStatic
        @NotNull
        public static final Kind getById(int id) {
            return Companion.getById(id);
        }

        Kind(int id) {
            this.id = id;
        }

        public final int getId() {
            return this.id;
        }

        static {
            Kind[] kindArrValues = values();
            int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(kindArrValues.length), 16);
            Map destination$iv$iv = new LinkedHashMap(capacity$iv);
            for (Kind kind : kindArrValues) {
                destination$iv$iv.put(Integer.valueOf(kind.getId()), kind);
            }
            entryById = destination$iv$iv;
        }

        /* compiled from: KotlinClassHeader.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/header/KotlinClassHeader$Kind$Companion.class */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            @NotNull
            public final Kind getById(int id) {
                Kind kind = (Kind) Kind.entryById.get(Integer.valueOf(id));
                return kind == null ? Kind.UNKNOWN : kind;
            }
        }
    }

    @Nullable
    public final String getMultifileClassName() {
        String str = this.extraString;
        if (getKind() == Kind.MULTIFILE_CLASS_PART) {
            return str;
        }
        return null;
    }

    @NotNull
    public final List<String> getMultifilePartNames() {
        String[] strArr = getKind() == Kind.MULTIFILE_CLASS ? this.data : null;
        List<String> listAsList = strArr == null ? null : ArraysKt.asList(strArr);
        return listAsList != null ? listAsList : CollectionsKt.emptyList();
    }

    public final boolean isUnstableJvmIrBinary() {
        return has(this.extraInt, 16) && !has(this.extraInt, 32);
    }

    public final boolean isUnstableFirBinary() {
        return has(this.extraInt, 64) && !has(this.extraInt, 32);
    }

    public final boolean isPreRelease() {
        return has(this.extraInt, 2);
    }

    @NotNull
    public String toString() {
        return this.kind + " version=" + this.metadataVersion;
    }

    private final boolean has(int $this$has, int flag) {
        return ($this$has & flag) != 0;
    }
}
