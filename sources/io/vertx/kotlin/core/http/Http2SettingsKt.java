package io.vertx.kotlin.core.http;

import io.vertx.core.http.Http2Settings;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Http2Settings.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u001aU\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0002\u0010\u000b\u001aS\u0010\f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {"Http2Settings", "Lio/vertx/core/http/Http2Settings;", "headerTableSize", "", "initialWindowSize", "", "maxConcurrentStreams", "maxFrameSize", "maxHeaderListSize", "pushEnabled", "", "(Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Boolean;)Lio/vertx/core/http/Http2Settings;", "http2SettingsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/http/Http2SettingsKt.class */
public final class Http2SettingsKt {
    @NotNull
    public static /* synthetic */ Http2Settings http2SettingsOf$default(Long l, Integer num, Long l2, Integer num2, Long l3, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        if ((i & 4) != 0) {
            l2 = (Long) null;
        }
        if ((i & 8) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 16) != 0) {
            l3 = (Long) null;
        }
        if ((i & 32) != 0) {
            bool = (Boolean) null;
        }
        return http2SettingsOf(l, num, l2, num2, l3, bool);
    }

    @NotNull
    public static final Http2Settings http2SettingsOf(@Nullable Long headerTableSize, @Nullable Integer initialWindowSize, @Nullable Long maxConcurrentStreams, @Nullable Integer maxFrameSize, @Nullable Long maxHeaderListSize, @Nullable Boolean pushEnabled) {
        Http2Settings $this$apply = new Http2Settings();
        if (headerTableSize != null) {
            $this$apply.setHeaderTableSize(headerTableSize.longValue());
        }
        if (initialWindowSize != null) {
            $this$apply.setInitialWindowSize(initialWindowSize.intValue());
        }
        if (maxConcurrentStreams != null) {
            $this$apply.setMaxConcurrentStreams(maxConcurrentStreams.longValue());
        }
        if (maxFrameSize != null) {
            $this$apply.setMaxFrameSize(maxFrameSize.intValue());
        }
        if (maxHeaderListSize != null) {
            $this$apply.setMaxHeaderListSize(maxHeaderListSize.longValue());
        }
        if (pushEnabled != null) {
            $this$apply.setPushEnabled(pushEnabled.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "http2SettingsOf(headerTableSize, initialWindowSize, maxConcurrentStreams, maxFrameSize, maxHeaderListSize, pushEnabled)"))
    @NotNull
    public static /* synthetic */ Http2Settings Http2Settings$default(Long l, Integer num, Long l2, Integer num2, Long l3, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        if ((i & 4) != 0) {
            l2 = (Long) null;
        }
        if ((i & 8) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 16) != 0) {
            l3 = (Long) null;
        }
        if ((i & 32) != 0) {
            bool = (Boolean) null;
        }
        return Http2Settings(l, num, l2, num2, l3, bool);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "http2SettingsOf(headerTableSize, initialWindowSize, maxConcurrentStreams, maxFrameSize, maxHeaderListSize, pushEnabled)"))
    @NotNull
    public static final Http2Settings Http2Settings(@Nullable Long headerTableSize, @Nullable Integer initialWindowSize, @Nullable Long maxConcurrentStreams, @Nullable Integer maxFrameSize, @Nullable Long maxHeaderListSize, @Nullable Boolean pushEnabled) {
        Http2Settings $this$apply = new Http2Settings();
        if (headerTableSize != null) {
            $this$apply.setHeaderTableSize(headerTableSize.longValue());
        }
        if (initialWindowSize != null) {
            $this$apply.setInitialWindowSize(initialWindowSize.intValue());
        }
        if (maxConcurrentStreams != null) {
            $this$apply.setMaxConcurrentStreams(maxConcurrentStreams.longValue());
        }
        if (maxFrameSize != null) {
            $this$apply.setMaxFrameSize(maxFrameSize.intValue());
        }
        if (maxHeaderListSize != null) {
            $this$apply.setMaxHeaderListSize(maxHeaderListSize.longValue());
        }
        if (pushEnabled != null) {
            $this$apply.setPushEnabled(pushEnabled.booleanValue());
        }
        return $this$apply;
    }
}
