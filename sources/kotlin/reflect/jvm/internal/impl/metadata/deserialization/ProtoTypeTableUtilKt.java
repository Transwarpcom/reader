package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: protoTypeTableUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/deserialization/ProtoTypeTableUtilKt.class */
public final class ProtoTypeTableUtilKt {
    @NotNull
    public static final List<ProtoBuf.Type> supertypes(@NotNull ProtoBuf.Class $this$supertypes, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$supertypes, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List<ProtoBuf.Type> supertypeList = $this$supertypes.getSupertypeList();
        List<ProtoBuf.Type> p0 = supertypeList;
        List<ProtoBuf.Type> list = !p0.isEmpty() ? supertypeList : null;
        if (list != null) {
            return list;
        }
        Iterable supertypeIdList = $this$supertypes.getSupertypeIdList();
        Intrinsics.checkNotNullExpressionValue(supertypeIdList, "supertypeIdList");
        Iterable $this$map$iv = supertypeIdList;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Integer it = (Integer) item$iv$iv;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            destination$iv$iv.add(typeTable.get(it.intValue()));
        }
        return (List) destination$iv$iv;
    }

    @Nullable
    public static final ProtoBuf.Type type(@NotNull ProtoBuf.Type.Argument $this$type, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$type, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$type.hasType()) {
            return $this$type.getType();
        }
        if ($this$type.hasTypeId()) {
            return typeTable.get($this$type.getTypeId());
        }
        return null;
    }

    @Nullable
    public static final ProtoBuf.Type flexibleUpperBound(@NotNull ProtoBuf.Type $this$flexibleUpperBound, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$flexibleUpperBound, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$flexibleUpperBound.hasFlexibleUpperBound()) {
            return $this$flexibleUpperBound.getFlexibleUpperBound();
        }
        if ($this$flexibleUpperBound.hasFlexibleUpperBoundId()) {
            return typeTable.get($this$flexibleUpperBound.getFlexibleUpperBoundId());
        }
        return null;
    }

    @NotNull
    public static final List<ProtoBuf.Type> upperBounds(@NotNull ProtoBuf.TypeParameter $this$upperBounds, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$upperBounds, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List<ProtoBuf.Type> upperBoundList = $this$upperBounds.getUpperBoundList();
        List<ProtoBuf.Type> p0 = upperBoundList;
        List<ProtoBuf.Type> list = !p0.isEmpty() ? upperBoundList : null;
        if (list != null) {
            return list;
        }
        Iterable upperBoundIdList = $this$upperBounds.getUpperBoundIdList();
        Intrinsics.checkNotNullExpressionValue(upperBoundIdList, "upperBoundIdList");
        Iterable $this$map$iv = upperBoundIdList;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Integer it = (Integer) item$iv$iv;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            destination$iv$iv.add(typeTable.get(it.intValue()));
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final ProtoBuf.Type returnType(@NotNull ProtoBuf.Function $this$returnType, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$returnType, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$returnType.hasReturnType()) {
            ProtoBuf.Type returnType = $this$returnType.getReturnType();
            Intrinsics.checkNotNullExpressionValue(returnType, "returnType");
            return returnType;
        }
        if ($this$returnType.hasReturnTypeId()) {
            return typeTable.get($this$returnType.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Function".toString());
    }

    public static final boolean hasReceiver(@NotNull ProtoBuf.Function $this$hasReceiver) {
        Intrinsics.checkNotNullParameter($this$hasReceiver, "<this>");
        return $this$hasReceiver.hasReceiverType() || $this$hasReceiver.hasReceiverTypeId();
    }

    @Nullable
    public static final ProtoBuf.Type receiverType(@NotNull ProtoBuf.Function $this$receiverType, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$receiverType, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$receiverType.hasReceiverType()) {
            return $this$receiverType.getReceiverType();
        }
        if ($this$receiverType.hasReceiverTypeId()) {
            return typeTable.get($this$receiverType.getReceiverTypeId());
        }
        return null;
    }

    @NotNull
    public static final ProtoBuf.Type returnType(@NotNull ProtoBuf.Property $this$returnType, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$returnType, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$returnType.hasReturnType()) {
            ProtoBuf.Type returnType = $this$returnType.getReturnType();
            Intrinsics.checkNotNullExpressionValue(returnType, "returnType");
            return returnType;
        }
        if ($this$returnType.hasReturnTypeId()) {
            return typeTable.get($this$returnType.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Property".toString());
    }

    public static final boolean hasReceiver(@NotNull ProtoBuf.Property $this$hasReceiver) {
        Intrinsics.checkNotNullParameter($this$hasReceiver, "<this>");
        return $this$hasReceiver.hasReceiverType() || $this$hasReceiver.hasReceiverTypeId();
    }

    @Nullable
    public static final ProtoBuf.Type receiverType(@NotNull ProtoBuf.Property $this$receiverType, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$receiverType, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$receiverType.hasReceiverType()) {
            return $this$receiverType.getReceiverType();
        }
        if ($this$receiverType.hasReceiverTypeId()) {
            return typeTable.get($this$receiverType.getReceiverTypeId());
        }
        return null;
    }

    @NotNull
    public static final ProtoBuf.Type type(@NotNull ProtoBuf.ValueParameter $this$type, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$type, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$type.hasType()) {
            ProtoBuf.Type type = $this$type.getType();
            Intrinsics.checkNotNullExpressionValue(type, "type");
            return type;
        }
        if ($this$type.hasTypeId()) {
            return typeTable.get($this$type.getTypeId());
        }
        throw new IllegalStateException("No type in ProtoBuf.ValueParameter".toString());
    }

    @Nullable
    public static final ProtoBuf.Type varargElementType(@NotNull ProtoBuf.ValueParameter $this$varargElementType, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$varargElementType, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$varargElementType.hasVarargElementType()) {
            return $this$varargElementType.getVarargElementType();
        }
        if ($this$varargElementType.hasVarargElementTypeId()) {
            return typeTable.get($this$varargElementType.getVarargElementTypeId());
        }
        return null;
    }

    @Nullable
    public static final ProtoBuf.Type outerType(@NotNull ProtoBuf.Type $this$outerType, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$outerType, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$outerType.hasOuterType()) {
            return $this$outerType.getOuterType();
        }
        if ($this$outerType.hasOuterTypeId()) {
            return typeTable.get($this$outerType.getOuterTypeId());
        }
        return null;
    }

    @Nullable
    public static final ProtoBuf.Type abbreviatedType(@NotNull ProtoBuf.Type $this$abbreviatedType, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$abbreviatedType, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$abbreviatedType.hasAbbreviatedType()) {
            return $this$abbreviatedType.getAbbreviatedType();
        }
        if ($this$abbreviatedType.hasAbbreviatedTypeId()) {
            return typeTable.get($this$abbreviatedType.getAbbreviatedTypeId());
        }
        return null;
    }

    @NotNull
    public static final ProtoBuf.Type underlyingType(@NotNull ProtoBuf.TypeAlias $this$underlyingType, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$underlyingType, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$underlyingType.hasUnderlyingType()) {
            ProtoBuf.Type underlyingType = $this$underlyingType.getUnderlyingType();
            Intrinsics.checkNotNullExpressionValue(underlyingType, "underlyingType");
            return underlyingType;
        }
        if ($this$underlyingType.hasUnderlyingTypeId()) {
            return typeTable.get($this$underlyingType.getUnderlyingTypeId());
        }
        throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias".toString());
    }

    @NotNull
    public static final ProtoBuf.Type expandedType(@NotNull ProtoBuf.TypeAlias $this$expandedType, @NotNull TypeTable typeTable) {
        Intrinsics.checkNotNullParameter($this$expandedType, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if ($this$expandedType.hasExpandedType()) {
            ProtoBuf.Type expandedType = $this$expandedType.getExpandedType();
            Intrinsics.checkNotNullExpressionValue(expandedType, "expandedType");
            return expandedType;
        }
        if ($this$expandedType.hasExpandedTypeId()) {
            return typeTable.get($this$expandedType.getExpandedTypeId());
        }
        throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias".toString());
    }
}
