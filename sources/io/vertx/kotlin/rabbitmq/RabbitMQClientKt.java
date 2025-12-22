package io.vertx.kotlin.rabbitmq;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.rabbitmq.QueueOptions;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.rabbitmq.RabbitMQConsumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RabbitMQClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��@\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b&\u001a%\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\b\u001a\u00020\t*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a%\u0010\b\u001a\u00020\t*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a%\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0012\u001a-\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0015\u001a-\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001b\u001a\u001d\u0010\u001c\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001eH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001f\u001a%\u0010\u001c\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010!\u001a-\u0010\u001c\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010#\u001a\u0015\u0010$\u001a\u00020\u0017*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010%\u001a-\u0010&\u001a\u00020\u0017*\u00020\u00022\u0006\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010)\u001a5\u0010*\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010.\u001a=\u0010*\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u00100\u001a\u001d\u00101\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a-\u00102\u001a\u00020\u0017*\u00020\u00022\u0006\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010)\u001a\u001d\u00103\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a-\u00104\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010)\u001a\u0015\u00105\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010%\u001a5\u00106\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u00108\u001a=\u00106\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u00109\u001a\u001d\u0010:\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a-\u0010;\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010>\u001a\u0015\u0010?\u001a\u00020\u0017*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010%\u001a\u0015\u0010@\u001a\u00020\u0017*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010%\u001a\u0015\u0010A\u001a\u00020\u0017*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010%\u001a\u001d\u0010A\u001a\u00020\u0017*\u00020\u00022\u0006\u0010B\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010C\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006D"}, d2 = {"basicAckAwait", "Lio/vertx/core/json/JsonObject;", "Lio/vertx/rabbitmq/RabbitMQClient;", "deliveryTag", "", "multiple", "", "(Lio/vertx/rabbitmq/RabbitMQClient;JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "basicConsumerAwait", "Lio/vertx/rabbitmq/RabbitMQConsumer;", "queue", "", "(Lio/vertx/rabbitmq/RabbitMQClient;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "options", "Lio/vertx/rabbitmq/QueueOptions;", "(Lio/vertx/rabbitmq/RabbitMQClient;Ljava/lang/String;Lio/vertx/rabbitmq/QueueOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "basicGetAwait", "autoAck", "(Lio/vertx/rabbitmq/RabbitMQClient;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "basicNackAwait", "requeue", "(Lio/vertx/rabbitmq/RabbitMQClient;JZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "basicPublishAwait", "", "exchange", "routingKey", "message", "(Lio/vertx/rabbitmq/RabbitMQClient;Ljava/lang/String;Ljava/lang/String;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "basicQosAwait", "prefetchCount", "", "(Lio/vertx/rabbitmq/RabbitMQClient;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "global", "(Lio/vertx/rabbitmq/RabbitMQClient;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prefetchSize", "(Lio/vertx/rabbitmq/RabbitMQClient;IIZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmSelectAwait", "(Lio/vertx/rabbitmq/RabbitMQClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exchangeBindAwait", RtspHeaders.Values.DESTINATION, PackageDocumentBase.DCTags.source, "(Lio/vertx/rabbitmq/RabbitMQClient;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exchangeDeclareAwait", "type", "durable", "autoDelete", "(Lio/vertx/rabbitmq/RabbitMQClient;Ljava/lang/String;Ljava/lang/String;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "config", "(Lio/vertx/rabbitmq/RabbitMQClient;Ljava/lang/String;Ljava/lang/String;ZZLio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exchangeDeleteAwait", "exchangeUnbindAwait", "messageCountAwait", "queueBindAwait", "queueDeclareAutoAwait", "queueDeclareAwait", "exclusive", "(Lio/vertx/rabbitmq/RabbitMQClient;Ljava/lang/String;ZZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/rabbitmq/RabbitMQClient;Ljava/lang/String;ZZZLio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queueDeleteAwait", "queueDeleteIfAwait", "ifUnused", "ifEmpty", "(Lio/vertx/rabbitmq/RabbitMQClient;Ljava/lang/String;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startAwait", "stopAwait", "waitForConfirmsAwait", RtspHeaders.Values.TIMEOUT, "(Lio/vertx/rabbitmq/RabbitMQClient;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/rabbitmq/RabbitMQClientKt.class */
public final class RabbitMQClientKt {
    @Nullable
    public static final Object basicAckAwait(@NotNull final RabbitMQClient $this$basicAckAwait, final long deliveryTag, final boolean multiple, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicAckAwait.2
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
                $this$basicAckAwait.basicAck(deliveryTag, multiple, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object basicNackAwait(@NotNull final RabbitMQClient $this$basicNackAwait, final long deliveryTag, final boolean multiple, final boolean requeue, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicNackAwait.2
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
                $this$basicNackAwait.basicNack(deliveryTag, multiple, requeue, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object basicGetAwait(@NotNull final RabbitMQClient $this$basicGetAwait, @NotNull final String queue, final boolean autoAck, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicGetAwait.2
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
                $this$basicGetAwait.basicGet(queue, autoAck, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object basicConsumerAwait(@NotNull final RabbitMQClient $this$basicConsumerAwait, @NotNull final String queue, @NotNull Continuation<? super RabbitMQConsumer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RabbitMQConsumer>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicConsumerAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<RabbitMQConsumer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<RabbitMQConsumer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$basicConsumerAwait.basicConsumer(queue, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object basicConsumerAwait(@NotNull final RabbitMQClient $this$basicConsumerAwait, @NotNull final String queue, @NotNull final QueueOptions options, @NotNull Continuation<? super RabbitMQConsumer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RabbitMQConsumer>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicConsumerAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<RabbitMQConsumer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<RabbitMQConsumer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$basicConsumerAwait.basicConsumer(queue, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object basicPublishAwait(@NotNull final RabbitMQClient $this$basicPublishAwait, @NotNull final String exchange, @NotNull final String routingKey, @NotNull final JsonObject message, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicPublishAwait.2
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
                $this$basicPublishAwait.basicPublish(exchange, routingKey, message, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicPublishAwait.2.1
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
    public static final Object confirmSelectAwait(@NotNull final RabbitMQClient $this$confirmSelectAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.confirmSelectAwait.2
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
                $this$confirmSelectAwait.confirmSelect(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.confirmSelectAwait.2.1
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
    public static final Object waitForConfirmsAwait(@NotNull final RabbitMQClient $this$waitForConfirmsAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.waitForConfirmsAwait.2
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
                $this$waitForConfirmsAwait.waitForConfirms(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.waitForConfirmsAwait.2.1
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
    public static final Object waitForConfirmsAwait(@NotNull final RabbitMQClient $this$waitForConfirmsAwait, final long timeout, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.waitForConfirmsAwait.4
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
                $this$waitForConfirmsAwait.waitForConfirms(timeout, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.waitForConfirmsAwait.4.1
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
    public static final Object basicQosAwait(@NotNull final RabbitMQClient $this$basicQosAwait, final int prefetchCount, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicQosAwait.2
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
                $this$basicQosAwait.basicQos(prefetchCount, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicQosAwait.2.1
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
    public static final Object basicQosAwait(@NotNull final RabbitMQClient $this$basicQosAwait, final int prefetchCount, final boolean global, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicQosAwait.4
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
                $this$basicQosAwait.basicQos(prefetchCount, global, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicQosAwait.4.1
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
    public static final Object basicQosAwait(@NotNull final RabbitMQClient $this$basicQosAwait, final int prefetchSize, final int prefetchCount, final boolean global, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicQosAwait.6
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
                $this$basicQosAwait.basicQos(prefetchSize, prefetchCount, global, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.basicQosAwait.6.1
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
    public static final Object exchangeDeclareAwait(@NotNull final RabbitMQClient $this$exchangeDeclareAwait, @NotNull final String exchange, @NotNull final String type, final boolean durable, final boolean autoDelete, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.exchangeDeclareAwait.2
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
                $this$exchangeDeclareAwait.exchangeDeclare(exchange, type, durable, autoDelete, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.exchangeDeclareAwait.2.1
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
    public static final Object exchangeDeclareAwait(@NotNull final RabbitMQClient $this$exchangeDeclareAwait, @NotNull final String exchange, @NotNull final String type, final boolean durable, final boolean autoDelete, @NotNull final JsonObject config, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.exchangeDeclareAwait.4
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
                $this$exchangeDeclareAwait.exchangeDeclare(exchange, type, durable, autoDelete, config, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.exchangeDeclareAwait.4.1
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
    public static final Object exchangeDeleteAwait(@NotNull final RabbitMQClient $this$exchangeDeleteAwait, @NotNull final String exchange, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.exchangeDeleteAwait.2
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
                $this$exchangeDeleteAwait.exchangeDelete(exchange, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.exchangeDeleteAwait.2.1
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
    public static final Object exchangeBindAwait(@NotNull final RabbitMQClient $this$exchangeBindAwait, @NotNull final String destination, @NotNull final String source, @NotNull final String routingKey, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.exchangeBindAwait.2
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
                $this$exchangeBindAwait.exchangeBind(destination, source, routingKey, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.exchangeBindAwait.2.1
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
    public static final Object exchangeUnbindAwait(@NotNull final RabbitMQClient $this$exchangeUnbindAwait, @NotNull final String destination, @NotNull final String source, @NotNull final String routingKey, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.exchangeUnbindAwait.2
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
                $this$exchangeUnbindAwait.exchangeUnbind(destination, source, routingKey, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.exchangeUnbindAwait.2.1
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
    public static final Object queueDeclareAutoAwait(@NotNull final RabbitMQClient $this$queueDeclareAutoAwait, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.queueDeclareAutoAwait.2
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
                $this$queueDeclareAutoAwait.queueDeclareAuto(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object queueDeclareAwait(@NotNull final RabbitMQClient $this$queueDeclareAwait, @NotNull final String queue, final boolean durable, final boolean exclusive, final boolean autoDelete, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.queueDeclareAwait.2
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
                $this$queueDeclareAwait.queueDeclare(queue, durable, exclusive, autoDelete, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object queueDeclareAwait(@NotNull final RabbitMQClient $this$queueDeclareAwait, @NotNull final String queue, final boolean durable, final boolean exclusive, final boolean autoDelete, @NotNull final JsonObject config, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.queueDeclareAwait.4
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
                $this$queueDeclareAwait.queueDeclare(queue, durable, exclusive, autoDelete, config, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object queueDeleteAwait(@NotNull final RabbitMQClient $this$queueDeleteAwait, @NotNull final String queue, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.queueDeleteAwait.2
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
                $this$queueDeleteAwait.queueDelete(queue, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object queueDeleteIfAwait(@NotNull final RabbitMQClient $this$queueDeleteIfAwait, @NotNull final String queue, final boolean ifUnused, final boolean ifEmpty, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.queueDeleteIfAwait.2
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
                $this$queueDeleteIfAwait.queueDeleteIf(queue, ifUnused, ifEmpty, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object queueBindAwait(@NotNull final RabbitMQClient $this$queueBindAwait, @NotNull final String queue, @NotNull final String exchange, @NotNull final String routingKey, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.queueBindAwait.2
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
                $this$queueBindAwait.queueBind(queue, exchange, routingKey, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.queueBindAwait.2.1
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
    public static final Object messageCountAwait(@NotNull final RabbitMQClient $this$messageCountAwait, @NotNull final String queue, @NotNull Continuation<? super Long> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Long>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.messageCountAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Long>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Long>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$messageCountAwait.messageCount(queue, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object startAwait(@NotNull final RabbitMQClient $this$startAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.startAwait.2
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
                $this$startAwait.start(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.startAwait.2.1
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
    public static final Object stopAwait(@NotNull final RabbitMQClient $this$stopAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.stopAwait.2
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
                $this$stopAwait.stop(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.rabbitmq.RabbitMQClientKt.stopAwait.2.1
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
