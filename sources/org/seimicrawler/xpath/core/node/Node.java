package org.seimicrawler.xpath.core.node;

import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.core.NodeTest;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/node/Node.class */
public class Node implements NodeTest {
    @Override // org.seimicrawler.xpath.core.NodeTest
    public String name() {
        return "node";
    }

    @Override // org.seimicrawler.xpath.core.NodeTest
    public XValue call(Scope scope) {
        Elements context = new Elements();
        Iterator<Element> it = scope.context().iterator();
        while (it.hasNext()) {
            Element el = it.next();
            context.addAll(el.children());
            String txt = el.ownText();
            if (StringUtils.isNotBlank(txt)) {
                Element et = new Element("");
                et.appendText(txt);
                context.add(et);
            }
        }
        return XValue.create(context);
    }
}
