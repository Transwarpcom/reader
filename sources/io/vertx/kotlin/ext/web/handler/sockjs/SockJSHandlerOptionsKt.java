package io.vertx.kotlin.ext.web.handler.sockjs;

import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SockJSHandlerOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��(\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u001a[\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\r\u001aY\u0010\u000e\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\r¨\u0006\u000f"}, d2 = {"SockJSHandlerOptions", "Lio/vertx/ext/web/handler/sockjs/SockJSHandlerOptions;", "disabledTransports", "", "", "heartbeatInterval", "", "insertJSESSIONID", "", "libraryURL", "maxBytesStreaming", "", "sessionTimeout", "(Ljava/lang/Iterable;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;)Lio/vertx/ext/web/handler/sockjs/SockJSHandlerOptions;", "sockJSHandlerOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/handler/sockjs/SockJSHandlerOptionsKt.class */
public final class SockJSHandlerOptionsKt {
    @NotNull
    public static /* synthetic */ SockJSHandlerOptions sockJSHandlerOptionsOf$default(Iterable iterable, Long l, Boolean bool, String str, Integer num, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            num = (Integer) null;
        }
        if ((i & 32) != 0) {
            l2 = (Long) null;
        }
        return sockJSHandlerOptionsOf(iterable, l, bool, str, num, l2);
    }

    @NotNull
    public static final SockJSHandlerOptions sockJSHandlerOptionsOf(@Nullable Iterable<String> iterable, @Nullable Long heartbeatInterval, @Nullable Boolean insertJSESSIONID, @Nullable String libraryURL, @Nullable Integer maxBytesStreaming, @Nullable Long sessionTimeout) {
        SockJSHandlerOptions $this$apply = new SockJSHandlerOptions();
        if (iterable != null) {
            for (String item : iterable) {
                $this$apply.addDisabledTransport(item);
            }
        }
        if (heartbeatInterval != null) {
            $this$apply.setHeartbeatInterval(heartbeatInterval.longValue());
        }
        if (insertJSESSIONID != null) {
            $this$apply.setInsertJSESSIONID(insertJSESSIONID.booleanValue());
        }
        if (libraryURL != null) {
            $this$apply.setLibraryURL(libraryURL);
        }
        if (maxBytesStreaming != null) {
            $this$apply.setMaxBytesStreaming(maxBytesStreaming.intValue());
        }
        if (sessionTimeout != null) {
            $this$apply.setSessionTimeout(sessionTimeout.longValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "sockJSHandlerOptionsOf(disabledTransports, heartbeatInterval, insertJSESSIONID, libraryURL, maxBytesStreaming, sessionTimeout)"))
    @NotNull
    public static /* synthetic */ SockJSHandlerOptions SockJSHandlerOptions$default(Iterable iterable, Long l, Boolean bool, String str, Integer num, Long l2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            num = (Integer) null;
        }
        if ((i & 32) != 0) {
            l2 = (Long) null;
        }
        return SockJSHandlerOptions(iterable, l, bool, str, num, l2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "sockJSHandlerOptionsOf(disabledTransports, heartbeatInterval, insertJSESSIONID, libraryURL, maxBytesStreaming, sessionTimeout)"))
    @NotNull
    public static final SockJSHandlerOptions SockJSHandlerOptions(@Nullable Iterable<String> iterable, @Nullable Long heartbeatInterval, @Nullable Boolean insertJSESSIONID, @Nullable String libraryURL, @Nullable Integer maxBytesStreaming, @Nullable Long sessionTimeout) {
        SockJSHandlerOptions $this$apply = new SockJSHandlerOptions();
        if (iterable != null) {
            for (String item : iterable) {
                $this$apply.addDisabledTransport(item);
            }
        }
        if (heartbeatInterval != null) {
            $this$apply.setHeartbeatInterval(heartbeatInterval.longValue());
        }
        if (insertJSESSIONID != null) {
            $this$apply.setInsertJSESSIONID(insertJSESSIONID.booleanValue());
        }
        if (libraryURL != null) {
            $this$apply.setLibraryURL(libraryURL);
        }
        if (maxBytesStreaming != null) {
            $this$apply.setMaxBytesStreaming(maxBytesStreaming.intValue());
        }
        if (sessionTimeout != null) {
            $this$apply.setSessionTimeout(sessionTimeout.longValue());
        }
        return $this$apply;
    }
}
