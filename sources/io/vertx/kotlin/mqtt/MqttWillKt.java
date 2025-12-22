package io.vertx.kotlin.mqtt;

import io.vertx.core.json.JsonObject;
import io.vertx.mqtt.MqttWill;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;

/* compiled from: MqttWill.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\b\u0010��\u001a\u00020\u0001H\u0007\u001a\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u0003"}, d2 = {"MqttWill", "Lio/vertx/mqtt/MqttWill;", "mqttWillOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/mqtt/MqttWillKt.class */
public final class MqttWillKt {
    @NotNull
    public static final MqttWill mqttWillOf() {
        return new MqttWill(new JsonObject());
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "mqttWillOf()"))
    @NotNull
    public static final MqttWill MqttWill() {
        return new MqttWill(new JsonObject());
    }
}
