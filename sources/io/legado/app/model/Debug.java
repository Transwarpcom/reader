package io.legado.app.model;

import io.legado.app.model.DebugLog;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Debug.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/legado/app/model/Debug;", "Lio/legado/app/model/DebugLog;", "()V", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/Debug.class */
public final class Debug implements DebugLog {

    @NotNull
    public static final Debug INSTANCE = new Debug();

    private Debug() {
    }

    @Override // io.legado.app.model.DebugLog, okhttp3.logging.HttpLoggingInterceptor.Logger
    public void log(@NotNull String message) {
        DebugLog.DefaultImpls.log(this, message);
    }

    @Override // io.legado.app.model.DebugLog
    public void log(@Nullable String sourceUrl, @Nullable String msg, boolean isHtml) {
        DebugLog.DefaultImpls.log(this, sourceUrl, msg, isHtml);
    }
}
