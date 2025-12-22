package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/convert/impl/AtomicReferenceConverter.class */
public class AtomicReferenceConverter extends AbstractConverter<AtomicReference> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.hutool.core.convert.AbstractConverter
    /* renamed from: convertInternal, reason: merged with bridge method [inline-methods] */
    public AtomicReference convertInternal2(Object value) throws ConvertException {
        Object targetValue = null;
        Type paramType = TypeUtil.getTypeArgument(AtomicReference.class);
        if (false == TypeUtil.isUnknown(paramType)) {
            targetValue = ConverterRegistry.getInstance().convert(paramType, value);
        }
        if (null == targetValue) {
            targetValue = value;
        }
        return new AtomicReference(targetValue);
    }
}
