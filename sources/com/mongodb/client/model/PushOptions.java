package com.mongodb.client.model;

import com.mongodb.lang.Nullable;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/PushOptions.class */
public class PushOptions {
    private Integer position;
    private Integer slice;
    private Integer sort;
    private Bson sortDocument;

    @Nullable
    public Integer getPosition() {
        return this.position;
    }

    public PushOptions position(@Nullable Integer position) {
        this.position = position;
        return this;
    }

    @Nullable
    public Integer getSlice() {
        return this.slice;
    }

    public PushOptions slice(@Nullable Integer slice) {
        this.slice = slice;
        return this;
    }

    @Nullable
    public Integer getSort() {
        return this.sort;
    }

    public PushOptions sort(@Nullable Integer sort) {
        if (this.sortDocument != null) {
            throw new IllegalStateException("sort can not be set if sortDocument already is");
        }
        this.sort = sort;
        return this;
    }

    @Nullable
    public Bson getSortDocument() {
        return this.sortDocument;
    }

    public PushOptions sortDocument(@Nullable Bson sortDocument) {
        if (this.sort != null) {
            throw new IllegalStateException("sortDocument can not be set if sort already is");
        }
        this.sortDocument = sortDocument;
        return this;
    }

    public String toString() {
        return "Push Options{position=" + this.position + ", slice=" + this.slice + (this.sort == null ? "" : ", sort=" + this.sort) + (this.sortDocument == null ? "" : ", sortDocument=" + this.sortDocument) + '}';
    }
}
