package io.vertx.ext.web.handler.sockjs;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/PermittedOptions.class */
public class PermittedOptions extends io.vertx.ext.bridge.PermittedOptions {
    public PermittedOptions() {
    }

    public PermittedOptions(PermittedOptions that) {
        super(that);
    }

    public PermittedOptions(JsonObject json) {
        super(json);
    }
}
