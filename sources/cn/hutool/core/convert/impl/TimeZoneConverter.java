package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import java.util.TimeZone;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/convert/impl/TimeZoneConverter.class */
public class TimeZoneConverter extends AbstractConverter<TimeZone> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.hutool.core.convert.AbstractConverter
    public TimeZone convertInternal(Object value) {
        return TimeZone.getTimeZone(convertToStr(value));
    }
}
