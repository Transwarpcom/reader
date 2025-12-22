package com.jayway.jsonpath.spi.mapper;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.TypeRef;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/mapper/TapestryMappingProvider.class */
public class TapestryMappingProvider implements MappingProvider {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [T, java.util.ArrayList] */
    @Override // com.jayway.jsonpath.spi.mapper.MappingProvider
    public <T> T map(Object obj, Class<T> cls, Configuration configuration) {
        if (obj == 0) {
            return null;
        }
        if (cls.isAssignableFrom(obj.getClass())) {
            return obj;
        }
        try {
            if (cls.isAssignableFrom(ArrayList.class) && configuration.jsonProvider().isArray(obj)) {
                ?? r0 = (T) new ArrayList(configuration.jsonProvider().length(obj));
                Iterator<?> it = configuration.jsonProvider().toIterable(obj).iterator();
                while (it.hasNext()) {
                    r0.add(it.next());
                }
                return r0;
            }
        } catch (Exception e) {
        }
        throw new MappingException("Cannot convert a " + obj.getClass().getName() + " to a " + cls + " use Tapestry's TypeCoercer instead.");
    }

    @Override // com.jayway.jsonpath.spi.mapper.MappingProvider
    public <T> T map(Object source, TypeRef<T> targetType, Configuration configuration) {
        throw new UnsupportedOperationException("Tapestry JSON provider does not support TypeRef! Use a Jackson or Gson based provider");
    }
}
