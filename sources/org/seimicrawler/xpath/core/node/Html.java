package org.seimicrawler.xpath.core.node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Element;
import org.seimicrawler.xpath.core.NodeTest;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/node/Html.class */
public class Html implements NodeTest {
    @Override // org.seimicrawler.xpath.core.NodeTest
    public String name() {
        return "html";
    }

    @Override // org.seimicrawler.xpath.core.NodeTest
    public XValue call(Scope scope) {
        List<String> res = new LinkedList<>();
        Iterator<Element> it = scope.context().iterator();
        while (it.hasNext()) {
            Element e = it.next();
            res.add(e.html());
        }
        return XValue.create(res);
    }
}
