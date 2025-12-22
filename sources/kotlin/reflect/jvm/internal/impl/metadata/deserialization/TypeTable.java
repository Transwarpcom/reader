package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeTable.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/TypeTable.class */
public final class TypeTable {

    @NotNull
    private final List<ProtoBuf.Type> types;

    public TypeTable(@NotNull ProtoBuf.TypeTable typeTable) {
        List list;
        ProtoBuf.Type typeBuild;
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List originalTypes = typeTable.getTypeList();
        if (typeTable.hasFirstNullable()) {
            int firstNullable = typeTable.getFirstNullable();
            Iterable typeList = typeTable.getTypeList();
            Intrinsics.checkNotNullExpressionValue(typeList, "typeTable.typeList");
            Iterable $this$mapIndexed$iv = typeList;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
            int index$iv$iv = 0;
            for (Object item$iv$iv : $this$mapIndexed$iv) {
                int i = index$iv$iv;
                index$iv$iv++;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                ProtoBuf.Type type = (ProtoBuf.Type) item$iv$iv;
                if (i >= firstNullable) {
                    typeBuild = type.toBuilder().setNullable(true).build();
                } else {
                    typeBuild = type;
                }
                destination$iv$iv.add(typeBuild);
            }
            list = (List) destination$iv$iv;
        } else {
            list = originalTypes;
        }
        List list2 = list;
        Intrinsics.checkNotNullExpressionValue(list2, "run {\n        val originalTypes = typeTable.typeList\n        if (typeTable.hasFirstNullable()) {\n            val firstNullable = typeTable.firstNullable\n            typeTable.typeList.mapIndexed { i, type ->\n                if (i >= firstNullable) {\n                    type.toBuilder().setNullable(true).build()\n                } else type\n            }\n        } else originalTypes\n    }");
        this.types = list2;
    }

    @NotNull
    public final ProtoBuf.Type get(int index) {
        return this.types.get(index);
    }
}
