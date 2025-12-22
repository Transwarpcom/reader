package com.mongodb.operation;

import com.mongodb.MongoChangeStreamException;
import com.mongodb.MongoCursorNotFoundException;
import com.mongodb.MongoException;
import com.mongodb.MongoNotPrimaryException;
import com.mongodb.MongoSocketException;
import java.util.Arrays;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/ChangeStreamBatchCursorHelper.class */
final class ChangeStreamBatchCursorHelper {
    private static final List<Integer> UNRETRYABLE_SERVER_ERROR_CODES = Arrays.asList(136, 237, 11601);

    static boolean isRetryableError(Throwable t) {
        if (!(t instanceof MongoException) || (t instanceof MongoChangeStreamException)) {
            return false;
        }
        return (t instanceof MongoNotPrimaryException) || (t instanceof MongoCursorNotFoundException) || (t instanceof MongoSocketException) || !UNRETRYABLE_SERVER_ERROR_CODES.contains(Integer.valueOf(((MongoException) t).getCode()));
    }

    private ChangeStreamBatchCursorHelper() {
    }
}
