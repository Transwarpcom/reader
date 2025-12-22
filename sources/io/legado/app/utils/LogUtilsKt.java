package io.legado.app.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: LogUtils.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n��\u001a\n\u0010��\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"printOnDebug", "", "", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/utils/LogUtilsKt.class */
public final class LogUtilsKt {
    public static final void printOnDebug(@NotNull Throwable $this$printOnDebug) {
        Intrinsics.checkNotNullParameter($this$printOnDebug, "<this>");
        $this$printOnDebug.printStackTrace();
    }
}
