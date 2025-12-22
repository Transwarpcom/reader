package io.vertx.kotlin.kafka.admin;

import io.vertx.kafka.admin.ConsumerGroupDescription;
import io.vertx.kafka.admin.MemberDescription;
import io.vertx.kafka.client.common.Node;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.apache.kafka.common.ConsumerGroupState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ConsumerGroupDescription.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��.\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a[\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u0007¢\u0006\u0002\u0010\u000e\u001aY\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000e¨\u0006\u0010"}, d2 = {"ConsumerGroupDescription", "Lio/vertx/kafka/admin/ConsumerGroupDescription;", "coordinator", "Lio/vertx/kafka/client/common/Node;", "groupId", "", "members", "", "Lio/vertx/kafka/admin/MemberDescription;", "partitionAssignor", "simpleConsumerGroup", "", "state", "Lorg/apache/kafka/common/ConsumerGroupState;", "(Lio/vertx/kafka/client/common/Node;Ljava/lang/String;Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/Boolean;Lorg/apache/kafka/common/ConsumerGroupState;)Lio/vertx/kafka/admin/ConsumerGroupDescription;", "consumerGroupDescriptionOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/admin/ConsumerGroupDescriptionKt.class */
public final class ConsumerGroupDescriptionKt {
    @NotNull
    public static /* synthetic */ ConsumerGroupDescription consumerGroupDescriptionOf$default(Node node, String str, Iterable iterable, String str2, Boolean bool, ConsumerGroupState consumerGroupState, int i, Object obj) {
        if ((i & 1) != 0) {
            node = (Node) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 32) != 0) {
            consumerGroupState = (ConsumerGroupState) null;
        }
        return consumerGroupDescriptionOf(node, str, iterable, str2, bool, consumerGroupState);
    }

    @NotNull
    public static final ConsumerGroupDescription consumerGroupDescriptionOf(@Nullable Node coordinator, @Nullable String groupId, @Nullable Iterable<? extends MemberDescription> iterable, @Nullable String partitionAssignor, @Nullable Boolean simpleConsumerGroup, @Nullable ConsumerGroupState state) {
        ConsumerGroupDescription $this$apply = new ConsumerGroupDescription();
        if (coordinator != null) {
            $this$apply.setCoordinator(coordinator);
        }
        if (groupId != null) {
            $this$apply.setGroupId(groupId);
        }
        if (iterable != null) {
            $this$apply.setMembers(CollectionsKt.toList(iterable));
        }
        if (partitionAssignor != null) {
            $this$apply.setPartitionAssignor(partitionAssignor);
        }
        if (simpleConsumerGroup != null) {
            $this$apply.setSimpleConsumerGroup(simpleConsumerGroup.booleanValue());
        }
        if (state != null) {
            $this$apply.setState(state);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "consumerGroupDescriptionOf(coordinator, groupId, members, partitionAssignor, simpleConsumerGroup, state)"))
    @NotNull
    public static /* synthetic */ ConsumerGroupDescription ConsumerGroupDescription$default(Node node, String str, Iterable iterable, String str2, Boolean bool, ConsumerGroupState consumerGroupState, int i, Object obj) {
        if ((i & 1) != 0) {
            node = (Node) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 32) != 0) {
            consumerGroupState = (ConsumerGroupState) null;
        }
        return ConsumerGroupDescription(node, str, iterable, str2, bool, consumerGroupState);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "consumerGroupDescriptionOf(coordinator, groupId, members, partitionAssignor, simpleConsumerGroup, state)"))
    @NotNull
    public static final ConsumerGroupDescription ConsumerGroupDescription(@Nullable Node coordinator, @Nullable String groupId, @Nullable Iterable<? extends MemberDescription> iterable, @Nullable String partitionAssignor, @Nullable Boolean simpleConsumerGroup, @Nullable ConsumerGroupState state) {
        ConsumerGroupDescription $this$apply = new ConsumerGroupDescription();
        if (coordinator != null) {
            $this$apply.setCoordinator(coordinator);
        }
        if (groupId != null) {
            $this$apply.setGroupId(groupId);
        }
        if (iterable != null) {
            $this$apply.setMembers(CollectionsKt.toList(iterable));
        }
        if (partitionAssignor != null) {
            $this$apply.setPartitionAssignor(partitionAssignor);
        }
        if (simpleConsumerGroup != null) {
            $this$apply.setSimpleConsumerGroup(simpleConsumerGroup.booleanValue());
        }
        if (state != null) {
            $this$apply.setState(state);
        }
        return $this$apply;
    }
}
