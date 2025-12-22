package cn.hutool.core.collection;

import java.util.List;
import java.util.RandomAccess;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/collection/RandomAccessPartition.class */
public class RandomAccessPartition<T> extends Partition<T> implements RandomAccess {
    public RandomAccessPartition(List<T> list, int size) {
        super(list, size);
    }
}
