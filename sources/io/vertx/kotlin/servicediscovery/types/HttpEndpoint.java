package io.vertx.kotlin.servicediscovery.types;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
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

/* compiled from: HttpEndpoint.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ5\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000fJ)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010J-\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000bJ5\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ!\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000fJ)\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lio/vertx/kotlin/servicediscovery/types/HttpEndpoint;", "", "()V", "getClientAwait", "Lio/vertx/core/http/HttpClient;", "discovery", "Lio/vertx/servicediscovery/ServiceDiscovery;", "filter", "Lkotlin/Function1;", "Lio/vertx/servicediscovery/Record;", "", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "conf", "Lio/vertx/core/json/JsonObject;", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lkotlin/jvm/functions/Function1;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lio/vertx/core/json/JsonObject;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWebClientAwait", "Lio/vertx/ext/web/client/WebClient;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/HttpEndpoint.class */
public final class HttpEndpoint {
    public static final HttpEndpoint INSTANCE = new HttpEndpoint();

    private HttpEndpoint() {
    }

    @Nullable
    public final Object getClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final JsonObject filter, @NotNull Continuation<? super HttpClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.HttpEndpoint.getClientAwait.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<HttpClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<HttpClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.servicediscovery.types.HttpEndpoint.getClient(discovery, filter, it);
            }
        }, continuation);
    }

    @Nullable
    public final Object getWebClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final JsonObject filter, @NotNull Continuation<? super WebClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<WebClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.HttpEndpoint.getWebClientAwait.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<WebClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<WebClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.servicediscovery.types.HttpEndpoint.getWebClient(discovery, filter, it);
            }
        }, continuation);
    }

    @Nullable
    public final Object getClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final JsonObject filter, @NotNull final JsonObject conf, @NotNull Continuation<? super HttpClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.HttpEndpoint.getClientAwait.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<HttpClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<HttpClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.servicediscovery.types.HttpEndpoint.getClient(discovery, filter, conf, it);
            }
        }, continuation);
    }

    @Nullable
    public final Object getWebClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final JsonObject filter, @NotNull final JsonObject conf, @NotNull Continuation<? super WebClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<WebClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.HttpEndpoint.getWebClientAwait.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<WebClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<WebClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.servicediscovery.types.HttpEndpoint.getWebClient(discovery, filter, conf, it);
            }
        }, continuation);
    }

    @Nullable
    public final Object getClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final Function1<? super Record, Boolean> function1, @NotNull Continuation<? super HttpClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.HttpEndpoint.getClientAwait.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: HttpEndpoint.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u000128\u0010\u0002\u001a4\u0012\u0004\u0012\u00020\u0004 \b*\u0019\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lio/vertx/core/AsyncResult;", "Lio/vertx/core/http/HttpClient;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.types.HttpEndpoint$getClientAwait$6$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/HttpEndpoint$getClientAwait$6$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<HttpClient>, Unit> {
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
                public /* bridge */ /* synthetic */ Unit invoke(AsyncResult<HttpClient> asyncResult) {
                    invoke2(asyncResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncResult<HttpClient> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<HttpClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<HttpClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = discovery;
                Function1 function12 = function1;
                Object httpEndpoint$sam$java_util_function_Function$0 = function12;
                if (function12 != null) {
                    httpEndpoint$sam$java_util_function_Function$0 = new HttpEndpoint$sam$java_util_function_Function$0(function12);
                }
                io.vertx.servicediscovery.types.HttpEndpoint.getClient(serviceDiscovery, (Function) httpEndpoint$sam$java_util_function_Function$0, new HttpEndpoint$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }

    @Nullable
    public final Object getWebClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final Function1<? super Record, Boolean> function1, @NotNull Continuation<? super WebClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<WebClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.HttpEndpoint.getWebClientAwait.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: HttpEndpoint.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u000128\u0010\u0002\u001a4\u0012\u0004\u0012\u00020\u0004 \b*\u0019\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lio/vertx/core/AsyncResult;", "Lio/vertx/ext/web/client/WebClient;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.types.HttpEndpoint$getWebClientAwait$6$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/HttpEndpoint$getWebClientAwait$6$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<WebClient>, Unit> {
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
                public /* bridge */ /* synthetic */ Unit invoke(AsyncResult<WebClient> asyncResult) {
                    invoke2(asyncResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncResult<WebClient> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<WebClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<WebClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = discovery;
                Function1 function12 = function1;
                Object httpEndpoint$sam$java_util_function_Function$0 = function12;
                if (function12 != null) {
                    httpEndpoint$sam$java_util_function_Function$0 = new HttpEndpoint$sam$java_util_function_Function$0(function12);
                }
                io.vertx.servicediscovery.types.HttpEndpoint.getWebClient(serviceDiscovery, (Function) httpEndpoint$sam$java_util_function_Function$0, new HttpEndpoint$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }

    @Nullable
    public final Object getClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final Function1<? super Record, Boolean> function1, @NotNull final JsonObject conf, @NotNull Continuation<? super HttpClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.HttpEndpoint.getClientAwait.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: HttpEndpoint.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u000128\u0010\u0002\u001a4\u0012\u0004\u0012\u00020\u0004 \b*\u0019\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lio/vertx/core/AsyncResult;", "Lio/vertx/core/http/HttpClient;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.types.HttpEndpoint$getClientAwait$8$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/HttpEndpoint$getClientAwait$8$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<HttpClient>, Unit> {
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
                public /* bridge */ /* synthetic */ Unit invoke(AsyncResult<HttpClient> asyncResult) {
                    invoke2(asyncResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncResult<HttpClient> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<HttpClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<HttpClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = discovery;
                Function1 function12 = function1;
                Object httpEndpoint$sam$java_util_function_Function$0 = function12;
                if (function12 != null) {
                    httpEndpoint$sam$java_util_function_Function$0 = new HttpEndpoint$sam$java_util_function_Function$0(function12);
                }
                io.vertx.servicediscovery.types.HttpEndpoint.getClient(serviceDiscovery, (Function) httpEndpoint$sam$java_util_function_Function$0, conf, new HttpEndpoint$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }

    @Nullable
    public final Object getWebClientAwait(@NotNull final ServiceDiscovery discovery, @NotNull final Function1<? super Record, Boolean> function1, @NotNull final JsonObject conf, @NotNull Continuation<? super WebClient> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<WebClient>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.HttpEndpoint.getWebClientAwait.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: HttpEndpoint.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u000128\u0010\u0002\u001a4\u0012\u0004\u0012\u00020\u0004 \b*\u0019\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lio/vertx/core/AsyncResult;", "Lio/vertx/ext/web/client/WebClient;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.types.HttpEndpoint$getWebClientAwait$8$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/HttpEndpoint$getWebClientAwait$8$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<WebClient>, Unit> {
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
                public /* bridge */ /* synthetic */ Unit invoke(AsyncResult<WebClient> asyncResult) {
                    invoke2(asyncResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncResult<WebClient> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<WebClient>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<WebClient>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = discovery;
                Function1 function12 = function1;
                Object httpEndpoint$sam$java_util_function_Function$0 = function12;
                if (function12 != null) {
                    httpEndpoint$sam$java_util_function_Function$0 = new HttpEndpoint$sam$java_util_function_Function$0(function12);
                }
                io.vertx.servicediscovery.types.HttpEndpoint.getWebClient(serviceDiscovery, (Function) httpEndpoint$sam$java_util_function_Function$0, conf, new HttpEndpoint$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }
}
