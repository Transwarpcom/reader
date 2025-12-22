package net.minidev.json;

import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/JSONStreamAwareEx.class */
public interface JSONStreamAwareEx extends JSONStreamAware {
    void writeJSONString(Appendable appendable, JSONStyle jSONStyle) throws IOException;
}
