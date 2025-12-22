package org.seimicrawler.xpath.core.axis;

import org.jsoup.select.Elements;
import org.seimicrawler.xpath.core.AxisSelector;
import org.seimicrawler.xpath.core.XValue;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/axis/SelfSelector.class */
public class SelfSelector implements AxisSelector {
    @Override // org.seimicrawler.xpath.core.AxisSelector
    public String name() {
        return "self";
    }

    @Override // org.seimicrawler.xpath.core.AxisSelector
    public XValue apply(Elements es) {
        return XValue.create(es);
    }
}
