package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.TxnError;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TxnError.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"TxnError", "Lio/vertx/ext/consul/TxnError;", "opIndex", "", "what", "", "(Ljava/lang/Integer;Ljava/lang/String;)Lio/vertx/ext/consul/TxnError;", "txnErrorOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/TxnErrorKt.class */
public final class TxnErrorKt {
    @NotNull
    public static /* synthetic */ TxnError txnErrorOf$default(Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return txnErrorOf(num, str);
    }

    @NotNull
    public static final TxnError txnErrorOf(@Nullable Integer opIndex, @Nullable String what) {
        TxnError $this$apply = new TxnError();
        if (opIndex != null) {
            $this$apply.setOpIndex(opIndex.intValue());
        }
        if (what != null) {
            $this$apply.setWhat(what);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "txnErrorOf(opIndex, what)"))
    @NotNull
    public static /* synthetic */ TxnError TxnError$default(Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return TxnError(num, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "txnErrorOf(opIndex, what)"))
    @NotNull
    public static final TxnError TxnError(@Nullable Integer opIndex, @Nullable String what) {
        TxnError $this$apply = new TxnError();
        if (opIndex != null) {
            $this$apply.setOpIndex(opIndex.intValue());
        }
        if (what != null) {
            $this$apply.setWhat(what);
        }
        return $this$apply;
    }
}
