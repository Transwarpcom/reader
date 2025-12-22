package io.vertx.kotlin.ext.stomp;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.stomp.Frame;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Frame.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010$\n\u0002\b\u0004\u001a\\\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0007\u001aZ\u0010\f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007¨\u0006\r"}, d2 = {"Frame", "Lio/vertx/ext/stomp/Frame;", NCXDocumentV3.XHTMLTgs.body, "Lio/vertx/core/buffer/Buffer;", "command", "Lio/vertx/ext/stomp/Frame$Command;", RtspHeaders.Values.DESTINATION, "", "headers", "", "id", "transaction", "frameOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/stomp/FrameKt.class */
public final class FrameKt {
    @NotNull
    public static /* synthetic */ Frame frameOf$default(Buffer buffer, Frame.Command command, String str, Map map, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 2) != 0) {
            command = (Frame.Command) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            map = (Map) null;
        }
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        if ((i & 32) != 0) {
            str3 = (String) null;
        }
        return frameOf(buffer, command, str, map, str2, str3);
    }

    @NotNull
    public static final Frame frameOf(@Nullable Buffer body, @Nullable Frame.Command command, @Nullable String destination, @Nullable Map<String, String> map, @Nullable String id, @Nullable String transaction) {
        Frame $this$apply = new Frame();
        if (body != null) {
            $this$apply.setBody(body);
        }
        if (command != null) {
            $this$apply.setCommand(command);
        }
        if (destination != null) {
            $this$apply.setDestination(destination);
        }
        if (map != null) {
            $this$apply.setHeaders(map);
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (transaction != null) {
            $this$apply.setTransaction(transaction);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "frameOf(body, command, destination, headers, id, transaction)"))
    @NotNull
    public static /* synthetic */ Frame Frame$default(Buffer buffer, Frame.Command command, String str, Map map, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 2) != 0) {
            command = (Frame.Command) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            map = (Map) null;
        }
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        if ((i & 32) != 0) {
            str3 = (String) null;
        }
        return Frame(buffer, command, str, map, str2, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "frameOf(body, command, destination, headers, id, transaction)"))
    @NotNull
    public static final Frame Frame(@Nullable Buffer body, @Nullable Frame.Command command, @Nullable String destination, @Nullable Map<String, String> map, @Nullable String id, @Nullable String transaction) {
        Frame $this$apply = new Frame();
        if (body != null) {
            $this$apply.setBody(body);
        }
        if (command != null) {
            $this$apply.setCommand(command);
        }
        if (destination != null) {
            $this$apply.setDestination(destination);
        }
        if (map != null) {
            $this$apply.setHeaders(map);
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (transaction != null) {
            $this$apply.setTransaction(transaction);
        }
        return $this$apply;
    }
}
