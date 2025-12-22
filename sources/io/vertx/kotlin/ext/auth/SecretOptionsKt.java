package io.vertx.kotlin.ext.auth;

import io.vertx.ext.auth.SecretOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SecretOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a \u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007\u001a\u001e\u0010\u0005\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¨\u0006\u0006"}, d2 = {"SecretOptions", "Lio/vertx/ext/auth/SecretOptions;", "secret", "", "type", "secretOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/SecretOptionsKt.class */
public final class SecretOptionsKt {
    @NotNull
    public static /* synthetic */ SecretOptions secretOptionsOf$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        return secretOptionsOf(str, str2);
    }

    @NotNull
    public static final SecretOptions secretOptionsOf(@Nullable String secret, @Nullable String type) {
        SecretOptions $this$apply = new SecretOptions();
        if (secret != null) {
            $this$apply.setSecret(secret);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "secretOptionsOf(secret, type)"))
    @NotNull
    public static /* synthetic */ SecretOptions SecretOptions$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        return SecretOptions(str, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "secretOptionsOf(secret, type)"))
    @NotNull
    public static final SecretOptions SecretOptions(@Nullable String secret, @Nullable String type) {
        SecretOptions $this$apply = new SecretOptions();
        if (secret != null) {
            $this$apply.setSecret(secret);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }
}
