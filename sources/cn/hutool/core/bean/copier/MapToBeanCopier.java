package cn.hutool.core.bean.copier;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.CaseInsensitiveMap;
import cn.hutool.core.map.MapWrapper;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/bean/copier/MapToBeanCopier.class */
public class MapToBeanCopier<T> extends AbsCopier<Map<?, ?>, T> {
    private final Type targetType;

    public MapToBeanCopier(Map<?, ?> source, T target, Type targetType, CopyOptions copyOptions) {
        super(source, target, copyOptions);
        if (source instanceof MapWrapper) {
            Map<?, ?> raw = ((MapWrapper) source).getRaw();
            if (raw instanceof CaseInsensitiveMap) {
                copyOptions.setIgnoreCase(true);
            }
        }
        this.targetType = targetType;
    }

    @Override // cn.hutool.core.lang.copier.Copier
    public T copy() throws Throwable {
        Class<?> actualEditable = this.target.getClass();
        if (null != this.copyOptions.editable) {
            Assert.isTrue(this.copyOptions.editable.isInstance(this.target), "Target class [{}] not assignable to Editable class [{}]", actualEditable.getName(), this.copyOptions.editable.getName());
            actualEditable = this.copyOptions.editable;
        }
        Map<String, PropDesc> targetPropDescMap = BeanUtil.getBeanDesc(actualEditable).getPropMap(this.copyOptions.ignoreCase);
        ((Map) this.source).forEach((sKey, sValue) -> {
            String sKeyStr;
            PropDesc tDesc;
            if (null == sKey || null == (sKeyStr = this.copyOptions.editFieldName(sKey.toString())) || null == (tDesc = findPropDesc(targetPropDescMap, sKeyStr)) || false == tDesc.isWritable(this.copyOptions.transientSupport)) {
                return;
            }
            String sKeyStr2 = tDesc.getFieldName();
            if (false == this.copyOptions.testPropertyFilter(tDesc.getField(), sValue)) {
                return;
            }
            Type fieldType = TypeUtil.getActualType(this.targetType, tDesc.getFieldType());
            Object newValue = Convert.convertWithCheck(fieldType, sValue, null, this.copyOptions.ignoreError);
            tDesc.setValue(this.target, this.copyOptions.editFieldValue(sKeyStr2, newValue), this.copyOptions.ignoreNullValue, this.copyOptions.ignoreError, this.copyOptions.override);
        });
        return this.target;
    }

    private PropDesc findPropDesc(Map<String, PropDesc> targetPropDescMap, String sKeyStr) {
        PropDesc propDesc = targetPropDescMap.get(sKeyStr);
        if (null != propDesc) {
            return propDesc;
        }
        String sKeyStr2 = StrUtil.toCamelCase(sKeyStr);
        PropDesc propDesc2 = targetPropDescMap.get(sKeyStr2);
        if (null != propDesc2) {
            return propDesc2;
        }
        if (sKeyStr2.startsWith(ch.qos.logback.core.joran.util.beans.BeanUtil.PREFIX_GETTER_IS)) {
            return targetPropDescMap.get(StrUtil.removePreAndLowerFirst(sKeyStr2, 2));
        }
        return null;
    }
}
