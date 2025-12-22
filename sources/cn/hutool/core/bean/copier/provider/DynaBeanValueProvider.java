package cn.hutool.core.bean.copier.provider;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.DynaBean;
import cn.hutool.core.bean.copier.ValueProvider;
import cn.hutool.core.convert.Convert;
import java.lang.reflect.Type;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/bean/copier/provider/DynaBeanValueProvider.class */
public class DynaBeanValueProvider implements ValueProvider<String> {
    private final DynaBean dynaBean;
    private final boolean ignoreError;

    public DynaBeanValueProvider(DynaBean dynaBean, boolean ignoreError) {
        this.dynaBean = dynaBean;
        this.ignoreError = ignoreError;
    }

    @Override // cn.hutool.core.bean.copier.ValueProvider
    public Object value(String key, Type valueType) throws BeanException {
        Object value = this.dynaBean.get(key);
        return Convert.convertWithCheck(valueType, value, null, this.ignoreError);
    }

    @Override // cn.hutool.core.bean.copier.ValueProvider
    public boolean containsKey(String key) {
        return this.dynaBean.containsProp(key);
    }
}
