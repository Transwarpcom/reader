package io.vertx.kotlin.ext.mail;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailMessage;
import io.vertx.ext.mail.MailResult;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MailClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006"}, d2 = {"sendMailAwait", "Lio/vertx/ext/mail/MailResult;", "Lio/vertx/ext/mail/MailClient;", "email", "Lio/vertx/ext/mail/MailMessage;", "(Lio/vertx/ext/mail/MailClient;Lio/vertx/ext/mail/MailMessage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/mail/MailClientKt.class */
public final class MailClientKt {
    @Nullable
    public static final Object sendMailAwait(@NotNull final MailClient $this$sendMailAwait, @NotNull final MailMessage email, @NotNull Continuation<? super MailResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MailResult>>, Unit>() { // from class: io.vertx.kotlin.ext.mail.MailClientKt.sendMailAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MailResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MailResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$sendMailAwait.sendMail(email, it);
            }
        }, continuation);
    }
}
