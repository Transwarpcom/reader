package org.bson.codecs.pojo;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PropertyModelSerializationImpl.class */
class PropertyModelSerializationImpl<T> implements PropertySerialization<T> {
    PropertyModelSerializationImpl() {
    }

    @Override // org.bson.codecs.pojo.PropertySerialization
    public boolean shouldSerialize(T value) {
        return value != null;
    }
}
