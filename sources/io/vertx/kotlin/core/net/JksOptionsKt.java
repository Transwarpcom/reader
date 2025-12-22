package io.vertx.kotlin.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.JksOptions;
import io.vertx.ext.web.handler.FormLoginHandler;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JksOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u001a*\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\b"}, d2 = {"JksOptions", "Lio/vertx/core/net/JksOptions;", FormLoginHandler.DEFAULT_PASSWORD_PARAM, "", "path", "value", "Lio/vertx/core/buffer/Buffer;", "jksOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/net/JksOptionsKt.class */
public final class JksOptionsKt {
    @NotNull
    public static /* synthetic */ JksOptions jksOptionsOf$default(String str, String str2, Buffer buffer, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            buffer = (Buffer) null;
        }
        return jksOptionsOf(str, str2, buffer);
    }

    @NotNull
    public static final JksOptions jksOptionsOf(@Nullable String password, @Nullable String path, @Nullable Buffer value) {
        JksOptions $this$apply = new JksOptions();
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (path != null) {
            $this$apply.setPath(path);
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jksOptionsOf(password, path, value)"))
    @NotNull
    public static /* synthetic */ JksOptions JksOptions$default(String str, String str2, Buffer buffer, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            buffer = (Buffer) null;
        }
        return JksOptions(str, str2, buffer);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jksOptionsOf(password, path, value)"))
    @NotNull
    public static final JksOptions JksOptions(@Nullable String password, @Nullable String path, @Nullable Buffer value) {
        JksOptions $this$apply = new JksOptions();
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (path != null) {
            $this$apply.setPath(path);
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }
}
