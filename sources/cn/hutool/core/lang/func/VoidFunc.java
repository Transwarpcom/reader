package cn.hutool.core.lang.func;

import java.io.Serializable;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/func/VoidFunc.class */
public interface VoidFunc<P> extends Serializable {
    void call(P... pArr) throws Exception;

    default void callWithRuntimeException(P... parameters) {
        try {
            call(parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
