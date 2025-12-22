package io.vertx.kotlin.amqp;

import io.vertx.amqp.AmqpSenderOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AmqpSenderOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"AmqpSenderOptions", "Lio/vertx/amqp/AmqpSenderOptions;", "autoDrained", "", "dynamic", "linkName", "", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;)Lio/vertx/amqp/AmqpSenderOptions;", "amqpSenderOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/amqp/AmqpSenderOptionsKt.class */
public final class AmqpSenderOptionsKt {
    @NotNull
    public static /* synthetic */ AmqpSenderOptions amqpSenderOptionsOf$default(Boolean bool, Boolean bool2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        return amqpSenderOptionsOf(bool, bool2, str);
    }

    @NotNull
    public static final AmqpSenderOptions amqpSenderOptionsOf(@Nullable Boolean autoDrained, @Nullable Boolean dynamic, @Nullable String linkName) {
        AmqpSenderOptions $this$apply = new AmqpSenderOptions();
        if (autoDrained != null) {
            $this$apply.setAutoDrained(autoDrained.booleanValue());
        }
        if (dynamic != null) {
            $this$apply.setDynamic(dynamic.booleanValue());
        }
        if (linkName != null) {
            $this$apply.setLinkName(linkName);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "amqpSenderOptionsOf(autoDrained, dynamic, linkName)"))
    @NotNull
    public static /* synthetic */ AmqpSenderOptions AmqpSenderOptions$default(Boolean bool, Boolean bool2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        return AmqpSenderOptions(bool, bool2, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "amqpSenderOptionsOf(autoDrained, dynamic, linkName)"))
    @NotNull
    public static final AmqpSenderOptions AmqpSenderOptions(@Nullable Boolean autoDrained, @Nullable Boolean dynamic, @Nullable String linkName) {
        AmqpSenderOptions $this$apply = new AmqpSenderOptions();
        if (autoDrained != null) {
            $this$apply.setAutoDrained(autoDrained.booleanValue());
        }
        if (dynamic != null) {
            $this$apply.setDynamic(dynamic.booleanValue());
        }
        if (linkName != null) {
            $this$apply.setLinkName(linkName);
        }
        return $this$apply;
    }
}
