package kotlin.reflect.jvm;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: KTypesJvm.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u001c\u0010��\u001a\u0006\u0012\u0002\b\u00030\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\"\u0010��\u001a\u0006\u0012\u0002\b\u00030\u0001*\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0003\u0010\b¨\u0006\t"}, d2 = {"jvmErasure", "Lkotlin/reflect/KClass;", "Lkotlin/reflect/KClassifier;", "getJvmErasure", "(Lkotlin/reflect/KClassifier;)Lkotlin/reflect/KClass;", "Lkotlin/reflect/KType;", "getJvmErasure$annotations", "(Lkotlin/reflect/KType;)V", "(Lkotlin/reflect/KType;)Lkotlin/reflect/KClass;", "kotlin-reflection"})
@JvmName(name = "KTypesJvm")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/KTypesJvm.class */
public final class KTypesJvm {
    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getJvmErasure$annotations(KType kType) {
    }

    @NotNull
    public static final KClass<?> getJvmErasure(@NotNull KType jvmErasure) {
        Intrinsics.checkNotNullParameter(jvmErasure, "$this$jvmErasure");
        KClassifier classifier = jvmErasure.getClassifier();
        if (classifier != null) {
            KClass<?> jvmErasure2 = getJvmErasure(classifier);
            if (jvmErasure2 != null) {
                return jvmErasure2;
            }
        }
        throw new KotlinReflectionInternalError("Cannot calculate JVM erasure for type: " + jvmErasure);
    }

    @NotNull
    public static final KClass<?> getJvmErasure(@NotNull KClassifier jvmErasure) {
        Object obj;
        Intrinsics.checkNotNullParameter(jvmErasure, "$this$jvmErasure");
        if (jvmErasure instanceof KClass) {
            return (KClass) jvmErasure;
        }
        if (jvmErasure instanceof KTypeParameter) {
            List bounds = ((KTypeParameter) jvmErasure).getUpperBounds();
            List $this$firstOrNull$iv = bounds;
            Iterator it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    KType it2 = (KType) element$iv;
                    if (it2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
                    }
                    ClassifierDescriptor classifierDescriptorMo3831getDeclarationDescriptor = ((KTypeImpl) it2).getType().getConstructor().mo3831getDeclarationDescriptor();
                    if (!(classifierDescriptorMo3831getDeclarationDescriptor instanceof ClassDescriptor)) {
                        classifierDescriptorMo3831getDeclarationDescriptor = null;
                    }
                    ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptorMo3831getDeclarationDescriptor;
                    if ((classDescriptor == null || classDescriptor.getKind() == ClassKind.INTERFACE || classDescriptor.getKind() == ClassKind.ANNOTATION_CLASS) ? false : true) {
                        obj = element$iv;
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            KType kType = (KType) obj;
            if (kType == null) {
                kType = (KType) CollectionsKt.firstOrNull(bounds);
            }
            KType representativeBound = kType;
            if (representativeBound != null) {
                KClass<?> jvmErasure2 = getJvmErasure(representativeBound);
                if (jvmErasure2 != null) {
                    return jvmErasure2;
                }
            }
            return Reflection.getOrCreateKotlinClass(Object.class);
        }
        throw new KotlinReflectionInternalError("Cannot calculate JVM erasure for type: " + jvmErasure);
    }
}
