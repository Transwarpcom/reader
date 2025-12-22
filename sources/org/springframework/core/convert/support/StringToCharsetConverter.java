package org.springframework.core.convert.support;

import java.nio.charset.Charset;
import org.springframework.core.convert.converter.Converter;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/convert/support/StringToCharsetConverter.class */
class StringToCharsetConverter implements Converter<String, Charset> {
    StringToCharsetConverter() {
    }

    @Override // org.springframework.core.convert.converter.Converter
    public Charset convert(String source) {
        return Charset.forName(source);
    }
}
