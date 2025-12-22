package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MemberScope.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScopeKt.class */
public final class MemberScopeKt {
    @Nullable
    public static final Set<Name> flatMapClassifierNamesOrNull(@NotNull Iterable<? extends MemberScope> iterable) {
        Collection collection;
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Collection destination$iv = new HashSet();
        Iterator<? extends MemberScope> it = iterable.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                MemberScope p0 = (MemberScope) element$iv;
                Iterable list$iv = p0.getClassifierNames();
                if (list$iv == null) {
                    collection = null;
                    break;
                }
                CollectionsKt.addAll(destination$iv, list$iv);
            } else {
                collection = destination$iv;
                break;
            }
        }
        return (Set) collection;
    }
}
