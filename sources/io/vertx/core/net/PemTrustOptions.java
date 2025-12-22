package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/PemTrustOptions.class */
public class PemTrustOptions implements TrustOptions, Cloneable {
    private ArrayList<String> certPaths;
    private ArrayList<Buffer> certValues;

    public PemTrustOptions() {
        this.certPaths = new ArrayList<>();
        this.certValues = new ArrayList<>();
    }

    public PemTrustOptions(PemTrustOptions other) {
        this.certPaths = new ArrayList<>(other.getCertPaths());
        this.certValues = new ArrayList<>(other.getCertValues());
    }

    public PemTrustOptions(JsonObject json) {
        this();
        PemTrustOptionsConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        PemTrustOptionsConverter.toJson(this, json);
        return json;
    }

    public List<String> getCertPaths() {
        return this.certPaths;
    }

    public PemTrustOptions addCertPath(String certPath) throws NullPointerException {
        Objects.requireNonNull(certPath, "No null certificate accepted");
        Arguments.require(!certPath.isEmpty(), "No empty certificate path accepted");
        this.certPaths.add(certPath);
        return this;
    }

    public List<Buffer> getCertValues() {
        return this.certValues;
    }

    public PemTrustOptions addCertValue(Buffer certValue) throws NullPointerException {
        Objects.requireNonNull(certValue, "No null certificate accepted");
        this.certValues.add(certValue);
        return this;
    }

    @Override // io.vertx.core.net.TrustOptions
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public PemTrustOptions m1984clone() {
        return new PemTrustOptions(this);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PemTrustOptions that = (PemTrustOptions) o;
        if (this.certPaths.equals(that.certPaths)) {
            return this.certValues.equals(that.certValues);
        }
        return false;
    }

    public int hashCode() {
        int result = this.certPaths.hashCode();
        return (31 * result) + this.certValues.hashCode();
    }
}
