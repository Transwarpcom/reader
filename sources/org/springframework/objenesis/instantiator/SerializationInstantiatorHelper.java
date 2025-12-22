package org.springframework.objenesis.instantiator;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/objenesis/instantiator/SerializationInstantiatorHelper.class */
public class SerializationInstantiatorHelper {
    public static <T> Class<? super T> getNonSerializableSuperClass(Class<T> cls) {
        Class<T> superclass = cls;
        while (Serializable.class.isAssignableFrom(superclass)) {
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                throw new Error("Bad class hierarchy: No non-serializable parents");
            }
        }
        return (Class<? super T>) superclass;
    }
}
