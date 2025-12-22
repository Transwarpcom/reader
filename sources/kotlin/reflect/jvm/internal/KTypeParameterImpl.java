package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeParameterReference;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.ReflectProperties;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: KTypeParameterImpl.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b��\u0018��2\u00020\u00012\u00020\u0002B\u0017\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\u001c\u001a\u00020\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0096\u0002J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u000eH\u0016J\u0010\u0010\"\u001a\u0006\u0012\u0002\b\u00030#*\u00020$H\u0002J\u0010\u0010%\u001a\u0006\u0012\u0002\b\u00030&*\u00020'H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006("}, d2 = {"Lkotlin/reflect/jvm/internal/KTypeParameterImpl;", "Lkotlin/reflect/KTypeParameter;", "Lkotlin/reflect/jvm/internal/KClassifierImpl;", "container", "Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/TypeParameterDescriptor;", "(Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "isReified", "", "()Z", "name", "", "getName", "()Ljava/lang/String;", "upperBounds", "", "Lkotlin/reflect/KType;", "getUpperBounds", "()Ljava/util/List;", "upperBounds$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "variance", "Lkotlin/reflect/KVariance;", "getVariance", "()Lkotlin/reflect/KVariance;", "equals", "other", "", IdentityNamingStrategy.HASH_CODE_KEY, "", "toString", "getContainerClass", "Ljava/lang/Class;", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedMemberDescriptor;", "toKClassImpl", "Lkotlin/reflect/jvm/internal/KClassImpl;", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KTypeParameterImpl.class */
public final class KTypeParameterImpl implements KTypeParameter, KClassifierImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KTypeParameterImpl.class), "upperBounds", "getUpperBounds()Ljava/util/List;"))};

    @NotNull
    private final ReflectProperties.LazySoftVal upperBounds$delegate;
    private final KTypeParameterOwnerImpl container;

    @NotNull
    private final TypeParameterDescriptor descriptor;

    @Override // kotlin.reflect.KTypeParameter
    @NotNull
    public List<KType> getUpperBounds() {
        return (List) this.upperBounds$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public KTypeParameterImpl(@Nullable KTypeParameterOwnerImpl container, @NotNull TypeParameterDescriptor descriptor) {
        KClassImpl kClassImpl;
        Object objAccept;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.descriptor = descriptor;
        this.upperBounds$delegate = ReflectProperties.lazySoft(new Function0<List<? extends KTypeImpl>>() { // from class: kotlin.reflect.jvm.internal.KTypeParameterImpl$upperBounds$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends KTypeImpl> invoke() {
                Iterable upperBounds = this.this$0.getDescriptor().getUpperBounds();
                Intrinsics.checkNotNullExpressionValue(upperBounds, "descriptor.upperBounds");
                Iterable $this$map$iv = upperBounds;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    KotlinType p1 = (KotlinType) item$iv$iv;
                    destination$iv$iv.add(new KTypeImpl(p1, null, 2, null));
                }
                return (List) destination$iv$iv;
            }
        });
        KTypeParameterImpl kTypeParameterImpl = this;
        KTypeParameterOwnerImpl kTypeParameterOwnerImpl = container;
        if (kTypeParameterOwnerImpl == null) {
            KTypeParameterImpl $this$run = this;
            DeclarationDescriptor declaration = $this$run.getDescriptor().getContainingDeclaration();
            Intrinsics.checkNotNullExpressionValue(declaration, "descriptor.containingDeclaration");
            if (declaration instanceof ClassDescriptor) {
                objAccept = $this$run.toKClassImpl((ClassDescriptor) declaration);
            } else if (declaration instanceof CallableMemberDescriptor) {
                DeclarationDescriptor callableContainer = ((CallableMemberDescriptor) declaration).getContainingDeclaration();
                Intrinsics.checkNotNullExpressionValue(callableContainer, "declaration.containingDeclaration");
                if (callableContainer instanceof ClassDescriptor) {
                    kClassImpl = $this$run.toKClassImpl((ClassDescriptor) callableContainer);
                } else {
                    DeclarationDescriptor declarationDescriptor = declaration;
                    DeserializedMemberDescriptor deserializedMember = (DeserializedMemberDescriptor) (declarationDescriptor instanceof DeserializedMemberDescriptor ? declarationDescriptor : null);
                    if (deserializedMember == null) {
                        throw new KotlinReflectionInternalError("Non-class callable descriptor must be deserialized: " + declaration);
                    }
                    KClass kotlinClass = JvmClassMappingKt.getKotlinClass($this$run.getContainerClass(deserializedMember));
                    if (kotlinClass == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>");
                    }
                    kClassImpl = (KClassImpl) kotlinClass;
                }
                KClassImpl callableContainerClass = kClassImpl;
                objAccept = declaration.accept(new CreateKCallableVisitor(callableContainerClass), Unit.INSTANCE);
            } else {
                throw new KotlinReflectionInternalError("Unknown type parameter container: " + declaration);
            }
            Intrinsics.checkNotNullExpressionValue(objAccept, "when (val declaration = … $declaration\")\n        }");
            KTypeParameterOwnerImpl kTypeParameterOwnerImpl2 = (KTypeParameterOwnerImpl) objAccept;
            kTypeParameterImpl = kTypeParameterImpl;
            kTypeParameterOwnerImpl = kTypeParameterOwnerImpl2;
        }
        kTypeParameterImpl.container = kTypeParameterOwnerImpl;
    }

    @Override // kotlin.reflect.jvm.internal.KClassifierImpl
    @NotNull
    public TypeParameterDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlin.reflect.KTypeParameter
    @NotNull
    public String getName() {
        String strAsString = getDescriptor().getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "descriptor.name.asString()");
        return strAsString;
    }

    @Override // kotlin.reflect.KTypeParameter
    @NotNull
    public KVariance getVariance() {
        switch (getDescriptor().getVariance()) {
            case INVARIANT:
                return KVariance.INVARIANT;
            case IN_VARIANCE:
                return KVariance.IN;
            case OUT_VARIANCE:
                return KVariance.OUT;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @Override // kotlin.reflect.KTypeParameter
    public boolean isReified() {
        return getDescriptor().isReified();
    }

    private final KClassImpl<?> toKClassImpl(ClassDescriptor $this$toKClassImpl) {
        Class<?> javaClass = UtilKt.toJavaClass($this$toKClassImpl);
        KClassImpl<?> kClassImpl = (KClassImpl) (javaClass != null ? JvmClassMappingKt.getKotlinClass(javaClass) : null);
        if (kClassImpl != null) {
            return kClassImpl;
        }
        throw new KotlinReflectionInternalError("Type parameter container is not resolved: " + $this$toKClassImpl.getContainingDeclaration());
    }

    private final Class<?> getContainerClass(DeserializedMemberDescriptor $this$getContainerClass) {
        Object $this$safeAs$iv = $this$getContainerClass.getContainerSource();
        Object obj = $this$safeAs$iv;
        if (!(obj instanceof JvmPackagePartSource)) {
            obj = null;
        }
        JvmPackagePartSource jvmPackagePartSource = (JvmPackagePartSource) obj;
        Object $this$safeAs$iv2 = jvmPackagePartSource != null ? jvmPackagePartSource.getKnownJvmBinaryClass() : null;
        Object obj2 = $this$safeAs$iv2;
        if (!(obj2 instanceof ReflectKotlinClass)) {
            obj2 = null;
        }
        ReflectKotlinClass reflectKotlinClass = (ReflectKotlinClass) obj2;
        if (reflectKotlinClass != null) {
            Class<?> klass = reflectKotlinClass.getKlass();
            if (klass != null) {
                return klass;
            }
        }
        throw new KotlinReflectionInternalError("Container of deserialized member is not resolved: " + $this$getContainerClass);
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof KTypeParameterImpl) && Intrinsics.areEqual(this.container, ((KTypeParameterImpl) other).container) && Intrinsics.areEqual(getName(), ((KTypeParameterImpl) other).getName());
    }

    public int hashCode() {
        return (this.container.hashCode() * 31) + getName().hashCode();
    }

    @NotNull
    public String toString() {
        return TypeParameterReference.Companion.toString(this);
    }
}
