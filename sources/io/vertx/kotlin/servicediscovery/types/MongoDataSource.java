package io.vertx.kotlin.servicediscovery.types;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
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

/* compiled from: MongoDataSource.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lio/vertx/kotlin/servicediscovery/types/MongoDataSource;", "", "()V", "getMongoClientAwait", "Lio/vertx/ext/mongo/MongoClient;", "discovery", "Lio/vertx/servicediscovery/ServiceDiscovery;", "filter", "Lkotlin/Function1;", "Lio/vertx/servicediscovery/Record;", "", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/vertx/core/json/JsonObject;", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumerConfiguration", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lio/vertx/core/json/JsonObject;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/MongoDataSource.class */
public final class MongoDataSource {
    public static final MongoDataSource INSTANCE = new MongoDataSource();

    private MongoDataSource() {
    }

    @Nullable
    public final Object getMongoClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final JsonObject filter, @NotNull Continuation<? super MongoClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.MongoDataSource.getMongoClientAwait.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.servicediscovery.types.MongoDataSource.getMongoClient(discovery, filter, it);
            }
        }, continuation);
    }

    @Nullable
    public final Object getMongoClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final Function1<? super Record, Boolean> function1, @NotNull Continuation<? super MongoClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.MongoDataSource.getMongoClientAwait.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: MongoDataSource.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u000128\u0010\u0002\u001a4\u0012\u0004\u0012\u00020\u0004 \b*\u0019\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lio/vertx/core/AsyncResult;", "Lio/vertx/ext/mongo/MongoClient;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.types.MongoDataSource$getMongoClientAwait$4$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/MongoDataSource$getMongoClientAwait$4$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<MongoClient>, Unit> {
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
                public /* bridge */ /* synthetic */ Unit invoke(AsyncResult<MongoClient> asyncResult) {
                    invoke2(asyncResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncResult<MongoClient> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = discovery;
                final Function1 function12 = function1;
                Object obj = function12;
                if (function12 != null) {
                    obj = new Function() { // from class: io.vertx.kotlin.servicediscovery.types.MongoDataSource$sam$java_util_function_Function$0
                        @Override // java.util.function.Function
                        public final /* synthetic */ Object apply(Object p0) {
                            return function12.invoke(p0);
                        }
                    };
                }
                final AnonymousClass1 anonymousClass1 = new AnonymousClass1(it);
                io.vertx.servicediscovery.types.MongoDataSource.getMongoClient(serviceDiscovery, (Function) obj, new Handler() { // from class: io.vertx.kotlin.servicediscovery.types.MongoDataSource$sam$io_vertx_core_Handler$0
                    @Override // io.vertx.core.Handler
                    public final /* synthetic */ void handle(Object p0) {
                        Intrinsics.checkExpressionValueIsNotNull(anonymousClass1.invoke(p0), "invoke(...)");
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public final Object getMongoClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final JsonObject filter, @NotNull final JsonObject consumerConfiguration, @NotNull Continuation<? super MongoClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.MongoDataSource.getMongoClientAwait.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.servicediscovery.types.MongoDataSource.getMongoClient(discovery, filter, consumerConfiguration, it);
            }
        }, continuation);
    }
}
