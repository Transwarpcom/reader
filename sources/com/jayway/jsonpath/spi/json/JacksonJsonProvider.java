package com.jayway.jsonpath.spi.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jayway.jsonpath.InvalidJsonException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/JacksonJsonProvider.class */
public class JacksonJsonProvider extends AbstractJsonProvider {
    private static final ObjectMapper defaultObjectMapper = new ObjectMapper();
    private static final ObjectReader defaultObjectReader = defaultObjectMapper.reader().forType(Object.class);
    protected ObjectMapper objectMapper;
    protected ObjectReader objectReader;

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    public JacksonJsonProvider() {
        this(defaultObjectMapper, defaultObjectReader);
    }

    public JacksonJsonProvider(ObjectMapper objectMapper) {
        this(objectMapper, objectMapper.reader().forType(Object.class));
    }

    public JacksonJsonProvider(ObjectMapper objectMapper, ObjectReader objectReader) {
        this.objectMapper = objectMapper;
        this.objectReader = objectReader;
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(String json) throws InvalidJsonException {
        try {
            return this.objectReader.readValue(json);
        } catch (IOException e) {
            throw new InvalidJsonException(e, json);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(InputStream jsonStream, String charset) throws InvalidJsonException {
        try {
            return this.objectReader.readValue(new InputStreamReader(jsonStream, charset));
        } catch (IOException e) {
            throw new InvalidJsonException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public String toJson(Object obj) throws IOException {
        StringWriter writer = new StringWriter();
        try {
            JsonGenerator generator = this.objectMapper.getFactory().createGenerator(writer);
            this.objectMapper.writeValue(generator, obj);
            writer.flush();
            writer.close();
            generator.close();
            return writer.getBuffer().toString();
        } catch (IOException e) {
            throw new InvalidJsonException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public List<Object> createArray() {
        return new LinkedList();
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object createMap() {
        return new LinkedHashMap();
    }
}
