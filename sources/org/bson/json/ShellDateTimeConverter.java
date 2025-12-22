package org.bson.json;

import cn.hutool.core.date.DatePattern;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/ShellDateTimeConverter.class */
class ShellDateTimeConverter implements Converter<Long> {
    ShellDateTimeConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Long value, StrictJsonWriter writer) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DatePattern.UTC_MS_PATTERN);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        if (value.longValue() >= -59014396800000L && value.longValue() <= 253399536000000L) {
            writer.writeRaw(String.format("ISODate(\"%s\")", dateFormat.format(new Date(value.longValue()))));
        } else {
            writer.writeRaw(String.format("new Date(%d)", value));
        }
    }
}
