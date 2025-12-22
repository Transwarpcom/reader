package com.htmake.reader;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;

/* compiled from: ReaderApplication.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u001a\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0019\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007\"\u000e\u0010��\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��¨\u0006\b"}, d2 = {"logger", "Lmu/KLogger;", "main", "", "args", "", "", "([Ljava/lang/String;)V", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/ReaderApplicationKt.class */
public final class ReaderApplicationKt {

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(new Function0<Unit>() { // from class: com.htmake.reader.ReaderApplicationKt$logger$1
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }
    });

    public static final void main(@NotNull String[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        logger.info("Starting application with args: {}", (Object) args);
        SpringApplication.run((Class<?>) ReaderApplication.class, (String[]) Arrays.copyOf(args, args.length));
    }
}
