package org.springframework.boot.context.properties.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.util.ObjectUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/source/SpringIterableConfigurationPropertySource.class */
class SpringIterableConfigurationPropertySource extends SpringConfigurationPropertySource implements IterableConfigurationPropertySource {
    private volatile Cache cache;

    SpringIterableConfigurationPropertySource(EnumerablePropertySource<?> propertySource, PropertyMapper mapper) {
        super(propertySource, mapper, null);
        assertEnumerablePropertySource();
    }

    private void assertEnumerablePropertySource() {
        if (getPropertySource() instanceof MapPropertySource) {
            try {
                ((MapPropertySource) getPropertySource()).getSource().size();
            } catch (UnsupportedOperationException e) {
                throw new IllegalArgumentException("PropertySource must be fully enumerable");
            }
        }
    }

    @Override // org.springframework.boot.context.properties.source.SpringConfigurationPropertySource, org.springframework.boot.context.properties.source.ConfigurationPropertySource
    public ConfigurationProperty getConfigurationProperty(ConfigurationPropertyName name) {
        ConfigurationProperty configurationProperty = super.getConfigurationProperty(name);
        if (configurationProperty == null) {
            configurationProperty = find(getPropertyMappings(getCache()), name);
        }
        return configurationProperty;
    }

    @Override // org.springframework.boot.context.properties.source.IterableConfigurationPropertySource
    public Stream<ConfigurationPropertyName> stream() {
        return getConfigurationPropertyNames().stream();
    }

    @Override // org.springframework.boot.context.properties.source.IterableConfigurationPropertySource, java.lang.Iterable
    public Iterator<ConfigurationPropertyName> iterator() {
        return getConfigurationPropertyNames().iterator();
    }

    @Override // org.springframework.boot.context.properties.source.SpringConfigurationPropertySource, org.springframework.boot.context.properties.source.ConfigurationPropertySource
    public ConfigurationPropertyState containsDescendantOf(ConfigurationPropertyName name) {
        name.getClass();
        return ConfigurationPropertyState.search(this, name::isAncestorOf);
    }

    private List<ConfigurationPropertyName> getConfigurationPropertyNames() {
        Cache cache = getCache();
        List<ConfigurationPropertyName> names = cache != null ? cache.getNames() : null;
        if (names != null) {
            return names;
        }
        PropertyMapping[] mappings = getPropertyMappings(cache);
        List<ConfigurationPropertyName> names2 = new ArrayList<>(mappings.length);
        for (PropertyMapping mapping : mappings) {
            names2.add(mapping.getConfigurationPropertyName());
        }
        List<ConfigurationPropertyName> names3 = Collections.unmodifiableList(names2);
        if (cache != null) {
            cache.setNames(names3);
        }
        return names3;
    }

    private PropertyMapping[] getPropertyMappings(Cache cache) {
        PropertyMapping[] result = cache != null ? cache.getMappings() : null;
        if (result != null) {
            return result;
        }
        String[] names = getPropertySource().getPropertyNames();
        List<PropertyMapping> mappings = new ArrayList<>(names.length * 2);
        for (String name : names) {
            for (PropertyMapping mapping : getMapper().map(name)) {
                mappings.add(mapping);
            }
        }
        PropertyMapping[] result2 = (PropertyMapping[]) mappings.toArray(new PropertyMapping[0]);
        if (cache != null) {
            cache.setMappings(result2);
        }
        return result2;
    }

    private Cache getCache() {
        CacheKey key = CacheKey.get(getPropertySource());
        if (key == null) {
            return null;
        }
        Cache cache = this.cache;
        if (cache != null) {
            try {
                if (cache.hasKeyEqualTo(key)) {
                    return cache;
                }
            } catch (ConcurrentModificationException e) {
                return null;
            }
        }
        Cache cache2 = new Cache(key.copy());
        this.cache = cache2;
        return cache2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.boot.context.properties.source.SpringConfigurationPropertySource
    public EnumerablePropertySource<?> getPropertySource() {
        return (EnumerablePropertySource) super.getPropertySource();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/source/SpringIterableConfigurationPropertySource$Cache.class */
    private static class Cache {
        private final CacheKey key;
        private List<ConfigurationPropertyName> names;
        private PropertyMapping[] mappings;

        Cache(CacheKey key) {
            this.key = key;
        }

        public boolean hasKeyEqualTo(CacheKey key) {
            return this.key.equals(key);
        }

        public List<ConfigurationPropertyName> getNames() {
            return this.names;
        }

        public void setNames(List<ConfigurationPropertyName> names) {
            this.names = names;
        }

        public PropertyMapping[] getMappings() {
            return this.mappings;
        }

        public void setMappings(PropertyMapping[] mappings) {
            this.mappings = mappings;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/source/SpringIterableConfigurationPropertySource$CacheKey.class */
    private static final class CacheKey {
        private final Object key;

        private CacheKey(Object key) {
            this.key = key;
        }

        public CacheKey copy() {
            return new CacheKey(copyKey(this.key));
        }

        private Object copyKey(Object key) {
            if (key instanceof Set) {
                return new HashSet((Set) key);
            }
            return ((String[]) key).clone();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return ObjectUtils.nullSafeEquals(this.key, ((CacheKey) obj).key);
        }

        public int hashCode() {
            return this.key.hashCode();
        }

        public static CacheKey get(EnumerablePropertySource<?> source) {
            if (source instanceof MapPropertySource) {
                return new CacheKey(((MapPropertySource) source).getSource().keySet());
            }
            return new CacheKey(source.getPropertyNames());
        }
    }
}
