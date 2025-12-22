package kotlin.reflect.full;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.KFunctionImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KClasses.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��Z\n��\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\r\u001a+\u0010S\u001a\u0002H\u001d\"\b\b��\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00022\b\u0010T\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0002\u0010U\u001a!\u0010V\u001a\u0002H\u001d\"\b\b��\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u0002H\u0007¢\u0006\u0002\u0010\u0013\u001a\u001c\u0010W\u001a\u000203*\u0006\u0012\u0002\b\u00030\u00022\n\u0010X\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0007\u001a\u001c\u0010Y\u001a\u000203*\u0006\u0012\u0002\b\u00030\u00022\n\u0010Z\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0007\u001a-\u0010[\u001a\u0004\u0018\u0001H\u001d\"\b\b��\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00022\b\u0010T\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0002\u0010U\",\u0010��\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"(\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006\"(\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u000e\"$\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0013\",\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\",\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0004\u001a\u0004\b\u001a\u0010\u0006\"B\u0010\u001b\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001c0\u0001\"\b\b��\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\",\u0010 \u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b!\u0010\u0004\u001a\u0004\b\"\u0010\u0006\">\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u00030$0\u0001\"\b\b��\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b%\u0010\u0004\u001a\u0004\b&\u0010\u0006\",\u0010'\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030(0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b)\u0010\u0004\u001a\u0004\b*\u0010\u0006\"\"\u0010+\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b,\u0010\u0004\u001a\u0004\b-\u0010.\",\u0010/\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b0\u0010\u0004\u001a\u0004\b1\u0010\u0006\"\u001c\u00102\u001a\u000203*\u0006\u0012\u0002\b\u0003048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00105\"\u001c\u00106\u001a\u000203*\u0006\u0012\u0002\b\u0003048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b6\u00105\",\u00107\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b8\u0010\u0004\u001a\u0004\b9\u0010\u0006\"B\u0010:\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001c0\u0001\"\b\b��\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b;\u0010\u0004\u001a\u0004\b<\u0010\u0006\",\u0010=\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b>\u0010\u0004\u001a\u0004\b?\u0010\u0006\">\u0010@\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u00030$0\u0001\"\b\b��\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bA\u0010\u0004\u001a\u0004\bB\u0010\u0006\"6\u0010C\u001a\n\u0012\u0004\u0012\u0002H\u001d\u0018\u00010\u0015\"\b\b��\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bD\u0010\u0004\u001a\u0004\bE\u0010F\",\u0010G\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bH\u0010\u0004\u001a\u0004\bI\u0010\u0006\",\u0010J\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030K0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bL\u0010\u0004\u001a\u0004\bM\u0010\u0006\",\u0010N\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020O*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bP\u0010\u0004\u001a\u0004\bQ\u0010R¨\u0006\\"}, d2 = {"allSuperclasses", "", "Lkotlin/reflect/KClass;", "getAllSuperclasses$annotations", "(Lkotlin/reflect/KClass;)V", "getAllSuperclasses", "(Lkotlin/reflect/KClass;)Ljava/util/Collection;", "allSupertypes", "Lkotlin/reflect/KType;", "getAllSupertypes$annotations", "getAllSupertypes", "companionObject", "getCompanionObject$annotations", "getCompanionObject", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KClass;", "companionObjectInstance", "", "getCompanionObjectInstance$annotations", "getCompanionObjectInstance", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "declaredFunctions", "Lkotlin/reflect/KFunction;", "getDeclaredFunctions$annotations", "getDeclaredFunctions", "declaredMemberExtensionFunctions", "getDeclaredMemberExtensionFunctions$annotations", "getDeclaredMemberExtensionFunctions", "declaredMemberExtensionProperties", "Lkotlin/reflect/KProperty2;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "getDeclaredMemberExtensionProperties$annotations", "getDeclaredMemberExtensionProperties", "declaredMemberFunctions", "getDeclaredMemberFunctions$annotations", "getDeclaredMemberFunctions", "declaredMemberProperties", "Lkotlin/reflect/KProperty1;", "getDeclaredMemberProperties$annotations", "getDeclaredMemberProperties", "declaredMembers", "Lkotlin/reflect/KCallable;", "getDeclaredMembers$annotations", "getDeclaredMembers", "defaultType", "getDefaultType$annotations", "getDefaultType", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KType;", "functions", "getFunctions$annotations", "getFunctions", "isExtension", "", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "(Lkotlin/reflect/jvm/internal/KCallableImpl;)Z", "isNotExtension", "memberExtensionFunctions", "getMemberExtensionFunctions$annotations", "getMemberExtensionFunctions", "memberExtensionProperties", "getMemberExtensionProperties$annotations", "getMemberExtensionProperties", "memberFunctions", "getMemberFunctions$annotations", "getMemberFunctions", "memberProperties", "getMemberProperties$annotations", "getMemberProperties", "primaryConstructor", "getPrimaryConstructor$annotations", "getPrimaryConstructor", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KFunction;", "staticFunctions", "getStaticFunctions$annotations", "getStaticFunctions", "staticProperties", "Lkotlin/reflect/KProperty0;", "getStaticProperties$annotations", "getStaticProperties", "superclasses", "", "getSuperclasses$annotations", "getSuperclasses", "(Lkotlin/reflect/KClass;)Ljava/util/List;", "cast", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "createInstance", "isSubclassOf", "base", "isSuperclassOf", "derived", "safeCast", "kotlin-reflection"})
@JvmName(name = "KClasses")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/full/KClasses.class */
public final class KClasses {
    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getPrimaryConstructor$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getCompanionObject$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getCompanionObjectInstance$annotations(KClass kClass) {
    }

    @Deprecated(message = "This function creates a type which rarely makes sense for generic classes. For example, such type can only be used in signatures of members of that class. Use starProjectedType or createType() for clearer semantics.")
    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getDefaultType$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getDeclaredMembers$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getStaticFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getMemberFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getMemberExtensionFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getDeclaredFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getDeclaredMemberFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getDeclaredMemberExtensionFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getStaticProperties$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getMemberProperties$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getMemberExtensionProperties$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getDeclaredMemberProperties$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getDeclaredMemberExtensionProperties$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getSuperclasses$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getAllSupertypes$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getAllSuperclasses$annotations(KClass kClass) {
    }

    @Nullable
    public static final <T> KFunction<T> getPrimaryConstructor(@NotNull KClass<T> primaryConstructor) {
        Object obj;
        Intrinsics.checkNotNullParameter(primaryConstructor, "$this$primaryConstructor");
        Iterable $this$firstOrNull$iv = ((KClassImpl) primaryConstructor).getConstructors();
        Iterator<T> it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                KFunction it2 = (KFunction) element$iv;
                if (it2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KFunctionImpl");
                }
                FunctionDescriptor descriptor = ((KFunctionImpl) it2).getDescriptor();
                if (descriptor == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ConstructorDescriptor");
                }
                if (((ConstructorDescriptor) descriptor).isPrimary()) {
                    obj = element$iv;
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        return (KFunction) obj;
    }

    @Nullable
    public static final KClass<?> getCompanionObject(@NotNull KClass<?> companionObject) {
        Object obj;
        Intrinsics.checkNotNullParameter(companionObject, "$this$companionObject");
        Iterable $this$firstOrNull$iv = companionObject.getNestedClasses();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                KClass it2 = (KClass) element$iv;
                if (it2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>");
                }
                if (((KClassImpl) it2).getDescriptor().isCompanionObject()) {
                    obj = element$iv;
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        return (KClass) obj;
    }

    @Nullable
    public static final Object getCompanionObjectInstance(@NotNull KClass<?> companionObjectInstance) {
        Intrinsics.checkNotNullParameter(companionObjectInstance, "$this$companionObjectInstance");
        KClass<?> companionObject = getCompanionObject(companionObjectInstance);
        if (companionObject != null) {
            return companionObject.getObjectInstance();
        }
        return null;
    }

    @NotNull
    public static final KType getDefaultType(@NotNull final KClass<?> defaultType) {
        Intrinsics.checkNotNullParameter(defaultType, "$this$defaultType");
        SimpleType defaultType2 = ((KClassImpl) defaultType).getDescriptor().getDefaultType();
        Intrinsics.checkNotNullExpressionValue(defaultType2, "(this as KClassImpl<*>).descriptor.defaultType");
        return new KTypeImpl(defaultType2, new Function0<Type>() { // from class: kotlin.reflect.full.KClasses$defaultType$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Type invoke() {
                return ((KClassImpl) defaultType).getJClass();
            }
        });
    }

    @NotNull
    public static final Collection<KCallable<?>> getDeclaredMembers(@NotNull KClass<?> declaredMembers) {
        Intrinsics.checkNotNullParameter(declaredMembers, "$this$declaredMembers");
        return ((KClassImpl.Data) ((KClassImpl) declaredMembers).getData().invoke()).getDeclaredMembers();
    }

    @NotNull
    public static final Collection<KFunction<?>> getFunctions(@NotNull KClass<?> functions) {
        Intrinsics.checkNotNullParameter(functions, "$this$functions");
        Iterable $this$filterIsInstance$iv = functions.getMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof KFunction) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final Collection<KFunction<?>> getStaticFunctions(@NotNull KClass<?> staticFunctions) {
        Intrinsics.checkNotNullParameter(staticFunctions, "$this$staticFunctions");
        Iterable $this$filterIsInstance$iv = ((KClassImpl.Data) ((KClassImpl) staticFunctions).getData().invoke()).getAllStaticMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof KFunction) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final Collection<KFunction<?>> getMemberFunctions(@NotNull KClass<?> memberFunctions) {
        Intrinsics.checkNotNullParameter(memberFunctions, "$this$memberFunctions");
        Iterable $this$filter$iv = ((KClassImpl.Data) ((KClassImpl) memberFunctions).getData().invoke()).getAllNonStaticMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            KCallableImpl it = (KCallableImpl) element$iv$iv;
            if (isNotExtension(it) && (it instanceof KFunction)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final Collection<KFunction<?>> getMemberExtensionFunctions(@NotNull KClass<?> memberExtensionFunctions) {
        Intrinsics.checkNotNullParameter(memberExtensionFunctions, "$this$memberExtensionFunctions");
        Iterable $this$filter$iv = ((KClassImpl.Data) ((KClassImpl) memberExtensionFunctions).getData().invoke()).getAllNonStaticMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            KCallableImpl it = (KCallableImpl) element$iv$iv;
            if (isExtension(it) && (it instanceof KFunction)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final Collection<KFunction<?>> getDeclaredFunctions(@NotNull KClass<?> declaredFunctions) {
        Intrinsics.checkNotNullParameter(declaredFunctions, "$this$declaredFunctions");
        Iterable $this$filterIsInstance$iv = ((KClassImpl.Data) ((KClassImpl) declaredFunctions).getData().invoke()).getDeclaredMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof KFunction) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final Collection<KFunction<?>> getDeclaredMemberFunctions(@NotNull KClass<?> declaredMemberFunctions) {
        Intrinsics.checkNotNullParameter(declaredMemberFunctions, "$this$declaredMemberFunctions");
        Iterable $this$filter$iv = ((KClassImpl.Data) ((KClassImpl) declaredMemberFunctions).getData().invoke()).getDeclaredNonStaticMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            KCallableImpl it = (KCallableImpl) element$iv$iv;
            if (isNotExtension(it) && (it instanceof KFunction)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final Collection<KFunction<?>> getDeclaredMemberExtensionFunctions(@NotNull KClass<?> declaredMemberExtensionFunctions) {
        Intrinsics.checkNotNullParameter(declaredMemberExtensionFunctions, "$this$declaredMemberExtensionFunctions");
        Iterable $this$filter$iv = ((KClassImpl.Data) ((KClassImpl) declaredMemberExtensionFunctions).getData().invoke()).getDeclaredNonStaticMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            KCallableImpl it = (KCallableImpl) element$iv$iv;
            if (isExtension(it) && (it instanceof KFunction)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final Collection<KProperty0<?>> getStaticProperties(@NotNull KClass<?> staticProperties) {
        Intrinsics.checkNotNullParameter(staticProperties, "$this$staticProperties");
        Iterable $this$filter$iv = ((KClassImpl.Data) ((KClassImpl) staticProperties).getData().invoke()).getAllStaticMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            KCallableImpl it = (KCallableImpl) element$iv$iv;
            if (isNotExtension(it) && (it instanceof KProperty0)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final <T> Collection<KProperty1<T, ?>> getMemberProperties(@NotNull KClass<T> memberProperties) {
        Intrinsics.checkNotNullParameter(memberProperties, "$this$memberProperties");
        Iterable $this$filter$iv = ((KClassImpl) memberProperties).getData().invoke().getAllNonStaticMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            KCallableImpl it = (KCallableImpl) element$iv$iv;
            if (isNotExtension(it) && (it instanceof KProperty1)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final <T> Collection<KProperty2<T, ?, ?>> getMemberExtensionProperties(@NotNull KClass<T> memberExtensionProperties) {
        Intrinsics.checkNotNullParameter(memberExtensionProperties, "$this$memberExtensionProperties");
        Iterable $this$filter$iv = ((KClassImpl) memberExtensionProperties).getData().invoke().getAllNonStaticMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            KCallableImpl it = (KCallableImpl) element$iv$iv;
            if (isExtension(it) && (it instanceof KProperty2)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final <T> Collection<KProperty1<T, ?>> getDeclaredMemberProperties(@NotNull KClass<T> declaredMemberProperties) {
        Intrinsics.checkNotNullParameter(declaredMemberProperties, "$this$declaredMemberProperties");
        Iterable $this$filter$iv = ((KClassImpl) declaredMemberProperties).getData().invoke().getDeclaredNonStaticMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            KCallableImpl it = (KCallableImpl) element$iv$iv;
            if (isNotExtension(it) && (it instanceof KProperty1)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final <T> Collection<KProperty2<T, ?, ?>> getDeclaredMemberExtensionProperties(@NotNull KClass<T> declaredMemberExtensionProperties) {
        Intrinsics.checkNotNullParameter(declaredMemberExtensionProperties, "$this$declaredMemberExtensionProperties");
        Iterable $this$filter$iv = ((KClassImpl) declaredMemberExtensionProperties).getData().invoke().getDeclaredNonStaticMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            KCallableImpl it = (KCallableImpl) element$iv$iv;
            if (isExtension(it) && (it instanceof KProperty2)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    private static final boolean isExtension(KCallableImpl<?> kCallableImpl) {
        return kCallableImpl.getDescriptor().getExtensionReceiverParameter() != null;
    }

    private static final boolean isNotExtension(KCallableImpl<?> kCallableImpl) {
        return !isExtension(kCallableImpl);
    }

    @NotNull
    public static final List<KClass<?>> getSuperclasses(@NotNull KClass<?> superclasses) {
        Intrinsics.checkNotNullParameter(superclasses, "$this$superclasses");
        Iterable $this$mapNotNull$iv = superclasses.getSupertypes();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            KType it = (KType) element$iv$iv$iv;
            KClassifier classifier = it.getClassifier();
            if (!(classifier instanceof KClass)) {
                classifier = null;
            }
            KClass kClass = (KClass) classifier;
            if (kClass != null) {
                destination$iv$iv.add(kClass);
            }
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static final Collection<KType> getAllSupertypes(@NotNull KClass<?> allSupertypes) {
        Intrinsics.checkNotNullParameter(allSupertypes, "$this$allSupertypes");
        Object objDfs = DFS.dfs(allSupertypes.getSupertypes(), new DFS.Neighbors<KType>() { // from class: kotlin.reflect.full.KClasses$allSupertypes$1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            @NotNull
            public final Iterable<KType> getNeighbors(KType current) {
                KClassifier classifier = current.getClassifier();
                if (!(classifier instanceof KClass)) {
                    classifier = null;
                }
                KClass klass = (KClass) classifier;
                if (klass == null) {
                    throw new KotlinReflectionInternalError("Supertype not a class: " + current);
                }
                Iterable supertypes = klass.getSupertypes();
                List typeArguments = current.getArguments();
                if (typeArguments.isEmpty()) {
                    return supertypes;
                }
                if (current == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
                }
                TypeSubstitutor substitutor = TypeSubstitutor.create(((KTypeImpl) current).getType());
                Iterable $this$map$iv = supertypes;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    KType supertype = (KType) item$iv$iv;
                    if (supertype == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
                    }
                    KotlinType substituted = substitutor.substitute(((KTypeImpl) supertype).getType(), Variance.INVARIANT);
                    if (substituted == null) {
                        throw new KotlinReflectionInternalError("Type substitution failed: " + supertype + " (" + current + ')');
                    }
                    Intrinsics.checkNotNullExpressionValue(substituted, "substitutor.substitute((…: $supertype ($current)\")");
                    destination$iv$iv.add(new KTypeImpl(substituted, null, 2, null));
                }
                return (List) destination$iv$iv;
            }
        }, new DFS.VisitedWithSet(), new DFS.NodeHandlerWithListResult<KType, KType>() { // from class: kotlin.reflect.full.KClasses$allSupertypes$2
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(@NotNull KType current) {
                Intrinsics.checkNotNullParameter(current, "current");
                ((LinkedList) this.result).add(current);
                return true;
            }
        });
        Intrinsics.checkNotNullExpressionValue(objDfs, "DFS.dfs(\n            sup…    }\n            }\n    )");
        return (Collection) objDfs;
    }

    @NotNull
    public static final Collection<KClass<?>> getAllSuperclasses(@NotNull KClass<?> allSuperclasses) {
        Intrinsics.checkNotNullParameter(allSuperclasses, "$this$allSuperclasses");
        Iterable $this$map$iv = getAllSupertypes(allSuperclasses);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            KType supertype = (KType) item$iv$iv;
            KClassifier classifier = supertype.getClassifier();
            if (!(classifier instanceof KClass)) {
                classifier = null;
            }
            KClass kClass = (KClass) classifier;
            if (kClass == null) {
                throw new KotlinReflectionInternalError("Supertype not a class: " + supertype);
            }
            destination$iv$iv.add(kClass);
        }
        return (List) destination$iv$iv;
    }

    /* compiled from: KClasses.kt */
    @Metadata(mv = {1, 5, 1}, k = 3)
    /* renamed from: kotlin.reflect.full.KClasses$isSubclassOf$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/full/KClasses$isSubclassOf$1.class */
    final /* synthetic */ class AnonymousClass1 extends PropertyReference1 {
        public static final KProperty1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "superclasses";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getSuperclasses(Lkotlin/reflect/KClass;)Ljava/util/List;";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinPackage(KClasses.class, "kotlin-reflection");
        }

        @Override // kotlin.reflect.KProperty1
        @Nullable
        public Object get(@Nullable Object receiver) {
            return KClasses.getSuperclasses((KClass) receiver);
        }
    }

    @SinceKotlin(version = "1.1")
    public static final boolean isSubclassOf(@NotNull KClass<?> isSubclassOf, @NotNull final KClass<?> base) {
        Intrinsics.checkNotNullParameter(isSubclassOf, "$this$isSubclassOf");
        Intrinsics.checkNotNullParameter(base, "base");
        if (!Intrinsics.areEqual(isSubclassOf, base)) {
            List listListOf = CollectionsKt.listOf(isSubclassOf);
            final KProperty1 kProperty1 = AnonymousClass1.INSTANCE;
            Object obj = kProperty1;
            if (kProperty1 != null) {
                obj = new DFS.Neighbors() { // from class: kotlin.reflect.full.KClasses$sam$org_jetbrains_kotlin_utils_DFS_Neighbors$0
                    @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
                    @NotNull
                    public final /* synthetic */ Iterable getNeighbors(Object p0) {
                        return (Iterable) kProperty1.invoke(p0);
                    }
                };
            }
            Boolean boolIfAny = DFS.ifAny(listListOf, (DFS.Neighbors) obj, new Function1<KClass<?>, Boolean>() { // from class: kotlin.reflect.full.KClasses.isSubclassOf.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(KClass<?> kClass) {
                    return Boolean.valueOf(Intrinsics.areEqual(kClass, base));
                }
            });
            Intrinsics.checkNotNullExpressionValue(boolIfAny, "DFS.ifAny(listOf(this), …erclasses) { it == base }");
            if (!boolIfAny.booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.1")
    public static final boolean isSuperclassOf(@NotNull KClass<?> isSuperclassOf, @NotNull KClass<?> derived) {
        Intrinsics.checkNotNullParameter(isSuperclassOf, "$this$isSuperclassOf");
        Intrinsics.checkNotNullParameter(derived, "derived");
        return isSubclassOf(derived, isSuperclassOf);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T> T cast(@NotNull KClass<T> cast, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(cast, "$this$cast");
        if (!cast.isInstance(obj)) {
            throw new TypeCastException("Value cannot be cast to " + cast.getQualifiedName());
        }
        if (obj == 0) {
            throw new NullPointerException("null cannot be cast to non-null type T");
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.1")
    @Nullable
    public static final <T> T safeCast(@NotNull KClass<T> safeCast, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(safeCast, "$this$safeCast");
        if (!safeCast.isInstance(obj)) {
            return null;
        }
        if (obj == 0) {
            throw new NullPointerException("null cannot be cast to non-null type T");
        }
        return obj;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T> T createInstance(@NotNull KClass<T> createInstance) {
        Object obj;
        boolean z;
        Intrinsics.checkNotNullParameter(createInstance, "$this$createInstance");
        T t = null;
        boolean z2 = false;
        Iterator<T> it = createInstance.getConstructors().iterator();
        while (true) {
            if (it.hasNext()) {
                T next = it.next();
                List<KParameter> parameters = ((KFunction) next).getParameters();
                if (!(parameters instanceof Collection) || !parameters.isEmpty()) {
                    Iterator<T> it2 = parameters.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            z = true;
                            break;
                        }
                        if (!((KParameter) it2.next()).isOptional()) {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = true;
                }
                if (z) {
                    if (z2) {
                        obj = null;
                        break;
                    }
                    t = next;
                    z2 = true;
                }
            } else {
                obj = !z2 ? null : t;
            }
        }
        KFunction kFunction = (KFunction) obj;
        if (kFunction != null) {
            return (T) kFunction.callBy(MapsKt.emptyMap());
        }
        throw new IllegalArgumentException("Class should have a single no-arg constructor: " + createInstance);
    }
}
