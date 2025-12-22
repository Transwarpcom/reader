package com.jayway.jsonpath.internal;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.InvalidModificationException;
import com.jayway.jsonpath.MapFunction;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.spi.json.JsonProvider;
import java.util.Collection;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/PathRef.class */
public abstract class PathRef implements Comparable<PathRef> {
    public static final PathRef NO_OP = new PathRef(null) { // from class: com.jayway.jsonpath.internal.PathRef.1
        @Override // com.jayway.jsonpath.internal.PathRef, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(PathRef pathRef) {
            return super.compareTo(pathRef);
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public Object getAccessor() {
            return null;
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void set(Object newVal, Configuration configuration) {
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void convert(MapFunction mapFunction, Configuration configuration) {
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void delete(Configuration configuration) {
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void add(Object newVal, Configuration configuration) {
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void put(String key, Object newVal, Configuration configuration) {
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void renameKey(String oldKeyName, String newKeyName, Configuration configuration) {
        }
    };
    protected Object parent;

    abstract Object getAccessor();

    public abstract void set(Object obj, Configuration configuration);

    public abstract void convert(MapFunction mapFunction, Configuration configuration);

    public abstract void delete(Configuration configuration);

    public abstract void add(Object obj, Configuration configuration);

    public abstract void put(String str, Object obj, Configuration configuration);

    public abstract void renameKey(String str, String str2, Configuration configuration);

    private PathRef(Object parent) {
        this.parent = parent;
    }

    protected void renameInMap(Object targetMap, String oldKeyName, String newKeyName, Configuration configuration) {
        if (configuration.jsonProvider().isMap(targetMap)) {
            if (configuration.jsonProvider().getMapValue(targetMap, oldKeyName) == JsonProvider.UNDEFINED) {
                throw new PathNotFoundException("No results for Key " + oldKeyName + " found in map!");
            }
            configuration.jsonProvider().setProperty(targetMap, newKeyName, configuration.jsonProvider().getMapValue(targetMap, oldKeyName));
            configuration.jsonProvider().removeProperty(targetMap, oldKeyName);
            return;
        }
        throw new InvalidModificationException("Can only rename properties in a map");
    }

    protected boolean targetInvalid(Object target) {
        return target == JsonProvider.UNDEFINED || target == null;
    }

    @Override // java.lang.Comparable
    public int compareTo(PathRef o) {
        return getAccessor().toString().compareTo(o.getAccessor().toString()) * (-1);
    }

    public static PathRef create(Object obj, String property) {
        return new ObjectPropertyPathRef(obj, property);
    }

    public static PathRef create(Object obj, Collection<String> properties) {
        return new ObjectMultiPropertyPathRef(obj, properties);
    }

    public static PathRef create(Object array, int index) {
        return new ArrayIndexPathRef(array, index);
    }

    public static PathRef createRoot(Object root) {
        return new RootPathRef(root);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/PathRef$RootPathRef.class */
    private static class RootPathRef extends PathRef {
        @Override // com.jayway.jsonpath.internal.PathRef, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(PathRef pathRef) {
            return super.compareTo(pathRef);
        }

        private RootPathRef(Object parent) {
            super(parent);
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        Object getAccessor() {
            return PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX;
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void set(Object newVal, Configuration configuration) {
            throw new InvalidModificationException("Invalid set operation");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void convert(MapFunction mapFunction, Configuration configuration) {
            throw new InvalidModificationException("Invalid map operation");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void delete(Configuration configuration) {
            throw new InvalidModificationException("Invalid delete operation");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void add(Object newVal, Configuration configuration) {
            if (configuration.jsonProvider().isArray(this.parent)) {
                configuration.jsonProvider().setArrayIndex(this.parent, configuration.jsonProvider().length(this.parent), newVal);
                return;
            }
            throw new InvalidModificationException("Invalid add operation. $ is not an array");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void put(String key, Object newVal, Configuration configuration) {
            if (configuration.jsonProvider().isMap(this.parent)) {
                configuration.jsonProvider().setProperty(this.parent, key, newVal);
                return;
            }
            throw new InvalidModificationException("Invalid put operation. $ is not a map");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void renameKey(String oldKeyName, String newKeyName, Configuration configuration) {
            Object target = this.parent;
            if (targetInvalid(target)) {
                return;
            }
            renameInMap(target, oldKeyName, newKeyName, configuration);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/PathRef$ArrayIndexPathRef.class */
    private static class ArrayIndexPathRef extends PathRef {
        private int index;

        private ArrayIndexPathRef(Object parent, int index) {
            super(parent);
            this.index = index;
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void set(Object newVal, Configuration configuration) {
            configuration.jsonProvider().setArrayIndex(this.parent, this.index, newVal);
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void convert(MapFunction mapFunction, Configuration configuration) {
            Object currentValue = configuration.jsonProvider().getArrayIndex(this.parent, this.index);
            configuration.jsonProvider().setArrayIndex(this.parent, this.index, mapFunction.map(currentValue, configuration));
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void delete(Configuration configuration) {
            configuration.jsonProvider().removeProperty(this.parent, Integer.valueOf(this.index));
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void add(Object value, Configuration configuration) {
            Object target = configuration.jsonProvider().getArrayIndex(this.parent, this.index);
            if (targetInvalid(target)) {
                return;
            }
            if (configuration.jsonProvider().isArray(target)) {
                configuration.jsonProvider().setProperty(target, null, value);
                return;
            }
            throw new InvalidModificationException("Can only add to an array");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void put(String key, Object value, Configuration configuration) {
            Object target = configuration.jsonProvider().getArrayIndex(this.parent, this.index);
            if (targetInvalid(target)) {
                return;
            }
            if (configuration.jsonProvider().isMap(target)) {
                configuration.jsonProvider().setProperty(target, key, value);
                return;
            }
            throw new InvalidModificationException("Can only add properties to a map");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void renameKey(String oldKeyName, String newKeyName, Configuration configuration) {
            Object target = configuration.jsonProvider().getArrayIndex(this.parent, this.index);
            if (targetInvalid(target)) {
                return;
            }
            renameInMap(target, oldKeyName, newKeyName, configuration);
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public Object getAccessor() {
            return Integer.valueOf(this.index);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.jayway.jsonpath.internal.PathRef, java.lang.Comparable
        public int compareTo(PathRef o) {
            if (o instanceof ArrayIndexPathRef) {
                ArrayIndexPathRef pf = (ArrayIndexPathRef) o;
                return Integer.compare(pf.index, this.index);
            }
            return super.compareTo(o);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/PathRef$ObjectPropertyPathRef.class */
    private static class ObjectPropertyPathRef extends PathRef {
        private String property;

        @Override // com.jayway.jsonpath.internal.PathRef, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(PathRef pathRef) {
            return super.compareTo(pathRef);
        }

        private ObjectPropertyPathRef(Object parent, String property) {
            super(parent);
            this.property = property;
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void set(Object newVal, Configuration configuration) {
            configuration.jsonProvider().setProperty(this.parent, this.property, newVal);
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void convert(MapFunction mapFunction, Configuration configuration) {
            Object currentValue = configuration.jsonProvider().getMapValue(this.parent, this.property);
            configuration.jsonProvider().setProperty(this.parent, this.property, mapFunction.map(currentValue, configuration));
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void delete(Configuration configuration) {
            configuration.jsonProvider().removeProperty(this.parent, this.property);
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void add(Object value, Configuration configuration) {
            Object target = configuration.jsonProvider().getMapValue(this.parent, this.property);
            if (targetInvalid(target)) {
                return;
            }
            if (configuration.jsonProvider().isArray(target)) {
                configuration.jsonProvider().setArrayIndex(target, configuration.jsonProvider().length(target), value);
                return;
            }
            throw new InvalidModificationException("Can only add to an array");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void put(String key, Object value, Configuration configuration) {
            Object target = configuration.jsonProvider().getMapValue(this.parent, this.property);
            if (targetInvalid(target)) {
                return;
            }
            if (configuration.jsonProvider().isMap(target)) {
                configuration.jsonProvider().setProperty(target, key, value);
                return;
            }
            throw new InvalidModificationException("Can only add properties to a map");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void renameKey(String oldKeyName, String newKeyName, Configuration configuration) {
            Object target = configuration.jsonProvider().getMapValue(this.parent, this.property);
            if (targetInvalid(target)) {
                return;
            }
            renameInMap(target, oldKeyName, newKeyName, configuration);
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public Object getAccessor() {
            return this.property;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/PathRef$ObjectMultiPropertyPathRef.class */
    private static class ObjectMultiPropertyPathRef extends PathRef {
        private Collection<String> properties;

        @Override // com.jayway.jsonpath.internal.PathRef, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(PathRef pathRef) {
            return super.compareTo(pathRef);
        }

        private ObjectMultiPropertyPathRef(Object parent, Collection<String> properties) {
            super(parent);
            this.properties = properties;
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void set(Object newVal, Configuration configuration) {
            for (String property : this.properties) {
                configuration.jsonProvider().setProperty(this.parent, property, newVal);
            }
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void convert(MapFunction mapFunction, Configuration configuration) {
            for (String property : this.properties) {
                Object currentValue = configuration.jsonProvider().getMapValue(this.parent, property);
                if (currentValue != JsonProvider.UNDEFINED) {
                    configuration.jsonProvider().setProperty(this.parent, property, mapFunction.map(currentValue, configuration));
                }
            }
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void delete(Configuration configuration) {
            for (String property : this.properties) {
                configuration.jsonProvider().removeProperty(this.parent, property);
            }
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void add(Object newVal, Configuration configuration) {
            throw new InvalidModificationException("Add can not be performed to multiple properties");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void put(String key, Object newVal, Configuration configuration) {
            throw new InvalidModificationException("Put can not be performed to multiple properties");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public void renameKey(String oldKeyName, String newKeyName, Configuration configuration) {
            throw new InvalidModificationException("Rename can not be performed to multiple properties");
        }

        @Override // com.jayway.jsonpath.internal.PathRef
        public Object getAccessor() {
            return Utils.join("&&", this.properties);
        }
    }
}
