package io.vertx.kotlin.ext.shell;

import io.vertx.ext.shell.ShellServiceOptions;
import io.vertx.ext.shell.term.HttpTermOptions;
import io.vertx.ext.shell.term.SSHTermOptions;
import io.vertx.ext.shell.term.TelnetTermOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ShellServiceOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��*\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001aU\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0002\u0010\r\u001aS\u0010\u000e\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\r¨\u0006\u000f"}, d2 = {"ShellServiceOptions", "Lio/vertx/ext/shell/ShellServiceOptions;", "httpOptions", "Lio/vertx/ext/shell/term/HttpTermOptions;", "reaperInterval", "", "sessionTimeout", "sshOptions", "Lio/vertx/ext/shell/term/SSHTermOptions;", "telnetOptions", "Lio/vertx/ext/shell/term/TelnetTermOptions;", "welcomeMessage", "", "(Lio/vertx/ext/shell/term/HttpTermOptions;Ljava/lang/Long;Ljava/lang/Long;Lio/vertx/ext/shell/term/SSHTermOptions;Lio/vertx/ext/shell/term/TelnetTermOptions;Ljava/lang/String;)Lio/vertx/ext/shell/ShellServiceOptions;", "shellServiceOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/shell/ShellServiceOptionsKt.class */
public final class ShellServiceOptionsKt {
    @NotNull
    public static /* synthetic */ ShellServiceOptions shellServiceOptionsOf$default(HttpTermOptions httpTermOptions, Long l, Long l2, SSHTermOptions sSHTermOptions, TelnetTermOptions telnetTermOptions, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            httpTermOptions = (HttpTermOptions) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            l2 = (Long) null;
        }
        if ((i & 8) != 0) {
            sSHTermOptions = (SSHTermOptions) null;
        }
        if ((i & 16) != 0) {
            telnetTermOptions = (TelnetTermOptions) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        return shellServiceOptionsOf(httpTermOptions, l, l2, sSHTermOptions, telnetTermOptions, str);
    }

    @NotNull
    public static final ShellServiceOptions shellServiceOptionsOf(@Nullable HttpTermOptions httpOptions, @Nullable Long reaperInterval, @Nullable Long sessionTimeout, @Nullable SSHTermOptions sshOptions, @Nullable TelnetTermOptions telnetOptions, @Nullable String welcomeMessage) {
        ShellServiceOptions $this$apply = new ShellServiceOptions();
        if (httpOptions != null) {
            $this$apply.setHttpOptions(httpOptions);
        }
        if (reaperInterval != null) {
            $this$apply.setReaperInterval(reaperInterval.longValue());
        }
        if (sessionTimeout != null) {
            $this$apply.setSessionTimeout(sessionTimeout.longValue());
        }
        if (sshOptions != null) {
            $this$apply.setSSHOptions(sshOptions);
        }
        if (telnetOptions != null) {
            $this$apply.setTelnetOptions(telnetOptions);
        }
        if (welcomeMessage != null) {
            $this$apply.setWelcomeMessage(welcomeMessage);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "shellServiceOptionsOf(httpOptions, reaperInterval, sessionTimeout, sshOptions, telnetOptions, welcomeMessage)"))
    @NotNull
    public static /* synthetic */ ShellServiceOptions ShellServiceOptions$default(HttpTermOptions httpTermOptions, Long l, Long l2, SSHTermOptions sSHTermOptions, TelnetTermOptions telnetTermOptions, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            httpTermOptions = (HttpTermOptions) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            l2 = (Long) null;
        }
        if ((i & 8) != 0) {
            sSHTermOptions = (SSHTermOptions) null;
        }
        if ((i & 16) != 0) {
            telnetTermOptions = (TelnetTermOptions) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        return ShellServiceOptions(httpTermOptions, l, l2, sSHTermOptions, telnetTermOptions, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "shellServiceOptionsOf(httpOptions, reaperInterval, sessionTimeout, sshOptions, telnetOptions, welcomeMessage)"))
    @NotNull
    public static final ShellServiceOptions ShellServiceOptions(@Nullable HttpTermOptions httpOptions, @Nullable Long reaperInterval, @Nullable Long sessionTimeout, @Nullable SSHTermOptions sshOptions, @Nullable TelnetTermOptions telnetOptions, @Nullable String welcomeMessage) {
        ShellServiceOptions $this$apply = new ShellServiceOptions();
        if (httpOptions != null) {
            $this$apply.setHttpOptions(httpOptions);
        }
        if (reaperInterval != null) {
            $this$apply.setReaperInterval(reaperInterval.longValue());
        }
        if (sessionTimeout != null) {
            $this$apply.setSessionTimeout(sessionTimeout.longValue());
        }
        if (sshOptions != null) {
            $this$apply.setSSHOptions(sshOptions);
        }
        if (telnetOptions != null) {
            $this$apply.setTelnetOptions(telnetOptions);
        }
        if (welcomeMessage != null) {
            $this$apply.setWelcomeMessage(welcomeMessage);
        }
        return $this$apply;
    }
}
