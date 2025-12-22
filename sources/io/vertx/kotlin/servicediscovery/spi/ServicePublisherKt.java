package io.vertx.kotlin.servicediscovery.spi;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.spi.ServicePublisher;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ServicePublisher.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u001d\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u001a\u001d\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"publishAwait", "Lio/vertx/servicediscovery/Record;", "Lio/vertx/servicediscovery/spi/ServicePublisher;", "record", "(Lio/vertx/servicediscovery/spi/ServicePublisher;Lio/vertx/servicediscovery/Record;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unpublishAwait", "", "id", "", "(Lio/vertx/servicediscovery/spi/ServicePublisher;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/spi/ServicePublisherKt.class */
public final class ServicePublisherKt {
    @Nullable
    public static final Object publishAwait(@NotNull final ServicePublisher $this$publishAwait, @NotNull final Record record, @NotNull Continuation<? super Record> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Record>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.spi.ServicePublisherKt.publishAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Record>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Record>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$publishAwait.publish(record, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object unpublishAwait(@NotNull final ServicePublisher $this$unpublishAwait, @NotNull final String id, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.spi.ServicePublisherKt.unpublishAwait.2
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
                $this$unpublishAwait.unpublish(id, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.servicediscovery.spi.ServicePublisherKt.unpublishAwait.2.1
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
    public static final Object updateAwait(@NotNull final ServicePublisher $this$updateAwait, @NotNull final Record record, @NotNull Continuation<? super Record> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Record>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.spi.ServicePublisherKt.updateAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Record>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Record>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$updateAwait.update(record, it);
            }
        }, continuation);
    }
}
