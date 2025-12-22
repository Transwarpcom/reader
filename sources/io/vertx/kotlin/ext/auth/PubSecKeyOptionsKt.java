package io.vertx.kotlin.ext.auth;

import io.vertx.ext.auth.PubSecKeyOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PubSecKeyOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0006\u001aI\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\t\u001aG\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"PubSecKeyOptions", "Lio/vertx/ext/auth/PubSecKeyOptions;", "algorithm", "", "certificate", "", "publicKey", "secretKey", "symmetric", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lio/vertx/ext/auth/PubSecKeyOptions;", "pubSecKeyOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/PubSecKeyOptionsKt.class */
public final class PubSecKeyOptionsKt {
    @NotNull
    public static /* synthetic */ PubSecKeyOptions pubSecKeyOptionsOf$default(String str, Boolean bool, String str2, String str3, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        return pubSecKeyOptionsOf(str, bool, str2, str3, bool2);
    }

    @NotNull
    public static final PubSecKeyOptions pubSecKeyOptionsOf(@Nullable String algorithm, @Nullable Boolean certificate, @Nullable String publicKey, @Nullable String secretKey, @Nullable Boolean symmetric) {
        PubSecKeyOptions $this$apply = new PubSecKeyOptions();
        if (algorithm != null) {
            $this$apply.setAlgorithm(algorithm);
        }
        if (certificate != null) {
            $this$apply.setCertificate(certificate.booleanValue());
        }
        if (publicKey != null) {
            $this$apply.setPublicKey(publicKey);
        }
        if (secretKey != null) {
            $this$apply.setSecretKey(secretKey);
        }
        if (symmetric != null) {
            $this$apply.setSymmetric(symmetric.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pubSecKeyOptionsOf(algorithm, certificate, publicKey, secretKey, symmetric)"))
    @NotNull
    public static /* synthetic */ PubSecKeyOptions PubSecKeyOptions$default(String str, Boolean bool, String str2, String str3, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        return PubSecKeyOptions(str, bool, str2, str3, bool2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pubSecKeyOptionsOf(algorithm, certificate, publicKey, secretKey, symmetric)"))
    @NotNull
    public static final PubSecKeyOptions PubSecKeyOptions(@Nullable String algorithm, @Nullable Boolean certificate, @Nullable String publicKey, @Nullable String secretKey, @Nullable Boolean symmetric) {
        PubSecKeyOptions $this$apply = new PubSecKeyOptions();
        if (algorithm != null) {
            $this$apply.setAlgorithm(algorithm);
        }
        if (certificate != null) {
            $this$apply.setCertificate(certificate.booleanValue());
        }
        if (publicKey != null) {
            $this$apply.setPublicKey(publicKey);
        }
        if (secretKey != null) {
            $this$apply.setSecretKey(secretKey);
        }
        if (symmetric != null) {
            $this$apply.setSymmetric(symmetric.booleanValue());
        }
        return $this$apply;
    }
}
