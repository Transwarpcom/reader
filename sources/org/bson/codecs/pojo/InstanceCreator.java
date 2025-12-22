package org.bson.codecs.pojo;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/InstanceCreator.class */
public interface InstanceCreator<T> {
    <S> void set(S s, PropertyModel<S> propertyModel);

    T getInstance();
}
