package com.mongodb.internal.operation;

import com.mongodb.client.model.IndexModel;
import java.util.ArrayList;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.BsonNumber;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/operation/IndexHelper.class */
public final class IndexHelper {
    public static List<String> getIndexNames(List<IndexModel> indexes, CodecRegistry codecRegistry) {
        List<String> indexNames = new ArrayList<>(indexes.size());
        for (IndexModel index : indexes) {
            String name = index.getOptions().getName();
            if (name != null) {
                indexNames.add(name);
            } else {
                indexNames.add(generateIndexName(index.getKeys().toBsonDocument(BsonDocument.class, codecRegistry)));
            }
        }
        return indexNames;
    }

    public static String generateIndexName(BsonDocument index) {
        StringBuilder indexName = new StringBuilder();
        for (String keyNames : index.keySet()) {
            if (indexName.length() != 0) {
                indexName.append('_');
            }
            indexName.append(keyNames).append('_');
            BsonValue ascOrDescValue = index.get((Object) keyNames);
            if (ascOrDescValue instanceof BsonNumber) {
                indexName.append(((BsonNumber) ascOrDescValue).intValue());
            } else if (ascOrDescValue instanceof BsonString) {
                indexName.append(((BsonString) ascOrDescValue).getValue().replace(' ', '_'));
            }
        }
        return indexName.toString();
    }

    private IndexHelper() {
    }
}
