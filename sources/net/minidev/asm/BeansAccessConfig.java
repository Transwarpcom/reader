package net.minidev.asm;

import java.util.HashMap;
import java.util.LinkedHashSet;

/* loaded from: reader.jar:BOOT-INF/lib/accessors-smart-2.4.7.jar:net/minidev/asm/BeansAccessConfig.class */
public class BeansAccessConfig {
    protected static HashMap<Class<?>, LinkedHashSet<Class<?>>> classMapper = new HashMap<>();
    protected static HashMap<Class<?>, HashMap<String, String>> classFiledNameMapper = new HashMap<>();

    static {
        addTypeMapper(Object.class, DefaultConverter.class);
        addTypeMapper(Object.class, ConvertDate.class);
    }

    public static void addTypeMapper(Class<?> clz, Class<?> mapper) {
        synchronized (classMapper) {
            LinkedHashSet<Class<?>> h = classMapper.get(clz);
            if (h == null) {
                h = new LinkedHashSet<>();
                classMapper.put(clz, h);
            }
            h.add(mapper);
        }
    }
}
