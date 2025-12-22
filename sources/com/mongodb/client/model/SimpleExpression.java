package com.mongodb.client.model;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWriter;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/SimpleExpression.class */
class SimpleExpression<TExpression> implements Bson {
    private final String name;
    private final TExpression expression;

    SimpleExpression(String name, TExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override // org.bson.conversions.Bson
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
        BsonDocumentWriter writer = new BsonDocumentWriter(new BsonDocument());
        writer.writeStartDocument();
        writer.writeName(this.name);
        BuildersHelper.encodeValue(writer, this.expression, codecRegistry);
        writer.writeEndDocument();
        return writer.getDocument();
    }

    public String toString() {
        return "Expression{name='" + this.name + "', expression=" + this.expression + '}';
    }
}
