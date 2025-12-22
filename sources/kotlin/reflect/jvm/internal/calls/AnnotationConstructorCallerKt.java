package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: AnnotationConstructorCaller.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��4\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n��\n\u0002\u0010\b\n\u0002\b\u0005\u001aI\u0010��\u001a\u0002H\u0001\"\b\b��\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH��¢\u0006\u0002\u0010\u000b\u001a$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002\u001a\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00022\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002¨\u0006\u0014"}, d2 = {"createAnnotationInstance", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "annotationClass", "Ljava/lang/Class;", "values", "", "", "methods", "", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/util/Map;Ljava/util/List;)Ljava/lang/Object;", "throwIllegalArgumentType", "", "index", "", "name", "expectedJvmType", "transformKotlinToJvm", "expectedType", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/calls/AnnotationConstructorCallerKt.class */
public final class AnnotationConstructorCallerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Object transformKotlinToJvm(Object $this$transformKotlinToJvm, Class<?> cls) {
        Object array;
        if ($this$transformKotlinToJvm instanceof Class) {
            return null;
        }
        if ($this$transformKotlinToJvm instanceof KClass) {
            array = JvmClassMappingKt.getJavaClass((KClass) $this$transformKotlinToJvm);
        } else if ($this$transformKotlinToJvm instanceof Object[]) {
            if (((Object[]) $this$transformKotlinToJvm) instanceof Class[]) {
                return null;
            }
            if (!(((Object[]) $this$transformKotlinToJvm) instanceof KClass[])) {
                array = (Object[]) $this$transformKotlinToJvm;
            } else {
                if ($this$transformKotlinToJvm == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.reflect.KClass<*>>");
                }
                KClass[] kClassArr = (KClass[]) $this$transformKotlinToJvm;
                Collection destination$iv$iv = new ArrayList(kClassArr.length);
                for (KClass kClass : kClassArr) {
                    destination$iv$iv.add(JvmClassMappingKt.getJavaClass(kClass));
                }
                Collection $this$toTypedArray$iv = (List) destination$iv$iv;
                array = $this$toTypedArray$iv.toArray(new Class[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
        } else {
            array = $this$transformKotlinToJvm;
        }
        Object result = array;
        if (cls.isInstance(result)) {
            return result;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void throwIllegalArgumentType(int index, String name, Class<?> cls) {
        KClass kotlinClass;
        String qualifiedName;
        if (Intrinsics.areEqual(cls, Class.class)) {
            kotlinClass = Reflection.getOrCreateKotlinClass(KClass.class);
        } else if (cls.isArray() && Intrinsics.areEqual(cls.getComponentType(), Class.class)) {
            kotlinClass = Reflection.getOrCreateKotlinClass(KClass[].class);
        } else {
            kotlinClass = JvmClassMappingKt.getKotlinClass(cls);
        }
        KClass kotlinClass2 = kotlinClass;
        if (Intrinsics.areEqual(kotlinClass2.getQualifiedName(), Reflection.getOrCreateKotlinClass(Object[].class).getQualifiedName())) {
            StringBuilder sbAppend = new StringBuilder().append(kotlinClass2.getQualifiedName()).append('<');
            Class<?> componentType = JvmClassMappingKt.getJavaClass(kotlinClass2).getComponentType();
            Intrinsics.checkNotNullExpressionValue(componentType, "kotlinClass.java.componentType");
            qualifiedName = sbAppend.append(JvmClassMappingKt.getKotlinClass(componentType).getQualifiedName()).append('>').toString();
        } else {
            qualifiedName = kotlinClass2.getQualifiedName();
        }
        String typeString = qualifiedName;
        throw new IllegalArgumentException("Argument #" + index + ' ' + name + " is not of the required type " + typeString);
    }

    public static /* synthetic */ Object createAnnotationInstance$default(Class cls, Map map, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            Iterable $this$map$iv = map.keySet();
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String name = (String) item$iv$iv;
                destination$iv$iv.add(cls.getDeclaredMethod(name, new Class[0]));
            }
            list = (List) destination$iv$iv;
        }
        return createAnnotationInstance(cls, map, list);
    }

    @NotNull
    public static final <T> T createAnnotationInstance(@NotNull final Class<T> annotationClass, @NotNull final Map<String, ? extends Object> values, @NotNull List<Method> methods) {
        Intrinsics.checkNotNullParameter(annotationClass, "annotationClass");
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(methods, "methods");
        final AnonymousClass2 anonymousClass2 = new AnonymousClass2(annotationClass, methods, values);
        final Lazy lazy = LazyKt.lazy(new Function0<Integer>() { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$hashCode$2
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Integer invoke() {
                return Integer.valueOf(invoke2());
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2() {
                int iHashCode;
                int iHashCode2 = 0;
                for (Object obj : values.entrySet()) {
                    int i = iHashCode2;
                    Map.Entry entry = (Map.Entry) obj;
                    String key = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof boolean[]) {
                        iHashCode = Arrays.hashCode((boolean[]) value);
                    } else if (value instanceof char[]) {
                        iHashCode = Arrays.hashCode((char[]) value);
                    } else if (value instanceof byte[]) {
                        iHashCode = Arrays.hashCode((byte[]) value);
                    } else if (value instanceof short[]) {
                        iHashCode = Arrays.hashCode((short[]) value);
                    } else if (value instanceof int[]) {
                        iHashCode = Arrays.hashCode((int[]) value);
                    } else if (value instanceof float[]) {
                        iHashCode = Arrays.hashCode((float[]) value);
                    } else if (value instanceof long[]) {
                        iHashCode = Arrays.hashCode((long[]) value);
                    } else if (value instanceof double[]) {
                        iHashCode = Arrays.hashCode((double[]) value);
                    } else {
                        iHashCode = value instanceof Object[] ? Arrays.hashCode((Object[]) value) : value.hashCode();
                    }
                    int valueHash = iHashCode;
                    iHashCode2 = i + ((127 * key.hashCode()) ^ valueHash);
                }
                return iHashCode2;
            }
        });
        final KProperty kProperty = null;
        final Lazy lazy2 = LazyKt.lazy(new Function0<String>() { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$toString$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final String invoke() {
                StringBuilder $this$buildString = new StringBuilder();
                $this$buildString.append('@');
                $this$buildString.append(annotationClass.getCanonicalName());
                CollectionsKt.joinTo$default(values.entrySet(), $this$buildString, ", ", "(", ")", 0, null, new Function1<Map.Entry<? extends String, ? extends Object>, CharSequence>() { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$toString$2$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ CharSequence invoke(Map.Entry<? extends String, ? extends Object> entry) {
                        return invoke2((Map.Entry<String, ? extends Object>) entry);
                    }

                    @NotNull
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final CharSequence invoke2(@NotNull Map.Entry<String, ? extends Object> entry) {
                        String string;
                        Intrinsics.checkNotNullParameter(entry, "entry");
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (value instanceof boolean[]) {
                            string = Arrays.toString((boolean[]) value);
                        } else if (value instanceof char[]) {
                            string = Arrays.toString((char[]) value);
                        } else if (value instanceof byte[]) {
                            string = Arrays.toString((byte[]) value);
                        } else if (value instanceof short[]) {
                            string = Arrays.toString((short[]) value);
                        } else if (value instanceof int[]) {
                            string = Arrays.toString((int[]) value);
                        } else if (value instanceof float[]) {
                            string = Arrays.toString((float[]) value);
                        } else if (value instanceof long[]) {
                            string = Arrays.toString((long[]) value);
                        } else if (value instanceof double[]) {
                            string = Arrays.toString((double[]) value);
                        } else {
                            string = value instanceof Object[] ? Arrays.toString((Object[]) value) : value.toString();
                        }
                        String valueString = string;
                        return key + '=' + valueString;
                    }
                }, 48, null);
                String string = $this$buildString.toString();
                Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
                return string;
            }
        });
        final KProperty kProperty2 = null;
        T t = (T) Proxy.newProxyInstance(annotationClass.getClassLoader(), new Class[]{annotationClass}, new InvocationHandler() { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$result$1
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
            java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
             */
            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object $noName_0, Method method, Object[] args) {
                Intrinsics.checkNotNullExpressionValue(method, "method");
                String name = method.getName();
                if (name != null) {
                    switch (name.hashCode()) {
                        case -1776922004:
                            if (name.equals("toString")) {
                                Lazy lazy3 = lazy2;
                                KProperty kProperty3 = kProperty2;
                                return lazy3.getValue();
                            }
                            break;
                        case 147696667:
                            if (name.equals(IdentityNamingStrategy.HASH_CODE_KEY)) {
                                Lazy lazy4 = lazy;
                                KProperty kProperty4 = kProperty;
                                return lazy4.getValue();
                            }
                            break;
                        case 1444986633:
                            if (name.equals("annotationType")) {
                                return annotationClass;
                            }
                            break;
                    }
                }
                if (Intrinsics.areEqual(name, "equals") && args != null && args.length == 1) {
                    return Boolean.valueOf(anonymousClass2.invoke2(ArraysKt.single(args)));
                }
                if (values.containsKey(name)) {
                    return values.get(name);
                }
                StringBuilder sbAppend = new StringBuilder().append("Method is not supported: ").append(method).append(" (args: ");
                Object[] objArr = args;
                if (objArr == null) {
                    objArr = new Object[0];
                }
                throw new KotlinReflectionInternalError(sbAppend.append(ArraysKt.toList(objArr)).append(')').toString());
            }
        });
        if (t == null) {
            throw new NullPointerException("null cannot be cast to non-null type T");
        }
        return t;
    }

    /* compiled from: AnnotationConstructorCaller.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, d1 = {"��\u0010\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n\u0002\b\u0002\u0010��\u001a\u00020\u0001\"\b\b��\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"equals", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "other", "invoke"})
    /* renamed from: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/calls/AnnotationConstructorCallerKt$createAnnotationInstance$2.class */
    static final class AnonymousClass2 extends Lambda implements Function1<Object, Boolean> {
        final /* synthetic */ Class $annotationClass;
        final /* synthetic */ List $methods;
        final /* synthetic */ Map $values;

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
            return Boolean.valueOf(invoke2(obj));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Class cls, List list, Map map) {
            super(1);
            this.$annotationClass = cls;
            this.$methods = list;
            this.$values = map;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean invoke2(@org.jetbrains.annotations.Nullable java.lang.Object r7) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
            /*
                Method dump skipped, instructions count: 510
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.AnonymousClass2.invoke2(java.lang.Object):boolean");
        }
    }
}
