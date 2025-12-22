package io.vertx.kotlin.kafka.client.common;

import io.vertx.kafka.client.common.TopicPartition;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TopicPartition.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"TopicPartition", "Lio/vertx/kafka/client/common/TopicPartition;", "partition", "", "topic", "", "(Ljava/lang/Integer;Ljava/lang/String;)Lio/vertx/kafka/client/common/TopicPartition;", "topicPartitionOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/client/common/TopicPartitionKt.class */
public final class TopicPartitionKt {
    @NotNull
    public static /* synthetic */ TopicPartition topicPartitionOf$default(Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return topicPartitionOf(num, str);
    }

    @NotNull
    public static final TopicPartition topicPartitionOf(@Nullable Integer partition, @Nullable String topic) {
        TopicPartition $this$apply = new TopicPartition();
        if (partition != null) {
            $this$apply.setPartition(partition.intValue());
        }
        if (topic != null) {
            $this$apply.setTopic(topic);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "topicPartitionOf(partition, topic)"))
    @NotNull
    public static /* synthetic */ TopicPartition TopicPartition$default(Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return TopicPartition(num, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "topicPartitionOf(partition, topic)"))
    @NotNull
    public static final TopicPartition TopicPartition(@Nullable Integer partition, @Nullable String topic) {
        TopicPartition $this$apply = new TopicPartition();
        if (partition != null) {
            $this$apply.setPartition(partition.intValue());
        }
        if (topic != null) {
            $this$apply.setTopic(topic);
        }
        return $this$apply;
    }
}
