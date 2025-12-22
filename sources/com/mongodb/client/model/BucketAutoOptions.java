package com.mongodb.client.model;

import com.mongodb.lang.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/BucketAutoOptions.class */
public class BucketAutoOptions {
    private List<BsonField> output;
    private BucketGranularity granularity;

    @Nullable
    public BucketGranularity getGranularity() {
        return this.granularity;
    }

    @Nullable
    public List<BsonField> getOutput() {
        if (this.output == null) {
            return null;
        }
        return new ArrayList(this.output);
    }

    public BucketAutoOptions granularity(@Nullable BucketGranularity granularity) {
        this.granularity = granularity;
        return this;
    }

    public BucketAutoOptions output(BsonField... output) {
        this.output = Arrays.asList(output);
        return this;
    }

    public BucketAutoOptions output(@Nullable List<BsonField> output) {
        this.output = output;
        return this;
    }

    public String toString() {
        return "BucketAutoOptions{output=" + this.output + ", granularity=" + this.granularity + '}';
    }
}
