package io.vertx.kotlin.ext.web.sstore;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.sstore.SessionStore;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SessionStore.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n��\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001f\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u001a\u0015\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"clearAwait", "", "Lio/vertx/ext/web/sstore/SessionStore;", "(Lio/vertx/ext/web/sstore/SessionStore;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAwait", "id", "", "(Lio/vertx/ext/web/sstore/SessionStore;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAwait", "Lio/vertx/ext/web/Session;", "cookieValue", "putAwait", "session", "(Lio/vertx/ext/web/sstore/SessionStore;Lio/vertx/ext/web/Session;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sizeAwait", "", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/sstore/SessionStoreKt.class */
public final class SessionStoreKt {
    @Nullable
    public static final Object getAwait(@NotNull final SessionStore $this$getAwait, @NotNull final String cookieValue, @NotNull Continuation<? super Session> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Session>>, Unit>() { // from class: io.vertx.kotlin.ext.web.sstore.SessionStoreKt.getAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Session>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Session>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getAwait.get(cookieValue, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object deleteAwait(@NotNull final SessionStore $this$deleteAwait, @NotNull final String id, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.web.sstore.SessionStoreKt.deleteAwait.2
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
                $this$deleteAwait.delete(id, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.web.sstore.SessionStoreKt.deleteAwait.2.1
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
    public static final Object putAwait(@NotNull final SessionStore $this$putAwait, @NotNull final Session session, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.web.sstore.SessionStoreKt.putAwait.2
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
                $this$putAwait.put(session, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.web.sstore.SessionStoreKt.putAwait.2.1
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
    public static final Object clearAwait(@NotNull final SessionStore $this$clearAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.web.sstore.SessionStoreKt.clearAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$clearAwait.clear(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.web.sstore.SessionStoreKt.clearAwait.2.1
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
    public static final Object sizeAwait(@NotNull final SessionStore $this$sizeAwait, @NotNull Continuation<? super Integer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Integer>>, Unit>() { // from class: io.vertx.kotlin.ext.web.sstore.SessionStoreKt.sizeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Integer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Integer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$sizeAwait.size(it);
            }
        }, continuation);
    }
}
