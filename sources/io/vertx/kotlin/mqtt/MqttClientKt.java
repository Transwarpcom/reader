package io.vertx.kotlin.mqtt;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.messages.MqttConnAckMessage;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MqttClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��>\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0004\u001a%\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a-\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a=\u0010\r\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016\u001a%\u0010\u0017\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0019\u001a)\u0010\u0017\u001a\u00020\u0004*\u00020\u00022\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u001bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001c\u001a\u001d\u0010\u001d\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"connectAwait", "Lio/vertx/mqtt/messages/MqttConnAckMessage;", "Lio/vertx/mqtt/MqttClient;", RtspHeaders.Values.PORT, "", "host", "", "(Lio/vertx/mqtt/MqttClient;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "serverName", "(Lio/vertx/mqtt/MqttClient;ILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnectAwait", "", "(Lio/vertx/mqtt/MqttClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "publishAwait", "topic", "payload", "Lio/vertx/core/buffer/Buffer;", "qosLevel", "Lio/netty/handler/codec/mqtt/MqttQoS;", "isDup", "", "isRetain", "(Lio/vertx/mqtt/MqttClient;Ljava/lang/String;Lio/vertx/core/buffer/Buffer;Lio/netty/handler/codec/mqtt/MqttQoS;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "subscribeAwait", "qos", "(Lio/vertx/mqtt/MqttClient;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "topics", "", "(Lio/vertx/mqtt/MqttClient;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unsubscribeAwait", "(Lio/vertx/mqtt/MqttClient;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/mqtt/MqttClientKt.class */
public final class MqttClientKt {
    @Nullable
    public static final Object connectAwait(@NotNull final MqttClient $this$connectAwait, final int port, @NotNull final String host, @NotNull Continuation<? super MqttConnAckMessage> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MqttConnAckMessage>>, Unit>() { // from class: io.vertx.kotlin.mqtt.MqttClientKt.connectAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MqttConnAckMessage>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MqttConnAckMessage>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$connectAwait.connect(port, host, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object connectAwait(@NotNull final MqttClient $this$connectAwait, final int port, @NotNull final String host, @NotNull final String serverName, @NotNull Continuation<? super MqttConnAckMessage> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MqttConnAckMessage>>, Unit>() { // from class: io.vertx.kotlin.mqtt.MqttClientKt.connectAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MqttConnAckMessage>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MqttConnAckMessage>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$connectAwait.connect(port, host, serverName, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object disconnectAwait(@NotNull final MqttClient $this$disconnectAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.mqtt.MqttClientKt.disconnectAwait.2
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
                $this$disconnectAwait.disconnect(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.mqtt.MqttClientKt.disconnectAwait.2.1
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
    public static final Object publishAwait(@NotNull final MqttClient $this$publishAwait, @NotNull final String topic, @NotNull final Buffer payload, @NotNull final MqttQoS qosLevel, final boolean isDup, final boolean isRetain, @NotNull Continuation<? super Integer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Integer>>, Unit>() { // from class: io.vertx.kotlin.mqtt.MqttClientKt.publishAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Integer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Integer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$publishAwait.publish(topic, payload, qosLevel, isDup, isRetain, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object subscribeAwait(@NotNull final MqttClient $this$subscribeAwait, @NotNull final String topic, final int qos, @NotNull Continuation<? super Integer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Integer>>, Unit>() { // from class: io.vertx.kotlin.mqtt.MqttClientKt.subscribeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Integer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Integer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$subscribeAwait.subscribe(topic, qos, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object subscribeAwait(@NotNull final MqttClient $this$subscribeAwait, @NotNull final Map<String, Integer> map, @NotNull Continuation<? super Integer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Integer>>, Unit>() { // from class: io.vertx.kotlin.mqtt.MqttClientKt.subscribeAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Integer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Integer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$subscribeAwait.subscribe(map, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object unsubscribeAwait(@NotNull final MqttClient $this$unsubscribeAwait, @NotNull final String topic, @NotNull Continuation<? super Integer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Integer>>, Unit>() { // from class: io.vertx.kotlin.mqtt.MqttClientKt.unsubscribeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Integer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Integer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$unsubscribeAwait.unsubscribe(topic, it);
            }
        }, continuation);
    }
}
