package cn.hutool.core.lang;

import cn.hutool.core.lang.mutable.MutableObj;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/Holder.class */
public final class Holder<T> extends MutableObj<T> {
    private static final long serialVersionUID = -3119568580130118011L;

    public static <T> Holder<T> of(T value) throws NullPointerException {
        if (null == value) {
            throw new NullPointerException("Holder can not hold a null value!");
        }
        return new Holder<>(value);
    }

    public Holder() {
    }

    public Holder(T value) {
        super(value);
    }
}
