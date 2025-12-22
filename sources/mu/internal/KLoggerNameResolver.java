package mu.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* compiled from: KLoggerNameResolver.kt */
@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u000e\b\b\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0080\b¢\u0006\u0002\b\bJ&\u0010\u0003\u001a\u00020\u0004\"\b\b��\u0010\t*\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH\u0080\b¢\u0006\u0002\b\bJ%\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b\"\b\b��\u0010\t*\u00020\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH\u0082\b¨\u0006\u000e"}, d2 = {"Lmu/internal/KLoggerNameResolver;", "", "()V", "name", "", "func", "Lkotlin/Function0;", "", "name$kotlin_logging", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "forClass", "Ljava/lang/Class;", "unwrapCompanionClass", "clazz", "kotlin-logging"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-logging-1.6.24.jar:mu/internal/KLoggerNameResolver.class */
public final class KLoggerNameResolver {
    public static final KLoggerNameResolver INSTANCE = new KLoggerNameResolver();

    private KLoggerNameResolver() {
    }

    @NotNull
    public final String name$kotlin_logging(@NotNull Function0<Unit> func) {
        String strSubstringBefore$default;
        Intrinsics.checkParameterIsNotNull(func, "func");
        String name = func.getClass().getName();
        if (StringsKt.contains$default((CharSequence) name, (CharSequence) "Kt$", false, 2, (Object) null)) {
            strSubstringBefore$default = StringsKt.substringBefore$default(name, "Kt$", (String) null, 2, (Object) null);
        } else {
            strSubstringBefore$default = StringsKt.contains$default((CharSequence) name, (CharSequence) PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, false, 2, (Object) null) ? StringsKt.substringBefore$default(name, PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, (String) null, 2, (Object) null) : name;
        }
        String slicedName = strSubstringBefore$default;
        Intrinsics.checkExpressionValueIsNotNull(slicedName, "slicedName");
        return slicedName;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:4:0x0015
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    @org.jetbrains.annotations.NotNull
    public final <T> java.lang.String name$kotlin_logging(@org.jetbrains.annotations.NotNull java.lang.Class<T> r5) {
        /*
            r4 = this;
            r0 = 0
            r6 = r0
            r0 = r5
            java.lang.String r1 = "forClass"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r1)
            r0 = r4
            r7 = r0
            r0 = 0
            r8 = r0
            r0 = r5
            java.lang.Class r0 = r0.getEnclosingClass()
            if (r0 == 0) goto L49
        L16:
            r0 = r5
            java.lang.Class r0 = r0.getEnclosingClass()     // Catch: java.lang.Exception -> L47
            r1 = r5
            java.lang.String r1 = r1.getSimpleName()     // Catch: java.lang.Exception -> L47
            java.lang.reflect.Field r0 = r0.getField(r1)     // Catch: java.lang.Exception -> L47
            r9 = r0
            r0 = r9
            int r0 = r0.getModifiers()     // Catch: java.lang.Exception -> L47
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)     // Catch: java.lang.Exception -> L47
            if (r0 == 0) goto L49
            r0 = r9
            java.lang.Class r0 = r0.getType()     // Catch: java.lang.Exception -> L47
            r1 = r5
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)     // Catch: java.lang.Exception -> L47
            if (r0 == 0) goto L49
            r0 = r5
            java.lang.Class r0 = r0.getEnclosingClass()     // Catch: java.lang.Exception -> L47
            r1 = r0
            java.lang.String r2 = "clazz.enclosingClass"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)     // Catch: java.lang.Exception -> L47
            goto L4a
        L47:
            r9 = move-exception
        L49:
            r0 = r5
        L4a:
            java.lang.String r0 = r0.getName()
            r1 = r0
            java.lang.String r2 = "unwrapCompanionClass(forClass).name"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: mu.internal.KLoggerNameResolver.name$kotlin_logging(java.lang.Class):java.lang.String");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:4:0x000a
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    /* JADX INFO: Access modifiers changed from: private */
    public final <T> java.lang.Class<?> unwrapCompanionClass(java.lang.Class<T> r5) {
        /*
            r4 = this;
            r0 = 0
            r6 = r0
            r0 = r5
            java.lang.Class r0 = r0.getEnclosingClass()
            if (r0 == 0) goto L38
        Lb:
            r0 = r5
            java.lang.Class r0 = r0.getEnclosingClass()     // Catch: java.lang.Exception -> L37
            r1 = r5
            java.lang.String r1 = r1.getSimpleName()     // Catch: java.lang.Exception -> L37
            java.lang.reflect.Field r0 = r0.getField(r1)     // Catch: java.lang.Exception -> L37
            r7 = r0
            r0 = r7
            int r0 = r0.getModifiers()     // Catch: java.lang.Exception -> L37
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)     // Catch: java.lang.Exception -> L37
            if (r0 == 0) goto L38
            r0 = r7
            java.lang.Class r0 = r0.getType()     // Catch: java.lang.Exception -> L37
            r1 = r5
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)     // Catch: java.lang.Exception -> L37
            if (r0 == 0) goto L38
            r0 = r5
            java.lang.Class r0 = r0.getEnclosingClass()     // Catch: java.lang.Exception -> L37
            r1 = r0
            java.lang.String r2 = "clazz.enclosingClass"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)     // Catch: java.lang.Exception -> L37
            return r0
        L37:
            r7 = move-exception
        L38:
            r0 = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: mu.internal.KLoggerNameResolver.unwrapCompanionClass(java.lang.Class):java.lang.Class");
    }
}
