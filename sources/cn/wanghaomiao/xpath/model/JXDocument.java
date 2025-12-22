package cn.wanghaomiao.xpath.model;

import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.exception.XpathSyntaxErrorException;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:cn/wanghaomiao/xpath/model/JXDocument.class */
public class JXDocument {
    private org.seimicrawler.xpath.JXDocument jxDoc;

    public JXDocument(Document doc) {
        this.jxDoc = org.seimicrawler.xpath.JXDocument.create(doc);
    }

    public JXDocument(String html) {
        this.jxDoc = org.seimicrawler.xpath.JXDocument.create(html);
    }

    public JXDocument(Elements els) {
        this.jxDoc = org.seimicrawler.xpath.JXDocument.create(els);
    }

    public List<Object> sel(String xpath) throws XpathSyntaxErrorException {
        return this.jxDoc.sel(xpath);
    }

    public List<JXNode> selN(String xpath) throws XpathSyntaxErrorException {
        List<JXNode> finalRes = new LinkedList<>();
        List<org.seimicrawler.xpath.JXNode> jxNodeList = this.jxDoc.selN(xpath);
        for (org.seimicrawler.xpath.JXNode n : jxNodeList) {
            if (n.isString()) {
                finalRes.add(JXNode.t(n.asString()));
            } else {
                finalRes.add(JXNode.e(n.asElement()));
            }
        }
        return finalRes;
    }

    public Object selOne(String xpath) throws XpathSyntaxErrorException {
        JXNode jxNode = selNOne(xpath);
        if (jxNode != null) {
            if (jxNode.isText()) {
                return jxNode.getTextVal();
            }
            return jxNode.getElement();
        }
        return null;
    }

    public JXNode selNOne(String xpath) throws XpathSyntaxErrorException {
        List<JXNode> jxNodeList = selN(xpath);
        if (jxNodeList != null && jxNodeList.size() > 0) {
            return jxNodeList.get(0);
        }
        return null;
    }
}
