package cn.wanghaomiao.xpath.model;

import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.exception.XpathSyntaxErrorException;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:cn/wanghaomiao/xpath/model/JXNode.class */
public class JXNode {
    private Element element;
    private boolean isText;
    private String textVal;

    public Element getElement() {
        return this.element;
    }

    public JXNode setElement(Element element) {
        this.element = element;
        return this;
    }

    public boolean isText() {
        return this.isText;
    }

    public JXNode setText(boolean text) {
        this.isText = text;
        return this;
    }

    public String getTextVal() {
        return this.textVal;
    }

    public JXNode setTextVal(String textVal) {
        this.textVal = textVal;
        return this;
    }

    public List<JXNode> sel(String xpath) throws XpathSyntaxErrorException {
        if (this.element == null) {
            return null;
        }
        JXDocument doc = new JXDocument(new Elements(this.element));
        return doc.selN(xpath);
    }

    public static JXNode e(Element element) {
        JXNode n = new JXNode();
        n.setElement(element).setText(false);
        return n;
    }

    public static JXNode t(String txt) {
        JXNode n = new JXNode();
        n.setTextVal(txt).setText(true);
        return n;
    }

    public String toString() {
        if (this.isText) {
            return this.textVal;
        }
        return this.element.toString();
    }
}
