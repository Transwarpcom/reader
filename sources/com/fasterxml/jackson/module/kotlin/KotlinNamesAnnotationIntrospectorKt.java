package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KAnnotatedElement;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.text.StringsKt;

/* compiled from: KotlinNamesAnnotationIntrospector.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��0\n��\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010��\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0003\u001a\f\u0010\t\u001a\u00020\u0007*\u00020\nH\u0002\u001a\u001e\u0010\u000b\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002\u001a\u001c\u0010\f\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0002¨\u0006\u000f"}, d2 = {"filterOutSingleStringCallables", "", "Lkotlin/reflect/KFunction;", "propertyNames", "", "", "isInlineClass", "", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMethod;", "isKotlinConstructorWithParameters", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedConstructor;", "isPossibleSingleString", "isPrimaryConstructor", "Lkotlin/reflect/KClass;", "kConstructor", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinNamesAnnotationIntrospectorKt.class */
public final class KotlinNamesAnnotationIntrospectorKt {
    @Deprecated(message = "To be removed in 2.14", replaceWith = @ReplaceWith(expression = "with(receiver) { declaringClass.declaredMethods.any { it.name.contains('-') } }", imports = {}))
    private static final boolean isInlineClass(AnnotatedMethod $this$isInlineClass) throws SecurityException {
        Object[] declaredMethods = $this$isInlineClass.getDeclaringClass().getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(declaredMethods, "declaringClass.declaredMethods");
        Object[] $this$any$iv = declaredMethods;
        for (Object element$iv : $this$any$iv) {
            Method it = (Method) element$iv;
            String name = it.getName();
            Intrinsics.checkNotNullExpressionValue(name, "it.name");
            if (StringsKt.contains$default((CharSequence) name, '-', false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isKotlinConstructorWithParameters(AnnotatedConstructor $this$isKotlinConstructorWithParameters) {
        if ($this$isKotlinConstructorWithParameters.getParameterCount() > 0) {
            Class<?> declaringClass = $this$isKotlinConstructorWithParameters.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "declaringClass");
            if (KotlinModuleKt.isKotlinClass(declaringClass) && !$this$isKotlinConstructorWithParameters.getDeclaringClass().isEnum()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isPossibleSingleString(KFunction<?> kFunction, Set<String> set) {
        Object obj;
        if (kFunction.getParameters().size() == 1 && !CollectionsKt.contains(set, kFunction.getParameters().get(0).getName()) && Intrinsics.areEqual(ReflectJvmMapping.getJavaType(kFunction.getParameters().get(0).getType()), String.class)) {
            KAnnotatedElement $this$hasAnnotation$iv = kFunction.getParameters().get(0);
            Iterable $this$firstOrNull$iv$iv$iv = $this$hasAnnotation$iv.getAnnotations();
            Iterator it = $this$firstOrNull$iv$iv$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv$iv$iv = it.next();
                    Annotation it$iv$iv = (Annotation) element$iv$iv$iv;
                    if (it$iv$iv instanceof JsonProperty) {
                        obj = element$iv$iv$iv;
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            if (!(((JsonProperty) obj) != null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection<KFunction<?>> filterOutSingleStringCallables(Collection<? extends KFunction<?>> collection, Set<String> set) {
        Collection<? extends KFunction<?>> $this$filter$iv = collection;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            KFunction it = (KFunction) element$iv$iv;
            if (!isPossibleSingleString(it, set)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isPrimaryConstructor(KClass<?> kClass, KFunction<?> kFunction) {
        KFunction it = KClasses.getPrimaryConstructor(kClass);
        return Intrinsics.areEqual(it, kFunction) || (it == null && kClass.getConstructors().size() == 1);
    }
}
