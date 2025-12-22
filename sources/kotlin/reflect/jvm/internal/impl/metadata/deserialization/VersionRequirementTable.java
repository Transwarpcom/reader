package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: VersionRequirement.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/VersionRequirementTable.class */
public final class VersionRequirementTable {

    @NotNull
    private final List<ProtoBuf.VersionRequirement> infos;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final VersionRequirementTable EMPTY = new VersionRequirementTable(CollectionsKt.emptyList());

    public /* synthetic */ VersionRequirementTable(List infos, DefaultConstructorMarker $constructor_marker) {
        this(infos);
    }

    private VersionRequirementTable(List<ProtoBuf.VersionRequirement> list) {
        this.infos = list;
    }

    @Nullable
    public final ProtoBuf.VersionRequirement get(int id) {
        return (ProtoBuf.VersionRequirement) CollectionsKt.getOrNull(this.infos, id);
    }

    /* compiled from: VersionRequirement.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/VersionRequirementTable$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final VersionRequirementTable getEMPTY() {
            return VersionRequirementTable.EMPTY;
        }

        @NotNull
        public final VersionRequirementTable create(@NotNull ProtoBuf.VersionRequirementTable table) {
            Intrinsics.checkNotNullParameter(table, "table");
            if (table.getRequirementCount() == 0) {
                return getEMPTY();
            }
            List<ProtoBuf.VersionRequirement> requirementList = table.getRequirementList();
            Intrinsics.checkNotNullExpressionValue(requirementList, "table.requirementList");
            return new VersionRequirementTable(requirementList, null);
        }
    }
}
