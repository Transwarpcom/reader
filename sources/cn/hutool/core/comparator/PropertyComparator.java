package cn.hutool.core.comparator;

import cn.hutool.core.bean.BeanUtil;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/comparator/PropertyComparator.class */
public class PropertyComparator<T> extends FuncComparator<T> {
    private static final long serialVersionUID = 9157326766723846313L;

    public PropertyComparator(String property) {
        this(property, true);
    }

    public PropertyComparator(String property, boolean isNullGreater) {
        super(isNullGreater, bean -> {
            return (Comparable) BeanUtil.getProperty(bean, property);
        });
    }
}
