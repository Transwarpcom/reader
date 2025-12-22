package org.springframework.core.convert.support;

import java.util.TimeZone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/convert/support/StringToTimeZoneConverter.class */
class StringToTimeZoneConverter implements Converter<String, TimeZone> {
    StringToTimeZoneConverter() {
    }

    @Override // org.springframework.core.convert.converter.Converter
    public TimeZone convert(String source) {
        return StringUtils.parseTimeZoneString(source);
    }
}
