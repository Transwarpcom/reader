package cn.hutool.core.lang.loader;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/loader/LazyLoader.class */
public abstract class LazyLoader<T> implements Loader<T>, Serializable {
    private static final long serialVersionUID = 1;
    private volatile T object;

    protected abstract T init();

    @Override // cn.hutool.core.lang.loader.Loader
    public T get() {
        T result = this.object;
        if (result == null) {
            synchronized (this) {
                result = this.object;
                if (result == null) {
                    T tInit = init();
                    result = tInit;
                    this.object = tInit;
                }
            }
        }
        return result;
    }
}
