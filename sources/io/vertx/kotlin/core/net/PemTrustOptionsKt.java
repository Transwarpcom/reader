package io.vertx.kotlin.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.PemTrustOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PemTrustOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001a\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003H\u0007\u001a*\u0010\u0007\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¨\u0006\b"}, d2 = {"PemTrustOptions", "Lio/vertx/core/net/PemTrustOptions;", "certPaths", "", "", "certValues", "Lio/vertx/core/buffer/Buffer;", "pemTrustOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/net/PemTrustOptionsKt.class */
public final class PemTrustOptionsKt {
    @NotNull
    public static /* synthetic */ PemTrustOptions pemTrustOptionsOf$default(Iterable iterable, Iterable iterable2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            iterable2 = (Iterable) null;
        }
        return pemTrustOptionsOf(iterable, iterable2);
    }

    @NotNull
    public static final PemTrustOptions pemTrustOptionsOf(@Nullable Iterable<String> iterable, @Nullable Iterable<? extends Buffer> iterable2) throws NullPointerException {
        PemTrustOptions $this$apply = new PemTrustOptions();
        if (iterable != null) {
            for (String item : iterable) {
                $this$apply.addCertPath(item);
            }
        }
        if (iterable2 != null) {
            for (Buffer item2 : iterable2) {
                $this$apply.addCertValue(item2);
            }
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pemTrustOptionsOf(certPaths, certValues)"))
    @NotNull
    public static /* synthetic */ PemTrustOptions PemTrustOptions$default(Iterable iterable, Iterable iterable2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            iterable2 = (Iterable) null;
        }
        return PemTrustOptions(iterable, iterable2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pemTrustOptionsOf(certPaths, certValues)"))
    @NotNull
    public static final PemTrustOptions PemTrustOptions(@Nullable Iterable<String> iterable, @Nullable Iterable<? extends Buffer> iterable2) throws NullPointerException {
        PemTrustOptions $this$apply = new PemTrustOptions();
        if (iterable != null) {
            for (String item : iterable) {
                $this$apply.addCertPath(item);
            }
        }
        if (iterable2 != null) {
            for (Buffer item2 : iterable2) {
                $this$apply.addCertValue(item2);
            }
        }
        return $this$apply;
    }
}
