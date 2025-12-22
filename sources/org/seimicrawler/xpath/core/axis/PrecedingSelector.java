package org.seimicrawler.xpath.core.axis;

import java.util.Iterator;
import java.util.LinkedList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.core.AxisSelector;
import org.seimicrawler.xpath.core.XValue;
import org.seimicrawler.xpath.util.CommonUtil;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/axis/PrecedingSelector.class */
public class PrecedingSelector implements AxisSelector {
    @Override // org.seimicrawler.xpath.core.AxisSelector
    public String name() {
        return "preceding";
    }

    @Override // org.seimicrawler.xpath.core.AxisSelector
    public XValue apply(Elements context) {
        Elements preceding = new Elements();
        LinkedList linkedList = new LinkedList();
        Iterator<Element> it = context.iterator();
        while (it.hasNext()) {
            Element el = it.next();
            Elements p = el.parents();
            Iterator<Element> it2 = p.iterator();
            while (it2.hasNext()) {
                Element pe = it2.next();
                Elements ps = CommonUtil.precedingSibling(pe);
                if (ps != null) {
                    linkedList.addAll(ps);
                }
            }
            Elements ps2 = CommonUtil.precedingSibling(el);
            if (ps2 != null) {
                linkedList.addAll(ps2);
            }
        }
        preceding.addAll(linkedList);
        return XValue.create(preceding);
    }
}
