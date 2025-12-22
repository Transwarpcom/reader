package cn.hutool.core.convert.impl;

import cn.hutool.core.convert.AbstractConverter;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/convert/impl/StackTraceElementConverter.class */
public class StackTraceElementConverter extends AbstractConverter<StackTraceElement> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.hutool.core.convert.AbstractConverter
    public StackTraceElement convertInternal(Object value) {
        if (value instanceof Map) {
            Map<?, ?> map = (Map) value;
            String declaringClass = MapUtil.getStr(map, "className");
            String methodName = MapUtil.getStr(map, "methodName");
            String fileName = MapUtil.getStr(map, "fileName");
            Integer lineNumber = MapUtil.getInt(map, "lineNumber");
            return new StackTraceElement(declaringClass, methodName, fileName, ((Integer) ObjectUtil.defaultIfNull((int) lineNumber, 0)).intValue());
        }
        return null;
    }
}
