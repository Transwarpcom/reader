package io.vertx.ext.bridge;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

@DataObject(generateConverter = true)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-bridge-common-3.8.5.jar:io/vertx/ext/bridge/BridgeOptions.class */
public class BridgeOptions {
    private List<PermittedOptions> inboundPermitted;
    private List<PermittedOptions> outboundPermitted;

    public BridgeOptions() {
        this.inboundPermitted = new ArrayList();
        this.outboundPermitted = new ArrayList();
    }

    public BridgeOptions(BridgeOptions that) {
        this.inboundPermitted = new ArrayList();
        this.outboundPermitted = new ArrayList();
        this.inboundPermitted = that.inboundPermitted;
        this.outboundPermitted = that.outboundPermitted;
    }

    public BridgeOptions(JsonObject json) {
        this.inboundPermitted = new ArrayList();
        this.outboundPermitted = new ArrayList();
        BridgeOptionsConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        BridgeOptionsConverter.toJson(this, json);
        return json;
    }

    public BridgeOptions addInboundPermitted(PermittedOptions permitted) {
        this.inboundPermitted.add(permitted);
        return this;
    }

    public List<PermittedOptions> getInboundPermitteds() {
        return this.inboundPermitted;
    }

    public BridgeOptions setInboundPermitteds(List<PermittedOptions> inboundPermitted) {
        this.inboundPermitted = inboundPermitted;
        return this;
    }

    public BridgeOptions addOutboundPermitted(PermittedOptions permitted) {
        this.outboundPermitted.add(permitted);
        return this;
    }

    public List<PermittedOptions> getOutboundPermitteds() {
        return this.outboundPermitted;
    }

    public BridgeOptions setOutboundPermitteds(List<PermittedOptions> outboundPermitted) {
        this.outboundPermitted = outboundPermitted;
        return this;
    }
}
