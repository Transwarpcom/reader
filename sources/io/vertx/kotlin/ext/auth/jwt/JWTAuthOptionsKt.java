package io.vertx.kotlin.ext.auth.jwt;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.SecretOptions;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.jwt.JWTOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JWTAuthOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��2\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ab\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0003H\u0007\u001a`\u0010\u000f\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0003¨\u0006\u0010"}, d2 = {"JWTAuthOptions", "Lio/vertx/ext/auth/jwt/JWTAuthOptions;", "jwks", "", "Lio/vertx/core/json/JsonObject;", "jwtOptions", "Lio/vertx/ext/jwt/JWTOptions;", "keyStore", "Lio/vertx/ext/auth/KeyStoreOptions;", "permissionsClaimKey", "", "pubSecKeys", "Lio/vertx/ext/auth/PubSecKeyOptions;", "secrets", "Lio/vertx/ext/auth/SecretOptions;", "jwtAuthOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/jwt/JWTAuthOptionsKt.class */
public final class JWTAuthOptionsKt {
    @NotNull
    public static /* synthetic */ JWTAuthOptions jwtAuthOptionsOf$default(Iterable iterable, JWTOptions jWTOptions, KeyStoreOptions keyStoreOptions, String str, Iterable iterable2, Iterable iterable3, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            jWTOptions = (JWTOptions) null;
        }
        if ((i & 4) != 0) {
            keyStoreOptions = (KeyStoreOptions) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 32) != 0) {
            iterable3 = (Iterable) null;
        }
        return jwtAuthOptionsOf(iterable, jWTOptions, keyStoreOptions, str, iterable2, iterable3);
    }

    @NotNull
    public static final JWTAuthOptions jwtAuthOptionsOf(@Nullable Iterable<? extends JsonObject> iterable, @Nullable JWTOptions jwtOptions, @Nullable KeyStoreOptions keyStore, @Nullable String permissionsClaimKey, @Nullable Iterable<? extends PubSecKeyOptions> iterable2, @Nullable Iterable<? extends SecretOptions> iterable3) {
        JWTAuthOptions $this$apply = new JWTAuthOptions();
        if (iterable != null) {
            $this$apply.setJwks(CollectionsKt.toList(iterable));
        }
        if (jwtOptions != null) {
            $this$apply.setJWTOptions(jwtOptions);
        }
        if (keyStore != null) {
            $this$apply.setKeyStore(keyStore);
        }
        if (permissionsClaimKey != null) {
            $this$apply.setPermissionsClaimKey(permissionsClaimKey);
        }
        if (iterable2 != null) {
            $this$apply.setPubSecKeys(CollectionsKt.toList(iterable2));
        }
        if (iterable3 != null) {
            $this$apply.setSecrets(CollectionsKt.toList(iterable3));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jwtAuthOptionsOf(jwks, jwtOptions, keyStore, permissionsClaimKey, pubSecKeys, secrets)"))
    @NotNull
    public static /* synthetic */ JWTAuthOptions JWTAuthOptions$default(Iterable iterable, JWTOptions jWTOptions, KeyStoreOptions keyStoreOptions, String str, Iterable iterable2, Iterable iterable3, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            jWTOptions = (JWTOptions) null;
        }
        if ((i & 4) != 0) {
            keyStoreOptions = (KeyStoreOptions) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 32) != 0) {
            iterable3 = (Iterable) null;
        }
        return JWTAuthOptions(iterable, jWTOptions, keyStoreOptions, str, iterable2, iterable3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jwtAuthOptionsOf(jwks, jwtOptions, keyStore, permissionsClaimKey, pubSecKeys, secrets)"))
    @NotNull
    public static final JWTAuthOptions JWTAuthOptions(@Nullable Iterable<? extends JsonObject> iterable, @Nullable JWTOptions jwtOptions, @Nullable KeyStoreOptions keyStore, @Nullable String permissionsClaimKey, @Nullable Iterable<? extends PubSecKeyOptions> iterable2, @Nullable Iterable<? extends SecretOptions> iterable3) {
        JWTAuthOptions $this$apply = new JWTAuthOptions();
        if (iterable != null) {
            $this$apply.setJwks(CollectionsKt.toList(iterable));
        }
        if (jwtOptions != null) {
            $this$apply.setJWTOptions(jwtOptions);
        }
        if (keyStore != null) {
            $this$apply.setKeyStore(keyStore);
        }
        if (permissionsClaimKey != null) {
            $this$apply.setPermissionsClaimKey(permissionsClaimKey);
        }
        if (iterable2 != null) {
            $this$apply.setPubSecKeys(CollectionsKt.toList(iterable2));
        }
        if (iterable3 != null) {
            $this$apply.setSecrets(CollectionsKt.toList(iterable3));
        }
        return $this$apply;
    }
}
