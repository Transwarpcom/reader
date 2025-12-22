package cn.hutool.core.lang.func;

import java.io.Serializable;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/func/Func0.class */
public interface Func0<R> extends Serializable {
    R call() throws Exception;

    default R callWithRuntimeException() {
        try {
            return call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
