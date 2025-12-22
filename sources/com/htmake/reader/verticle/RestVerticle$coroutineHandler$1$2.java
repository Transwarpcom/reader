package com.htmake.reader.verticle;

import com.htmake.reader.utils.VertExtKt;
import io.vertx.ext.web.RoutingContext;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RestVerticle.kt */
@Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "RestVerticle.kt", l = {163}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.verticle.RestVerticle$coroutineHandler$1$2")
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/verticle/RestVerticle$coroutineHandler$1$2.class */
final class RestVerticle$coroutineHandler$1$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ RoutingContext $ctx;
    final /* synthetic */ Function2<RoutingContext, Continuation<Object>, Object> $fn;
    final /* synthetic */ RestVerticle this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    RestVerticle$coroutineHandler$1$2(RoutingContext $ctx, Function2<? super RoutingContext, ? super Continuation<Object>, ? extends Object> $fn, RestVerticle this$0, Continuation<? super RestVerticle$coroutineHandler$1$2> $completion) {
        super(2, $completion);
        this.$ctx = $ctx;
        this.$fn = $fn;
        this.this$0 = this$0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
        return new RestVerticle$coroutineHandler$1$2(this.$ctx, this.$fn, this.this$0, $completion);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
        return ((RestVerticle$coroutineHandler$1$2) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
        RoutingContext routingContext;
        Object objInvoke;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoutingContext ctx = this.$ctx;
                    Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
                    routingContext = ctx;
                    Function2<RoutingContext, Continuation<Object>, Object> function2 = this.$fn;
                    RoutingContext ctx2 = this.$ctx;
                    Intrinsics.checkNotNullExpressionValue(ctx2, "ctx");
                    this.L$0 = routingContext;
                    this.label = 1;
                    objInvoke = function2.invoke(ctx2, this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    routingContext = (RoutingContext) this.L$0;
                    ResultKt.throwOnFailure($result);
                    objInvoke = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            VertExtKt.success(routingContext, objInvoke);
        } catch (Exception e) {
            RestVerticle restVerticle = this.this$0;
            RoutingContext ctx3 = this.$ctx;
            Intrinsics.checkNotNullExpressionValue(ctx3, "ctx");
            restVerticle.onHandlerError(ctx3, e);
        }
        return Unit.INSTANCE;
    }
}
