package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.util.BooleanUtil;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/convert/impl/AtomicBooleanConverter.class */
public class AtomicBooleanConverter extends AbstractConverter<AtomicBoolean> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.hutool.core.convert.AbstractConverter
    public AtomicBoolean convertInternal(Object value) {
        if (value instanceof Boolean) {
            return new AtomicBoolean(((Boolean) value).booleanValue());
        }
        String valueStr = convertToStr(value);
        return new AtomicBoolean(BooleanUtil.toBoolean(valueStr));
    }
}
