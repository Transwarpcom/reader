package io.vertx.kotlin.kafka.admin;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.kafka.admin.ConsumerGroupListing;
import io.vertx.kafka.admin.KafkaAdminClient;
import io.vertx.kafka.admin.NewTopic;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KafkaAdminClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��,\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n��\u001a#\u0010��\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"createTopicsAwait", "", "Lio/vertx/kafka/admin/KafkaAdminClient;", "topics", "", "Lio/vertx/kafka/admin/NewTopic;", "(Lio/vertx/kafka/admin/KafkaAdminClient;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTopicsAwait", "topicNames", "", "listConsumerGroupsAwait", "Lio/vertx/kafka/admin/ConsumerGroupListing;", "(Lio/vertx/kafka/admin/KafkaAdminClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listTopicsAwait", "", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/kafka/admin/KafkaAdminClientKt.class */
public final class KafkaAdminClientKt {
    @Nullable
    public static final Object listTopicsAwait(@NotNull final KafkaAdminClient $this$listTopicsAwait, @NotNull Continuation<? super Set<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Set<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.kafka.admin.KafkaAdminClientKt.listTopicsAwait.2
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
                $this$listTopicsAwait.listTopics(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createTopicsAwait(@NotNull final KafkaAdminClient $this$createTopicsAwait, @NotNull final List<? extends NewTopic> list, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.admin.KafkaAdminClientKt.createTopicsAwait.2
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
                $this$createTopicsAwait.createTopics(list, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.admin.KafkaAdminClientKt.createTopicsAwait.2.1
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
    public static final Object deleteTopicsAwait(@NotNull final KafkaAdminClient $this$deleteTopicsAwait, @NotNull final List<String> list, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.kafka.admin.KafkaAdminClientKt.deleteTopicsAwait.2
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
                $this$deleteTopicsAwait.deleteTopics(list, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.kafka.admin.KafkaAdminClientKt.deleteTopicsAwait.2.1
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
    public static final Object listConsumerGroupsAwait(@NotNull final KafkaAdminClient $this$listConsumerGroupsAwait, @NotNull Continuation<? super List<? extends ConsumerGroupListing>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends ConsumerGroupListing>>>, Unit>() { // from class: io.vertx.kotlin.kafka.admin.KafkaAdminClientKt.listConsumerGroupsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends ConsumerGroupListing>>> handler) {
                invoke2((Handler<AsyncResult<List<ConsumerGroupListing>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<ConsumerGroupListing>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listConsumerGroupsAwait.listConsumerGroups(it);
            }
        }, continuation);
    }
}
