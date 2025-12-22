package io.vertx.kotlin.ext.web.handler.sockjs;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: PermittedOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a8\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0007\u001a6\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¨\u0006\t"}, d2 = {"PermittedOptions", "Lio/vertx/ext/web/handler/sockjs/PermittedOptions;", "address", "", "addressRegex", BeanDefinitionParserDelegate.ARG_TYPE_MATCH_ATTRIBUTE, "Lio/vertx/core/json/JsonObject;", "requiredAuthority", "permittedOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/handler/sockjs/PermittedOptionsKt.class */
public final class PermittedOptionsKt {
    @NotNull
    public static /* synthetic */ PermittedOptions permittedOptionsOf$default(String str, String str2, JsonObject jsonObject, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        return permittedOptionsOf(str, str2, jsonObject, str3);
    }

    @NotNull
    public static final PermittedOptions permittedOptionsOf(@Nullable String address, @Nullable String addressRegex, @Nullable JsonObject match, @Nullable String requiredAuthority) {
        PermittedOptions $this$apply = new PermittedOptions();
        if (address != null) {
            $this$apply.setAddress(address);
        }
        if (addressRegex != null) {
            $this$apply.setAddressRegex(addressRegex);
        }
        if (match != null) {
            $this$apply.setMatch(match);
        }
        if (requiredAuthority != null) {
            $this$apply.setRequiredAuthority(requiredAuthority);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "permittedOptionsOf(address, addressRegex, match, requiredAuthority)"))
    @NotNull
    public static /* synthetic */ PermittedOptions PermittedOptions$default(String str, String str2, JsonObject jsonObject, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        return PermittedOptions(str, str2, jsonObject, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "permittedOptionsOf(address, addressRegex, match, requiredAuthority)"))
    @NotNull
    public static final PermittedOptions PermittedOptions(@Nullable String address, @Nullable String addressRegex, @Nullable JsonObject match, @Nullable String requiredAuthority) {
        PermittedOptions $this$apply = new PermittedOptions();
        if (address != null) {
            $this$apply.setAddress(address);
        }
        if (addressRegex != null) {
            $this$apply.setAddressRegex(addressRegex);
        }
        if (match != null) {
            $this$apply.setMatch(match);
        }
        if (requiredAuthority != null) {
            $this$apply.setRequiredAuthority(requiredAuthority);
        }
        return $this$apply;
    }
}
