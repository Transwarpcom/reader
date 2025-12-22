package io.vertx.kotlin.ext.stomp;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.stomp.StompServerConnection;
import io.vertx.ext.stomp.StompServerHandler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: StompServerHandler.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001a\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a-\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"onAuthenticationRequestAwait", "", "Lio/vertx/ext/stomp/StompServerHandler;", "connection", "Lio/vertx/ext/stomp/StompServerConnection;", "login", "", "passcode", "(Lio/vertx/ext/stomp/StompServerHandler;Lio/vertx/ext/stomp/StompServerConnection;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/stomp/StompServerHandlerKt.class */
public final class StompServerHandlerKt {
    @Nullable
    public static final Object onAuthenticationRequestAwait(@NotNull final StompServerHandler $this$onAuthenticationRequestAwait, @NotNull final StompServerConnection connection, @NotNull final String login, @NotNull final String passcode, @NotNull Continuation<? super Boolean> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Boolean>>, Unit>() { // from class: io.vertx.kotlin.ext.stomp.StompServerHandlerKt.onAuthenticationRequestAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Boolean>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Boolean>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$onAuthenticationRequestAwait.onAuthenticationRequest(connection, login, passcode, it);
            }
        }, continuation);
    }
}
