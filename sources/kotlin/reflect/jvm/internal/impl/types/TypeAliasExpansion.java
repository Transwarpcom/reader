package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeAliasExpansion.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeAliasExpansion.class */
public final class TypeAliasExpansion {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @Nullable
    private final TypeAliasExpansion parent;

    @NotNull
    private final TypeAliasDescriptor descriptor;

    @NotNull
    private final List<TypeProjection> arguments;

    @NotNull
    private final Map<TypeParameterDescriptor, TypeProjection> mapping;

    public /* synthetic */ TypeAliasExpansion(TypeAliasExpansion parent, TypeAliasDescriptor descriptor, List arguments, Map mapping, DefaultConstructorMarker $constructor_marker) {
        this(parent, descriptor, arguments, mapping);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TypeAliasExpansion(TypeAliasExpansion parent, TypeAliasDescriptor descriptor, List<? extends TypeProjection> list, Map<TypeParameterDescriptor, ? extends TypeProjection> map) {
        this.parent = parent;
        this.descriptor = descriptor;
        this.arguments = list;
        this.mapping = map;
    }

    @NotNull
    public final TypeAliasDescriptor getDescriptor() {
        return this.descriptor;
    }

    @NotNull
    public final List<TypeProjection> getArguments() {
        return this.arguments;
    }

    @Nullable
    public final TypeProjection getReplacement(@NotNull TypeConstructor constructor) {
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        ClassifierDescriptor descriptor = constructor.mo3831getDeclarationDescriptor();
        if (descriptor instanceof TypeParameterDescriptor) {
            return this.mapping.get(descriptor);
        }
        return null;
    }

    public final boolean isRecursion(@NotNull TypeAliasDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (!Intrinsics.areEqual(this.descriptor, descriptor)) {
            TypeAliasExpansion typeAliasExpansion = this.parent;
            if (!(typeAliasExpansion == null ? false : typeAliasExpansion.isRecursion(descriptor))) {
                return false;
            }
        }
        return true;
    }

    /* compiled from: TypeAliasExpansion.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeAliasExpansion$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final TypeAliasExpansion create(@Nullable TypeAliasExpansion parent, @NotNull TypeAliasDescriptor typeAliasDescriptor, @NotNull List<? extends TypeProjection> arguments) {
            Intrinsics.checkNotNullParameter(typeAliasDescriptor, "typeAliasDescriptor");
            Intrinsics.checkNotNullParameter(arguments, "arguments");
            Iterable parameters = typeAliasDescriptor.getTypeConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "typeAliasDescriptor.typeConstructor.parameters");
            Iterable $this$map$iv = parameters;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                TypeParameterDescriptor it = (TypeParameterDescriptor) item$iv$iv;
                destination$iv$iv.add(it.getOriginal());
            }
            List typeParameters = (List) destination$iv$iv;
            Map mappedArguments = MapsKt.toMap(CollectionsKt.zip(typeParameters, arguments));
            return new TypeAliasExpansion(parent, typeAliasDescriptor, arguments, mappedArguments, null);
        }
    }
}
