package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractChannel.kt */
@Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
@DebugMetadata(f = "AbstractChannel.kt", l = {633}, i = {}, s = {}, n = {}, m = "receiveCatching-JP2dKIU", c = "kotlinx.coroutines.channels.AbstractChannel")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/AbstractChannel$receiveCatching$1.class */
final class AbstractChannel$receiveCatching$1 extends ContinuationImpl {
    /* synthetic */ Object result;
    final /* synthetic */ AbstractChannel<E> this$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AbstractChannel$receiveCatching$1(AbstractChannel<E> abstractChannel, Continuation<? super AbstractChannel$receiveCatching$1> continuation) {
        super(continuation);
        this.this$0 = abstractChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        Object objMo4209receiveCatchingJP2dKIU = this.this$0.mo4209receiveCatchingJP2dKIU(this);
        return objMo4209receiveCatchingJP2dKIU == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo4209receiveCatchingJP2dKIU : ChannelResult.m4233boximpl(objMo4209receiveCatchingJP2dKIU);
    }
}
