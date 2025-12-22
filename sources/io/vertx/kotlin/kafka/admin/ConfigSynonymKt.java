package io.vertx.kotlin.kafka.admin;

import io.vertx.kafka.admin.ConfigSynonym;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ConfigSynonym.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a,\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0007\u001a*\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¨\u0006\b"}, d2 = {"ConfigSynonym", "Lio/vertx/kafka/admin/ConfigSynonym;", "name", "", PackageDocumentBase.DCTags.source, "Lorg/apache/kafka/clients/admin/ConfigEntry$ConfigSource;", "value", "configSynonymOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/admin/ConfigSynonymKt.class */
public final class ConfigSynonymKt {
    @NotNull
    public static /* synthetic */ ConfigSynonym configSynonymOf$default(String str, ConfigEntry.ConfigSource configSource, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            configSource = (ConfigEntry.ConfigSource) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        return configSynonymOf(str, configSource, str2);
    }

    @NotNull
    public static final ConfigSynonym configSynonymOf(@Nullable String name, @Nullable ConfigEntry.ConfigSource source, @Nullable String value) {
        ConfigSynonym $this$apply = new ConfigSynonym();
        if (name != null) {
            $this$apply.setName(name);
        }
        if (source != null) {
            $this$apply.setSource(source);
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "configSynonymOf(name, source, value)"))
    @NotNull
    public static /* synthetic */ ConfigSynonym ConfigSynonym$default(String str, ConfigEntry.ConfigSource configSource, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            configSource = (ConfigEntry.ConfigSource) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        return ConfigSynonym(str, configSource, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "configSynonymOf(name, source, value)"))
    @NotNull
    public static final ConfigSynonym ConfigSynonym(@Nullable String name, @Nullable ConfigEntry.ConfigSource source, @Nullable String value) {
        ConfigSynonym $this$apply = new ConfigSynonym();
        if (name != null) {
            $this$apply.setName(name);
        }
        if (source != null) {
            $this$apply.setSource(source);
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }
}
