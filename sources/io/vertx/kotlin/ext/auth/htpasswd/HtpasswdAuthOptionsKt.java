package io.vertx.kotlin.ext.auth.htpasswd;

import io.vertx.ext.auth.htpasswd.HtpasswdAuthOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: HtpasswdAuthOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"HtpasswdAuthOptions", "Lio/vertx/ext/auth/htpasswd/HtpasswdAuthOptions;", "htpasswdFile", "", "plainTextEnabled", "", "(Ljava/lang/String;Ljava/lang/Boolean;)Lio/vertx/ext/auth/htpasswd/HtpasswdAuthOptions;", "htpasswdAuthOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/htpasswd/HtpasswdAuthOptionsKt.class */
public final class HtpasswdAuthOptionsKt {
    @NotNull
    public static /* synthetic */ HtpasswdAuthOptions htpasswdAuthOptionsOf$default(String str, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        return htpasswdAuthOptionsOf(str, bool);
    }

    @NotNull
    public static final HtpasswdAuthOptions htpasswdAuthOptionsOf(@Nullable String htpasswdFile, @Nullable Boolean plainTextEnabled) {
        HtpasswdAuthOptions $this$apply = new HtpasswdAuthOptions();
        if (htpasswdFile != null) {
            $this$apply.setHtpasswdFile(htpasswdFile);
        }
        if (plainTextEnabled != null) {
            $this$apply.setPlainTextEnabled(plainTextEnabled.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "htpasswdAuthOptionsOf(htpasswdFile, plainTextEnabled)"))
    @NotNull
    public static /* synthetic */ HtpasswdAuthOptions HtpasswdAuthOptions$default(String str, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        return HtpasswdAuthOptions(str, bool);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "htpasswdAuthOptionsOf(htpasswdFile, plainTextEnabled)"))
    @NotNull
    public static final HtpasswdAuthOptions HtpasswdAuthOptions(@Nullable String htpasswdFile, @Nullable Boolean plainTextEnabled) {
        HtpasswdAuthOptions $this$apply = new HtpasswdAuthOptions();
        if (htpasswdFile != null) {
            $this$apply.setHtpasswdFile(htpasswdFile);
        }
        if (plainTextEnabled != null) {
            $this$apply.setPlainTextEnabled(plainTextEnabled.booleanValue());
        }
        return $this$apply;
    }
}
