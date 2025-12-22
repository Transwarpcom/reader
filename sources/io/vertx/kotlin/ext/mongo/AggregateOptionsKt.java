package io.vertx.kotlin.ext.mongo;

import io.vertx.ext.mongo.AggregateOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AggregateOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n��\n\u0002\u0010\t\n\u0002\b\u0004\u001a=\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\t\u001a;\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"AggregateOptions", "Lio/vertx/ext/mongo/AggregateOptions;", "allowDiskUse", "", "batchSize", "", "maxAwaitTime", "", "maxTime", "(Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;)Lio/vertx/ext/mongo/AggregateOptions;", "aggregateOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/mongo/AggregateOptionsKt.class */
public final class AggregateOptionsKt {
    @NotNull
    public static /* synthetic */ AggregateOptions aggregateOptionsOf$default(Boolean bool, Integer num, Long l, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        if ((i & 4) != 0) {
            l = (Long) null;
        }
        if ((i & 8) != 0) {
            l2 = (Long) null;
        }
        return aggregateOptionsOf(bool, num, l, l2);
    }

    @NotNull
    public static final AggregateOptions aggregateOptionsOf(@Nullable Boolean allowDiskUse, @Nullable Integer batchSize, @Nullable Long maxAwaitTime, @Nullable Long maxTime) {
        AggregateOptions $this$apply = new AggregateOptions();
        if (allowDiskUse != null) {
            $this$apply.setAllowDiskUse(allowDiskUse);
        }
        if (batchSize != null) {
            $this$apply.setBatchSize(batchSize.intValue());
        }
        if (maxAwaitTime != null) {
            $this$apply.setMaxAwaitTime(maxAwaitTime.longValue());
        }
        if (maxTime != null) {
            $this$apply.setMaxTime(maxTime.longValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "aggregateOptionsOf(allowDiskUse, batchSize, maxAwaitTime, maxTime)"))
    @NotNull
    public static /* synthetic */ AggregateOptions AggregateOptions$default(Boolean bool, Integer num, Long l, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        if ((i & 4) != 0) {
            l = (Long) null;
        }
        if ((i & 8) != 0) {
            l2 = (Long) null;
        }
        return AggregateOptions(bool, num, l, l2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "aggregateOptionsOf(allowDiskUse, batchSize, maxAwaitTime, maxTime)"))
    @NotNull
    public static final AggregateOptions AggregateOptions(@Nullable Boolean allowDiskUse, @Nullable Integer batchSize, @Nullable Long maxAwaitTime, @Nullable Long maxTime) {
        AggregateOptions $this$apply = new AggregateOptions();
        if (allowDiskUse != null) {
            $this$apply.setAllowDiskUse(allowDiskUse);
        }
        if (batchSize != null) {
            $this$apply.setBatchSize(batchSize.intValue());
        }
        if (maxAwaitTime != null) {
            $this$apply.setMaxAwaitTime(maxAwaitTime.longValue());
        }
        if (maxTime != null) {
            $this$apply.setMaxTime(maxTime.longValue());
        }
        return $this$apply;
    }
}
