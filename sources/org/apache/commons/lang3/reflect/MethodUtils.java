package org.apache.commons.lang3.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/reflect/MethodUtils.class */
public class MethodUtils {
    public static Object invokeMethod(Object object, String methodName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return invokeMethod(object, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, (Class<?>[]) null);
    }

    public static Object invokeMethod(Object object, boolean forceAccess, String methodName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return invokeMethod(object, forceAccess, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
    }

    public static Object invokeMethod(Object object, String methodName, Object... args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return invokeMethod(object, methodName, args2, parameterTypes);
    }

    public static Object invokeMethod(Object object, boolean forceAccess, String methodName, Object... args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return invokeMethod(object, forceAccess, methodName, args2, parameterTypes);
    }

    public static Object invokeMethod(Object object, boolean forceAccess, String methodName, Object[] args, Class<?>[] parameterTypes) throws IllegalAccessException, NoSuchMethodException, SecurityException, NegativeArraySizeException, InvocationTargetException {
        String messagePrefix;
        Method method;
        Class<?>[] parameterTypes2 = ArrayUtils.nullToEmpty(parameterTypes);
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        if (forceAccess) {
            messagePrefix = "No such method: ";
            method = getMatchingMethod(object.getClass(), methodName, parameterTypes2);
            if (method != null && !method.isAccessible()) {
                method.setAccessible(true);
            }
        } else {
            messagePrefix = "No such accessible method: ";
            method = getMatchingAccessibleMethod(object.getClass(), methodName, parameterTypes2);
        }
        if (method == null) {
            throw new NoSuchMethodException(messagePrefix + methodName + "() on object: " + object.getClass().getName());
        }
        return method.invoke(object, toVarArgs(method, args2));
    }

    public static Object invokeMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return invokeMethod(object, false, methodName, args, parameterTypes);
    }

    public static Object invokeExactMethod(Object object, String methodName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return invokeExactMethod(object, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
    }

    public static Object invokeExactMethod(Object object, String methodName, Object... args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return invokeExactMethod(object, methodName, args2, parameterTypes);
    }

    public static Object invokeExactMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Method method = getAccessibleMethod(object.getClass(), methodName, ArrayUtils.nullToEmpty(parameterTypes));
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName());
        }
        return method.invoke(object, args2);
    }

    public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Method method = getAccessibleMethod(cls, methodName, ArrayUtils.nullToEmpty(parameterTypes));
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
        }
        return method.invoke(null, args2);
    }

    public static Object invokeStaticMethod(Class<?> cls, String methodName, Object... args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return invokeStaticMethod(cls, methodName, args2, parameterTypes);
    }

    public static Object invokeStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes) throws IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Method method = getMatchingAccessibleMethod(cls, methodName, ArrayUtils.nullToEmpty(parameterTypes));
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
        }
        return method.invoke(null, toVarArgs(method, args2));
    }

    private static Object[] toVarArgs(Method method, Object[] args) throws NegativeArraySizeException {
        if (method.isVarArgs()) {
            Class<?>[] methodParameterTypes = method.getParameterTypes();
            args = getVarArgs(args, methodParameterTypes);
        }
        return args;
    }

    static Object[] getVarArgs(Object[] args, Class<?>[] methodParameterTypes) throws NegativeArraySizeException {
        if (args.length == methodParameterTypes.length && args[args.length - 1].getClass().equals(methodParameterTypes[methodParameterTypes.length - 1])) {
            return args;
        }
        Object[] newArgs = new Object[methodParameterTypes.length];
        System.arraycopy(args, 0, newArgs, 0, methodParameterTypes.length - 1);
        Class<?> varArgComponentType = methodParameterTypes[methodParameterTypes.length - 1].getComponentType();
        int varArgLength = (args.length - methodParameterTypes.length) + 1;
        Object varArgsArray = Array.newInstance(ClassUtils.primitiveToWrapper(varArgComponentType), varArgLength);
        System.arraycopy(args, methodParameterTypes.length - 1, varArgsArray, 0, varArgLength);
        if (varArgComponentType.isPrimitive()) {
            varArgsArray = ArrayUtils.toPrimitive(varArgsArray);
        }
        newArgs[methodParameterTypes.length - 1] = varArgsArray;
        return newArgs;
    }

    public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object... args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] args2 = ArrayUtils.nullToEmpty(args);
        Class<?>[] parameterTypes = ClassUtils.toClass(args2);
        return invokeExactStaticMethod(cls, methodName, args2, parameterTypes);
    }

    public static Method getAccessibleMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        try {
            return getAccessibleMethod(cls.getMethod(methodName, parameterTypes));
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method getAccessibleMethod(Method method) {
        if (!MemberUtils.isAccessible(method)) {
            return null;
        }
        Class<?> cls = method.getDeclaringClass();
        if (Modifier.isPublic(cls.getModifiers())) {
            return method;
        }
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Method method2 = getAccessibleMethodFromInterfaceNest(cls, methodName, parameterTypes);
        if (method2 == null) {
            method2 = getAccessibleMethodFromSuperclass(cls, methodName, parameterTypes);
        }
        return method2;
    }

    private static Method getAccessibleMethodFromSuperclass(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        Class<?> superclass = cls.getSuperclass();
        while (true) {
            Class<?> parentClass = superclass;
            if (parentClass != null) {
                if (Modifier.isPublic(parentClass.getModifiers())) {
                    try {
                        return parentClass.getMethod(methodName, parameterTypes);
                    } catch (NoSuchMethodException e) {
                        return null;
                    }
                }
                superclass = parentClass.getSuperclass();
            } else {
                return null;
            }
        }
    }

    private static Method getAccessibleMethodFromInterfaceNest(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                if (Modifier.isPublic(anInterface.getModifiers())) {
                    try {
                        return anInterface.getDeclaredMethod(methodName, parameterTypes);
                    } catch (NoSuchMethodException e) {
                        Method method = getAccessibleMethodFromInterfaceNest(anInterface, methodName, parameterTypes);
                        if (method != null) {
                            return method;
                        }
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    public static Method getMatchingAccessibleMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
        Method accessibleMethod;
        try {
            Method method = cls.getMethod(methodName, parameterTypes);
            MemberUtils.setAccessibleWorkaround(method);
            return method;
        } catch (NoSuchMethodException e) {
            Method bestMatch = null;
            Method[] methods = cls.getMethods();
            for (Method method2 : methods) {
                if (method2.getName().equals(methodName) && MemberUtils.isMatchingMethod(method2, parameterTypes) && (accessibleMethod = getAccessibleMethod(method2)) != null && (bestMatch == null || MemberUtils.compareMethodFit(accessibleMethod, bestMatch, parameterTypes) < 0)) {
                    bestMatch = accessibleMethod;
                }
            }
            if (bestMatch != null) {
                MemberUtils.setAccessibleWorkaround(bestMatch);
            }
            if (bestMatch != null && bestMatch.isVarArgs() && bestMatch.getParameterTypes().length > 0 && parameterTypes.length > 0) {
                Class<?>[] methodParameterTypes = bestMatch.getParameterTypes();
                Class<?> methodParameterComponentType = methodParameterTypes[methodParameterTypes.length - 1].getComponentType();
                String methodParameterComponentTypeName = ClassUtils.primitiveToWrapper(methodParameterComponentType).getName();
                String parameterTypeName = parameterTypes[parameterTypes.length - 1].getName();
                String parameterTypeSuperClassName = parameterTypes[parameterTypes.length - 1].getSuperclass().getName();
                if (!methodParameterComponentTypeName.equals(parameterTypeName) && !methodParameterComponentTypeName.equals(parameterTypeSuperClassName)) {
                    return null;
                }
            }
            return bestMatch;
        }
    }

    public static Method getMatchingMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) throws SecurityException {
        Validate.notNull(cls, "Null class not allowed.", new Object[0]);
        Validate.notEmpty(methodName, "Null or blank methodName not allowed.", new Object[0]);
        Method[] methodArray = cls.getDeclaredMethods();
        List<Class<?>> superclassList = ClassUtils.getAllSuperclasses(cls);
        for (Class<?> klass : superclassList) {
            methodArray = (Method[]) ArrayUtils.addAll(methodArray, klass.getDeclaredMethods());
        }
        Method inexactMatch = null;
        for (Method method : methodArray) {
            if (methodName.equals(method.getName()) && Objects.deepEquals(parameterTypes, method.getParameterTypes())) {
                return method;
            }
            if (methodName.equals(method.getName()) && ClassUtils.isAssignable(parameterTypes, method.getParameterTypes(), true)) {
                if (inexactMatch == null) {
                    inexactMatch = method;
                } else if (distance(parameterTypes, method.getParameterTypes()) < distance(parameterTypes, inexactMatch.getParameterTypes())) {
                    inexactMatch = method;
                }
            }
        }
        return inexactMatch;
    }

    private static int distance(Class<?>[] classArray, Class<?>[] toClassArray) {
        int answer = 0;
        if (!ClassUtils.isAssignable(classArray, toClassArray, true)) {
            return -1;
        }
        for (int offset = 0; offset < classArray.length; offset++) {
            if (!classArray[offset].equals(toClassArray[offset])) {
                if (ClassUtils.isAssignable(classArray[offset], toClassArray[offset], true) && !ClassUtils.isAssignable(classArray[offset], toClassArray[offset], false)) {
                    answer++;
                } else {
                    answer += 2;
                }
            }
        }
        return answer;
    }

    public static Set<Method> getOverrideHierarchy(Method method, ClassUtils.Interfaces interfacesBehavior) throws NoSuchMethodException, SecurityException {
        Validate.notNull(method);
        Set<Method> result = new LinkedHashSet<>();
        result.add(method);
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> declaringClass = method.getDeclaringClass();
        Iterator<Class<?>> hierarchy = ClassUtils.hierarchy(declaringClass, interfacesBehavior).iterator();
        hierarchy.next();
        while (hierarchy.hasNext()) {
            Class<?> c = hierarchy.next();
            Method m = getMatchingAccessibleMethod(c, method.getName(), parameterTypes);
            if (m != null) {
                if (Arrays.equals(m.getParameterTypes(), parameterTypes)) {
                    result.add(m);
                } else {
                    Map<TypeVariable<?>, Type> typeArguments = TypeUtils.getTypeArguments(declaringClass, m.getDeclaringClass());
                    int i = 0;
                    while (true) {
                        if (i < parameterTypes.length) {
                            Type childType = TypeUtils.unrollVariables(typeArguments, method.getGenericParameterTypes()[i]);
                            Type parentType = TypeUtils.unrollVariables(typeArguments, m.getGenericParameterTypes()[i]);
                            if (!TypeUtils.equals(childType, parentType)) {
                                break;
                            }
                            i++;
                        } else {
                            result.add(m);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static Method[] getMethodsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
        return getMethodsWithAnnotation(cls, annotationCls, false, false);
    }

    public static List<Method> getMethodsListWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
        return getMethodsListWithAnnotation(cls, annotationCls, false, false);
    }

    public static Method[] getMethodsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls, boolean searchSupers, boolean ignoreAccess) {
        List<Method> annotatedMethodsList = getMethodsListWithAnnotation(cls, annotationCls, searchSupers, ignoreAccess);
        return (Method[]) annotatedMethodsList.toArray(new Method[annotatedMethodsList.size()]);
    }

    public static List<Method> getMethodsListWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls, boolean searchSupers, boolean ignoreAccess) {
        Validate.isTrue(cls != null, "The class must not be null", new Object[0]);
        Validate.isTrue(annotationCls != null, "The annotation class must not be null", new Object[0]);
        List<Class<?>> classes = searchSupers ? getAllSuperclassesAndInterfaces(cls) : new ArrayList<>();
        classes.add(0, cls);
        List<Method> annotatedMethods = new ArrayList<>();
        for (Class<?> acls : classes) {
            Method[] methods = ignoreAccess ? acls.getDeclaredMethods() : acls.getMethods();
            for (Method method : methods) {
                if (method.getAnnotation(annotationCls) != null) {
                    annotatedMethods.add(method);
                }
            }
        }
        return annotatedMethods;
    }

    public static <A extends Annotation> A getAnnotation(Method method, Class<A> cls, boolean z, boolean z2) throws NoSuchMethodException, SecurityException {
        Method method2;
        Validate.isTrue(method != null, "The method must not be null", new Object[0]);
        Validate.isTrue(cls != null, "The annotation class must not be null", new Object[0]);
        if (!z2 && !MemberUtils.isAccessible(method)) {
            return null;
        }
        Annotation annotation = method.getAnnotation(cls);
        if (annotation == null && z) {
            for (Class<?> cls2 : getAllSuperclassesAndInterfaces(method.getDeclaringClass())) {
                if (z2) {
                    try {
                        method2 = cls2.getDeclaredMethod(method.getName(), method.getParameterTypes());
                    } catch (NoSuchMethodException e) {
                    }
                } else {
                    method2 = cls2.getMethod(method.getName(), method.getParameterTypes());
                }
                annotation = method2.getAnnotation(cls);
                if (annotation != null) {
                    break;
                }
            }
        }
        return (A) annotation;
    }

    private static List<Class<?>> getAllSuperclassesAndInterfaces(Class<?> cls) {
        Class<?> cls2;
        if (cls == null) {
            return null;
        }
        List<Class<?>> allSuperClassesAndInterfaces = new ArrayList<>();
        List<Class<?>> allSuperclasses = ClassUtils.getAllSuperclasses(cls);
        int superClassIndex = 0;
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(cls);
        int interfaceIndex = 0;
        while (true) {
            if (interfaceIndex < allInterfaces.size() || superClassIndex < allSuperclasses.size()) {
                if (interfaceIndex >= allInterfaces.size()) {
                    int i = superClassIndex;
                    superClassIndex++;
                    cls2 = allSuperclasses.get(i);
                } else if (superClassIndex >= allSuperclasses.size()) {
                    int i2 = interfaceIndex;
                    interfaceIndex++;
                    cls2 = allInterfaces.get(i2);
                } else if (interfaceIndex < superClassIndex) {
                    int i3 = interfaceIndex;
                    interfaceIndex++;
                    cls2 = allInterfaces.get(i3);
                } else if (superClassIndex < interfaceIndex) {
                    int i4 = superClassIndex;
                    superClassIndex++;
                    cls2 = allSuperclasses.get(i4);
                } else {
                    int i5 = interfaceIndex;
                    interfaceIndex++;
                    cls2 = allInterfaces.get(i5);
                }
                Class<?> acls = cls2;
                allSuperClassesAndInterfaces.add(acls);
            } else {
                return allSuperClassesAndInterfaces;
            }
        }
    }
}
