package io.vertx.kotlin.servicediscovery.types;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
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
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MessageSource.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J9\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b��\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\fJ-\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b��\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/vertx/kotlin/servicediscovery/types/MessageSource;", "", "()V", "getConsumerAwait", "Lio/vertx/core/eventbus/MessageConsumer;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "discovery", "Lio/vertx/servicediscovery/ServiceDiscovery;", "filter", "Lkotlin/Function1;", "Lio/vertx/servicediscovery/Record;", "", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/vertx/core/json/JsonObject;", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/MessageSource.class */
public final class MessageSource {
    public static final MessageSource INSTANCE = new MessageSource();

    private MessageSource() {
    }

    @Nullable
    public final <T> Object getConsumerAwait(@NotNull final ServiceDiscovery discovery, @NotNull final JsonObject filter, @NotNull Continuation<? super MessageConsumer<T>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MessageConsumer<T>>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.MessageSource.getConsumerAwait.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull Handler<AsyncResult<MessageConsumer<T>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.servicediscovery.types.MessageSource.getConsumer(discovery, filter, it);
            }
        }, continuation);
    }

    @Nullable
    public final <T> Object getConsumerAwait(@NotNull final ServiceDiscovery discovery, @NotNull final Function1<? super Record, Boolean> function1, @NotNull Continuation<? super MessageConsumer<T>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MessageConsumer<T>>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.types.MessageSource.getConsumerAwait.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: MessageSource.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u001a\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u00022D\u0010\u0003\u001a@\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005 \t*\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0018\u00010\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b0\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "p1", "Lio/vertx/core/AsyncResult;", "Lio/vertx/core/eventbus/MessageConsumer;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.types.MessageSource$getConsumerAwait$4$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/MessageSource$getConsumerAwait$4$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<MessageConsumer<T>>, Unit> {
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
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke((AsyncResult) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(AsyncResult<MessageConsumer<T>> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull Handler<AsyncResult<MessageConsumer<T>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = discovery;
                final Function1 function12 = function1;
                Object obj = function12;
                if (function12 != null) {
                    obj = new Function() { // from class: io.vertx.kotlin.servicediscovery.types.MessageSource$sam$java_util_function_Function$0
                        @Override // java.util.function.Function
                        public final /* synthetic */ Object apply(Object p0) {
                            return function12.invoke(p0);
                        }
                    };
                }
                final AnonymousClass1 anonymousClass1 = new AnonymousClass1(it);
                io.vertx.servicediscovery.types.MessageSource.getConsumer(serviceDiscovery, (Function) obj, new Handler() { // from class: io.vertx.kotlin.servicediscovery.types.MessageSource$sam$io_vertx_core_Handler$0
                    @Override // io.vertx.core.Handler
                    public final /* synthetic */ void handle(Object p0) {
                        Intrinsics.checkExpressionValueIsNotNull(anonymousClass1.invoke(p0), "invoke(...)");
                    }
                });
            }
        }, continuation);
    }
}
