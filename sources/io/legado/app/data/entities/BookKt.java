package io.legado.app.data.entities;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;

/* compiled from: Book.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010��\u001a\u00020\u0001¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/data/entities/BookKt.class */
public final class BookKt {

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(new Function0<Unit>() { // from class: io.legado.app.data.entities.BookKt$logger$1
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }
    });

    @NotNull
    public static final KLogger getLogger() {
        return logger;
    }
}
