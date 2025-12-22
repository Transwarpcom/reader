package io.vertx.kotlin.core.http;

import io.vertx.core.http.StreamPriority;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: StreamPriority.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\n\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a/\u0010\t\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"StreamPriority", "Lio/vertx/core/http/StreamPriority;", "dependency", "", "exclusive", "", "weight", "", "(Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Short;)Lio/vertx/core/http/StreamPriority;", "streamPriorityOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/http/StreamPriorityKt.class */
public final class StreamPriorityKt {
    @NotNull
    public static /* synthetic */ StreamPriority streamPriorityOf$default(Integer num, Boolean bool, Short sh, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            sh = (Short) null;
        }
        return streamPriorityOf(num, bool, sh);
    }

    @NotNull
    public static final StreamPriority streamPriorityOf(@Nullable Integer dependency, @Nullable Boolean exclusive, @Nullable Short weight) {
        StreamPriority $this$apply = new StreamPriority();
        if (dependency != null) {
            $this$apply.setDependency(dependency.intValue());
        }
        if (exclusive != null) {
            $this$apply.setExclusive(exclusive.booleanValue());
        }
        if (weight != null) {
            $this$apply.setWeight(weight.shortValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "streamPriorityOf(dependency, exclusive, weight)"))
    @NotNull
    public static /* synthetic */ StreamPriority StreamPriority$default(Integer num, Boolean bool, Short sh, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            sh = (Short) null;
        }
        return StreamPriority(num, bool, sh);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "streamPriorityOf(dependency, exclusive, weight)"))
    @NotNull
    public static final StreamPriority StreamPriority(@Nullable Integer dependency, @Nullable Boolean exclusive, @Nullable Short weight) {
        StreamPriority $this$apply = new StreamPriority();
        if (dependency != null) {
            $this$apply.setDependency(dependency.intValue());
        }
        if (exclusive != null) {
            $this$apply.setExclusive(exclusive.booleanValue());
        }
        if (weight != null) {
            $this$apply.setWeight(weight.shortValue());
        }
        return $this$apply;
    }
}
