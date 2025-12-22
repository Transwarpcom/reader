package org.bson.codecs.pojo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PropertyReflectionUtils.class */
final class PropertyReflectionUtils {
    private static final String IS_PREFIX = "is";
    private static final String GET_PREFIX = "get";
    private static final String SET_PREFIX = "set";

    private PropertyReflectionUtils() {
    }

    static boolean isGetter(Method method) {
        if (method.getParameterTypes().length > 0) {
            return false;
        }
        if (method.getName().startsWith("get") && method.getName().length() > "get".length()) {
            return Character.isUpperCase(method.getName().charAt("get".length()));
        }
        if (method.getName().startsWith("is") && method.getName().length() > "is".length()) {
            return Character.isUpperCase(method.getName().charAt("is".length()));
        }
        return false;
    }

    static boolean isSetter(Method method) {
        if (method.getName().startsWith("set") && method.getName().length() > "set".length() && method.getParameterTypes().length == 1) {
            return Character.isUpperCase(method.getName().charAt("set".length()));
        }
        return false;
    }

    static String toPropertyName(Method method) {
        String name = method.getName();
        String propertyName = name.substring(name.startsWith("is") ? 2 : 3, name.length());
        char[] chars = propertyName.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    static PropertyMethods getPropertyMethods(Class<?> clazz) throws SecurityException {
        List<Method> setters = new ArrayList<>();
        List<Method> getters = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers()) && !method.isBridge()) {
                if (isGetter(method)) {
                    getters.add(method);
                } else if (isSetter(method)) {
                    setters.add(method);
                }
            }
        }
        return new PropertyMethods(getters, setters);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PropertyReflectionUtils$PropertyMethods.class */
    static class PropertyMethods {
        private final Collection<Method> getterMethods;
        private final Collection<Method> setterMethods;

        PropertyMethods(Collection<Method> getterMethods, Collection<Method> setterMethods) {
            this.getterMethods = getterMethods;
            this.setterMethods = setterMethods;
        }

        Collection<Method> getGetterMethods() {
            return this.getterMethods;
        }

        Collection<Method> getSetterMethods() {
            return this.setterMethods;
        }
    }
}
