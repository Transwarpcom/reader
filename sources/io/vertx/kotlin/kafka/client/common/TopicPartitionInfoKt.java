package io.vertx.kotlin.kafka.client.common;

import io.vertx.kafka.client.common.Node;
import io.vertx.kafka.client.common.TopicPartitionInfo;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TopicPartitionInfo.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u001aI\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\t\u001aG\u0010\n\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"TopicPartitionInfo", "Lio/vertx/kafka/client/common/TopicPartitionInfo;", "isr", "", "Lio/vertx/kafka/client/common/Node;", "leader", "partition", "", "replicas", "(Ljava/lang/Iterable;Lio/vertx/kafka/client/common/Node;Ljava/lang/Integer;Ljava/lang/Iterable;)Lio/vertx/kafka/client/common/TopicPartitionInfo;", "topicPartitionInfoOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/client/common/TopicPartitionInfoKt.class */
public final class TopicPartitionInfoKt {
    @NotNull
    public static /* synthetic */ TopicPartitionInfo topicPartitionInfoOf$default(Iterable iterable, Node node, Integer num, Iterable iterable2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            node = (Node) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            iterable2 = (Iterable) null;
        }
        return topicPartitionInfoOf(iterable, node, num, iterable2);
    }

    @NotNull
    public static final TopicPartitionInfo topicPartitionInfoOf(@Nullable Iterable<? extends Node> iterable, @Nullable Node leader, @Nullable Integer partition, @Nullable Iterable<? extends Node> iterable2) {
        TopicPartitionInfo $this$apply = new TopicPartitionInfo();
        if (iterable != null) {
            $this$apply.setIsr(CollectionsKt.toList(iterable));
        }
        if (leader != null) {
            $this$apply.setLeader(leader);
        }
        if (partition != null) {
            $this$apply.setPartition(partition.intValue());
        }
        if (iterable2 != null) {
            $this$apply.setReplicas(CollectionsKt.toList(iterable2));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "topicPartitionInfoOf(isr, leader, partition, replicas)"))
    @NotNull
    public static /* synthetic */ TopicPartitionInfo TopicPartitionInfo$default(Iterable iterable, Node node, Integer num, Iterable iterable2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            node = (Node) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            iterable2 = (Iterable) null;
        }
        return TopicPartitionInfo(iterable, node, num, iterable2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "topicPartitionInfoOf(isr, leader, partition, replicas)"))
    @NotNull
    public static final TopicPartitionInfo TopicPartitionInfo(@Nullable Iterable<? extends Node> iterable, @Nullable Node leader, @Nullable Integer partition, @Nullable Iterable<? extends Node> iterable2) {
        TopicPartitionInfo $this$apply = new TopicPartitionInfo();
        if (iterable != null) {
            $this$apply.setIsr(CollectionsKt.toList(iterable));
        }
        if (leader != null) {
            $this$apply.setLeader(leader);
        }
        if (partition != null) {
            $this$apply.setPartition(partition.intValue());
        }
        if (iterable2 != null) {
            $this$apply.setReplicas(CollectionsKt.toList(iterable2));
        }
        return $this$apply;
    }
}
