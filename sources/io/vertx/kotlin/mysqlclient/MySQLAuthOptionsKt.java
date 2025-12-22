package io.vertx.kotlin.mysqlclient;

import ch.qos.logback.classic.ClassicConstants;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.handler.FormLoginHandler;
import io.vertx.mysqlclient.MySQLAuthOptions;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MySQLAuthOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001at\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0007\u001ar\u0010\r\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¨\u0006\u000e"}, d2 = {"MySQLAuthOptions", "Lio/vertx/mysqlclient/MySQLAuthOptions;", "charset", "", "collation", "database", FormLoginHandler.DEFAULT_PASSWORD_PARAM, PackageDocumentBase.OPFAttributes.properties, "", "serverRsaPublicKeyPath", "serverRsaPublicKeyValue", "Lio/vertx/core/buffer/Buffer;", ClassicConstants.USER_MDC_KEY, "mySQLAuthOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/mysqlclient/MySQLAuthOptionsKt.class */
public final class MySQLAuthOptionsKt {
    @NotNull
    public static /* synthetic */ MySQLAuthOptions mySQLAuthOptionsOf$default(String str, String str2, String str3, String str4, Map map, String str5, Buffer buffer, String str6, int i, Object obj) {
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
            str4 = (String) null;
        }
        if ((i & 16) != 0) {
            map = (Map) null;
        }
        if ((i & 32) != 0) {
            str5 = (String) null;
        }
        if ((i & 64) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 128) != 0) {
            str6 = (String) null;
        }
        return mySQLAuthOptionsOf(str, str2, str3, str4, map, str5, buffer, str6);
    }

    @NotNull
    public static final MySQLAuthOptions mySQLAuthOptionsOf(@Nullable String charset, @Nullable String collation, @Nullable String database, @Nullable String password, @Nullable Map<String, String> map, @Nullable String serverRsaPublicKeyPath, @Nullable Buffer serverRsaPublicKeyValue, @Nullable String user) {
        MySQLAuthOptions $this$apply = new MySQLAuthOptions();
        if (charset != null) {
            $this$apply.setCharset(charset);
        }
        if (collation != null) {
            $this$apply.setCollation(collation);
        }
        if (database != null) {
            $this$apply.setDatabase(database);
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (map != null) {
            $this$apply.setProperties(map);
        }
        if (serverRsaPublicKeyPath != null) {
            $this$apply.setServerRsaPublicKeyPath(serverRsaPublicKeyPath);
        }
        if (serverRsaPublicKeyValue != null) {
            $this$apply.setServerRsaPublicKeyValue(serverRsaPublicKeyValue);
        }
        if (user != null) {
            $this$apply.setUser(user);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mySQLAuthOptionsOf(charset, collation, database, password, properties, serverRsaPublicKeyPath, serverRsaPublicKeyValue, user)"))
    @NotNull
    public static /* synthetic */ MySQLAuthOptions MySQLAuthOptions$default(String str, String str2, String str3, String str4, Map map, String str5, Buffer buffer, String str6, int i, Object obj) {
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
            str4 = (String) null;
        }
        if ((i & 16) != 0) {
            map = (Map) null;
        }
        if ((i & 32) != 0) {
            str5 = (String) null;
        }
        if ((i & 64) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 128) != 0) {
            str6 = (String) null;
        }
        return MySQLAuthOptions(str, str2, str3, str4, map, str5, buffer, str6);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mySQLAuthOptionsOf(charset, collation, database, password, properties, serverRsaPublicKeyPath, serverRsaPublicKeyValue, user)"))
    @NotNull
    public static final MySQLAuthOptions MySQLAuthOptions(@Nullable String charset, @Nullable String collation, @Nullable String database, @Nullable String password, @Nullable Map<String, String> map, @Nullable String serverRsaPublicKeyPath, @Nullable Buffer serverRsaPublicKeyValue, @Nullable String user) {
        MySQLAuthOptions $this$apply = new MySQLAuthOptions();
        if (charset != null) {
            $this$apply.setCharset(charset);
        }
        if (collation != null) {
            $this$apply.setCollation(collation);
        }
        if (database != null) {
            $this$apply.setDatabase(database);
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (map != null) {
            $this$apply.setProperties(map);
        }
        if (serverRsaPublicKeyPath != null) {
            $this$apply.setServerRsaPublicKeyPath(serverRsaPublicKeyPath);
        }
        if (serverRsaPublicKeyValue != null) {
            $this$apply.setServerRsaPublicKeyValue(serverRsaPublicKeyValue);
        }
        if (user != null) {
            $this$apply.setUser(user);
        }
        return $this$apply;
    }
}
