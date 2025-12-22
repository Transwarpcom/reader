package io.vertx.kotlin.config;

import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.json.JsonObject;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ConfigStoreOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a=\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\t\u001a;\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"ConfigStoreOptions", "Lio/vertx/config/ConfigStoreOptions;", "config", "Lio/vertx/core/json/JsonObject;", PackageDocumentBase.DCTags.format, "", "optional", "", "type", "(Lio/vertx/core/json/JsonObject;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lio/vertx/config/ConfigStoreOptions;", "configStoreOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/config/ConfigStoreOptionsKt.class */
public final class ConfigStoreOptionsKt {
    @NotNull
    public static /* synthetic */ ConfigStoreOptions configStoreOptionsOf$default(JsonObject jsonObject, String str, Boolean bool, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        return configStoreOptionsOf(jsonObject, str, bool, str2);
    }

    @NotNull
    public static final ConfigStoreOptions configStoreOptionsOf(@Nullable JsonObject config, @Nullable String format, @Nullable Boolean optional, @Nullable String type) {
        ConfigStoreOptions $this$apply = new ConfigStoreOptions();
        if (config != null) {
            $this$apply.setConfig(config);
        }
        if (format != null) {
            $this$apply.setFormat(format);
        }
        if (optional != null) {
            $this$apply.setOptional(optional.booleanValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "configStoreOptionsOf(config, format, optional, type)"))
    @NotNull
    public static /* synthetic */ ConfigStoreOptions ConfigStoreOptions$default(JsonObject jsonObject, String str, Boolean bool, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        return ConfigStoreOptions(jsonObject, str, bool, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "configStoreOptionsOf(config, format, optional, type)"))
    @NotNull
    public static final ConfigStoreOptions ConfigStoreOptions(@Nullable JsonObject config, @Nullable String format, @Nullable Boolean optional, @Nullable String type) {
        ConfigStoreOptions $this$apply = new ConfigStoreOptions();
        if (config != null) {
            $this$apply.setConfig(config);
        }
        if (format != null) {
            $this$apply.setFormat(format);
        }
        if (optional != null) {
            $this$apply.setOptional(optional.booleanValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }
}
