package org.jsoup.nodes;

import ch.qos.logback.classic.spi.CallerData;
import java.io.IOException;
import javax.annotation.Nullable;
import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Comment.class */
public class Comment extends LeafNode {
    @Override // org.jsoup.nodes.LeafNode, org.jsoup.nodes.Node
    public /* bridge */ /* synthetic */ Node empty() {
        return super.empty();
    }

    @Override // org.jsoup.nodes.LeafNode, org.jsoup.nodes.Node
    public /* bridge */ /* synthetic */ int childNodeSize() {
        return super.childNodeSize();
    }

    @Override // org.jsoup.nodes.LeafNode, org.jsoup.nodes.Node
    public /* bridge */ /* synthetic */ String baseUri() {
        return super.baseUri();
    }

    @Override // org.jsoup.nodes.LeafNode, org.jsoup.nodes.Node
    public /* bridge */ /* synthetic */ String absUrl(String str) {
        return super.absUrl(str);
    }

    @Override // org.jsoup.nodes.LeafNode, org.jsoup.nodes.Node
    public /* bridge */ /* synthetic */ Node removeAttr(String str) {
        return super.removeAttr(str);
    }

    @Override // org.jsoup.nodes.LeafNode, org.jsoup.nodes.Node
    public /* bridge */ /* synthetic */ boolean hasAttr(String str) {
        return super.hasAttr(str);
    }

    @Override // org.jsoup.nodes.LeafNode, org.jsoup.nodes.Node
    public /* bridge */ /* synthetic */ Node attr(String str, String str2) {
        return super.attr(str, str2);
    }

    @Override // org.jsoup.nodes.LeafNode, org.jsoup.nodes.Node
    public /* bridge */ /* synthetic */ String attr(String str) {
        return super.attr(str);
    }

    public Comment(String data) {
        this.value = data;
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#comment";
    }

    public String getData() {
        return coreValue();
    }

    public Comment setData(String data) {
        coreValue(data);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        if (out.prettyPrint() && ((siblingIndex() == 0 && (this.parentNode instanceof Element) && ((Element) this.parentNode).tag().formatAsBlock()) || out.outline())) {
            indent(accum, depth, out);
        }
        accum.append("<!--").append(getData()).append("-->");
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) {
    }

    @Override // org.jsoup.nodes.Node
    public String toString() {
        return outerHtml();
    }

    @Override // org.jsoup.nodes.Node
    /* renamed from: clone */
    public Comment mo5203clone() {
        return (Comment) super.mo5203clone();
    }

    public boolean isXmlDeclaration() {
        String data = getData();
        return isXmlDeclarationData(data);
    }

    private static boolean isXmlDeclarationData(String data) {
        return data.length() > 1 && (data.startsWith("!") || data.startsWith(CallerData.NA));
    }

    @Nullable
    public XmlDeclaration asXmlDeclaration() {
        String data = getData();
        XmlDeclaration decl = null;
        String declContent = data.substring(1, data.length() - 1);
        if (isXmlDeclarationData(declContent)) {
            return null;
        }
        String fragment = "<" + declContent + ">";
        Document doc = Parser.htmlParser().settings(ParseSettings.preserveCase).parseInput(fragment, baseUri());
        if (doc.body().children().size() > 0) {
            Element el = doc.body().child(0);
            decl = new XmlDeclaration(NodeUtils.parser(doc).settings().normalizeTag(el.tagName()), data.startsWith("!"));
            decl.attributes().addAll(el.attributes());
        }
        return decl;
    }
}
