package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AbstractChannel.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018��*\u0006\b��\u0010\u0001 ��2\u00020\u0002J\u0015\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028��H&¢\u0006\u0002\u0010\tJ'\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00028��2\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000eH&¢\u0006\u0002\u0010\u000fR\u0012\u0010\u0003\u001a\u00020\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/channels/ReceiveOrClosed;", "E", "", "offerResult", "getOfferResult", "()Ljava/lang/Object;", "completeResumeReceive", "", "value", "(Ljava/lang/Object;)V", "tryResumeReceive", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ReceiveOrClosed.class */
public interface ReceiveOrClosed<E> {
    @NotNull
    Object getOfferResult();

    @Nullable
    Symbol tryResumeReceive(E e, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp);

    void completeResumeReceive(E e);
}
