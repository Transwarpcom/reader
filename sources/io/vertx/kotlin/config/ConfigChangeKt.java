package io.vertx.kotlin.config;

import io.vertx.config.ConfigChange;
import io.vertx.core.json.JsonObject;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ConfigChange.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007\u001a\u001e\u0010\u0005\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¨\u0006\u0006"}, d2 = {"ConfigChange", "Lio/vertx/config/ConfigChange;", "newConfiguration", "Lio/vertx/core/json/JsonObject;", "previousConfiguration", "configChangeOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/config/ConfigChangeKt.class */
public final class ConfigChangeKt {
    @NotNull
    public static /* synthetic */ ConfigChange configChangeOf$default(JsonObject jsonObject, JsonObject jsonObject2, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        return configChangeOf(jsonObject, jsonObject2);
    }

    @NotNull
    public static final ConfigChange configChangeOf(@Nullable JsonObject newConfiguration, @Nullable JsonObject previousConfiguration) {
        ConfigChange $this$apply = new ConfigChange();
        if (newConfiguration != null) {
            $this$apply.setNewConfiguration(newConfiguration);
        }
        if (previousConfiguration != null) {
            $this$apply.setPreviousConfiguration(previousConfiguration);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "configChangeOf(newConfiguration, previousConfiguration)"))
    @NotNull
    public static /* synthetic */ ConfigChange ConfigChange$default(JsonObject jsonObject, JsonObject jsonObject2, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        return ConfigChange(jsonObject, jsonObject2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "configChangeOf(newConfiguration, previousConfiguration)"))
    @NotNull
    public static final ConfigChange ConfigChange(@Nullable JsonObject newConfiguration, @Nullable JsonObject previousConfiguration) {
        ConfigChange $this$apply = new ConfigChange();
        if (newConfiguration != null) {
            $this$apply.setNewConfiguration(newConfiguration);
        }
        if (previousConfiguration != null) {
            $this$apply.setPreviousConfiguration(previousConfiguration);
        }
        return $this$apply;
    }
}
