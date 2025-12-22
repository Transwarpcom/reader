package io.vertx.kotlin.ext.web;

import io.vertx.ext.web.Http2PushMapping;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Http2PushMapping.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"Http2PushMapping", "Lio/vertx/ext/web/Http2PushMapping;", "extensionTarget", "", "filePath", "noPush", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lio/vertx/ext/web/Http2PushMapping;", "http2PushMappingOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/Http2PushMappingKt.class */
public final class Http2PushMappingKt {
    @NotNull
    public static /* synthetic */ Http2PushMapping http2PushMappingOf$default(String str, String str2, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        return http2PushMappingOf(str, str2, bool);
    }

    @NotNull
    public static final Http2PushMapping http2PushMappingOf(@Nullable String extensionTarget, @Nullable String filePath, @Nullable Boolean noPush) {
        Http2PushMapping $this$apply = new Http2PushMapping();
        if (extensionTarget != null) {
            $this$apply.setExtensionTarget(extensionTarget);
        }
        if (filePath != null) {
            $this$apply.setFilePath(filePath);
        }
        if (noPush != null) {
            $this$apply.setNoPush(noPush.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "http2PushMappingOf(extensionTarget, filePath, noPush)"))
    @NotNull
    public static /* synthetic */ Http2PushMapping Http2PushMapping$default(String str, String str2, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        return Http2PushMapping(str, str2, bool);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "http2PushMappingOf(extensionTarget, filePath, noPush)"))
    @NotNull
    public static final Http2PushMapping Http2PushMapping(@Nullable String extensionTarget, @Nullable String filePath, @Nullable Boolean noPush) {
        Http2PushMapping $this$apply = new Http2PushMapping();
        if (extensionTarget != null) {
            $this$apply.setExtensionTarget(extensionTarget);
        }
        if (filePath != null) {
            $this$apply.setFilePath(filePath);
        }
        if (noPush != null) {
            $this$apply.setNoPush(noPush.booleanValue());
        }
        return $this$apply;
    }
}
