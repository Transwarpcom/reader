package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/convert/support/NumberToCharacterConverter.class */
final class NumberToCharacterConverter implements Converter<Number, Character> {
    NumberToCharacterConverter() {
    }

    @Override // org.springframework.core.convert.converter.Converter
    public Character convert(Number source) {
        return Character.valueOf((char) source.shortValue());
    }
}
