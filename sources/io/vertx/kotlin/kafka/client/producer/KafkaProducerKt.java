package io.vertx.kotlin.kafka.client.producer;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.kafka.client.common.PartitionInfo;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import io.vertx.kafka.client.producer.RecordMetadata;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KafkaProducer.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��<\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a-\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a5\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a-\u0010\t\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001aA\u0010\t\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a;\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0012\u001aA\u0010\u0013\u001a\u00020\u0014\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001aA\u0010\u0016\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"closeAwait", "", OperatorName.STROKING_COLOR_CMYK, "V", "Lio/vertx/kafka/client/producer/KafkaProducer;", "(Lio/vertx/kafka/client/producer/KafkaProducer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", RtspHeaders.Values.TIMEOUT, "", "(Lio/vertx/kafka/client/producer/KafkaProducer;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "endAwait", "data", "Lio/vertx/kafka/client/producer/KafkaProducerRecord;", "(Lio/vertx/kafka/client/producer/KafkaProducer;Lio/vertx/kafka/client/producer/KafkaProducerRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "partitionsForAwait", "", "Lio/vertx/kafka/client/common/PartitionInfo;", "topic", "", "(Lio/vertx/kafka/client/producer/KafkaProducer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendAwait", "Lio/vertx/kafka/client/producer/RecordMetadata;", "record", "writeAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/client/producer/KafkaProducerKt.class */
public final class KafkaProducerKt {
    @Nullable
    public static final <K, V> Object endAwait(@NotNull final KafkaProducer<K, V> kafkaProducer, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.endAwait.2
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
                kafkaProducer.end(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.endAwait.2.1
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
    public static final <K, V> Object endAwait(@NotNull final KafkaProducer<K, V> kafkaProducer, @NotNull final KafkaProducerRecord<K, V> kafkaProducerRecord, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.endAwait.4
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
                kafkaProducer.end(kafkaProducerRecord, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.endAwait.4.1
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
    public static final <K, V> Object writeAwait(@NotNull final KafkaProducer<K, V> kafkaProducer, @NotNull final KafkaProducerRecord<K, V> kafkaProducerRecord, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.writeAwait.2
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
                kafkaProducer.write(kafkaProducerRecord, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.writeAwait.2.1
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
    public static final <K, V> Object sendAwait(@NotNull final KafkaProducer<K, V> kafkaProducer, @NotNull final KafkaProducerRecord<K, V> kafkaProducerRecord, @NotNull Continuation<? super RecordMetadata> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RecordMetadata>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.sendAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<RecordMetadata>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<RecordMetadata>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                kafkaProducer.send(kafkaProducerRecord, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object partitionsForAwait(@NotNull final KafkaProducer<K, V> kafkaProducer, @NotNull final String topic, @NotNull Continuation<? super List<? extends PartitionInfo>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends PartitionInfo>>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.partitionsForAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends PartitionInfo>>> handler) {
                invoke2((Handler<AsyncResult<List<PartitionInfo>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<PartitionInfo>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                kafkaProducer.partitionsFor(topic, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object closeAwait(@NotNull final KafkaProducer<K, V> kafkaProducer, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.closeAwait.2
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
                kafkaProducer.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.closeAwait.2.1
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
    public static final <K, V> Object closeAwait(@NotNull final KafkaProducer<K, V> kafkaProducer, final long timeout, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.closeAwait.4
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
                kafkaProducer.close(timeout, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.producer.KafkaProducerKt.closeAwait.4.1
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
