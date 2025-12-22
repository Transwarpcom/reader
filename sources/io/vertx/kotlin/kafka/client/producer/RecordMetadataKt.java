package io.vertx.kotlin.kafka.client.producer;

import io.vertx.kafka.client.producer.RecordMetadata;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RecordMetadata.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001aI\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001aG\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"RecordMetadata", "Lio/vertx/kafka/client/producer/RecordMetadata;", "checksum", "", "offset", "partition", "", "timestamp", "topic", "", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;)Lio/vertx/kafka/client/producer/RecordMetadata;", "recordMetadataOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/client/producer/RecordMetadataKt.class */
public final class RecordMetadataKt {
    @NotNull
    public static /* synthetic */ RecordMetadata recordMetadataOf$default(Long l, Long l2, Integer num, Long l3, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            l3 = (Long) null;
        }
        if ((i & 16) != 0) {
            str = (String) null;
        }
        return recordMetadataOf(l, l2, num, l3, str);
    }

    @NotNull
    public static final RecordMetadata recordMetadataOf(@Nullable Long checksum, @Nullable Long offset, @Nullable Integer partition, @Nullable Long timestamp, @Nullable String topic) {
        RecordMetadata $this$apply = new RecordMetadata();
        if (checksum != null) {
            $this$apply.setChecksum(checksum.longValue());
        }
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        if (partition != null) {
            $this$apply.setPartition(partition.intValue());
        }
        if (timestamp != null) {
            $this$apply.setTimestamp(timestamp.longValue());
        }
        if (topic != null) {
            $this$apply.setTopic(topic);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "recordMetadataOf(checksum, offset, partition, timestamp, topic)"))
    @NotNull
    public static /* synthetic */ RecordMetadata RecordMetadata$default(Long l, Long l2, Integer num, Long l3, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            l3 = (Long) null;
        }
        if ((i & 16) != 0) {
            str = (String) null;
        }
        return RecordMetadata(l, l2, num, l3, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "recordMetadataOf(checksum, offset, partition, timestamp, topic)"))
    @NotNull
    public static final RecordMetadata RecordMetadata(@Nullable Long checksum, @Nullable Long offset, @Nullable Integer partition, @Nullable Long timestamp, @Nullable String topic) {
        RecordMetadata $this$apply = new RecordMetadata();
        if (checksum != null) {
            $this$apply.setChecksum(checksum.longValue());
        }
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        if (partition != null) {
            $this$apply.setPartition(partition.intValue());
        }
        if (timestamp != null) {
            $this$apply.setTimestamp(timestamp.longValue());
        }
        if (topic != null) {
            $this$apply.setTopic(topic);
        }
        return $this$apply;
    }
}
