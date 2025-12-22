package io.vertx.kotlin.ext.auth.oauth2;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.AccessToken;
import io.vertx.ext.auth.oauth2.OAuth2Response;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;

/* compiled from: AccessToken.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��.\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\u001a5\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a\u001d\u0010\r\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a\u0015\u0010\u0011\u001a\u00020\u000e*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a\u0015\u0010\u0012\u001a\u00020\u000e*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a\u001d\u0010\u0013\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a\u0015\u0010\u0015\u001a\u00020\b*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"fetchAwait", "Lio/vertx/ext/auth/oauth2/OAuth2Response;", "Lio/vertx/ext/auth/oauth2/AccessToken;", "method", "Lio/vertx/core/http/HttpMethod;", DefaultBeanDefinitionDocumentReader.RESOURCE_ATTRIBUTE, "", "headers", "Lio/vertx/core/json/JsonObject;", "payload", "Lio/vertx/core/buffer/Buffer;", "(Lio/vertx/ext/auth/oauth2/AccessToken;Lio/vertx/core/http/HttpMethod;Ljava/lang/String;Lio/vertx/core/json/JsonObject;Lio/vertx/core/buffer/Buffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/ext/auth/oauth2/AccessToken;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "introspectAwait", "", "(Lio/vertx/ext/auth/oauth2/AccessToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tokenType", "logoutAwait", "refreshAwait", "revokeAwait", "token_type", "userInfoAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/auth/oauth2/AccessTokenKt.class */
public final class AccessTokenKt {
    @Nullable
    public static final Object refreshAwait(@NotNull final AccessToken $this$refreshAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.refreshAwait.2
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
                $this$refreshAwait.refresh(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.refreshAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object revokeAwait(@NotNull final AccessToken $this$revokeAwait, @NotNull final String token_type, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.revokeAwait.2
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
                $this$revokeAwait.revoke(token_type, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.revokeAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object logoutAwait(@NotNull final AccessToken $this$logoutAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.logoutAwait.2
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
                $this$logoutAwait.logout(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.logoutAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object introspectAwait(@NotNull final AccessToken $this$introspectAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.introspectAwait.2
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
                $this$introspectAwait.introspect(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.introspectAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object introspectAwait(@NotNull final AccessToken $this$introspectAwait, @NotNull final String tokenType, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.introspectAwait.4
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
                $this$introspectAwait.introspect(tokenType, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.introspectAwait.4.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object userInfoAwait(@NotNull final AccessToken $this$userInfoAwait, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.userInfoAwait.2
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
                $this$userInfoAwait.userInfo(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object fetchAwait(@NotNull final AccessToken $this$fetchAwait, @NotNull final String resource, @NotNull Continuation<? super OAuth2Response> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<OAuth2Response>>, Unit>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.fetchAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<OAuth2Response>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<OAuth2Response>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$fetchAwait.fetch(resource, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object fetchAwait(@NotNull final AccessToken $this$fetchAwait, @NotNull final HttpMethod method, @NotNull final String resource, @NotNull final JsonObject headers, @NotNull final Buffer payload, @NotNull Continuation<? super OAuth2Response> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<OAuth2Response>>, Unit>() { // from class: io.vertx.kotlin.ext.auth.oauth2.AccessTokenKt.fetchAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<OAuth2Response>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<OAuth2Response>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$fetchAwait.fetch(method, resource, headers, payload, it);
            }
        }, continuation);
    }
}
