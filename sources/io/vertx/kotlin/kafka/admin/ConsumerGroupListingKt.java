package io.vertx.kotlin.kafka.admin;

import io.vertx.kafka.admin.ConsumerGroupListing;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ConsumerGroupListing.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"ConsumerGroupListing", "Lio/vertx/kafka/admin/ConsumerGroupListing;", "groupId", "", "simpleConsumerGroup", "", "(Ljava/lang/String;Ljava/lang/Boolean;)Lio/vertx/kafka/admin/ConsumerGroupListing;", "consumerGroupListingOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/admin/ConsumerGroupListingKt.class */
public final class ConsumerGroupListingKt {
    @NotNull
    public static /* synthetic */ ConsumerGroupListing consumerGroupListingOf$default(String str, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        return consumerGroupListingOf(str, bool);
    }

    @NotNull
    public static final ConsumerGroupListing consumerGroupListingOf(@Nullable String groupId, @Nullable Boolean simpleConsumerGroup) {
        ConsumerGroupListing $this$apply = new ConsumerGroupListing();
        if (groupId != null) {
            $this$apply.setGroupId(groupId);
        }
        if (simpleConsumerGroup != null) {
            $this$apply.setSimpleConsumerGroup(simpleConsumerGroup.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "consumerGroupListingOf(groupId, simpleConsumerGroup)"))
    @NotNull
    public static /* synthetic */ ConsumerGroupListing ConsumerGroupListing$default(String str, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        return ConsumerGroupListing(str, bool);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "consumerGroupListingOf(groupId, simpleConsumerGroup)"))
    @NotNull
    public static final ConsumerGroupListing ConsumerGroupListing(@Nullable String groupId, @Nullable Boolean simpleConsumerGroup) {
        ConsumerGroupListing $this$apply = new ConsumerGroupListing();
        if (groupId != null) {
            $this$apply.setGroupId(groupId);
        }
        if (simpleConsumerGroup != null) {
            $this$apply.setSimpleConsumerGroup(simpleConsumerGroup.booleanValue());
        }
        return $this$apply;
    }
}
