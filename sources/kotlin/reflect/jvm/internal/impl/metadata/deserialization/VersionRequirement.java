package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.DeprecationLevel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: VersionRequirement.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/VersionRequirement.class */
public final class VersionRequirement {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Version version;

    @NotNull
    private final ProtoBuf.VersionRequirement.VersionKind kind;

    @NotNull
    private final DeprecationLevel level;

    @Nullable
    private final Integer errorCode;

    @Nullable
    private final String message;

    public VersionRequirement(@NotNull Version version, @NotNull ProtoBuf.VersionRequirement.VersionKind kind, @NotNull DeprecationLevel level, @Nullable Integer errorCode, @Nullable String message) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(level, "level");
        this.version = version;
        this.kind = kind;
        this.level = level;
        this.errorCode = errorCode;
        this.message = message;
    }

    @NotNull
    public final Version getVersion() {
        return this.version;
    }

    @NotNull
    public final ProtoBuf.VersionRequirement.VersionKind getKind() {
        return this.kind;
    }

    /* compiled from: VersionRequirement.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/VersionRequirement$Version.class */
    public static final class Version {
        private final int major;
        private final int minor;
        private final int patch;

        @NotNull
        public static final Companion Companion = new Companion(null);

        @JvmField
        @NotNull
        public static final Version INFINITY = new Version(256, 256, 256);

        public int hashCode() {
            int result = this.major;
            return (((result * 31) + this.minor) * 31) + this.patch;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Version)) {
                return false;
            }
            Version version = (Version) other;
            return this.major == version.major && this.minor == version.minor && this.patch == version.patch;
        }

        public Version(int major, int minor, int patch) {
            this.major = major;
            this.minor = minor;
            this.patch = patch;
        }

        public /* synthetic */ Version(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, (i4 & 4) != 0 ? 0 : i3);
        }

        @NotNull
        public final String asString() {
            return this.patch == 0 ? new StringBuilder().append(this.major).append('.').append(this.minor).toString() : new StringBuilder().append(this.major).append('.').append(this.minor).append('.').append(this.patch).toString();
        }

        @NotNull
        public String toString() {
            return asString();
        }

        /* compiled from: VersionRequirement.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/VersionRequirement$Version$Companion.class */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            private Companion() {
            }

            @NotNull
            public final Version decode(@Nullable Integer version, @Nullable Integer versionFull) {
                if (versionFull != null) {
                    return new Version(versionFull.intValue() & 255, (versionFull.intValue() >> 8) & 255, (versionFull.intValue() >> 16) & 255);
                }
                if (version != null) {
                    return new Version(version.intValue() & 7, (version.intValue() >> 3) & 15, (version.intValue() >> 7) & 127);
                }
                return Version.INFINITY;
            }
        }
    }

    @NotNull
    public String toString() {
        return "since " + this.version + ' ' + this.level + (this.errorCode != null ? Intrinsics.stringPlus(" error ", this.errorCode) : "") + (this.message != null ? Intrinsics.stringPlus(": ", this.message) : "");
    }

    /* compiled from: VersionRequirement.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/VersionRequirement$Companion.class */
    public static final class Companion {

        /* compiled from: VersionRequirement.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/VersionRequirement$Companion$WhenMappings.class */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ProtoBuf.VersionRequirement.Level.values().length];
                iArr[ProtoBuf.VersionRequirement.Level.WARNING.ordinal()] = 1;
                iArr[ProtoBuf.VersionRequirement.Level.ERROR.ordinal()] = 2;
                iArr[ProtoBuf.VersionRequirement.Level.HIDDEN.ordinal()] = 3;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final List<VersionRequirement> create(@NotNull MessageLite proto, @NotNull NameResolver nameResolver, @NotNull VersionRequirementTable table) {
            Iterable versionRequirementList;
            Intrinsics.checkNotNullParameter(proto, "proto");
            Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
            Intrinsics.checkNotNullParameter(table, "table");
            if (proto instanceof ProtoBuf.Class) {
                versionRequirementList = ((ProtoBuf.Class) proto).getVersionRequirementList();
            } else if (proto instanceof ProtoBuf.Constructor) {
                versionRequirementList = ((ProtoBuf.Constructor) proto).getVersionRequirementList();
            } else if (proto instanceof ProtoBuf.Function) {
                versionRequirementList = ((ProtoBuf.Function) proto).getVersionRequirementList();
            } else if (proto instanceof ProtoBuf.Property) {
                versionRequirementList = ((ProtoBuf.Property) proto).getVersionRequirementList();
            } else {
                if (!(proto instanceof ProtoBuf.TypeAlias)) {
                    throw new IllegalStateException(Intrinsics.stringPlus("Unexpected declaration: ", proto.getClass()));
                }
                versionRequirementList = ((ProtoBuf.TypeAlias) proto).getVersionRequirementList();
            }
            Iterable ids = versionRequirementList;
            Intrinsics.checkNotNullExpressionValue(ids, "ids");
            Iterable $this$mapNotNull$iv = ids;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Integer id = (Integer) element$iv$iv$iv;
                Companion companion = VersionRequirement.Companion;
                Intrinsics.checkNotNullExpressionValue(id, "id");
                VersionRequirement versionRequirementCreate = companion.create(id.intValue(), nameResolver, table);
                if (versionRequirementCreate != null) {
                    destination$iv$iv.add(versionRequirementCreate);
                }
            }
            return (List) destination$iv$iv;
        }

        @Nullable
        public final VersionRequirement create(int id, @NotNull NameResolver nameResolver, @NotNull VersionRequirementTable table) {
            DeprecationLevel deprecationLevel;
            Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
            Intrinsics.checkNotNullParameter(table, "table");
            ProtoBuf.VersionRequirement info = table.get(id);
            if (info == null) {
                return null;
            }
            Version version = Version.Companion.decode(info.hasVersion() ? Integer.valueOf(info.getVersion()) : null, info.hasVersionFull() ? Integer.valueOf(info.getVersionFull()) : null);
            ProtoBuf.VersionRequirement.Level level = info.getLevel();
            Intrinsics.checkNotNull(level);
            switch (WhenMappings.$EnumSwitchMapping$0[level.ordinal()]) {
                case 1:
                    deprecationLevel = DeprecationLevel.WARNING;
                    break;
                case 2:
                    deprecationLevel = DeprecationLevel.ERROR;
                    break;
                case 3:
                    deprecationLevel = DeprecationLevel.HIDDEN;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            DeprecationLevel level2 = deprecationLevel;
            Integer errorCode = info.hasErrorCode() ? Integer.valueOf(info.getErrorCode()) : null;
            String message = info.hasMessage() ? nameResolver.getString(info.getMessage()) : null;
            ProtoBuf.VersionRequirement.VersionKind versionKind = info.getVersionKind();
            Intrinsics.checkNotNullExpressionValue(versionKind, "info.versionKind");
            return new VersionRequirement(version, versionKind, level2, errorCode, message);
        }
    }
}
