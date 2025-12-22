package com.fasterxml.jackson.core;

/* loaded from: reader.jar:BOOT-INF/lib/jackson-core-2.9.9.jar:com/fasterxml/jackson/core/FormatFeature.class */
public interface FormatFeature {
    boolean enabledByDefault();

    int getMask();

    boolean enabledIn(int i);
}
