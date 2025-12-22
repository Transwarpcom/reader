package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin._Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeSubstitution.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/IndexedParametersSubstitution.class */
public final class IndexedParametersSubstitution extends TypeSubstitution {

    @NotNull
    private final TypeParameterDescriptor[] parameters;

    @NotNull
    private final TypeProjection[] arguments;
    private final boolean approximateContravariantCapturedTypes;

    public /* synthetic */ IndexedParametersSubstitution(TypeParameterDescriptor[] typeParameterDescriptorArr, TypeProjection[] typeProjectionArr, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeParameterDescriptorArr, typeProjectionArr, (i & 4) != 0 ? false : z);
    }

    @NotNull
    public final TypeParameterDescriptor[] getParameters() {
        return this.parameters;
    }

    @NotNull
    public final TypeProjection[] getArguments() {
        return this.arguments;
    }

    public IndexedParametersSubstitution(@NotNull TypeParameterDescriptor[] parameters, @NotNull TypeProjection[] arguments, boolean approximateContravariantCapturedTypes) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        this.parameters = parameters;
        this.arguments = arguments;
        this.approximateContravariantCapturedTypes = approximateContravariantCapturedTypes;
        boolean z = this.parameters.length <= this.arguments.length;
        if (!_Assertions.ENABLED || z) {
        } else {
            throw new AssertionError("Number of arguments should not be less than number of parameters, but: parameters=" + getParameters().length + ", args=" + getArguments().length);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public IndexedParametersSubstitution(@NotNull List<? extends TypeParameterDescriptor> parameters, @NotNull List<? extends TypeProjection> argumentsList) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(argumentsList, "argumentsList");
        List<? extends TypeParameterDescriptor> $this$toTypedArray$iv = parameters;
        Object[] array = $this$toTypedArray$iv.toArray(new TypeParameterDescriptor[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        TypeParameterDescriptor[] typeParameterDescriptorArr = (TypeParameterDescriptor[]) array;
        List<? extends TypeProjection> $this$toTypedArray$iv2 = argumentsList;
        Object[] array2 = $this$toTypedArray$iv2.toArray(new TypeProjection[0]);
        if (array2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        this(typeParameterDescriptorArr, (TypeProjection[]) array2, false, 4, null);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return this.arguments.length == 0;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean approximateContravariantCapturedTypes() {
        return this.approximateContravariantCapturedTypes;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    @Nullable
    /* renamed from: get */
    public TypeProjection mo3923get(@NotNull KotlinType key) {
        int index;
        Intrinsics.checkNotNullParameter(key, "key");
        ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = key.getConstructor().mo3831getDeclarationDescriptor();
        TypeParameterDescriptor parameter = classifierDescriptorMo3831getDeclarationDescriptor instanceof TypeParameterDescriptor ? (TypeParameterDescriptor) classifierDescriptorMo3831getDeclarationDescriptor : null;
        if (parameter != null && (index = parameter.getIndex()) < this.parameters.length && Intrinsics.areEqual(this.parameters[index].getTypeConstructor(), parameter.getTypeConstructor())) {
            return this.arguments[index];
        }
        return null;
    }
}
