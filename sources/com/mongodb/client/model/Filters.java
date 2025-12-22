package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;
import com.mongodb.client.model.geojson.Geometry;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.lang.Nullable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWriter;
import org.bson.BsonDouble;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonRegularExpression;
import org.bson.BsonString;
import org.bson.BsonType;
import org.bson.BsonValue;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Filters.class */
public final class Filters {
    private Filters() {
    }

    public static <TItem> Bson eq(@Nullable TItem value) {
        return eq("_id", value);
    }

    public static <TItem> Bson eq(String fieldName, @Nullable TItem value) {
        return new SimpleEncodingFilter(fieldName, value);
    }

    public static <TItem> Bson ne(String fieldName, @Nullable TItem value) {
        return new OperatorFilter("$ne", fieldName, value);
    }

    public static <TItem> Bson gt(String fieldName, TItem value) {
        return new OperatorFilter("$gt", fieldName, value);
    }

    public static <TItem> Bson lt(String fieldName, TItem value) {
        return new OperatorFilter("$lt", fieldName, value);
    }

    public static <TItem> Bson gte(String fieldName, TItem value) {
        return new OperatorFilter("$gte", fieldName, value);
    }

    public static <TItem> Bson lte(String fieldName, TItem value) {
        return new OperatorFilter("$lte", fieldName, value);
    }

    public static <TItem> Bson in(String fieldName, TItem... values) {
        return in(fieldName, Arrays.asList(values));
    }

    public static <TItem> Bson in(String fieldName, Iterable<TItem> values) {
        return new IterableOperatorFilter(fieldName, "$in", values);
    }

    public static <TItem> Bson nin(String fieldName, TItem... values) {
        return nin(fieldName, Arrays.asList(values));
    }

    public static <TItem> Bson nin(String fieldName, Iterable<TItem> values) {
        return new IterableOperatorFilter(fieldName, "$nin", values);
    }

    public static Bson and(Iterable<Bson> filters) {
        return new AndFilter(filters);
    }

    public static Bson and(Bson... filters) {
        return and(Arrays.asList(filters));
    }

    public static Bson or(Iterable<Bson> filters) {
        return new OrNorFilter(OrNorFilter.Operator.OR, filters);
    }

    public static Bson or(Bson... filters) {
        return or(Arrays.asList(filters));
    }

    public static Bson not(Bson filter) {
        return new NotFilter(filter);
    }

    public static Bson nor(Bson... filters) {
        return nor(Arrays.asList(filters));
    }

    public static Bson nor(Iterable<Bson> filters) {
        return new OrNorFilter(OrNorFilter.Operator.NOR, filters);
    }

    public static Bson exists(String fieldName) {
        return exists(fieldName, true);
    }

    public static Bson exists(String fieldName, boolean exists) {
        return new OperatorFilter("$exists", fieldName, BsonBoolean.valueOf(exists));
    }

    public static Bson type(String fieldName, BsonType type) {
        return new OperatorFilter("$type", fieldName, new BsonInt32(type.getValue()));
    }

    public static Bson type(String fieldName, String type) {
        return new OperatorFilter("$type", fieldName, new BsonString(type));
    }

    public static Bson mod(String fieldName, long divisor, long remainder) {
        return new OperatorFilter("$mod", fieldName, new BsonArray(Arrays.asList(new BsonInt64(divisor), new BsonInt64(remainder))));
    }

    public static Bson regex(String fieldName, String pattern) {
        return regex(fieldName, pattern, null);
    }

    public static Bson regex(String fieldName, String pattern, @Nullable String options) {
        Assertions.notNull("pattern", pattern);
        return new SimpleFilter(fieldName, new BsonRegularExpression(pattern, options));
    }

    public static Bson regex(String fieldName, Pattern pattern) {
        Assertions.notNull("pattern", pattern);
        return new SimpleEncodingFilter(fieldName, pattern);
    }

    public static Bson text(String search) {
        Assertions.notNull("search", search);
        return text(search, new TextSearchOptions());
    }

    @Deprecated
    public static Bson text(String search, String language) {
        Assertions.notNull("search", search);
        return text(search, new TextSearchOptions().language(language));
    }

    public static Bson text(String search, TextSearchOptions textSearchOptions) {
        Assertions.notNull("search", search);
        Assertions.notNull("textSearchOptions", textSearchOptions);
        return new TextFilter(search, textSearchOptions);
    }

    public static Bson where(String javaScriptExpression) {
        Assertions.notNull("javaScriptExpression", javaScriptExpression);
        return new BsonDocument("$where", new BsonString(javaScriptExpression));
    }

    public static <TExpression> Bson expr(TExpression expression) {
        return new SimpleEncodingFilter("$expr", expression);
    }

    public static <TItem> Bson all(String fieldName, TItem... values) {
        return all(fieldName, Arrays.asList(values));
    }

    public static <TItem> Bson all(String fieldName, Iterable<TItem> values) {
        return new IterableOperatorFilter(fieldName, "$all", values);
    }

    public static Bson elemMatch(final String fieldName, final Bson filter) {
        return new Bson() { // from class: com.mongodb.client.model.Filters.1
            @Override // org.bson.conversions.Bson
            public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
                return new BsonDocument(fieldName, new BsonDocument("$elemMatch", filter.toBsonDocument(documentClass, codecRegistry)));
            }
        };
    }

    public static Bson size(String fieldName, int size) {
        return new OperatorFilter("$size", fieldName, Integer.valueOf(size));
    }

    public static Bson bitsAllClear(String fieldName, long bitmask) {
        return new OperatorFilter("$bitsAllClear", fieldName, Long.valueOf(bitmask));
    }

    public static Bson bitsAllSet(String fieldName, long bitmask) {
        return new OperatorFilter("$bitsAllSet", fieldName, Long.valueOf(bitmask));
    }

    public static Bson bitsAnyClear(String fieldName, long bitmask) {
        return new OperatorFilter("$bitsAnyClear", fieldName, Long.valueOf(bitmask));
    }

    public static Bson bitsAnySet(String fieldName, long bitmask) {
        return new OperatorFilter("$bitsAnySet", fieldName, Long.valueOf(bitmask));
    }

    public static Bson geoWithin(String fieldName, Geometry geometry) {
        return new GeometryOperatorFilter("$geoWithin", fieldName, geometry);
    }

    public static Bson geoWithin(String fieldName, Bson geometry) {
        return new GeometryOperatorFilter("$geoWithin", fieldName, geometry);
    }

    public static Bson geoWithinBox(String fieldName, double lowerLeftX, double lowerLeftY, double upperRightX, double upperRightY) {
        BsonDocument box = new BsonDocument("$box", new BsonArray(Arrays.asList(new BsonArray(Arrays.asList(new BsonDouble(lowerLeftX), new BsonDouble(lowerLeftY))), new BsonArray(Arrays.asList(new BsonDouble(upperRightX), new BsonDouble(upperRightY))))));
        return new OperatorFilter("$geoWithin", fieldName, box);
    }

    public static Bson geoWithinPolygon(String fieldName, List<List<Double>> points) {
        BsonArray pointsArray = new BsonArray();
        for (List<Double> point : points) {
            pointsArray.add((BsonValue) new BsonArray(Arrays.asList(new BsonDouble(point.get(0).doubleValue()), new BsonDouble(point.get(1).doubleValue()))));
        }
        BsonDocument polygon = new BsonDocument("$polygon", pointsArray);
        return new OperatorFilter("$geoWithin", fieldName, polygon);
    }

    public static Bson geoWithinCenter(String fieldName, double x, double y, double radius) {
        BsonDocument center = new BsonDocument("$center", new BsonArray(Arrays.asList(new BsonArray(Arrays.asList(new BsonDouble(x), new BsonDouble(y))), new BsonDouble(radius))));
        return new OperatorFilter("$geoWithin", fieldName, center);
    }

    public static Bson geoWithinCenterSphere(String fieldName, double x, double y, double radius) {
        BsonDocument centerSphere = new BsonDocument("$centerSphere", new BsonArray(Arrays.asList(new BsonArray(Arrays.asList(new BsonDouble(x), new BsonDouble(y))), new BsonDouble(radius))));
        return new OperatorFilter("$geoWithin", fieldName, centerSphere);
    }

    public static Bson geoIntersects(String fieldName, Bson geometry) {
        return new GeometryOperatorFilter("$geoIntersects", fieldName, geometry);
    }

    public static Bson geoIntersects(String fieldName, Geometry geometry) {
        return new GeometryOperatorFilter("$geoIntersects", fieldName, geometry);
    }

    public static Bson near(String fieldName, Point geometry, Double maxDistance, Double minDistance) {
        return new GeometryOperatorFilter("$near", fieldName, geometry, maxDistance, minDistance);
    }

    public static Bson near(String fieldName, Bson geometry, Double maxDistance, Double minDistance) {
        return new GeometryOperatorFilter("$near", fieldName, geometry, maxDistance, minDistance);
    }

    public static Bson near(String fieldName, double x, double y, Double maxDistance, Double minDistance) {
        return createNearFilterDocument(fieldName, x, y, maxDistance, minDistance, "$near");
    }

    public static Bson nearSphere(String fieldName, Point geometry, Double maxDistance, Double minDistance) {
        return new GeometryOperatorFilter("$nearSphere", fieldName, geometry, maxDistance, minDistance);
    }

    public static Bson nearSphere(String fieldName, Bson geometry, Double maxDistance, Double minDistance) {
        return new GeometryOperatorFilter("$nearSphere", fieldName, geometry, maxDistance, minDistance);
    }

    public static Bson nearSphere(String fieldName, double x, double y, Double maxDistance, Double minDistance) {
        return createNearFilterDocument(fieldName, x, y, maxDistance, minDistance, "$nearSphere");
    }

    public static Bson jsonSchema(Bson schema) {
        return new SimpleEncodingFilter("$jsonSchema", schema);
    }

    private static Bson createNearFilterDocument(String fieldName, double x, double y, @Nullable Double maxDistance, @Nullable Double minDistance, String operator) {
        BsonDocument nearFilter = new BsonDocument(operator, new BsonArray(Arrays.asList(new BsonDouble(x), new BsonDouble(y))));
        if (maxDistance != null) {
            nearFilter.append("$maxDistance", new BsonDouble(maxDistance.doubleValue()));
        }
        if (minDistance != null) {
            nearFilter.append("$minDistance", new BsonDouble(minDistance.doubleValue()));
        }
        return new BsonDocument(fieldName, nearFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String operatorFilterToString(String fieldName, String operator, Object value) {
        return "Operator Filter{fieldName='" + fieldName + "', operator='" + operator + "', value=" + value + '}';
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Filters$SimpleFilter.class */
    private static final class SimpleFilter implements Bson {
        private final String fieldName;
        private final BsonValue value;

        private SimpleFilter(String fieldName, BsonValue value) {
            this.fieldName = (String) Assertions.notNull("fieldName", fieldName);
            this.value = (BsonValue) Assertions.notNull("value", value);
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            return new BsonDocument(this.fieldName, this.value);
        }

        public String toString() {
            return Filters.operatorFilterToString(this.fieldName, "$eq", this.value);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Filters$OperatorFilter.class */
    private static final class OperatorFilter<TItem> implements Bson {
        private final String operatorName;
        private final String fieldName;
        private final TItem value;

        OperatorFilter(String operatorName, String fieldName, TItem value) {
            this.operatorName = (String) Assertions.notNull("operatorName", operatorName);
            this.fieldName = (String) Assertions.notNull("fieldName", fieldName);
            this.value = value;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeName(this.fieldName);
            writer.writeStartDocument();
            writer.writeName(this.operatorName);
            BuildersHelper.encodeValue(writer, this.value, codecRegistry);
            writer.writeEndDocument();
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return Filters.operatorFilterToString(this.fieldName, this.operatorName, this.value);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Filters$AndFilter.class */
    private static class AndFilter implements Bson {
        private final Iterable<Bson> filters;

        AndFilter(Iterable<Bson> filters) {
            this.filters = (Iterable) Assertions.notNull("filters", filters);
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            BsonDocument andRenderable = new BsonDocument();
            for (Bson filter : this.filters) {
                BsonDocument renderedRenderable = filter.toBsonDocument(documentClass, codecRegistry);
                for (Map.Entry<String, BsonValue> element : renderedRenderable.entrySet()) {
                    addClause(andRenderable, element);
                }
            }
            if (andRenderable.isEmpty()) {
                andRenderable.append("$and", new BsonArray());
            }
            return andRenderable;
        }

        private void addClause(BsonDocument document, Map.Entry<String, BsonValue> clause) {
            if (clause.getKey().equals("$and")) {
                Iterator<BsonValue> it = clause.getValue().asArray().iterator();
                while (it.hasNext()) {
                    BsonValue value = it.next();
                    for (Map.Entry<String, BsonValue> element : value.asDocument().entrySet()) {
                        addClause(document, element);
                    }
                }
                return;
            }
            if (document.size() == 1 && document.keySet().iterator().next().equals("$and")) {
                document.get("$and").asArray().add((BsonValue) new BsonDocument(clause.getKey(), clause.getValue()));
                return;
            }
            if (document.containsKey(clause.getKey())) {
                if (document.get((Object) clause.getKey()).isDocument() && clause.getValue().isDocument()) {
                    BsonDocument existingClauseValue = document.get((Object) clause.getKey()).asDocument();
                    BsonDocument clauseValue = clause.getValue().asDocument();
                    if (keysIntersect(clauseValue, existingClauseValue)) {
                        promoteRenderableToDollarForm(document, clause);
                        return;
                    } else {
                        existingClauseValue.putAll(clauseValue);
                        return;
                    }
                }
                promoteRenderableToDollarForm(document, clause);
                return;
            }
            document.append(clause.getKey(), clause.getValue());
        }

        private boolean keysIntersect(BsonDocument first, BsonDocument second) {
            for (String name : first.keySet()) {
                if (second.containsKey(name)) {
                    return true;
                }
            }
            return false;
        }

        private void promoteRenderableToDollarForm(BsonDocument document, Map.Entry<String, BsonValue> clause) {
            BsonArray clauses = new BsonArray();
            for (Map.Entry<String, BsonValue> queryElement : document.entrySet()) {
                clauses.add((BsonValue) new BsonDocument(queryElement.getKey(), queryElement.getValue()));
            }
            clauses.add((BsonValue) new BsonDocument(clause.getKey(), clause.getValue()));
            document.clear();
            document.put("$and", (BsonValue) clauses);
        }

        public String toString() {
            return "And Filter{filters=" + this.filters + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Filters$OrNorFilter.class */
    private static class OrNorFilter implements Bson {
        private final Operator operator;
        private final Iterable<Bson> filters;

        /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Filters$OrNorFilter$Operator.class */
        private enum Operator {
            OR("$or", "Or"),
            NOR("$nor", "Nor");

            private final String name;
            private final String toStringName;

            Operator(String name, String toStringName) {
                this.name = name;
                this.toStringName = toStringName;
            }
        }

        OrNorFilter(Operator operator, Iterable<Bson> filters) {
            this.operator = (Operator) Assertions.notNull("operator", operator);
            this.filters = (Iterable) Assertions.notNull("filters", filters);
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            BsonDocument orRenderable = new BsonDocument();
            BsonArray filtersArray = new BsonArray();
            for (Bson filter : this.filters) {
                filtersArray.add((BsonValue) filter.toBsonDocument(documentClass, codecRegistry));
            }
            orRenderable.put(this.operator.name, (BsonValue) filtersArray);
            return orRenderable;
        }

        public String toString() {
            return this.operator.toStringName + " Filter{filters=" + this.filters + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Filters$IterableOperatorFilter.class */
    private static class IterableOperatorFilter<TItem> implements Bson {
        private final String fieldName;
        private final String operatorName;
        private final Iterable<TItem> values;

        IterableOperatorFilter(String fieldName, String operatorName, Iterable<TItem> values) {
            this.fieldName = (String) Assertions.notNull("fieldName", fieldName);
            this.operatorName = (String) Assertions.notNull("operatorName", operatorName);
            this.values = (Iterable) Assertions.notNull("values", values);
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeName(this.fieldName);
            writer.writeStartDocument();
            writer.writeName(this.operatorName);
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
            return Filters.operatorFilterToString(this.fieldName, this.operatorName, this.values);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Filters$SimpleEncodingFilter.class */
    private static class SimpleEncodingFilter<TItem> implements Bson {
        private final String fieldName;
        private final TItem value;

        SimpleEncodingFilter(String fieldName, TItem value) {
            this.fieldName = (String) Assertions.notNull("fieldName", fieldName);
            this.value = value;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeName(this.fieldName);
            BuildersHelper.encodeValue(writer, this.value, codecRegistry);
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Filter{fieldName='" + this.fieldName + "', value=" + this.value + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Filters$NotFilter.class */
    private static class NotFilter implements Bson {
        private final Bson filter;

        NotFilter(Bson filter) {
            this.filter = (Bson) Assertions.notNull("filter", filter);
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            BsonDocument filterDocument = this.filter.toBsonDocument(documentClass, codecRegistry);
            if (filterDocument.size() == 1) {
                Map.Entry<String, BsonValue> entry = filterDocument.entrySet().iterator().next();
                return createFilter(entry.getKey(), entry.getValue());
            }
            BsonArray values = new BsonArray();
            for (Map.Entry<String, BsonValue> docs : filterDocument.entrySet()) {
                values.add((BsonValue) new BsonDocument(docs.getKey(), docs.getValue()));
            }
            return createFilter("$and", values);
        }

        private BsonDocument createFilter(String fieldName, BsonValue value) {
            if (fieldName.startsWith(PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX)) {
                return new BsonDocument("$not", new BsonDocument(fieldName, value));
            }
            if (value.isDocument() || value.isRegularExpression()) {
                return new BsonDocument(fieldName, new BsonDocument("$not", value));
            }
            return new BsonDocument(fieldName, new BsonDocument("$not", new BsonDocument("$eq", value)));
        }

        public String toString() {
            return "Not Filter{filter=" + this.filter + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Filters$GeometryOperatorFilter.class */
    private static class GeometryOperatorFilter<TItem> implements Bson {
        private final String operatorName;
        private final String fieldName;
        private final TItem geometry;
        private final Double maxDistance;
        private final Double minDistance;

        GeometryOperatorFilter(String operatorName, String fieldName, TItem geometry) {
            this(operatorName, fieldName, geometry, null, null);
        }

        GeometryOperatorFilter(String str, String str2, TItem titem, @Nullable Double d, @Nullable Double d2) {
            this.operatorName = str;
            this.fieldName = (String) Assertions.notNull("fieldName", str2);
            this.geometry = (TItem) Assertions.notNull("geometry", titem);
            this.maxDistance = d;
            this.minDistance = d2;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeName(this.fieldName);
            writer.writeStartDocument();
            writer.writeName(this.operatorName);
            writer.writeStartDocument();
            writer.writeName("$geometry");
            BuildersHelper.encodeValue(writer, this.geometry, codecRegistry);
            if (this.maxDistance != null) {
                writer.writeDouble("$maxDistance", this.maxDistance.doubleValue());
            }
            if (this.minDistance != null) {
                writer.writeDouble("$minDistance", this.minDistance.doubleValue());
            }
            writer.writeEndDocument();
            writer.writeEndDocument();
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Geometry Operator Filter{fieldName='" + this.fieldName + "', operator='" + this.operatorName + "', geometry=" + this.geometry + ", maxDistance=" + this.maxDistance + ", minDistance=" + this.minDistance + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Filters$TextFilter.class */
    private static class TextFilter implements Bson {
        private final String search;
        private final TextSearchOptions textSearchOptions;

        TextFilter(String search, TextSearchOptions textSearchOptions) {
            this.search = search;
            this.textSearchOptions = textSearchOptions;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            BsonDocument searchDocument = new BsonDocument("$search", new BsonString(this.search));
            String language = this.textSearchOptions.getLanguage();
            if (language != null) {
                searchDocument.put("$language", (BsonValue) new BsonString(language));
            }
            Boolean caseSensitive = this.textSearchOptions.getCaseSensitive();
            if (caseSensitive != null) {
                searchDocument.put("$caseSensitive", (BsonValue) BsonBoolean.valueOf(caseSensitive.booleanValue()));
            }
            Boolean diacriticSensitive = this.textSearchOptions.getDiacriticSensitive();
            if (diacriticSensitive != null) {
                searchDocument.put("$diacriticSensitive", (BsonValue) BsonBoolean.valueOf(diacriticSensitive.booleanValue()));
            }
            return new BsonDocument("$text", searchDocument);
        }

        public String toString() {
            return "Text Filter{search='" + this.search + "', textSearchOptions=" + this.textSearchOptions + '}';
        }
    }
}
