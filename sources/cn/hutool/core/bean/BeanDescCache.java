package cn.hutool.core.bean;

import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.lang.func.Func0;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/bean/BeanDescCache.class */
public enum BeanDescCache {
    INSTANCE;

    private final SimpleCache<Class<?>, BeanDesc> bdCache = new SimpleCache<>();

    BeanDescCache() {
    }

    public BeanDesc getBeanDesc(Class<?> beanClass, Func0<BeanDesc> supplier) {
        return this.bdCache.get(beanClass, supplier);
    }

    public void clear() {
        this.bdCache.clear();
    }
}
