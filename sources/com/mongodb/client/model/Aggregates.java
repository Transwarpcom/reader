package com.mongodb.client.model;

import ch.qos.logback.classic.joran.action.InsertFromJNDIAction;
import com.mongodb.lang.Nullable;
import io.vertx.core.cli.converters.FromBasedConverter;
import java.util.Arrays;
import java.util.List;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWriter;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.assertions.Assertions;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Aggregates.class */
public final class Aggregates {
    public static Bson addFields(Field<?>... fields) {
        return addFields((List<Field<?>>) Arrays.asList(fields));
    }

    public static Bson addFields(List<Field<?>> fields) {
        return new AddFieldsStage(fields);
    }

    public static <TExpression, Boundary> Bson bucket(TExpression groupBy, List<Boundary> boundaries) {
        return bucket(groupBy, boundaries, new BucketOptions());
    }

    public static <TExpression, TBoundary> Bson bucket(TExpression groupBy, List<TBoundary> boundaries, BucketOptions options) {
        return new BucketStage(groupBy, boundaries, options);
    }

    public static <TExpression> Bson bucketAuto(TExpression groupBy, int buckets) {
        return bucketAuto(groupBy, buckets, new BucketAutoOptions());
    }

    public static <TExpression> Bson bucketAuto(TExpression groupBy, int buckets, BucketAutoOptions options) {
        return new BucketAutoStage(groupBy, buckets, options);
    }

    public static Bson count() {
        return count("count");
    }

    public static Bson count(String field) {
        return new BsonDocument("$count", new BsonString(field));
    }

    public static Bson match(Bson filter) {
        return new SimplePipelineStage("$match", filter);
    }

    public static Bson project(Bson projection) {
        return new SimplePipelineStage("$project", projection);
    }

    public static Bson sort(Bson sort) {
        return new SimplePipelineStage("$sort", sort);
    }

    public static <TExpression> Bson sortByCount(TExpression filter) {
        return new SortByCountStage(filter);
    }

    public static Bson skip(int skip) {
        return new BsonDocument("$skip", new BsonInt32(skip));
    }

    public static Bson limit(int limit) {
        return new BsonDocument("$limit", new BsonInt32(limit));
    }

    public static Bson lookup(String from, String localField, String foreignField, String as) {
        return new BsonDocument("$lookup", new BsonDocument(FromBasedConverter.FROM, new BsonString(from)).append("localField", new BsonString(localField)).append("foreignField", new BsonString(foreignField)).append(InsertFromJNDIAction.AS_ATTR, new BsonString(as)));
    }

    public static Bson lookup(String from, List<? extends Bson> pipeline, String as) {
        return lookup(from, (List<Variable<?>>) null, pipeline, as);
    }

    public static Bson lookup(String from, @Nullable List<Variable<?>> let, List<? extends Bson> pipeline, String as) {
        return new LookupStage(from, let, pipeline, as);
    }

    public static Bson facet(List<Facet> facets) {
        return new FacetStage(facets);
    }

    public static Bson facet(Facet... facets) {
        return new FacetStage(Arrays.asList(facets));
    }

    public static <TExpression> Bson graphLookup(String from, TExpression startWith, String connectFromField, String connectToField, String as) {
        return graphLookup(from, startWith, connectFromField, connectToField, as, new GraphLookupOptions());
    }

    public static <TExpression> Bson graphLookup(String from, TExpression startWith, String connectFromField, String connectToField, String as, GraphLookupOptions options) {
        Assertions.notNull("options", options);
        return new GraphLookupStage(from, startWith, connectFromField, connectToField, as, options);
    }

    public static <TExpression> Bson group(@Nullable TExpression id, BsonField... fieldAccumulators) {
        return group(id, (List<BsonField>) Arrays.asList(fieldAccumulators));
    }

    public static <TExpression> Bson group(@Nullable TExpression id, List<BsonField> fieldAccumulators) {
        return new GroupStage(id, fieldAccumulators);
    }

    public static Bson unwind(String fieldName) {
        return new BsonDocument("$unwind", new BsonString(fieldName));
    }

    public static Bson unwind(String fieldName, UnwindOptions unwindOptions) {
        Assertions.notNull("unwindOptions", unwindOptions);
        BsonDocument options = new BsonDocument("path", new BsonString(fieldName));
        Boolean preserveNullAndEmptyArrays = unwindOptions.isPreserveNullAndEmptyArrays();
        if (preserveNullAndEmptyArrays != null) {
            options.append("preserveNullAndEmptyArrays", BsonBoolean.valueOf(preserveNullAndEmptyArrays.booleanValue()));
        }
        String includeArrayIndex = unwindOptions.getIncludeArrayIndex();
        if (includeArrayIndex != null) {
            options.append("includeArrayIndex", new BsonString(includeArrayIndex));
        }
        return new BsonDocument("$unwind", options);
    }

    public static Bson out(String collectionName) {
        return new BsonDocument("$out", new BsonString(collectionName));
    }

    public static <TExpression> Bson replaceRoot(TExpression value) {
        return new ReplaceRootStage(value);
    }

    public static Bson sample(int size) {
        return new BsonDocument("$sample", new BsonDocument("size", new BsonInt32(size)));
    }

    static void writeBucketOutput(CodecRegistry codecRegistry, BsonDocumentWriter writer, @Nullable List<BsonField> output) {
        if (output != null) {
            writer.writeName("output");
            writer.writeStartDocument();
            for (BsonField field : output) {
                writer.writeName(field.getName());
                BuildersHelper.encodeValue(writer, field.getValue(), codecRegistry);
            }
            writer.writeEndDocument();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Aggregates$SimplePipelineStage.class */
    private static class SimplePipelineStage implements Bson {
        private final String name;
        private final Bson value;

        SimplePipelineStage(String name, Bson value) {
            this.name = name;
            this.value = value;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
            return new BsonDocument(this.name, this.value.toBsonDocument(documentClass, codecRegistry));
        }

        public String toString() {
            return "Stage{name='" + this.name + "', value=" + this.value + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Aggregates$BucketStage.class */
    private static final class BucketStage<TExpression, TBoundary> implements Bson {
        private final TExpression groupBy;
        private final List<TBoundary> boundaries;
        private final BucketOptions options;

        BucketStage(TExpression groupBy, List<TBoundary> boundaries, BucketOptions options) {
            Assertions.notNull("options", options);
            this.groupBy = groupBy;
            this.boundaries = boundaries;
            this.options = options;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeStartDocument("$bucket");
            writer.writeName("groupBy");
            BuildersHelper.encodeValue(writer, this.groupBy, codecRegistry);
            writer.writeStartArray("boundaries");
            for (TBoundary boundary : this.boundaries) {
                BuildersHelper.encodeValue(writer, boundary, codecRegistry);
            }
            writer.writeEndArray();
            Object defaultBucket = this.options.getDefaultBucket();
            if (defaultBucket != null) {
                writer.writeName("default");
                BuildersHelper.encodeValue(writer, defaultBucket, codecRegistry);
            }
            Aggregates.writeBucketOutput(codecRegistry, writer, this.options.getOutput());
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Stage{name='$bucket', boundaries=" + this.boundaries + ", groupBy=" + this.groupBy + ", options=" + this.options + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Aggregates$BucketAutoStage.class */
    private static final class BucketAutoStage<TExpression> implements Bson {
        private final TExpression groupBy;
        private final int buckets;
        private final BucketAutoOptions options;

        BucketAutoStage(TExpression groupBy, int buckets, BucketAutoOptions options) {
            Assertions.notNull("options", options);
            this.groupBy = groupBy;
            this.buckets = buckets;
            this.options = options;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeStartDocument("$bucketAuto");
            writer.writeName("groupBy");
            BuildersHelper.encodeValue(writer, this.groupBy, codecRegistry);
            writer.writeInt32("buckets", this.buckets);
            Aggregates.writeBucketOutput(codecRegistry, writer, this.options.getOutput());
            BucketGranularity granularity = this.options.getGranularity();
            if (granularity != null) {
                writer.writeString("granularity", granularity.getValue());
            }
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Stage{name='$bucketAuto', buckets=" + this.buckets + ", groupBy=" + this.groupBy + ", options=" + this.options + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Aggregates$LookupStage.class */
    private static final class LookupStage implements Bson {
        private final String from;
        private final List<Variable<?>> let;
        private final List<? extends Bson> pipeline;
        private final String as;

        private LookupStage(String from, @Nullable List<Variable<?>> let, List<? extends Bson> pipeline, String as) {
            this.from = from;
            this.let = let;
            this.pipeline = pipeline;
            this.as = as;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeStartDocument("$lookup");
            writer.writeString(FromBasedConverter.FROM, this.from);
            if (this.let != null) {
                writer.writeStartDocument("let");
                for (Variable<?> variable : this.let) {
                    writer.writeName(variable.getName());
                    BuildersHelper.encodeValue(writer, variable.getValue(), codecRegistry);
                }
                writer.writeEndDocument();
            }
            writer.writeName("pipeline");
            writer.writeStartArray();
            for (Bson stage : this.pipeline) {
                BuildersHelper.encodeValue(writer, stage, codecRegistry);
            }
            writer.writeEndArray();
            writer.writeString(InsertFromJNDIAction.AS_ATTR, this.as);
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Stage{name='$lookup', from='" + this.from + "', let=" + this.let + ", pipeline=" + this.pipeline + ", as='" + this.as + "'}";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Aggregates$GraphLookupStage.class */
    private static final class GraphLookupStage<TExpression> implements Bson {
        private final String from;
        private final TExpression startWith;
        private final String connectFromField;
        private final String connectToField;
        private final String as;
        private final GraphLookupOptions options;

        private GraphLookupStage(String from, TExpression startWith, String connectFromField, String connectToField, String as, GraphLookupOptions options) {
            this.from = from;
            this.startWith = startWith;
            this.connectFromField = connectFromField;
            this.connectToField = connectToField;
            this.as = as;
            this.options = options;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeStartDocument("$graphLookup");
            writer.writeString(FromBasedConverter.FROM, this.from);
            writer.writeName("startWith");
            BuildersHelper.encodeValue(writer, this.startWith, codecRegistry);
            writer.writeString("connectFromField", this.connectFromField);
            writer.writeString("connectToField", this.connectToField);
            writer.writeString(InsertFromJNDIAction.AS_ATTR, this.as);
            Integer maxDepth = this.options.getMaxDepth();
            if (maxDepth != null) {
                writer.writeInt32("maxDepth", maxDepth.intValue());
            }
            String depthField = this.options.getDepthField();
            if (depthField != null) {
                writer.writeString("depthField", depthField);
            }
            Bson restrictSearchWithMatch = this.options.getRestrictSearchWithMatch();
            if (restrictSearchWithMatch != null) {
                writer.writeName("restrictSearchWithMatch");
                BuildersHelper.encodeValue(writer, restrictSearchWithMatch, codecRegistry);
            }
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Stage{name='$graphLookup', as='" + this.as + "', connectFromField='" + this.connectFromField + "', connectToField='" + this.connectToField + "', from='" + this.from + "', options=" + this.options + ", startWith=" + this.startWith + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Aggregates$GroupStage.class */
    private static class GroupStage<TExpression> implements Bson {
        private final TExpression id;
        private final List<BsonField> fieldAccumulators;

        GroupStage(TExpression id, List<BsonField> fieldAccumulators) {
            this.id = id;
            this.fieldAccumulators = fieldAccumulators;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeStartDocument("$group");
            writer.writeName("_id");
            BuildersHelper.encodeValue(writer, this.id, codecRegistry);
            for (BsonField fieldAccumulator : this.fieldAccumulators) {
                writer.writeName(fieldAccumulator.getName());
                BuildersHelper.encodeValue(writer, fieldAccumulator.getValue(), codecRegistry);
            }
            writer.writeEndDocument();
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Stage{name='$group', id=" + this.id + ", fieldAccumulators=" + this.fieldAccumulators + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Aggregates$SortByCountStage.class */
    private static class SortByCountStage<TExpression> implements Bson {
        private final TExpression filter;

        SortByCountStage(TExpression filter) {
            this.filter = filter;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeName("$sortByCount");
            BuildersHelper.encodeValue(writer, this.filter, codecRegistry);
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Stage{name='$sortByCount', id=" + this.filter + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Aggregates$FacetStage.class */
    private static class FacetStage implements Bson {
        private final List<Facet> facets;

        FacetStage(List<Facet> facets) {
            this.facets = facets;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeName("$facet");
            writer.writeStartDocument();
            for (Facet facet : this.facets) {
                writer.writeName(facet.getName());
                writer.writeStartArray();
                for (Bson bson : facet.getPipeline()) {
                    BuildersHelper.encodeValue(writer, bson, codecRegistry);
                }
                writer.writeEndArray();
            }
            writer.writeEndDocument();
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Stage{name='$facet', facets=" + this.facets + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Aggregates$AddFieldsStage.class */
    private static class AddFieldsStage implements Bson {
        private final List<Field<?>> fields;

        AddFieldsStage(List<Field<?>> fields) {
            this.fields = fields;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeName("$addFields");
            writer.writeStartDocument();
            for (Field<?> field : this.fields) {
                writer.writeName(field.getName());
                BuildersHelper.encodeValue(writer, field.getValue(), codecRegistry);
            }
            writer.writeEndDocument();
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Stage{name='$addFields', fields=" + this.fields + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Aggregates$ReplaceRootStage.class */
    private static class ReplaceRootStage<TExpression> implements Bson {
        private final TExpression value;

        ReplaceRootStage(TExpression value) {
            this.value = value;
        }

        @Override // org.bson.conversions.Bson
        public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
            BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
            writer.writeStartDocument();
            writer.writeName("$replaceRoot");
            writer.writeStartDocument();
            writer.writeName("newRoot");
            BuildersHelper.encodeValue(writer, this.value, codecRegistry);
            writer.writeEndDocument();
            writer.writeEndDocument();
            return writer.getDocument();
        }

        public String toString() {
            return "Stage{name='$replaceRoot', value=" + this.value + '}';
        }
    }

    private Aggregates() {
    }
}
