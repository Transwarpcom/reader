package net.minidev.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import net.minidev.json.reader.JsonWriter;
import net.minidev.json.reader.JsonWriterI;
import net.minidev.json.writer.CompessorMapper;
import net.minidev.json.writer.FakeMapper;
import net.minidev.json.writer.JsonReader;
import net.minidev.json.writer.JsonReaderI;
import net.minidev.json.writer.UpdaterMapper;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/JSONValue.class */
public class JSONValue {
    public static JSONStyle COMPRESSION = JSONStyle.NO_COMPRESS;
    public static final JsonWriter defaultWriter = new JsonWriter();
    public static final JsonReader defaultReader = new JsonReader();

    public static Object parse(InputStream in) {
        try {
            return new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(in);
        } catch (Exception e) {
            return null;
        }
    }

    public static Object parse(byte[] in) {
        try {
            return new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(in);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T parse(InputStream inputStream, Class<T> cls) {
        try {
            return (T) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(inputStream, defaultReader.getMapper((Class) cls));
        } catch (Exception e) {
            return null;
        }
    }

    public static Object parse(Reader in) {
        try {
            return new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(in);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T parse(byte[] bArr, Class<T> cls) {
        try {
            return (T) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(bArr, defaultReader.getMapper((Class) cls));
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T parse(Reader reader, Class<T> cls) {
        try {
            return (T) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(reader, defaultReader.getMapper((Class) cls));
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T parse(Reader reader, T t) {
        try {
            return (T) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(reader, new UpdaterMapper(defaultReader, t));
        } catch (Exception e) {
            return null;
        }
    }

    protected static <T> T parse(Reader reader, JsonReaderI<T> jsonReaderI) {
        try {
            return (T) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(reader, jsonReaderI);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T parse(String str, Class<T> cls) {
        try {
            return (T) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(str, defaultReader.getMapper((Class) cls));
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T parse(InputStream inputStream, T t) {
        try {
            return (T) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(inputStream, new UpdaterMapper(defaultReader, t));
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T parse(String str, T t) {
        try {
            return (T) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(str, new UpdaterMapper(defaultReader, t));
        } catch (Exception e) {
            return null;
        }
    }

    protected static <T> T parse(byte[] bArr, JsonReaderI<T> jsonReaderI) {
        try {
            return (T) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(bArr, jsonReaderI);
        } catch (Exception e) {
            return null;
        }
    }

    protected static <T> T parse(String str, JsonReaderI<T> jsonReaderI) {
        try {
            return (T) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(str, jsonReaderI);
        } catch (Exception e) {
            return null;
        }
    }

    public static Object parse(String s) {
        try {
            return new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static Object parseKeepingOrder(Reader in) {
        try {
            return new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(in, defaultReader.DEFAULT_ORDERED);
        } catch (Exception e) {
            return null;
        }
    }

    public static Object parseKeepingOrder(String in) {
        try {
            return new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(in, defaultReader.DEFAULT_ORDERED);
        } catch (Exception e) {
            return null;
        }
    }

    public static String compress(String input, JSONStyle style) {
        try {
            StringBuilder sb = new StringBuilder();
            new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(input, new CompessorMapper(defaultReader, sb, style));
            return sb.toString();
        } catch (Exception e) {
            return input;
        }
    }

    public static String compress(String input) {
        return compress(input, JSONStyle.MAX_COMPRESS);
    }

    public static String uncompress(String input) {
        return compress(input, JSONStyle.NO_COMPRESS);
    }

    public static Object parseWithException(byte[] in) throws ParseException, IOException {
        return new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(in, defaultReader.DEFAULT);
    }

    public static Object parseWithException(InputStream in) throws ParseException, IOException {
        return new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(in, defaultReader.DEFAULT);
    }

    public static Object parseWithException(Reader in) throws ParseException, IOException {
        return new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(in, defaultReader.DEFAULT);
    }

    public static Object parseWithException(String input) throws ParseException {
        return new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(input, defaultReader.DEFAULT);
    }

    public static <T> T parseWithException(String str, Class<T> cls) throws ParseException {
        return (T) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(str, defaultReader.getMapper((Class) cls));
    }

    public static Object parseStrict(Reader in) throws ParseException, IOException {
        return new JSONParser(JSONParser.MODE_RFC4627).parse(in, defaultReader.DEFAULT);
    }

    public static Object parseStrict(String s) throws ParseException {
        return new JSONParser(JSONParser.MODE_RFC4627).parse(s, defaultReader.DEFAULT);
    }

    public static boolean isValidJsonStrict(Reader in) throws IOException {
        try {
            new JSONParser(JSONParser.MODE_RFC4627).parse(in, FakeMapper.DEFAULT);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidJsonStrict(String s) {
        try {
            new JSONParser(JSONParser.MODE_RFC4627).parse(s, FakeMapper.DEFAULT);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidJson(Reader in) throws IOException {
        try {
            new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(in, FakeMapper.DEFAULT);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidJson(String s) {
        try {
            new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(s, FakeMapper.DEFAULT);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void writeJSONString(Object value, Appendable out) throws IOException {
        writeJSONString(value, out, COMPRESSION);
    }

    public static <T> void remapField(Class<T> type, String jsonFieldName, String javaFieldName) {
        defaultReader.remapField(type, jsonFieldName, javaFieldName);
        defaultWriter.remapField(type, javaFieldName, jsonFieldName);
    }

    public static <T> void registerWriter(Class<?> cls, JsonWriterI<T> writer) {
        defaultWriter.registerWriter(writer, cls);
    }

    public static <T> void registerReader(Class<T> type, JsonReaderI<T> mapper) {
        defaultReader.registerReader(type, mapper);
    }

    public static void writeJSONString(Object value, Appendable out, JSONStyle compression) throws IOException {
        if (value == null) {
            out.append("null");
            return;
        }
        Class<?> clz = value.getClass();
        JsonWriterI w = defaultWriter.getWrite(clz);
        if (w == null) {
            if (clz.isArray()) {
                w = JsonWriter.arrayWriter;
            } else {
                w = defaultWriter.getWriterByInterface(value.getClass());
                if (w == null) {
                    w = JsonWriter.beansWriterASM;
                }
            }
            defaultWriter.registerWriter(w, clz);
        }
        w.writeJSONString(value, out, compression);
    }

    public static String toJSONString(Object value) {
        return toJSONString(value, COMPRESSION);
    }

    public static String toJSONString(Object value, JSONStyle compression) {
        StringBuilder sb = new StringBuilder();
        try {
            writeJSONString(value, sb, compression);
        } catch (IOException e) {
        }
        return sb.toString();
    }

    public static String escape(String s) {
        return escape(s, COMPRESSION);
    }

    public static String escape(String s, JSONStyle compression) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        compression.escape(s, sb);
        return sb.toString();
    }

    public static void escape(String s, Appendable ap) {
        escape(s, ap, COMPRESSION);
    }

    public static void escape(String s, Appendable ap, JSONStyle compression) {
        if (s == null) {
            return;
        }
        compression.escape(s, ap);
    }
}
