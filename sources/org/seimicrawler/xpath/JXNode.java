package org.seimicrawler.xpath;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/JXNode.class */
public class JXNode {
    private Object value;

    public JXNode(Object val) {
        this.value = val;
    }

    public boolean isElement() {
        return this.value instanceof Element;
    }

    public Element asElement() {
        return (Element) this.value;
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    public String asString() {
        if (isString()) {
            return (String) this.value;
        }
        if (isElement()) {
            Element e = (Element) this.value;
            if (Objects.equals(e.tagName(), Constants.DEF_TEXT_TAG_NAME)) {
                return e.ownText();
            }
            return e.toString();
        }
        return String.valueOf(this.value);
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    public Double asDouble() {
        return (Double) this.value;
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    public Boolean asBoolean() {
        return (Boolean) this.value;
    }

    public boolean isDate() {
        return this.value instanceof Date;
    }

    public Date asDate() {
        return (Date) this.value;
    }

    public List<JXNode> sel(String xpath) {
        if (!isElement()) {
            return null;
        }
        JXDocument doc = new JXDocument(new Elements(asElement()));
        return doc.selN(xpath);
    }

    public JXNode selOne(String xpath) {
        List<JXNode> jxNodeList = sel(xpath);
        if (jxNodeList != null && jxNodeList.size() > 0) {
            return jxNodeList.get(0);
        }
        return null;
    }

    public static JXNode create(Object val) {
        return new JXNode(val);
    }

    public String toString() {
        return asString();
    }

    public Object value() {
        if (isElement()) {
            return asElement();
        }
        if (isBoolean()) {
            return asBoolean();
        }
        if (isNumber()) {
            return asDouble();
        }
        if (isDate()) {
            return asDate();
        }
        return asString();
    }
}
