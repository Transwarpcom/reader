package org.bson.codecs.pojo;

import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/TypeWithTypeParameters.class */
public interface TypeWithTypeParameters<T> {
    Class<T> getType();

    List<? extends TypeWithTypeParameters<?>> getTypeParameters();
}
