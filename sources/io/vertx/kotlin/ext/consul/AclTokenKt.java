package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.AclToken;
import io.vertx.ext.consul.AclTokenType;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AclToken.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a8\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007\u001a6\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\t"}, d2 = {"AclToken", "Lio/vertx/ext/consul/AclToken;", "id", "", "name", "rules", "type", "Lio/vertx/ext/consul/AclTokenType;", "aclTokenOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/AclTokenKt.class */
public final class AclTokenKt {
    @NotNull
    public static /* synthetic */ AclToken aclTokenOf$default(String str, String str2, String str3, AclTokenType aclTokenType, int i, Object obj) {
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
            aclTokenType = (AclTokenType) null;
        }
        return aclTokenOf(str, str2, str3, aclTokenType);
    }

    @NotNull
    public static final AclToken aclTokenOf(@Nullable String id, @Nullable String name, @Nullable String rules, @Nullable AclTokenType type) {
        AclToken $this$apply = new AclToken();
        if (id != null) {
            $this$apply.setId(id);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (rules != null) {
            $this$apply.setRules(rules);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "aclTokenOf(id, name, rules, type)"))
    @NotNull
    public static /* synthetic */ AclToken AclToken$default(String str, String str2, String str3, AclTokenType aclTokenType, int i, Object obj) {
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
            aclTokenType = (AclTokenType) null;
        }
        return AclToken(str, str2, str3, aclTokenType);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "aclTokenOf(id, name, rules, type)"))
    @NotNull
    public static final AclToken AclToken(@Nullable String id, @Nullable String name, @Nullable String rules, @Nullable AclTokenType type) {
        AclToken $this$apply = new AclToken();
        if (id != null) {
            $this$apply.setId(id);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (rules != null) {
            $this$apply.setRules(rules);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }
}
