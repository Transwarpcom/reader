package io.vertx.kotlin.ext.shell;

import io.vertx.ext.shell.ShellServerOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ShellServerOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"ShellServerOptions", "Lio/vertx/ext/shell/ShellServerOptions;", "reaperInterval", "", "sessionTimeout", "welcomeMessage", "", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)Lio/vertx/ext/shell/ShellServerOptions;", "shellServerOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/shell/ShellServerOptionsKt.class */
public final class ShellServerOptionsKt {
    @NotNull
    public static /* synthetic */ ShellServerOptions shellServerOptionsOf$default(Long l, Long l2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        return shellServerOptionsOf(l, l2, str);
    }

    @NotNull
    public static final ShellServerOptions shellServerOptionsOf(@Nullable Long reaperInterval, @Nullable Long sessionTimeout, @Nullable String welcomeMessage) {
        ShellServerOptions $this$apply = new ShellServerOptions();
        if (reaperInterval != null) {
            $this$apply.setReaperInterval(reaperInterval.longValue());
        }
        if (sessionTimeout != null) {
            $this$apply.setSessionTimeout(sessionTimeout.longValue());
        }
        if (welcomeMessage != null) {
            $this$apply.setWelcomeMessage(welcomeMessage);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "shellServerOptionsOf(reaperInterval, sessionTimeout, welcomeMessage)"))
    @NotNull
    public static /* synthetic */ ShellServerOptions ShellServerOptions$default(Long l, Long l2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        return ShellServerOptions(l, l2, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "shellServerOptionsOf(reaperInterval, sessionTimeout, welcomeMessage)"))
    @NotNull
    public static final ShellServerOptions ShellServerOptions(@Nullable Long reaperInterval, @Nullable Long sessionTimeout, @Nullable String welcomeMessage) {
        ShellServerOptions $this$apply = new ShellServerOptions();
        if (reaperInterval != null) {
            $this$apply.setReaperInterval(reaperInterval.longValue());
        }
        if (sessionTimeout != null) {
            $this$apply.setSessionTimeout(sessionTimeout.longValue());
        }
        if (welcomeMessage != null) {
            $this$apply.setWelcomeMessage(welcomeMessage);
        }
        return $this$apply;
    }
}
