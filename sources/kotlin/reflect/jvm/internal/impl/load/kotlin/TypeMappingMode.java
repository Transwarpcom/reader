package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TypeMappingMode.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/TypeMappingMode.class */
public final class TypeMappingMode {
    private final boolean needPrimitiveBoxing;
    private final boolean needInlineClassWrapping;
    private final boolean isForAnnotationParameter;
    private final boolean skipDeclarationSiteWildcards;
    private final boolean skipDeclarationSiteWildcardsIfPossible;

    @Nullable
    private final TypeMappingMode genericArgumentMode;
    private final boolean kotlinCollectionsToJavaCollections;

    @Nullable
    private final TypeMappingMode genericContravariantArgumentMode;

    @Nullable
    private final TypeMappingMode genericInvariantArgumentMode;
    private final boolean mapTypeAliases;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    public static final TypeMappingMode GENERIC_ARGUMENT = new TypeMappingMode(false, false, false, false, false, null, false, null, null, false, 1023, null);

    @JvmField
    @NotNull
    public static final TypeMappingMode GENERIC_ARGUMENT_UAST = new TypeMappingMode(false, false, false, false, false, null, false, null, null, true, 511, null);

    @JvmField
    @NotNull
    public static final TypeMappingMode RETURN_TYPE_BOXED = new TypeMappingMode(false, true, false, false, false, null, false, null, null, false, 1021, null);

    @JvmField
    @NotNull
    public static final TypeMappingMode DEFAULT = new TypeMappingMode(false, false, false, false, false, GENERIC_ARGUMENT, false, null, null, false, 988, null);

    @JvmField
    @NotNull
    public static final TypeMappingMode DEFAULT_UAST = new TypeMappingMode(false, false, false, false, false, GENERIC_ARGUMENT_UAST, false, null, null, true, 476, null);

    @JvmField
    @NotNull
    public static final TypeMappingMode CLASS_DECLARATION = new TypeMappingMode(false, true, false, false, false, GENERIC_ARGUMENT, false, null, null, false, 988, null);

    @JvmField
    @NotNull
    public static final TypeMappingMode SUPER_TYPE = new TypeMappingMode(false, false, false, true, false, GENERIC_ARGUMENT, false, null, null, false, 983, null);

    @JvmField
    @NotNull
    public static final TypeMappingMode SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS = new TypeMappingMode(false, false, false, true, false, GENERIC_ARGUMENT, false, null, null, false, 919, null);

    @JvmField
    @NotNull
    public static final TypeMappingMode VALUE_FOR_ANNOTATION = new TypeMappingMode(false, false, true, false, false, GENERIC_ARGUMENT, false, null, null, false, 984, null);

    /* compiled from: TypeMappingMode.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/TypeMappingMode$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            iArr[Variance.IN_VARIANCE.ordinal()] = 1;
            iArr[Variance.INVARIANT.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public TypeMappingMode() {
        this(false, false, false, false, false, null, false, null, null, false, 1023, null);
    }

    public TypeMappingMode(boolean needPrimitiveBoxing, boolean needInlineClassWrapping, boolean isForAnnotationParameter, boolean skipDeclarationSiteWildcards, boolean skipDeclarationSiteWildcardsIfPossible, @Nullable TypeMappingMode genericArgumentMode, boolean kotlinCollectionsToJavaCollections, @Nullable TypeMappingMode genericContravariantArgumentMode, @Nullable TypeMappingMode genericInvariantArgumentMode, boolean mapTypeAliases) {
        this.needPrimitiveBoxing = needPrimitiveBoxing;
        this.needInlineClassWrapping = needInlineClassWrapping;
        this.isForAnnotationParameter = isForAnnotationParameter;
        this.skipDeclarationSiteWildcards = skipDeclarationSiteWildcards;
        this.skipDeclarationSiteWildcardsIfPossible = skipDeclarationSiteWildcardsIfPossible;
        this.genericArgumentMode = genericArgumentMode;
        this.kotlinCollectionsToJavaCollections = kotlinCollectionsToJavaCollections;
        this.genericContravariantArgumentMode = genericContravariantArgumentMode;
        this.genericInvariantArgumentMode = genericInvariantArgumentMode;
        this.mapTypeAliases = mapTypeAliases;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TypeMappingMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, boolean z6, TypeMappingMode typeMappingMode2, TypeMappingMode typeMappingMode3, boolean z7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        z = (i & 1) != 0 ? true : z;
        z2 = (i & 2) != 0 ? true : z2;
        z3 = (i & 4) != 0 ? false : z3;
        z4 = (i & 8) != 0 ? false : z4;
        z5 = (i & 16) != 0 ? false : z5;
        typeMappingMode = (i & 32) != 0 ? null : typeMappingMode;
        this(z, z2, z3, z4, z5, typeMappingMode, (i & 64) != 0 ? true : z6, (i & 128) != 0 ? typeMappingMode : typeMappingMode2, (i & 256) != 0 ? typeMappingMode : typeMappingMode3, (i & 512) != 0 ? false : z7);
    }

    public final boolean getNeedPrimitiveBoxing() {
        return this.needPrimitiveBoxing;
    }

    public final boolean getNeedInlineClassWrapping() {
        return this.needInlineClassWrapping;
    }

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    public final boolean getKotlinCollectionsToJavaCollections() {
        return this.kotlinCollectionsToJavaCollections;
    }

    public final boolean getMapTypeAliases() {
        return this.mapTypeAliases;
    }

    /* compiled from: TypeMappingMode.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/TypeMappingMode$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    @NotNull
    public final TypeMappingMode toGenericArgumentMode(@NotNull Variance effectiveVariance, boolean ofArray) {
        Intrinsics.checkNotNullParameter(effectiveVariance, "effectiveVariance");
        if (ofArray && this.isForAnnotationParameter) {
            return this;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[effectiveVariance.ordinal()]) {
            case 1:
                TypeMappingMode typeMappingMode = this.genericContravariantArgumentMode;
                return typeMappingMode == null ? this : typeMappingMode;
            case 2:
                TypeMappingMode typeMappingMode2 = this.genericInvariantArgumentMode;
                return typeMappingMode2 == null ? this : typeMappingMode2;
            default:
                TypeMappingMode typeMappingMode3 = this.genericArgumentMode;
                return typeMappingMode3 == null ? this : typeMappingMode3;
        }
    }

    @NotNull
    public final TypeMappingMode wrapInlineClassesMode() {
        return new TypeMappingMode(this.needPrimitiveBoxing, true, this.isForAnnotationParameter, this.skipDeclarationSiteWildcards, this.skipDeclarationSiteWildcardsIfPossible, this.genericArgumentMode, this.kotlinCollectionsToJavaCollections, this.genericContravariantArgumentMode, this.genericInvariantArgumentMode, false, 512, null);
    }
}
