package kotlinx.coroutines.channels;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: LinkedListChannel.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012 \u0010\u0003\u001a\u001c\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00028��\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028��H\u0014¢\u0006\u0002\u0010\u0011J!\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028��2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0014¢\u0006\u0002\u0010\u0015J/\u0010\u0016\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0014ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\b\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0014\u0010\u000b\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\n\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/channels/LinkedListChannel;", "E", "Lkotlinx/coroutines/channels/AbstractChannel;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlin/jvm/functions/Function1;)V", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "offerInternal", "", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "onCancelIdempotentList", BeanDefinitionParserDelegate.LIST_ELEMENT, "Lkotlinx/coroutines/internal/InlineList;", "Lkotlinx/coroutines/channels/Send;", "closed", "Lkotlinx/coroutines/channels/Closed;", "onCancelIdempotentList-w-w6eGU", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)V", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/LinkedListChannel.class */
public class LinkedListChannel<E> extends AbstractChannel<E> {
    public LinkedListChannel(@Nullable Function1<? super E, Unit> function1) {
        super(function1);
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferAlwaysEmpty() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferEmpty() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferAlwaysFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    protected Object offerInternal(E e) {
        ReceiveOrClosed sendResult;
        do {
            Object result = super.offerInternal(e);
            if (result == AbstractChannelKt.OFFER_SUCCESS) {
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            if (result == AbstractChannelKt.OFFER_FAILED) {
                sendResult = sendBuffered(e);
                if (sendResult == null) {
                    return AbstractChannelKt.OFFER_SUCCESS;
                }
            } else {
                if (result instanceof Closed) {
                    return result;
                }
                throw new IllegalStateException(Intrinsics.stringPlus("Invalid offerInternal result ", result).toString());
            }
        } while (!(sendResult instanceof Closed));
        return sendResult;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    protected Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        Object objOfferSelectInternal;
        while (true) {
            if (getHasReceiveOrClosed()) {
                objOfferSelectInternal = super.offerSelectInternal(e, selectInstance);
            } else {
                Object objPerformAtomicTrySelect = selectInstance.performAtomicTrySelect(describeSendBuffered(e));
                objOfferSelectInternal = objPerformAtomicTrySelect == null ? AbstractChannelKt.OFFER_SUCCESS : objPerformAtomicTrySelect;
            }
            Object result = objOfferSelectInternal;
            if (result == SelectKt.getALREADY_SELECTED()) {
                return SelectKt.getALREADY_SELECTED();
            }
            if (result == AbstractChannelKt.OFFER_SUCCESS) {
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            if (result != AbstractChannelKt.OFFER_FAILED && result != AtomicKt.RETRY_ATOMIC) {
                if (result instanceof Closed) {
                    return result;
                }
                throw new IllegalStateException(Intrinsics.stringPlus("Invalid result ", result).toString());
            }
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    /* renamed from: onCancelIdempotentList-w-w6eGU */
    protected void mo4211onCancelIdempotentListww6eGU(@NotNull Object list, @NotNull Closed<?> closed) {
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException = null;
        if (list != null) {
            if (!(list instanceof ArrayList)) {
                Send it = (Send) list;
                if (it instanceof AbstractSendChannel.SendBuffered) {
                    Function1<E, Unit> function1 = this.onUndeliveredElement;
                    undeliveredElementExceptionCallUndeliveredElementCatchingException = function1 == null ? null : OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, ((AbstractSendChannel.SendBuffered) it).element, (UndeliveredElementException) null);
                } else {
                    it.resumeSendClosed(closed);
                }
            } else {
                if (list == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
                }
                ArrayList list$iv = (ArrayList) list;
                int size = list$iv.size() - 1;
                if (0 <= size) {
                    do {
                        int i$iv = size;
                        size--;
                        Send it2 = (Send) list$iv.get(i$iv);
                        if (it2 instanceof AbstractSendChannel.SendBuffered) {
                            Function1<E, Unit> function12 = this.onUndeliveredElement;
                            undeliveredElementExceptionCallUndeliveredElementCatchingException = function12 == null ? null : OnUndeliveredElementKt.callUndeliveredElementCatchingException(function12, ((AbstractSendChannel.SendBuffered) it2).element, undeliveredElementExceptionCallUndeliveredElementCatchingException);
                        } else {
                            it2.resumeSendClosed(closed);
                        }
                    } while (0 <= size);
                }
            }
        }
        UndeliveredElementException it3 = undeliveredElementExceptionCallUndeliveredElementCatchingException;
        if (it3 != null) {
            throw it3;
        }
    }
}
