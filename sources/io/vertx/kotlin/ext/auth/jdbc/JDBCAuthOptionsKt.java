package io.vertx.kotlin.ext.auth.jdbc;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jdbc.JDBCAuthOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JDBCAuthOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\u001aa\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0002\u0010\f\u001a_\u0010\r\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\f¨\u0006\u000e"}, d2 = {"JDBCAuthOptions", "Lio/vertx/ext/auth/jdbc/JDBCAuthOptions;", "authenticationQuery", "", "config", "Lio/vertx/core/json/JsonObject;", "datasourceName", "permissionsQuery", "rolesPrefix", "rolesQuery", "shared", "", "(Ljava/lang/String;Lio/vertx/core/json/JsonObject;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lio/vertx/ext/auth/jdbc/JDBCAuthOptions;", "jdbcAuthOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/jdbc/JDBCAuthOptionsKt.class */
public final class JDBCAuthOptionsKt {
    @NotNull
    public static /* synthetic */ JDBCAuthOptions jdbcAuthOptionsOf$default(String str, JsonObject jsonObject, String str2, String str3, String str4, String str5, Boolean bool, int i, Object obj) {
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
            bool = (Boolean) null;
        }
        return jdbcAuthOptionsOf(str, jsonObject, str2, str3, str4, str5, bool);
    }

    @NotNull
    public static final JDBCAuthOptions jdbcAuthOptionsOf(@Nullable String authenticationQuery, @Nullable JsonObject config, @Nullable String datasourceName, @Nullable String permissionsQuery, @Nullable String rolesPrefix, @Nullable String rolesQuery, @Nullable Boolean shared) {
        JDBCAuthOptions $this$apply = new JDBCAuthOptions();
        if (authenticationQuery != null) {
            $this$apply.setAuthenticationQuery(authenticationQuery);
        }
        if (config != null) {
            $this$apply.setConfig(config);
        }
        if (datasourceName != null) {
            $this$apply.setDatasourceName(datasourceName);
        }
        if (permissionsQuery != null) {
            $this$apply.setPermissionsQuery(permissionsQuery);
        }
        if (rolesPrefix != null) {
            $this$apply.setRolesPrefix(rolesPrefix);
        }
        if (rolesQuery != null) {
            $this$apply.setRolesQuery(rolesQuery);
        }
        if (shared != null) {
            $this$apply.setShared(shared.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jdbcAuthOptionsOf(authenticationQuery, config, datasourceName, permissionsQuery, rolesPrefix, rolesQuery, shared)"))
    @NotNull
    public static /* synthetic */ JDBCAuthOptions JDBCAuthOptions$default(String str, JsonObject jsonObject, String str2, String str3, String str4, String str5, Boolean bool, int i, Object obj) {
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
            bool = (Boolean) null;
        }
        return JDBCAuthOptions(str, jsonObject, str2, str3, str4, str5, bool);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jdbcAuthOptionsOf(authenticationQuery, config, datasourceName, permissionsQuery, rolesPrefix, rolesQuery, shared)"))
    @NotNull
    public static final JDBCAuthOptions JDBCAuthOptions(@Nullable String authenticationQuery, @Nullable JsonObject config, @Nullable String datasourceName, @Nullable String permissionsQuery, @Nullable String rolesPrefix, @Nullable String rolesQuery, @Nullable Boolean shared) {
        JDBCAuthOptions $this$apply = new JDBCAuthOptions();
        if (authenticationQuery != null) {
            $this$apply.setAuthenticationQuery(authenticationQuery);
        }
        if (config != null) {
            $this$apply.setConfig(config);
        }
        if (datasourceName != null) {
            $this$apply.setDatasourceName(datasourceName);
        }
        if (permissionsQuery != null) {
            $this$apply.setPermissionsQuery(permissionsQuery);
        }
        if (rolesPrefix != null) {
            $this$apply.setRolesPrefix(rolesPrefix);
        }
        if (rolesQuery != null) {
            $this$apply.setRolesQuery(rolesQuery);
        }
        if (shared != null) {
            $this$apply.setShared(shared.booleanValue());
        }
        return $this$apply;
    }
}
