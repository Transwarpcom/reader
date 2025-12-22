package kotlin.jvm;

import java.lang.annotation.Annotation;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JvmClassMapping.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��,\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010��\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u001f\u0010\u0018\u001a\u00020\u0019\"\n\b��\u0010\u0002\u0018\u0001*\u00020\r*\u0006\u0012\u0002\b\u00030\u001a¢\u0006\u0002\u0010\u001b\"'\u0010��\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\b\b��\u0010\u0002*\u00020\u0003*\u0002H\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"-\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00018G¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"&\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b��\u0010\u0002*\u00020\r*\u0002H\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\u000e\";\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007\"\b\b��\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018Ç\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\t\u001a\u0004\b\u0010\u0010\u000b\"+\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b��\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000b\"-\u0010\u0013\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0007\"\b\b��\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000b\"+\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b��\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001c"}, d2 = {"annotationClass", "Lkotlin/reflect/KClass;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "java", "Ljava/lang/Class;", "getJavaClass$annotations", "(Lkotlin/reflect/KClass;)V", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "javaClass", "", "(Ljava/lang/Object;)Ljava/lang/Class;", "getRuntimeClassOfKClassInstance$annotations", "getRuntimeClassOfKClassInstance", "javaObjectType", "getJavaObjectType", "javaPrimitiveType", "getJavaPrimitiveType", "kotlin", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "isArrayOf", "", "", "([Ljava/lang/Object;)Z", "kotlin-stdlib"})
@JvmName(name = "JvmClassMappingKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/jvm/JvmClassMappingKt.class */
public final class JvmClassMappingKt {
    public static /* synthetic */ void getJavaClass$annotations(KClass kClass) {
    }

    @Deprecated(message = "Use 'java' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.", replaceWith = @ReplaceWith(imports = {}, expression = "(this as Any).javaClass"), level = DeprecationLevel.ERROR)
    public static /* synthetic */ void getRuntimeClassOfKClassInstance$annotations(KClass kClass) {
    }

    @JvmName(name = "getJavaClass")
    @NotNull
    public static final <T> Class<T> getJavaClass(@NotNull KClass<T> java) {
        Intrinsics.checkNotNullParameter(java, "$this$java");
        Class<T> cls = (Class<T>) ((ClassBasedDeclarationContainer) java).getJClass();
        if (cls == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        return cls;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    @Nullable
    public static final <T> Class<T> getJavaPrimitiveType(@NotNull KClass<T> javaPrimitiveType) {
        Intrinsics.checkNotNullParameter(javaPrimitiveType, "$this$javaPrimitiveType");
        Class thisJClass = ((ClassBasedDeclarationContainer) javaPrimitiveType).getJClass();
        if (thisJClass.isPrimitive()) {
            if (thisJClass == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
            }
            return thisJClass;
        }
        String name = thisJClass.getName();
        if (name != null) {
            switch (name.hashCode()) {
                case -2056817302:
                    if (name.equals("java.lang.Integer")) {
                        return Integer.TYPE;
                    }
                    break;
                case -527879800:
                    if (name.equals("java.lang.Float")) {
                        return Float.TYPE;
                    }
                    break;
                case -515992664:
                    if (name.equals("java.lang.Short")) {
                        return Short.TYPE;
                    }
                    break;
                case 155276373:
                    if (name.equals("java.lang.Character")) {
                        return Character.TYPE;
                    }
                    break;
                case 344809556:
                    if (name.equals("java.lang.Boolean")) {
                        return Boolean.TYPE;
                    }
                    break;
                case 398507100:
                    if (name.equals("java.lang.Byte")) {
                        return Byte.TYPE;
                    }
                    break;
                case 398795216:
                    if (name.equals("java.lang.Long")) {
                        return Long.TYPE;
                    }
                    break;
                case 399092968:
                    if (name.equals("java.lang.Void")) {
                        return Void.TYPE;
                    }
                    break;
                case 761287205:
                    if (name.equals("java.lang.Double")) {
                        return Double.TYPE;
                    }
                    break;
            }
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0125  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Class<T> getJavaObjectType(@org.jetbrains.annotations.NotNull kotlin.reflect.KClass<T> r5) {
        /*
            r0 = r5
            java.lang.String r1 = "$this$javaObjectType"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r5
            kotlin.jvm.internal.ClassBasedDeclarationContainer r0 = (kotlin.jvm.internal.ClassBasedDeclarationContainer) r0
            java.lang.Class r0 = r0.getJClass()
            r6 = r0
            r0 = r6
            boolean r0 = r0.isPrimitive()
            if (r0 != 0) goto L27
            r0 = r6
            r1 = r0
            if (r1 != 0) goto L26
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            r2 = r1
            java.lang.String r3 = "null cannot be cast to non-null type java.lang.Class<T>"
            r2.<init>(r3)
            throw r1
        L26:
            return r0
        L27:
            r0 = r6
            java.lang.String r0 = r0.getName()
            r1 = r0
            if (r1 != 0) goto L33
        L30:
            goto L125
        L33:
            r7 = r0
            r0 = r7
            int r0 = r0.hashCode()
            switch(r0) {
                case -1325958191: goto Lb0;
                case 104431: goto Le0;
                case 3039496: goto La4;
                case 3052374: goto Lbc;
                case 3327612: goto Lec;
                case 3625364: goto L98;
                case 64711720: goto L8c;
                case 97526364: goto Ld4;
                case 109413500: goto Lc8;
                default: goto L125;
            }
        L8c:
            r0 = r7
            java.lang.String r1 = "boolean"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L125
            goto Lf8
        L98:
            r0 = r7
            java.lang.String r1 = "void"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L125
            goto L120
        La4:
            r0 = r7
            java.lang.String r1 = "byte"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L125
            goto L102
        Lb0:
            r0 = r7
            java.lang.String r1 = "double"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L125
            goto L11b
        Lbc:
            r0 = r7
            java.lang.String r1 = "char"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L125
            goto Lfd
        Lc8:
            r0 = r7
            java.lang.String r1 = "short"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L125
            goto L107
        Ld4:
            r0 = r7
            java.lang.String r1 = "float"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L125
            goto L111
        Le0:
            r0 = r7
            java.lang.String r1 = "int"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L125
            goto L10c
        Lec:
            r0 = r7
            java.lang.String r1 = "long"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L125
            goto L116
        Lf8:
            java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
            goto L126
        Lfd:
            java.lang.Class<java.lang.Character> r0 = java.lang.Character.class
            goto L126
        L102:
            java.lang.Class<java.lang.Byte> r0 = java.lang.Byte.class
            goto L126
        L107:
            java.lang.Class<java.lang.Short> r0 = java.lang.Short.class
            goto L126
        L10c:
            java.lang.Class<java.lang.Integer> r0 = java.lang.Integer.class
            goto L126
        L111:
            java.lang.Class<java.lang.Float> r0 = java.lang.Float.class
            goto L126
        L116:
            java.lang.Class<java.lang.Long> r0 = java.lang.Long.class
            goto L126
        L11b:
            java.lang.Class<java.lang.Double> r0 = java.lang.Double.class
            goto L126
        L120:
            java.lang.Class<java.lang.Void> r0 = java.lang.Void.class
            goto L126
        L125:
            r0 = r6
        L126:
            r1 = r0
            if (r1 != 0) goto L134
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            r2 = r1
            java.lang.String r3 = "null cannot be cast to non-null type java.lang.Class<T>"
            r2.<init>(r3)
            throw r1
        L134:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.JvmClassMappingKt.getJavaObjectType(kotlin.reflect.KClass):java.lang.Class");
    }

    @JvmName(name = "getKotlinClass")
    @NotNull
    public static final <T> KClass<T> getKotlinClass(@NotNull Class<T> kotlin2) {
        Intrinsics.checkNotNullParameter(kotlin2, "$this$kotlin");
        return Reflection.getOrCreateKotlinClass(kotlin2);
    }

    @NotNull
    public static final <T> Class<T> getJavaClass(@NotNull T javaClass) {
        Intrinsics.checkNotNullParameter(javaClass, "$this$javaClass");
        Class<T> cls = (Class<T>) javaClass.getClass();
        if (cls == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        return cls;
    }

    @JvmName(name = "getRuntimeClassOfKClassInstance")
    @NotNull
    public static final <T> Class<KClass<T>> getRuntimeClassOfKClassInstance(@NotNull KClass<T> javaClass) {
        Intrinsics.checkNotNullParameter(javaClass, "$this$javaClass");
        Class<KClass<T>> cls = (Class<KClass<T>>) javaClass.getClass();
        if (cls == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<kotlin.reflect.KClass<T>>");
        }
        return cls;
    }

    public static final /* synthetic */ <T> boolean isArrayOf(Object[] isArrayOf) {
        Intrinsics.checkNotNullParameter(isArrayOf, "$this$isArrayOf");
        Intrinsics.reifiedOperationMarker(4, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
        return Object.class.isAssignableFrom(isArrayOf.getClass().getComponentType());
    }

    @NotNull
    public static final <T extends Annotation> KClass<? extends T> getAnnotationClass(@NotNull T annotationClass) {
        Intrinsics.checkNotNullParameter(annotationClass, "$this$annotationClass");
        Class<? extends Annotation> clsAnnotationType = annotationClass.annotationType();
        Intrinsics.checkNotNullExpressionValue(clsAnnotationType, "(this as java.lang.annot…otation).annotationType()");
        KClass<? extends T> kotlinClass = getKotlinClass(clsAnnotationType);
        if (kotlinClass == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.KClass<out T>");
        }
        return kotlinClass;
    }
}
