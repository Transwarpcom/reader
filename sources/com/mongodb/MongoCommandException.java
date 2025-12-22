package com.mongodb;

import java.io.StringWriter;
import java.util.Iterator;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoCommandException.class */
public class MongoCommandException extends MongoServerException {
    private static final long serialVersionUID = 8160676451944215078L;
    private final BsonDocument response;

    public MongoCommandException(BsonDocument response, ServerAddress address) {
        super(extractErrorCode(response), String.format("Command failed with error %s: '%s' on server %s. The full response is %s", extractErrorCodeAndName(response), extractErrorMessage(response), address, getResponseAsJson(response)), address);
        this.response = response;
        Iterator<BsonValue> it = response.getArray("errorLabels", new BsonArray()).iterator();
        while (it.hasNext()) {
            BsonValue curErrorLabel = it.next();
            addLabel(curErrorLabel.asString().getValue());
        }
    }

    public int getErrorCode() {
        return getCode();
    }

    public String getErrorCodeName() {
        return extractErrorCodeName(this.response);
    }

    public String getErrorMessage() {
        return extractErrorMessage(this.response);
    }

    public BsonDocument getResponse() {
        return this.response;
    }

    private static String getResponseAsJson(BsonDocument commandResponse) {
        StringWriter writer = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(writer);
        new BsonDocumentCodec().encode((BsonWriter) jsonWriter, commandResponse, EncoderContext.builder().build());
        return writer.toString();
    }

    private static String extractErrorCodeAndName(BsonDocument response) {
        int errorCode = extractErrorCode(response);
        String errorCodeName = extractErrorCodeName(response);
        if (errorCodeName.isEmpty()) {
            return Integer.toString(errorCode);
        }
        return String.format("%d (%s)", Integer.valueOf(errorCode), errorCodeName);
    }

    private static int extractErrorCode(BsonDocument response) {
        return response.getNumber("code", new BsonInt32(-1)).intValue();
    }

    private static String extractErrorCodeName(BsonDocument response) {
        return response.getString("codeName", new BsonString("")).getValue();
    }

    private static String extractErrorMessage(BsonDocument response) {
        String errorMessage = response.getString("errmsg", new BsonString("")).getValue();
        if (errorMessage == null) {
            throw new MongoInternalException("This value should not be null");
        }
        return errorMessage;
    }
}
