package io.vertx.kotlin.servicediscovery.types;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.redis.RedisClient;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RedisDataSource.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ5\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000fJ)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/vertx/kotlin/servicediscovery/types/RedisDataSource;", "", "()V", "getRedisClientAwait", "Lio/vertx/redis/RedisClient;", "discovery", "Lio/vertx/servicediscovery/ServiceDiscovery;", "filter", "Lkotlin/Function1;", "Lio/vertx/servicediscovery/Record;", "", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumerConfiguration", "Lio/vertx/core/json/JsonObject;", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lkotlin/jvm/functions/Function1;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lio/vertx/core/json/JsonObject;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/RedisDataSource.class */
public final class RedisDataSource {
    public static final RedisDataSource INSTANCE = new RedisDataSource();

    private RedisDataSource() {
    }

    @Nullable
    public final Object getRedisClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final JsonObject filter, @NotNull Continuation<? super RedisClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RedisClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.RedisDataSource.getRedisClientAwait.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<RedisClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<RedisClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.servicediscovery.types.RedisDataSource.getRedisClient(discovery, filter, it);
            }
        }, continuation);
    }

    @Nullable
    public final Object getRedisClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final Function1<? super Record, Boolean> function1, @NotNull Continuation<? super RedisClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RedisClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.RedisDataSource.getRedisClientAwait.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: RedisDataSource.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u000128\u0010\u0002\u001a4\u0012\u0004\u0012\u00020\u0004 \b*\u0019\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lio/vertx/core/AsyncResult;", "Lio/vertx/redis/RedisClient;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.types.RedisDataSource$getRedisClientAwait$4$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/RedisDataSource$getRedisClientAwait$4$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<RedisClient>, Unit> {
                @Override // kotlin.jvm.internal.CallableReference
                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(Handler.class);
                }

                @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
                public final String getName() {
                    return "handle";
                }

                @Override // kotlin.jvm.internal.CallableReference
                public final String getSignature() {
                    return "handle(Ljava/lang/Object;)V";
                }

                AnonymousClass1(Handler handler) {
                    super(1, handler);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AsyncResult<RedisClient> asyncResult) {
                    invoke2(asyncResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncResult<RedisClient> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<RedisClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<RedisClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = discovery;
                Function1 function12 = function1;
                Object redisDataSource$sam$java_util_function_Function$0 = function12;
                if (function12 != null) {
                    redisDataSource$sam$java_util_function_Function$0 = new RedisDataSource$sam$java_util_function_Function$0(function12);
                }
                io.vertx.servicediscovery.types.RedisDataSource.getRedisClient(serviceDiscovery, (Function) redisDataSource$sam$java_util_function_Function$0, new RedisDataSource$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }

    @Nullable
    public final Object getRedisClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final JsonObject filter, @NotNull final JsonObject consumerConfiguration, @NotNull Continuation<? super RedisClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RedisClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.RedisDataSource.getRedisClientAwait.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<RedisClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<RedisClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.servicediscovery.types.RedisDataSource.getRedisClient(discovery, filter, consumerConfiguration, it);
            }
        }, continuation);
    }

    @Nullable
    public final Object getRedisClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final Function1<? super Record, Boolean> function1, @NotNull final JsonObject consumerConfiguration, @NotNull Continuation<? super RedisClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RedisClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.RedisDataSource.getRedisClientAwait.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: RedisDataSource.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u000128\u0010\u0002\u001a4\u0012\u0004\u0012\u00020\u0004 \b*\u0019\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lio/vertx/core/AsyncResult;", "Lio/vertx/redis/RedisClient;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.types.RedisDataSource$getRedisClientAwait$8$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/RedisDataSource$getRedisClientAwait$8$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<RedisClient>, Unit> {
                @Override // kotlin.jvm.internal.CallableReference
                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(Handler.class);
                }

                @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
                public final String getName() {
                    return "handle";
                }

                @Override // kotlin.jvm.internal.CallableReference
                public final String getSignature() {
                    return "handle(Ljava/lang/Object;)V";
                }

                AnonymousClass1(Handler handler) {
                    super(1, handler);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AsyncResult<RedisClient> asyncResult) {
                    invoke2(asyncResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncResult<RedisClient> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<RedisClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<RedisClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = discovery;
                Function1 function12 = function1;
                Object redisDataSource$sam$java_util_function_Function$0 = function12;
                if (function12 != null) {
                    redisDataSource$sam$java_util_function_Function$0 = new RedisDataSource$sam$java_util_function_Function$0(function12);
                }
                io.vertx.servicediscovery.types.RedisDataSource.getRedisClient(serviceDiscovery, (Function) redisDataSource$sam$java_util_function_Function$0, consumerConfiguration, new RedisDataSource$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }
}
