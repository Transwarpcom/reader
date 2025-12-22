package io.vertx.kotlin.ext.auth.oauth2.providers;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2ClientOptions;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OpenIDConnectAuth.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lio/vertx/kotlin/ext/auth/oauth2/providers/OpenIDConnectAuth;", "", "()V", "discoverAwait", "Lio/vertx/ext/auth/oauth2/OAuth2Auth;", "vertx", "Lio/vertx/core/Vertx;", "config", "Lio/vertx/ext/auth/oauth2/OAuth2ClientOptions;", "(Lio/vertx/core/Vertx;Lio/vertx/ext/auth/oauth2/OAuth2ClientOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/oauth2/providers/OpenIDConnectAuth.class */
public final class OpenIDConnectAuth {
    public static final OpenIDConnectAuth INSTANCE = new OpenIDConnectAuth();

    private OpenIDConnectAuth() {
    }

    @Nullable
    public final Object discoverAwait(@NotNull final Vertx vertx, @NotNull final OAuth2ClientOptions config, @NotNull Continuation<? super OAuth2Auth> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<OAuth2Auth>>, Unit>() { // from class: io.vertx.kotlin.ext.auth.oauth2.providers.OpenIDConnectAuth.discoverAwait.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<OAuth2Auth>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<OAuth2Auth>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.ext.auth.oauth2.providers.OpenIDConnectAuth.discover(vertx, config, it);
            }
        }, continuation);
    }
}
