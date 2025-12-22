package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWriter;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Updates.class */
public final class Updates {
    public static Bson combine(Bson... updates) {
        return combine((List<? extends Bson>) Arrays.asList(updates));
    }

    public static Bson combine(List<? extends Bson> updates) {
        Assertions.notNull("updates", updates);
        return new CompositeUpdate(updates);
    }

    public static <TItem> Bson set(String fieldName, @Nullable TItem value) {
        return new SimpleUpdate(fieldName, value, "$set");
    }

    public static Bson unset(String fieldName) {
        return new SimpleUpdate(fieldName, "", "$unset");
    }

    public static <TItem> Bson setOnInsert(String fieldName, @Nullable TItem value) {
        return new SimpleUpdate(fieldName, value, "$setOnInsert");
    }

    public static Bson rename(String fieldName, String newFieldName) {
        Assertions.notNull("newFieldName", newFieldName);
        return new SimpleUpdate(fieldName, newFieldName, "$rename");
    }

    public static Bson inc(String fieldName, Number number) {
        Assertions.notNull("number", number);
        return new SimpleUpdate(fieldName, number, "$inc");
    }

    public static Bson mul(String fieldName, Number number) {
        Assertions.notNull("number", number);
        return new SimpleUpdate(fieldName, number, "$mul");
    }

    public static <TItem> Bson min(String fieldName, TItem value) {
        return new SimpleUpdate(fieldName, value, "$min");
    }

    public static <TItem> Bson max(String fieldName, TItem value) {
        return new SimpleUpdate(fieldName, value, "$max");
    }

    public static Bson currentDate(String fieldName) {
        return new SimpleUpdate(fieldName, true, "$currentDate");
    }

    public static Bson currentTimestamp(String fieldName) {
        return new SimpleUpdate(fieldName, new BsonDocument("$type", new BsonString("timestamp")), "$currentDate");
    }

    public static <TItem> Bson addToSet(String fieldName, @Nullable TItem value) {
        return new SimpleUpdate(fieldName, value, "$addToSet");
    }

    public static <TItem> Bson addEachToSet(String fieldName, List<TItem> values) {
        return new WithEachUpdate(fieldName, values, "$addToSet");
    }

    public static <TItem> Bson push(String fieldName, @Nullable TItem value) {
        return new SimpleUpdate(fieldName, value, "$push");
    }

    public static <TItem> Bson pushEach(String fieldName, List<TItem> values) {
        return new PushUpdate(fieldName, values, new PushOptions());
    }

    public static <TItem> Bson pushEach(String fieldName, List<TItem> values, PushOptions options) {
        return new PushUpdate(fieldName, values, options);
    }

    public static <TItem> Bson pull(String fieldName, @Nullable TItem value) {
        return new SimpleUpdate(fieldName, value, "$pull");
    }

    public static Bson pullByFilter(final Bson filter) {
        return new Bson() { // from class: com.mongodb.client.model.Updates.1
            @Override // org.bson.conversions.Bson
            public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
                BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
                writer.writeStartDocument();
                writer.writeName("$pull");
                BuildersHelper.encodeValue(writer, filter, codecRegistry);
                writer.writeEndDocument();
                return writer.getDocument();
            }
        };
    }

    public static <TItem> Bson pullAll(String fieldName, List<TItem> values) {
        return new PullAllUpdate(fieldName, values);
    }

    public static Bson popFirst(String fieldName) {
        return new SimpleUpdate(fieldName, -1, "$pop");
    }

    public static Bson popLast(String fieldName) {
        return new SimpleUpdate(fieldName, 1, "$pop");
    }

    public static Bson bitwiseAnd(String fieldName, int value) {
        return createBitUpdateDocument(fieldName, "and", value);
    }

    public static Bson bitwiseAnd(String fieldName, long value) {
        return createBitUpdateDocument(fieldName, "and", value);
    }

    public static Bson bitwiseOr(String fieldName, int value) {
        return createBitUpdateDocument(fieldName, "or", value);
    }

    public static Bson bitwiseOr(String fieldName, long value) {
        return createBitUpdateDocument(fieldName, "or", value);
    }

    public static Bson bitwiseXor(String fieldName, int value) {
        return createBitUpdateDocument(fieldName, "xor", value);
    }

    public static Bson bitwiseXor(String fieldName, long value) {
        return createBitUpdateDocument(fieldName, "xor", value);
    }

    private static Bson createBitUpdateDocument(String fieldName, String bitwiseOperator, int value) {
        return createBitUpdateDocument(fieldName, bitwiseOperator, new BsonInt32(value));
    }

    private static Bson createBitUpdateDocument(String fieldName, String bitwiseOperator, long value) {
        return createBitUpdateDocument(fieldName, bitwiseOperator, new BsonInt64(value));
    }

    private static Bson createBitUpdateDocument(String fieldName, String bitwiseOperator, BsonValue value) {
        return new BsonDocument("$bit", new BsonDocument(fieldName, new BsonDocument(bitwiseOperator, value)));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Updates$SimpleUpdate.class */
    private static class SimpleUpdate<TItem> implements Bson {
        private final String fieldName;
        private final TItem value;
        private final String operator;

        SimpleUpdate(String fieldName, TItem value, String operator) {
            this.fieldName = (String) Assertions.notNull("fieldName", fieldName);
            this.value = value;
            this.operator = operator;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeName(this.operator);
            writer.writeStartDocument();
            writer.writeName(this.fieldName);
            BuildersHelper.encodeValue(writer, this.value, codecRegistry);
            writer.writeEndDocument();
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Update{fieldName='" + this.fieldName + "', operator='" + this.operator + "', value=" + this.value + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Updates$WithEachUpdate.class */
    private static class WithEachUpdate<TItem> implements Bson {
        private final String fieldName;
        private final List<TItem> values;
        private final String operator;

        WithEachUpdate(String fieldName, List<TItem> values, String operator) {
            this.fieldName = (String) Assertions.notNull("fieldName", fieldName);
            this.values = (List) Assertions.notNull("values", values);
            this.operator = operator;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeName(this.operator);
            writer.writeStartDocument();
            writer.writeName(this.fieldName);
            writer.writeStartDocument();
            writer.writeStartArray("$each");
            for (TItem value : this.values) {
                BuildersHelper.encodeValue(writer, value, codecRegistry);
            }
            writer.writeEndArray();
            writeAdditionalFields(writer, tDocumentClass, codecRegistry);
            writer.writeEndDocument();
            writer.writeEndDocument();
            writer.writeEndDocument();
            return writer.getDocument();
        }

        protected <TDocument> void writeAdditionalFields(BsonDocumentWriter writer, Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        }

        protected String additionalFieldsToString() {
            return "";
        }

        public String toString() {
            return "Each Update{fieldName='" + this.fieldName + "', operator='" + this.operator + "', values=" + this.values + additionalFieldsToString() + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Updates$PushUpdate.class */
    private static class PushUpdate<TItem> extends WithEachUpdate<TItem> {
        private final PushOptions options;

        PushUpdate(String fieldName, List<TItem> values, PushOptions options) {
            super(fieldName, values, "$push");
            this.options = (PushOptions) Assertions.notNull("options", options);
        }

        @Override // com.mongodb.client.model.Updates.WithEachUpdate
        protected <TDocument> void writeAdditionalFields(BsonDocumentWriter writer, Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            Integer position = this.options.getPosition();
            if (position != null) {
                writer.writeInt32("$position", position.intValue());
            }
            Integer slice = this.options.getSlice();
            if (slice != null) {
                writer.writeInt32("$slice", slice.intValue());
            }
            Integer sort = this.options.getSort();
            if (sort != null) {
                writer.writeInt32("$sort", sort.intValue());
                return;
            }
            Bson sortDocument = this.options.getSortDocument();
            if (sortDocument != null) {
                writer.writeName("$sort");
                BuildersHelper.encodeValue(writer, sortDocument, codecRegistry);
            }
        }

        @Override // com.mongodb.client.model.Updates.WithEachUpdate
        protected String additionalFieldsToString() {
            return ", options=" + this.options;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Updates$PullAllUpdate.class */
    private static class PullAllUpdate<TItem> implements Bson {
        private final String fieldName;
        private final List<TItem> values;

        PullAllUpdate(String fieldName, List<TItem> values) {
            this.fieldName = (String) Assertions.notNull("fieldName", fieldName);
            this.values = (List) Assertions.notNull("values", values);
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeName("$pullAll");
            writer.writeStartDocument();
            writer.writeName(this.fieldName);
            writer.writeStartArray();
            for (TItem value : this.values) {
                BuildersHelper.encodeValue(writer, value, codecRegistry);
            }
            writer.writeEndArray();
            writer.writeEndDocument();
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Update{fieldName='" + this.fieldName + "', operator='$pullAll', value=" + this.values + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Updates$CompositeUpdate.class */
    private static class CompositeUpdate implements Bson {
        private final List<? extends Bson> updates;

        CompositeUpdate(List<? extends Bson> updates) {
            this.updates = updates;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocument document = new BsonDocument();
            for (Bson update : this.updates) {
                BsonDocument rendered = update.toBsonDocument(tDocumentClass, codecRegistry);
                for (Map.Entry<String, BsonValue> element : rendered.entrySet()) {
                    if (document.containsKey(element.getKey())) {
                        BsonDocument currentOperatorDocument = (BsonDocument) element.getValue();
                        BsonDocument existingOperatorDocument = document.getDocument(element.getKey());
                        for (Map.Entry<String, BsonValue> currentOperationDocumentElements : currentOperatorDocument.entrySet()) {
                            existingOperatorDocument.append(currentOperationDocumentElements.getKey(), currentOperationDocumentElements.getValue());
                        }
                    } else {
                        document.append(element.getKey(), element.getValue());
                    }
                }
            }
            return document;
        }

        public String toString() {
            return "Updates{updates=" + this.updates + '}';
        }
    }

    private Updates() {
    }
}
