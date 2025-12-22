package io.vertx.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/ServiceHelper.class */
public class ServiceHelper {
    public static <T> T loadFactory(Class<T> cls) {
        T t = (T) loadFactoryOrNull(cls);
        if (t == null) {
            throw new IllegalStateException("Cannot find META-INF/services/" + cls.getName() + " on classpath");
        }
        return t;
    }

    public static <T> T loadFactoryOrNull(Class<T> clazz) {
        Collection<T> collection = loadFactories(clazz);
        if (!collection.isEmpty()) {
            return collection.iterator().next();
        }
        return null;
    }

    public static <T> Collection<T> loadFactories(Class<T> clazz) {
        return loadFactories(clazz, null);
    }

    public static <T> Collection<T> loadFactories(Class<T> clazz, ClassLoader classLoader) {
        ServiceLoader<T> factories;
        List<T> list = new ArrayList<>();
        if (classLoader != null) {
            factories = ServiceLoader.load(clazz, classLoader);
        } else {
            factories = ServiceLoader.load(clazz);
        }
        if (factories.iterator().hasNext()) {
            Iterator<T> it = factories.iterator();
            list.getClass();
            it.forEachRemaining(list::add);
            return list;
        }
        ServiceLoader<T> factories2 = ServiceLoader.load(clazz, ServiceHelper.class.getClassLoader());
        if (factories2.iterator().hasNext()) {
            Iterator<T> it2 = factories2.iterator();
            list.getClass();
            it2.forEachRemaining(list::add);
            return list;
        }
        return Collections.emptyList();
    }
}
