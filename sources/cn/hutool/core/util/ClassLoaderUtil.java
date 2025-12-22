package cn.hutool.core.util;

import cn.hutool.core.convert.BasicType;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.JarClassLoader;
import cn.hutool.core.lang.SimpleCache;
import java.io.File;
import java.lang.reflect.Array;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/util/ClassLoaderUtil.class */
public class ClassLoaderUtil {
    private static final String ARRAY_SUFFIX = "[]";
    private static final String INTERNAL_ARRAY_PREFIX = "[";
    private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";
    private static final char PACKAGE_SEPARATOR = '.';
    private static final char INNER_CLASS_SEPARATOR = '$';
    private static final Map<String, Class<?>> PRIMITIVE_TYPE_NAME_MAP = new ConcurrentHashMap(32);
    private static final SimpleCache<String, Class<?>> CLASS_CACHE = new SimpleCache<>();

    static {
        List<Class<?>> primitiveTypes = new ArrayList<>(32);
        primitiveTypes.addAll(BasicType.PRIMITIVE_WRAPPER_MAP.keySet());
        primitiveTypes.add(boolean[].class);
        primitiveTypes.add(byte[].class);
        primitiveTypes.add(char[].class);
        primitiveTypes.add(double[].class);
        primitiveTypes.add(float[].class);
        primitiveTypes.add(int[].class);
        primitiveTypes.add(long[].class);
        primitiveTypes.add(short[].class);
        primitiveTypes.add(Void.TYPE);
        for (Class<?> primitiveType : primitiveTypes) {
            PRIMITIVE_TYPE_NAME_MAP.put(primitiveType.getName(), primitiveType);
        }
    }

    public static ClassLoader getContextClassLoader() {
        if (System.getSecurityManager() == null) {
            return Thread.currentThread().getContextClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(() -> {
            return Thread.currentThread().getContextClassLoader();
        });
    }

    public static ClassLoader getSystemClassLoader() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(ClassLoader::getSystemClassLoader);
    }

    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = getContextClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoaderUtil.class.getClassLoader();
            if (null == classLoader) {
                classLoader = getSystemClassLoader();
            }
        }
        return classLoader;
    }

    public static Class<?> loadClass(String name) throws UtilException {
        return loadClass(name, true);
    }

    public static Class<?> loadClass(String name, boolean isInitialized) throws UtilException {
        return loadClass(name, null, isInitialized);
    }

    public static Class<?> loadClass(String name, ClassLoader classLoader, boolean isInitialized) throws UtilException, ClassNotFoundException, IllegalArgumentException {
        Class<?> clazz;
        Assert.notNull(name, "Name must not be null", new Object[0]);
        String name2 = name.replace('/', '.');
        Class<?> clazz2 = loadPrimitiveClass(name2);
        if (clazz2 == null) {
            clazz2 = CLASS_CACHE.get(name2);
        }
        if (clazz2 != null) {
            return clazz2;
        }
        if (name2.endsWith("[]")) {
            String elementClassName = name2.substring(0, name2.length() - "[]".length());
            Class<?> elementClass = loadClass(elementClassName, classLoader, isInitialized);
            clazz = Array.newInstance(elementClass, 0).getClass();
        } else if (name2.startsWith(NON_PRIMITIVE_ARRAY_PREFIX) && name2.endsWith(";")) {
            String elementName = name2.substring(NON_PRIMITIVE_ARRAY_PREFIX.length(), name2.length() - 1);
            Class<?> elementClass2 = loadClass(elementName, classLoader, isInitialized);
            clazz = Array.newInstance(elementClass2, 0).getClass();
        } else if (name2.startsWith("[")) {
            String elementName2 = name2.substring("[".length());
            Class<?> elementClass3 = loadClass(elementName2, classLoader, isInitialized);
            clazz = Array.newInstance(elementClass3, 0).getClass();
        } else {
            if (null == classLoader) {
                classLoader = getClassLoader();
            }
            try {
                clazz = Class.forName(name2, isInitialized, classLoader);
            } catch (ClassNotFoundException ex) {
                clazz = tryLoadInnerClass(name2, classLoader, isInitialized);
                if (null == clazz) {
                    throw new UtilException(ex);
                }
            }
        }
        return CLASS_CACHE.put(name2, clazz);
    }

    public static Class<?> loadPrimitiveClass(String name) {
        Class<?> result = null;
        if (StrUtil.isNotBlank(name)) {
            String name2 = name.trim();
            if (name2.length() <= 8) {
                result = PRIMITIVE_TYPE_NAME_MAP.get(name2);
            }
        }
        return result;
    }

    public static JarClassLoader getJarClassLoader(File jarOrDir) {
        return JarClassLoader.load(jarOrDir);
    }

    public static Class<?> loadClass(File jarOrDir, String name) {
        try {
            return getJarClassLoader(jarOrDir).loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new UtilException(e);
        }
    }

    public static boolean isPresent(String className) {
        return isPresent(className, null);
    }

    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            loadClass(className, classLoader, false);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static Class<?> tryLoadInnerClass(String name, ClassLoader classLoader, boolean isInitialized) {
        int lastDotIndex = name.lastIndexOf(46);
        if (lastDotIndex > 0) {
            String innerClassName = name.substring(0, lastDotIndex) + '$' + name.substring(lastDotIndex + 1);
            try {
                return Class.forName(innerClassName, isInitialized, classLoader);
            } catch (ClassNotFoundException e) {
                return null;
            }
        }
        return null;
    }
}
