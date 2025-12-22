package org.seimicrawler.xpath.core.axis;

import java.util.HashSet;
import java.util.Iterator;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.core.AxisSelector;
import org.seimicrawler.xpath.core.XValue;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/axis/DescendantSelector.class */
public class DescendantSelector implements AxisSelector {
    @Override // org.seimicrawler.xpath.core.AxisSelector
    public String name() {
        return "descendant";
    }

    @Override // org.seimicrawler.xpath.core.AxisSelector
    public XValue apply(Elements context) {
        HashSet hashSet = new HashSet();
        Elements descendant = new Elements();
        Iterator<Element> it = context.iterator();
        while (it.hasNext()) {
            Element el = it.next();
            Elements tmp = el.getAllElements();
            tmp.remove(el);
            hashSet.addAll(tmp);
        }
        descendant.addAll(hashSet);
        return XValue.create(descendant);
    }
}
