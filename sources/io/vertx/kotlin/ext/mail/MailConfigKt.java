package io.vertx.kotlin.ext.mail;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.ext.mail.LoginOption;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.mail.StartTLSOptions;
import io.vertx.ext.web.handler.FormLoginHandler;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MailConfig.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��4\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aß\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0019\u001aÝ\u0001\u0010\u001a\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0019¨\u0006\u001b"}, d2 = {"MailConfig", "Lio/vertx/ext/mail/MailConfig;", "allowRcptErrors", "", "authMethods", "", "disableEsmtp", "enabledSecureTransportProtocols", "", "hostname", "keepAlive", "keyStore", "keyStorePassword", "login", "Lio/vertx/ext/mail/LoginOption;", "maxPoolSize", "", "ownHostname", FormLoginHandler.DEFAULT_PASSWORD_PARAM, RtspHeaders.Values.PORT, "ssl", "starttls", "Lio/vertx/ext/mail/StartTLSOptions;", "trustAll", FormLoginHandler.DEFAULT_USERNAME_PARAM, "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Lio/vertx/ext/mail/LoginOption;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Lio/vertx/ext/mail/StartTLSOptions;Ljava/lang/Boolean;Ljava/lang/String;)Lio/vertx/ext/mail/MailConfig;", "mailConfigOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/mail/MailConfigKt.class */
public final class MailConfigKt {
    @NotNull
    public static /* synthetic */ MailConfig mailConfigOf$default(Boolean bool, String str, Boolean bool2, Iterable iterable, String str2, Boolean bool3, String str3, String str4, LoginOption loginOption, Integer num, String str5, String str6, Integer num2, Boolean bool4, StartTLSOptions startTLSOptions, Boolean bool5, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        if ((i & 32) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 64) != 0) {
            str3 = (String) null;
        }
        if ((i & 128) != 0) {
            str4 = (String) null;
        }
        if ((i & 256) != 0) {
            loginOption = (LoginOption) null;
        }
        if ((i & 512) != 0) {
            num = (Integer) null;
        }
        if ((i & 1024) != 0) {
            str5 = (String) null;
        }
        if ((i & 2048) != 0) {
            str6 = (String) null;
        }
        if ((i & 4096) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 8192) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 16384) != 0) {
            startTLSOptions = (StartTLSOptions) null;
        }
        if ((i & 32768) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 65536) != 0) {
            str7 = (String) null;
        }
        return mailConfigOf(bool, str, bool2, iterable, str2, bool3, str3, str4, loginOption, num, str5, str6, num2, bool4, startTLSOptions, bool5, str7);
    }

    @NotNull
    public static final MailConfig mailConfigOf(@Nullable Boolean allowRcptErrors, @Nullable String authMethods, @Nullable Boolean disableEsmtp, @Nullable Iterable<String> iterable, @Nullable String hostname, @Nullable Boolean keepAlive, @Nullable String keyStore, @Nullable String keyStorePassword, @Nullable LoginOption login, @Nullable Integer maxPoolSize, @Nullable String ownHostname, @Nullable String password, @Nullable Integer port, @Nullable Boolean ssl, @Nullable StartTLSOptions starttls, @Nullable Boolean trustAll, @Nullable String username) {
        MailConfig $this$apply = new MailConfig();
        if (allowRcptErrors != null) {
            $this$apply.setAllowRcptErrors(allowRcptErrors.booleanValue());
        }
        if (authMethods != null) {
            $this$apply.setAuthMethods(authMethods);
        }
        if (disableEsmtp != null) {
            $this$apply.setDisableEsmtp(disableEsmtp.booleanValue());
        }
        if (iterable != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable));
        }
        if (hostname != null) {
            $this$apply.setHostname(hostname);
        }
        if (keepAlive != null) {
            $this$apply.setKeepAlive(keepAlive.booleanValue());
        }
        if (keyStore != null) {
            $this$apply.setKeyStore(keyStore);
        }
        if (keyStorePassword != null) {
            $this$apply.setKeyStorePassword(keyStorePassword);
        }
        if (login != null) {
            $this$apply.setLogin(login);
        }
        if (maxPoolSize != null) {
            $this$apply.setMaxPoolSize(maxPoolSize.intValue());
        }
        if (ownHostname != null) {
            $this$apply.setOwnHostname(ownHostname);
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (ssl != null) {
            $this$apply.setSsl(ssl.booleanValue());
        }
        if (starttls != null) {
            $this$apply.setStarttls(starttls);
        }
        if (trustAll != null) {
            $this$apply.setTrustAll(trustAll.booleanValue());
        }
        if (username != null) {
            $this$apply.setUsername(username);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mailConfigOf(allowRcptErrors, authMethods, disableEsmtp, enabledSecureTransportProtocols, hostname, keepAlive, keyStore, keyStorePassword, login, maxPoolSize, ownHostname, password, port, ssl, starttls, trustAll, username)"))
    @NotNull
    public static /* synthetic */ MailConfig MailConfig$default(Boolean bool, String str, Boolean bool2, Iterable iterable, String str2, Boolean bool3, String str3, String str4, LoginOption loginOption, Integer num, String str5, String str6, Integer num2, Boolean bool4, StartTLSOptions startTLSOptions, Boolean bool5, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        if ((i & 32) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 64) != 0) {
            str3 = (String) null;
        }
        if ((i & 128) != 0) {
            str4 = (String) null;
        }
        if ((i & 256) != 0) {
            loginOption = (LoginOption) null;
        }
        if ((i & 512) != 0) {
            num = (Integer) null;
        }
        if ((i & 1024) != 0) {
            str5 = (String) null;
        }
        if ((i & 2048) != 0) {
            str6 = (String) null;
        }
        if ((i & 4096) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 8192) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 16384) != 0) {
            startTLSOptions = (StartTLSOptions) null;
        }
        if ((i & 32768) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 65536) != 0) {
            str7 = (String) null;
        }
        return MailConfig(bool, str, bool2, iterable, str2, bool3, str3, str4, loginOption, num, str5, str6, num2, bool4, startTLSOptions, bool5, str7);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mailConfigOf(allowRcptErrors, authMethods, disableEsmtp, enabledSecureTransportProtocols, hostname, keepAlive, keyStore, keyStorePassword, login, maxPoolSize, ownHostname, password, port, ssl, starttls, trustAll, username)"))
    @NotNull
    public static final MailConfig MailConfig(@Nullable Boolean allowRcptErrors, @Nullable String authMethods, @Nullable Boolean disableEsmtp, @Nullable Iterable<String> iterable, @Nullable String hostname, @Nullable Boolean keepAlive, @Nullable String keyStore, @Nullable String keyStorePassword, @Nullable LoginOption login, @Nullable Integer maxPoolSize, @Nullable String ownHostname, @Nullable String password, @Nullable Integer port, @Nullable Boolean ssl, @Nullable StartTLSOptions starttls, @Nullable Boolean trustAll, @Nullable String username) {
        MailConfig $this$apply = new MailConfig();
        if (allowRcptErrors != null) {
            $this$apply.setAllowRcptErrors(allowRcptErrors.booleanValue());
        }
        if (authMethods != null) {
            $this$apply.setAuthMethods(authMethods);
        }
        if (disableEsmtp != null) {
            $this$apply.setDisableEsmtp(disableEsmtp.booleanValue());
        }
        if (iterable != null) {
            $this$apply.setEnabledSecureTransportProtocols(CollectionsKt.toSet(iterable));
        }
        if (hostname != null) {
            $this$apply.setHostname(hostname);
        }
        if (keepAlive != null) {
            $this$apply.setKeepAlive(keepAlive.booleanValue());
        }
        if (keyStore != null) {
            $this$apply.setKeyStore(keyStore);
        }
        if (keyStorePassword != null) {
            $this$apply.setKeyStorePassword(keyStorePassword);
        }
        if (login != null) {
            $this$apply.setLogin(login);
        }
        if (maxPoolSize != null) {
            $this$apply.setMaxPoolSize(maxPoolSize.intValue());
        }
        if (ownHostname != null) {
            $this$apply.setOwnHostname(ownHostname);
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (ssl != null) {
            $this$apply.setSsl(ssl.booleanValue());
        }
        if (starttls != null) {
            $this$apply.setStarttls(starttls);
        }
        if (trustAll != null) {
            $this$apply.setTrustAll(trustAll.booleanValue());
        }
        if (username != null) {
            $this$apply.setUsername(username);
        }
        return $this$apply;
    }
}
