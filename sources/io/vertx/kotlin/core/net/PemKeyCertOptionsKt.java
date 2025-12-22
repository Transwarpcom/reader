package io.vertx.kotlin.core.net;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.PemKeyCertOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PemKeyCertOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u001c\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0080\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005H\u0007\u001a~\u0010\r\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¨\u0006\u000e"}, d2 = {"PemKeyCertOptions", "Lio/vertx/core/net/PemKeyCertOptions;", "certPath", "", "certPaths", "", "certValue", "Lio/vertx/core/buffer/Buffer;", "certValues", "keyPath", "keyPaths", "keyValue", "keyValues", "pemKeyCertOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/net/PemKeyCertOptionsKt.class */
public final class PemKeyCertOptionsKt {
    @NotNull
    public static /* synthetic */ PemKeyCertOptions pemKeyCertOptionsOf$default(String str, Iterable iterable, Buffer buffer, Iterable iterable2, String str2, Iterable iterable3, Buffer buffer2, Iterable iterable4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 8) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        if ((i & 32) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 64) != 0) {
            buffer2 = (Buffer) null;
        }
        if ((i & 128) != 0) {
            iterable4 = (Iterable) null;
        }
        return pemKeyCertOptionsOf(str, iterable, buffer, iterable2, str2, iterable3, buffer2, iterable4);
    }

    @NotNull
    public static final PemKeyCertOptions pemKeyCertOptionsOf(@Nullable String certPath, @Nullable Iterable<String> iterable, @Nullable Buffer certValue, @Nullable Iterable<? extends Buffer> iterable2, @Nullable String keyPath, @Nullable Iterable<String> iterable3, @Nullable Buffer keyValue, @Nullable Iterable<? extends Buffer> iterable4) {
        PemKeyCertOptions $this$apply = new PemKeyCertOptions();
        if (certPath != null) {
            $this$apply.setCertPath(certPath);
        }
        if (iterable != null) {
            $this$apply.setCertPaths(CollectionsKt.toList(iterable));
        }
        if (certValue != null) {
            $this$apply.setCertValue(certValue);
        }
        if (iterable2 != null) {
            $this$apply.setCertValues(CollectionsKt.toList(iterable2));
        }
        if (keyPath != null) {
            $this$apply.setKeyPath(keyPath);
        }
        if (iterable3 != null) {
            $this$apply.setKeyPaths(CollectionsKt.toList(iterable3));
        }
        if (keyValue != null) {
            $this$apply.setKeyValue(keyValue);
        }
        if (iterable4 != null) {
            $this$apply.setKeyValues(CollectionsKt.toList(iterable4));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pemKeyCertOptionsOf(certPath, certPaths, certValue, certValues, keyPath, keyPaths, keyValue, keyValues)"))
    @NotNull
    public static /* synthetic */ PemKeyCertOptions PemKeyCertOptions$default(String str, Iterable iterable, Buffer buffer, Iterable iterable2, String str2, Iterable iterable3, Buffer buffer2, Iterable iterable4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 8) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        if ((i & 32) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 64) != 0) {
            buffer2 = (Buffer) null;
        }
        if ((i & 128) != 0) {
            iterable4 = (Iterable) null;
        }
        return PemKeyCertOptions(str, iterable, buffer, iterable2, str2, iterable3, buffer2, iterable4);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pemKeyCertOptionsOf(certPath, certPaths, certValue, certValues, keyPath, keyPaths, keyValue, keyValues)"))
    @NotNull
    public static final PemKeyCertOptions PemKeyCertOptions(@Nullable String certPath, @Nullable Iterable<String> iterable, @Nullable Buffer certValue, @Nullable Iterable<? extends Buffer> iterable2, @Nullable String keyPath, @Nullable Iterable<String> iterable3, @Nullable Buffer keyValue, @Nullable Iterable<? extends Buffer> iterable4) {
        PemKeyCertOptions $this$apply = new PemKeyCertOptions();
        if (certPath != null) {
            $this$apply.setCertPath(certPath);
        }
        if (iterable != null) {
            $this$apply.setCertPaths(CollectionsKt.toList(iterable));
        }
        if (certValue != null) {
            $this$apply.setCertValue(certValue);
        }
        if (iterable2 != null) {
            $this$apply.setCertValues(CollectionsKt.toList(iterable2));
        }
        if (keyPath != null) {
            $this$apply.setKeyPath(keyPath);
        }
        if (iterable3 != null) {
            $this$apply.setKeyPaths(CollectionsKt.toList(iterable3));
        }
        if (keyValue != null) {
            $this$apply.setKeyValue(keyValue);
        }
        if (iterable4 != null) {
            $this$apply.setKeyValues(CollectionsKt.toList(iterable4));
        }
        return $this$apply;
    }
}
