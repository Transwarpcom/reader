package cn.hutool.core.collection;

import java.util.Spliterator;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/collection/SpliteratorUtil.class */
public class SpliteratorUtil {
    public static <F, T> Spliterator<T> trans(Spliterator<F> fromSpliterator, Function<? super F, ? extends T> function) {
        return new TransSpliterator(fromSpliterator, function);
    }
}
