package cn.hutool.core.date.format;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/date/format/DateParser.class */
public interface DateParser extends DateBasic {
    Date parse(String str) throws ParseException;

    Date parse(String str, ParsePosition parsePosition);

    boolean parse(String str, ParsePosition parsePosition, Calendar calendar);

    default Object parseObject(String source) throws ParseException {
        return parse(source);
    }

    default Object parseObject(String source, ParsePosition pos) {
        return parse(source, pos);
    }
}
