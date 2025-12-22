package io.vertx.kotlin.redis.op;

import io.vertx.redis.op.KillFilter;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KillFilter.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a=\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¢\u0006\u0002\u0010\t\u001a;\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"KillFilter", "Lio/vertx/redis/op/KillFilter;", "addr", "", "id", "skipme", "", "type", "Lio/vertx/redis/op/KillFilter$Type;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lio/vertx/redis/op/KillFilter$Type;)Lio/vertx/redis/op/KillFilter;", "killFilterOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/KillFilterKt.class */
public final class KillFilterKt {
    @NotNull
    public static /* synthetic */ KillFilter killFilterOf$default(String str, String str2, Boolean bool, KillFilter.Type type, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            type = (KillFilter.Type) null;
        }
        return killFilterOf(str, str2, bool, type);
    }

    @NotNull
    public static final KillFilter killFilterOf(@Nullable String addr, @Nullable String id, @Nullable Boolean skipme, @Nullable KillFilter.Type type) {
        KillFilter $this$apply = new KillFilter();
        if (addr != null) {
            $this$apply.setAddr(addr);
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (skipme != null) {
            $this$apply.setSkipme(skipme.booleanValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "killFilterOf(addr, id, skipme, type)"))
    @NotNull
    public static /* synthetic */ KillFilter KillFilter$default(String str, String str2, Boolean bool, KillFilter.Type type, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            type = (KillFilter.Type) null;
        }
        return KillFilter(str, str2, bool, type);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "killFilterOf(addr, id, skipme, type)"))
    @NotNull
    public static final KillFilter KillFilter(@Nullable String addr, @Nullable String id, @Nullable Boolean skipme, @Nullable KillFilter.Type type) {
        KillFilter $this$apply = new KillFilter();
        if (addr != null) {
            $this$apply.setAddr(addr);
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (skipme != null) {
            $this$apply.setSkipme(skipme.booleanValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }
}
