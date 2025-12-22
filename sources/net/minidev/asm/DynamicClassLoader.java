package net.minidev.asm;

import java.lang.reflect.Method;

/* loaded from: reader.jar:BOOT-INF/lib/accessors-smart-2.4.7.jar:net/minidev/asm/DynamicClassLoader.class */
class DynamicClassLoader extends ClassLoader {
    private static final String BEAN_AC = BeansAccess.class.getName();
    private static final Class<?>[] DEF_CLASS_SIG = {String.class, byte[].class, Integer.TYPE, Integer.TYPE};

    DynamicClassLoader(ClassLoader parent) {
        super(parent);
    }

    public static <T> Class<T> directLoad(Class<? extends T> cls, String str, byte[] bArr) {
        return (Class<T>) new DynamicClassLoader(cls.getClassLoader()).defineClass(str, bArr);
    }

    public static <T> T directInstance(Class<? extends T> parent, String clsName, byte[] clsData) throws IllegalAccessException, InstantiationException {
        Class<T> clzz = directLoad(parent, clsName, clsData);
        return clzz.newInstance();
    }

    @Override // java.lang.ClassLoader
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.equals(BEAN_AC)) {
            return BeansAccess.class;
        }
        return super.loadClass(name, resolve);
    }

    Class<?> defineClass(String name, byte[] bytes) throws NoSuchMethodException, SecurityException, ClassFormatError {
        try {
            Method method = ClassLoader.class.getDeclaredMethod("defineClass", DEF_CLASS_SIG);
            method.setAccessible(true);
            return (Class) method.invoke(getParent(), name, bytes, 0, Integer.valueOf(bytes.length));
        } catch (Exception e) {
            return defineClass(name, bytes, 0, bytes.length);
        }
    }
}
