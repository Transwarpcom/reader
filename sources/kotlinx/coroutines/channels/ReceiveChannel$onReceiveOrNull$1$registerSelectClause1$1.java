package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [R, E] */
/* compiled from: Channel.kt */
@Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\b\n\u0002\b\u0004\n\u0002\u0018\u0002\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001\"\u0006\b\u0001\u0010\u0002 \u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "R", "E", "it", "Lkotlinx/coroutines/channels/ChannelResult;"})
@DebugMetadata(f = "Channel.kt", l = {376}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "kotlinx.coroutines.channels.ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1.class */
final class ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1<E, R> extends SuspendLambda implements Function2<ChannelResult<? extends E>, Continuation<? super R>, Object> {
    int label;
    /* synthetic */ Object L$0;
    final /* synthetic */ Function2<E, Continuation<? super R>, Object> $block;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1(Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1> continuation) {
        super(2, continuation);
        this.$block = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> continuation) {
        ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1 receiveChannel$onReceiveOrNull$1$registerSelectClause1$1 = new ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1(this.$block, continuation);
        receiveChannel$onReceiveOrNull$1$registerSelectClause1$1.L$0 = value;
        return receiveChannel$onReceiveOrNull$1$registerSelectClause1$1;
    }

    @Nullable
    /* renamed from: invoke-WpGqRn0, reason: not valid java name */
    public final Object m4243invokeWpGqRn0(@NotNull Object p1, @Nullable Continuation<? super R> continuation) {
        return ((ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1) create(ChannelResult.m4233boximpl(p1), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) {
        return m4243invokeWpGqRn0(((ChannelResult) p1).m4234unboximpl(), (Continuation) p2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Object objM4234unboximpl = ((ChannelResult) this.L$0).m4234unboximpl();
                Throwable thM4228exceptionOrNullimpl = ChannelResult.m4228exceptionOrNullimpl(objM4234unboximpl);
                if (thM4228exceptionOrNullimpl != null) {
                    throw thM4228exceptionOrNullimpl;
                }
                Function2<E, Continuation<? super R>, Object> function2 = this.$block;
                Object objM4226getOrNullimpl = ChannelResult.m4226getOrNullimpl(objM4234unboximpl);
                this.label = 1;
                Object objInvoke = function2.invoke(objM4226getOrNullimpl, this);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objInvoke;
            case 1:
                ResultKt.throwOnFailure(obj);
                return obj;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
