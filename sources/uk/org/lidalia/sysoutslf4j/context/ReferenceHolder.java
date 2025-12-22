package uk.org.lidalia.sysoutslf4j.context;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/ReferenceHolder.class */
final class ReferenceHolder {
    private static final Map<Object, Object> REFERENCES = Collections.synchronizedMap(new IdentityHashMap());

    static void preventGarbageCollectionForLifeOfClassLoader(Object objectToBeMaintained) {
        REFERENCES.put(objectToBeMaintained, objectToBeMaintained);
    }

    private ReferenceHolder() {
        throw new UnsupportedOperationException("Not instantiable");
    }
}
