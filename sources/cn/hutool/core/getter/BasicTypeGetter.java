package cn.hutool.core.getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/getter/BasicTypeGetter.class */
public interface BasicTypeGetter<K> {
    Object getObj(K k);

    String getStr(K k);

    Integer getInt(K k);

    Short getShort(K k);

    Boolean getBool(K k);

    Long getLong(K k);

    Character getChar(K k);

    Float getFloat(K k);

    Double getDouble(K k);

    Byte getByte(K k);

    BigDecimal getBigDecimal(K k);

    BigInteger getBigInteger(K k);

    <E extends Enum<E>> E getEnum(Class<E> cls, K k);

    Date getDate(K k);
}
