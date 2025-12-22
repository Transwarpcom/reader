package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import javax.annotation.Nullable;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/Elements.class */
public class Elements extends ArrayList<Element> {
    public Elements() {
    }

    public Elements(int initialCapacity) {
        super(initialCapacity);
    }

    public Elements(Collection<Element> elements) {
        super(elements);
    }

    public Elements(List<Element> elements) {
        super(elements);
    }

    public Elements(Element... elements) {
        super(Arrays.asList(elements));
    }

    @Override // java.util.ArrayList
    public Elements clone() {
        Elements clone = new Elements(size());
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element e = it.next();
            clone.add(e.mo5203clone());
        }
        return clone;
    }

    public String attr(String attributeKey) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            if (element.hasAttr(attributeKey)) {
                return element.attr(attributeKey);
            }
        }
        return "";
    }

    public boolean hasAttr(String attributeKey) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            if (element.hasAttr(attributeKey)) {
                return true;
            }
        }
        return false;
    }

    public List<String> eachAttr(String attributeKey) {
        List<String> attrs = new ArrayList<>(size());
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            if (element.hasAttr(attributeKey)) {
                attrs.add(element.attr(attributeKey));
            }
        }
        return attrs;
    }

    public Elements attr(String attributeKey, String attributeValue) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.attr(attributeKey, attributeValue);
        }
        return this;
    }

    public Elements removeAttr(String attributeKey) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.removeAttr(attributeKey);
        }
        return this;
    }

    public Elements addClass(String className) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.addClass(className);
        }
        return this;
    }

    public Elements removeClass(String className) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.removeClass(className);
        }
        return this;
    }

    public Elements toggleClass(String className) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.toggleClass(className);
        }
        return this;
    }

    public boolean hasClass(String className) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            if (element.hasClass(className)) {
                return true;
            }
        }
        return false;
    }

    public String val() {
        if (size() > 0) {
            return first().val();
        }
        return "";
    }

    public Elements val(String value) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.val(value);
        }
        return this;
    }

    public String text() {
        StringBuilder sb = StringUtil.borrowBuilder();
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(element.text());
        }
        return StringUtil.releaseBuilder(sb);
    }

    public boolean hasText() {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            if (element.hasText()) {
                return true;
            }
        }
        return false;
    }

    public List<String> eachText() {
        ArrayList<String> texts = new ArrayList<>(size());
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element el = it.next();
            if (el.hasText()) {
                texts.add(el.text());
            }
        }
        return texts;
    }

    public String html() {
        StringBuilder sb = StringUtil.borrowBuilder();
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            if (sb.length() != 0) {
                sb.append("\n");
            }
            sb.append(element.html());
        }
        return StringUtil.releaseBuilder(sb);
    }

    public String outerHtml() {
        StringBuilder sb = StringUtil.borrowBuilder();
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            if (sb.length() != 0) {
                sb.append("\n");
            }
            sb.append(element.outerHtml());
        }
        return StringUtil.releaseBuilder(sb);
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return outerHtml();
    }

    public Elements tagName(String tagName) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.tagName(tagName);
        }
        return this;
    }

    public Elements html(String html) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.html(html);
        }
        return this;
    }

    public Elements prepend(String html) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.prepend(html);
        }
        return this;
    }

    public Elements append(String html) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.append(html);
        }
        return this;
    }

    public Elements before(String html) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.before(html);
        }
        return this;
    }

    public Elements after(String html) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.after(html);
        }
        return this;
    }

    public Elements wrap(String html) {
        Validate.notEmpty(html);
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.wrap(html);
        }
        return this;
    }

    public Elements unwrap() {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.unwrap();
        }
        return this;
    }

    public Elements empty() {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.empty();
        }
        return this;
    }

    public Elements remove() {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element element = it.next();
            element.remove();
        }
        return this;
    }

    public Elements select(String query) {
        return Selector.select(query, this);
    }

    public Elements not(String query) {
        Elements out = Selector.select(query, this);
        return Selector.filterOut(this, out);
    }

    public Elements eq(int index) {
        return size() > index ? new Elements(get(index)) : new Elements();
    }

    public boolean is(String query) {
        Evaluator eval = QueryParser.parse(query);
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element e = it.next();
            if (e.is(eval)) {
                return true;
            }
        }
        return false;
    }

    public Elements next() {
        return siblings(null, true, false);
    }

    public Elements next(String query) {
        return siblings(query, true, false);
    }

    public Elements nextAll() {
        return siblings(null, true, true);
    }

    public Elements nextAll(String query) {
        return siblings(query, true, true);
    }

    public Elements prev() {
        return siblings(null, false, false);
    }

    public Elements prev(String query) {
        return siblings(query, false, false);
    }

    public Elements prevAll() {
        return siblings(null, false, true);
    }

    public Elements prevAll(String query) {
        return siblings(query, false, true);
    }

    private Elements siblings(@Nullable String query, boolean next, boolean all) {
        Elements els = new Elements();
        Evaluator eval = query != null ? QueryParser.parse(query) : null;
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element e = it.next();
            do {
                Element sib = next ? e.nextElementSibling() : e.previousElementSibling();
                if (sib != null) {
                    if (eval == null || sib.is(eval)) {
                        els.add(sib);
                    }
                    e = sib;
                }
            } while (all);
        }
        return els;
    }

    public Elements parents() {
        HashSet<Element> combo = new LinkedHashSet<>();
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element e = it.next();
            combo.addAll(e.parents());
        }
        return new Elements(combo);
    }

    @Nullable
    public Element first() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }

    @Nullable
    public Element last() {
        if (isEmpty()) {
            return null;
        }
        return get(size() - 1);
    }

    public Elements traverse(NodeVisitor nodeVisitor) {
        NodeTraversor.traverse(nodeVisitor, this);
        return this;
    }

    public Elements filter(NodeFilter nodeFilter) {
        NodeTraversor.filter(nodeFilter, this);
        return this;
    }

    public List<FormElement> forms() {
        ArrayList<FormElement> forms = new ArrayList<>();
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element el = it.next();
            if (el instanceof FormElement) {
                forms.add((FormElement) el);
            }
        }
        return forms;
    }

    public List<Comment> comments() {
        return childNodesOfType(Comment.class);
    }

    public List<TextNode> textNodes() {
        return childNodesOfType(TextNode.class);
    }

    public List<DataNode> dataNodes() {
        return childNodesOfType(DataNode.class);
    }

    private <T extends Node> List<T> childNodesOfType(Class<T> tClass) {
        ArrayList<T> nodes = new ArrayList<>();
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element el = it.next();
            for (int i = 0; i < el.childNodeSize(); i++) {
                Node node = el.childNode(i);
                if (tClass.isInstance(node)) {
                    nodes.add(tClass.cast(node));
                }
            }
        }
        return nodes;
    }
}
