package io.vertx.ext.web;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/Http2PushMapping.class */
public class Http2PushMapping {
    private String filePath;
    private String extensionTarget;
    private boolean noPush;

    public Http2PushMapping(Http2PushMapping other) {
        this.filePath = other.filePath;
        this.extensionTarget = other.extensionTarget;
        this.noPush = other.noPush;
    }

    public Http2PushMapping() {
    }

    public Http2PushMapping(JsonObject json) {
        this.filePath = json.getString("filePath");
        this.extensionTarget = json.getString("extensionTarget");
        this.noPush = json.getBoolean("noPush").booleanValue();
    }

    public Http2PushMapping(String filePath, String extensionTarget, boolean noPush) {
        this.filePath = filePath;
        this.extensionTarget = extensionTarget;
        this.noPush = noPush;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getExtensionTarget() {
        return this.extensionTarget;
    }

    public void setExtensionTarget(String extensionTarget) {
        this.extensionTarget = extensionTarget;
    }

    public boolean isNoPush() {
        return this.noPush;
    }

    public void setNoPush(boolean noPush) {
        this.noPush = noPush;
    }
}
