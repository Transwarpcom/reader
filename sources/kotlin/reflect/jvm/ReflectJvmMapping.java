package kotlin.reflect.jvm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReflectJvmMapping.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��J\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u001a\u000e\u0010%\u001a\u0004\u0018\u00010&*\u00020'H\u0002\"/\u0010��\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u001b\u0010\b\u001a\u0004\u0018\u00010\t*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\"\u0015\u0010\u0018\u001a\u00020\u0019*\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"-\u0010\u001d\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0003\"\b\b��\u0010\u0002*\u00020\u001e*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \"\u001b\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003*\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010!\"\u001b\u0010\"\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n*\u00020\t8F¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006("}, d2 = {"javaConstructor", "Ljava/lang/reflect/Constructor;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlin/reflect/KFunction;", "getJavaConstructor$annotations", "(Lkotlin/reflect/KFunction;)V", "getJavaConstructor", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Constructor;", "javaField", "Ljava/lang/reflect/Field;", "Lkotlin/reflect/KProperty;", "getJavaField", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Field;", "javaGetter", "Ljava/lang/reflect/Method;", "getJavaGetter", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Method;", "javaMethod", "getJavaMethod", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Method;", "javaSetter", "Lkotlin/reflect/KMutableProperty;", "getJavaSetter", "(Lkotlin/reflect/KMutableProperty;)Ljava/lang/reflect/Method;", "javaType", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "kotlinFunction", "", "getKotlinFunction", "(Ljava/lang/reflect/Constructor;)Lkotlin/reflect/KFunction;", "(Ljava/lang/reflect/Method;)Lkotlin/reflect/KFunction;", "kotlinProperty", "getKotlinProperty", "(Ljava/lang/reflect/Field;)Lkotlin/reflect/KProperty;", "getKPackage", "Lkotlin/reflect/KDeclarationContainer;", "Ljava/lang/reflect/Member;", "kotlin-reflection"})
@JvmName(name = "ReflectJvmMapping")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/ReflectJvmMapping.class */
public final class ReflectJvmMapping {
    public static /* synthetic */ void getJavaConstructor$annotations(KFunction kFunction) {
    }

    @Nullable
    public static final Field getJavaField(@NotNull KProperty<?> javaField) {
        Intrinsics.checkNotNullParameter(javaField, "$this$javaField");
        KPropertyImpl<?> kPropertyImplAsKPropertyImpl = UtilKt.asKPropertyImpl(javaField);
        if (kPropertyImplAsKPropertyImpl != null) {
            return kPropertyImplAsKPropertyImpl.getJavaField();
        }
        return null;
    }

    @Nullable
    public static final Method getJavaGetter(@NotNull KProperty<?> javaGetter) {
        Intrinsics.checkNotNullParameter(javaGetter, "$this$javaGetter");
        return getJavaMethod(javaGetter.getGetter());
    }

    @Nullable
    public static final Method getJavaSetter(@NotNull KMutableProperty<?> javaSetter) {
        Intrinsics.checkNotNullParameter(javaSetter, "$this$javaSetter");
        return getJavaMethod(javaSetter.getSetter());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.reflect.Method getJavaMethod(@org.jetbrains.annotations.NotNull kotlin.reflect.KFunction<?> r3) {
        /*
            r0 = r3
            java.lang.String r1 = "$this$javaMethod"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r3
            kotlin.reflect.jvm.internal.KCallableImpl r0 = kotlin.reflect.jvm.internal.UtilKt.asKCallableImpl(r0)
            r1 = r0
            if (r1 == 0) goto L1d
            kotlin.reflect.jvm.internal.calls.Caller r0 = r0.getCaller()
            r1 = r0
            if (r1 == 0) goto L1d
            java.lang.reflect.Member r0 = r0.mo3475getMember()
            goto L1f
        L1d:
            r0 = 0
        L1f:
            r1 = r0
            boolean r1 = r1 instanceof java.lang.reflect.Method
            if (r1 != 0) goto L28
        L27:
            r0 = 0
        L28:
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(kotlin.reflect.KFunction):java.lang.reflect.Method");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.reflect.Constructor<T> getJavaConstructor(@org.jetbrains.annotations.NotNull kotlin.reflect.KFunction<? extends T> r3) {
        /*
            r0 = r3
            java.lang.String r1 = "$this$javaConstructor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r3
            kotlin.reflect.jvm.internal.KCallableImpl r0 = kotlin.reflect.jvm.internal.UtilKt.asKCallableImpl(r0)
            r1 = r0
            if (r1 == 0) goto L1d
            kotlin.reflect.jvm.internal.calls.Caller r0 = r0.getCaller()
            r1 = r0
            if (r1 == 0) goto L1d
            java.lang.reflect.Member r0 = r0.mo3475getMember()
            goto L1f
        L1d:
            r0 = 0
        L1f:
            r1 = r0
            boolean r1 = r1 instanceof java.lang.reflect.Constructor
            if (r1 != 0) goto L28
        L27:
            r0 = 0
        L28:
            java.lang.reflect.Constructor r0 = (java.lang.reflect.Constructor) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.ReflectJvmMapping.getJavaConstructor(kotlin.reflect.KFunction):java.lang.reflect.Constructor");
    }

    @NotNull
    public static final Type getJavaType(@NotNull KType javaType) {
        Intrinsics.checkNotNullParameter(javaType, "$this$javaType");
        Type javaType2 = ((KTypeImpl) javaType).getJavaType();
        return javaType2 != null ? javaType2 : TypesJVMKt.getJavaType(javaType);
    }

    @Nullable
    public static final KProperty<?> getKotlinProperty(@NotNull Field kotlinProperty) {
        Object obj;
        Object obj2;
        Intrinsics.checkNotNullParameter(kotlinProperty, "$this$kotlinProperty");
        if (kotlinProperty.isSynthetic()) {
            return null;
        }
        KDeclarationContainer kotlinPackage = getKPackage(kotlinProperty);
        if (kotlinPackage == null) {
            Class<?> declaringClass = kotlinProperty.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "declaringClass");
            Iterable $this$firstOrNull$iv = KClasses.getMemberProperties(JvmClassMappingKt.getKotlinClass(declaringClass));
            Iterator it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                Object element$iv = it.next();
                KProperty1 it2 = (KProperty1) element$iv;
                if (Intrinsics.areEqual(getJavaField(it2), kotlinProperty)) {
                    obj = element$iv;
                    break;
                }
            }
            return (KProperty) obj;
        }
        Iterable $this$filterIsInstance$iv = kotlinPackage.getMembers();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof KProperty) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$firstOrNull$iv2 = (List) destination$iv$iv;
        Iterator it3 = $this$firstOrNull$iv2.iterator();
        while (true) {
            if (!it3.hasNext()) {
                obj2 = null;
                break;
            }
            Object element$iv2 = it3.next();
            KProperty it4 = (KProperty) element$iv2;
            if (Intrinsics.areEqual(getJavaField(it4), kotlinProperty)) {
                obj2 = element$iv2;
                break;
            }
        }
        return (KProperty) obj2;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final kotlin.reflect.KDeclarationContainer getKPackage(java.lang.reflect.Member r7) {
        /*
            kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass$Factory r0 = kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass.Factory
            r1 = r7
            java.lang.Class r1 = r1.getDeclaringClass()
            r2 = r1
            java.lang.String r3 = "declaringClass"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass r0 = r0.create(r1)
            r1 = r0
            if (r1 == 0) goto L23
            kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader r0 = r0.getClassHeader()
            r1 = r0
            if (r1 == 0) goto L23
            kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader$Kind r0 = r0.getKind()
            goto L25
        L23:
            r0 = 0
        L25:
            r1 = r0
            if (r1 != 0) goto L2d
        L2a:
            goto L6c
        L2d:
            int[] r1 = kotlin.reflect.jvm.ReflectJvmMapping.WhenMappings.$EnumSwitchMapping$0
            r2 = r0; r0 = r1; r1 = r2; 
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L50;
                case 2: goto L50;
                case 3: goto L50;
                default: goto L6c;
            }
        L50:
            kotlin.reflect.jvm.internal.KPackageImpl r0 = new kotlin.reflect.jvm.internal.KPackageImpl
            r1 = r0
            r2 = r7
            java.lang.Class r2 = r2.getDeclaringClass()
            r3 = r2
            java.lang.String r4 = "declaringClass"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            r3 = 0
            r4 = 2
            r5 = 0
            r1.<init>(r2, r3, r4, r5)
            kotlin.reflect.KDeclarationContainer r0 = (kotlin.reflect.KDeclarationContainer) r0
            goto L6d
        L6c:
            r0 = 0
        L6d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.ReflectJvmMapping.getKPackage(java.lang.reflect.Member):kotlin.reflect.KDeclarationContainer");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0148 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[LOOP:2: B:24:0x00db->B:65:?, LOOP_END, SYNTHETIC] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.reflect.KFunction<?> getKotlinFunction(@org.jetbrains.annotations.NotNull java.lang.reflect.Method r4) {
        /*
            Method dump skipped, instructions count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.ReflectJvmMapping.getKotlinFunction(java.lang.reflect.Method):kotlin.reflect.KFunction");
    }

    @Nullable
    public static final <T> KFunction<T> getKotlinFunction(@NotNull Constructor<T> kotlinFunction) {
        Object obj;
        Intrinsics.checkNotNullParameter(kotlinFunction, "$this$kotlinFunction");
        Class<T> declaringClass = kotlinFunction.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "declaringClass");
        Iterable $this$firstOrNull$iv = JvmClassMappingKt.getKotlinClass(declaringClass).getConstructors();
        Iterator<T> it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                KFunction it2 = (KFunction) element$iv;
                if (Intrinsics.areEqual(getJavaConstructor(it2), kotlinFunction)) {
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
}
