package io.vertx.kotlin.sqlclient;

import io.vertx.sqlclient.PoolOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PoolOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0004\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a#\u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0007"}, d2 = {"PoolOptions", "Lio/vertx/sqlclient/PoolOptions;", "maxSize", "", "maxWaitQueueSize", "(Ljava/lang/Integer;Ljava/lang/Integer;)Lio/vertx/sqlclient/PoolOptions;", "poolOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/sqlclient/PoolOptionsKt.class */
public final class PoolOptionsKt {
    @NotNull
    public static /* synthetic */ PoolOptions poolOptionsOf$default(Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        return poolOptionsOf(num, num2);
    }

    @NotNull
    public static final PoolOptions poolOptionsOf(@Nullable Integer maxSize, @Nullable Integer maxWaitQueueSize) {
        PoolOptions $this$apply = new PoolOptions();
        if (maxSize != null) {
            $this$apply.setMaxSize(maxSize.intValue());
        }
        if (maxWaitQueueSize != null) {
            $this$apply.setMaxWaitQueueSize(maxWaitQueueSize.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "poolOptionsOf(maxSize, maxWaitQueueSize)"))
    @NotNull
    public static /* synthetic */ PoolOptions PoolOptions$default(Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        return PoolOptions(num, num2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "poolOptionsOf(maxSize, maxWaitQueueSize)"))
    @NotNull
    public static final PoolOptions PoolOptions(@Nullable Integer maxSize, @Nullable Integer maxWaitQueueSize) {
        PoolOptions $this$apply = new PoolOptions();
        if (maxSize != null) {
            $this$apply.setMaxSize(maxSize.intValue());
        }
        if (maxWaitQueueSize != null) {
            $this$apply.setMaxWaitQueueSize(maxWaitQueueSize.intValue());
        }
        return $this$apply;
    }
}
