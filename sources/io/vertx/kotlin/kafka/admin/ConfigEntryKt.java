package io.vertx.kotlin.kafka.admin;

import io.vertx.kafka.admin.ConfigEntry;
import io.vertx.kafka.admin.ConfigSynonym;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ConfigEntry.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��(\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001ag\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u000e\u001ae\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000e¨\u0006\u0010"}, d2 = {"ConfigEntry", "Lio/vertx/kafka/admin/ConfigEntry;", "default", "", "name", "", "readOnly", "sensitive", PackageDocumentBase.DCTags.source, "Lorg/apache/kafka/clients/admin/ConfigEntry$ConfigSource;", "synonyms", "", "Lio/vertx/kafka/admin/ConfigSynonym;", "value", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lorg/apache/kafka/clients/admin/ConfigEntry$ConfigSource;Ljava/lang/Iterable;Ljava/lang/String;)Lio/vertx/kafka/admin/ConfigEntry;", "configEntryOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/admin/ConfigEntryKt.class */
public final class ConfigEntryKt {
    @NotNull
    public static /* synthetic */ ConfigEntry configEntryOf$default(Boolean bool, String str, Boolean bool2, Boolean bool3, ConfigEntry.ConfigSource configSource, Iterable iterable, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 16) != 0) {
            configSource = (ConfigEntry.ConfigSource) null;
        }
        if ((i & 32) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 64) != 0) {
            str2 = (String) null;
        }
        return configEntryOf(bool, str, bool2, bool3, configSource, iterable, str2);
    }

    @NotNull
    public static final io.vertx.kafka.admin.ConfigEntry configEntryOf(@Nullable Boolean bool, @Nullable String name, @Nullable Boolean readOnly, @Nullable Boolean sensitive, @Nullable ConfigEntry.ConfigSource source, @Nullable Iterable<? extends ConfigSynonym> iterable, @Nullable String value) {
        io.vertx.kafka.admin.ConfigEntry $this$apply = new io.vertx.kafka.admin.ConfigEntry();
        if (bool != null) {
            $this$apply.setDefault(bool.booleanValue());
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (readOnly != null) {
            $this$apply.setReadOnly(readOnly.booleanValue());
        }
        if (sensitive != null) {
            $this$apply.setSensitive(sensitive.booleanValue());
        }
        if (source != null) {
            $this$apply.setSource(source);
        }
        if (iterable != null) {
            $this$apply.setSynonyms(CollectionsKt.toList(iterable));
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "configEntryOf(default, name, readOnly, sensitive, source, synonyms, value)"))
    @NotNull
    public static /* synthetic */ io.vertx.kafka.admin.ConfigEntry ConfigEntry$default(Boolean bool, String str, Boolean bool2, Boolean bool3, ConfigEntry.ConfigSource configSource, Iterable iterable, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 16) != 0) {
            configSource = (ConfigEntry.ConfigSource) null;
        }
        if ((i & 32) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 64) != 0) {
            str2 = (String) null;
        }
        return ConfigEntry(bool, str, bool2, bool3, configSource, iterable, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "configEntryOf(default, name, readOnly, sensitive, source, synonyms, value)"))
    @NotNull
    public static final io.vertx.kafka.admin.ConfigEntry ConfigEntry(@Nullable Boolean bool, @Nullable String name, @Nullable Boolean readOnly, @Nullable Boolean sensitive, @Nullable ConfigEntry.ConfigSource source, @Nullable Iterable<? extends ConfigSynonym> iterable, @Nullable String value) {
        io.vertx.kafka.admin.ConfigEntry $this$apply = new io.vertx.kafka.admin.ConfigEntry();
        if (bool != null) {
            $this$apply.setDefault(bool.booleanValue());
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (readOnly != null) {
            $this$apply.setReadOnly(readOnly.booleanValue());
        }
        if (sensitive != null) {
            $this$apply.setSensitive(sensitive.booleanValue());
        }
        if (source != null) {
            $this$apply.setSource(source);
        }
        if (iterable != null) {
            $this$apply.setSynonyms(CollectionsKt.toList(iterable));
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }
}
