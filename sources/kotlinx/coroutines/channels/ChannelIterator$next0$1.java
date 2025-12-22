package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.ChannelIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Channel.kt */
@Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
@DebugMetadata(f = "Channel.kt", l = {585}, i = {0}, s = {"L$0"}, n = {"this"}, m = "next", c = "kotlinx.coroutines.channels.ChannelIterator$DefaultImpls")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ChannelIterator$next0$1.class */
final class ChannelIterator$next0$1<E> extends ContinuationImpl {
    Object L$0;
    /* synthetic */ Object result;
    int label;

    ChannelIterator$next0$1(Continuation<? super ChannelIterator$next0$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) {
        this.result = $result;
        this.label |= Integer.MIN_VALUE;
        return ChannelIterator.DefaultImpls.next(null, this);
    }
}
