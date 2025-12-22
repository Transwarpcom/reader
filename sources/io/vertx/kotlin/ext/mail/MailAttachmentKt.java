package io.vertx.kotlin.ext.mail;

import io.vertx.core.buffer.Buffer;
import io.vertx.ext.mail.MailAttachment;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MailAttachment.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\u001ah\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0007\u001af\u0010\f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¨\u0006\r"}, d2 = {"MailAttachment", "Lio/vertx/ext/mail/MailAttachment;", "contentId", "", "contentType", "data", "Lio/vertx/core/buffer/Buffer;", "description", "disposition", "headers", "", "name", "mailAttachmentOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/mail/MailAttachmentKt.class */
public final class MailAttachmentKt {
    @NotNull
    public static /* synthetic */ MailAttachment mailAttachmentOf$default(String str, String str2, Buffer buffer, String str3, String str4, Map map, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            str4 = (String) null;
        }
        if ((i & 32) != 0) {
            map = (Map) null;
        }
        if ((i & 64) != 0) {
            str5 = (String) null;
        }
        return mailAttachmentOf(str, str2, buffer, str3, str4, map, str5);
    }

    @NotNull
    public static final MailAttachment mailAttachmentOf(@Nullable String contentId, @Nullable String contentType, @Nullable Buffer data, @Nullable String description, @Nullable String disposition, @Nullable Map<String, String> map, @Nullable String name) {
        MailAttachment $this$apply = new MailAttachment();
        if (contentId != null) {
            $this$apply.setContentId(contentId);
        }
        if (contentType != null) {
            $this$apply.setContentType(contentType);
        }
        if (data != null) {
            $this$apply.setData(data);
        }
        if (description != null) {
            $this$apply.setDescription(description);
        }
        if (disposition != null) {
            $this$apply.setDisposition(disposition);
        }
        if (map != null) {
            for (Map.Entry item : map.entrySet()) {
                $this$apply.addHeader(item.getKey(), item.getValue());
            }
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mailAttachmentOf(contentId, contentType, data, description, disposition, headers, name)"))
    @NotNull
    public static /* synthetic */ MailAttachment MailAttachment$default(String str, String str2, Buffer buffer, String str3, String str4, Map map, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            buffer = (Buffer) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            str4 = (String) null;
        }
        if ((i & 32) != 0) {
            map = (Map) null;
        }
        if ((i & 64) != 0) {
            str5 = (String) null;
        }
        return MailAttachment(str, str2, buffer, str3, str4, map, str5);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mailAttachmentOf(contentId, contentType, data, description, disposition, headers, name)"))
    @NotNull
    public static final MailAttachment MailAttachment(@Nullable String contentId, @Nullable String contentType, @Nullable Buffer data, @Nullable String description, @Nullable String disposition, @Nullable Map<String, String> map, @Nullable String name) {
        MailAttachment $this$apply = new MailAttachment();
        if (contentId != null) {
            $this$apply.setContentId(contentId);
        }
        if (contentType != null) {
            $this$apply.setContentType(contentType);
        }
        if (data != null) {
            $this$apply.setData(data);
        }
        if (description != null) {
            $this$apply.setDescription(description);
        }
        if (disposition != null) {
            $this$apply.setDisposition(disposition);
        }
        if (map != null) {
            for (Map.Entry item : map.entrySet()) {
                $this$apply.addHeader(item.getKey(), item.getValue());
            }
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        return $this$apply;
    }
}
