package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.util.CharsetUtil;
import java.nio.charset.Charset;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/convert/impl/CharsetConverter.class */
public class CharsetConverter extends AbstractConverter<Charset> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.hutool.core.convert.AbstractConverter
    public Charset convertInternal(Object value) {
        return CharsetUtil.charset(convertToStr(value));
    }
}
