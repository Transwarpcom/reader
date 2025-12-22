package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ProtoContainer.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/ProtoContainer.class */
public abstract class ProtoContainer {

    @NotNull
    private final NameResolver nameResolver;

    @NotNull
    private final TypeTable typeTable;

    @Nullable
    private final SourceElement source;

    @NotNull
    public abstract FqName debugFqName();

    public /* synthetic */ ProtoContainer(NameResolver nameResolver, TypeTable typeTable, SourceElement source, DefaultConstructorMarker $constructor_marker) {
        this(nameResolver, typeTable, source);
    }

    private ProtoContainer(NameResolver nameResolver, TypeTable typeTable, SourceElement source) {
        this.nameResolver = nameResolver;
        this.typeTable = typeTable;
        this.source = source;
    }

    @NotNull
    public final NameResolver getNameResolver() {
        return this.nameResolver;
    }

    @NotNull
    public final TypeTable getTypeTable() {
        return this.typeTable;
    }

    @Nullable
    public final SourceElement getSource() {
        return this.source;
    }

    /* compiled from: ProtoContainer.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/ProtoContainer$Class.class */
    public static final class Class extends ProtoContainer {

        @NotNull
        private final ProtoBuf.Class classProto;

        @Nullable
        private final Class outerClass;

        @NotNull
        private final ClassId classId;

        @NotNull
        private final ProtoBuf.Class.Kind kind;
        private final boolean isInner;

        @NotNull
        public final ProtoBuf.Class getClassProto() {
            return this.classProto;
        }

        @Nullable
        public final Class getOuterClass() {
            return this.outerClass;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Class(@NotNull ProtoBuf.Class classProto, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, @Nullable SourceElement source, @Nullable Class outerClass) {
            super(nameResolver, typeTable, source, null);
            Intrinsics.checkNotNullParameter(classProto, "classProto");
            Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
            Intrinsics.checkNotNullParameter(typeTable, "typeTable");
            this.classProto = classProto;
            this.outerClass = outerClass;
            this.classId = NameResolverUtilKt.getClassId(nameResolver, this.classProto.getFqName());
            ProtoBuf.Class.Kind kind = Flags.CLASS_KIND.get(this.classProto.getFlags());
            this.kind = kind == null ? ProtoBuf.Class.Kind.CLASS : kind;
            Boolean bool = Flags.IS_INNER.get(this.classProto.getFlags());
            Intrinsics.checkNotNullExpressionValue(bool, "IS_INNER.get(classProto.flags)");
            this.isInner = bool.booleanValue();
        }

        @NotNull
        public final ClassId getClassId() {
            return this.classId;
        }

        @NotNull
        public final ProtoBuf.Class.Kind getKind() {
            return this.kind;
        }

        public final boolean isInner() {
            return this.isInner;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer
        @NotNull
        public FqName debugFqName() {
            FqName fqNameAsSingleFqName = this.classId.asSingleFqName();
            Intrinsics.checkNotNullExpressionValue(fqNameAsSingleFqName, "classId.asSingleFqName()");
            return fqNameAsSingleFqName;
        }
    }

    /* compiled from: ProtoContainer.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/ProtoContainer$Package.class */
    public static final class Package extends ProtoContainer {

        @NotNull
        private final FqName fqName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Package(@NotNull FqName fqName, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, @Nullable SourceElement source) {
            super(nameResolver, typeTable, source, null);
            Intrinsics.checkNotNullParameter(fqName, "fqName");
            Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
            Intrinsics.checkNotNullParameter(typeTable, "typeTable");
            this.fqName = fqName;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer
        @NotNull
        public FqName debugFqName() {
            return this.fqName;
        }
    }

    @NotNull
    public String toString() {
        return ((Object) getClass().getSimpleName()) + ": " + debugFqName();
    }
}
