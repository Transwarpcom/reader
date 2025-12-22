package io.vertx.kotlin.ext.consul;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.ext.consul.Session;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Session.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001a\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n��\n\u0002\u0010\t\n\u0002\b\u0007\u001a[\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\u000b\u001aY\u0010\f\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {RtspHeaders.Names.SESSION, "Lio/vertx/ext/consul/Session;", "checks", "", "", "createIndex", "", "id", "index", "lockDelay", "node", "(Ljava/lang/Iterable;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)Lio/vertx/ext/consul/Session;", "sessionOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/SessionKt.class */
public final class SessionKt {
    @NotNull
    public static /* synthetic */ Session sessionOf$default(Iterable iterable, Long l, String str, Long l2, Long l3, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            l2 = (Long) null;
        }
        if ((i & 16) != 0) {
            l3 = (Long) null;
        }
        if ((i & 32) != 0) {
            str2 = (String) null;
        }
        return sessionOf(iterable, l, str, l2, l3, str2);
    }

    @NotNull
    public static final Session sessionOf(@Nullable Iterable<String> iterable, @Nullable Long createIndex, @Nullable String id, @Nullable Long index, @Nullable Long lockDelay, @Nullable String node) {
        Session $this$apply = new Session();
        if (iterable != null) {
            $this$apply.setChecks(CollectionsKt.toList(iterable));
        }
        if (createIndex != null) {
            $this$apply.setCreateIndex(createIndex.longValue());
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (index != null) {
            $this$apply.setIndex(index.longValue());
        }
        if (lockDelay != null) {
            $this$apply.setLockDelay(lockDelay.longValue());
        }
        if (node != null) {
            $this$apply.setNode(node);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "sessionOf(checks, createIndex, id, index, lockDelay, node)"))
    @NotNull
    public static /* synthetic */ Session Session$default(Iterable iterable, Long l, String str, Long l2, Long l3, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            l2 = (Long) null;
        }
        if ((i & 16) != 0) {
            l3 = (Long) null;
        }
        if ((i & 32) != 0) {
            str2 = (String) null;
        }
        return Session(iterable, l, str, l2, l3, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "sessionOf(checks, createIndex, id, index, lockDelay, node)"))
    @NotNull
    public static final Session Session(@Nullable Iterable<String> iterable, @Nullable Long createIndex, @Nullable String id, @Nullable Long index, @Nullable Long lockDelay, @Nullable String node) {
        Session $this$apply = new Session();
        if (iterable != null) {
            $this$apply.setChecks(CollectionsKt.toList(iterable));
        }
        if (createIndex != null) {
            $this$apply.setCreateIndex(createIndex.longValue());
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (index != null) {
            $this$apply.setIndex(index.longValue());
        }
        if (lockDelay != null) {
            $this$apply.setLockDelay(lockDelay.longValue());
        }
        if (node != null) {
            $this$apply.setNode(node);
        }
        return $this$apply;
    }
}
