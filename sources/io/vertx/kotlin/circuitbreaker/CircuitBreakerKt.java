package io.vertx.kotlin.circuitbreaker;

import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.function.Function;
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

/* compiled from: CircuitBreaker.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a5\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001*\u00020\u00022\u0018\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001aI\u0010\b\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001*\u00020\u00022\u0018\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\u00010\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"executeAwait", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lio/vertx/circuitbreaker/CircuitBreaker;", "command", "Lkotlin/Function1;", "Lio/vertx/core/Promise;", "", "(Lio/vertx/circuitbreaker/CircuitBreaker;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeWithFallbackAwait", "fallback", "", "(Lio/vertx/circuitbreaker/CircuitBreaker;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/circuitbreaker/CircuitBreakerKt.class */
public final class CircuitBreakerKt {
    @Nullable
    public static final <T> Object executeWithFallbackAwait(@NotNull final CircuitBreaker $this$executeWithFallbackAwait, @NotNull final Function1<? super Promise<T>, Unit> function1, @NotNull final Function1<? super Throwable, ? extends T> function12, @NotNull Continuation<? super T> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<T>>, Unit>() { // from class: io.vertx.kotlin.circuitbreaker.CircuitBreakerKt.executeWithFallbackAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: CircuitBreaker.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0016\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u000228\u0010\u0003\u001a4\u0012\u0004\u0012\u0002H\u0002 \b*\u0019\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "p1", "Lio/vertx/core/AsyncResult;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.circuitbreaker.CircuitBreakerKt$executeWithFallbackAwait$2$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/circuitbreaker/CircuitBreakerKt$executeWithFallbackAwait$2$1.class */
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

            public final void invoke(@NotNull Handler<AsyncResult<T>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                CircuitBreaker circuitBreaker = $this$executeWithFallbackAwait;
                Function1 function13 = function1;
                Object circuitBreakerKt$sam$io_vertx_core_Handler$0 = function13;
                if (function13 != null) {
                    circuitBreakerKt$sam$io_vertx_core_Handler$0 = new CircuitBreakerKt$sam$io_vertx_core_Handler$0(function13);
                }
                Handler handler = (Handler) circuitBreakerKt$sam$io_vertx_core_Handler$0;
                final Function1 function14 = function12;
                Object obj = function14;
                if (function14 != null) {
                    obj = new Function() { // from class: io.vertx.kotlin.circuitbreaker.CircuitBreakerKt$sam$java_util_function_Function$0
                        @Override // java.util.function.Function
                        public final /* synthetic */ Object apply(Object p0) {
                            return function14.invoke(p0);
                        }
                    };
                }
                circuitBreaker.executeWithFallback(handler, (Function) obj, new CircuitBreakerKt$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }

    @Nullable
    public static final <T> Object executeAwait(@NotNull final CircuitBreaker $this$executeAwait, @NotNull final Function1<? super Promise<T>, Unit> function1, @NotNull Continuation<? super T> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<T>>, Unit>() { // from class: io.vertx.kotlin.circuitbreaker.CircuitBreakerKt.executeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: CircuitBreaker.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0016\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u000228\u0010\u0003\u001a4\u0012\u0004\u0012\u0002H\u0002 \b*\u0019\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "p1", "Lio/vertx/core/AsyncResult;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.circuitbreaker.CircuitBreakerKt$executeAwait$2$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/circuitbreaker/CircuitBreakerKt$executeAwait$2$1.class */
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

            public final void invoke(@NotNull Handler<AsyncResult<T>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                CircuitBreaker circuitBreaker = $this$executeAwait;
                Function1 function12 = function1;
                Object circuitBreakerKt$sam$io_vertx_core_Handler$0 = function12;
                if (function12 != null) {
                    circuitBreakerKt$sam$io_vertx_core_Handler$0 = new CircuitBreakerKt$sam$io_vertx_core_Handler$0(function12);
                }
                circuitBreaker.execute((Handler) circuitBreakerKt$sam$io_vertx_core_Handler$0, new CircuitBreakerKt$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }
}
