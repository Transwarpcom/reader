package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

/* compiled from: AnnotationTypeQualifierResolver.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/AnnotationTypeQualifierResolverKt.class */
public final class AnnotationTypeQualifierResolverKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isAnnotatedWithTypeQualifier(ClassDescriptor $this$isAnnotatedWithTypeQualifier) {
        return AnnotationQualifiersFqNamesKt.getBUILT_IN_TYPE_QUALIFIER_FQ_NAMES().contains(DescriptorUtilsKt.getFqNameSafe($this$isAnnotatedWithTypeQualifier)) || $this$isAnnotatedWithTypeQualifier.getAnnotations().hasAnnotation(AnnotationQualifiersFqNamesKt.getTYPE_QUALIFIER_FQNAME());
    }
}
