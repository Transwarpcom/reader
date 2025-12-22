package com.mongodb.client.model;

import com.mongodb.lang.Nullable;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/GraphLookupOptions.class */
public final class GraphLookupOptions {
    private Integer maxDepth;
    private String depthField;
    private Bson restrictSearchWithMatch;

    public GraphLookupOptions depthField(@Nullable String field) {
        this.depthField = field;
        return this;
    }

    @Nullable
    public String getDepthField() {
        return this.depthField;
    }

    public GraphLookupOptions maxDepth(@Nullable Integer max) {
        this.maxDepth = max;
        return this;
    }

    @Nullable
    public Integer getMaxDepth() {
        return this.maxDepth;
    }

    public GraphLookupOptions restrictSearchWithMatch(@Nullable Bson filter) {
        this.restrictSearchWithMatch = filter;
        return this;
    }

    @Nullable
    public Bson getRestrictSearchWithMatch() {
        return this.restrictSearchWithMatch;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append("GraphLookupOptions{");
        if (this.depthField != null) {
            stringBuilder.append("depthField='").append(this.depthField).append('\'');
            if (this.maxDepth != null) {
                stringBuilder.append(", ");
            }
        }
        if (this.maxDepth != null) {
            stringBuilder.append("maxDepth=").append(this.maxDepth);
            if (this.restrictSearchWithMatch != null) {
                stringBuilder.append(", ");
            }
        }
        if (this.restrictSearchWithMatch != null) {
            stringBuilder.append("restrictSearchWithMatch=").append(this.restrictSearchWithMatch);
        }
        return stringBuilder.append('}').toString();
    }
}
