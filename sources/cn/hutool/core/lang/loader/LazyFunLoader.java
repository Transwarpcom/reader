package cn.hutool.core.lang.loader;

import cn.hutool.core.lang.Assert;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/loader/LazyFunLoader.class */
public class LazyFunLoader<T> extends LazyLoader<T> {
    private static final long serialVersionUID = 1;
    private Supplier<T> supplier;

    public static <T> LazyFunLoader<T> on(Supplier<T> supplier) throws IllegalArgumentException {
        Assert.notNull(supplier, "supplier must be not null!", new Object[0]);
        return new LazyFunLoader<>(supplier);
    }

    public LazyFunLoader(Supplier<T> supplier) throws IllegalArgumentException {
        Assert.notNull(supplier);
        this.supplier = supplier;
    }

    @Override // cn.hutool.core.lang.loader.LazyLoader
    protected T init() {
        T t = this.supplier.get();
        this.supplier = null;
        return t;
    }

    public boolean isInitialize() {
        return this.supplier == null;
    }

    public void ifInitialized(Consumer<T> consumer) throws IllegalArgumentException {
        Assert.notNull(consumer);
        if (isInitialize()) {
            consumer.accept(get());
        }
    }
}
