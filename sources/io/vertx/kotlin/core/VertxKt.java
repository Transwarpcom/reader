package io.vertx.kotlin.core;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Vertx.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��@\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a%\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a+\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a\u001d\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010\u001a%\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a7\u0010\u0012\u001a\u0004\u0018\u0001H\u0013\"\u0004\b��\u0010\u0013*\u00020\u00022\u0018\u0010\u0014\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130\u0016\u0012\u0004\u0012\u00020\u00010\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0017\u001a?\u0010\u0012\u001a\u0004\u0018\u0001H\u0013\"\u0004\b��\u0010\u0013*\u00020\u00022\u0018\u0010\u0014\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130\u0016\u0012\u0004\u0012\u00020\u00010\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001a\u001a\u001d\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"closeAwait", "", "Lio/vertx/core/Vertx;", "(Lio/vertx/core/Vertx;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deployVerticleAwait", "", "verticle", "Lio/vertx/core/Verticle;", "(Lio/vertx/core/Vertx;Lio/vertx/core/Verticle;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "options", "Lio/vertx/core/DeploymentOptions;", "(Lio/vertx/core/Vertx;Lio/vertx/core/Verticle;Lio/vertx/core/DeploymentOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verticleSupplier", "Ljava/util/function/Supplier;", "(Lio/vertx/core/Vertx;Ljava/util/function/Supplier;Lio/vertx/core/DeploymentOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "name", "(Lio/vertx/core/Vertx;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/core/Vertx;Ljava/lang/String;Lio/vertx/core/DeploymentOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeBlockingAwait", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "blockingCodeHandler", "Lkotlin/Function1;", "Lio/vertx/core/Promise;", "(Lio/vertx/core/Vertx;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ordered", "", "(Lio/vertx/core/Vertx;Lkotlin/jvm/functions/Function1;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "undeployAwait", "deploymentID", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/VertxKt.class */
public final class VertxKt {
    @Nullable
    public static final Object closeAwait(@NotNull final io.vertx.core.Vertx $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.VertxKt.closeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.VertxKt.closeAwait.2.1
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
    public static final Object deployVerticleAwait(@NotNull final io.vertx.core.Vertx $this$deployVerticleAwait, @NotNull final String name, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.VertxKt.deployVerticleAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deployVerticleAwait.deployVerticle(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object deployVerticleAwait(@NotNull final io.vertx.core.Vertx $this$deployVerticleAwait, @NotNull final String name, @NotNull final DeploymentOptions options, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.VertxKt.deployVerticleAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deployVerticleAwait.deployVerticle(name, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object undeployAwait(@NotNull final io.vertx.core.Vertx $this$undeployAwait, @NotNull final String deploymentID, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.VertxKt.undeployAwait.2
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
                $this$undeployAwait.undeploy(deploymentID, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.VertxKt.undeployAwait.2.1
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
    public static final <T> Object executeBlockingAwait(@NotNull final io.vertx.core.Vertx $this$executeBlockingAwait, @NotNull final Function1<? super Promise<T>, Unit> function1, final boolean ordered, @NotNull Continuation<? super T> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<T>>, Unit>() { // from class: io.vertx.kotlin.core.VertxKt.executeBlockingAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: Vertx.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0016\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u00022<\u0010\u0003\u001a8\u0012\u0006\u0012\u0004\u0018\u0001H\u0002 \b*\u001b\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "p1", "Lio/vertx/core/AsyncResult;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.core.VertxKt$executeBlockingAwait$2$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/VertxKt$executeBlockingAwait$2$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<T>, Unit> {
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke((AsyncResult) obj);
                    return Unit.INSTANCE;
                }

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

                public final void invoke(AsyncResult<T> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(@NotNull Handler<AsyncResult<T>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.core.Vertx vertx = $this$executeBlockingAwait;
                Function1 function12 = function1;
                VertxKt$sam$io_vertx_core_Handler$0 vertxKt$sam$io_vertx_core_Handler$0 = function12;
                if (function12 != 0) {
                    vertxKt$sam$io_vertx_core_Handler$0 = new VertxKt$sam$io_vertx_core_Handler$0(function12);
                }
                vertx.executeBlocking(vertxKt$sam$io_vertx_core_Handler$0, ordered, new VertxKt$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }

    @Nullable
    public static final <T> Object executeBlockingAwait(@NotNull final io.vertx.core.Vertx $this$executeBlockingAwait, @NotNull final Function1<? super Promise<T>, Unit> function1, @NotNull Continuation<? super T> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<T>>, Unit>() { // from class: io.vertx.kotlin.core.VertxKt.executeBlockingAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: Vertx.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0016\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u00022<\u0010\u0003\u001a8\u0012\u0006\u0012\u0004\u0018\u0001H\u0002 \b*\u001b\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "p1", "Lio/vertx/core/AsyncResult;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.core.VertxKt$executeBlockingAwait$4$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/VertxKt$executeBlockingAwait$4$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<T>, Unit> {
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke((AsyncResult) obj);
                    return Unit.INSTANCE;
                }

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

                public final void invoke(AsyncResult<T> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(@NotNull Handler<AsyncResult<T>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.core.Vertx vertx = $this$executeBlockingAwait;
                Function1 function12 = function1;
                VertxKt$sam$io_vertx_core_Handler$0 vertxKt$sam$io_vertx_core_Handler$0 = function12;
                if (function12 != 0) {
                    vertxKt$sam$io_vertx_core_Handler$0 = new VertxKt$sam$io_vertx_core_Handler$0(function12);
                }
                vertx.executeBlocking(vertxKt$sam$io_vertx_core_Handler$0, new VertxKt$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }

    @Nullable
    public static final Object deployVerticleAwait(@NotNull final io.vertx.core.Vertx $this$deployVerticleAwait, @NotNull final Verticle verticle, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.VertxKt.deployVerticleAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deployVerticleAwait.deployVerticle(verticle, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object deployVerticleAwait(@NotNull final io.vertx.core.Vertx $this$deployVerticleAwait, @NotNull final Verticle verticle, @NotNull final DeploymentOptions options, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.VertxKt.deployVerticleAwait.8
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deployVerticleAwait.deployVerticle(verticle, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object deployVerticleAwait(@NotNull final io.vertx.core.Vertx $this$deployVerticleAwait, @NotNull final Supplier<Verticle> supplier, @NotNull final DeploymentOptions options, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.VertxKt.deployVerticleAwait.10
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deployVerticleAwait.deployVerticle(supplier, options, it);
            }
        }, continuation);
    }
}
