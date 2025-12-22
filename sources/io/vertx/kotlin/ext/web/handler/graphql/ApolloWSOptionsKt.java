package io.vertx.kotlin.ext.web.handler.graphql;

import io.vertx.ext.web.handler.graphql.ApolloWSOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ApolloWSOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0003\u001a\u0019\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"ApolloWSOptions", "Lio/vertx/ext/web/handler/graphql/ApolloWSOptions;", "keepAlive", "", "(Ljava/lang/Long;)Lio/vertx/ext/web/handler/graphql/ApolloWSOptions;", "apolloWSOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/handler/graphql/ApolloWSOptionsKt.class */
public final class ApolloWSOptionsKt {
    @NotNull
    public static final ApolloWSOptions apolloWSOptionsOf(@Nullable Long keepAlive) {
        ApolloWSOptions $this$apply = new ApolloWSOptions();
        if (keepAlive != null) {
            $this$apply.setKeepAlive(keepAlive.longValue());
        }
        return $this$apply;
    }

    @NotNull
    public static /* synthetic */ ApolloWSOptions apolloWSOptionsOf$default(Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        return apolloWSOptionsOf(l);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "apolloWSOptionsOf(keepAlive)"))
    @NotNull
    public static final ApolloWSOptions ApolloWSOptions(@Nullable Long keepAlive) {
        ApolloWSOptions $this$apply = new ApolloWSOptions();
        if (keepAlive != null) {
            $this$apply.setKeepAlive(keepAlive.longValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "apolloWSOptionsOf(keepAlive)"))
    @NotNull
    public static /* synthetic */ ApolloWSOptions ApolloWSOptions$default(Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        return ApolloWSOptions(l);
    }
}
