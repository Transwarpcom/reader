package com.mongodb.client.model;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Accumulators.class */
public final class Accumulators {
    public static <TExpression> BsonField sum(String fieldName, TExpression expression) {
        return accumulator("$sum", fieldName, expression);
    }

    public static <TExpression> BsonField avg(String fieldName, TExpression expression) {
        return accumulator("$avg", fieldName, expression);
    }

    public static <TExpression> BsonField first(String fieldName, TExpression expression) {
        return accumulator("$first", fieldName, expression);
    }

    public static <TExpression> BsonField last(String fieldName, TExpression expression) {
        return accumulator("$last", fieldName, expression);
    }

    public static <TExpression> BsonField max(String fieldName, TExpression expression) {
        return accumulator("$max", fieldName, expression);
    }

    public static <TExpression> BsonField min(String fieldName, TExpression expression) {
        return accumulator("$min", fieldName, expression);
    }

    public static <TExpression> BsonField push(String fieldName, TExpression expression) {
        return accumulator("$push", fieldName, expression);
    }

    public static <TExpression> BsonField addToSet(String fieldName, TExpression expression) {
        return accumulator("$addToSet", fieldName, expression);
    }

    public static <TExpression> BsonField stdDevPop(String fieldName, TExpression expression) {
        return accumulator("$stdDevPop", fieldName, expression);
    }

    public static <TExpression> BsonField stdDevSamp(String fieldName, TExpression expression) {
        return accumulator("$stdDevSamp", fieldName, expression);
    }

    private static <TExpression> BsonField accumulator(String name, String fieldName, TExpression expression) {
        return new BsonField(fieldName, new SimpleExpression(name, expression));
    }

    private Accumulators() {
    }
}
