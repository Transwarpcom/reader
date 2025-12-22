package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.UncheckedIOException;
import org.jsoup.nodes.Document;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/CDataNode.class */
public class CDataNode extends TextNode {
    public CDataNode(String text) {
        super(text);
    }

    @Override // org.jsoup.nodes.TextNode, org.jsoup.nodes.Node
    public String nodeName() {
        return "#cdata";
    }

    @Override // org.jsoup.nodes.TextNode
    public String text() {
        return getWholeText();
    }

    @Override // org.jsoup.nodes.TextNode, org.jsoup.nodes.Node
    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        accum.append("<![CDATA[").append(getWholeText());
    }

    @Override // org.jsoup.nodes.TextNode, org.jsoup.nodes.Node
    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        try {
            accum.append("]]>");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override // org.jsoup.nodes.TextNode, org.jsoup.nodes.Node
    /* renamed from: clone */
    public CDataNode mo5203clone() {
        return (CDataNode) super.mo5203clone();
    }
}
