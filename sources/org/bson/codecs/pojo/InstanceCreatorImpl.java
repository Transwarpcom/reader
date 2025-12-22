package org.bson.codecs.pojo;

import java.util.HashMap;
import java.util.Map;
import org.bson.codecs.configuration.CodecConfigurationException;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/InstanceCreatorImpl.class */
final class InstanceCreatorImpl<T> implements InstanceCreator<T> {
    private final CreatorExecutable<T> creatorExecutable;
    private final Map<PropertyModel<?>, Object> cachedValues;
    private final Map<String, Integer> properties;
    private final Object[] params;
    private T newInstance;

    InstanceCreatorImpl(CreatorExecutable<T> creatorExecutable) {
        this.creatorExecutable = creatorExecutable;
        if (creatorExecutable.getProperties().isEmpty()) {
            this.cachedValues = null;
            this.properties = null;
            this.params = null;
            this.newInstance = creatorExecutable.getInstance();
            return;
        }
        this.cachedValues = new HashMap();
        this.properties = new HashMap();
        for (int i = 0; i < creatorExecutable.getProperties().size(); i++) {
            if (creatorExecutable.getIdPropertyIndex() != null && creatorExecutable.getIdPropertyIndex().intValue() == i) {
                this.properties.put("_id", creatorExecutable.getIdPropertyIndex());
            } else {
                this.properties.put(creatorExecutable.getProperties().get(i).value(), Integer.valueOf(i));
            }
        }
        this.params = new Object[this.properties.size()];
    }

    @Override // org.bson.codecs.pojo.InstanceCreator
    public <S> void set(S value, PropertyModel<S> propertyModel) {
        if (this.newInstance != null) {
            propertyModel.getPropertyAccessor().set(this.newInstance, value);
            return;
        }
        if (!this.properties.isEmpty()) {
            String propertyName = propertyModel.getWriteName();
            if (!this.properties.containsKey(propertyName)) {
                propertyName = propertyModel.getName();
            }
            Integer index = this.properties.get(propertyName);
            if (index != null) {
                this.params[index.intValue()] = value;
            }
            this.properties.remove(propertyName);
        }
        if (this.properties.isEmpty()) {
            constructInstanceAndProcessCachedValues();
        } else {
            this.cachedValues.put(propertyModel, value);
        }
    }

    @Override // org.bson.codecs.pojo.InstanceCreator
    public T getInstance() {
        if (this.newInstance == null) {
            try {
                for (Map.Entry<String, Integer> entry : this.properties.entrySet()) {
                    this.params[entry.getValue().intValue()] = null;
                }
                constructInstanceAndProcessCachedValues();
            } catch (CodecConfigurationException e) {
                throw new CodecConfigurationException(String.format("Could not construct new instance of: %s. Missing the following properties: %s", this.creatorExecutable.getType().getSimpleName(), this.properties.keySet()), e);
            }
        }
        return this.newInstance;
    }

    private void constructInstanceAndProcessCachedValues() {
        try {
            this.newInstance = this.creatorExecutable.getInstance(this.params);
            for (Map.Entry<PropertyModel<?>, Object> entry : this.cachedValues.entrySet()) {
                setPropertyValue((PropertyModel) entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            throw new CodecConfigurationException(e.getMessage(), e);
        }
    }

    private <S> void setPropertyValue(PropertyModel<S> propertyModel, Object value) {
        set(value, propertyModel);
    }
}
