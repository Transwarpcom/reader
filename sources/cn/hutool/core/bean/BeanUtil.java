package cn.hutool.core.bean;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.bean.copier.ValueProvider;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.map.CaseInsensitiveMap;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ModifierUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/bean/BeanUtil.class */
public class BeanUtil {
    private static /* synthetic */ Object $deserializeLambda$(SerializedLambda lambda) {
        switch (lambda.getImplMethodName()) {
            case "lambda$getPropertyDescriptorMap$58f3b7cb$1":
                if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("cn/hutool/core/lang/func/Func0") && lambda.getFunctionalInterfaceMethodName().equals("call") && lambda.getFunctionalInterfaceMethodSignature().equals("()Ljava/lang/Object;") && lambda.getImplClass().equals("cn/hutool/core/bean/BeanUtil") && lambda.getImplMethodSignature().equals("(Ljava/lang/Class;Z)Ljava/util/Map;")) {
                    Class cls = (Class) lambda.getCapturedArg(0);
                    boolean zBooleanValue = ((Boolean) lambda.getCapturedArg(1)).booleanValue();
                    return () -> {
                        return internalGetPropertyDescriptorMap(cls, zBooleanValue);
                    };
                }
                break;
            case "lambda$getBeanDesc$e7c7684d$1":
                if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("cn/hutool/core/lang/func/Func0") && lambda.getFunctionalInterfaceMethodName().equals("call") && lambda.getFunctionalInterfaceMethodSignature().equals("()Ljava/lang/Object;") && lambda.getImplClass().equals("cn/hutool/core/bean/BeanUtil") && lambda.getImplMethodSignature().equals("(Ljava/lang/Class;)Lcn/hutool/core/bean/BeanDesc;")) {
                    Class cls2 = (Class) lambda.getCapturedArg(0);
                    return () -> {
                        return new BeanDesc(cls2);
                    };
                }
                break;
        }
        throw new IllegalArgumentException("Invalid lambda deserialization");
    }

    public static boolean isReadableBean(Class<?> clazz) {
        return hasGetter(clazz) || hasPublicField(clazz);
    }

    public static boolean isBean(Class<?> clazz) {
        return hasSetter(clazz) || hasPublicField(clazz);
    }

    public static boolean hasSetter(Class<?> clazz) throws SecurityException {
        if (ClassUtil.isNormalClass(clazz)) {
            for (Method method : clazz.getMethods()) {
                if (method.getParameterCount() == 1 && method.getName().startsWith("set")) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean hasGetter(Class<?> clazz) throws SecurityException {
        if (ClassUtil.isNormalClass(clazz)) {
            for (Method method : clazz.getMethods()) {
                if (method.getParameterCount() == 0 && (method.getName().startsWith(ch.qos.logback.core.joran.util.beans.BeanUtil.PREFIX_GETTER_GET) || method.getName().startsWith(ch.qos.logback.core.joran.util.beans.BeanUtil.PREFIX_GETTER_IS))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean hasPublicField(Class<?> clazz) throws SecurityException {
        if (ClassUtil.isNormalClass(clazz)) {
            for (Field field : clazz.getFields()) {
                if (ModifierUtil.isPublic(field) && false == ModifierUtil.isStatic(field)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static DynaBean createDynaBean(Object bean) {
        return new DynaBean(bean);
    }

    public static PropertyEditor findEditor(Class<?> type) {
        return PropertyEditorManager.findEditor(type);
    }

    public static BeanDesc getBeanDesc(Class<?> clazz) {
        return BeanDescCache.INSTANCE.getBeanDesc(clazz, () -> {
            return new BeanDesc(clazz);
        });
    }

    public static void descForEach(Class<?> clazz, Consumer<? super PropDesc> action) {
        getBeanDesc(clazz).getProps().forEach(action);
    }

    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws BeanException {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            return (PropertyDescriptor[]) ArrayUtil.filter(beanInfo.getPropertyDescriptors(), t -> {
                return false == "class".equals(t.getName());
            });
        } catch (IntrospectionException e) {
            throw new BeanException((Throwable) e);
        }
    }

    public static Map<String, PropertyDescriptor> getPropertyDescriptorMap(Class<?> clazz, boolean ignoreCase) throws BeanException {
        return BeanInfoCache.INSTANCE.getPropertyDescriptorMap(clazz, ignoreCase, () -> {
            return internalGetPropertyDescriptorMap(clazz, ignoreCase);
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, PropertyDescriptor> internalGetPropertyDescriptorMap(Class<?> clazz, boolean ignoreCase) throws BeanException {
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
        Map<String, PropertyDescriptor> map = ignoreCase ? new CaseInsensitiveMap<>(propertyDescriptors.length, 1.0f) : new HashMap<>(propertyDescriptors.length, 1.0f);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            map.put(propertyDescriptor.getName(), propertyDescriptor);
        }
        return map;
    }

    public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String fieldName) throws BeanException {
        return getPropertyDescriptor(clazz, fieldName, false);
    }

    public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String fieldName, boolean ignoreCase) throws BeanException {
        Map<String, PropertyDescriptor> map = getPropertyDescriptorMap(clazz, ignoreCase);
        if (null == map) {
            return null;
        }
        return map.get(fieldName);
    }

    public static Object getFieldValue(Object bean, String fieldNameOrIndex) {
        if (null == bean || null == fieldNameOrIndex) {
            return null;
        }
        if (bean instanceof Map) {
            return ((Map) bean).get(fieldNameOrIndex);
        }
        if (bean instanceof Collection) {
            try {
                return CollUtil.get((Collection) bean, Integer.parseInt(fieldNameOrIndex));
            } catch (NumberFormatException e) {
                return CollUtil.map((Collection) bean, beanEle -> {
                    return getFieldValue(beanEle, fieldNameOrIndex);
                }, false);
            }
        }
        if (ArrayUtil.isArray(bean)) {
            try {
                return ArrayUtil.get(bean, Integer.parseInt(fieldNameOrIndex));
            } catch (NumberFormatException e2) {
                return ArrayUtil.map(bean, Object.class, beanEle2 -> {
                    return getFieldValue(beanEle2, fieldNameOrIndex);
                });
            }
        }
        return ReflectUtil.getFieldValue(bean, fieldNameOrIndex);
    }

    public static void setFieldValue(Object bean, String fieldNameOrIndex, Object value) throws UtilException {
        if (bean instanceof Map) {
            ((Map) bean).put(fieldNameOrIndex, value);
            return;
        }
        if (bean instanceof List) {
            CollUtil.setOrAppend((List) bean, Convert.toInt(fieldNameOrIndex).intValue(), value);
        } else if (ArrayUtil.isArray(bean)) {
            ArrayUtil.setOrAppend(bean, Convert.toInt(fieldNameOrIndex).intValue(), value);
        } else {
            ReflectUtil.setFieldValue(bean, fieldNameOrIndex, value);
        }
    }

    public static <T> T getProperty(Object obj, String str) {
        if (null == obj || StrUtil.isBlank(str)) {
            return null;
        }
        return (T) BeanPath.create(str).get(obj);
    }

    public static void setProperty(Object bean, String expression, Object value) {
        BeanPath.create(expression).set(bean, value);
    }

    @Deprecated
    public static <T> T mapToBean(Map<?, ?> map, Class<T> cls, boolean z) {
        return (T) fillBeanWithMap(map, ReflectUtil.newInstanceIfPossible(cls), z);
    }

    @Deprecated
    public static <T> T mapToBeanIgnoreCase(Map<?, ?> map, Class<T> cls, boolean z) {
        return (T) fillBeanWithMapIgnoreCase(map, ReflectUtil.newInstanceIfPossible(cls), z);
    }

    @Deprecated
    public static <T> T mapToBean(Map<?, ?> map, Class<T> cls, CopyOptions copyOptions) {
        return (T) fillBeanWithMap(map, ReflectUtil.newInstanceIfPossible(cls), copyOptions);
    }

    public static <T> T mapToBean(Map<?, ?> map, Class<T> cls, boolean z, CopyOptions copyOptions) {
        return (T) fillBeanWithMap(map, ReflectUtil.newInstanceIfPossible(cls), z, copyOptions);
    }

    public static <T> T fillBeanWithMap(Map<?, ?> map, T t, boolean z) {
        return (T) fillBeanWithMap(map, (Object) t, false, z);
    }

    public static <T> T fillBeanWithMap(Map<?, ?> map, T t, boolean z, boolean z2) {
        return (T) fillBeanWithMap(map, t, z, CopyOptions.create().setIgnoreError(z2));
    }

    public static <T> T fillBeanWithMapIgnoreCase(Map<?, ?> map, T t, boolean z) {
        return (T) fillBeanWithMap(map, t, CopyOptions.create().setIgnoreCase(true).setIgnoreError(z));
    }

    public static <T> T fillBeanWithMap(Map<?, ?> map, T t, CopyOptions copyOptions) {
        return (T) fillBeanWithMap(map, (Object) t, false, copyOptions);
    }

    public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, boolean isToCamelCase, CopyOptions copyOptions) {
        if (MapUtil.isEmpty(map)) {
            return bean;
        }
        if (isToCamelCase) {
            map = MapUtil.toCamelCaseMap(map);
        }
        copyProperties(map, bean, copyOptions);
        return bean;
    }

    public static <T> T toBean(Object obj, Class<T> cls) {
        return (T) toBean(obj, cls, (CopyOptions) null);
    }

    public static <T> T toBeanIgnoreError(Object obj, Class<T> cls) {
        return (T) toBean(obj, cls, CopyOptions.create().setIgnoreError(true));
    }

    public static <T> T toBeanIgnoreCase(Object obj, Class<T> cls, boolean z) {
        return (T) toBean(obj, cls, CopyOptions.create().setIgnoreCase(true).setIgnoreError(z));
    }

    public static <T> T toBean(Object obj, Class<T> cls, CopyOptions copyOptions) {
        if (null == obj) {
            return null;
        }
        T t = (T) ReflectUtil.newInstanceIfPossible(cls);
        copyProperties(obj, t, copyOptions);
        return t;
    }

    public static <T> T toBean(Class<T> cls, ValueProvider<String> valueProvider, CopyOptions copyOptions) {
        if (null == cls || null == valueProvider) {
            return null;
        }
        return (T) fillBean(ReflectUtil.newInstanceIfPossible(cls), valueProvider, copyOptions);
    }

    public static <T> T fillBean(T t, ValueProvider<String> valueProvider, CopyOptions copyOptions) {
        if (null == valueProvider) {
            return t;
        }
        return (T) BeanCopier.create(valueProvider, t, copyOptions).copy();
    }

    public static Map<String, Object> beanToMap(Object bean) {
        return beanToMap(bean, false, false);
    }

    public static Map<String, Object> beanToMap(Object bean, boolean isToUnderlineCase, boolean ignoreNullValue) {
        if (null == bean) {
            return null;
        }
        return beanToMap(bean, new LinkedHashMap(), isToUnderlineCase, ignoreNullValue);
    }

    public static Map<String, Object> beanToMap(Object bean, Map<String, Object> targetMap, boolean isToUnderlineCase, boolean ignoreNullValue) {
        if (null == bean) {
            return null;
        }
        return beanToMap(bean, targetMap, ignoreNullValue, (Editor<String>) key -> {
            return isToUnderlineCase ? StrUtil.toUnderlineCase(key) : key;
        });
    }

    public static Map<String, Object> beanToMap(Object bean, Map<String, Object> targetMap, boolean ignoreNullValue, Editor<String> keyEditor) {
        if (null == bean) {
            return null;
        }
        return (Map) BeanCopier.create(bean, targetMap, CopyOptions.create().setIgnoreNullValue(ignoreNullValue).setFieldNameEditor(keyEditor)).copy();
    }

    public static Map<String, Object> beanToMap(Object bean, Map<String, Object> targetMap, CopyOptions copyOptions) {
        if (null == bean) {
            return null;
        }
        return (Map) BeanCopier.create(bean, targetMap, copyOptions).copy();
    }

    public static <T> T copyProperties(Object obj, Class<T> cls, String... strArr) {
        T t = (T) ReflectUtil.newInstanceIfPossible(cls);
        copyProperties(obj, t, CopyOptions.create().setIgnoreProperties(strArr));
        return t;
    }

    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        copyProperties(source, target, CopyOptions.create().setIgnoreProperties(ignoreProperties));
    }

    public static void copyProperties(Object source, Object target, boolean ignoreCase) {
        BeanCopier.create(source, target, CopyOptions.create().setIgnoreCase(ignoreCase)).copy();
    }

    public static void copyProperties(Object source, Object target, CopyOptions copyOptions) {
        BeanCopier.create(source, target, (CopyOptions) ObjectUtil.defaultIfNull(copyOptions, (Supplier<? extends CopyOptions>) CopyOptions::create)).copy();
    }

    public static <T> List<T> copyToList(Collection<?> collection, Class<T> targetType, CopyOptions copyOptions) {
        if (null == collection) {
            return null;
        }
        if (collection.isEmpty()) {
            return new ArrayList(0);
        }
        return (List) collection.stream().map(source -> {
            Object objNewInstanceIfPossible = ReflectUtil.newInstanceIfPossible(targetType);
            copyProperties(source, objNewInstanceIfPossible, copyOptions);
            return objNewInstanceIfPossible;
        }).collect(Collectors.toList());
    }

    public static <T> List<T> copyToList(Collection<?> collection, Class<T> targetType) {
        return copyToList(collection, targetType, CopyOptions.create());
    }

    public static boolean isMatchName(Object bean, String beanClassName, boolean isSimple) {
        if (null == bean || StrUtil.isBlank(beanClassName)) {
            return false;
        }
        return ClassUtil.getClassName(bean, isSimple).equals(isSimple ? StrUtil.upperFirst(beanClassName) : beanClassName);
    }

    public static <T> T edit(T bean, Editor<Field> editor) throws SecurityException {
        if (bean == null) {
            return null;
        }
        Field[] fields = ReflectUtil.getFields(bean.getClass());
        for (Field field : fields) {
            if (!ModifierUtil.isStatic(field)) {
                editor.edit(field);
            }
        }
        return bean;
    }

    public static <T> T trimStrFields(T t, String... strArr) {
        return (T) edit(t, field -> {
            String val;
            if (strArr != null && ArrayUtil.containsIgnoreCase(strArr, field.getName())) {
                return field;
            }
            if (String.class.equals(field.getType()) && null != (val = (String) ReflectUtil.getFieldValue(t, field))) {
                String trimVal = StrUtil.trim(val);
                if (false == val.equals(trimVal)) {
                    ReflectUtil.setFieldValue(t, field, trimVal);
                }
            }
            return field;
        });
    }

    public static boolean isNotEmpty(Object bean, String... ignoreFiledNames) {
        return false == isEmpty(bean, ignoreFiledNames);
    }

    public static boolean isEmpty(Object bean, String... ignoreFiledNames) throws SecurityException {
        if (null != bean) {
            for (Field field : ReflectUtil.getFields(bean.getClass())) {
                if (!ModifierUtil.isStatic(field) && false == ArrayUtil.contains(ignoreFiledNames, field.getName()) && null != ReflectUtil.getFieldValue(bean, field)) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public static boolean hasNullField(Object bean, String... ignoreFiledNames) throws SecurityException {
        if (null == bean) {
            return true;
        }
        for (Field field : ReflectUtil.getFields(bean.getClass())) {
            if (!ModifierUtil.isStatic(field) && false == ArrayUtil.contains(ignoreFiledNames, field.getName()) && null == ReflectUtil.getFieldValue(bean, field)) {
                return true;
            }
        }
        return false;
    }

    public static String getFieldName(String getterOrSetterName) {
        if (getterOrSetterName.startsWith(ch.qos.logback.core.joran.util.beans.BeanUtil.PREFIX_GETTER_GET) || getterOrSetterName.startsWith("set")) {
            return StrUtil.removePreAndLowerFirst(getterOrSetterName, 3);
        }
        if (getterOrSetterName.startsWith(ch.qos.logback.core.joran.util.beans.BeanUtil.PREFIX_GETTER_IS)) {
            return StrUtil.removePreAndLowerFirst(getterOrSetterName, 2);
        }
        throw new IllegalArgumentException("Invalid Getter or Setter name: " + getterOrSetterName);
    }
}
