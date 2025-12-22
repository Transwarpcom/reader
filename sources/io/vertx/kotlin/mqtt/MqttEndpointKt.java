package io.vertx.kotlin.mqtt;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.mqtt.MqttEndpoint;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MqttEndpoint.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��&\n��\n\u0002\u0010\b\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a=\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001aE\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"publishAwait", "", "Lio/vertx/mqtt/MqttEndpoint;", "topic", "", "payload", "Lio/vertx/core/buffer/Buffer;", "qosLevel", "Lio/netty/handler/codec/mqtt/MqttQoS;", "isDup", "", "isRetain", "(Lio/vertx/mqtt/MqttEndpoint;Ljava/lang/String;Lio/vertx/core/buffer/Buffer;Lio/netty/handler/codec/mqtt/MqttQoS;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "messageId", "(Lio/vertx/mqtt/MqttEndpoint;Ljava/lang/String;Lio/vertx/core/buffer/Buffer;Lio/netty/handler/codec/mqtt/MqttQoS;ZZILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/mqtt/MqttEndpointKt.class */
public final class MqttEndpointKt {
    @Nullable
    public static final Object publishAwait(@NotNull final MqttEndpoint $this$publishAwait, @NotNull final String topic, @NotNull final Buffer payload, @NotNull final MqttQoS qosLevel, final boolean isDup, final boolean isRetain, @NotNull Continuation<? super Integer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Integer>>, Unit>() { // from class: io.vertx.kotlin.mqtt.MqttEndpointKt.publishAwait.2
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
    public static final Object publishAwait(@NotNull final MqttEndpoint $this$publishAwait, @NotNull final String topic, @NotNull final Buffer payload, @NotNull final MqttQoS qosLevel, final boolean isDup, final boolean isRetain, final int messageId, @NotNull Continuation<? super Integer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Integer>>, Unit>() { // from class: io.vertx.kotlin.mqtt.MqttEndpointKt.publishAwait.4
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
                $this$publishAwait.publish(topic, payload, qosLevel, isDup, isRetain, messageId, it);
            }
        }, continuation);
    }
}
