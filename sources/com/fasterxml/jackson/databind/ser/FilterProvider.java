package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

/* loaded from: reader.jar:BOOT-INF/lib/jackson-databind-2.9.9.jar:com/fasterxml/jackson/databind/ser/FilterProvider.class */
public abstract class FilterProvider {
    @Deprecated
    public abstract BeanPropertyFilter findFilter(Object obj);

    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {
        BeanPropertyFilter old = findFilter(filterId);
        if (old == null) {
            return null;
        }
        return SimpleBeanPropertyFilter.from(old);
    }
}
