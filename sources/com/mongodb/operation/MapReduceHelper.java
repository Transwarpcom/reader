package com.mongodb.operation;

import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/MapReduceHelper.class */
final class MapReduceHelper {
    static MapReduceStatistics createStatistics(BsonDocument result) {
        return new MapReduceStatistics(getInputCount(result), getOutputCount(result), getEmitCount(result), getDuration(result));
    }

    private static int getInputCount(BsonDocument result) {
        return result.getDocument("counts").getNumber("input").intValue();
    }

    private static int getOutputCount(BsonDocument result) {
        return result.getDocument("counts").getNumber("output").intValue();
    }

    private static int getEmitCount(BsonDocument result) {
        return result.getDocument("counts").getNumber("emit").intValue();
    }

    private static int getDuration(BsonDocument result) {
        return result.getNumber("timeMillis").intValue();
    }

    private MapReduceHelper() {
    }
}
