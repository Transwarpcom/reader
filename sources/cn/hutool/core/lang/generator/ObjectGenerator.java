package cn.hutool.core.lang.generator;

import cn.hutool.core.util.ReflectUtil;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/generator/ObjectGenerator.class */
public class ObjectGenerator<T> implements Generator<T> {
    private final Class<T> clazz;

    public ObjectGenerator(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override // cn.hutool.core.lang.generator.Generator
    public T next() {
        return (T) ReflectUtil.newInstanceIfPossible(this.clazz);
    }
}
