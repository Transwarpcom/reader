package io.vertx.kotlin.ext.auth.jwt;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JWTOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��,\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\b\u001a¯\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0013\u001a\u00ad\u0001\u0010\u0014\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0013¨\u0006\u0015"}, d2 = {"JWTOptions", "Lio/vertx/ext/auth/jwt/JWTOptions;", "algorithm", "", "audience", "", "audiences", "expiresInMinutes", "", "expiresInSeconds", "header", "Lio/vertx/core/json/JsonObject;", "ignoreExpiration", "", "issuer", "leeway", "noTimestamp", "permissions", PackageDocumentBase.DCTags.subject, "(Ljava/lang/String;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Integer;Ljava/lang/Integer;Lio/vertx/core/json/JsonObject;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/String;)Lio/vertx/ext/auth/jwt/JWTOptions;", "jwtOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/jwt/JWTOptionsKt.class */
public final class JWTOptionsKt {
    @NotNull
    public static /* synthetic */ JWTOptions jwtOptionsOf$default(String str, Iterable iterable, Iterable iterable2, Integer num, Integer num2, JsonObject jsonObject, Boolean bool, String str2, Integer num3, Boolean bool2, Iterable iterable3, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 8) != 0) {
            num = (Integer) null;
        }
        if ((i & 16) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 32) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 64) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 128) != 0) {
            str2 = (String) null;
        }
        if ((i & 256) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 512) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 2048) != 0) {
            str3 = (String) null;
        }
        return jwtOptionsOf(str, iterable, iterable2, num, num2, jsonObject, bool, str2, num3, bool2, iterable3, str3);
    }

    @NotNull
    public static final JWTOptions jwtOptionsOf(@Nullable String algorithm, @Nullable Iterable<String> iterable, @Nullable Iterable<String> iterable2, @Nullable Integer expiresInMinutes, @Nullable Integer expiresInSeconds, @Nullable JsonObject header, @Nullable Boolean ignoreExpiration, @Nullable String issuer, @Nullable Integer leeway, @Nullable Boolean noTimestamp, @Nullable Iterable<String> iterable3, @Nullable String subject) {
        JWTOptions $this$apply = new JWTOptions();
        if (algorithm != null) {
            $this$apply.setAlgorithm(algorithm);
        }
        if (iterable != null) {
            $this$apply.setAudience(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            for (String item : iterable2) {
                $this$apply.addAudience(item);
            }
        }
        if (expiresInMinutes != null) {
            $this$apply.setExpiresInMinutes(expiresInMinutes.intValue());
        }
        if (expiresInSeconds != null) {
            $this$apply.setExpiresInSeconds(expiresInSeconds.intValue());
        }
        if (header != null) {
            $this$apply.setHeader(header);
        }
        if (ignoreExpiration != null) {
            $this$apply.setIgnoreExpiration(ignoreExpiration.booleanValue());
        }
        if (issuer != null) {
            $this$apply.setIssuer(issuer);
        }
        if (leeway != null) {
            $this$apply.setLeeway(leeway.intValue());
        }
        if (noTimestamp != null) {
            $this$apply.setNoTimestamp(noTimestamp.booleanValue());
        }
        if (iterable3 != null) {
            $this$apply.setPermissions(CollectionsKt.toList(iterable3));
        }
        if (subject != null) {
            $this$apply.setSubject(subject);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jwtOptionsOf(algorithm, audience, audiences, expiresInMinutes, expiresInSeconds, header, ignoreExpiration, issuer, leeway, noTimestamp, permissions, subject)"))
    @NotNull
    public static /* synthetic */ JWTOptions JWTOptions$default(String str, Iterable iterable, Iterable iterable2, Integer num, Integer num2, JsonObject jsonObject, Boolean bool, String str2, Integer num3, Boolean bool2, Iterable iterable3, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 8) != 0) {
            num = (Integer) null;
        }
        if ((i & 16) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 32) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 64) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 128) != 0) {
            str2 = (String) null;
        }
        if ((i & 256) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 512) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 2048) != 0) {
            str3 = (String) null;
        }
        return JWTOptions(str, iterable, iterable2, num, num2, jsonObject, bool, str2, num3, bool2, iterable3, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jwtOptionsOf(algorithm, audience, audiences, expiresInMinutes, expiresInSeconds, header, ignoreExpiration, issuer, leeway, noTimestamp, permissions, subject)"))
    @NotNull
    public static final JWTOptions JWTOptions(@Nullable String algorithm, @Nullable Iterable<String> iterable, @Nullable Iterable<String> iterable2, @Nullable Integer expiresInMinutes, @Nullable Integer expiresInSeconds, @Nullable JsonObject header, @Nullable Boolean ignoreExpiration, @Nullable String issuer, @Nullable Integer leeway, @Nullable Boolean noTimestamp, @Nullable Iterable<String> iterable3, @Nullable String subject) {
        JWTOptions $this$apply = new JWTOptions();
        if (algorithm != null) {
            $this$apply.setAlgorithm(algorithm);
        }
        if (iterable != null) {
            $this$apply.setAudience(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            for (String item : iterable2) {
                $this$apply.addAudience(item);
            }
        }
        if (expiresInMinutes != null) {
            $this$apply.setExpiresInMinutes(expiresInMinutes.intValue());
        }
        if (expiresInSeconds != null) {
            $this$apply.setExpiresInSeconds(expiresInSeconds.intValue());
        }
        if (header != null) {
            $this$apply.setHeader(header);
        }
        if (ignoreExpiration != null) {
            $this$apply.setIgnoreExpiration(ignoreExpiration.booleanValue());
        }
        if (issuer != null) {
            $this$apply.setIssuer(issuer);
        }
        if (leeway != null) {
            $this$apply.setLeeway(leeway.intValue());
        }
        if (noTimestamp != null) {
            $this$apply.setNoTimestamp(noTimestamp.booleanValue());
        }
        if (iterable3 != null) {
            $this$apply.setPermissions(CollectionsKt.toList(iterable3));
        }
        if (subject != null) {
            $this$apply.setSubject(subject);
        }
        return $this$apply;
    }
}
