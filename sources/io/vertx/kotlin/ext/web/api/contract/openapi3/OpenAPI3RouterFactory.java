package io.vertx.kotlin.ext.web.api.contract.openapi3;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OpenAPI3RouterFactory.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\tJ/\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lio/vertx/kotlin/ext/web/api/contract/openapi3/OpenAPI3RouterFactory;", "", "()V", "createAwait", "Lio/vertx/ext/web/api/contract/openapi3/OpenAPI3RouterFactory;", "vertx", "Lio/vertx/core/Vertx;", "url", "", "(Lio/vertx/core/Vertx;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "auth", "", "Lio/vertx/core/json/JsonObject;", "(Lio/vertx/core/Vertx;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/api/contract/openapi3/OpenAPI3RouterFactory.class */
public final class OpenAPI3RouterFactory {
    public static final OpenAPI3RouterFactory INSTANCE = new OpenAPI3RouterFactory();

    private OpenAPI3RouterFactory() {
    }

    @Nullable
    public final Object createAwait(@NotNull final Vertx vertx, @NotNull final String url, @NotNull Continuation<? super io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory>>, Unit>() { // from class: io.vertx.kotlin.ext.web.api.contract.openapi3.OpenAPI3RouterFactory.createAwait.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory.create(vertx, url, it);
            }
        }, continuation);
    }

    @Nullable
    public final Object createAwait(@NotNull final Vertx vertx, @NotNull final String url, @NotNull final List<? extends JsonObject> list, @NotNull Continuation<? super io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory>>, Unit>() { // from class: io.vertx.kotlin.ext.web.api.contract.openapi3.OpenAPI3RouterFactory.createAwait.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory.create(vertx, url, list, it);
            }
        }, continuation);
    }
}
