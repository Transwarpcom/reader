package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/PemKeyCertOptions.class */
public class PemKeyCertOptions implements KeyCertOptions, Cloneable {
    private List<String> keyPaths;
    private List<Buffer> keyValues;
    private List<String> certPaths;
    private List<Buffer> certValues;

    public PemKeyCertOptions() {
        init();
    }

    private void init() {
        this.keyPaths = new ArrayList();
        this.keyValues = new ArrayList();
        this.certPaths = new ArrayList();
        this.certValues = new ArrayList();
    }

    public PemKeyCertOptions(PemKeyCertOptions other) {
        this.keyPaths = other.keyPaths != null ? new ArrayList(other.keyPaths) : new ArrayList();
        this.keyValues = other.keyValues != null ? new ArrayList(other.keyValues) : new ArrayList();
        this.certPaths = other.certPaths != null ? new ArrayList(other.certPaths) : new ArrayList();
        this.certValues = other.certValues != null ? new ArrayList(other.certValues) : new ArrayList();
    }

    public PemKeyCertOptions(JsonObject json) {
        init();
        PemKeyCertOptionsConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        PemKeyCertOptionsConverter.toJson(this, json);
        return json;
    }

    @GenIgnore
    public String getKeyPath() {
        if (this.keyPaths.isEmpty()) {
            return null;
        }
        return this.keyPaths.get(0);
    }

    public PemKeyCertOptions setKeyPath(String keyPath) {
        this.keyPaths.clear();
        if (keyPath != null) {
            this.keyPaths.add(keyPath);
        }
        return this;
    }

    public List<String> getKeyPaths() {
        return this.keyPaths;
    }

    public PemKeyCertOptions setKeyPaths(List<String> keyPaths) {
        this.keyPaths.clear();
        this.keyPaths.addAll(keyPaths);
        return this;
    }

    @GenIgnore
    public PemKeyCertOptions addKeyPath(String keyPath) {
        Arguments.require(keyPath != null, "Null keyPath");
        this.keyPaths.add(keyPath);
        return this;
    }

    @GenIgnore
    public Buffer getKeyValue() {
        if (this.keyValues.isEmpty()) {
            return null;
        }
        return this.keyValues.get(0);
    }

    public PemKeyCertOptions setKeyValue(Buffer keyValue) {
        this.keyValues.clear();
        if (keyValue != null) {
            this.keyValues.add(keyValue);
        }
        return this;
    }

    public List<Buffer> getKeyValues() {
        return this.keyValues;
    }

    public PemKeyCertOptions setKeyValues(List<Buffer> keyValues) {
        this.keyValues.clear();
        this.keyValues.addAll(keyValues);
        return this;
    }

    @GenIgnore
    public PemKeyCertOptions addKeyValue(Buffer keyValue) {
        Arguments.require(keyValue != null, "Null keyValue");
        this.keyValues.add(keyValue);
        return this;
    }

    @GenIgnore
    public String getCertPath() {
        if (this.certPaths.isEmpty()) {
            return null;
        }
        return this.certPaths.get(0);
    }

    public PemKeyCertOptions setCertPath(String certPath) {
        this.certPaths.clear();
        if (certPath != null) {
            this.certPaths.add(certPath);
        }
        return this;
    }

    public List<String> getCertPaths() {
        return this.certPaths;
    }

    public PemKeyCertOptions setCertPaths(List<String> certPaths) {
        this.certPaths.clear();
        this.certPaths.addAll(certPaths);
        return this;
    }

    @GenIgnore
    public PemKeyCertOptions addCertPath(String certPath) {
        Arguments.require(certPath != null, "Null certPath");
        this.certPaths.add(certPath);
        return this;
    }

    @GenIgnore
    public Buffer getCertValue() {
        if (this.certValues.isEmpty()) {
            return null;
        }
        return this.certValues.get(0);
    }

    public PemKeyCertOptions setCertValue(Buffer certValue) {
        this.certValues.clear();
        if (certValue != null) {
            this.certValues.add(certValue);
        }
        return this;
    }

    public List<Buffer> getCertValues() {
        return this.certValues;
    }

    public PemKeyCertOptions setCertValues(List<Buffer> certValues) {
        this.certValues.clear();
        this.certValues.addAll(certValues);
        return this;
    }

    @GenIgnore
    public PemKeyCertOptions addCertValue(Buffer certValue) {
        Arguments.require(certValue != null, "Null certValue");
        this.certValues.add(certValue);
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PemKeyCertOptions)) {
            return false;
        }
        PemKeyCertOptions that = (PemKeyCertOptions) o;
        if (!this.keyPaths.equals(that.keyPaths) || !this.keyValues.equals(that.keyValues) || !this.certPaths.equals(that.certPaths) || !this.certValues.equals(that.certValues)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = 1 + (31 * 1) + this.keyPaths.hashCode();
        int result2 = result + (31 * result) + this.keyValues.hashCode();
        int result3 = result2 + (31 * result2) + this.certPaths.hashCode();
        return result3 + (31 * result3) + this.certValues.hashCode();
    }

    @Override // io.vertx.core.net.KeyCertOptions, io.vertx.core.net.TrustOptions
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public PemKeyCertOptions m1984clone() {
        return new PemKeyCertOptions(this);
    }
}
