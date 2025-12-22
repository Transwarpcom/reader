package kotlin.reflect.jvm.internal.impl.util.collectionUtils;

import java.util.Collection;
import java.util.LinkedHashSet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: scopeUtils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/collectionUtils/ScopeUtilsKt.class */
public final class ScopeUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static final <T> Collection<T> concat(@Nullable Collection<? extends T> collection, @NotNull Collection<? extends T> collection2) {
        Intrinsics.checkNotNullParameter(collection2, "collection");
        if (collection2.isEmpty()) {
            return collection;
        }
        if (collection == 0) {
            return collection2;
        }
        if (collection instanceof LinkedHashSet) {
            ((LinkedHashSet) collection).addAll(collection2);
            return collection;
        }
        LinkedHashSet result = new LinkedHashSet(collection);
        result.addAll(collection2);
        return result;
    }

    @NotNull
    public static final SmartList<MemberScope> listOfNonEmptyScopes(@NotNull Iterable<? extends MemberScope> scopes) {
        Intrinsics.checkNotNullParameter(scopes, "scopes");
        Collection destination$iv = new SmartList();
        for (MemberScope memberScope : scopes) {
            MemberScope it = memberScope;
            if ((it == null || it == MemberScope.Empty.INSTANCE) ? false : true) {
                destination$iv.add(memberScope);
            }
        }
        return (SmartList) destination$iv;
    }
}
