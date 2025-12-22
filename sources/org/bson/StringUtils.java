package org.bson;

import java.util.Collection;
import java.util.Iterator;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/StringUtils.class */
final class StringUtils {
    public static String join(String delimiter, Collection<?> s) {
        StringBuilder builder = new StringBuilder();
        Iterator<?> iter = s.iterator();
        while (iter.hasNext()) {
            builder.append(iter.next());
            if (!iter.hasNext()) {
                break;
            }
            builder.append(delimiter);
        }
        return builder.toString();
    }

    private StringUtils() {
    }
}
