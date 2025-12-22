package io.vertx.kotlin.kafka.client.consumer;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.streams.WriteStream;
import io.vertx.kafka.client.common.PartitionInfo;
import io.vertx.kafka.client.common.TopicPartition;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import io.vertx.kafka.client.consumer.KafkaConsumerRecord;
import io.vertx.kafka.client.consumer.KafkaConsumerRecords;
import io.vertx.kafka.client.consumer.OffsetAndMetadata;
import io.vertx.kafka.client.consumer.OffsetAndTimestamp;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KafkaConsumer.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��^\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a5\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a;\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a3\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\t\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a5\u0010\r\u001a\u00020\u000e\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a-\u0010\u000f\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a-\u0010\u0010\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a5\u0010\u0011\u001a\u00020\u0012\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a5\u0010\u0013\u001a\u00020\u000e\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a=\u0010\u0014\u001a\u00020\u0015\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u000eH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0017\u001a;\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001d\u001a5\u0010\u001e\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a;\u0010\u001e\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a3\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\t\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001aG\u0010 \u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0018\u0010!\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030#0\"H\u0086@ø\u0001��¢\u0006\u0002\u0010$\u001aA\u0010%\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030&\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010'\u001a\u00020\u000eH\u0086@ø\u0001��¢\u0006\u0002\u0010(\u001a5\u0010)\u001a\u00020\u000e\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010*\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a5\u0010+\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a;\u0010+\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a=\u0010,\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u000eH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0017\u001a5\u0010.\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a;\u0010.\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a5\u0010/\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a;\u0010/\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a5\u00100\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001d\u001a;\u00100\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001c0\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a3\u00102\u001a\b\u0012\u0004\u0012\u00020\u001c0\t\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a-\u00103\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"assignAwait", "", OperatorName.STROKING_COLOR_CMYK, "V", "Lio/vertx/kafka/client/consumer/KafkaConsumer;", "topicPartition", "Lio/vertx/kafka/client/common/TopicPartition;", "(Lio/vertx/kafka/client/consumer/KafkaConsumer;Lio/vertx/kafka/client/common/TopicPartition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "topicPartitions", "", "(Lio/vertx/kafka/client/consumer/KafkaConsumer;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "assignmentAwait", "(Lio/vertx/kafka/client/consumer/KafkaConsumer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "beginningOffsetsAwait", "", "closeAwait", "commitAwait", "committedAwait", "Lio/vertx/kafka/client/consumer/OffsetAndMetadata;", "endOffsetsAwait", "offsetsForTimesAwait", "Lio/vertx/kafka/client/consumer/OffsetAndTimestamp;", "timestamp", "(Lio/vertx/kafka/client/consumer/KafkaConsumer;Lio/vertx/kafka/client/common/TopicPartition;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "partitionsForAwait", "", "Lio/vertx/kafka/client/common/PartitionInfo;", "topic", "", "(Lio/vertx/kafka/client/consumer/KafkaConsumer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pauseAwait", "pausedAwait", "pipeToAwait", "dst", "Lio/vertx/core/streams/WriteStream;", "Lio/vertx/kafka/client/consumer/KafkaConsumerRecord;", "(Lio/vertx/kafka/client/consumer/KafkaConsumer;Lio/vertx/core/streams/WriteStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pollAwait", "Lio/vertx/kafka/client/consumer/KafkaConsumerRecords;", RtspHeaders.Values.TIMEOUT, "(Lio/vertx/kafka/client/consumer/KafkaConsumer;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "positionAwait", "partition", "resumeAwait", "seekAwait", "offset", "seekToBeginningAwait", "seekToEndAwait", "subscribeAwait", "topics", "subscriptionAwait", "unsubscribeAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/client/consumer/KafkaConsumerKt.class */
public final class KafkaConsumerKt {
    @Nullable
    public static final <K, V> Object pipeToAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final WriteStream<KafkaConsumerRecord<K, V>> writeStream, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.pipeToAwait.2
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
                kafkaConsumer.pipeTo(writeStream, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.pipeToAwait.2.1
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
    public static final <K, V> Object subscribeAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final String topic, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.subscribeAwait.2
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
                kafkaConsumer.subscribe(topic, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.subscribeAwait.2.1
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
    public static final <K, V> Object subscribeAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final Set<String> set, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.subscribeAwait.4
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
                kafkaConsumer.subscribe(set, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.subscribeAwait.4.1
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
    public static final <K, V> Object assignAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final TopicPartition topicPartition, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.assignAwait.2
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
                kafkaConsumer.assign(topicPartition, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.assignAwait.2.1
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
    public static final <K, V> Object assignAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final Set<? extends TopicPartition> set, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.assignAwait.4
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
                kafkaConsumer.assign(set, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.assignAwait.4.1
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
    public static final <K, V> Object assignmentAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull Continuation<? super Set<? extends TopicPartition>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Set<? extends TopicPartition>>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.assignmentAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Set<? extends TopicPartition>>> handler) {
                invoke2((Handler<AsyncResult<Set<TopicPartition>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Set<TopicPartition>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                kafkaConsumer.assignment(it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object unsubscribeAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.unsubscribeAwait.2
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
                kafkaConsumer.unsubscribe(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.unsubscribeAwait.2.1
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
    public static final <K, V> Object subscriptionAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull Continuation<? super Set<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Set<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.subscriptionAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Set<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<Set<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Set<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                kafkaConsumer.subscription(it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object pauseAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final TopicPartition topicPartition, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.pauseAwait.2
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
                kafkaConsumer.pause(topicPartition, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.pauseAwait.2.1
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
    public static final <K, V> Object pauseAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final Set<? extends TopicPartition> set, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.pauseAwait.4
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
                kafkaConsumer.pause(set, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.pauseAwait.4.1
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
    public static final <K, V> Object pausedAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull Continuation<? super Set<? extends TopicPartition>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Set<? extends TopicPartition>>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.pausedAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Set<? extends TopicPartition>>> handler) {
                invoke2((Handler<AsyncResult<Set<TopicPartition>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Set<TopicPartition>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                kafkaConsumer.paused(it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object resumeAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final TopicPartition topicPartition, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.resumeAwait.2
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
                kafkaConsumer.resume(topicPartition, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.resumeAwait.2.1
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
    public static final <K, V> Object resumeAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final Set<? extends TopicPartition> set, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.resumeAwait.4
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
                kafkaConsumer.resume(set, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.resumeAwait.4.1
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
    public static final <K, V> Object seekAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final TopicPartition topicPartition, final long offset, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.seekAwait.2
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
                kafkaConsumer.seek(topicPartition, offset, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.seekAwait.2.1
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
    public static final <K, V> Object seekToBeginningAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final TopicPartition topicPartition, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.seekToBeginningAwait.2
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
                kafkaConsumer.seekToBeginning(topicPartition, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.seekToBeginningAwait.2.1
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
    public static final <K, V> Object seekToBeginningAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final Set<? extends TopicPartition> set, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.seekToBeginningAwait.4
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
                kafkaConsumer.seekToBeginning(set, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.seekToBeginningAwait.4.1
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
    public static final <K, V> Object seekToEndAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final TopicPartition topicPartition, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.seekToEndAwait.2
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
                kafkaConsumer.seekToEnd(topicPartition, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.seekToEndAwait.2.1
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
    public static final <K, V> Object seekToEndAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final Set<? extends TopicPartition> set, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.seekToEndAwait.4
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
                kafkaConsumer.seekToEnd(set, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.seekToEndAwait.4.1
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
    public static final <K, V> Object commitAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.commitAwait.2
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
                kafkaConsumer.commit(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.commitAwait.2.1
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
    public static final <K, V> Object committedAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final TopicPartition topicPartition, @NotNull Continuation<? super OffsetAndMetadata> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<OffsetAndMetadata>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.committedAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<OffsetAndMetadata>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<OffsetAndMetadata>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                kafkaConsumer.committed(topicPartition, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object partitionsForAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final String topic, @NotNull Continuation<? super List<? extends PartitionInfo>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends PartitionInfo>>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.partitionsForAwait.2
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
                kafkaConsumer.partitionsFor(topic, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object closeAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.closeAwait.2
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
                kafkaConsumer.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.closeAwait.2.1
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
    public static final <K, V> Object positionAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final TopicPartition partition, @NotNull Continuation<? super Long> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Long>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.positionAwait.2
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
                kafkaConsumer.position(partition, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object offsetsForTimesAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final TopicPartition topicPartition, final long timestamp, @NotNull Continuation<? super OffsetAndTimestamp> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<OffsetAndTimestamp>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.offsetsForTimesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<OffsetAndTimestamp>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<OffsetAndTimestamp>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                kafkaConsumer.offsetsForTimes(topicPartition, Long.valueOf(timestamp), it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object beginningOffsetsAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final TopicPartition topicPartition, @NotNull Continuation<? super Long> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Long>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.beginningOffsetsAwait.2
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
                kafkaConsumer.beginningOffsets(topicPartition, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object endOffsetsAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, @NotNull final TopicPartition topicPartition, @NotNull Continuation<? super Long> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Long>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.endOffsetsAwait.2
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
                kafkaConsumer.endOffsets(topicPartition, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <K, V> Object pollAwait(@NotNull final KafkaConsumer<K, V> kafkaConsumer, final long timeout, @NotNull Continuation<? super KafkaConsumerRecords<K, V>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<KafkaConsumerRecords<K, V>>>, Unit>() { // from class: io.vertx.kotlin.kafka.client.consumer.KafkaConsumerKt.pollAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<KafkaConsumerRecords<K, V>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                kafkaConsumer.poll(timeout, it);
            }
        }, continuation);
    }
}
