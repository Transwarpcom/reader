package io.vertx.kotlin.pgclient.data;

import io.netty.handler.codec.http.cookie.CookieHeaderNames;
import io.vertx.pgclient.data.Path;
import io.vertx.pgclient.data.Point;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Path.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001a\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0007\u001a)\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {CookieHeaderNames.PATH, "Lio/vertx/pgclient/data/Path;", "open", "", "points", "", "Lio/vertx/pgclient/data/Point;", "(Ljava/lang/Boolean;Ljava/lang/Iterable;)Lio/vertx/pgclient/data/Path;", "pathOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/pgclient/data/PathKt.class */
public final class PathKt {
    @NotNull
    public static /* synthetic */ Path pathOf$default(Boolean bool, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        return pathOf(bool, iterable);
    }

    @NotNull
    public static final Path pathOf(@Nullable Boolean open, @Nullable Iterable<? extends Point> iterable) {
        Path $this$apply = new Path();
        if (open != null) {
            $this$apply.setOpen(open.booleanValue());
        }
        if (iterable != null) {
            $this$apply.setPoints(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pathOf(open, points)"))
    @NotNull
    public static /* synthetic */ Path Path$default(Boolean bool, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        return Path(bool, iterable);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pathOf(open, points)"))
    @NotNull
    public static final Path Path(@Nullable Boolean open, @Nullable Iterable<? extends Point> iterable) {
        Path $this$apply = new Path();
        if (open != null) {
            $this$apply.setOpen(open.booleanValue());
        }
        if (iterable != null) {
            $this$apply.setPoints(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }
}
