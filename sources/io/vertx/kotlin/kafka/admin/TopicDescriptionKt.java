package io.vertx.kotlin.kafka.admin;

import io.vertx.kafka.admin.TopicDescription;
import io.vertx.kafka.client.common.TopicPartitionInfo;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TopicDescription.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\t\u001a5\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"TopicDescription", "Lio/vertx/kafka/admin/TopicDescription;", "internal", "", "name", "", "partitions", "", "Lio/vertx/kafka/client/common/TopicPartitionInfo;", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Iterable;)Lio/vertx/kafka/admin/TopicDescription;", "topicDescriptionOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/admin/TopicDescriptionKt.class */
public final class TopicDescriptionKt {
    @NotNull
    public static /* synthetic */ TopicDescription topicDescriptionOf$default(Boolean bool, String str, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            iterable = (Iterable) null;
        }
        return topicDescriptionOf(bool, str, iterable);
    }

    @NotNull
    public static final TopicDescription topicDescriptionOf(@Nullable Boolean internal, @Nullable String name, @Nullable Iterable<? extends TopicPartitionInfo> iterable) {
        TopicDescription $this$apply = new TopicDescription();
        if (internal != null) {
            $this$apply.setInternal(internal.booleanValue());
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (iterable != null) {
            $this$apply.setPartitions(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "topicDescriptionOf(internal, name, partitions)"))
    @NotNull
    public static /* synthetic */ TopicDescription TopicDescription$default(Boolean bool, String str, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            iterable = (Iterable) null;
        }
        return TopicDescription(bool, str, iterable);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "topicDescriptionOf(internal, name, partitions)"))
    @NotNull
    public static final TopicDescription TopicDescription(@Nullable Boolean internal, @Nullable String name, @Nullable Iterable<? extends TopicPartitionInfo> iterable) {
        TopicDescription $this$apply = new TopicDescription();
        if (internal != null) {
            $this$apply.setInternal(internal.booleanValue());
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (iterable != null) {
            $this$apply.setPartitions(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }
}
