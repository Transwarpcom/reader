package com.fasterxml.jackson.databind.cfg;

/* loaded from: reader.jar:BOOT-INF/lib/jackson-databind-2.9.9.jar:com/fasterxml/jackson/databind/cfg/ConfigFeature.class */
public interface ConfigFeature {
    boolean enabledByDefault();

    int getMask();

    boolean enabledIn(int i);
}
