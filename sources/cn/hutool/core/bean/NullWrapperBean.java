package cn.hutool.core.bean;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/bean/NullWrapperBean.class */
public class NullWrapperBean<T> {
    private final Class<T> clazz;

    public NullWrapperBean(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Class<T> getWrappedClass() {
        return this.clazz;
    }
}
