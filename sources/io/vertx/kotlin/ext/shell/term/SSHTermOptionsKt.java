package io.vertx.kotlin.ext.shell.term;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.ext.shell.term.SSHTermOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SSHTermOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��*\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\u001aa\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u0007¢\u0006\u0002\u0010\u000e\u001a_\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000e¨\u0006\u0010"}, d2 = {"SSHTermOptions", "Lio/vertx/ext/shell/term/SSHTermOptions;", "defaultCharset", "", "host", "intputrc", "keyPairOptions", "Lio/vertx/core/net/JksOptions;", "pemKeyPairOptions", "Lio/vertx/core/net/PemKeyCertOptions;", "pfxKeyPairOptions", "Lio/vertx/core/net/PfxOptions;", RtspHeaders.Values.PORT, "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/vertx/core/net/JksOptions;Lio/vertx/core/net/PemKeyCertOptions;Lio/vertx/core/net/PfxOptions;Ljava/lang/Integer;)Lio/vertx/ext/shell/term/SSHTermOptions;", "sshTermOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/shell/term/SSHTermOptionsKt.class */
public final class SSHTermOptionsKt {
    @NotNull
    public static /* synthetic */ SSHTermOptions sshTermOptionsOf$default(String str, String str2, String str3, JksOptions jksOptions, PemKeyCertOptions pemKeyCertOptions, PfxOptions pfxOptions, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        if ((i & 8) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 16) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i & 32) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i & 64) != 0) {
            num = (Integer) null;
        }
        return sshTermOptionsOf(str, str2, str3, jksOptions, pemKeyCertOptions, pfxOptions, num);
    }

    @NotNull
    public static final SSHTermOptions sshTermOptionsOf(@Nullable String defaultCharset, @Nullable String host, @Nullable String intputrc, @Nullable JksOptions keyPairOptions, @Nullable PemKeyCertOptions pemKeyPairOptions, @Nullable PfxOptions pfxKeyPairOptions, @Nullable Integer port) {
        SSHTermOptions $this$apply = new SSHTermOptions();
        if (defaultCharset != null) {
            $this$apply.setDefaultCharset(defaultCharset);
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (intputrc != null) {
            $this$apply.setIntputrc(intputrc);
        }
        if (keyPairOptions != null) {
            $this$apply.setKeyPairOptions(keyPairOptions);
        }
        if (pemKeyPairOptions != null) {
            $this$apply.setPemKeyPairOptions(pemKeyPairOptions);
        }
        if (pfxKeyPairOptions != null) {
            $this$apply.setPfxKeyPairOptions(pfxKeyPairOptions);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "sshTermOptionsOf(defaultCharset, host, intputrc, keyPairOptions, pemKeyPairOptions, pfxKeyPairOptions, port)"))
    @NotNull
    public static /* synthetic */ SSHTermOptions SSHTermOptions$default(String str, String str2, String str3, JksOptions jksOptions, PemKeyCertOptions pemKeyCertOptions, PfxOptions pfxOptions, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        if ((i & 8) != 0) {
            jksOptions = (JksOptions) null;
        }
        if ((i & 16) != 0) {
            pemKeyCertOptions = (PemKeyCertOptions) null;
        }
        if ((i & 32) != 0) {
            pfxOptions = (PfxOptions) null;
        }
        if ((i & 64) != 0) {
            num = (Integer) null;
        }
        return SSHTermOptions(str, str2, str3, jksOptions, pemKeyCertOptions, pfxOptions, num);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "sshTermOptionsOf(defaultCharset, host, intputrc, keyPairOptions, pemKeyPairOptions, pfxKeyPairOptions, port)"))
    @NotNull
    public static final SSHTermOptions SSHTermOptions(@Nullable String defaultCharset, @Nullable String host, @Nullable String intputrc, @Nullable JksOptions keyPairOptions, @Nullable PemKeyCertOptions pemKeyPairOptions, @Nullable PfxOptions pfxKeyPairOptions, @Nullable Integer port) {
        SSHTermOptions $this$apply = new SSHTermOptions();
        if (defaultCharset != null) {
            $this$apply.setDefaultCharset(defaultCharset);
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (intputrc != null) {
            $this$apply.setIntputrc(intputrc);
        }
        if (keyPairOptions != null) {
            $this$apply.setKeyPairOptions(keyPairOptions);
        }
        if (pemKeyPairOptions != null) {
            $this$apply.setPemKeyPairOptions(pemKeyPairOptions);
        }
        if (pfxKeyPairOptions != null) {
            $this$apply.setPfxKeyPairOptions(pfxKeyPairOptions);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        return $this$apply;
    }
}
