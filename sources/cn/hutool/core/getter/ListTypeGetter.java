package cn.hutool.core.getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/getter/ListTypeGetter.class */
public interface ListTypeGetter {
    List<Object> getObjList(String str);

    List<String> getStrList(String str);

    List<Integer> getIntList(String str);

    List<Short> getShortList(String str);

    List<Boolean> getBoolList(String str);

    List<Long> getLongList(String str);

    List<Character> getCharList(String str);

    List<Double> getDoubleList(String str);

    List<Byte> getByteList(String str);

    List<BigDecimal> getBigDecimalList(String str);

    List<BigInteger> getBigIntegerList(String str);
}
