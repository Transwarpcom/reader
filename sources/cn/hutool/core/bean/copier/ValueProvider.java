package cn.hutool.core.bean.copier;

import java.lang.reflect.Type;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/bean/copier/ValueProvider.class */
public interface ValueProvider<T> {
    Object value(T t, Type type);

    boolean containsKey(T t);
}
