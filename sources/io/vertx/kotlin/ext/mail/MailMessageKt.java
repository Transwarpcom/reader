package io.vertx.kotlin.ext.mail;

import io.vertx.core.cli.converters.FromBasedConverter;
import io.vertx.ext.mail.MailAttachment;
import io.vertx.ext.mail.MailMessage;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import me.ag2s.epublib.epub.NCXDocumentV2;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MailMessage.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��*\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\b\u001aÇ\u0001\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0013\u001aÅ\u0001\u0010\u0014\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0002\u0010\u0013¨\u0006\u0015"}, d2 = {"MailMessage", "Lio/vertx/ext/mail/MailMessage;", "attachment", "", "Lio/vertx/ext/mail/MailAttachment;", "bcc", "", "bounceAddress", "cc", "fixedHeaders", "", FromBasedConverter.FROM, "headers", "", "html", "inlineAttachment", PackageDocumentBase.DCTags.subject, NCXDocumentV2.NCXTags.text, "to", "(Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/Iterable;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Iterable;)Lio/vertx/ext/mail/MailMessage;", "mailMessageOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/mail/MailMessageKt.class */
public final class MailMessageKt {
    @NotNull
    public static /* synthetic */ MailMessage mailMessageOf$default(Iterable iterable, Iterable iterable2, String str, Iterable iterable3, Boolean bool, String str2, Map map, String str3, Iterable iterable4, String str4, String str5, Iterable iterable5, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 16) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 32) != 0) {
            str2 = (String) null;
        }
        if ((i & 64) != 0) {
            map = (Map) null;
        }
        if ((i & 128) != 0) {
            str3 = (String) null;
        }
        if ((i & 256) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 512) != 0) {
            str4 = (String) null;
        }
        if ((i & 1024) != 0) {
            str5 = (String) null;
        }
        if ((i & 2048) != 0) {
            iterable5 = (Iterable) null;
        }
        return mailMessageOf(iterable, iterable2, str, iterable3, bool, str2, map, str3, iterable4, str4, str5, iterable5);
    }

    @NotNull
    public static final MailMessage mailMessageOf(@Nullable Iterable<? extends MailAttachment> iterable, @Nullable Iterable<String> iterable2, @Nullable String bounceAddress, @Nullable Iterable<String> iterable3, @Nullable Boolean fixedHeaders, @Nullable String from, @Nullable Map<String, String> map, @Nullable String html, @Nullable Iterable<? extends MailAttachment> iterable4, @Nullable String subject, @Nullable String text, @Nullable Iterable<String> iterable5) {
        MailMessage $this$apply = new MailMessage();
        if (iterable != null) {
            $this$apply.setAttachment(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            $this$apply.setBcc(CollectionsKt.toList(iterable2));
        }
        if (bounceAddress != null) {
            $this$apply.setBounceAddress(bounceAddress);
        }
        if (iterable3 != null) {
            $this$apply.setCc(CollectionsKt.toList(iterable3));
        }
        if (fixedHeaders != null) {
            $this$apply.setFixedHeaders(fixedHeaders.booleanValue());
        }
        if (from != null) {
            $this$apply.setFrom(from);
        }
        if (map != null) {
            for (Map.Entry item : map.entrySet()) {
                $this$apply.addHeader(item.getKey(), item.getValue());
            }
        }
        if (html != null) {
            $this$apply.setHtml(html);
        }
        if (iterable4 != null) {
            $this$apply.setInlineAttachment(CollectionsKt.toList(iterable4));
        }
        if (subject != null) {
            $this$apply.setSubject(subject);
        }
        if (text != null) {
            $this$apply.setText(text);
        }
        if (iterable5 != null) {
            $this$apply.setTo(CollectionsKt.toList(iterable5));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mailMessageOf(attachment, bcc, bounceAddress, cc, fixedHeaders, from, headers, html, inlineAttachment, subject, text, to)"))
    @NotNull
    public static /* synthetic */ MailMessage MailMessage$default(Iterable iterable, Iterable iterable2, String str, Iterable iterable3, Boolean bool, String str2, Map map, String str3, Iterable iterable4, String str4, String str5, Iterable iterable5, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 16) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 32) != 0) {
            str2 = (String) null;
        }
        if ((i & 64) != 0) {
            map = (Map) null;
        }
        if ((i & 128) != 0) {
            str3 = (String) null;
        }
        if ((i & 256) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 512) != 0) {
            str4 = (String) null;
        }
        if ((i & 1024) != 0) {
            str5 = (String) null;
        }
        if ((i & 2048) != 0) {
            iterable5 = (Iterable) null;
        }
        return MailMessage(iterable, iterable2, str, iterable3, bool, str2, map, str3, iterable4, str4, str5, iterable5);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mailMessageOf(attachment, bcc, bounceAddress, cc, fixedHeaders, from, headers, html, inlineAttachment, subject, text, to)"))
    @NotNull
    public static final MailMessage MailMessage(@Nullable Iterable<? extends MailAttachment> iterable, @Nullable Iterable<String> iterable2, @Nullable String bounceAddress, @Nullable Iterable<String> iterable3, @Nullable Boolean fixedHeaders, @Nullable String from, @Nullable Map<String, String> map, @Nullable String html, @Nullable Iterable<? extends MailAttachment> iterable4, @Nullable String subject, @Nullable String text, @Nullable Iterable<String> iterable5) {
        MailMessage $this$apply = new MailMessage();
        if (iterable != null) {
            $this$apply.setAttachment(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            $this$apply.setBcc(CollectionsKt.toList(iterable2));
        }
        if (bounceAddress != null) {
            $this$apply.setBounceAddress(bounceAddress);
        }
        if (iterable3 != null) {
            $this$apply.setCc(CollectionsKt.toList(iterable3));
        }
        if (fixedHeaders != null) {
            $this$apply.setFixedHeaders(fixedHeaders.booleanValue());
        }
        if (from != null) {
            $this$apply.setFrom(from);
        }
        if (map != null) {
            for (Map.Entry item : map.entrySet()) {
                $this$apply.addHeader(item.getKey(), item.getValue());
            }
        }
        if (html != null) {
            $this$apply.setHtml(html);
        }
        if (iterable4 != null) {
            $this$apply.setInlineAttachment(CollectionsKt.toList(iterable4));
        }
        if (subject != null) {
            $this$apply.setSubject(subject);
        }
        if (text != null) {
            $this$apply.setText(text);
        }
        if (iterable5 != null) {
            $this$apply.setTo(CollectionsKt.toList(iterable5));
        }
        return $this$apply;
    }
}
