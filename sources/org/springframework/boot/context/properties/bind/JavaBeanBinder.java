package org.springframework.boot.context.properties.bind;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertyState;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/bind/JavaBeanBinder.class */
class JavaBeanBinder implements BeanBinder {
    JavaBeanBinder() {
    }

    @Override // org.springframework.boot.context.properties.bind.BeanBinder
    public <T> T bind(ConfigurationPropertyName name, Bindable<T> target, Binder.Context context, BeanPropertyBinder propertyBinder) {
        boolean hasKnownBindableProperties = hasKnownBindableProperties(name, context);
        Bean<T> bean = Bean.get(target, hasKnownBindableProperties);
        if (bean == null) {
            return null;
        }
        BeanSupplier<T> beanSupplier = bean.getSupplier(target);
        boolean bound = bind(propertyBinder, bean, beanSupplier);
        if (bound) {
            return beanSupplier.get();
        }
        return null;
    }

    private boolean hasKnownBindableProperties(ConfigurationPropertyName name, Binder.Context context) {
        for (ConfigurationPropertySource source : context.getSources()) {
            if (source.containsDescendantOf(name) == ConfigurationPropertyState.PRESENT) {
                return true;
            }
        }
        return false;
    }

    private <T> boolean bind(BeanPropertyBinder propertyBinder, Bean<T> bean, BeanSupplier<T> beanSupplier) {
        boolean bound = false;
        for (BeanProperty beanProperty : bean.getProperties().values()) {
            bound |= bind(beanSupplier, propertyBinder, beanProperty);
        }
        return bound;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> boolean bind(BeanSupplier<T> beanSupplier, BeanPropertyBinder propertyBinder, BeanProperty property) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String propertyName = property.getName();
        ResolvableType type = property.getType();
        Supplier<Object> value = property.getValue(beanSupplier);
        Annotation[] annotations = property.getAnnotations();
        Object bound = propertyBinder.bindProperty(propertyName, Bindable.of(type).withSuppliedValue(value).withAnnotations(annotations));
        if (bound == null) {
            return false;
        }
        if (property.isSettable()) {
            property.setValue(beanSupplier, bound);
            return true;
        }
        if (value == null || !bound.equals(value.get())) {
            throw new IllegalStateException("No setter found for property: " + property.getName());
        }
        return true;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/bind/JavaBeanBinder$Bean.class */
    static class Bean<T> {
        private static Bean<?> cached;
        private final ResolvableType type;
        private final Class<?> resolvedType;
        private final Map<String, BeanProperty> properties = new LinkedHashMap();

        Bean(ResolvableType type, Class<?> resolvedType) throws SecurityException {
            this.type = type;
            this.resolvedType = resolvedType;
            addProperties(resolvedType);
        }

        private void addProperties(Class<?> type) throws SecurityException {
            while (type != null && !Object.class.equals(type)) {
                Method[] declaredMethods = type.getDeclaredMethods();
                Field[] declaredFields = type.getDeclaredFields();
                addProperties(declaredMethods, declaredFields);
                type = type.getSuperclass();
            }
        }

        protected void addProperties(Method[] declaredMethods, Field[] declaredFields) {
            for (int i = 0; i < declaredMethods.length; i++) {
                if (!isCandidate(declaredMethods[i])) {
                    declaredMethods[i] = null;
                }
            }
            for (Method method : declaredMethods) {
                addMethodIfPossible(method, BeanUtil.PREFIX_GETTER_GET, 0, (v0, v1) -> {
                    v0.addGetter(v1);
                });
                addMethodIfPossible(method, BeanUtil.PREFIX_GETTER_IS, 0, (v0, v1) -> {
                    v0.addGetter(v1);
                });
            }
            for (Method method2 : declaredMethods) {
                addMethodIfPossible(method2, "set", 1, (v0, v1) -> {
                    v0.addSetter(v1);
                });
            }
            for (Field field : declaredFields) {
                addField(field);
            }
        }

        private boolean isCandidate(Method method) {
            int modifiers = method.getModifiers();
            return (!Modifier.isPublic(modifiers) || Modifier.isAbstract(modifiers) || Modifier.isStatic(modifiers) || Object.class.equals(method.getDeclaringClass()) || Class.class.equals(method.getDeclaringClass())) ? false : true;
        }

        private void addMethodIfPossible(Method method, String prefix, int parameterCount, BiConsumer<BeanProperty, Method> consumer) {
            if (method != null && method.getParameterCount() == parameterCount && method.getName().startsWith(prefix) && method.getName().length() > prefix.length()) {
                String propertyName = Introspector.decapitalize(method.getName().substring(prefix.length()));
                consumer.accept(this.properties.computeIfAbsent(propertyName, this::getBeanProperty), method);
            }
        }

        private BeanProperty getBeanProperty(String name) {
            return new BeanProperty(name, this.type);
        }

        private void addField(Field field) {
            BeanProperty property = this.properties.get(field.getName());
            if (property != null) {
                property.addField(field);
            }
        }

        public Map<String, BeanProperty> getProperties() {
            return this.properties;
        }

        public BeanSupplier<T> getSupplier(Bindable<T> target) {
            return new BeanSupplier<>(() -> {
                Object instance = null;
                if (target.getValue() != null) {
                    instance = target.getValue().get();
                }
                if (instance == null) {
                    instance = BeanUtils.instantiateClass(this.resolvedType);
                }
                return instance;
            });
        }

        public static <T> Bean<T> get(Bindable<T> bindable, boolean z) {
            ResolvableType type = bindable.getType();
            Class<?> clsResolve = type.resolve(Object.class);
            Supplier<T> value = bindable.getValue();
            T t = null;
            if (z && value != null) {
                t = value.get();
                clsResolve = t != null ? t.getClass() : clsResolve;
            }
            if (t == null && !isInstantiable(clsResolve)) {
                return null;
            }
            Bean<?> bean = cached;
            if (bean == null || !bean.isOfType(type, clsResolve)) {
                bean = new Bean<>(type, clsResolve);
                cached = bean;
            }
            return (Bean<T>) bean;
        }

        private static boolean isInstantiable(Class<?> type) throws NoSuchMethodException, SecurityException {
            if (type.isInterface()) {
                return false;
            }
            try {
                type.getDeclaredConstructor(new Class[0]);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        private boolean isOfType(ResolvableType type, Class<?> resolvedType) {
            if (this.type.hasGenerics() || type.hasGenerics()) {
                return this.type.equals(type);
            }
            return this.resolvedType != null && this.resolvedType.equals(resolvedType);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/bind/JavaBeanBinder$BeanSupplier.class */
    private static class BeanSupplier<T> implements Supplier<T> {
        private final Supplier<T> factory;
        private T instance;

        BeanSupplier(Supplier<T> factory) {
            this.factory = factory;
        }

        @Override // java.util.function.Supplier
        public T get() {
            if (this.instance == null) {
                this.instance = this.factory.get();
            }
            return this.instance;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/bind/JavaBeanBinder$BeanProperty.class */
    static class BeanProperty {
        private final String name;
        private final ResolvableType declaringClassType;
        private Method getter;
        private Method setter;
        private Field field;

        BeanProperty(String name, ResolvableType declaringClassType) {
            this.name = BeanPropertyName.toDashedForm(name);
            this.declaringClassType = declaringClassType;
        }

        public void addGetter(Method getter) {
            if (this.getter == null) {
                this.getter = getter;
            }
        }

        public void addSetter(Method setter) {
            if (this.setter == null || isBetterSetter(setter)) {
                this.setter = setter;
            }
        }

        private boolean isBetterSetter(Method setter) {
            return this.getter != null && this.getter.getReturnType().equals(setter.getParameterTypes()[0]);
        }

        public void addField(Field field) {
            if (this.field == null) {
                this.field = field;
            }
        }

        public String getName() {
            return this.name;
        }

        public ResolvableType getType() {
            if (this.setter != null) {
                MethodParameter methodParameter = new MethodParameter(this.setter, 0);
                return ResolvableType.forMethodParameter(methodParameter, this.declaringClassType);
            }
            MethodParameter methodParameter2 = new MethodParameter(this.getter, -1);
            return ResolvableType.forMethodParameter(methodParameter2, this.declaringClassType);
        }

        public Annotation[] getAnnotations() {
            try {
                if (this.field != null) {
                    return this.field.getDeclaredAnnotations();
                }
                return null;
            } catch (Exception e) {
                return null;
            }
        }

        public Supplier<Object> getValue(Supplier<?> instance) {
            if (this.getter == null) {
                return null;
            }
            return () -> {
                try {
                    this.getter.setAccessible(true);
                    return this.getter.invoke(instance.get(), new Object[0]);
                } catch (Exception ex) {
                    throw new IllegalStateException("Unable to get value for property " + this.name, ex);
                }
            };
        }

        public boolean isSettable() {
            return this.setter != null;
        }

        public void setValue(Supplier<?> instance, Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            try {
                this.setter.setAccessible(true);
                this.setter.invoke(instance.get(), value);
            } catch (Exception ex) {
                throw new IllegalStateException("Unable to set value for property " + this.name, ex);
            }
        }
    }
}
