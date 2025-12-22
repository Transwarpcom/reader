package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.checker.SimpleClassicTypeSystemContext;
import org.jetbrains.annotations.NotNull;

/* compiled from: typeEnhancement.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/TypeEnhancementKt.class */
public final class TypeEnhancementKt {

    @NotNull
    private static final EnhancedTypeAnnotations ENHANCED_NULLABILITY_ANNOTATIONS;

    @NotNull
    private static final EnhancedTypeAnnotations ENHANCED_MUTABILITY_ANNOTATIONS;

    /* compiled from: typeEnhancement.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/TypeEnhancementKt$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[MutabilityQualifier.values().length];
            iArr[MutabilityQualifier.READ_ONLY.ordinal()] = 1;
            iArr[MutabilityQualifier.MUTABLE.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[NullabilityQualifier.values().length];
            iArr2[NullabilityQualifier.NULLABLE.ordinal()] = 1;
            iArr2[NullabilityQualifier.NOT_NULL.ordinal()] = 2;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final boolean hasEnhancedNullability(@NotNull KotlinType $this$hasEnhancedNullability) {
        Intrinsics.checkNotNullParameter($this$hasEnhancedNullability, "<this>");
        return TypeEnchancementUtilsKt.hasEnhancedNullability(SimpleClassicTypeSystemContext.INSTANCE, $this$hasEnhancedNullability);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Annotations compositeAnnotationsOrSingle(List<? extends Annotations> list) {
        switch (list.size()) {
            case 0:
                throw new IllegalStateException("At least one Annotations object expected".toString());
            case 1:
                return (Annotations) CollectionsKt.single((List) list);
            default:
                return new CompositeAnnotations((List<? extends Annotations>) CollectionsKt.toList(list));
        }
    }

    private static final <T> EnhancementResult<T> noChange(T t) {
        return new EnhancementResult<>(t, null);
    }

    private static final <T> EnhancementResult<T> enhancedNullability(T t) {
        return new EnhancementResult<>(t, ENHANCED_NULLABILITY_ANNOTATIONS);
    }

    private static final <T> EnhancementResult<T> enhancedMutability(T t) {
        return new EnhancementResult<>(t, ENHANCED_MUTABILITY_ANNOTATIONS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EnhancementResult<ClassifierDescriptor> enhanceMutability(ClassifierDescriptor $this$enhanceMutability, JavaTypeQualifiers qualifiers, TypeComponentPosition position) {
        if (TypeComponentPositionKt.shouldEnhance(position) && ($this$enhanceMutability instanceof ClassDescriptor)) {
            JavaToKotlinClassMapper mapper = JavaToKotlinClassMapper.INSTANCE;
            MutabilityQualifier mutability = qualifiers.getMutability();
            switch (mutability == null ? -1 : WhenMappings.$EnumSwitchMapping$0[mutability.ordinal()]) {
                case 1:
                    if (position == TypeComponentPosition.FLEXIBLE_LOWER && mapper.isMutable((ClassDescriptor) $this$enhanceMutability)) {
                        return enhancedMutability(mapper.convertMutableToReadOnly((ClassDescriptor) $this$enhanceMutability));
                    }
                    break;
                case 2:
                    if (position == TypeComponentPosition.FLEXIBLE_UPPER && mapper.isReadOnly((ClassDescriptor) $this$enhanceMutability)) {
                        return enhancedMutability(mapper.convertReadOnlyToMutable((ClassDescriptor) $this$enhanceMutability));
                    }
                    break;
            }
            return noChange($this$enhanceMutability);
        }
        return noChange($this$enhanceMutability);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EnhancementResult<Boolean> getEnhancedNullability(KotlinType $this$getEnhancedNullability, JavaTypeQualifiers qualifiers, TypeComponentPosition position) {
        if (!TypeComponentPositionKt.shouldEnhance(position)) {
            return noChange(Boolean.valueOf($this$getEnhancedNullability.isMarkedNullable()));
        }
        NullabilityQualifier nullability = qualifiers.getNullability();
        switch (nullability == null ? -1 : WhenMappings.$EnumSwitchMapping$1[nullability.ordinal()]) {
        }
        return noChange(Boolean.valueOf($this$getEnhancedNullability.isMarkedNullable()));
    }

    static {
        FqName ENHANCED_NULLABILITY_ANNOTATION = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        Intrinsics.checkNotNullExpressionValue(ENHANCED_NULLABILITY_ANNOTATION, "ENHANCED_NULLABILITY_ANNOTATION");
        ENHANCED_NULLABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(ENHANCED_NULLABILITY_ANNOTATION);
        FqName ENHANCED_MUTABILITY_ANNOTATION = JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION;
        Intrinsics.checkNotNullExpressionValue(ENHANCED_MUTABILITY_ANNOTATION, "ENHANCED_MUTABILITY_ANNOTATION");
        ENHANCED_MUTABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(ENHANCED_MUTABILITY_ANNOTATION);
    }
}
