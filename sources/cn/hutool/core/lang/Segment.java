package cn.hutool.core.lang;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import java.lang.Number;
import java.lang.reflect.Type;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/Segment.class */
public interface Segment<T extends Number> {
    T getStartIndex();

    T getEndIndex();

    default T length() {
        Number number = (Number) Assert.notNull(getStartIndex(), "Start index must be not null!", new Object[0]);
        return (T) Convert.convert((Type) number.getClass(), (Object) NumberUtil.sub((Number) Assert.notNull(getEndIndex(), "End index must be not null!", new Object[0]), number).abs());
    }
}
