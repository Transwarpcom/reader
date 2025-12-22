package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.TxnKVOperation;
import io.vertx.ext.consul.TxnKVVerb;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TxnKVOperation.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aU\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u000b\u001aS\u0010\f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {"TxnKVOperation", "Lio/vertx/ext/consul/TxnKVOperation;", "flags", "", "index", "key", "", "session", "type", "Lio/vertx/ext/consul/TxnKVVerb;", "value", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Lio/vertx/ext/consul/TxnKVVerb;Ljava/lang/String;)Lio/vertx/ext/consul/TxnKVOperation;", "txnKVOperationOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/TxnKVOperationKt.class */
public final class TxnKVOperationKt {
    @NotNull
    public static /* synthetic */ TxnKVOperation txnKVOperationOf$default(Long l, Long l2, String str, String str2, TxnKVVerb txnKVVerb, String str3, int i, Object obj) {
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
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            txnKVVerb = (TxnKVVerb) null;
        }
        if ((i & 32) != 0) {
            str3 = (String) null;
        }
        return txnKVOperationOf(l, l2, str, str2, txnKVVerb, str3);
    }

    @NotNull
    public static final TxnKVOperation txnKVOperationOf(@Nullable Long flags, @Nullable Long index, @Nullable String key, @Nullable String session, @Nullable TxnKVVerb type, @Nullable String value) {
        TxnKVOperation $this$apply = new TxnKVOperation();
        if (flags != null) {
            $this$apply.setFlags(flags.longValue());
        }
        if (index != null) {
            $this$apply.setIndex(index.longValue());
        }
        if (key != null) {
            $this$apply.setKey(key);
        }
        if (session != null) {
            $this$apply.setSession(session);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "txnKVOperationOf(flags, index, key, session, type, value)"))
    @NotNull
    public static /* synthetic */ TxnKVOperation TxnKVOperation$default(Long l, Long l2, String str, String str2, TxnKVVerb txnKVVerb, String str3, int i, Object obj) {
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
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            txnKVVerb = (TxnKVVerb) null;
        }
        if ((i & 32) != 0) {
            str3 = (String) null;
        }
        return TxnKVOperation(l, l2, str, str2, txnKVVerb, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "txnKVOperationOf(flags, index, key, session, type, value)"))
    @NotNull
    public static final TxnKVOperation TxnKVOperation(@Nullable Long flags, @Nullable Long index, @Nullable String key, @Nullable String session, @Nullable TxnKVVerb type, @Nullable String value) {
        TxnKVOperation $this$apply = new TxnKVOperation();
        if (flags != null) {
            $this$apply.setFlags(flags.longValue());
        }
        if (index != null) {
            $this$apply.setIndex(index.longValue());
        }
        if (key != null) {
            $this$apply.setKey(key);
        }
        if (session != null) {
            $this$apply.setSession(session);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }
}
