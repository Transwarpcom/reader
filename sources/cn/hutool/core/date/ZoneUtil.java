package cn.hutool.core.date;

import java.time.ZoneId;
import java.util.TimeZone;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/date/ZoneUtil.class */
public class ZoneUtil {
    public static TimeZone toTimeZone(ZoneId zoneId) {
        if (null == zoneId) {
            return TimeZone.getDefault();
        }
        return TimeZone.getTimeZone(zoneId);
    }

    public static ZoneId toZoneId(TimeZone timeZone) {
        if (null == timeZone) {
            return ZoneId.systemDefault();
        }
        return timeZone.toZoneId();
    }
}
