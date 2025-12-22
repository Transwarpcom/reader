package io.legado.app.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: RegexTimeoutException.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lio/legado/app/exception/RegexTimeoutException;", "Lio/legado/app/exception/NoStackTraceException;", "msg", "", "(Ljava/lang/String;)V", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/exception/RegexTimeoutException.class */
public final class RegexTimeoutException extends NoStackTraceException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RegexTimeoutException(@NotNull String msg) {
        super(msg);
        Intrinsics.checkNotNullParameter(msg, "msg");
    }
}
