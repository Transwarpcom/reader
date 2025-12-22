package org.jsoup.nodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Node.class */
public abstract class Node implements Cloneable {
    static final List<Node> EmptyNodes = Collections.emptyList();
    static final String EmptyString = "";

    @Nullable
    Node parentNode;
    int siblingIndex;

    public abstract String nodeName();

    protected abstract boolean hasAttributes();

    public abstract Attributes attributes();

    public abstract String baseUri();

    protected abstract void doSetBaseUri(String str);

    protected abstract List<Node> ensureChildNodes();

    public abstract int childNodeSize();

    public abstract Node empty();

    abstract void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException;

    abstract void outerHtmlTail(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException;

    protected Node() {
    }

    public boolean hasParent() {
        return this.parentNode != null;
    }

    public String attr(String attributeKey) {
        Validate.notNull(attributeKey);
        if (!hasAttributes()) {
            return "";
        }
        String val = attributes().getIgnoreCase(attributeKey);
        if (val.length() > 0) {
            return val;
        }
        if (attributeKey.startsWith("abs:")) {
            return absUrl(attributeKey.substring("abs:".length()));
        }
        return "";
    }

    public Node attr(String attributeKey, String attributeValue) {
        attributes().putIgnoreCase(NodeUtils.parser(this).settings().normalizeAttribute(attributeKey), attributeValue);
        return this;
    }

    public boolean hasAttr(String attributeKey) {
        Validate.notNull(attributeKey);
        if (!hasAttributes()) {
            return false;
        }
        if (attributeKey.startsWith("abs:")) {
            String key = attributeKey.substring("abs:".length());
            if (attributes().hasKeyIgnoreCase(key) && !absUrl(key).isEmpty()) {
                return true;
            }
        }
        return attributes().hasKeyIgnoreCase(attributeKey);
    }

    public Node removeAttr(String attributeKey) {
        Validate.notNull(attributeKey);
        if (hasAttributes()) {
            attributes().removeIgnoreCase(attributeKey);
        }
        return this;
    }

    public Node clearAttributes() {
        if (hasAttributes()) {
            Iterator<Attribute> it = attributes().iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }
        return this;
    }

    public void setBaseUri(String baseUri) {
        Validate.notNull(baseUri);
        doSetBaseUri(baseUri);
    }

    public String absUrl(String attributeKey) {
        Validate.notEmpty(attributeKey);
        if (!hasAttributes() || !attributes().hasKeyIgnoreCase(attributeKey)) {
            return "";
        }
        return StringUtil.resolve(baseUri(), attributes().getIgnoreCase(attributeKey));
    }

    public Node childNode(int index) {
        return ensureChildNodes().get(index);
    }

    public List<Node> childNodes() {
        if (childNodeSize() == 0) {
            return EmptyNodes;
        }
        List<Node> children = ensureChildNodes();
        List<Node> rewrap = new ArrayList<>(children.size());
        rewrap.addAll(children);
        return Collections.unmodifiableList(rewrap);
    }

    public List<Node> childNodesCopy() {
        List<Node> nodes = ensureChildNodes();
        ArrayList<Node> children = new ArrayList<>(nodes.size());
        for (Node node : nodes) {
            children.add(node.mo5203clone());
        }
        return children;
    }

    protected Node[] childNodesAsArray() {
        return (Node[]) ensureChildNodes().toArray(new Node[0]);
    }

    @Nullable
    public Node parent() {
        return this.parentNode;
    }

    @Nullable
    public final Node parentNode() {
        return this.parentNode;
    }

    public Node root() {
        Node node = this;
        while (true) {
            Node node2 = node;
            if (node2.parentNode != null) {
                node = node2.parentNode;
            } else {
                return node2;
            }
        }
    }

    @Nullable
    public Document ownerDocument() {
        Node root = root();
        if (root instanceof Document) {
            return (Document) root;
        }
        return null;
    }

    public void remove() {
        Validate.notNull(this.parentNode);
        this.parentNode.removeChild(this);
    }

    public Node before(String html) {
        addSiblingHtml(this.siblingIndex, html);
        return this;
    }

    public Node before(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(this.siblingIndex, node);
        return this;
    }

    public Node after(String html) {
        addSiblingHtml(this.siblingIndex + 1, html);
        return this;
    }

    public Node after(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(this.siblingIndex + 1, node);
        return this;
    }

    private void addSiblingHtml(int index, String html) {
        Validate.notNull(html);
        Validate.notNull(this.parentNode);
        Element context = parent() instanceof Element ? (Element) parent() : null;
        List<Node> nodes = NodeUtils.parser(this).parseFragmentInput(html, context, baseUri());
        this.parentNode.addChildren(index, (Node[]) nodes.toArray(new Node[0]));
    }

    public Node wrap(String html) {
        Element element;
        Validate.notEmpty(html);
        if (this.parentNode == null || !(this.parentNode instanceof Element)) {
            element = this instanceof Element ? (Element) this : null;
        } else {
            element = (Element) this.parentNode;
        }
        Element context = element;
        List<Node> wrapChildren = NodeUtils.parser(this).parseFragmentInput(html, context, baseUri());
        Node wrapNode = wrapChildren.get(0);
        if (!(wrapNode instanceof Element)) {
            return this;
        }
        Element wrap = (Element) wrapNode;
        Element deepest = getDeepChild(wrap);
        if (this.parentNode != null) {
            this.parentNode.replaceChild(this, wrap);
        }
        deepest.addChildren(this);
        if (wrapChildren.size() > 0) {
            for (int i = 0; i < wrapChildren.size(); i++) {
                Node remainder = wrapChildren.get(i);
                if (wrap != remainder) {
                    if (remainder.parentNode != null) {
                        remainder.parentNode.removeChild(remainder);
                    }
                    wrap.after(remainder);
                }
            }
        }
        return this;
    }

    @Nullable
    public Node unwrap() {
        Validate.notNull(this.parentNode);
        List<Node> childNodes = ensureChildNodes();
        Node firstChild = childNodes.size() > 0 ? childNodes.get(0) : null;
        this.parentNode.addChildren(this.siblingIndex, childNodesAsArray());
        remove();
        return firstChild;
    }

    private Element getDeepChild(Element el) {
        List<Element> children = el.children();
        if (children.size() > 0) {
            return getDeepChild(children.get(0));
        }
        return el;
    }

    void nodelistChanged() {
    }

    public void replaceWith(Node in) {
        Validate.notNull(in);
        Validate.notNull(this.parentNode);
        this.parentNode.replaceChild(this, in);
    }

    protected void setParentNode(Node parentNode) {
        Validate.notNull(parentNode);
        if (this.parentNode != null) {
            this.parentNode.removeChild(this);
        }
        this.parentNode = parentNode;
    }

    protected void replaceChild(Node out, Node in) {
        Validate.isTrue(out.parentNode == this);
        Validate.notNull(in);
        if (in.parentNode != null) {
            in.parentNode.removeChild(in);
        }
        int index = out.siblingIndex;
        ensureChildNodes().set(index, in);
        in.parentNode = this;
        in.setSiblingIndex(index);
        out.parentNode = null;
    }

    protected void removeChild(Node out) {
        Validate.isTrue(out.parentNode == this);
        int index = out.siblingIndex;
        ensureChildNodes().remove(index);
        reindexChildren(index);
        out.parentNode = null;
    }

    protected void addChildren(Node... children) {
        List<Node> nodes = ensureChildNodes();
        for (Node child : children) {
            reparentChild(child);
            nodes.add(child);
            child.setSiblingIndex(nodes.size() - 1);
        }
    }

    protected void addChildren(int index, Node... children) {
        Validate.notNull(children);
        if (children.length == 0) {
            return;
        }
        List<Node> nodes = ensureChildNodes();
        Node firstParent = children[0].parent();
        if (firstParent != null && firstParent.childNodeSize() == children.length) {
            boolean sameList = true;
            List<Node> firstParentNodes = firstParent.ensureChildNodes();
            int i = children.length;
            while (true) {
                int i2 = i;
                i--;
                if (i2 > 0) {
                    if (children[i] != firstParentNodes.get(i)) {
                        sameList = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (sameList) {
                firstParent.empty();
                nodes.addAll(index, Arrays.asList(children));
                int i3 = children.length;
                while (true) {
                    int i4 = i3;
                    i3--;
                    if (i4 > 0) {
                        children[i3].parentNode = this;
                    } else {
                        reindexChildren(index);
                        return;
                    }
                }
            }
        }
        Validate.noNullElements(children);
        for (Node child : children) {
            reparentChild(child);
        }
        nodes.addAll(index, Arrays.asList(children));
        reindexChildren(index);
    }

    protected void reparentChild(Node child) {
        child.setParentNode(this);
    }

    private void reindexChildren(int start) {
        List<Node> childNodes = ensureChildNodes();
        for (int i = start; i < childNodes.size(); i++) {
            childNodes.get(i).setSiblingIndex(i);
        }
    }

    public List<Node> siblingNodes() {
        if (this.parentNode == null) {
            return Collections.emptyList();
        }
        List<Node> nodes = this.parentNode.ensureChildNodes();
        List<Node> siblings = new ArrayList<>(nodes.size() - 1);
        for (Node node : nodes) {
            if (node != this) {
                siblings.add(node);
            }
        }
        return siblings;
    }

    @Nullable
    public Node nextSibling() {
        if (this.parentNode == null) {
            return null;
        }
        List<Node> siblings = this.parentNode.ensureChildNodes();
        int index = this.siblingIndex + 1;
        if (siblings.size() > index) {
            return siblings.get(index);
        }
        return null;
    }

    @Nullable
    public Node previousSibling() {
        if (this.parentNode != null && this.siblingIndex > 0) {
            return this.parentNode.ensureChildNodes().get(this.siblingIndex - 1);
        }
        return null;
    }

    public int siblingIndex() {
        return this.siblingIndex;
    }

    protected void setSiblingIndex(int siblingIndex) {
        this.siblingIndex = siblingIndex;
    }

    public Node traverse(NodeVisitor nodeVisitor) {
        Validate.notNull(nodeVisitor);
        NodeTraversor.traverse(nodeVisitor, this);
        return this;
    }

    public Node filter(NodeFilter nodeFilter) {
        Validate.notNull(nodeFilter);
        NodeTraversor.filter(nodeFilter, this);
        return this;
    }

    public String outerHtml() {
        StringBuilder accum = StringUtil.borrowBuilder();
        outerHtml(accum);
        return StringUtil.releaseBuilder(accum);
    }

    protected void outerHtml(Appendable accum) {
        NodeTraversor.traverse(new OuterHtmlVisitor(accum, NodeUtils.outputSettings(this)), this);
    }

    public <T extends Appendable> T html(T appendable) {
        outerHtml(appendable);
        return appendable;
    }

    public String toString() {
        return outerHtml();
    }

    protected void indent(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        accum.append('\n').append(StringUtil.padding(depth * out.indentAmount()));
    }

    public boolean equals(Object o) {
        return this == o;
    }

    public boolean hasSameValue(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return outerHtml().equals(((Node) o).outerHtml());
    }

    @Override // 
    /* renamed from: clone */
    public Node mo5203clone() {
        Node thisClone = doClone(null);
        LinkedList<Node> nodesToProcess = new LinkedList<>();
        nodesToProcess.add(thisClone);
        while (!nodesToProcess.isEmpty()) {
            Node currParent = nodesToProcess.remove();
            int size = currParent.childNodeSize();
            for (int i = 0; i < size; i++) {
                List<Node> childNodes = currParent.ensureChildNodes();
                Node childClone = childNodes.get(i).doClone(currParent);
                childNodes.set(i, childClone);
                nodesToProcess.add(childClone);
            }
        }
        return thisClone;
    }

    public Node shallowClone() {
        return doClone(null);
    }

    protected Node doClone(@Nullable Node parent) {
        try {
            Node clone = (Node) super.clone();
            clone.parentNode = parent;
            clone.siblingIndex = parent == null ? 0 : this.siblingIndex;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Node$OuterHtmlVisitor.class */
    private static class OuterHtmlVisitor implements NodeVisitor {
        private final Appendable accum;
        private final Document.OutputSettings out;

        OuterHtmlVisitor(Appendable accum, Document.OutputSettings out) {
            this.accum = accum;
            this.out = out;
            out.prepareEncoder();
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(Node node, int depth) {
            try {
                node.outerHtmlHead(this.accum, depth, this.out);
            } catch (IOException exception) {
                throw new SerializationException(exception);
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(Node node, int depth) {
            if (!node.nodeName().equals("#text")) {
                try {
                    node.outerHtmlTail(this.accum, depth, this.out);
                } catch (IOException exception) {
                    throw new SerializationException(exception);
                }
            }
        }
    }
}
