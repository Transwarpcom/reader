package com.fasterxml.jackson.module.kotlin;

import ch.qos.logback.core.CoreConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.module.kotlin.ValueClassStaticJsonValueSerializer;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.full.KClassifiers;
import kotlin.reflect.jvm.ReflectJvmMapping;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinAnnotationIntrospector.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b��\u0018�� 92\u00020\u0001:\u00019B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u0018\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00162\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0017\u0010\u0018\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0019\u001a\u00020\u001aH\u0016¢\u0006\u0002\u0010\u001bJ#\u0010\u001c\u001a\u0004\u0018\u00010\u00072\b\u0010\u001d\u001a\u0004\u0018\u00010\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u0004\u0018\u00010\u0007*\u00020!H\u0002¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u0004\u0018\u00010\u0007*\u00020$H\u0002¢\u0006\u0002\u0010%J\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0007*\u00020&H\u0002¢\u0006\u0002\u0010'J\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0007*\u00020$H\u0002¢\u0006\u0002\u0010%J\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0007*\u00020(H\u0002¢\u0006\u0002\u0010)J\u0018\u0010*\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030+2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010.\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030+H\u0002J\u0018\u0010/\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030+2\u0006\u0010,\u001a\u00020-H\u0002J\u0018\u00100\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030+2\u0006\u0010,\u001a\u00020-H\u0002J\f\u00101\u001a\u00020\u0007*\u000202H\u0002J\u0013\u00103\u001a\u0004\u0018\u00010\u0007*\u000204H\u0002¢\u0006\u0002\u00105J\u0013\u00103\u001a\u0004\u0018\u00010\u0007*\u00020!H\u0002¢\u0006\u0002\u0010\"J\u0014\u00106\u001a\u00020\u0007*\n\u0012\u0002\b\u0003\u0012\u0002\b\u000307H\u0002J\u0010\u00108\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030+H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006:"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinAnnotationIntrospector;", "Lcom/fasterxml/jackson/databind/introspect/NopAnnotationIntrospector;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lcom/fasterxml/jackson/databind/Module$SetupContext;", "cache", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "nullToEmptyCollection", "", "nullToEmptyMap", "nullIsSameAsDefault", "(Lcom/fasterxml/jackson/databind/Module$SetupContext;Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;ZZZ)V", "findCreatorAnnotation", "Lcom/fasterxml/jackson/annotation/JsonCreator$Mode;", "config", "Lcom/fasterxml/jackson/databind/cfg/MapperConfig;", "a", "Lcom/fasterxml/jackson/databind/introspect/Annotated;", "findNullSerializer", "Lcom/fasterxml/jackson/databind/ser/std/StdSerializer;", "am", "findSerializer", "findSubtypes", "", "Lcom/fasterxml/jackson/databind/jsontype/NamedType;", "hasRequiredMarker", "m", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;", "(Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;)Ljava/lang/Boolean;", "requiredAnnotationOrNullability", "byAnnotation", "byNullability", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)Ljava/lang/Boolean;", "getRequiredMarkerFromAccessorLikeMethod", "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)Ljava/lang/Boolean;", "getRequiredMarkerFromCorrespondingAccessor", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMethod;", "(Lcom/fasterxml/jackson/databind/introspect/AnnotatedMethod;)Ljava/lang/Boolean;", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedField;", "(Lcom/fasterxml/jackson/databind/introspect/AnnotatedField;)Ljava/lang/Boolean;", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedParameter;", "(Lcom/fasterxml/jackson/databind/introspect/AnnotatedParameter;)Ljava/lang/Boolean;", "isConstructorParameterRequired", "Lkotlin/reflect/KFunction;", "index", "", "isGetterLike", "isMethodParameterRequired", "isParameterRequired", "isRequired", "Lkotlin/reflect/KType;", "isRequiredByAnnotation", "Ljava/lang/reflect/AccessibleObject;", "(Ljava/lang/reflect/AccessibleObject;)Ljava/lang/Boolean;", "isRequiredByNullability", "Lkotlin/reflect/KProperty1;", "isSetterLike", "Companion", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinAnnotationIntrospector.class */
public final class KotlinAnnotationIntrospector extends NopAnnotationIntrospector {

    @NotNull
    private final Module.SetupContext context;

    @NotNull
    private final ReflectionCache cache;
    private final boolean nullToEmptyCollection;
    private final boolean nullToEmptyMap;
    private final boolean nullIsSameAsDefault;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KType UNIT_TYPE = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Unit.class), null, false, null, 7, null);

    public KotlinAnnotationIntrospector(@NotNull Module.SetupContext context, @NotNull ReflectionCache cache, boolean nullToEmptyCollection, boolean nullToEmptyMap, boolean nullIsSameAsDefault) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cache, "cache");
        this.context = context;
        this.cache = cache;
        this.nullToEmptyCollection = nullToEmptyCollection;
        this.nullToEmptyMap = nullToEmptyMap;
        this.nullIsSameAsDefault = nullIsSameAsDefault;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    @Nullable
    public Boolean hasRequiredMarker(@NotNull final AnnotatedMember m) {
        Intrinsics.checkNotNullParameter(m, "m");
        Boolean hasRequired = this.cache.javaMemberIsRequired(m, new Function1<AnnotatedMember, Boolean>() { // from class: com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector$hasRequiredMarker$hasRequired$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Boolean invoke(@NotNull AnnotatedMember it) {
                Boolean bool;
                Boolean boolHasRequiredMarker;
                Intrinsics.checkNotNullParameter(it, "it");
                try {
                    if (this.this$0.nullToEmptyCollection && m.getType().isCollectionLikeType()) {
                        boolHasRequiredMarker = false;
                    } else if (this.this$0.nullToEmptyMap && m.getType().isMapLikeType()) {
                        boolHasRequiredMarker = false;
                    } else {
                        Class<?> declaringClass = m.getMember().getDeclaringClass();
                        Intrinsics.checkNotNullExpressionValue(declaringClass, "m.member.declaringClass");
                        if (KotlinModuleKt.isKotlinClass(declaringClass)) {
                            AnnotatedMember annotatedMember = m;
                            if (annotatedMember instanceof AnnotatedField) {
                                boolHasRequiredMarker = this.this$0.hasRequiredMarker((AnnotatedField) m);
                            } else {
                                boolHasRequiredMarker = annotatedMember instanceof AnnotatedMethod ? this.this$0.hasRequiredMarker((AnnotatedMethod) m) : annotatedMember instanceof AnnotatedParameter ? this.this$0.hasRequiredMarker((AnnotatedParameter) m) : null;
                            }
                        } else {
                            boolHasRequiredMarker = null;
                        }
                    }
                    bool = boolHasRequiredMarker;
                } catch (UnsupportedOperationException e) {
                    bool = (Boolean) null;
                }
                return bool;
            }
        });
        return hasRequired;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    @Nullable
    public JsonCreator.Mode findCreatorAnnotation(@NotNull MapperConfig<?> config, @NotNull Annotated a) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(a, "a");
        return super.findCreatorAnnotation(config, a);
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    @Nullable
    public StdSerializer<?> findSerializer(@NotNull Annotated am) throws SecurityException {
        Collection memberProperties;
        Object obj;
        KProperty1 kProperty1;
        KType returnType;
        Class outerClazz;
        Intrinsics.checkNotNullParameter(am, "am");
        if (!(am instanceof AnnotatedMethod) || !KotlinVersion.CURRENT.isAtLeast(1, 5)) {
            return null;
        }
        Method $this$findSerializer_u24lambda_u2d0 = ((AnnotatedMethod) am).getMember();
        Class<?> returnType2 = $this$findSerializer_u24lambda_u2d0.getReturnType();
        Intrinsics.checkNotNullExpressionValue(returnType2, "this.returnType");
        if (ExtensionsKt.isUnboxableValueClass(returnType2)) {
            return null;
        }
        Class<?> declaringClass = $this$findSerializer_u24lambda_u2d0.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "getter\n                        .declaringClass");
        KClass it = JvmClassMappingKt.getKotlinClass(declaringClass);
        try {
            memberProperties = KClasses.getMemberProperties(it);
        } catch (Error e) {
            memberProperties = (Collection) null;
        }
        Collection collection = memberProperties;
        if (collection == null) {
            kProperty1 = null;
        } else {
            Iterator it2 = collection.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                Object next = it2.next();
                KProperty1 it3 = (KProperty1) next;
                if (Intrinsics.areEqual(ReflectJvmMapping.getJavaGetter(it3), $this$findSerializer_u24lambda_u2d0)) {
                    obj = next;
                    break;
                }
            }
            kProperty1 = (KProperty1) obj;
        }
        KProperty1 kotlinProperty = kProperty1;
        KClassifier classifier = (kotlinProperty == null || (returnType = kotlinProperty.getReturnType()) == null) ? null : returnType.getClassifier();
        KClassifier kClassifier = classifier;
        KClass it4 = kClassifier instanceof KClass ? (KClass) kClassifier : null;
        if (it4 == null) {
            return null;
        }
        KClass kClass = it4.isValue() ? it4 : null;
        if (kClass == null || (outerClazz = JvmClassMappingKt.getJavaClass(kClass)) == null) {
            return null;
        }
        Class innerClazz = $this$findSerializer_u24lambda_u2d0.getReturnType();
        ValueClassStaticJsonValueSerializer.Companion companion = ValueClassStaticJsonValueSerializer.Companion;
        Intrinsics.checkNotNullExpressionValue(innerClazz, "innerClazz");
        ValueClassStaticJsonValueSerializer valueClassStaticJsonValueSerializerCreatedOrNull = companion.createdOrNull(outerClazz, innerClazz);
        return valueClassStaticJsonValueSerializerCreatedOrNull == null ? new ValueClassBoxSerializer(outerClazz, innerClazz) : valueClassStaticJsonValueSerializerCreatedOrNull;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    @Nullable
    public StdSerializer<?> findNullSerializer(@NotNull Annotated am) {
        Intrinsics.checkNotNullParameter(am, "am");
        return findSerializer(am);
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    @Nullable
    public List<NamedType> findSubtypes(@NotNull Annotated a) {
        Intrinsics.checkNotNullParameter(a, "a");
        Class it = a.getRawType();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        Class rawType = KotlinModuleKt.isKotlinClass(it) ? it : null;
        if (rawType == null) {
            return null;
        }
        Iterable $this$map$iv = JvmClassMappingKt.getKotlinClass(rawType).getSealedSubclasses();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(new NamedType(JvmClassMappingKt.getJavaClass((KClass) item$iv$iv)));
        }
        List<NamedType> mutableList = CollectionsKt.toMutableList(destination$iv$iv);
        return mutableList.isEmpty() ? null : mutableList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Boolean hasRequiredMarker(AnnotatedField $this$hasRequiredMarker) {
        KType returnType;
        Member member = $this$hasRequiredMarker.getMember();
        if (member == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.reflect.Field");
        }
        Boolean byAnnotation = isRequiredByAnnotation((Field) member);
        Member member2 = $this$hasRequiredMarker.getMember();
        if (member2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.reflect.Field");
        }
        KProperty<?> kotlinProperty = ReflectJvmMapping.getKotlinProperty((Field) member2);
        Boolean boolValueOf = (kotlinProperty == null || (returnType = kotlinProperty.getReturnType()) == null) ? null : Boolean.valueOf(isRequired(returnType));
        Boolean byNullability = boolValueOf;
        return requiredAnnotationOrNullability(byAnnotation, byNullability);
    }

    private final Boolean isRequiredByAnnotation(AccessibleObject $this$isRequiredByAnnotation) {
        Annotation annotation;
        Annotation[] annotations = $this$isRequiredByAnnotation.getAnnotations();
        if (annotations == null) {
            return null;
        }
        int length = annotations.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                annotation = null;
                break;
            }
            Annotation annotation2 = annotations[i];
            if (Intrinsics.areEqual(JvmClassMappingKt.getAnnotationClass(annotation2), Reflection.getOrCreateKotlinClass(JsonProperty.class))) {
                annotation = annotation2;
                break;
            }
            i++;
        }
        Annotation it = annotation;
        if (it == null) {
            return null;
        }
        return Boolean.valueOf(((JsonProperty) it).required());
    }

    private final Boolean requiredAnnotationOrNullability(Boolean byAnnotation, Boolean byNullability) {
        if (byAnnotation != null && byNullability != null) {
            return Boolean.valueOf(byAnnotation.booleanValue() || byNullability.booleanValue());
        }
        if (byNullability != null) {
            return byNullability;
        }
        return byAnnotation;
    }

    private final Boolean isRequiredByAnnotation(Method $this$isRequiredByAnnotation) {
        Object obj;
        Object[] annotations = $this$isRequiredByAnnotation.getAnnotations();
        Intrinsics.checkNotNullExpressionValue(annotations, "this.annotations");
        Object[] $this$firstOrNull$iv = annotations;
        int length = $this$firstOrNull$iv.length;
        int i = 0;
        while (true) {
            if (i < length) {
                Object element$iv = $this$firstOrNull$iv[i];
                Annotation it = (Annotation) element$iv;
                if (Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(it)), JsonProperty.class)) {
                    obj = element$iv;
                    break;
                }
                i++;
            } else {
                obj = null;
                break;
            }
        }
        Object obj2 = obj;
        JsonProperty jsonProperty = obj2 instanceof JsonProperty ? (JsonProperty) obj2 : null;
        if (jsonProperty == null) {
            return null;
        }
        return Boolean.valueOf(jsonProperty.required());
    }

    private final boolean isRequiredByNullability(KProperty1<?, ?> kProperty1) {
        return isRequired(kProperty1.getReturnType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Boolean hasRequiredMarker(AnnotatedMethod $this$hasRequiredMarker) {
        Boolean requiredMarkerFromCorrespondingAccessor = getRequiredMarkerFromCorrespondingAccessor($this$hasRequiredMarker);
        if (requiredMarkerFromCorrespondingAccessor != null) {
            return requiredMarkerFromCorrespondingAccessor;
        }
        Method member = $this$hasRequiredMarker.getMember();
        Intrinsics.checkNotNullExpressionValue(member, "this.member");
        return getRequiredMarkerFromAccessorLikeMethod(member);
    }

    private final Boolean getRequiredMarkerFromCorrespondingAccessor(AnnotatedMethod $this$getRequiredMarkerFromCorrespondingAccessor) {
        Class<?> declaringClass = $this$getRequiredMarkerFromCorrespondingAccessor.getMember().getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "member.declaringClass");
        Iterable $this$forEach$iv = KClasses.getDeclaredMemberProperties(JvmClassMappingKt.getKotlinClass(declaringClass));
        for (Object element$iv : $this$forEach$iv) {
            KProperty1 kProperty = (KProperty1) element$iv;
            if (!Intrinsics.areEqual(ReflectJvmMapping.getJavaGetter(kProperty), $this$getRequiredMarkerFromCorrespondingAccessor.getMember())) {
                KMutableProperty1 kMutableProperty1 = kProperty instanceof KMutableProperty1 ? (KMutableProperty1) kProperty : null;
                if (Intrinsics.areEqual(kMutableProperty1 == null ? null : ReflectJvmMapping.getJavaSetter(kMutableProperty1), $this$getRequiredMarkerFromCorrespondingAccessor.getMember())) {
                }
            }
            Method member = $this$getRequiredMarkerFromCorrespondingAccessor.getMember();
            Intrinsics.checkNotNullExpressionValue(member, "this.member");
            Boolean byAnnotation = isRequiredByAnnotation(member);
            boolean byNullability = isRequiredByNullability(kProperty);
            return requiredAnnotationOrNullability(byAnnotation, Boolean.valueOf(byNullability));
        }
        return null;
    }

    private final Boolean getRequiredMarkerFromAccessorLikeMethod(Method $this$getRequiredMarkerFromAccessorLikeMethod) {
        KFunction method = ReflectJvmMapping.getKotlinFunction($this$getRequiredMarkerFromAccessorLikeMethod);
        if (method != null) {
            Boolean byAnnotation = isRequiredByAnnotation($this$getRequiredMarkerFromAccessorLikeMethod);
            if (isGetterLike(method)) {
                return requiredAnnotationOrNullability(byAnnotation, Boolean.valueOf(isRequired(method.getReturnType())));
            }
            if (isSetterLike(method)) {
                return requiredAnnotationOrNullability(byAnnotation, Boolean.valueOf(isMethodParameterRequired(method, 0)));
            }
            return null;
        }
        return null;
    }

    private final boolean isGetterLike(KFunction<?> kFunction) {
        return kFunction.getParameters().size() == 1;
    }

    private final boolean isSetterLike(KFunction<?> kFunction) {
        return kFunction.getParameters().size() == 2 && Intrinsics.areEqual(kFunction.getReturnType(), UNIT_TYPE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Boolean hasRequiredMarker(AnnotatedParameter $this$hasRequiredMarker) {
        Boolean boolValueOf;
        Member member = $this$hasRequiredMarker.getMember();
        JsonProperty jsonProperty = (JsonProperty) $this$hasRequiredMarker.getAnnotation(JsonProperty.class);
        Boolean byAnnotation = jsonProperty == null ? null : Boolean.valueOf(jsonProperty.required());
        if (member instanceof Constructor) {
            Intrinsics.checkNotNullExpressionValue(member, "member");
            KFunction<?> kotlinFunction = ReflectJvmMapping.getKotlinFunction((Constructor) member);
            boolValueOf = kotlinFunction == null ? null : Boolean.valueOf(isConstructorParameterRequired(kotlinFunction, $this$hasRequiredMarker.getIndex()));
        } else if (member instanceof Method) {
            Intrinsics.checkNotNullExpressionValue(member, "member");
            KFunction<?> kotlinFunction2 = ReflectJvmMapping.getKotlinFunction((Method) member);
            boolValueOf = kotlinFunction2 == null ? null : Boolean.valueOf(isMethodParameterRequired(kotlinFunction2, $this$hasRequiredMarker.getIndex()));
        } else {
            boolValueOf = null;
        }
        Boolean byNullability = boolValueOf;
        return requiredAnnotationOrNullability(byAnnotation, byNullability);
    }

    private final boolean isConstructorParameterRequired(KFunction<?> kFunction, int index) {
        return isParameterRequired(kFunction, index);
    }

    private final boolean isMethodParameterRequired(KFunction<?> kFunction, int index) {
        return isParameterRequired(kFunction, index + 1);
    }

    private final boolean isParameterRequired(KFunction<?> kFunction, int index) {
        KParameter param = kFunction.getParameters().get(index);
        KType paramType = param.getType();
        Type javaType = ReflectJvmMapping.getJavaType(paramType);
        boolean isPrimitive = javaType instanceof Class ? ((Class) javaType).isPrimitive() : false;
        return (paramType.isMarkedNullable() || param.isOptional() || (isPrimitive && !this.context.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES))) ? false : true;
    }

    private final boolean isRequired(KType $this$isRequired) {
        return !$this$isRequired.isMarkedNullable();
    }

    /* compiled from: KotlinAnnotationIntrospector.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinAnnotationIntrospector$Companion;", "", "()V", "UNIT_TYPE", "Lkotlin/reflect/KType;", "getUNIT_TYPE", "()Lkotlin/reflect/KType;", "jackson-module-kotlin"})
    /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinAnnotationIntrospector$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KType getUNIT_TYPE() {
            return KotlinAnnotationIntrospector.UNIT_TYPE;
        }
    }
}
