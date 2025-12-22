package io.vertx.kotlin.ext.consul;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.ext.consul.SessionBehavior;
import io.vertx.ext.consul.SessionOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SessionOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n��\n\u0002\u0010\t\n\u0002\b\u0006\u001a[\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0007¢\u0006\u0002\u0010\f\u001aY\u0010\r\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\f¨\u0006\u000e"}, d2 = {"SessionOptions", "Lio/vertx/ext/consul/SessionOptions;", "behavior", "Lio/vertx/ext/consul/SessionBehavior;", "checks", "", "", "lockDelay", "", "name", "node", RtspHeaders.Values.TTL, "(Lio/vertx/ext/consul/SessionBehavior;Ljava/lang/Iterable;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lio/vertx/ext/consul/SessionOptions;", "sessionOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/SessionOptionsKt.class */
public final class SessionOptionsKt {
    @NotNull
    public static /* synthetic */ SessionOptions sessionOptionsOf$default(SessionBehavior sessionBehavior, Iterable iterable, Long l, String str, String str2, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            sessionBehavior = (SessionBehavior) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            l = (Long) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        if ((i & 32) != 0) {
            l2 = (Long) null;
        }
        return sessionOptionsOf(sessionBehavior, iterable, l, str, str2, l2);
    }

    @NotNull
    public static final SessionOptions sessionOptionsOf(@Nullable SessionBehavior behavior, @Nullable Iterable<String> iterable, @Nullable Long lockDelay, @Nullable String name, @Nullable String node, @Nullable Long ttl) {
        SessionOptions $this$apply = new SessionOptions();
        if (behavior != null) {
            $this$apply.setBehavior(behavior);
        }
        if (iterable != null) {
            $this$apply.setChecks(CollectionsKt.toList(iterable));
        }
        if (lockDelay != null) {
            $this$apply.setLockDelay(lockDelay.longValue());
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (node != null) {
            $this$apply.setNode(node);
        }
        if (ttl != null) {
            $this$apply.setTtl(ttl.longValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "sessionOptionsOf(behavior, checks, lockDelay, name, node, ttl)"))
    @NotNull
    public static /* synthetic */ SessionOptions SessionOptions$default(SessionBehavior sessionBehavior, Iterable iterable, Long l, String str, String str2, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            sessionBehavior = (SessionBehavior) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            l = (Long) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        if ((i & 32) != 0) {
            l2 = (Long) null;
        }
        return SessionOptions(sessionBehavior, iterable, l, str, str2, l2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "sessionOptionsOf(behavior, checks, lockDelay, name, node, ttl)"))
    @NotNull
    public static final SessionOptions SessionOptions(@Nullable SessionBehavior behavior, @Nullable Iterable<String> iterable, @Nullable Long lockDelay, @Nullable String name, @Nullable String node, @Nullable Long ttl) {
        SessionOptions $this$apply = new SessionOptions();
        if (behavior != null) {
            $this$apply.setBehavior(behavior);
        }
        if (iterable != null) {
            $this$apply.setChecks(CollectionsKt.toList(iterable));
        }
        if (lockDelay != null) {
            $this$apply.setLockDelay(lockDelay.longValue());
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (node != null) {
            $this$apply.setNode(node);
        }
        if (ttl != null) {
            $this$apply.setTtl(ttl.longValue());
        }
        return $this$apply;
    }
}
