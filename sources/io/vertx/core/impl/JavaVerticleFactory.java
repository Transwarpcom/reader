package io.vertx.core.impl;

import cn.hutool.core.io.file.FileNameUtil;
import io.vertx.core.Verticle;
import io.vertx.core.impl.verticle.CompilingClassLoader;
import io.vertx.core.spi.VerticleFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/JavaVerticleFactory.class */
public class JavaVerticleFactory implements VerticleFactory {
    @Override // io.vertx.core.spi.VerticleFactory
    public String prefix() {
        return "java";
    }

    @Override // io.vertx.core.spi.VerticleFactory
    public Verticle createVerticle(String verticleName, ClassLoader classLoader) throws Exception {
        Class clazz;
        String verticleName2 = VerticleFactory.removePrefix(verticleName);
        if (verticleName2.endsWith(FileNameUtil.EXT_JAVA)) {
            CompilingClassLoader compilingLoader = new CompilingClassLoader(classLoader, verticleName2);
            String className = compilingLoader.resolveMainClassName();
            clazz = compilingLoader.loadClass(className);
        } else {
            clazz = classLoader.loadClass(verticleName2);
        }
        return (Verticle) clazz.newInstance();
    }
}
