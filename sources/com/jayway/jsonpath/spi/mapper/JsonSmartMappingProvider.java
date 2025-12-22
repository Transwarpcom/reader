package com.jayway.jsonpath.spi.mapper;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.TypeRef;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.Callable;
import net.minidev.json.JSONValue;
import net.minidev.json.writer.JsonReader;
import net.minidev.json.writer.JsonReaderI;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JsonSmartMappingProvider.class */
public class JsonSmartMappingProvider implements MappingProvider {
    private static JsonReader DEFAULT = new JsonReader();
    private final Callable<JsonReader> factory;

    static {
        DEFAULT.registerReader(Long.class, new LongReader());
        DEFAULT.registerReader(Long.TYPE, new LongReader());
        DEFAULT.registerReader(Integer.class, new IntegerReader());
        DEFAULT.registerReader(Integer.TYPE, new IntegerReader());
        DEFAULT.registerReader(Double.class, new DoubleReader());
        DEFAULT.registerReader(Double.TYPE, new DoubleReader());
        DEFAULT.registerReader(Float.class, new FloatReader());
        DEFAULT.registerReader(Float.TYPE, new FloatReader());
        DEFAULT.registerReader(BigDecimal.class, new BigDecimalReader());
        DEFAULT.registerReader(String.class, new StringReader());
        DEFAULT.registerReader(Date.class, new DateReader());
        DEFAULT.registerReader(BigInteger.class, new BigIntegerReader());
        DEFAULT.registerReader(Boolean.TYPE, new BooleanReader());
    }

    public JsonSmartMappingProvider(final JsonReader jsonReader) {
        this(new Callable<JsonReader>() { // from class: com.jayway.jsonpath.spi.mapper.JsonSmartMappingProvider.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public JsonReader call() {
                return jsonReader;
            }
        });
    }

    public JsonSmartMappingProvider(Callable<JsonReader> factory) {
        this.factory = factory;
    }

    public JsonSmartMappingProvider() {
        this(DEFAULT);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jayway.jsonpath.spi.mapper.MappingProvider
    public <T> T map(Object obj, Class<T> cls, Configuration configuration) {
        if (obj == 0) {
            return null;
        }
        if (cls.isAssignableFrom(obj.getClass())) {
            return obj;
        }
        try {
            if (!configuration.jsonProvider().isMap(obj) && !configuration.jsonProvider().isArray(obj)) {
                return this.factory.call().getMapper((Class) cls).convert(obj);
            }
            return (T) JSONValue.parse(configuration.jsonProvider().toJson(obj), (Class) cls);
        } catch (Exception e) {
            throw new MappingException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.mapper.MappingProvider
    public <T> T map(Object source, TypeRef<T> targetType, Configuration configuration) {
        throw new UnsupportedOperationException("Json-smart provider does not support TypeRef! Use a Jackson or Gson based provider");
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JsonSmartMappingProvider$StringReader.class */
    private static class StringReader extends JsonReaderI<String> {
        public StringReader() {
            super(null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.minidev.json.writer.JsonReaderI
        public String convert(Object src) {
            if (src == null) {
                return null;
            }
            return src.toString();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JsonSmartMappingProvider$IntegerReader.class */
    private static class IntegerReader extends JsonReaderI<Integer> {
        public IntegerReader() {
            super(null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.minidev.json.writer.JsonReaderI
        public Integer convert(Object src) {
            if (src == null) {
                return null;
            }
            if (Integer.class.isAssignableFrom(src.getClass())) {
                return (Integer) src;
            }
            if (Long.class.isAssignableFrom(src.getClass())) {
                return Integer.valueOf(((Long) src).intValue());
            }
            if (Double.class.isAssignableFrom(src.getClass())) {
                return Integer.valueOf(((Double) src).intValue());
            }
            if (BigDecimal.class.isAssignableFrom(src.getClass())) {
                return Integer.valueOf(((BigDecimal) src).intValue());
            }
            if (Float.class.isAssignableFrom(src.getClass())) {
                return Integer.valueOf(((Float) src).intValue());
            }
            if (String.class.isAssignableFrom(src.getClass())) {
                return Integer.valueOf(src.toString());
            }
            throw new MappingException("can not map a " + src.getClass() + " to " + Integer.class.getName());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JsonSmartMappingProvider$LongReader.class */
    private static class LongReader extends JsonReaderI<Long> {
        public LongReader() {
            super(null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.minidev.json.writer.JsonReaderI
        public Long convert(Object src) {
            if (src == null) {
                return null;
            }
            if (Long.class.isAssignableFrom(src.getClass())) {
                return (Long) src;
            }
            if (Integer.class.isAssignableFrom(src.getClass())) {
                return Long.valueOf(((Integer) src).longValue());
            }
            if (Double.class.isAssignableFrom(src.getClass())) {
                return Long.valueOf(((Double) src).longValue());
            }
            if (BigDecimal.class.isAssignableFrom(src.getClass())) {
                return Long.valueOf(((BigDecimal) src).longValue());
            }
            if (Float.class.isAssignableFrom(src.getClass())) {
                return Long.valueOf(((Float) src).longValue());
            }
            if (String.class.isAssignableFrom(src.getClass())) {
                return Long.valueOf(src.toString());
            }
            throw new MappingException("can not map a " + src.getClass() + " to " + Long.class.getName());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JsonSmartMappingProvider$DoubleReader.class */
    private static class DoubleReader extends JsonReaderI<Double> {
        public DoubleReader() {
            super(null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.minidev.json.writer.JsonReaderI
        public Double convert(Object src) {
            if (src == null) {
                return null;
            }
            if (Double.class.isAssignableFrom(src.getClass())) {
                return (Double) src;
            }
            if (Integer.class.isAssignableFrom(src.getClass())) {
                return Double.valueOf(((Integer) src).doubleValue());
            }
            if (Long.class.isAssignableFrom(src.getClass())) {
                return Double.valueOf(((Long) src).doubleValue());
            }
            if (BigDecimal.class.isAssignableFrom(src.getClass())) {
                return Double.valueOf(((BigDecimal) src).doubleValue());
            }
            if (Float.class.isAssignableFrom(src.getClass())) {
                return Double.valueOf(((Float) src).doubleValue());
            }
            if (String.class.isAssignableFrom(src.getClass())) {
                return Double.valueOf(src.toString());
            }
            throw new MappingException("can not map a " + src.getClass() + " to " + Double.class.getName());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JsonSmartMappingProvider$FloatReader.class */
    private static class FloatReader extends JsonReaderI<Float> {
        public FloatReader() {
            super(null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.minidev.json.writer.JsonReaderI
        public Float convert(Object src) {
            if (src == null) {
                return null;
            }
            if (Float.class.isAssignableFrom(src.getClass())) {
                return (Float) src;
            }
            if (Integer.class.isAssignableFrom(src.getClass())) {
                return Float.valueOf(((Integer) src).floatValue());
            }
            if (Long.class.isAssignableFrom(src.getClass())) {
                return Float.valueOf(((Long) src).floatValue());
            }
            if (BigDecimal.class.isAssignableFrom(src.getClass())) {
                return Float.valueOf(((BigDecimal) src).floatValue());
            }
            if (Double.class.isAssignableFrom(src.getClass())) {
                return Float.valueOf(((Double) src).floatValue());
            }
            if (String.class.isAssignableFrom(src.getClass())) {
                return Float.valueOf(src.toString());
            }
            throw new MappingException("can not map a " + src.getClass() + " to " + Float.class.getName());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JsonSmartMappingProvider$BigDecimalReader.class */
    private static class BigDecimalReader extends JsonReaderI<BigDecimal> {
        public BigDecimalReader() {
            super(null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.minidev.json.writer.JsonReaderI
        public BigDecimal convert(Object src) {
            if (src == null) {
                return null;
            }
            return new BigDecimal(src.toString());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JsonSmartMappingProvider$BigIntegerReader.class */
    private static class BigIntegerReader extends JsonReaderI<BigInteger> {
        public BigIntegerReader() {
            super(null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.minidev.json.writer.JsonReaderI
        public BigInteger convert(Object src) {
            if (src == null) {
                return null;
            }
            return new BigInteger(src.toString());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JsonSmartMappingProvider$DateReader.class */
    private static class DateReader extends JsonReaderI<Date> {
        public DateReader() {
            super(null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.minidev.json.writer.JsonReaderI
        public Date convert(Object src) {
            if (src == null) {
                return null;
            }
            if (Date.class.isAssignableFrom(src.getClass())) {
                return (Date) src;
            }
            if (Long.class.isAssignableFrom(src.getClass())) {
                return new Date(((Long) src).longValue());
            }
            if (String.class.isAssignableFrom(src.getClass())) {
                try {
                    return DateFormat.getInstance().parse(src.toString());
                } catch (ParseException e) {
                    throw new MappingException(e);
                }
            }
            throw new MappingException("can not map a " + src.getClass() + " to " + Date.class.getName());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/JsonSmartMappingProvider$BooleanReader.class */
    private static class BooleanReader extends JsonReaderI<Boolean> {
        public BooleanReader() {
            super(null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.minidev.json.writer.JsonReaderI
        public Boolean convert(Object src) {
            if (src == null) {
                return null;
            }
            if (Boolean.class.isAssignableFrom(src.getClass())) {
                return (Boolean) src;
            }
            throw new MappingException("can not map a " + src.getClass() + " to " + Boolean.class.getName());
        }
    }
}
