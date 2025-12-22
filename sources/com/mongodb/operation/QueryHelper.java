package com.mongodb.operation;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoCursorNotFoundException;
import com.mongodb.MongoQueryException;
import com.mongodb.ServerCursor;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/QueryHelper.class */
final class QueryHelper {
    static MongoQueryException translateCommandException(MongoCommandException commandException, ServerCursor cursor) {
        if (commandException.getErrorCode() == 43) {
            return new MongoCursorNotFoundException(cursor.getId(), cursor.getAddress());
        }
        return new MongoQueryException(cursor.getAddress(), commandException.getErrorCode(), commandException.getErrorMessage());
    }

    private QueryHelper() {
    }
}
