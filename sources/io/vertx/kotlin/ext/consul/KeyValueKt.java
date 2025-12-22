package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.KeyValue;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KeyValue.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u001aa\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u000b\u001a_\u0010\f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {"KeyValue", "Lio/vertx/ext/consul/KeyValue;", "createIndex", "", "flags", "key", "", "lockIndex", "modifyIndex", "session", "value", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)Lio/vertx/ext/consul/KeyValue;", "keyValueOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/KeyValueKt.class */
public final class KeyValueKt {
    @NotNull
    public static /* synthetic */ KeyValue keyValueOf$default(Long l, Long l2, String str, Long l3, Long l4, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            l3 = (Long) null;
        }
        if ((i & 16) != 0) {
            l4 = (Long) null;
        }
        if ((i & 32) != 0) {
            str2 = (String) null;
        }
        if ((i & 64) != 0) {
            str3 = (String) null;
        }
        return keyValueOf(l, l2, str, l3, l4, str2, str3);
    }

    @NotNull
    public static final KeyValue keyValueOf(@Nullable Long createIndex, @Nullable Long flags, @Nullable String key, @Nullable Long lockIndex, @Nullable Long modifyIndex, @Nullable String session, @Nullable String value) {
        KeyValue $this$apply = new KeyValue();
        if (createIndex != null) {
            $this$apply.setCreateIndex(createIndex.longValue());
        }
        if (flags != null) {
            $this$apply.setFlags(flags.longValue());
        }
        if (key != null) {
            $this$apply.setKey(key);
        }
        if (lockIndex != null) {
            $this$apply.setLockIndex(lockIndex.longValue());
        }
        if (modifyIndex != null) {
            $this$apply.setModifyIndex(modifyIndex.longValue());
        }
        if (session != null) {
            $this$apply.setSession(session);
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "keyValueOf(createIndex, flags, key, lockIndex, modifyIndex, session, value)"))
    @NotNull
    public static /* synthetic */ KeyValue KeyValue$default(Long l, Long l2, String str, Long l3, Long l4, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            l2 = (Long) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            l3 = (Long) null;
        }
        if ((i & 16) != 0) {
            l4 = (Long) null;
        }
        if ((i & 32) != 0) {
            str2 = (String) null;
        }
        if ((i & 64) != 0) {
            str3 = (String) null;
        }
        return KeyValue(l, l2, str, l3, l4, str2, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "keyValueOf(createIndex, flags, key, lockIndex, modifyIndex, session, value)"))
    @NotNull
    public static final KeyValue KeyValue(@Nullable Long createIndex, @Nullable Long flags, @Nullable String key, @Nullable Long lockIndex, @Nullable Long modifyIndex, @Nullable String session, @Nullable String value) {
        KeyValue $this$apply = new KeyValue();
        if (createIndex != null) {
            $this$apply.setCreateIndex(createIndex.longValue());
        }
        if (flags != null) {
            $this$apply.setFlags(flags.longValue());
        }
        if (key != null) {
            $this$apply.setKey(key);
        }
        if (lockIndex != null) {
            $this$apply.setLockIndex(lockIndex.longValue());
        }
        if (modifyIndex != null) {
            $this$apply.setModifyIndex(modifyIndex.longValue());
        }
        if (session != null) {
            $this$apply.setSession(session);
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }
}
