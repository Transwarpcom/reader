package org.bson.codecs.pojo;

import java.lang.reflect.InvocationTargetException;
import org.bson.codecs.configuration.CodecConfigurationException;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PropertyAccessorImpl.class */
final class PropertyAccessorImpl<T> implements PropertyAccessor<T> {
    private final PropertyMetadata<T> propertyMetadata;

    PropertyAccessorImpl(PropertyMetadata<T> propertyMetadata) {
        this.propertyMetadata = propertyMetadata;
    }

    @Override // org.bson.codecs.pojo.PropertyAccessor
    public <S> T get(S s) {
        try {
            if (this.propertyMetadata.isSerializable()) {
                if (this.propertyMetadata.getGetter() != null) {
                    return (T) this.propertyMetadata.getGetter().invoke(s, new Object[0]);
                }
                return (T) this.propertyMetadata.getField().get(s);
            }
            throw getError(null);
        } catch (Exception e) {
            throw getError(e);
        }
    }

    @Override // org.bson.codecs.pojo.PropertyAccessor
    public <S> void set(S instance, T value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            if (this.propertyMetadata.isDeserializable()) {
                if (this.propertyMetadata.getSetter() != null) {
                    this.propertyMetadata.getSetter().invoke(instance, value);
                } else {
                    this.propertyMetadata.getField().set(instance, value);
                }
            }
        } catch (Exception e) {
            throw setError(e);
        }
    }

    PropertyMetadata<T> getPropertyMetadata() {
        return this.propertyMetadata;
    }

    private CodecConfigurationException getError(Exception cause) {
        return new CodecConfigurationException(String.format("Unable to get value for property '%s' in %s", this.propertyMetadata.getName(), this.propertyMetadata.getDeclaringClassName()), cause);
    }

    private CodecConfigurationException setError(Exception cause) {
        return new CodecConfigurationException(String.format("Unable to set value for property '%s' in %s", this.propertyMetadata.getName(), this.propertyMetadata.getDeclaringClassName()), cause);
    }
}
