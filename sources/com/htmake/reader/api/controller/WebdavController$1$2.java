package com.htmake.reader.api.controller;

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

/* compiled from: WebdavController.kt */
@Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "WebdavController.kt", l = {119}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.WebdavController$1$2")
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/WebdavController$1$2.class */
final class WebdavController$1$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ WebdavController this$0;
    final /* synthetic */ RoutingContext $it;
    final /* synthetic */ Function2<RoutingContext, Exception, Unit> $onHandlerError;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    WebdavController$1$2(WebdavController this$0, RoutingContext $it, Function2<? super RoutingContext, ? super Exception, Unit> $onHandlerError, Continuation<? super WebdavController$1$2> $completion) {
        super(2, $completion);
        this.this$0 = this$0;
        this.$it = $it;
        this.$onHandlerError = $onHandlerError;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
        return new WebdavController$1$2(this.this$0, this.$it, this.$onHandlerError, $completion);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
        return ((WebdavController$1$2) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    WebdavController webdavController = this.this$0;
                    RoutingContext it = this.$it;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    this.label = 1;
                    if (webdavController.webdavList(it, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Exception e) {
            Function2<RoutingContext, Exception, Unit> function2 = this.$onHandlerError;
            RoutingContext it2 = this.$it;
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            function2.invoke(it2, e);
        }
        return Unit.INSTANCE;
    }
}
