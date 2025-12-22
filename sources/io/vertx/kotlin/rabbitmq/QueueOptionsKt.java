package io.vertx.kotlin.rabbitmq;

import io.vertx.rabbitmq.QueueOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: QueueOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"QueueOptions", "Lio/vertx/rabbitmq/QueueOptions;", "autoAck", "", "keepMostRecent", "maxInternalQueueSize", "", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;)Lio/vertx/rabbitmq/QueueOptions;", "queueOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/rabbitmq/QueueOptionsKt.class */
public final class QueueOptionsKt {
    @NotNull
    public static /* synthetic */ QueueOptions queueOptionsOf$default(Boolean bool, Boolean bool2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return queueOptionsOf(bool, bool2, num);
    }

    @NotNull
    public static final QueueOptions queueOptionsOf(@Nullable Boolean autoAck, @Nullable Boolean keepMostRecent, @Nullable Integer maxInternalQueueSize) {
        QueueOptions $this$apply = new QueueOptions();
        if (autoAck != null) {
            $this$apply.setAutoAck(autoAck.booleanValue());
        }
        if (keepMostRecent != null) {
            $this$apply.setKeepMostRecent(keepMostRecent.booleanValue());
        }
        if (maxInternalQueueSize != null) {
            $this$apply.setMaxInternalQueueSize(maxInternalQueueSize.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "queueOptionsOf(autoAck, keepMostRecent, maxInternalQueueSize)"))
    @NotNull
    public static /* synthetic */ QueueOptions QueueOptions$default(Boolean bool, Boolean bool2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return QueueOptions(bool, bool2, num);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "queueOptionsOf(autoAck, keepMostRecent, maxInternalQueueSize)"))
    @NotNull
    public static final QueueOptions QueueOptions(@Nullable Boolean autoAck, @Nullable Boolean keepMostRecent, @Nullable Integer maxInternalQueueSize) {
        QueueOptions $this$apply = new QueueOptions();
        if (autoAck != null) {
            $this$apply.setAutoAck(autoAck.booleanValue());
        }
        if (keepMostRecent != null) {
            $this$apply.setKeepMostRecent(keepMostRecent.booleanValue());
        }
        if (maxInternalQueueSize != null) {
            $this$apply.setMaxInternalQueueSize(maxInternalQueueSize.intValue());
        }
        return $this$apply;
    }
}
