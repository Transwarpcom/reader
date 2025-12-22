package io.vertx.kotlin.ext.auth;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AuthProvider.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006"}, d2 = {"authenticateAwait", "Lio/vertx/ext/auth/User;", "Lio/vertx/ext/auth/AuthProvider;", "authInfo", "Lio/vertx/core/json/JsonObject;", "(Lio/vertx/ext/auth/AuthProvider;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/AuthProviderKt.class */
public final class AuthProviderKt {
    @Nullable
    public static final Object authenticateAwait(@NotNull final AuthProvider $this$authenticateAwait, @NotNull final JsonObject authInfo, @NotNull Continuation<? super User> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<User>>, Unit>() { // from class: io.vertx.kotlin.ext.auth.AuthProviderKt.authenticateAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<User>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<User>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$authenticateAwait.authenticate(authInfo, it);
            }
        }, continuation);
    }
}
