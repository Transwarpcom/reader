package org.bson.codecs.pojo;

import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/ClassModel.class */
public final class ClassModel<T> {
    private final String name;
    private final Class<T> type;
    private final boolean hasTypeParameters;
    private final InstanceCreatorFactory<T> instanceCreatorFactory;
    private final boolean discriminatorEnabled;
    private final String discriminatorKey;
    private final String discriminator;
    private final PropertyModel<?> idProperty;
    private final List<PropertyModel<?>> propertyModels;
    private final Map<String, TypeParameterMap> propertyNameToTypeParameterMap;

    ClassModel(Class<T> clazz, Map<String, TypeParameterMap> propertyNameToTypeParameterMap, InstanceCreatorFactory<T> instanceCreatorFactory, Boolean discriminatorEnabled, String discriminatorKey, String discriminator, PropertyModel<?> idProperty, List<PropertyModel<?>> propertyModels) {
        this.name = clazz.getSimpleName();
        this.type = clazz;
        this.hasTypeParameters = clazz.getTypeParameters().length > 0;
        this.propertyNameToTypeParameterMap = propertyNameToTypeParameterMap;
        this.instanceCreatorFactory = instanceCreatorFactory;
        this.discriminatorEnabled = discriminatorEnabled.booleanValue();
        this.discriminatorKey = discriminatorKey;
        this.discriminator = discriminator;
        this.idProperty = idProperty;
        this.propertyModels = propertyModels;
    }

    public static <S> ClassModelBuilder<S> builder(Class<S> type) {
        return new ClassModelBuilder<>(type);
    }

    InstanceCreator<T> getInstanceCreator() {
        return this.instanceCreatorFactory.create();
    }

    public Class<T> getType() {
        return this.type;
    }

    public boolean hasTypeParameters() {
        return this.hasTypeParameters;
    }

    public boolean useDiscriminator() {
        return this.discriminatorEnabled;
    }

    public String getDiscriminatorKey() {
        return this.discriminatorKey;
    }

    public String getDiscriminator() {
        return this.discriminator;
    }

    public PropertyModel<?> getPropertyModel(String propertyName) {
        for (PropertyModel<?> propertyModel : this.propertyModels) {
            if (propertyModel.getName().equals(propertyName)) {
                return propertyModel;
            }
        }
        return null;
    }

    public List<PropertyModel<?>> getPropertyModels() {
        return this.propertyModels;
    }

    public PropertyModel<?> getIdPropertyModel() {
        return this.idProperty;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "ClassModel{type=" + this.type + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassModel<?> that = (ClassModel) o;
        if (this.discriminatorEnabled != that.discriminatorEnabled || !getType().equals(that.getType()) || !getInstanceCreatorFactory().equals(that.getInstanceCreatorFactory())) {
            return false;
        }
        if (getDiscriminatorKey() != null) {
            if (!getDiscriminatorKey().equals(that.getDiscriminatorKey())) {
                return false;
            }
        } else if (that.getDiscriminatorKey() != null) {
            return false;
        }
        if (getDiscriminator() != null) {
            if (!getDiscriminator().equals(that.getDiscriminator())) {
                return false;
            }
        } else if (that.getDiscriminator() != null) {
            return false;
        }
        if (this.idProperty != null) {
            if (!this.idProperty.equals(that.idProperty)) {
                return false;
            }
        } else if (that.idProperty != null) {
            return false;
        }
        if (!getPropertyModels().equals(that.getPropertyModels()) || !getPropertyNameToTypeParameterMap().equals(that.getPropertyNameToTypeParameterMap())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = getType().hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + getInstanceCreatorFactory().hashCode())) + (this.discriminatorEnabled ? 1 : 0))) + (getDiscriminatorKey() != null ? getDiscriminatorKey().hashCode() : 0))) + (getDiscriminator() != null ? getDiscriminator().hashCode() : 0))) + (this.idProperty != null ? this.idProperty.hashCode() : 0))) + getPropertyModels().hashCode())) + getPropertyNameToTypeParameterMap().hashCode();
    }

    InstanceCreatorFactory<T> getInstanceCreatorFactory() {
        return this.instanceCreatorFactory;
    }

    Map<String, TypeParameterMap> getPropertyNameToTypeParameterMap() {
        return this.propertyNameToTypeParameterMap;
    }
}
