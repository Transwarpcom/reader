package io.vertx.kotlin.ext.web.handler.graphql;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.graphql.GraphiQLHandlerOptions;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: GraphiQLHandlerOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aU\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0002\u0010\u000b\u001aS\u0010\f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {"GraphiQLHandlerOptions", "Lio/vertx/ext/web/handler/graphql/GraphiQLHandlerOptions;", "enabled", "", "graphQLUri", "", "headers", "", "query", "variables", "Lio/vertx/core/json/JsonObject;", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Lio/vertx/core/json/JsonObject;)Lio/vertx/ext/web/handler/graphql/GraphiQLHandlerOptions;", "graphiQLHandlerOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/handler/graphql/GraphiQLHandlerOptionsKt.class */
public final class GraphiQLHandlerOptionsKt {
    @NotNull
    public static /* synthetic */ GraphiQLHandlerOptions graphiQLHandlerOptionsOf$default(Boolean bool, String str, Map map, String str2, JsonObject jsonObject, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            map = (Map) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            jsonObject = (JsonObject) null;
        }
        return graphiQLHandlerOptionsOf(bool, str, map, str2, jsonObject);
    }

    @NotNull
    public static final GraphiQLHandlerOptions graphiQLHandlerOptionsOf(@Nullable Boolean enabled, @Nullable String graphQLUri, @Nullable Map<String, String> map, @Nullable String query, @Nullable JsonObject variables) {
        GraphiQLHandlerOptions $this$apply = new GraphiQLHandlerOptions();
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (graphQLUri != null) {
            $this$apply.setGraphQLUri(graphQLUri);
        }
        if (map != null) {
            $this$apply.setHeaders(map);
        }
        if (query != null) {
            $this$apply.setQuery(query);
        }
        if (variables != null) {
            $this$apply.setVariables(variables);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "graphiQLHandlerOptionsOf(enabled, graphQLUri, headers, query, variables)"))
    @NotNull
    public static /* synthetic */ GraphiQLHandlerOptions GraphiQLHandlerOptions$default(Boolean bool, String str, Map map, String str2, JsonObject jsonObject, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            map = (Map) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            jsonObject = (JsonObject) null;
        }
        return GraphiQLHandlerOptions(bool, str, map, str2, jsonObject);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "graphiQLHandlerOptionsOf(enabled, graphQLUri, headers, query, variables)"))
    @NotNull
    public static final GraphiQLHandlerOptions GraphiQLHandlerOptions(@Nullable Boolean enabled, @Nullable String graphQLUri, @Nullable Map<String, String> map, @Nullable String query, @Nullable JsonObject variables) {
        GraphiQLHandlerOptions $this$apply = new GraphiQLHandlerOptions();
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (graphQLUri != null) {
            $this$apply.setGraphQLUri(graphQLUri);
        }
        if (map != null) {
            $this$apply.setHeaders(map);
        }
        if (query != null) {
            $this$apply.setQuery(query);
        }
        if (variables != null) {
            $this$apply.setVariables(variables);
        }
        return $this$apply;
    }
}
