package com.mongodb.client.model;

import com.mongodb.lang.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/BucketOptions.class */
public class BucketOptions {
    private Object defaultBucket;
    private List<BsonField> output;

    public BucketOptions defaultBucket(@Nullable Object name) {
        this.defaultBucket = name;
        return this;
    }

    @Nullable
    public Object getDefaultBucket() {
        return this.defaultBucket;
    }

    @Nullable
    public List<BsonField> getOutput() {
        if (this.output == null) {
            return null;
        }
        return new ArrayList(this.output);
    }

    public BucketOptions output(BsonField... output) {
        this.output = Arrays.asList(output);
        return this;
    }

    public BucketOptions output(List<BsonField> output) {
        this.output = output;
        return this;
    }

    public String toString() {
        return "BucketOptions{defaultBucket=" + this.defaultBucket + ", output=" + this.output + '}';
    }
}
