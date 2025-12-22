package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;
import java.util.Arrays;
import java.util.List;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Projections.class */
public final class Projections {
    private Projections() {
    }

    public static <TExpression> Bson computed(String fieldName, TExpression expression) {
        return new SimpleExpression(fieldName, expression);
    }

    public static Bson include(String... fieldNames) {
        return include((List<String>) Arrays.asList(fieldNames));
    }

    public static Bson include(List<String> fieldNames) {
        return combine(fieldNames, new BsonInt32(1));
    }

    public static Bson exclude(String... fieldNames) {
        return exclude((List<String>) Arrays.asList(fieldNames));
    }

    public static Bson exclude(List<String> fieldNames) {
        return combine(fieldNames, new BsonInt32(0));
    }

    public static Bson excludeId() {
        return new BsonDocument("_id", new BsonInt32(0));
    }

    public static Bson elemMatch(String fieldName) {
        return new BsonDocument(fieldName + ".$", new BsonInt32(1));
    }

    public static Bson elemMatch(String fieldName, Bson filter) {
        return new ElemMatchFilterProjection(fieldName, filter);
    }

    public static Bson metaTextScore(String fieldName) {
        return new BsonDocument(fieldName, new BsonDocument("$meta", new BsonString("textScore")));
    }

    public static Bson slice(String fieldName, int limit) {
        return new BsonDocument(fieldName, new BsonDocument("$slice", new BsonInt32(limit)));
    }

    public static Bson slice(String fieldName, int skip, int limit) {
        return new BsonDocument(fieldName, new BsonDocument("$slice", new BsonArray(Arrays.asList(new BsonInt32(skip), new BsonInt32(limit)))));
    }

    public static Bson fields(Bson... projections) {
        return fields((List<? extends Bson>) Arrays.asList(projections));
    }

    public static Bson fields(List<? extends Bson> projections) {
        Assertions.notNull("sorts", projections);
        return new FieldsProjection(projections);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Projections$FieldsProjection.class */
    private static class FieldsProjection implements Bson {
        private final List<? extends Bson> projections;

        FieldsProjection(List<? extends Bson> projections) {
            this.projections = projections;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            BsonDocument combinedDocument = new BsonDocument();
            for (Bson sort : this.projections) {
                BsonDocument sortDocument = sort.toBsonDocument(documentClass, codecRegistry);
                for (String key : sortDocument.keySet()) {
                    combinedDocument.remove((Object) key);
                    combinedDocument.append(key, sortDocument.get((Object) key));
                }
            }
            return combinedDocument;
        }

        public String toString() {
            return "Projections{projections=" + this.projections + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Projections$ElemMatchFilterProjection.class */
    private static class ElemMatchFilterProjection implements Bson {
        private final String fieldName;
        private final Bson filter;

        ElemMatchFilterProjection(String fieldName, Bson filter) {
            this.fieldName = fieldName;
            this.filter = filter;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            return new BsonDocument(this.fieldName, new BsonDocument("$elemMatch", this.filter.toBsonDocument(documentClass, codecRegistry)));
        }

        public String toString() {
            return "ElemMatch Projection{fieldName='" + this.fieldName + "', filter=" + this.filter + '}';
        }
    }

    private static Bson combine(List<String> fieldNames, BsonValue value) {
        BsonDocument document = new BsonDocument();
        for (String fieldName : fieldNames) {
            document.remove((Object) fieldName);
            document.append(fieldName, value);
        }
        return document;
    }
}
