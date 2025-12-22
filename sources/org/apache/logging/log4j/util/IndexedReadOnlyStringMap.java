package org.apache.logging.log4j.util;

/* loaded from: reader.jar:BOOT-INF/lib/log4j-api-2.11.2.jar:org/apache/logging/log4j/util/IndexedReadOnlyStringMap.class */
public interface IndexedReadOnlyStringMap extends ReadOnlyStringMap {
    String getKeyAt(int i);

    <V> V getValueAt(int i);

    int indexOfKey(String str);
}
