package com.jayway.jsonpath.spi.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.jayway.jsonpath.InvalidJsonException;
import com.jayway.jsonpath.JsonPathException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/JacksonJsonNodeJsonProvider.class */
public class JacksonJsonNodeJsonProvider extends AbstractJsonProvider {
    private static final ObjectMapper defaultObjectMapper = new ObjectMapper();
    protected ObjectMapper objectMapper;

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    public JacksonJsonNodeJsonProvider() {
        this(defaultObjectMapper);
    }

    public JacksonJsonNodeJsonProvider(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(String json) throws InvalidJsonException {
        try {
            return this.objectMapper.readTree(json);
        } catch (IOException e) {
            throw new InvalidJsonException(e, json);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(InputStream jsonStream, String charset) throws InvalidJsonException {
        try {
            return this.objectMapper.readTree(new InputStreamReader(jsonStream, charset));
        } catch (IOException e) {
            throw new InvalidJsonException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public String toJson(Object obj) {
        if (!(obj instanceof JsonNode)) {
            throw new JsonPathException("Not a JSON Node");
        }
        return obj.toString();
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object createArray() {
        return JsonNodeFactory.instance.arrayNode();
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object createMap() {
        return JsonNodeFactory.instance.objectNode();
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object unwrap(Object o) {
        if (o == null) {
            return null;
        }
        if (!(o instanceof JsonNode)) {
            return o;
        }
        JsonNode e = (JsonNode) o;
        if (e.isValueNode()) {
            if (e.isTextual()) {
                return e.asText();
            }
            if (e.isBoolean()) {
                return Boolean.valueOf(e.asBoolean());
            }
            if (e.isInt()) {
                return Integer.valueOf(e.asInt());
            }
            if (e.isLong()) {
                return Long.valueOf(e.asLong());
            }
            if (e.isBigInteger()) {
                return e.bigIntegerValue();
            }
            if (e.isDouble()) {
                return Double.valueOf(e.doubleValue());
            }
            if (e.isFloat()) {
                return Float.valueOf(e.floatValue());
            }
            if (e.isBigDecimal()) {
                return e.decimalValue();
            }
            if (e.isNull()) {
                return null;
            }
        }
        return o;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isArray(Object obj) {
        return (obj instanceof ArrayNode) || (obj instanceof List);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object getArrayIndex(Object obj, int idx) {
        return toJsonArray(obj).get(idx);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void setArrayIndex(Object array, int index, Object newValue) {
        if (!isArray(array)) {
            throw new UnsupportedOperationException();
        }
        ArrayNode arrayNode = toJsonArray(array);
        if (index == arrayNode.size()) {
            arrayNode.add(createJsonElement(newValue));
        } else {
            arrayNode.set(index, createJsonElement(newValue));
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object getMapValue(Object obj, String key) {
        ObjectNode jsonObject = toJsonObject(obj);
        Object o = jsonObject.get(key);
        if (!jsonObject.has(key)) {
            return UNDEFINED;
        }
        return unwrap(o);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void setProperty(Object obj, Object key, Object value) {
        int index;
        if (isMap(obj)) {
            setValueInObjectNode((ObjectNode) obj, key, value);
            return;
        }
        ArrayNode array = (ArrayNode) obj;
        if (key != null) {
            index = key instanceof Integer ? ((Integer) key).intValue() : Integer.parseInt(key.toString());
        } else {
            index = array.size();
        }
        if (index == array.size()) {
            array.add(createJsonElement(value));
        } else {
            array.set(index, createJsonElement(value));
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void removeProperty(Object obj, Object key) {
        if (isMap(obj)) {
            toJsonObject(obj).remove(key.toString());
            return;
        }
        ArrayNode array = toJsonArray(obj);
        int index = key instanceof Integer ? ((Integer) key).intValue() : Integer.parseInt(key.toString());
        array.remove(index);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isMap(Object obj) {
        return obj instanceof ObjectNode;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Collection<String> getPropertyKeys(Object obj) {
        List<String> keys = new ArrayList<>();
        Iterator<String> iter = toJsonObject(obj).fieldNames();
        while (iter.hasNext()) {
            keys.add(iter.next());
        }
        return keys;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public int length(Object obj) {
        if (isArray(obj)) {
            return toJsonArray(obj).size();
        }
        if (isMap(obj)) {
            return toJsonObject(obj).size();
        }
        if (obj instanceof TextNode) {
            TextNode element = (TextNode) obj;
            return element.size();
        }
        throw new JsonPathException("length operation can not applied to " + (obj != null ? obj.getClass().getName() : "null"));
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Iterable<?> toIterable(Object obj) {
        ArrayNode arr = toJsonArray(obj);
        List<Object> values = new ArrayList<>(arr.size());
        Iterator<JsonNode> it = arr.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            values.add(unwrap(o));
        }
        return values;
    }

    private JsonNode createJsonElement(Object o) {
        if (o != null) {
            if (o instanceof JsonNode) {
                return (JsonNode) o;
            }
            return this.objectMapper.valueToTree(o);
        }
        return null;
    }

    private ArrayNode toJsonArray(Object o) {
        return (ArrayNode) o;
    }

    private ObjectNode toJsonObject(Object o) {
        return (ObjectNode) o;
    }

    private void setValueInObjectNode(ObjectNode objectNode, Object key, Object value) {
        if (value instanceof JsonNode) {
            objectNode.set(key.toString(), (JsonNode) value);
            return;
        }
        if (value instanceof String) {
            objectNode.put(key.toString(), (String) value);
            return;
        }
        if (value instanceof Integer) {
            objectNode.put(key.toString(), (Integer) value);
            return;
        }
        if (value instanceof Long) {
            objectNode.put(key.toString(), (Long) value);
            return;
        }
        if (value instanceof Short) {
            objectNode.put(key.toString(), (Short) value);
            return;
        }
        if (value instanceof BigInteger) {
            objectNode.put(key.toString(), (BigInteger) value);
            return;
        }
        if (value instanceof Double) {
            objectNode.put(key.toString(), (Double) value);
            return;
        }
        if (value instanceof Float) {
            objectNode.put(key.toString(), (Float) value);
            return;
        }
        if (value instanceof BigDecimal) {
            objectNode.put(key.toString(), (BigDecimal) value);
            return;
        }
        if (value instanceof Boolean) {
            objectNode.put(key.toString(), (Boolean) value);
            return;
        }
        if (value instanceof byte[]) {
            objectNode.put(key.toString(), (byte[]) value);
        } else if (value == null) {
            objectNode.set(key.toString(), null);
        } else {
            objectNode.set(key.toString(), createJsonElement(value));
        }
    }
}
