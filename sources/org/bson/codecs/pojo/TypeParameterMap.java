package org.bson.codecs.pojo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/TypeParameterMap.class */
final class TypeParameterMap {
    private final Map<Integer, Integer> propertyToClassParamIndexMap;

    static Builder builder() {
        return new Builder();
    }

    Map<Integer, Integer> getPropertyToClassParamIndexMap() {
        return this.propertyToClassParamIndexMap;
    }

    boolean hasTypeParameters() {
        return !this.propertyToClassParamIndexMap.isEmpty();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/TypeParameterMap$Builder.class */
    static final class Builder {
        private final Map<Integer, Integer> propertyToClassParamIndexMap;

        private Builder() {
            this.propertyToClassParamIndexMap = new HashMap();
        }

        Builder addIndex(int classTypeParameterIndex) {
            this.propertyToClassParamIndexMap.put(-1, Integer.valueOf(classTypeParameterIndex));
            return this;
        }

        Builder addIndex(int propertyTypeParameterIndex, int classTypeParameterIndex) {
            this.propertyToClassParamIndexMap.put(Integer.valueOf(propertyTypeParameterIndex), Integer.valueOf(classTypeParameterIndex));
            return this;
        }

        TypeParameterMap build() {
            if (this.propertyToClassParamIndexMap.size() > 1 && this.propertyToClassParamIndexMap.containsKey(-1)) {
                throw new IllegalStateException("You cannot have a generic field that also has type parameters.");
            }
            return new TypeParameterMap(this.propertyToClassParamIndexMap);
        }
    }

    public String toString() {
        return "TypeParameterMap{fieldToClassParamIndexMap=" + this.propertyToClassParamIndexMap + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypeParameterMap that = (TypeParameterMap) o;
        if (!getPropertyToClassParamIndexMap().equals(that.getPropertyToClassParamIndexMap())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getPropertyToClassParamIndexMap().hashCode();
    }

    private TypeParameterMap(Map<Integer, Integer> propertyToClassParamIndexMap) {
        this.propertyToClassParamIndexMap = Collections.unmodifiableMap(propertyToClassParamIndexMap);
    }
}
