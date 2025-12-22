package org.springframework.boot.context.properties.bind;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.boot.context.properties.source.ConfigurationProperty;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.context.properties.source.ConfigurationPropertyState;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/bind/Binder.class */
public class Binder {
    private static final Set<Class<?>> NON_BEAN_CLASSES = Collections.unmodifiableSet(new HashSet(Arrays.asList(Object.class, Class.class)));
    private static final List<BeanBinder> BEAN_BINDERS;
    private final Iterable<ConfigurationPropertySource> sources;
    private final PlaceholdersResolver placeholdersResolver;
    private final ConversionService conversionService;
    private final Consumer<PropertyEditorRegistry> propertyEditorInitializer;

    static {
        List<BeanBinder> binders = new ArrayList<>();
        binders.add(new JavaBeanBinder());
        BEAN_BINDERS = Collections.unmodifiableList(binders);
    }

    public Binder(ConfigurationPropertySource... sources) {
        this(Arrays.asList(sources), null, null, null);
    }

    public Binder(Iterable<ConfigurationPropertySource> sources) {
        this(sources, null, null, null);
    }

    public Binder(Iterable<ConfigurationPropertySource> sources, PlaceholdersResolver placeholdersResolver) {
        this(sources, placeholdersResolver, null, null);
    }

    public Binder(Iterable<ConfigurationPropertySource> sources, PlaceholdersResolver placeholdersResolver, ConversionService conversionService) {
        this(sources, placeholdersResolver, conversionService, null);
    }

    public Binder(Iterable<ConfigurationPropertySource> sources, PlaceholdersResolver placeholdersResolver, ConversionService conversionService, Consumer<PropertyEditorRegistry> propertyEditorInitializer) {
        Assert.notNull(sources, "Sources must not be null");
        this.sources = sources;
        this.placeholdersResolver = placeholdersResolver != null ? placeholdersResolver : PlaceholdersResolver.NONE;
        this.conversionService = conversionService != null ? conversionService : ApplicationConversionService.getSharedInstance();
        this.propertyEditorInitializer = propertyEditorInitializer;
    }

    public <T> BindResult<T> bind(String name, Class<T> target) {
        return bind(name, Bindable.of(target));
    }

    public <T> BindResult<T> bind(String name, Bindable<T> target) {
        return bind(ConfigurationPropertyName.of(name), target, (BindHandler) null);
    }

    public <T> BindResult<T> bind(ConfigurationPropertyName name, Bindable<T> target) {
        return bind(name, target, (BindHandler) null);
    }

    public <T> BindResult<T> bind(String name, Bindable<T> target, BindHandler handler) {
        return bind(ConfigurationPropertyName.of(name), target, handler);
    }

    public <T> BindResult<T> bind(ConfigurationPropertyName name, Bindable<T> target, BindHandler handler) {
        Assert.notNull(name, "Name must not be null");
        Assert.notNull(target, "Target must not be null");
        BindHandler handler2 = handler != null ? handler : BindHandler.DEFAULT;
        Context context = new Context();
        return BindResult.of(bind(name, target, handler2, context, false));
    }

    protected final <T> T bind(ConfigurationPropertyName configurationPropertyName, Bindable<T> bindable, BindHandler bindHandler, Context context, boolean z) {
        context.clearConfigurationProperty();
        try {
            bindable = bindHandler.onStart(configurationPropertyName, bindable, context);
            if (bindable == null) {
                return null;
            }
            return (T) handleBindResult(configurationPropertyName, bindable, bindHandler, context, bindObject(configurationPropertyName, bindable, bindHandler, context, z));
        } catch (Exception e) {
            return (T) handleBindError(configurationPropertyName, bindable, bindHandler, context, e);
        }
    }

    private <T> T handleBindResult(ConfigurationPropertyName configurationPropertyName, Bindable<T> bindable, BindHandler bindHandler, Context context, Object obj) throws Exception {
        if (obj != null) {
            obj = context.getConverter().convert(bindHandler.onSuccess(configurationPropertyName, bindable, context, obj), bindable);
        }
        bindHandler.onFinish(configurationPropertyName, bindable, context, obj);
        return (T) context.getConverter().convert(obj, bindable);
    }

    private <T> T handleBindError(ConfigurationPropertyName configurationPropertyName, Bindable<T> bindable, BindHandler bindHandler, Context context, Exception exc) {
        try {
            return (T) context.getConverter().convert(bindHandler.onFailure(configurationPropertyName, bindable, context, exc), bindable);
        } catch (Exception e) {
            if (e instanceof BindException) {
                throw ((BindException) e);
            }
            throw new BindException(configurationPropertyName, bindable, context.getConfigurationProperty(), e);
        }
    }

    private <T> Object bindObject(ConfigurationPropertyName name, Bindable<T> target, BindHandler handler, Context context, boolean allowRecursiveBinding) {
        ConfigurationProperty property = findProperty(name, context);
        if (property == null && containsNoDescendantOf(context.getSources(), name)) {
            return null;
        }
        AggregateBinder<?> aggregateBinder = getAggregateBinder(target, context);
        if (aggregateBinder != null) {
            return bindAggregate(name, target, handler, context, aggregateBinder);
        }
        if (property != null) {
            try {
                return bindProperty(target, context, property);
            } catch (ConverterNotFoundException ex) {
                Object bean = bindBean(name, target, handler, context, allowRecursiveBinding);
                if (bean != null) {
                    return bean;
                }
                throw ex;
            }
        }
        return bindBean(name, target, handler, context, allowRecursiveBinding);
    }

    private AggregateBinder<?> getAggregateBinder(Bindable<?> target, Context context) {
        Class<?> resolvedType = target.getType().resolve(Object.class);
        if (Map.class.isAssignableFrom(resolvedType)) {
            return new MapBinder(context);
        }
        if (Collection.class.isAssignableFrom(resolvedType)) {
            return new CollectionBinder(context);
        }
        if (target.getType().isArray()) {
            return new ArrayBinder(context);
        }
        return null;
    }

    private <T> Object bindAggregate(ConfigurationPropertyName name, Bindable<T> target, BindHandler handler, Context context, AggregateBinder<?> aggregateBinder) {
        AggregateElementBinder elementBinder = (itemName, itemTarget, source) -> {
            boolean allowRecursiveBinding = aggregateBinder.isAllowRecursiveBinding(source);
            Supplier<?> supplier = () -> {
                return bind(itemName, itemTarget, handler, context, allowRecursiveBinding);
            };
            return context.withSource(source, supplier);
        };
        return context.withIncreasedDepth(() -> {
            return aggregateBinder.bind(name, target, elementBinder);
        });
    }

    private ConfigurationProperty findProperty(ConfigurationPropertyName name, Context context) {
        if (name.isEmpty()) {
            return null;
        }
        for (ConfigurationPropertySource source : context.getSources()) {
            ConfigurationProperty property = source.getConfigurationProperty(name);
            if (property != null) {
                return property;
            }
        }
        return null;
    }

    private <T> Object bindProperty(Bindable<T> target, Context context, ConfigurationProperty property) {
        context.setConfigurationProperty(property);
        Object result = property.getValue();
        return context.getConverter().convert(this.placeholdersResolver.resolvePlaceholders(result), target);
    }

    private Object bindBean(ConfigurationPropertyName name, Bindable<?> target, BindHandler handler, Context context, boolean allowRecursiveBinding) {
        if (containsNoDescendantOf(context.getSources(), name) || isUnbindableBean(name, target, context)) {
            return null;
        }
        BeanPropertyBinder propertyBinder = (propertyName, propertyTarget) -> {
            return bind(name.append(propertyName), propertyTarget, handler, context, false);
        };
        Class<?> type = target.getType().resolve(Object.class);
        if (!allowRecursiveBinding && context.hasBoundBean(type)) {
            return null;
        }
        return context.withBean(type, () -> {
            return BEAN_BINDERS.stream().map(b -> {
                return b.bind(name, target, context, propertyBinder);
            }).filter(Objects::nonNull).findFirst().orElse(null);
        });
    }

    private boolean isUnbindableBean(ConfigurationPropertyName name, Bindable<?> target, Context context) {
        for (ConfigurationPropertySource source : context.getSources()) {
            if (source.containsDescendantOf(name) == ConfigurationPropertyState.PRESENT) {
                return false;
            }
        }
        Class<?> resolved = target.getType().resolve(Object.class);
        if (resolved.isPrimitive() || NON_BEAN_CLASSES.contains(resolved)) {
            return true;
        }
        return resolved.getName().startsWith("java.");
    }

    private boolean containsNoDescendantOf(Iterable<ConfigurationPropertySource> sources, ConfigurationPropertyName name) {
        for (ConfigurationPropertySource source : sources) {
            if (source.containsDescendantOf(name) != ConfigurationPropertyState.ABSENT) {
                return false;
            }
        }
        return true;
    }

    public static Binder get(Environment environment) {
        return new Binder(ConfigurationPropertySources.get(environment), new PropertySourcesPlaceholdersResolver(environment));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/bind/Binder$Context.class */
    final class Context implements BindContext {
        private final BindConverter converter;
        private int depth;
        private int sourcePushCount;
        private ConfigurationProperty configurationProperty;
        private final List<ConfigurationPropertySource> source = Arrays.asList((ConfigurationPropertySource) null);
        private final Deque<Class<?>> beans = new ArrayDeque();

        Context() {
            this.converter = BindConverter.get(Binder.this.conversionService, Binder.this.propertyEditorInitializer);
        }

        private void increaseDepth() {
            this.depth++;
        }

        private void decreaseDepth() {
            this.depth--;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T> T withSource(ConfigurationPropertySource source, Supplier<T> supplier) {
            if (source == null) {
                return supplier.get();
            }
            this.source.set(0, source);
            this.sourcePushCount++;
            try {
                T t = supplier.get();
                this.sourcePushCount--;
                return t;
            } catch (Throwable th) {
                this.sourcePushCount--;
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T> T withBean(Class<?> cls, Supplier<T> supplier) {
            this.beans.push(cls);
            try {
                T t = (T) withIncreasedDepth(supplier);
                this.beans.pop();
                return t;
            } catch (Throwable th) {
                this.beans.pop();
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasBoundBean(Class<?> bean) {
            return this.beans.contains(bean);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T> T withIncreasedDepth(Supplier<T> supplier) {
            increaseDepth();
            try {
                return supplier.get();
            } finally {
                decreaseDepth();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfigurationProperty(ConfigurationProperty configurationProperty) {
            this.configurationProperty = configurationProperty;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConfigurationProperty() {
            this.configurationProperty = null;
        }

        public PlaceholdersResolver getPlaceholdersResolver() {
            return Binder.this.placeholdersResolver;
        }

        public BindConverter getConverter() {
            return this.converter;
        }

        @Override // org.springframework.boot.context.properties.bind.BindContext
        public Binder getBinder() {
            return Binder.this;
        }

        @Override // org.springframework.boot.context.properties.bind.BindContext
        public int getDepth() {
            return this.depth;
        }

        @Override // org.springframework.boot.context.properties.bind.BindContext
        public Iterable<ConfigurationPropertySource> getSources() {
            if (this.sourcePushCount <= 0) {
                return Binder.this.sources;
            }
            return this.source;
        }

        @Override // org.springframework.boot.context.properties.bind.BindContext
        public ConfigurationProperty getConfigurationProperty() {
            return this.configurationProperty;
        }
    }
}
