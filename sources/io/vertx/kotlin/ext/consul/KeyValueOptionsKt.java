package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.KeyValueOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KeyValueOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\t\n\u0002\b\u0005\u001a=\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\b\u001a;\u0010\t\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"KeyValueOptions", "Lio/vertx/ext/consul/KeyValueOptions;", "acquireSession", "", "casIndex", "", "flags", "releaseSession", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)Lio/vertx/ext/consul/KeyValueOptions;", "keyValueOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/KeyValueOptionsKt.class */
public final class KeyValueOptionsKt {
    @NotNull
    public static /* synthetic */ KeyValueOptions keyValueOptionsOf$default(String str, Long l, Long l2, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            l2 = (Long) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        return keyValueOptionsOf(str, l, l2, str2);
    }

    @NotNull
    public static final KeyValueOptions keyValueOptionsOf(@Nullable String acquireSession, @Nullable Long casIndex, @Nullable Long flags, @Nullable String releaseSession) {
        KeyValueOptions $this$apply = new KeyValueOptions();
        if (acquireSession != null) {
            $this$apply.setAcquireSession(acquireSession);
        }
        if (casIndex != null) {
            $this$apply.setCasIndex(casIndex.longValue());
        }
        if (flags != null) {
            $this$apply.setFlags(flags.longValue());
        }
        if (releaseSession != null) {
            $this$apply.setReleaseSession(releaseSession);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "keyValueOptionsOf(acquireSession, casIndex, flags, releaseSession)"))
    @NotNull
    public static /* synthetic */ KeyValueOptions KeyValueOptions$default(String str, Long l, Long l2, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            l2 = (Long) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        return KeyValueOptions(str, l, l2, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "keyValueOptionsOf(acquireSession, casIndex, flags, releaseSession)"))
    @NotNull
    public static final KeyValueOptions KeyValueOptions(@Nullable String acquireSession, @Nullable Long casIndex, @Nullable Long flags, @Nullable String releaseSession) {
        KeyValueOptions $this$apply = new KeyValueOptions();
        if (acquireSession != null) {
            $this$apply.setAcquireSession(acquireSession);
        }
        if (casIndex != null) {
            $this$apply.setCasIndex(casIndex.longValue());
        }
        if (flags != null) {
            $this$apply.setFlags(flags.longValue());
        }
        if (releaseSession != null) {
            $this$apply.setReleaseSession(releaseSession);
        }
        return $this$apply;
    }
}
