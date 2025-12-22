package io.vertx.ext.web.impl;

import io.vertx.ext.web.ParsedHeaderValue;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/ParsableHeaderValue.class */
public class ParsableHeaderValue implements ParsedHeaderValue {
    private String headerContent;
    protected String value;
    private float weight;
    private Map<String, String> parameter;
    private int paramsWeight;

    public ParsableHeaderValue(String headerContent) {
        Objects.requireNonNull(headerContent, "headerContent must not be null");
        this.headerContent = headerContent;
        this.value = null;
        this.weight = -1.0f;
        this.parameter = Collections.emptyMap();
    }

    @Override // io.vertx.ext.web.ParsedHeaderValue
    public String rawValue() {
        return this.headerContent;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValue
    public String value() {
        ensureHeaderProcessed();
        return this.value;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValue
    public float weight() {
        ensureHeaderProcessed();
        return this.weight;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValue
    public boolean isPermitted() {
        ensureHeaderProcessed();
        return ((double) this.weight) < 0.001d;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValue
    public String parameter(String key) {
        ensureHeaderProcessed();
        return this.parameter.get(key);
    }

    @Override // io.vertx.ext.web.ParsedHeaderValue
    public Map<String, String> parameters() {
        ensureHeaderProcessed();
        return Collections.unmodifiableMap(this.parameter);
    }

    @Override // io.vertx.ext.web.ParsedHeaderValue
    public final boolean isMatchedBy(ParsedHeaderValue matchTry) {
        ParsableHeaderValue impl = (ParsableHeaderValue) matchTry;
        return this.headerContent.equals(impl.headerContent) || isMatchedBy2(impl);
    }

    protected boolean isMatchedBy2(ParsableHeaderValue matchTry) {
        ensureHeaderProcessed();
        if (matchTry.parameter.isEmpty()) {
            return true;
        }
        if (this.parameter.isEmpty()) {
            return false;
        }
        for (Map.Entry<String, String> requiredParameter : matchTry.parameter.entrySet()) {
            String parameterValueToTest = this.parameter.get(requiredParameter.getKey());
            String requiredParamVal = requiredParameter.getValue();
            if (parameterValueToTest != null) {
                if (!requiredParamVal.isEmpty() && !requiredParamVal.equalsIgnoreCase(parameterValueToTest)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValue
    public <T extends ParsedHeaderValue> T findMatchedBy(Collection<T> matchTries) {
        for (T matchTry : matchTries) {
            if (isMatchedBy(matchTry)) {
                return matchTry;
            }
        }
        return null;
    }

    private void ensureParameterIsHashMap() {
        if (this.parameter.isEmpty()) {
            this.parameter = new HashMap();
        }
    }

    protected void ensureHeaderProcessed() {
        if (this.weight < 0.0f) {
            this.weight = 1.0f;
            HeaderParser.parseHeaderValue(this.headerContent, this::setValue, (v1) -> {
                setWeight(v1);
            }, this::addParameter);
            this.paramsWeight = this.parameter.isEmpty() ? 0 : 1;
        }
    }

    public ParsableHeaderValue forceParse() {
        ensureHeaderProcessed();
        return this;
    }

    private void setValue(String value) {
        this.value = value;
    }

    private void addParameter(String key, String value) {
        ensureParameterIsHashMap();
        if (value == null) {
            value = "";
            this.paramsWeight = Math.max(1, this.paramsWeight);
        } else {
            this.paramsWeight = Math.max(2, this.paramsWeight);
        }
        this.parameter.put(key, value);
    }

    private void setWeight(float weight) {
        this.weight = ((int) (Math.max(0.0f, Math.min(1.0f, weight)) * 100.0f)) / 100.0f;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValue
    public final int weightedOrder() {
        ensureHeaderProcessed();
        return ((int) (weight() * 1000.0f)) + (weightedOrderPart2() * 10) + this.paramsWeight;
    }

    protected int weightedOrderPart2() {
        return 0;
    }

    public int hashCode() {
        return Objects.hash(this.headerContent);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ParsableHeaderValue)) {
            return false;
        }
        ParsableHeaderValue other = (ParsableHeaderValue) obj;
        if (this.headerContent == null) {
            return other.headerContent == null;
        }
        return this.headerContent.equals(other.headerContent);
    }
}
