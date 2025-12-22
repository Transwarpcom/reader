package cn.hutool.core.lang.func;

import java.io.Serializable;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/func/Func.class */
public interface Func<P, R> extends Serializable {
    R call(P... pArr) throws Exception;

    default R callWithRuntimeException(P... parameters) {
        try {
            return call(parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
