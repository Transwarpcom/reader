package org.springframework.core.convert.support;

import java.time.ZoneId;
import java.util.TimeZone;
import org.springframework.core.convert.converter.Converter;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/convert/support/ZoneIdToTimeZoneConverter.class */
final class ZoneIdToTimeZoneConverter implements Converter<ZoneId, TimeZone> {
    ZoneIdToTimeZoneConverter() {
    }

    @Override // org.springframework.core.convert.converter.Converter
    public TimeZone convert(ZoneId source) {
        return TimeZone.getTimeZone(source);
    }
}
