package cn.hutool.core.date.format;

import java.util.Locale;
import java.util.TimeZone;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/date/format/DateBasic.class */
public interface DateBasic {
    String getPattern();

    TimeZone getTimeZone();

    Locale getLocale();
}
