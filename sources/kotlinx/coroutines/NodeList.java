package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: JobSupport.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b��\u0018��2\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\b\u0010\r\u001a\u00020\u000bH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006R\u0014\u0010\u0007\u001a\u00020��8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/NodeList;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/Incomplete;", "()V", "isActive", "", "()Z", BeanDefinitionParserDelegate.LIST_ELEMENT, "getList", "()Lkotlinx/coroutines/NodeList;", "getString", "", "state", "toString", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/NodeList.class */
public final class NodeList extends LockFreeLinkedListHead implements Incomplete {
    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.Incomplete
    @NotNull
    public NodeList getList() {
        return this;
    }

    @NotNull
    public final String getString(@NotNull String state) {
        StringBuilder $this$getString_u24lambda_u2d1 = new StringBuilder();
        $this$getString_u24lambda_u2d1.append("List{");
        $this$getString_u24lambda_u2d1.append(state);
        $this$getString_u24lambda_u2d1.append("}[");
        boolean first = true;
        NodeList this_$iv = this;
        LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) this_$iv.getNext();
        while (true) {
            LockFreeLinkedListNode cur$iv = nextNode;
            if (Intrinsics.areEqual(cur$iv, this_$iv)) {
                $this$getString_u24lambda_u2d1.append("]");
                String string = $this$getString_u24lambda_u2d1.toString();
                Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
                return string;
            }
            if (cur$iv instanceof JobNode) {
                JobNode node = (JobNode) cur$iv;
                if (first) {
                    first = false;
                } else {
                    $this$getString_u24lambda_u2d1.append(", ");
                }
                $this$getString_u24lambda_u2d1.append(node);
            }
            nextNode = cur$iv.getNextNode();
        }
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return DebugKt.getDEBUG() ? getString("Active") : super.toString();
    }
}
