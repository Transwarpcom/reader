package com.mongodb;

import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoCompressor.class */
public final class MongoCompressor {
    public static final String LEVEL = "LEVEL";
    private final String name;
    private final Map<String, Object> properties;

    public static MongoCompressor createSnappyCompressor() {
        return new MongoCompressor("snappy");
    }

    public static MongoCompressor createZlibCompressor() {
        return new MongoCompressor("zlib");
    }

    public String getName() {
        return this.name;
    }

    @Nullable
    public <T> T getProperty(String str, T t) {
        Assertions.notNull("key", str);
        T t2 = (T) this.properties.get(str.toLowerCase());
        return (t2 != null || this.properties.containsKey(str)) ? t2 : t;
    }

    public <T> T getPropertyNonNull(String str, T t) {
        T t2 = (T) getProperty(str, t);
        if (t2 == null) {
            throw new IllegalArgumentException();
        }
        return t2;
    }

    public <T> MongoCompressor withProperty(String key, T value) {
        return new MongoCompressor(this, key, value);
    }

    private MongoCompressor(String name) {
        this.name = name;
        this.properties = Collections.emptyMap();
    }

    private <T> MongoCompressor(MongoCompressor from, String propertyKey, T propertyValue) {
        Assertions.notNull("propertyKey", propertyKey);
        this.name = from.name;
        this.properties = new HashMap(from.properties);
        this.properties.put(propertyKey.toLowerCase(), propertyValue);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MongoCompressor that = (MongoCompressor) o;
        if (!this.name.equals(that.name)) {
            return false;
        }
        return this.properties.equals(that.properties);
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (31 * result) + this.properties.hashCode();
    }

    public String toString() {
        return "MongoCompressor{name='" + this.name + "', properties=" + this.properties + '}';
    }
}
