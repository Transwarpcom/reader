package org.seimicrawler.xpath.core.axis;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.core.AxisSelector;
import org.seimicrawler.xpath.core.XValue;
import org.seimicrawler.xpath.util.CommonUtil;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/axis/FollowingSelector.class */
public class FollowingSelector implements AxisSelector {
    @Override // org.seimicrawler.xpath.core.AxisSelector
    public String name() {
        return "following";
    }

    @Override // org.seimicrawler.xpath.core.AxisSelector
    public XValue apply(Elements context) {
        List<Element> total = new LinkedList<>();
        Iterator<Element> it = context.iterator();
        while (it.hasNext()) {
            Element el = it.next();
            Elements p = el.parents();
            Iterator<Element> it2 = p.iterator();
            while (it2.hasNext()) {
                Element pe = it2.next();
                Elements fs = CommonUtil.followingSibling(pe);
                if (fs != null) {
                    Iterator<Element> it3 = fs.iterator();
                    while (it3.hasNext()) {
                        Element pse = it3.next();
                        total.addAll(pse.getAllElements());
                    }
                }
            }
            Elements fs2 = CommonUtil.followingSibling(el);
            if (fs2 != null) {
                Iterator<Element> it4 = fs2.iterator();
                while (it4.hasNext()) {
                    Element se = it4.next();
                    total.addAll(se.getAllElements());
                }
            }
        }
        return XValue.create(new Elements(total));
    }
}
