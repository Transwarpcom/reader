package com.mongodb;

import com.mongodb.lang.Nullable;
import java.util.Iterator;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/WriteConcernException.class */
public class WriteConcernException extends MongoServerException {
    private static final long serialVersionUID = -1100801000476719450L;
    private final WriteConcernResult writeConcernResult;
    private final BsonDocument response;

    public WriteConcernException(BsonDocument response, ServerAddress address, WriteConcernResult writeConcernResult) {
        super(extractErrorCode(response), String.format("Write failed with error code %d and error message '%s'", Integer.valueOf(extractErrorCode(response)), extractErrorMessage(response)), address);
        this.response = response;
        this.writeConcernResult = writeConcernResult;
    }

    public static int extractErrorCode(BsonDocument response) {
        String errorMessage = extractErrorMessage(response);
        if (errorMessage != null) {
            if (response.containsKey("err") && errorMessage.contains("E11000 duplicate key error")) {
                return 11000;
            }
            if (!response.containsKey("code") && response.containsKey("errObjects")) {
                Iterator<BsonValue> it = response.getArray("errObjects").iterator();
                while (it.hasNext()) {
                    BsonValue curErrorDocument = it.next();
                    if (errorMessage.equals(extractErrorMessage(curErrorDocument.asDocument()))) {
                        return curErrorDocument.asDocument().getNumber("code").intValue();
                    }
                }
            }
        }
        return response.getNumber("code", new BsonInt32(-1)).intValue();
    }

    @Nullable
    public static String extractErrorMessage(BsonDocument response) {
        if (response.isString("err")) {
            return response.getString("err").getValue();
        }
        if (response.isString("errmsg")) {
            return response.getString("errmsg").getValue();
        }
        return null;
    }

    public WriteConcernResult getWriteConcernResult() {
        return this.writeConcernResult;
    }

    public int getErrorCode() {
        return extractErrorCode(this.response);
    }

    @Nullable
    public String getErrorMessage() {
        return extractErrorMessage(this.response);
    }

    public BsonDocument getResponse() {
        return this.response;
    }
}
