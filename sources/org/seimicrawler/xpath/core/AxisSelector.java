package org.seimicrawler.xpath.core;

import org.jsoup.select.Elements;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/AxisSelector.class */
public interface AxisSelector {
    String name();

    XValue apply(Elements elements);
}
