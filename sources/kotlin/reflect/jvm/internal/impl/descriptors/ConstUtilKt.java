package kotlin.reflect.jvm.internal.impl.descriptors;

/* compiled from: ConstUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/ConstUtilKt.class */
public final class ConstUtilKt {
    /* JADX WARN: Removed duplicated region for block: B:6:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean canBeUsedForConstVal(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.KotlinType r3) {
        /*
            r0 = r3
            java.lang.String r1 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r3
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isPrimitiveType(r0)
            if (r0 != 0) goto L18
            kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes r0 = kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes.INSTANCE
            r0 = r3
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes.isUnsignedType(r0)
            if (r0 == 0) goto L1f
        L18:
            r0 = r3
            boolean r0 = kotlin.reflect.jvm.internal.impl.types.TypeUtils.isNullableType(r0)
            if (r0 == 0) goto L26
        L1f:
            r0 = r3
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isString(r0)
            if (r0 == 0) goto L2a
        L26:
            r0 = 1
            goto L2b
        L2a:
            r0 = 0
        L2b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.ConstUtilKt.canBeUsedForConstVal(kotlin.reflect.jvm.internal.impl.types.KotlinType):boolean");
    }
}
