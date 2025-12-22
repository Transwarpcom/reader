package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/convert/impl/CharacterConverter.class */
public class CharacterConverter extends AbstractConverter<Character> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.hutool.core.convert.AbstractConverter
    public Character convertInternal(Object value) {
        if (value instanceof Boolean) {
            return BooleanUtil.toCharacter(((Boolean) value).booleanValue());
        }
        String valueStr = convertToStr(value);
        if (StrUtil.isNotBlank(valueStr)) {
            return Character.valueOf(valueStr.charAt(0));
        }
        return null;
    }
}
