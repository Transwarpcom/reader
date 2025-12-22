package org.seimicrawler.xpath.core.axis;

import java.util.Iterator;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.core.AxisSelector;
import org.seimicrawler.xpath.core.XValue;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/axis/ChildSelector.class */
public class ChildSelector implements AxisSelector {
    @Override // org.seimicrawler.xpath.core.AxisSelector
    public String name() {
        return "child";
    }

    @Override // org.seimicrawler.xpath.core.AxisSelector
    public XValue apply(Elements context) {
        Elements childs = new Elements();
        Iterator<Element> it = context.iterator();
        while (it.hasNext()) {
            Element el = it.next();
            childs.addAll(el.children());
        }
        return XValue.create(childs);
    }
}
