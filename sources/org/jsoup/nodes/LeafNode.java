package org.jsoup.nodes;

import java.util.List;
import org.jsoup.helper.Validate;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/LeafNode.class */
abstract class LeafNode extends Node {
    Object value;

    LeafNode() {
    }

    @Override // org.jsoup.nodes.Node
    protected final boolean hasAttributes() {
        return this.value instanceof Attributes;
    }

    @Override // org.jsoup.nodes.Node
    public final Attributes attributes() {
        ensureAttributes();
        return (Attributes) this.value;
    }

    private void ensureAttributes() {
        if (!hasAttributes()) {
            Object coreValue = this.value;
            Attributes attributes = new Attributes();
            this.value = attributes;
            if (coreValue != null) {
                attributes.put(nodeName(), (String) coreValue);
            }
        }
    }

    String coreValue() {
        return attr(nodeName());
    }

    void coreValue(String value) {
        attr(nodeName(), value);
    }

    @Override // org.jsoup.nodes.Node
    public String attr(String key) {
        Validate.notNull(key);
        if (hasAttributes()) {
            return super.attr(key);
        }
        return key.equals(nodeName()) ? (String) this.value : "";
    }

    @Override // org.jsoup.nodes.Node
    public Node attr(String key, String value) {
        if (!hasAttributes() && key.equals(nodeName())) {
            this.value = value;
        } else {
            ensureAttributes();
            super.attr(key, value);
        }
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public boolean hasAttr(String key) {
        ensureAttributes();
        return super.hasAttr(key);
    }

    @Override // org.jsoup.nodes.Node
    public Node removeAttr(String key) {
        ensureAttributes();
        return super.removeAttr(key);
    }

    @Override // org.jsoup.nodes.Node
    public String absUrl(String key) {
        ensureAttributes();
        return super.absUrl(key);
    }

    @Override // org.jsoup.nodes.Node
    public String baseUri() {
        return hasParent() ? parent().baseUri() : "";
    }

    @Override // org.jsoup.nodes.Node
    protected void doSetBaseUri(String baseUri) {
    }

    @Override // org.jsoup.nodes.Node
    public int childNodeSize() {
        return 0;
    }

    @Override // org.jsoup.nodes.Node
    public Node empty() {
        return this;
    }

    @Override // org.jsoup.nodes.Node
    protected List<Node> ensureChildNodes() {
        return EmptyNodes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.nodes.Node
    public LeafNode doClone(Node parent) {
        LeafNode clone = (LeafNode) super.doClone(parent);
        if (hasAttributes()) {
            clone.value = ((Attributes) this.value).m5202clone();
        }
        return clone;
    }
}
