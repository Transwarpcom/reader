package io.vertx.kotlin.ext.mail;

import io.vertx.ext.mail.MailResult;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MailResult.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u001c\n\u0002\b\u0002\u001a&\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H\u0007\u001a$\u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¨\u0006\u0007"}, d2 = {"MailResult", "Lio/vertx/ext/mail/MailResult;", "messageID", "", "recipients", "", "mailResultOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/mail/MailResultKt.class */
public final class MailResultKt {
    @NotNull
    public static /* synthetic */ MailResult mailResultOf$default(String str, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        return mailResultOf(str, iterable);
    }

    @NotNull
    public static final MailResult mailResultOf(@Nullable String messageID, @Nullable Iterable<String> iterable) {
        MailResult $this$apply = new MailResult();
        if (messageID != null) {
            $this$apply.setMessageID(messageID);
        }
        if (iterable != null) {
            $this$apply.setRecipients(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mailResultOf(messageID, recipients)"))
    @NotNull
    public static /* synthetic */ MailResult MailResult$default(String str, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        return MailResult(str, iterable);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mailResultOf(messageID, recipients)"))
    @NotNull
    public static final MailResult MailResult(@Nullable String messageID, @Nullable Iterable<String> iterable) {
        MailResult $this$apply = new MailResult();
        if (messageID != null) {
            $this$apply.setMessageID(messageID);
        }
        if (iterable != null) {
            $this$apply.setRecipients(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }
}
