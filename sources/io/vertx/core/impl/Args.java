package io.vertx.core.impl;

import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/Args.class */
public class Args {
    public final Map<String, String> map = new HashMap();

    public Args(String[] args) {
        String currentKey = null;
        for (String arg : args) {
            if (arg.startsWith("-")) {
                if (currentKey != null) {
                    this.map.put(currentKey, "");
                }
                currentKey = arg;
            } else if (currentKey != null) {
                this.map.put(currentKey, arg);
                currentKey = null;
            }
        }
        if (currentKey != null) {
            this.map.put(currentKey, "");
        }
    }

    public int getInt(String argName) throws NumberFormatException {
        int val;
        String arg = this.map.get(argName);
        if (arg != null) {
            try {
                val = Integer.parseInt(arg.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid " + argName + ": " + arg);
            }
        } else {
            val = -1;
        }
        return val;
    }
}
