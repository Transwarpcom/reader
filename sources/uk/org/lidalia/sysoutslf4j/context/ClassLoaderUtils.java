package uk.org.lidalia.sysoutslf4j.context;

import uk.org.lidalia.sysoutslf4j.common.WrappedCheckedException;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/ClassLoaderUtils.class */
final class ClassLoaderUtils {
    static Class<?> loadClass(ClassLoader classLoader, Class<?> classToLoad) {
        try {
            return classLoader.loadClass(classToLoad.getName());
        } catch (ClassNotFoundException cne) {
            throw new WrappedCheckedException(cne);
        }
    }

    private ClassLoaderUtils() {
        throw new UnsupportedOperationException("Not instantiable");
    }
}
