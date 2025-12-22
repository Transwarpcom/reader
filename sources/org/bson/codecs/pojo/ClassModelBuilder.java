package org.bson.codecs.pojo;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.assertions.Assertions;
import org.bson.codecs.configuration.CodecConfigurationException;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/ClassModelBuilder.class */
public class ClassModelBuilder<T> {
    static final String ID_PROPERTY_NAME = "_id";
    private InstanceCreatorFactory<T> instanceCreatorFactory;
    private Class<T> type;
    private boolean discriminatorEnabled;
    private String discriminator;
    private String discriminatorKey;
    private String idPropertyName;
    private final List<PropertyModelBuilder<?>> propertyModelBuilders = new ArrayList();
    private Map<String, TypeParameterMap> propertyNameToTypeParameterMap = Collections.emptyMap();
    private List<Convention> conventions = Conventions.DEFAULT_CONVENTIONS;
    private List<Annotation> annotations = Collections.emptyList();

    ClassModelBuilder(Class<T> type) {
        PojoBuilderHelper.configureClassModelBuilder(this, (Class) Assertions.notNull("type", type));
    }

    public ClassModelBuilder<T> instanceCreatorFactory(InstanceCreatorFactory<T> instanceCreatorFactory) {
        this.instanceCreatorFactory = (InstanceCreatorFactory) Assertions.notNull("instanceCreatorFactory", instanceCreatorFactory);
        return this;
    }

    public InstanceCreatorFactory<T> getInstanceCreatorFactory() {
        return this.instanceCreatorFactory;
    }

    public ClassModelBuilder<T> type(Class<T> type) {
        this.type = (Class) Assertions.notNull("type", type);
        return this;
    }

    public Class<T> getType() {
        return this.type;
    }

    public ClassModelBuilder<T> conventions(List<Convention> conventions) {
        this.conventions = (List) Assertions.notNull("conventions", conventions);
        return this;
    }

    public List<Convention> getConventions() {
        return this.conventions;
    }

    public ClassModelBuilder<T> annotations(List<Annotation> annotations) {
        this.annotations = (List) Assertions.notNull("annotations", annotations);
        return this;
    }

    public List<Annotation> getAnnotations() {
        return this.annotations;
    }

    public ClassModelBuilder<T> discriminator(String discriminator) {
        this.discriminator = discriminator;
        return this;
    }

    public String getDiscriminator() {
        return this.discriminator;
    }

    public ClassModelBuilder<T> discriminatorKey(String discriminatorKey) {
        this.discriminatorKey = discriminatorKey;
        return this;
    }

    public String getDiscriminatorKey() {
        return this.discriminatorKey;
    }

    public ClassModelBuilder<T> enableDiscriminator(boolean discriminatorEnabled) {
        this.discriminatorEnabled = discriminatorEnabled;
        return this;
    }

    public Boolean useDiscriminator() {
        return Boolean.valueOf(this.discriminatorEnabled);
    }

    public ClassModelBuilder<T> idPropertyName(String idPropertyName) {
        this.idPropertyName = idPropertyName;
        return this;
    }

    public String getIdPropertyName() {
        return this.idPropertyName;
    }

    public boolean removeProperty(String propertyName) {
        return this.propertyModelBuilders.remove(getProperty((String) Assertions.notNull("propertyName", propertyName)));
    }

    public PropertyModelBuilder<?> getProperty(String propertyName) {
        Assertions.notNull("propertyName", propertyName);
        for (PropertyModelBuilder<?> propertyModelBuilder : this.propertyModelBuilders) {
            if (propertyModelBuilder.getName().equals(propertyName)) {
                return propertyModelBuilder;
            }
        }
        return null;
    }

    public List<PropertyModelBuilder<?>> getPropertyModelBuilders() {
        return Collections.unmodifiableList(this.propertyModelBuilders);
    }

    public ClassModel<T> build() {
        List<PropertyModel<?>> propertyModels = new ArrayList<>();
        PropertyModel<?> idPropertyModel = null;
        PojoBuilderHelper.stateNotNull("type", this.type);
        for (Convention convention : this.conventions) {
            convention.apply(this);
        }
        PojoBuilderHelper.stateNotNull("instanceCreatorFactory", this.instanceCreatorFactory);
        if (this.discriminatorEnabled) {
            PojoBuilderHelper.stateNotNull("discriminatorKey", this.discriminatorKey);
            PojoBuilderHelper.stateNotNull("discriminator", this.discriminator);
        }
        for (PropertyModelBuilder<?> propertyModelBuilder : this.propertyModelBuilders) {
            boolean isIdProperty = propertyModelBuilder.getName().equals(this.idPropertyName);
            if (isIdProperty) {
                propertyModelBuilder.readName(ID_PROPERTY_NAME).writeName(ID_PROPERTY_NAME);
            }
            PropertyModel<?> model = propertyModelBuilder.build();
            propertyModels.add(model);
            if (isIdProperty) {
                idPropertyModel = model;
            }
        }
        validatePropertyModels(this.type.getSimpleName(), propertyModels);
        return new ClassModel<>(this.type, this.propertyNameToTypeParameterMap, this.instanceCreatorFactory, Boolean.valueOf(this.discriminatorEnabled), this.discriminatorKey, this.discriminator, idPropertyModel, Collections.unmodifiableList(propertyModels));
    }

    public String toString() {
        return String.format("ClassModelBuilder{type=%s}", this.type);
    }

    Map<String, TypeParameterMap> getPropertyNameToTypeParameterMap() {
        return this.propertyNameToTypeParameterMap;
    }

    ClassModelBuilder<T> propertyNameToTypeParameterMap(Map<String, TypeParameterMap> propertyNameToTypeParameterMap) {
        this.propertyNameToTypeParameterMap = Collections.unmodifiableMap(new HashMap(propertyNameToTypeParameterMap));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    ClassModelBuilder<T> addProperty(PropertyModelBuilder<?> propertyModelBuilder) {
        this.propertyModelBuilders.add(Assertions.notNull("propertyModelBuilder", propertyModelBuilder));
        return this;
    }

    private void validatePropertyModels(String declaringClass, List<PropertyModel<?>> propertyModels) {
        Map<String, Integer> propertyNameMap = new HashMap<>();
        Map<String, Integer> propertyReadNameMap = new HashMap<>();
        Map<String, Integer> propertyWriteNameMap = new HashMap<>();
        for (PropertyModel<?> propertyModel : propertyModels) {
            checkForDuplicates("property", propertyModel.getName(), propertyNameMap, declaringClass);
            if (propertyModel.isReadable()) {
                checkForDuplicates("read property", propertyModel.getReadName(), propertyReadNameMap, declaringClass);
            }
            if (propertyModel.isWritable()) {
                checkForDuplicates("write property", propertyModel.getWriteName(), propertyWriteNameMap, declaringClass);
            }
        }
        if (this.idPropertyName != null && !propertyNameMap.containsKey(this.idPropertyName)) {
            throw new CodecConfigurationException(String.format("Invalid id property, property named '%s' can not be found.", this.idPropertyName));
        }
    }

    private void checkForDuplicates(String propertyType, String propertyName, Map<String, Integer> propertyNameMap, String declaringClass) {
        if (propertyNameMap.containsKey(propertyName)) {
            throw new CodecConfigurationException(String.format("Duplicate %s named '%s' found in %s.", propertyType, propertyName, declaringClass));
        }
        propertyNameMap.put(propertyName, 1);
    }
}
