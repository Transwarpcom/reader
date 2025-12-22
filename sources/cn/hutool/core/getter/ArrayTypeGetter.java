package cn.hutool.core.getter;

import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/getter/ArrayTypeGetter.class */
public interface ArrayTypeGetter {
    String[] getObjs(String str);

    String[] getStrs(String str);

    Integer[] getInts(String str);

    Short[] getShorts(String str);

    Boolean[] getBools(String str);

    Long[] getLongs(String str);

    Character[] getChars(String str);

    Double[] getDoubles(String str);

    Byte[] getBytes(String str);

    BigInteger[] getBigIntegers(String str);

    BigDecimal[] getBigDecimals(String str);
}
