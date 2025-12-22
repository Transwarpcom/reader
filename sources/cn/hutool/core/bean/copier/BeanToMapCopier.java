package cn.hutool.core.bean.copier;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/bean/copier/BeanToMapCopier.class */
public class BeanToMapCopier extends AbsCopier<Object, Map> {
    private final Type targetType;

    public BeanToMapCopier(Object source, Map target, Type targetType, CopyOptions copyOptions) {
        super(source, target, copyOptions);
        this.targetType = targetType;
    }

    @Override // cn.hutool.core.lang.copier.Copier
    public Map copy() throws Throwable {
        Class<?> actualEditable = this.source.getClass();
        if (null != this.copyOptions.editable) {
            Assert.isTrue(this.copyOptions.editable.isInstance(this.source), "Source class [{}] not assignable to Editable class [{}]", actualEditable.getName(), this.copyOptions.editable.getName());
            actualEditable = this.copyOptions.editable;
        }
        Map<String, PropDesc> sourcePropDescMap = BeanUtil.getBeanDesc(actualEditable).getPropMap(this.copyOptions.ignoreCase);
        sourcePropDescMap.forEach((sFieldName, sDesc) -> {
            String sFieldName;
            if (null == sFieldName || false == sDesc.isReadable(this.copyOptions.transientSupport) || null == (sFieldName = this.copyOptions.editFieldName(sFieldName))) {
                return;
            }
            Object sValue = sDesc.getValue(this.source);
            if (false == this.copyOptions.testPropertyFilter(sDesc.getField(), sValue)) {
                return;
            }
            Type[] typeArguments = TypeUtil.getTypeArguments(this.targetType);
            if (null != typeArguments) {
                sValue = this.copyOptions.editFieldValue(sFieldName, Convert.convertWithCheck(typeArguments[1], sValue, null, this.copyOptions.ignoreError));
            }
            if (null != sValue || false == this.copyOptions.ignoreNullValue) {
                ((Map) this.target).put(sFieldName, sValue);
            }
        });
        return (Map) this.target;
    }
}
