package io.vertx.kotlin.kafka.client.common;

import io.vertx.kafka.client.common.ConfigResource;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.apache.kafka.common.config.ConfigResource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ConfigResource.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a/\u0010\t\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"ConfigResource", "Lio/vertx/kafka/client/common/ConfigResource;", "default", "", "name", "", "type", "Lorg/apache/kafka/common/config/ConfigResource$Type;", "(Ljava/lang/Boolean;Ljava/lang/String;Lorg/apache/kafka/common/config/ConfigResource$Type;)Lio/vertx/kafka/client/common/ConfigResource;", "configResourceOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/client/common/ConfigResourceKt.class */
public final class ConfigResourceKt {
    @NotNull
    public static /* synthetic */ ConfigResource configResourceOf$default(Boolean bool, String str, ConfigResource.Type type, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            type = (ConfigResource.Type) null;
        }
        return configResourceOf(bool, str, type);
    }

    @NotNull
    public static final io.vertx.kafka.client.common.ConfigResource configResourceOf(@Nullable Boolean bool, @Nullable String name, @Nullable ConfigResource.Type type) {
        io.vertx.kafka.client.common.ConfigResource $this$apply = new io.vertx.kafka.client.common.ConfigResource();
        if (bool != null) {
            $this$apply.setDefault(bool.booleanValue());
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "configResourceOf(default, name, type)"))
    @NotNull
    public static /* synthetic */ io.vertx.kafka.client.common.ConfigResource ConfigResource$default(Boolean bool, String str, ConfigResource.Type type, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            type = (ConfigResource.Type) null;
        }
        return ConfigResource(bool, str, type);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "configResourceOf(default, name, type)"))
    @NotNull
    public static final io.vertx.kafka.client.common.ConfigResource ConfigResource(@Nullable Boolean bool, @Nullable String name, @Nullable ConfigResource.Type type) {
        io.vertx.kafka.client.common.ConfigResource $this$apply = new io.vertx.kafka.client.common.ConfigResource();
        if (bool != null) {
            $this$apply.setDefault(bool.booleanValue());
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }
}
