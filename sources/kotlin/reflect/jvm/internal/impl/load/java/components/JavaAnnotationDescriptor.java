package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Collection;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaAnnotationMapper.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/components/JavaAnnotationDescriptor.class */
public class JavaAnnotationDescriptor implements AnnotationDescriptor, PossiblyExternalAnnotationDescriptor {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JavaAnnotationDescriptor.class), "type", "getType()Lorg/jetbrains/kotlin/types/SimpleType;"))};

    @NotNull
    private final FqName fqName;

    @NotNull
    private final SourceElement source;

    @NotNull
    private final NotNullLazyValue type$delegate;

    @Nullable
    private final JavaAnnotationArgument firstArgument;
    private final boolean isIdeExternalAnnotation;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v40, types: [java.lang.Object, kotlin.reflect.jvm.internal.impl.descriptors.SourceElement] */
    public JavaAnnotationDescriptor(@NotNull final LazyJavaResolverContext c, @Nullable JavaAnnotation annotation, @NotNull FqName fqName) {
        JavaSourceElement javaSourceElementSource;
        JavaSourceElement javaSourceElement;
        Collection<JavaAnnotationArgument> arguments;
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        this.fqName = fqName;
        JavaAnnotationDescriptor javaAnnotationDescriptor = this;
        if (annotation == null) {
            javaSourceElementSource = null;
        } else {
            javaAnnotationDescriptor = javaAnnotationDescriptor;
            javaSourceElementSource = c.getComponents().getSourceElementFactory().source(annotation);
        }
        JavaSourceElement javaSourceElement2 = javaSourceElementSource;
        if (javaSourceElement2 == null) {
            ?? NO_SOURCE = SourceElement.NO_SOURCE;
            Intrinsics.checkNotNullExpressionValue(NO_SOURCE, "NO_SOURCE");
            javaSourceElement = NO_SOURCE;
        } else {
            javaSourceElement = javaSourceElement2;
        }
        javaAnnotationDescriptor.source = javaSourceElement;
        this.type$delegate = c.getStorageManager().createLazyValue(new Function0<SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationDescriptor$type$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final SimpleType invoke() {
                SimpleType defaultType = c.getModule().getBuiltIns().getBuiltInClassByFqName(this.getFqName()).getDefaultType();
                Intrinsics.checkNotNullExpressionValue(defaultType, "c.module.builtIns.getBuiltInClassByFqName(fqName).defaultType");
                return defaultType;
            }
        });
        JavaAnnotationArgument javaAnnotationArgument = (annotation == null || (arguments = annotation.getArguments()) == null) ? null : (JavaAnnotationArgument) CollectionsKt.firstOrNull(arguments);
        this.firstArgument = javaAnnotationArgument;
        this.isIdeExternalAnnotation = Intrinsics.areEqual((Object) (annotation == null ? null : Boolean.valueOf(annotation.isIdeExternalAnnotation())), (Object) true);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    public FqName getFqName() {
        return this.fqName;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    public SourceElement getSource() {
        return this.source;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    public SimpleType getType() {
        return (SimpleType) StorageKt.getValue(this.type$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    @Nullable
    protected final JavaAnnotationArgument getFirstArgument() {
        return this.firstArgument;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    public Map<Name, ConstantValue<?>> getAllValueArguments() {
        return MapsKt.emptyMap();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor
    public boolean isIdeExternalAnnotation() {
        return this.isIdeExternalAnnotation;
    }
}
