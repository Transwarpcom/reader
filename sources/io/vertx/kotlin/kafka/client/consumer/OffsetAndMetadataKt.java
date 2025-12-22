package io.vertx.kotlin.kafka.client.consumer;

import io.vertx.kafka.client.consumer.OffsetAndMetadata;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OffsetAndMetadata.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\t\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"OffsetAndMetadata", "Lio/vertx/kafka/client/consumer/OffsetAndMetadata;", PackageDocumentBase.OPFTags.metadata, "", "offset", "", "(Ljava/lang/String;Ljava/lang/Long;)Lio/vertx/kafka/client/consumer/OffsetAndMetadata;", "offsetAndMetadataOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/client/consumer/OffsetAndMetadataKt.class */
public final class OffsetAndMetadataKt {
    @NotNull
    public static /* synthetic */ OffsetAndMetadata offsetAndMetadataOf$default(String str, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        return offsetAndMetadataOf(str, l);
    }

    @NotNull
    public static final OffsetAndMetadata offsetAndMetadataOf(@Nullable String metadata, @Nullable Long offset) {
        OffsetAndMetadata $this$apply = new OffsetAndMetadata();
        if (metadata != null) {
            $this$apply.setMetadata(metadata);
        }
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "offsetAndMetadataOf(metadata, offset)"))
    @NotNull
    public static /* synthetic */ OffsetAndMetadata OffsetAndMetadata$default(String str, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        return OffsetAndMetadata(str, l);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "offsetAndMetadataOf(metadata, offset)"))
    @NotNull
    public static final OffsetAndMetadata OffsetAndMetadata(@Nullable String metadata, @Nullable Long offset) {
        OffsetAndMetadata $this$apply = new OffsetAndMetadata();
        if (metadata != null) {
            $this$apply.setMetadata(metadata);
        }
        if (offset != null) {
            $this$apply.setOffset(offset.longValue());
        }
        return $this$apply;
    }
}
