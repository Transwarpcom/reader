package io.vertx.kotlin.amqp;

import io.vertx.amqp.AmqpReceiverOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AmqpReceiverOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\u001ay\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u000e\u001aw\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u000e¨\u0006\u0010"}, d2 = {"AmqpReceiverOptions", "Lio/vertx/amqp/AmqpReceiverOptions;", "autoAcknowledgement", "", "capabilities", "", "", "capabilitys", "durable", "dynamic", "linkName", "maxBufferedMessages", "", "qos", "(Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lio/vertx/amqp/AmqpReceiverOptions;", "amqpReceiverOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/amqp/AmqpReceiverOptionsKt.class */
public final class AmqpReceiverOptionsKt {
    @NotNull
    public static /* synthetic */ AmqpReceiverOptions amqpReceiverOptionsOf$default(Boolean bool, Iterable iterable, Iterable iterable2, Boolean bool2, Boolean bool3, String str, Integer num, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 8) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 16) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        if ((i & 64) != 0) {
            num = (Integer) null;
        }
        if ((i & 128) != 0) {
            str2 = (String) null;
        }
        return amqpReceiverOptionsOf(bool, iterable, iterable2, bool2, bool3, str, num, str2);
    }

    @NotNull
    public static final AmqpReceiverOptions amqpReceiverOptionsOf(@Nullable Boolean autoAcknowledgement, @Nullable Iterable<String> iterable, @Nullable Iterable<String> iterable2, @Nullable Boolean durable, @Nullable Boolean dynamic, @Nullable String linkName, @Nullable Integer maxBufferedMessages, @Nullable String qos) {
        AmqpReceiverOptions $this$apply = new AmqpReceiverOptions();
        if (autoAcknowledgement != null) {
            $this$apply.setAutoAcknowledgement(autoAcknowledgement.booleanValue());
        }
        if (iterable != null) {
            $this$apply.setCapabilities(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            for (String item : iterable2) {
                $this$apply.addCapability(item);
            }
        }
        if (durable != null) {
            $this$apply.setDurable(durable.booleanValue());
        }
        if (dynamic != null) {
            $this$apply.setDynamic(dynamic.booleanValue());
        }
        if (linkName != null) {
            $this$apply.setLinkName(linkName);
        }
        if (maxBufferedMessages != null) {
            $this$apply.setMaxBufferedMessages(maxBufferedMessages.intValue());
        }
        if (qos != null) {
            $this$apply.setQos(qos);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "amqpReceiverOptionsOf(autoAcknowledgement, capabilities, capabilitys, durable, dynamic, linkName, maxBufferedMessages, qos)"))
    @NotNull
    public static /* synthetic */ AmqpReceiverOptions AmqpReceiverOptions$default(Boolean bool, Iterable iterable, Iterable iterable2, Boolean bool2, Boolean bool3, String str, Integer num, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 8) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 16) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        if ((i & 64) != 0) {
            num = (Integer) null;
        }
        if ((i & 128) != 0) {
            str2 = (String) null;
        }
        return AmqpReceiverOptions(bool, iterable, iterable2, bool2, bool3, str, num, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "amqpReceiverOptionsOf(autoAcknowledgement, capabilities, capabilitys, durable, dynamic, linkName, maxBufferedMessages, qos)"))
    @NotNull
    public static final AmqpReceiverOptions AmqpReceiverOptions(@Nullable Boolean autoAcknowledgement, @Nullable Iterable<String> iterable, @Nullable Iterable<String> iterable2, @Nullable Boolean durable, @Nullable Boolean dynamic, @Nullable String linkName, @Nullable Integer maxBufferedMessages, @Nullable String qos) {
        AmqpReceiverOptions $this$apply = new AmqpReceiverOptions();
        if (autoAcknowledgement != null) {
            $this$apply.setAutoAcknowledgement(autoAcknowledgement.booleanValue());
        }
        if (iterable != null) {
            $this$apply.setCapabilities(CollectionsKt.toList(iterable));
        }
        if (iterable2 != null) {
            for (String item : iterable2) {
                $this$apply.addCapability(item);
            }
        }
        if (durable != null) {
            $this$apply.setDurable(durable.booleanValue());
        }
        if (dynamic != null) {
            $this$apply.setDynamic(dynamic.booleanValue());
        }
        if (linkName != null) {
            $this$apply.setLinkName(linkName);
        }
        if (maxBufferedMessages != null) {
            $this$apply.setMaxBufferedMessages(maxBufferedMessages.intValue());
        }
        if (qos != null) {
            $this$apply.setQos(qos);
        }
        return $this$apply;
    }
}
