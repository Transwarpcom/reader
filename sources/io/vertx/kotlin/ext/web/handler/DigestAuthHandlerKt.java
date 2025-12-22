package io.vertx.kotlin.ext.web.handler;

import ch.qos.logback.classic.ClassicConstants;
import ch.qos.logback.core.CoreConstants;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.DigestAuthHandler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DigestAuthHandler.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a\u001d\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"authorizeAwait", "", "Lio/vertx/ext/web/handler/DigestAuthHandler;", ClassicConstants.USER_MDC_KEY, "Lio/vertx/ext/auth/User;", "(Lio/vertx/ext/web/handler/DigestAuthHandler;Lio/vertx/ext/auth/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseCredentialsAwait", "Lio/vertx/core/json/JsonObject;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/handler/DigestAuthHandler;Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/handler/DigestAuthHandlerKt.class */
public final class DigestAuthHandlerKt {
    @Nullable
    public static final Object parseCredentialsAwait(@NotNull final DigestAuthHandler $this$parseCredentialsAwait, @NotNull final RoutingContext context, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.web.handler.DigestAuthHandlerKt.parseCredentialsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonObject>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonObject>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$parseCredentialsAwait.parseCredentials(context, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object authorizeAwait(@NotNull final DigestAuthHandler $this$authorizeAwait, @NotNull final User user, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.web.handler.DigestAuthHandlerKt.authorizeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$authorizeAwait.authorize(user, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.web.handler.DigestAuthHandlerKt.authorizeAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }
}
