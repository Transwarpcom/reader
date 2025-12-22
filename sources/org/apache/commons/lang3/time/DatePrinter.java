package org.apache.commons.lang3.time;

import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/DatePrinter.class */
public interface DatePrinter {
    String format(long j);

    String format(Date date);

    String format(Calendar calendar);

    @Deprecated
    StringBuffer format(long j, StringBuffer stringBuffer);

    @Deprecated
    StringBuffer format(Date date, StringBuffer stringBuffer);

    @Deprecated
    StringBuffer format(Calendar calendar, StringBuffer stringBuffer);

    <B extends Appendable> B format(long j, B b);

    <B extends Appendable> B format(Date date, B b);

    <B extends Appendable> B format(Calendar calendar, B b);

    String getPattern();

    TimeZone getTimeZone();

    Locale getLocale();

    StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition);
}
