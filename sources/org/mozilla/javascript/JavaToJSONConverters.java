package org.mozilla.javascript;

import java.util.function.UnaryOperator;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/JavaToJSONConverters.class */
public class JavaToJSONConverters {
    public static final UnaryOperator<Object> STRING = o -> {
        return o.toString();
    };

    private JavaToJSONConverters() {
    }
}
