package org.seimicrawler.xpath.core.axis;

import java.util.Iterator;
import java.util.LinkedList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.core.AxisSelector;
import org.seimicrawler.xpath.core.XValue;
import org.seimicrawler.xpath.util.CommonUtil;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/axis/PrecedingSiblingSelector.class */
public class PrecedingSiblingSelector implements AxisSelector {
    @Override // org.seimicrawler.xpath.core.AxisSelector
    public String name() {
        return "preceding-sibling";
    }

    @Override // org.seimicrawler.xpath.core.AxisSelector
    public XValue apply(Elements context) {
        LinkedList linkedList = new LinkedList();
        Iterator<Element> it = context.iterator();
        while (it.hasNext()) {
            Element el = it.next();
            Elements ps = CommonUtil.precedingSibling(el);
            if (ps != null) {
                linkedList.addAll(ps);
            }
        }
        Elements newContext = new Elements();
        newContext.addAll(linkedList);
        return XValue.create(newContext);
    }
}
