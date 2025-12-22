package io.vertx.kotlin.ext.auth.shiro;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.shiro.ShiroAuthOptions;
import io.vertx.ext.auth.shiro.ShiroAuthRealmType;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ShiroAuthOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007\u001a\u001e\u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0007"}, d2 = {"ShiroAuthOptions", "Lio/vertx/ext/auth/shiro/ShiroAuthOptions;", "config", "Lio/vertx/core/json/JsonObject;", "type", "Lio/vertx/ext/auth/shiro/ShiroAuthRealmType;", "shiroAuthOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/shiro/ShiroAuthOptionsKt.class */
public final class ShiroAuthOptionsKt {
    @NotNull
    public static /* synthetic */ ShiroAuthOptions shiroAuthOptionsOf$default(JsonObject jsonObject, ShiroAuthRealmType shiroAuthRealmType, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            shiroAuthRealmType = (ShiroAuthRealmType) null;
        }
        return shiroAuthOptionsOf(jsonObject, shiroAuthRealmType);
    }

    @NotNull
    public static final ShiroAuthOptions shiroAuthOptionsOf(@Nullable JsonObject config, @Nullable ShiroAuthRealmType type) {
        ShiroAuthOptions $this$apply = new ShiroAuthOptions();
        if (config != null) {
            $this$apply.setConfig(config);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "shiroAuthOptionsOf(config, type)"))
    @NotNull
    public static /* synthetic */ ShiroAuthOptions ShiroAuthOptions$default(JsonObject jsonObject, ShiroAuthRealmType shiroAuthRealmType, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            shiroAuthRealmType = (ShiroAuthRealmType) null;
        }
        return ShiroAuthOptions(jsonObject, shiroAuthRealmType);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "shiroAuthOptionsOf(config, type)"))
    @NotNull
    public static final ShiroAuthOptions ShiroAuthOptions(@Nullable JsonObject config, @Nullable ShiroAuthRealmType type) {
        ShiroAuthOptions $this$apply = new ShiroAuthOptions();
        if (config != null) {
            $this$apply.setConfig(config);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }
}
