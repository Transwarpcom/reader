package net.minidev.asm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minidev.asm.ex.NoSuchFieldException;

/* loaded from: reader.jar:BOOT-INF/lib/accessors-smart-2.4.7.jar:net/minidev/asm/BeansAccess.class */
public abstract class BeansAccess<T> {
    private HashMap<String, Accessor> map;
    private Accessor[] accs;
    private static ConcurrentHashMap<Class<?>, BeansAccess<?>> cache = new ConcurrentHashMap<>();

    public abstract void set(T t, int i, Object obj);

    public abstract Object get(T t, int i);

    public abstract T newInstance();

    protected void setAccessor(Accessor[] accs) {
        int i = 0;
        this.accs = accs;
        this.map = new HashMap<>();
        for (Accessor acc : accs) {
            int i2 = i;
            i++;
            acc.index = i2;
            this.map.put(acc.getName(), acc);
        }
    }

    public HashMap<String, Accessor> getMap() {
        return this.map;
    }

    public Accessor[] getAccessors() {
        return this.accs;
    }

    public static <P> BeansAccess<P> get(Class<P> type) {
        return get(type, (FieldFilter) null);
    }

    public static <P> BeansAccess<P> get(Class<P> type, FieldFilter filter) {
        String accessClassName;
        BeansAccess<P> access = (BeansAccess) cache.get(type);
        if (access != null) {
            return access;
        }
        Accessor[] accs = ASMUtil.getAccessors(type, filter);
        String className = type.getName();
        if (className.startsWith("java.util.")) {
            accessClassName = "net.minidev.asm." + className + "AccAccess";
        } else {
            accessClassName = className.concat("AccAccess");
        }
        DynamicClassLoader loader = new DynamicClassLoader(type.getClassLoader());
        Class<?> accessClass = null;
        try {
            accessClass = loader.loadClass(accessClassName);
        } catch (ClassNotFoundException e) {
        }
        LinkedList<Class<?>> parentClasses = getParents(type);
        if (accessClass == null) {
            BeansAccessBuilder builder = new BeansAccessBuilder(type, accs, loader);
            Iterator<Class<?>> it = parentClasses.iterator();
            while (it.hasNext()) {
                Class<?> c = it.next();
                builder.addConversion(BeansAccessConfig.classMapper.get(c));
            }
            accessClass = builder.bulid();
        }
        try {
            BeansAccess<P> access2 = (BeansAccess) accessClass.newInstance();
            access2.setAccessor(accs);
            cache.putIfAbsent(type, access2);
            Iterator<Class<?>> it2 = parentClasses.iterator();
            while (it2.hasNext()) {
                Class<?> c2 = it2.next();
                addAlias(access2, BeansAccessConfig.classFiledNameMapper.get(c2));
            }
            return access2;
        } catch (Exception ex) {
            throw new RuntimeException("Error constructing accessor class: " + accessClassName, ex);
        }
    }

    private static LinkedList<Class<?>> getParents(Class<?> type) {
        LinkedList<Class<?>> m = new LinkedList<>();
        while (type != null && !type.equals(Object.class)) {
            m.addLast(type);
            for (Class<?> c : type.getInterfaces()) {
                m.addLast(c);
            }
            type = type.getSuperclass();
        }
        m.addLast(Object.class);
        return m;
    }

    private static void addAlias(BeansAccess<?> access, HashMap<String, String> m) {
        if (m == null) {
            return;
        }
        HashMap<String, Accessor> changes = new HashMap<>();
        for (Map.Entry<String, String> e : m.entrySet()) {
            Accessor a1 = ((BeansAccess) access).map.get(e.getValue());
            if (a1 != null) {
                changes.put(e.getValue(), a1);
            }
        }
        ((BeansAccess) access).map.putAll(changes);
    }

    public void set(T object, String methodName, Object value) {
        int i = getIndex(methodName);
        if (i == -1) {
            throw new NoSuchFieldException(methodName + " in " + object.getClass() + " to put value : " + value);
        }
        set((BeansAccess<T>) object, i, value);
    }

    public Object get(T object, String methodName) {
        return get((BeansAccess<T>) object, getIndex(methodName));
    }

    public int getIndex(String name) {
        Accessor ac = this.map.get(name);
        if (ac == null) {
            return -1;
        }
        return ac.index;
    }
}
