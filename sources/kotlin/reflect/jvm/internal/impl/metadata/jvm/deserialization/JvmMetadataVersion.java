package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmMetadataVersion.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmMetadataVersion.class */
public final class JvmMetadataVersion extends BinaryVersion {
    private final boolean isStrictSemantics;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    public static final JvmMetadataVersion INSTANCE = new JvmMetadataVersion(1, 5, 1);

    @JvmField
    @NotNull
    public static final JvmMetadataVersion INVALID_VERSION = new JvmMetadataVersion(new int[0]);

    /* JADX WARN: Illegal instructions before constructor call */
    public JvmMetadataVersion(@NotNull int[] versionArray, boolean isStrictSemantics) {
        Intrinsics.checkNotNullParameter(versionArray, "versionArray");
        int[] iArr = new int[versionArray.length];
        System.arraycopy(versionArray, 0, iArr, 0, versionArray.length);
        super(iArr);
        this.isStrictSemantics = isStrictSemantics;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JvmMetadataVersion(@NotNull int... numbers) {
        this(numbers, false);
        Intrinsics.checkNotNullParameter(numbers, "numbers");
    }

    public boolean isCompatible() {
        boolean zIsCompatibleTo;
        if (getMajor() != 1 || getMinor() != 0) {
            if (this.isStrictSemantics) {
                zIsCompatibleTo = isCompatibleTo(INSTANCE);
            } else {
                zIsCompatibleTo = getMajor() == INSTANCE.getMajor() && getMinor() <= INSTANCE.getMinor() + 1;
            }
            if (zIsCompatibleTo) {
                return true;
            }
        }
        return false;
    }

    /* compiled from: JvmMetadataVersion.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmMetadataVersion$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }
}
