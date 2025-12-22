package cn.hutool.core.bean.copier;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/bean/copier/MapToMapCopier.class */
public class MapToMapCopier extends AbsCopier<Map, Map> {
    private final Type targetType;

    public MapToMapCopier(Map source, Map target, Type targetType, CopyOptions copyOptions) {
        super(source, target, copyOptions);
        this.targetType = targetType;
    }

    @Override // cn.hutool.core.lang.copier.Copier
    public Map copy() {
        ((Map) this.source).forEach((sKey, sValue) -> {
            String sKeyStr;
            if (null == sKey || null == (sKeyStr = this.copyOptions.editFieldName(sKey.toString()))) {
                return;
            }
            Object targetValue = ((Map) this.target).get(sKeyStr);
            if (false == this.copyOptions.override && null != targetValue) {
                return;
            }
            Type[] typeArguments = TypeUtil.getTypeArguments(this.targetType);
            if (null != typeArguments) {
                sValue = this.copyOptions.editFieldValue(sKeyStr, Convert.convertWithCheck(typeArguments[1], sValue, null, this.copyOptions.ignoreError));
            }
            ((Map) this.target).put(sKeyStr, sValue);
        });
        return (Map) this.target;
    }
}
