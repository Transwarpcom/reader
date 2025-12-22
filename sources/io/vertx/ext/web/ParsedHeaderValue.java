package io.vertx.ext.web;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import java.util.Collection;
import java.util.Map;

@VertxGen(concrete = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/ParsedHeaderValue.class */
public interface ParsedHeaderValue {
    public static final float DEFAULT_WEIGHT = 1.0f;

    String rawValue();

    String value();

    float weight();

    String parameter(String str);

    Map<String, String> parameters();

    boolean isPermitted();

    boolean isMatchedBy(ParsedHeaderValue parsedHeaderValue);

    @GenIgnore
    <T extends ParsedHeaderValue> T findMatchedBy(Collection<T> collection);

    int weightedOrder();
}
