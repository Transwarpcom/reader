package io.vertx.kotlin.ext.auth.jwt;

import io.vertx.ext.auth.jwt.JWTKeyStoreOptions;
import io.vertx.ext.web.handler.FormLoginHandler;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JWTKeyStoreOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a,\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u0007\u001a*\u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¨\u0006\u0007"}, d2 = {"JWTKeyStoreOptions", "Lio/vertx/ext/auth/jwt/JWTKeyStoreOptions;", FormLoginHandler.DEFAULT_PASSWORD_PARAM, "", "path", "type", "jwtKeyStoreOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/jwt/JWTKeyStoreOptionsKt.class */
public final class JWTKeyStoreOptionsKt {
    @NotNull
    public static /* synthetic */ JWTKeyStoreOptions jwtKeyStoreOptionsOf$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        return jwtKeyStoreOptionsOf(str, str2, str3);
    }

    @NotNull
    public static final JWTKeyStoreOptions jwtKeyStoreOptionsOf(@Nullable String password, @Nullable String path, @Nullable String type) {
        JWTKeyStoreOptions $this$apply = new JWTKeyStoreOptions();
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (path != null) {
            $this$apply.setPath(path);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jwtKeyStoreOptionsOf(password, path, type)"))
    @NotNull
    public static /* synthetic */ JWTKeyStoreOptions JWTKeyStoreOptions$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        return JWTKeyStoreOptions(str, str2, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jwtKeyStoreOptionsOf(password, path, type)"))
    @NotNull
    public static final JWTKeyStoreOptions JWTKeyStoreOptions(@Nullable String password, @Nullable String path, @Nullable String type) {
        JWTKeyStoreOptions $this$apply = new JWTKeyStoreOptions();
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (path != null) {
            $this$apply.setPath(path);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }
}
