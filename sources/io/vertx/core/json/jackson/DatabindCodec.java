package io.vertx.core.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.Json;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/jackson/DatabindCodec.class */
public class DatabindCodec {
    public static ObjectMapper mapper() {
        return Json.mapper;
    }

    public static ObjectMapper prettyMapper() {
        return Json.prettyMapper;
    }
}
