package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

/* compiled from: ConcurrentWeakMap.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\"\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0001\n��\n\u0002\u0010��\n��\u001a\b\u0010\b\u001a\u00020\tH\u0002\u001a\u000e\u0010\n\u001a\u00020\u0003*\u0004\u0018\u00010\u000bH\u0002\"\u000e\u0010��\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\f"}, d2 = {"MAGIC", "", "MARKED_NULL", "Lkotlinx/coroutines/debug/internal/Marked;", "MARKED_TRUE", "MIN_CAPACITY", "REHASH", "Lkotlinx/coroutines/internal/Symbol;", "noImpl", "", "mark", "", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/ConcurrentWeakMapKt.class */
public final class ConcurrentWeakMapKt {
    private static final int MAGIC = -1640531527;
    private static final int MIN_CAPACITY = 16;

    @NotNull
    private static final Symbol REHASH = new Symbol("REHASH");

    @NotNull
    private static final Marked MARKED_NULL = new Marked(null);

    @NotNull
    private static final Marked MARKED_TRUE = new Marked(true);

    /* JADX INFO: Access modifiers changed from: private */
    public static final Marked mark(Object $this$mark) {
        return $this$mark == null ? MARKED_NULL : Intrinsics.areEqual($this$mark, (Object) true) ? MARKED_TRUE : new Marked($this$mark);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void noImpl() {
        throw new UnsupportedOperationException("not implemented");
    }
}
