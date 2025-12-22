package io.vertx.ext.bridge;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-bridge-common-3.8.5.jar:io/vertx/ext/bridge/PermittedOptions.class */
public class PermittedOptions {
    public static JsonObject DEFAULT_MATCH = null;
    public static String DEFAULT_ADDRESS = null;
    public static String DEFAULT_ADDRESS_REGEX = null;
    public static String DEFAULT_REQUIRED_AUTHORITY = null;
    private String address;
    private String addressRegex;
    private String requiredAuthority;
    private JsonObject match;

    public PermittedOptions() {
        this.address = DEFAULT_ADDRESS;
        this.addressRegex = DEFAULT_ADDRESS_REGEX;
        this.requiredAuthority = DEFAULT_REQUIRED_AUTHORITY;
        this.match = DEFAULT_MATCH;
    }

    public PermittedOptions(PermittedOptions that) {
        this.address = DEFAULT_ADDRESS;
        this.addressRegex = DEFAULT_ADDRESS_REGEX;
        this.requiredAuthority = DEFAULT_REQUIRED_AUTHORITY;
        this.match = DEFAULT_MATCH;
        this.address = that.address;
        this.addressRegex = that.addressRegex;
        this.match = that.match;
        this.requiredAuthority = that.requiredAuthority;
    }

    public PermittedOptions(JsonObject json) {
        this.address = DEFAULT_ADDRESS;
        this.addressRegex = DEFAULT_ADDRESS_REGEX;
        this.requiredAuthority = DEFAULT_REQUIRED_AUTHORITY;
        this.match = DEFAULT_MATCH;
        PermittedOptionsConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        PermittedOptionsConverter.toJson(this, json);
        return json;
    }

    public String getAddress() {
        return this.address;
    }

    public PermittedOptions setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAddressRegex() {
        return this.addressRegex;
    }

    public PermittedOptions setAddressRegex(String addressRegex) {
        this.addressRegex = addressRegex;
        return this;
    }

    public JsonObject getMatch() {
        return this.match;
    }

    public PermittedOptions setMatch(JsonObject match) {
        this.match = match;
        return this;
    }

    public String getRequiredAuthority() {
        return this.requiredAuthority;
    }

    public PermittedOptions setRequiredAuthority(String requiredAuthority) {
        this.requiredAuthority = requiredAuthority;
        return this;
    }
}
