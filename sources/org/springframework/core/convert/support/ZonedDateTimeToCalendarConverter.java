package org.springframework.core.convert.support;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.springframework.core.convert.converter.Converter;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/convert/support/ZonedDateTimeToCalendarConverter.class */
final class ZonedDateTimeToCalendarConverter implements Converter<ZonedDateTime, Calendar> {
    ZonedDateTimeToCalendarConverter() {
    }

    @Override // org.springframework.core.convert.converter.Converter
    public Calendar convert(ZonedDateTime source) {
        return GregorianCalendar.from(source);
    }
}
