package com.mongodb.operation;

import com.mongodb.ExplainVerbosity;
import com.mongodb.MongoInternalException;
import org.bson.BsonDocument;
import org.bson.BsonString;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/ExplainHelper.class */
final class ExplainHelper {
    static BsonDocument asExplainCommand(BsonDocument command, ExplainVerbosity explainVerbosity) {
        return new BsonDocument("explain", command).append("verbosity", getVerbosityAsString(explainVerbosity));
    }

    private static BsonString getVerbosityAsString(ExplainVerbosity explainVerbosity) {
        switch (explainVerbosity) {
            case QUERY_PLANNER:
                return new BsonString("queryPlanner");
            case EXECUTION_STATS:
                return new BsonString("executionStats");
            case ALL_PLANS_EXECUTIONS:
                return new BsonString("allPlansExecution");
            default:
                throw new MongoInternalException(String.format("Unsupported explain verbosity %s", explainVerbosity));
        }
    }

    private ExplainHelper() {
    }
}
