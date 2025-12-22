package io.vertx.kotlin.kafka.client.common;

import io.vertx.kafka.client.common.Node;
import io.vertx.kafka.client.common.PartitionInfo;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PartitionInfo.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001aU\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0002\u0010\u000b\u001aS\u0010\f\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {"PartitionInfo", "Lio/vertx/kafka/client/common/PartitionInfo;", "inSyncReplicas", "", "Lio/vertx/kafka/client/common/Node;", "leader", "partition", "", "replicas", "topic", "", "(Ljava/lang/Iterable;Lio/vertx/kafka/client/common/Node;Ljava/lang/Integer;Ljava/lang/Iterable;Ljava/lang/String;)Lio/vertx/kafka/client/common/PartitionInfo;", "partitionInfoOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/client/common/PartitionInfoKt.class */
public final class PartitionInfoKt {
    @NotNull
    public static /* synthetic */ PartitionInfo partitionInfoOf$default(Iterable iterable, Node node, Integer num, Iterable iterable2, String str, int i, Object obj) {
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
        if ((i & 16) != 0) {
            str = (String) null;
        }
        return partitionInfoOf(iterable, node, num, iterable2, str);
    }

    @NotNull
    public static final PartitionInfo partitionInfoOf(@Nullable Iterable<? extends Node> iterable, @Nullable Node leader, @Nullable Integer partition, @Nullable Iterable<? extends Node> iterable2, @Nullable String topic) {
        PartitionInfo $this$apply = new PartitionInfo();
        if (iterable != null) {
            $this$apply.setInSyncReplicas(CollectionsKt.toList(iterable));
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
        if (topic != null) {
            $this$apply.setTopic(topic);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "partitionInfoOf(inSyncReplicas, leader, partition, replicas, topic)"))
    @NotNull
    public static /* synthetic */ PartitionInfo PartitionInfo$default(Iterable iterable, Node node, Integer num, Iterable iterable2, String str, int i, Object obj) {
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
        if ((i & 16) != 0) {
            str = (String) null;
        }
        return PartitionInfo(iterable, node, num, iterable2, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "partitionInfoOf(inSyncReplicas, leader, partition, replicas, topic)"))
    @NotNull
    public static final PartitionInfo PartitionInfo(@Nullable Iterable<? extends Node> iterable, @Nullable Node leader, @Nullable Integer partition, @Nullable Iterable<? extends Node> iterable2, @Nullable String topic) {
        PartitionInfo $this$apply = new PartitionInfo();
        if (iterable != null) {
            $this$apply.setInSyncReplicas(CollectionsKt.toList(iterable));
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
        if (topic != null) {
            $this$apply.setTopic(topic);
        }
        return $this$apply;
    }
}
