package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.util.BooleanUtil;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/convert/impl/BooleanConverter.class */
public class BooleanConverter extends AbstractConverter<Boolean> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.hutool.core.convert.AbstractConverter
    public Boolean convertInternal(Object value) {
        if (value instanceof Number) {
            return Boolean.valueOf(0.0d != ((Number) value).doubleValue());
        }
        return Boolean.valueOf(BooleanUtil.toBoolean(convertToStr(value)));
    }
}
