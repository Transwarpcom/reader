package io.vertx.kotlin.ext.auth.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.mongo.HashSaltStyle;
import io.vertx.ext.auth.mongo.MongoAuthOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MongoAuthOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a\u0091\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0011\u001a\u008f\u0001\u0010\u0012\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0011¨\u0006\u0013"}, d2 = {"MongoAuthOptions", "Lio/vertx/ext/auth/mongo/MongoAuthOptions;", "collectionName", "", "config", "Lio/vertx/core/json/JsonObject;", "datasourceName", "passwordField", "permissionField", "roleField", "saltField", "saltStyle", "Lio/vertx/ext/auth/mongo/HashSaltStyle;", "shared", "", "usernameCredentialField", "usernameField", "(Ljava/lang/String;Lio/vertx/core/json/JsonObject;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/vertx/ext/auth/mongo/HashSaltStyle;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lio/vertx/ext/auth/mongo/MongoAuthOptions;", "mongoAuthOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/mongo/MongoAuthOptionsKt.class */
public final class MongoAuthOptionsKt {
    @NotNull
    public static /* synthetic */ MongoAuthOptions mongoAuthOptionsOf$default(String str, JsonObject jsonObject, String str2, String str3, String str4, String str5, String str6, HashSaltStyle hashSaltStyle, Boolean bool, String str7, String str8, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            str4 = (String) null;
        }
        if ((i & 32) != 0) {
            str5 = (String) null;
        }
        if ((i & 64) != 0) {
            str6 = (String) null;
        }
        if ((i & 128) != 0) {
            hashSaltStyle = (HashSaltStyle) null;
        }
        if ((i & 256) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 512) != 0) {
            str7 = (String) null;
        }
        if ((i & 1024) != 0) {
            str8 = (String) null;
        }
        return mongoAuthOptionsOf(str, jsonObject, str2, str3, str4, str5, str6, hashSaltStyle, bool, str7, str8);
    }

    @NotNull
    public static final MongoAuthOptions mongoAuthOptionsOf(@Nullable String collectionName, @Nullable JsonObject config, @Nullable String datasourceName, @Nullable String passwordField, @Nullable String permissionField, @Nullable String roleField, @Nullable String saltField, @Nullable HashSaltStyle saltStyle, @Nullable Boolean shared, @Nullable String usernameCredentialField, @Nullable String usernameField) {
        MongoAuthOptions $this$apply = new MongoAuthOptions();
        if (collectionName != null) {
            $this$apply.setCollectionName(collectionName);
        }
        if (config != null) {
            $this$apply.setConfig(config);
        }
        if (datasourceName != null) {
            $this$apply.setDatasourceName(datasourceName);
        }
        if (passwordField != null) {
            $this$apply.setPasswordField(passwordField);
        }
        if (permissionField != null) {
            $this$apply.setPermissionField(permissionField);
        }
        if (roleField != null) {
            $this$apply.setRoleField(roleField);
        }
        if (saltField != null) {
            $this$apply.setSaltField(saltField);
        }
        if (saltStyle != null) {
            $this$apply.setSaltStyle(saltStyle);
        }
        if (shared != null) {
            $this$apply.setShared(shared.booleanValue());
        }
        if (usernameCredentialField != null) {
            $this$apply.setUsernameCredentialField(usernameCredentialField);
        }
        if (usernameField != null) {
            $this$apply.setUsernameField(usernameField);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mongoAuthOptionsOf(collectionName, config, datasourceName, passwordField, permissionField, roleField, saltField, saltStyle, shared, usernameCredentialField, usernameField)"))
    @NotNull
    public static /* synthetic */ MongoAuthOptions MongoAuthOptions$default(String str, JsonObject jsonObject, String str2, String str3, String str4, String str5, String str6, HashSaltStyle hashSaltStyle, Boolean bool, String str7, String str8, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            str4 = (String) null;
        }
        if ((i & 32) != 0) {
            str5 = (String) null;
        }
        if ((i & 64) != 0) {
            str6 = (String) null;
        }
        if ((i & 128) != 0) {
            hashSaltStyle = (HashSaltStyle) null;
        }
        if ((i & 256) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 512) != 0) {
            str7 = (String) null;
        }
        if ((i & 1024) != 0) {
            str8 = (String) null;
        }
        return MongoAuthOptions(str, jsonObject, str2, str3, str4, str5, str6, hashSaltStyle, bool, str7, str8);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mongoAuthOptionsOf(collectionName, config, datasourceName, passwordField, permissionField, roleField, saltField, saltStyle, shared, usernameCredentialField, usernameField)"))
    @NotNull
    public static final MongoAuthOptions MongoAuthOptions(@Nullable String collectionName, @Nullable JsonObject config, @Nullable String datasourceName, @Nullable String passwordField, @Nullable String permissionField, @Nullable String roleField, @Nullable String saltField, @Nullable HashSaltStyle saltStyle, @Nullable Boolean shared, @Nullable String usernameCredentialField, @Nullable String usernameField) {
        MongoAuthOptions $this$apply = new MongoAuthOptions();
        if (collectionName != null) {
            $this$apply.setCollectionName(collectionName);
        }
        if (config != null) {
            $this$apply.setConfig(config);
        }
        if (datasourceName != null) {
            $this$apply.setDatasourceName(datasourceName);
        }
        if (passwordField != null) {
            $this$apply.setPasswordField(passwordField);
        }
        if (permissionField != null) {
            $this$apply.setPermissionField(permissionField);
        }
        if (roleField != null) {
            $this$apply.setRoleField(roleField);
        }
        if (saltField != null) {
            $this$apply.setSaltField(saltField);
        }
        if (saltStyle != null) {
            $this$apply.setSaltStyle(saltStyle);
        }
        if (shared != null) {
            $this$apply.setShared(shared.booleanValue());
        }
        if (usernameCredentialField != null) {
            $this$apply.setUsernameCredentialField(usernameCredentialField);
        }
        if (usernameField != null) {
            $this$apply.setUsernameField(usernameField);
        }
        return $this$apply;
    }
}
