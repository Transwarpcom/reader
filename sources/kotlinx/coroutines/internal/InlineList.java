package kotlinx.coroutines.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: InlineList.kt */
@JvmInline
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0081@\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B\u0016\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002ø\u0001��¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\t\u0010\nJ$\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00020\f0\u000eH\u0086\b¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\u0016\u001a\u00028��H\u0086\u0002ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u001aHÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0002X\u0082\u0004¢\u0006\u0002\n��\u0088\u0001\u0003\u0092\u0001\u0004\u0018\u00010\u0002ø\u0001��\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Lkotlinx/coroutines/internal/InlineList;", "E", "", "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "equals", "", "other", "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "forEachReversed", "", "action", "Lkotlin/Function1;", "forEachReversed-impl", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", IdentityNamingStrategy.HASH_CODE_KEY, "", "hashCode-impl", "(Ljava/lang/Object;)I", "plus", "element", "plus-FjFbRPM", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/InlineList.class */
public final class InlineList<E> {

    @Nullable
    private final Object holder;

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m4309toStringimpl(Object arg0) {
        return "InlineList(holder=" + arg0 + ')';
    }

    public String toString() {
        return m4309toStringimpl(this.holder);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m4310hashCodeimpl(Object arg0) {
        if (arg0 == null) {
            return 0;
        }
        return arg0.hashCode();
    }

    public int hashCode() {
        return m4310hashCodeimpl(this.holder);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m4311equalsimpl(Object arg0, Object other) {
        return (other instanceof InlineList) && Intrinsics.areEqual(arg0, ((InlineList) other).m4315unboximpl());
    }

    public boolean equals(Object other) {
        return m4311equalsimpl(this.holder, other);
    }

    @NotNull
    /* renamed from: constructor-impl, reason: not valid java name */
    public static <E> Object m4312constructorimpl(@Nullable Object holder) {
        return holder;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ InlineList m4314boximpl(Object v) {
        return new InlineList(v);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m4315unboximpl() {
        return this.holder;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4316equalsimpl0(Object p1, Object p2) {
        return Intrinsics.areEqual(p1, p2);
    }

    private /* synthetic */ InlineList(Object holder) {
        this.holder = holder;
    }

    /* renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ Object m4313constructorimpl$default(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            obj = null;
        }
        return m4312constructorimpl(obj);
    }

    @NotNull
    /* renamed from: plus-FjFbRPM, reason: not valid java name */
    public static final Object m4307plusFjFbRPM(Object arg0, E e) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(!(e instanceof List))) {
                throw new AssertionError();
            }
        }
        if (arg0 == null) {
            return m4312constructorimpl(e);
        }
        if (arg0 instanceof ArrayList) {
            if (arg0 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
            }
            ((ArrayList) arg0).add(e);
            return m4312constructorimpl(arg0);
        }
        ArrayList list = new ArrayList(4);
        list.add(arg0);
        list.add(e);
        return m4312constructorimpl(list);
    }

    /* renamed from: forEachReversed-impl, reason: not valid java name */
    public static final void m4308forEachReversedimpl(Object obj, @NotNull Function1<? super E, Unit> function1) {
        if (obj == null) {
            return;
        }
        if (obj instanceof ArrayList) {
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
            }
            ArrayList arrayList = (ArrayList) obj;
            int size = arrayList.size() - 1;
            if (0 <= size) {
                do {
                    int i = size;
                    size--;
                    function1.invoke((Object) arrayList.get(i));
                } while (0 <= size);
                return;
            }
            return;
        }
        function1.invoke(obj);
    }
}
