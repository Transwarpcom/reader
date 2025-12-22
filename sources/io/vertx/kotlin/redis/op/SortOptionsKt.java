package io.vertx.kotlin.redis.op;

import io.vertx.redis.op.SortOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SortOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0004\u001aO\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\n\u001aM\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"SortOptions", "Lio/vertx/redis/op/SortOptions;", "alpha", "", "by", "", "descending", "gets", "", "store", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/String;)Lio/vertx/redis/op/SortOptions;", "sortOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/SortOptionsKt.class */
public final class SortOptionsKt {
    @NotNull
    public static /* synthetic */ SortOptions sortOptionsOf$default(Boolean bool, String str, Boolean bool2, Iterable iterable, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        return sortOptionsOf(bool, str, bool2, iterable, str2);
    }

    @NotNull
    public static final SortOptions sortOptionsOf(@Nullable Boolean alpha, @Nullable String by, @Nullable Boolean descending, @Nullable Iterable<String> iterable, @Nullable String store) {
        SortOptions $this$apply = new SortOptions();
        if (alpha != null) {
            $this$apply.setAlpha(alpha);
        }
        if (by != null) {
            $this$apply.setBy(by);
        }
        if (descending != null) {
            $this$apply.setDescending(descending);
        }
        if (iterable != null) {
            for (String item : iterable) {
                $this$apply.addGet(item);
            }
        }
        if (store != null) {
            $this$apply.setStore(store);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "sortOptionsOf(alpha, by, descending, gets, store)"))
    @NotNull
    public static /* synthetic */ SortOptions SortOptions$default(Boolean bool, String str, Boolean bool2, Iterable iterable, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        return SortOptions(bool, str, bool2, iterable, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "sortOptionsOf(alpha, by, descending, gets, store)"))
    @NotNull
    public static final SortOptions SortOptions(@Nullable Boolean alpha, @Nullable String by, @Nullable Boolean descending, @Nullable Iterable<String> iterable, @Nullable String store) {
        SortOptions $this$apply = new SortOptions();
        if (alpha != null) {
            $this$apply.setAlpha(alpha);
        }
        if (by != null) {
            $this$apply.setBy(by);
        }
        if (descending != null) {
            $this$apply.setDescending(descending);
        }
        if (iterable != null) {
            for (String item : iterable) {
                $this$apply.addGet(item);
            }
        }
        if (store != null) {
            $this$apply.setStore(store);
        }
        return $this$apply;
    }
}
