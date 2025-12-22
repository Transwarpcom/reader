package mu;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import mu.internal.KLoggerFactory;
import mu.internal.LocationAwareKLogger;
import mu.internal.LocationIgnorantKLogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

/* compiled from: KLogging.kt */
@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\bf\u0018��2\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Lmu/KLoggable;", "", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "name", "", "kotlin-logging"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-logging-1.6.24.jar:mu/KLoggable.class */
public interface KLoggable {
    @NotNull
    KLogger getLogger();

    @NotNull
    KLogger logger();

    @NotNull
    KLogger logger(@NotNull String str);

    /* compiled from: KLogging.kt */
    @Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 3)
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-logging-1.6.24.jar:mu/KLoggable$DefaultImpls.class */
    public static final class DefaultImpls {
        /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
            jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:4:0x0028
            	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
            	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
            */
        @org.jetbrains.annotations.NotNull
        public static mu.KLogger logger(mu.KLoggable r4) {
            /*
                mu.internal.KLoggerFactory r0 = mu.internal.KLoggerFactory.INSTANCE
                r5 = r0
                r0 = r4
                r6 = r0
                r0 = 0
                r7 = r0
                r0 = r5
                r8 = r0
                mu.internal.KLoggerNameResolver r0 = mu.internal.KLoggerNameResolver.INSTANCE
                r9 = r0
                r0 = r6
                java.lang.Class r0 = r0.getClass()
                r10 = r0
                r0 = 0
                r11 = r0
                r0 = r9
                r12 = r0
                r0 = 0
                r13 = r0
                r0 = r10
                java.lang.Class r0 = r0.getEnclosingClass()
                if (r0 == 0) goto L60
            L29:
                r0 = r10
                java.lang.Class r0 = r0.getEnclosingClass()     // Catch: java.lang.Exception -> L5e
                r1 = r10
                java.lang.String r1 = r1.getSimpleName()     // Catch: java.lang.Exception -> L5e
                java.lang.reflect.Field r0 = r0.getField(r1)     // Catch: java.lang.Exception -> L5e
                r14 = r0
                r0 = r14
                int r0 = r0.getModifiers()     // Catch: java.lang.Exception -> L5e
                boolean r0 = java.lang.reflect.Modifier.isStatic(r0)     // Catch: java.lang.Exception -> L5e
                if (r0 == 0) goto L60
                r0 = r14
                java.lang.Class r0 = r0.getType()     // Catch: java.lang.Exception -> L5e
                r1 = r10
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)     // Catch: java.lang.Exception -> L5e
                if (r0 == 0) goto L60
                r0 = r10
                java.lang.Class r0 = r0.getEnclosingClass()     // Catch: java.lang.Exception -> L5e
                r1 = r0
                java.lang.String r2 = "clazz.enclosingClass"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)     // Catch: java.lang.Exception -> L5e
                goto L62
            L5e:
                r14 = move-exception
            L60:
                r0 = r10
            L62:
                java.lang.String r0 = r0.getName()
                r1 = r0
                java.lang.String r2 = "unwrapCompanionClass(forClass).name"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
                r9 = r0
                r0 = 0
                r10 = r0
                r0 = r8
                r11 = r0
                r0 = r8
                r12 = r0
                r0 = 0
                r13 = r0
                r0 = r9
                org.slf4j.Logger r0 = org.slf4j.LoggerFactory.getLogger(r0)
                r1 = r0
                java.lang.String r2 = "LoggerFactory.getLogger(name)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
                r12 = r0
                r0 = 0
                r13 = r0
                r0 = r12
                boolean r0 = r0 instanceof org.slf4j.spi.LocationAwareLogger
                if (r0 == 0) goto La5
                mu.internal.LocationAwareKLogger r0 = new mu.internal.LocationAwareKLogger
                r1 = r0
                r2 = r12
                org.slf4j.spi.LocationAwareLogger r2 = (org.slf4j.spi.LocationAwareLogger) r2
                r1.<init>(r2)
                mu.KLogger r0 = (mu.KLogger) r0
                goto Lb1
            La5:
                mu.internal.LocationIgnorantKLogger r0 = new mu.internal.LocationIgnorantKLogger
                r1 = r0
                r2 = r12
                r1.<init>(r2)
                mu.KLogger r0 = (mu.KLogger) r0
            Lb1:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: mu.KLoggable.DefaultImpls.logger(mu.KLoggable):mu.KLogger");
        }

        @NotNull
        public static KLogger logger(KLoggable $this, @NotNull String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            KLoggerFactory kLoggerFactory = KLoggerFactory.INSTANCE;
            Logger jLogger$iv$iv = LoggerFactory.getLogger(name);
            Intrinsics.checkExpressionValueIsNotNull(jLogger$iv$iv, "LoggerFactory.getLogger(name)");
            if (jLogger$iv$iv instanceof LocationAwareLogger) {
                return new LocationAwareKLogger((LocationAwareLogger) jLogger$iv$iv);
            }
            return new LocationIgnorantKLogger(jLogger$iv$iv);
        }
    }
}
