package org.seimicrawler.xpath.core;

import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/Function.class */
public interface Function {
    String name();

    XValue call(Scope scope, List<XValue> list);
}
