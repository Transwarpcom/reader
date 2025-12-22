package mu.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mu.KLogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* compiled from: KLoggerFactory.kt */
@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0082\bJ\u001e\u0010\u0007\u001a\u00020\b2\u000e\b\b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0080\b¢\u0006\u0002\b\fJ\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0080\b¢\u0006\u0002\b\fJ\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0080\b¢\u0006\u0002\b\fJ\u0011\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\b¨\u0006\u0010"}, d2 = {"Lmu/internal/KLoggerFactory;", "", "()V", "jLogger", "Lorg/slf4j/Logger;", "name", "", "logger", "Lmu/KLogger;", "func", "Lkotlin/Function0;", "", "logger$kotlin_logging", "loggable", "Lmu/KLoggable;", "wrapJLogger", "kotlin-logging"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-logging-1.6.24.jar:mu/internal/KLoggerFactory.class */
public final class KLoggerFactory {
    public static final KLoggerFactory INSTANCE = new KLoggerFactory();

    private KLoggerFactory() {
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:4:0x0028
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    @org.jetbrains.annotations.NotNull
    public final mu.KLogger logger$kotlin_logging(@org.jetbrains.annotations.NotNull mu.KLoggable r5) {
        /*
            r4 = this;
            r0 = 0
            r6 = r0
            r0 = r5
            java.lang.String r1 = "loggable"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r1)
            r0 = r4
            r7 = r0
            mu.internal.KLoggerNameResolver r0 = mu.internal.KLoggerNameResolver.INSTANCE
            r8 = r0
            r0 = r5
            java.lang.Class r0 = r0.getClass()
            r9 = r0
            r0 = 0
            r10 = r0
            r0 = r8
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = r9
            java.lang.Class r0 = r0.getEnclosingClass()
            if (r0 == 0) goto L60
        L29:
            r0 = r9
            java.lang.Class r0 = r0.getEnclosingClass()     // Catch: java.lang.Exception -> L5e
            r1 = r9
            java.lang.String r1 = r1.getSimpleName()     // Catch: java.lang.Exception -> L5e
            java.lang.reflect.Field r0 = r0.getField(r1)     // Catch: java.lang.Exception -> L5e
            r13 = r0
            r0 = r13
            int r0 = r0.getModifiers()     // Catch: java.lang.Exception -> L5e
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)     // Catch: java.lang.Exception -> L5e
            if (r0 == 0) goto L60
            r0 = r13
            java.lang.Class r0 = r0.getType()     // Catch: java.lang.Exception -> L5e
            r1 = r9
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)     // Catch: java.lang.Exception -> L5e
            if (r0 == 0) goto L60
            r0 = r9
            java.lang.Class r0 = r0.getEnclosingClass()     // Catch: java.lang.Exception -> L5e
            r1 = r0
            java.lang.String r2 = "clazz.enclosingClass"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)     // Catch: java.lang.Exception -> L5e
            goto L62
        L5e:
            r13 = move-exception
        L60:
            r0 = r9
        L62:
            java.lang.String r0 = r0.getName()
            r1 = r0
            java.lang.String r2 = "unwrapCompanionClass(forClass).name"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r7
            r10 = r0
            r0 = r7
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = r8
            org.slf4j.Logger r0 = org.slf4j.LoggerFactory.getLogger(r0)
            r1 = r0
            java.lang.String r2 = "LoggerFactory.getLogger(name)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = r11
            boolean r0 = r0 instanceof org.slf4j.spi.LocationAwareLogger
            if (r0 == 0) goto La3
            mu.internal.LocationAwareKLogger r0 = new mu.internal.LocationAwareKLogger
            r1 = r0
            r2 = r11
            org.slf4j.spi.LocationAwareLogger r2 = (org.slf4j.spi.LocationAwareLogger) r2
            r1.<init>(r2)
            mu.KLogger r0 = (mu.KLogger) r0
            goto Laf
        La3:
            mu.internal.LocationIgnorantKLogger r0 = new mu.internal.LocationIgnorantKLogger
            r1 = r0
            r2 = r11
            r1.<init>(r2)
            mu.KLogger r0 = (mu.KLogger) r0
        Laf:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: mu.internal.KLoggerFactory.logger$kotlin_logging(mu.KLoggable):mu.KLogger");
    }

    @NotNull
    public final KLogger logger$kotlin_logging(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Logger jLogger$iv = LoggerFactory.getLogger(name);
        Intrinsics.checkExpressionValueIsNotNull(jLogger$iv, "LoggerFactory.getLogger(name)");
        if (jLogger$iv instanceof LocationAwareLogger) {
            return new LocationAwareKLogger((LocationAwareLogger) jLogger$iv);
        }
        return new LocationIgnorantKLogger(jLogger$iv);
    }

    @NotNull
    public final KLogger logger$kotlin_logging(@NotNull Function0<Unit> func) {
        String strSubstringBefore$default;
        Intrinsics.checkParameterIsNotNull(func, "func");
        KLoggerNameResolver kLoggerNameResolver = KLoggerNameResolver.INSTANCE;
        String name$iv = func.getClass().getName();
        if (StringsKt.contains$default((CharSequence) name$iv, (CharSequence) "Kt$", false, 2, (Object) null)) {
            strSubstringBefore$default = StringsKt.substringBefore$default(name$iv, "Kt$", (String) null, 2, (Object) null);
        } else {
            strSubstringBefore$default = StringsKt.contains$default((CharSequence) name$iv, (CharSequence) PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, false, 2, (Object) null) ? StringsKt.substringBefore$default(name$iv, PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, (String) null, 2, (Object) null) : name$iv;
        }
        String slicedName = strSubstringBefore$default;
        Intrinsics.checkExpressionValueIsNotNull(slicedName, "slicedName");
        Logger jLogger$iv$iv = LoggerFactory.getLogger(slicedName);
        Intrinsics.checkExpressionValueIsNotNull(jLogger$iv$iv, "LoggerFactory.getLogger(name)");
        if (jLogger$iv$iv instanceof LocationAwareLogger) {
            return new LocationAwareKLogger((LocationAwareLogger) jLogger$iv$iv);
        }
        return new LocationIgnorantKLogger(jLogger$iv$iv);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Logger jLogger(String name) {
        Logger logger = LoggerFactory.getLogger(name);
        Intrinsics.checkExpressionValueIsNotNull(logger, "LoggerFactory.getLogger(name)");
        return logger;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KLogger wrapJLogger(Logger jLogger) {
        if (jLogger instanceof LocationAwareLogger) {
            return new LocationAwareKLogger((LocationAwareLogger) jLogger);
        }
        return new LocationIgnorantKLogger(jLogger);
    }
}
