package io.vertx.kotlin.kafka.admin;

import io.vertx.kafka.admin.NewTopic;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NewTopic.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\n\n\u0002\b\u0003\u001aI\u0010��\u001a\u00020\u00012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001aG\u0010\u000b\u001a\u00020\u00012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"NewTopic", "Lio/vertx/kafka/admin/NewTopic;", "config", "", "", "name", "numPartitions", "", "replicationFactor", "", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Short;)Lio/vertx/kafka/admin/NewTopic;", "newTopicOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/admin/NewTopicKt.class */
public final class NewTopicKt {
    @NotNull
    public static /* synthetic */ NewTopic newTopicOf$default(Map map, String str, Integer num, Short sh, int i, Object obj) {
        if ((i & 1) != 0) {
            map = (Map) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            sh = (Short) null;
        }
        return newTopicOf(map, str, num, sh);
    }

    @NotNull
    public static final NewTopic newTopicOf(@Nullable Map<String, String> map, @Nullable String name, @Nullable Integer numPartitions, @Nullable Short replicationFactor) {
        NewTopic $this$apply = new NewTopic();
        if (map != null) {
            $this$apply.setConfig(map);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (numPartitions != null) {
            $this$apply.setNumPartitions(numPartitions.intValue());
        }
        if (replicationFactor != null) {
            $this$apply.setReplicationFactor(replicationFactor.shortValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "newTopicOf(config, name, numPartitions, replicationFactor)"))
    @NotNull
    public static /* synthetic */ NewTopic NewTopic$default(Map map, String str, Integer num, Short sh, int i, Object obj) {
        if ((i & 1) != 0) {
            map = (Map) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            sh = (Short) null;
        }
        return NewTopic(map, str, num, sh);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "newTopicOf(config, name, numPartitions, replicationFactor)"))
    @NotNull
    public static final NewTopic NewTopic(@Nullable Map<String, String> map, @Nullable String name, @Nullable Integer numPartitions, @Nullable Short replicationFactor) {
        NewTopic $this$apply = new NewTopic();
        if (map != null) {
            $this$apply.setConfig(map);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (numPartitions != null) {
            $this$apply.setNumPartitions(numPartitions.intValue());
        }
        if (replicationFactor != null) {
            $this$apply.setReplicationFactor(replicationFactor.shortValue());
        }
        return $this$apply;
    }
}
