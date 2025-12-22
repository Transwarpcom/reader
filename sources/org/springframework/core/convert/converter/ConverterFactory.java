package org.springframework.core.convert.converter;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/convert/converter/ConverterFactory.class */
public interface ConverterFactory<S, R> {
    <T extends R> Converter<S, T> getConverter(Class<T> cls);
}
