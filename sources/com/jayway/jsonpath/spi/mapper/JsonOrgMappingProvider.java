package com.jayway.jsonpath.spi.mapper;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.TypeRef;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JsonOrgMappingProvider.class */
public class JsonOrgMappingProvider implements MappingProvider {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jayway.jsonpath.spi.mapper.MappingProvider
    public <T> T map(Object obj, Class<T> cls, Configuration configuration) {
        if (obj == 0) {
            return null;
        }
        if (cls.equals(Object.class) || cls.equals(List.class) || cls.equals(Map.class)) {
            return (T) mapToObject(obj);
        }
        return obj;
    }

    @Override // com.jayway.jsonpath.spi.mapper.MappingProvider
    public <T> T map(Object source, TypeRef<T> targetType, Configuration configuration) {
        throw new UnsupportedOperationException("JsonOrg provider does not support TypeRef! Use a Jackson or Gson based provider");
    }

    private Object mapToObject(Object source) {
        if (source instanceof JSONArray) {
            List<Object> mapped = new ArrayList<>();
            JSONArray array = (JSONArray) source;
            for (int i = 0; i < array.length(); i++) {
                mapped.add(mapToObject(array.get(i)));
            }
            return mapped;
        }
        if (source instanceof JSONObject) {
            Map<String, Object> mapped2 = new HashMap<>();
            JSONObject obj = (JSONObject) source;
            for (Object o : obj.keySet()) {
                String key = o.toString();
                mapped2.put(key, mapToObject(obj.get(key)));
            }
            return mapped2;
        }
        if (source == JSONObject.NULL) {
            return null;
        }
        return source;
    }
}
