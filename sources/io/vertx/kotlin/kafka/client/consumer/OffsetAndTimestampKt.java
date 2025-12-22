package io.vertx.kotlin.kafka.client.consumer;

import io.vertx.kafka.client.consumer.OffsetAndTimestamp;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OffsetAndTimestamp.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0004\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a#\u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0007"}, d2 = {"OffsetAndTimestamp", "Lio/vertx/kafka/client/consumer/OffsetAndTimestamp;", "offset", "", "timestamp", "(Ljava/lang/Long;Ljava/lang/Long;)Lio/vertx/kafka/client/consumer/OffsetAndTimestamp;", "offsetAndTimestampOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/client/consumer/OffsetAndTimestampKt.class */
public final class OffsetAndTimestampKt {
    @NotNull
    public static /* synthetic */ OffsetAndTimestamp offsetAndTimestampOf$default(Long l, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        return offsetAndTimestampOf(l, l2);
    }

    @NotNull
    public static final OffsetAndTimestamp offsetAndTimestampOf(@Nullable Long offset, @Nullable Long timestamp) {
        OffsetAndTimestamp $this$apply = new OffsetAndTimestamp();
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        if (timestamp != null) {
            $this$apply.setTimestamp(timestamp.longValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "offsetAndTimestampOf(offset, timestamp)"))
    @NotNull
    public static /* synthetic */ OffsetAndTimestamp OffsetAndTimestamp$default(Long l, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        return OffsetAndTimestamp(l, l2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "offsetAndTimestampOf(offset, timestamp)"))
    @NotNull
    public static final OffsetAndTimestamp OffsetAndTimestamp(@Nullable Long offset, @Nullable Long timestamp) {
        OffsetAndTimestamp $this$apply = new OffsetAndTimestamp();
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        if (timestamp != null) {
            $this$apply.setTimestamp(timestamp.longValue());
        }
        return $this$apply;
    }
}
