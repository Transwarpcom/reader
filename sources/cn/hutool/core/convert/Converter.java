package cn.hutool.core.convert;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/convert/Converter.class */
public interface Converter<T> {
    T convert(Object obj, T t) throws IllegalArgumentException;

    default T convertWithCheck(Object value, T defaultValue, boolean quietly) throws Exception {
        try {
            return convert(value, defaultValue);
        } catch (Exception e) {
            if (quietly) {
                return defaultValue;
            }
            throw e;
        }
    }
}
