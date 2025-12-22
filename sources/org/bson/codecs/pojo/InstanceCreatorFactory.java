package org.bson.codecs.pojo;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/InstanceCreatorFactory.class */
public interface InstanceCreatorFactory<T> {
    InstanceCreator<T> create();
}
