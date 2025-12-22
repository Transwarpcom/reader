package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.convert.Convert;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/convert/impl/AtomicIntegerArrayConverter.class */
public class AtomicIntegerArrayConverter extends AbstractConverter<AtomicIntegerArray> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.hutool.core.convert.AbstractConverter
    public AtomicIntegerArray convertInternal(Object value) {
        return new AtomicIntegerArray((int[]) Convert.convert(int[].class, value));
    }
}
