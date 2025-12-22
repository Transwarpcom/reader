package net.minidev.json.writer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONAwareEx;
import net.minidev.json.JSONObject;
import net.minidev.json.writer.ArraysMapper;
import net.minidev.json.writer.BeansMapper;
import net.minidev.json.writer.CollectionMapper;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/writer/JsonReader.class */
public class JsonReader {
    private final ConcurrentHashMap<Type, JsonReaderI<?>> cache = new ConcurrentHashMap<>(100);
    public JsonReaderI<JSONAwareEx> DEFAULT;
    public JsonReaderI<JSONAwareEx> DEFAULT_ORDERED;

    public JsonReader() {
        this.cache.put(Date.class, BeansMapper.MAPPER_DATE);
        this.cache.put(int[].class, ArraysMapper.MAPPER_PRIM_INT);
        this.cache.put(Integer[].class, ArraysMapper.MAPPER_INT);
        this.cache.put(short[].class, ArraysMapper.MAPPER_PRIM_INT);
        this.cache.put(Short[].class, ArraysMapper.MAPPER_INT);
        this.cache.put(long[].class, ArraysMapper.MAPPER_PRIM_LONG);
        this.cache.put(Long[].class, ArraysMapper.MAPPER_LONG);
        this.cache.put(byte[].class, ArraysMapper.MAPPER_PRIM_BYTE);
        this.cache.put(Byte[].class, ArraysMapper.MAPPER_BYTE);
        this.cache.put(char[].class, ArraysMapper.MAPPER_PRIM_CHAR);
        this.cache.put(Character[].class, ArraysMapper.MAPPER_CHAR);
        this.cache.put(float[].class, ArraysMapper.MAPPER_PRIM_FLOAT);
        this.cache.put(Float[].class, ArraysMapper.MAPPER_FLOAT);
        this.cache.put(double[].class, ArraysMapper.MAPPER_PRIM_DOUBLE);
        this.cache.put(Double[].class, ArraysMapper.MAPPER_DOUBLE);
        this.cache.put(boolean[].class, ArraysMapper.MAPPER_PRIM_BOOL);
        this.cache.put(Boolean[].class, ArraysMapper.MAPPER_BOOL);
        this.DEFAULT = new DefaultMapper(this);
        this.DEFAULT_ORDERED = new DefaultMapperOrdered(this);
        this.cache.put(JSONAwareEx.class, this.DEFAULT);
        this.cache.put(JSONAware.class, this.DEFAULT);
        this.cache.put(JSONArray.class, this.DEFAULT);
        this.cache.put(JSONObject.class, this.DEFAULT);
    }

    public <T> void remapField(Class<T> type, String fromJson, String toJava) {
        JsonReaderI<T> map = getMapper((Class) type);
        if (!(map instanceof MapperRemapped)) {
            map = new MapperRemapped(map);
            registerReader(type, map);
        }
        ((MapperRemapped) map).renameField(fromJson, toJava);
    }

    public <T> void registerReader(Class<T> type, JsonReaderI<T> mapper) {
        this.cache.put(type, mapper);
    }

    public <T> JsonReaderI<T> getMapper(Type type) {
        if (type instanceof ParameterizedType) {
            return getMapper((ParameterizedType) type);
        }
        return getMapper((Class) type);
    }

    public <T> JsonReaderI<T> getMapper(Class<T> cls) {
        JsonReaderI bean;
        DefaultMapperCollection defaultMapperCollection = (JsonReaderI<T>) this.cache.get(cls);
        if (defaultMapperCollection != null) {
            return defaultMapperCollection;
        }
        if (cls instanceof Class) {
            if (Map.class.isAssignableFrom(cls) || List.class.isAssignableFrom(cls)) {
                defaultMapperCollection = new DefaultMapperCollection(this, cls);
            }
            if (defaultMapperCollection != null) {
                this.cache.put(cls, defaultMapperCollection);
                return defaultMapperCollection;
            }
        }
        if (cls.isArray()) {
            bean = new ArraysMapper.GenericMapper(this, cls);
        } else if (List.class.isAssignableFrom(cls)) {
            bean = new CollectionMapper.ListClass(this, cls);
        } else if (Map.class.isAssignableFrom(cls)) {
            bean = new CollectionMapper.MapClass(this, cls);
        } else {
            bean = new BeansMapper.Bean(this, cls);
        }
        this.cache.putIfAbsent(cls, bean);
        return bean;
    }

    public <T> JsonReaderI<T> getMapper(ParameterizedType parameterizedType) {
        CollectionMapper.MapType mapType = (JsonReaderI<T>) this.cache.get(parameterizedType);
        if (mapType != null) {
            return mapType;
        }
        Class cls = (Class) parameterizedType.getRawType();
        if (List.class.isAssignableFrom(cls)) {
            mapType = new CollectionMapper.ListType(this, parameterizedType);
        } else if (Map.class.isAssignableFrom(cls)) {
            mapType = new CollectionMapper.MapType(this, parameterizedType);
        }
        this.cache.putIfAbsent(parameterizedType, mapType);
        return mapType;
    }
}
