package com.mongodb.client.model;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/ParallelCollectionScanOptions.class */
public class ParallelCollectionScanOptions {
    private int batchSize;

    public int getBatchSize() {
        return this.batchSize;
    }

    public ParallelCollectionScanOptions batchSize(int batchSize) {
        this.batchSize = batchSize;
        return this;
    }
}
