package org.bson.codecs.pojo;

import java.util.Collection;
import java.util.Map;
import org.bson.codecs.configuration.CodecConfigurationException;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/ConventionUseGettersAsSettersImpl.class */
final class ConventionUseGettersAsSettersImpl implements Convention {
    ConventionUseGettersAsSettersImpl() {
    }

    @Override // org.bson.codecs.pojo.Convention
    public void apply(ClassModelBuilder<?> classModelBuilder) {
        for (PropertyModelBuilder<?> propertyModelBuilder : classModelBuilder.getPropertyModelBuilders()) {
            if (!(propertyModelBuilder.getPropertyAccessor() instanceof PropertyAccessorImpl)) {
                throw new CodecConfigurationException(String.format("The USE_GETTER_AS_SETTER_CONVENTION is not compatible with propertyModelBuilder instance that have custom implementations of org.bson.codecs.pojo.PropertyAccessor: %s", propertyModelBuilder.getPropertyAccessor().getClass().getName()));
            }
            PropertyAccessorImpl<?> defaultAccessor = (PropertyAccessorImpl) propertyModelBuilder.getPropertyAccessor();
            PropertyMetadata<?> propertyMetaData = defaultAccessor.getPropertyMetadata();
            if (!propertyMetaData.isDeserializable() && propertyMetaData.isSerializable() && isMapOrCollection(propertyMetaData.getTypeData().getType())) {
                setPropertyAccessor(propertyModelBuilder);
            }
        }
    }

    private <T> boolean isMapOrCollection(Class<T> clazz) {
        return Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz);
    }

    private <T> void setPropertyAccessor(PropertyModelBuilder<T> propertyModelBuilder) {
        propertyModelBuilder.propertyAccessor(new PrivateProperyAccessor((PropertyAccessorImpl) propertyModelBuilder.getPropertyAccessor()));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/ConventionUseGettersAsSettersImpl$PrivateProperyAccessor.class */
    private static final class PrivateProperyAccessor<T> implements PropertyAccessor<T> {
        private final PropertyAccessorImpl<T> wrapped;

        private PrivateProperyAccessor(PropertyAccessorImpl<T> wrapped) {
            this.wrapped = wrapped;
        }

        @Override // org.bson.codecs.pojo.PropertyAccessor
        public <S> T get(S instance) {
            return this.wrapped.get(instance);
        }

        @Override // org.bson.codecs.pojo.PropertyAccessor
        public <S> void set(S instance, T value) {
            if (value instanceof Collection) {
                mutateCollection(instance, (Collection) value);
            } else if (value instanceof Map) {
                mutateMap(instance, (Map) value);
            } else {
                throwCodecConfigurationException(String.format("Unexpected type: '%s'", value.getClass()), null);
            }
        }

        private <S> void mutateCollection(S instance, Collection value) {
            T originalCollection = get(instance);
            Collection<?> collection = (Collection) originalCollection;
            if (collection == null) {
                throwCodecConfigurationException("The getter returned null.", null);
                return;
            }
            if (!collection.isEmpty()) {
                throwCodecConfigurationException("The getter returned a non empty collection.", null);
                return;
            }
            try {
                collection.addAll(value);
            } catch (Exception e) {
                throwCodecConfigurationException("collection#addAll failed.", e);
            }
        }

        private <S> void mutateMap(S instance, Map value) {
            T originalMap = get(instance);
            Map<?, ?> map = (Map) originalMap;
            if (map == null) {
                throwCodecConfigurationException("The getter returned null.", null);
                return;
            }
            if (!map.isEmpty()) {
                throwCodecConfigurationException("The getter returned a non empty map.", null);
                return;
            }
            try {
                map.putAll(value);
            } catch (Exception e) {
                throwCodecConfigurationException("map#putAll failed.", e);
            }
        }

        private void throwCodecConfigurationException(String reason, Exception cause) {
            throw new CodecConfigurationException(String.format("Cannot use getter in '%s' to set '%s'. %s", this.wrapped.getPropertyMetadata().getDeclaringClassName(), this.wrapped.getPropertyMetadata().getName(), reason), cause);
        }
    }
}
