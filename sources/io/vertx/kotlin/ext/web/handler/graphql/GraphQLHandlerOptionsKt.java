package io.vertx.kotlin.ext.web.handler.graphql;

import io.vertx.ext.web.handler.graphql.GraphQLHandlerOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: GraphQLHandlerOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0019\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"GraphQLHandlerOptions", "Lio/vertx/ext/web/handler/graphql/GraphQLHandlerOptions;", "requestBatchingEnabled", "", "(Ljava/lang/Boolean;)Lio/vertx/ext/web/handler/graphql/GraphQLHandlerOptions;", "graphQLHandlerOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/handler/graphql/GraphQLHandlerOptionsKt.class */
public final class GraphQLHandlerOptionsKt {
    @NotNull
    public static final GraphQLHandlerOptions graphQLHandlerOptionsOf(@Nullable Boolean requestBatchingEnabled) {
        GraphQLHandlerOptions $this$apply = new GraphQLHandlerOptions();
        if (requestBatchingEnabled != null) {
            $this$apply.setRequestBatchingEnabled(requestBatchingEnabled.booleanValue());
        }
        return $this$apply;
    }

    @NotNull
    public static /* synthetic */ GraphQLHandlerOptions graphQLHandlerOptionsOf$default(Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        return graphQLHandlerOptionsOf(bool);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "graphQLHandlerOptionsOf(requestBatchingEnabled)"))
    @NotNull
    public static final GraphQLHandlerOptions GraphQLHandlerOptions(@Nullable Boolean requestBatchingEnabled) {
        GraphQLHandlerOptions $this$apply = new GraphQLHandlerOptions();
        if (requestBatchingEnabled != null) {
            $this$apply.setRequestBatchingEnabled(requestBatchingEnabled.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "graphQLHandlerOptionsOf(requestBatchingEnabled)"))
    @NotNull
    public static /* synthetic */ GraphQLHandlerOptions GraphQLHandlerOptions$default(Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        return GraphQLHandlerOptions(bool);
    }
}
