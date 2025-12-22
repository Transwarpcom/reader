package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.Nullable;

/* compiled from: LockFreeLinkedList.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0007\u001a\u00020\b\"\u000e\b��\u0010\t\u0018\u0001*\u00060\u0001j\u0002`\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\b0\fH\u0086\bJ\u0010\u0010\r\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\nH\u0014J\u0006\u0010\u000e\u001a\u00020\u0004J\r\u0010\u000f\u001a\u00020\bH��¢\u0006\u0002\b\u0010R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "()V", "isEmpty", "", "()Z", "isRemoved", "forEach", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/internal/Node;", "block", "Lkotlin/Function1;", "nextIfRemoved", "remove", "validate", "validate$kotlinx_coroutines_core", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/LockFreeLinkedListHead.class */
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    public final boolean isEmpty() {
        return getNext() == this;
    }

    public final /* synthetic */ <T extends LockFreeLinkedListNode> void forEach(Function1<? super T, Unit> function1) {
        LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) getNext();
        while (true) {
            LockFreeLinkedListNode cur = nextNode;
            if (!Intrinsics.areEqual(cur, this)) {
                Intrinsics.reifiedOperationMarker(3, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
                if (cur instanceof LockFreeLinkedListNode) {
                    function1.invoke(cur);
                }
                nextNode = cur.getNextNode();
            } else {
                return;
            }
        }
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public final boolean remove() {
        throw new IllegalStateException("head cannot be removed".toString());
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public boolean isRemoved() {
        return false;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @Nullable
    protected LockFreeLinkedListNode nextIfRemoved() {
        return null;
    }

    public final void validate$kotlinx_coroutines_core() {
        LockFreeLinkedListNode prev = this;
        LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) getNext();
        while (true) {
            LockFreeLinkedListNode cur = lockFreeLinkedListNode;
            if (!Intrinsics.areEqual(cur, this)) {
                LockFreeLinkedListNode next = cur.getNextNode();
                cur.validateNode$kotlinx_coroutines_core(prev, next);
                prev = cur;
                lockFreeLinkedListNode = next;
            } else {
                validateNode$kotlinx_coroutines_core(prev, (LockFreeLinkedListNode) getNext());
                return;
            }
        }
    }
}
