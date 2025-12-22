package org.bson.codecs.pojo;

import java.util.ArrayList;
import java.util.List;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PropertyCodecRegistryImpl.class */
class PropertyCodecRegistryImpl implements PropertyCodecRegistry {
    private final List<PropertyCodecProvider> propertyCodecProviders;

    PropertyCodecRegistryImpl(PojoCodec<?> pojoCodec, CodecRegistry codecRegistry, List<PropertyCodecProvider> propertyCodecProviders) {
        List<PropertyCodecProvider> augmentedProviders = new ArrayList<>();
        if (propertyCodecProviders != null) {
            augmentedProviders.addAll(propertyCodecProviders);
        }
        augmentedProviders.add(new CollectionPropertyCodecProvider());
        augmentedProviders.add(new MapPropertyCodecProvider());
        augmentedProviders.add(new EnumPropertyCodecProvider(codecRegistry));
        augmentedProviders.add(new FallbackPropertyCodecProvider(pojoCodec, codecRegistry));
        this.propertyCodecProviders = augmentedProviders;
    }

    @Override // org.bson.codecs.pojo.PropertyCodecRegistry
    public <S> Codec<S> get(TypeWithTypeParameters<S> type) {
        for (PropertyCodecProvider propertyCodecProvider : this.propertyCodecProviders) {
            Codec<S> codec = propertyCodecProvider.get(type, this);
            if (codec != null) {
                return codec;
            }
        }
        return null;
    }
}
