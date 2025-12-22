package com.mongodb.client.model;

import java.util.Arrays;
import java.util.List;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Facet.class */
public class Facet {
    private final String name;
    private final List<? extends Bson> pipeline;

    public Facet(String name, List<? extends Bson> pipeline) {
        this.name = name;
        this.pipeline = pipeline;
    }

    public Facet(String name, Bson... pipeline) {
        this(name, (List<? extends Bson>) Arrays.asList(pipeline));
    }

    public String getName() {
        return this.name;
    }

    public List<? extends Bson> getPipeline() {
        return this.pipeline;
    }

    public String toString() {
        return "Facet{name='" + this.name + "', pipeline=" + this.pipeline + '}';
    }
}
