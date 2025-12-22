package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Atomic.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0001H&J\u0016\u0010\r\u001a\u0004\u0018\u00010\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0004H&R\u001e\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0086.¢\u0006\u000e\n��\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/internal/AtomicDesc;", "", "()V", "atomicOp", "Lkotlinx/coroutines/internal/AtomicOp;", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "setAtomicOp", "(Lkotlinx/coroutines/internal/AtomicOp;)V", "complete", "", "op", "failure", "prepare", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/AtomicDesc.class */
public abstract class AtomicDesc {
    public AtomicOp<?> atomicOp;

    @Nullable
    public abstract Object prepare(@NotNull AtomicOp<?> atomicOp);

    public abstract void complete(@NotNull AtomicOp<?> atomicOp, @Nullable Object obj);

    @NotNull
    public final AtomicOp<?> getAtomicOp() {
        AtomicOp<?> atomicOp = this.atomicOp;
        if (atomicOp != null) {
            return atomicOp;
        }
        Intrinsics.throwUninitializedPropertyAccessException("atomicOp");
        return null;
    }

    public final void setAtomicOp(@NotNull AtomicOp<?> atomicOp) {
        this.atomicOp = atomicOp;
    }
}
