package org.jsoup.nodes;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Document.class */
public class Document extends Element {

    @Nullable
    private Connection connection;
    private OutputSettings outputSettings;
    private Parser parser;
    private QuirksMode quirksMode;
    private final String location;
    private boolean updateMetaCharset;
    private static final Evaluator titleEval = new Evaluator.Tag("title");

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Document$QuirksMode.class */
    public enum QuirksMode {
        noQuirks,
        quirks,
        limitedQuirks
    }

    public Document(String baseUri) {
        super(Tag.valueOf("#root", ParseSettings.htmlDefault), baseUri);
        this.outputSettings = new OutputSettings();
        this.quirksMode = QuirksMode.noQuirks;
        this.updateMetaCharset = false;
        this.location = baseUri;
        this.parser = Parser.htmlParser();
    }

    public static Document createShell(String baseUri) {
        Validate.notNull(baseUri);
        Document doc = new Document(baseUri);
        doc.parser = doc.parser();
        Element html = doc.appendElement("html");
        html.appendElement("head");
        html.appendElement(NCXDocumentV3.XHTMLTgs.body);
        return doc;
    }

    public String location() {
        return this.location;
    }

    public Connection connection() {
        if (this.connection == null) {
            return Jsoup.newSession();
        }
        return this.connection;
    }

    @Nullable
    public DocumentType documentType() {
        for (Node node : this.childNodes) {
            if (node instanceof DocumentType) {
                return (DocumentType) node;
            }
            if (!(node instanceof LeafNode)) {
                return null;
            }
        }
        return null;
    }

    private Element htmlEl() {
        for (Element el : childElementsList()) {
            if (el.normalName().equals("html")) {
                return el;
            }
        }
        return appendElement("html");
    }

    public Element head() {
        Element html = htmlEl();
        for (Element el : html.childElementsList()) {
            if (el.normalName().equals("head")) {
                return el;
            }
        }
        return html.prependElement("head");
    }

    public Element body() {
        Element html = htmlEl();
        for (Element el : html.childElementsList()) {
            if (NCXDocumentV3.XHTMLTgs.body.equals(el.normalName()) || "frameset".equals(el.normalName())) {
                return el;
            }
        }
        return html.appendElement(NCXDocumentV3.XHTMLTgs.body);
    }

    public String title() {
        Element titleEl = head().selectFirst(titleEval);
        return titleEl != null ? StringUtil.normaliseWhitespace(titleEl.text()).trim() : "";
    }

    public void title(String title) {
        Validate.notNull(title);
        Element titleEl = head().selectFirst(titleEval);
        if (titleEl == null) {
            titleEl = head().appendElement("title");
        }
        titleEl.text(title);
    }

    public Element createElement(String tagName) {
        return new Element(Tag.valueOf(tagName, ParseSettings.preserveCase), baseUri());
    }

    public Document normalise() {
        Element htmlEl = htmlEl();
        Element head = head();
        body();
        normaliseTextNodes(head);
        normaliseTextNodes(htmlEl);
        normaliseTextNodes(this);
        normaliseStructure("head", htmlEl);
        normaliseStructure(NCXDocumentV3.XHTMLTgs.body, htmlEl);
        ensureMetaCharsetElement();
        return this;
    }

    private void normaliseTextNodes(Element element) {
        List<Node> toMove = new ArrayList<>();
        for (Node node : element.childNodes) {
            if (node instanceof TextNode) {
                TextNode tn = (TextNode) node;
                if (!tn.isBlank()) {
                    toMove.add(tn);
                }
            }
        }
        for (int i = toMove.size() - 1; i >= 0; i--) {
            Node node2 = toMove.get(i);
            element.removeChild(node2);
            body().prependChild(new TextNode(" "));
            body().prependChild(node2);
        }
    }

    private void normaliseStructure(String tag, Element htmlEl) {
        Elements elements = getElementsByTag(tag);
        Element master = elements.first();
        if (elements.size() > 1) {
            List<Node> toMove = new ArrayList<>();
            for (int i = 1; i < elements.size(); i++) {
                Node dupe = elements.get(i);
                toMove.addAll(dupe.ensureChildNodes());
                dupe.remove();
            }
            Iterator<Node> it = toMove.iterator();
            while (it.hasNext()) {
                master.appendChild(it.next());
            }
        }
        if (master.parent() != null && !master.parent().equals(htmlEl)) {
            htmlEl.appendChild(master);
        }
    }

    @Override // org.jsoup.nodes.Node
    public String outerHtml() {
        return super.html();
    }

    @Override // org.jsoup.nodes.Element
    public Element text(String text) {
        body().text(text);
        return this;
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.Node
    public String nodeName() {
        return "#document";
    }

    public void charset(Charset charset) {
        updateMetaCharsetElement(true);
        this.outputSettings.charset(charset);
        ensureMetaCharsetElement();
    }

    public Charset charset() {
        return this.outputSettings.charset();
    }

    public void updateMetaCharsetElement(boolean update) {
        this.updateMetaCharset = update;
    }

    public boolean updateMetaCharsetElement() {
        return this.updateMetaCharset;
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.Node
    /* renamed from: clone */
    public Document mo5203clone() {
        Document clone = (Document) super.mo5203clone();
        clone.outputSettings = this.outputSettings.m5205clone();
        return clone;
    }

    private void ensureMetaCharsetElement() {
        if (this.updateMetaCharset) {
            OutputSettings.Syntax syntax = outputSettings().syntax();
            if (syntax == OutputSettings.Syntax.html) {
                Element metaCharset = selectFirst("meta[charset]");
                if (metaCharset != null) {
                    metaCharset.attr("charset", charset().displayName());
                } else {
                    head().appendElement("meta").attr("charset", charset().displayName());
                }
                select("meta[name=charset]").remove();
                return;
            }
            if (syntax == OutputSettings.Syntax.xml) {
                Node node = ensureChildNodes().get(0);
                if (node instanceof XmlDeclaration) {
                    XmlDeclaration decl = (XmlDeclaration) node;
                    if (decl.name().equals("xml")) {
                        decl.attr("encoding", charset().displayName());
                        if (decl.hasAttr("version")) {
                            decl.attr("version", "1.0");
                            return;
                        }
                        return;
                    }
                    XmlDeclaration decl2 = new XmlDeclaration("xml", false);
                    decl2.attr("version", "1.0");
                    decl2.attr("encoding", charset().displayName());
                    prependChild(decl2);
                    return;
                }
                XmlDeclaration decl3 = new XmlDeclaration("xml", false);
                decl3.attr("version", "1.0");
                decl3.attr("encoding", charset().displayName());
                prependChild(decl3);
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Document$OutputSettings.class */
    public static class OutputSettings implements Cloneable {

        @Nullable
        Entities.CoreCharset coreCharset;
        private Entities.EscapeMode escapeMode = Entities.EscapeMode.base;
        private Charset charset = DataUtil.UTF_8;
        private final ThreadLocal<CharsetEncoder> encoderThreadLocal = new ThreadLocal<>();
        private boolean prettyPrint = true;
        private boolean outline = false;
        private int indentAmount = 1;
        private Syntax syntax = Syntax.html;

        /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Document$OutputSettings$Syntax.class */
        public enum Syntax {
            html,
            xml
        }

        public Entities.EscapeMode escapeMode() {
            return this.escapeMode;
        }

        public OutputSettings escapeMode(Entities.EscapeMode escapeMode) {
            this.escapeMode = escapeMode;
            return this;
        }

        public Charset charset() {
            return this.charset;
        }

        public OutputSettings charset(Charset charset) {
            this.charset = charset;
            return this;
        }

        public OutputSettings charset(String charset) {
            charset(Charset.forName(charset));
            return this;
        }

        CharsetEncoder prepareEncoder() {
            CharsetEncoder encoder = this.charset.newEncoder();
            this.encoderThreadLocal.set(encoder);
            this.coreCharset = Entities.CoreCharset.byName(encoder.charset().name());
            return encoder;
        }

        CharsetEncoder encoder() {
            CharsetEncoder encoder = this.encoderThreadLocal.get();
            return encoder != null ? encoder : prepareEncoder();
        }

        public Syntax syntax() {
            return this.syntax;
        }

        public OutputSettings syntax(Syntax syntax) {
            this.syntax = syntax;
            return this;
        }

        public boolean prettyPrint() {
            return this.prettyPrint;
        }

        public OutputSettings prettyPrint(boolean pretty) {
            this.prettyPrint = pretty;
            return this;
        }

        public boolean outline() {
            return this.outline;
        }

        public OutputSettings outline(boolean outlineMode) {
            this.outline = outlineMode;
            return this;
        }

        public int indentAmount() {
            return this.indentAmount;
        }

        public OutputSettings indentAmount(int indentAmount) {
            Validate.isTrue(indentAmount >= 0);
            this.indentAmount = indentAmount;
            return this;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public OutputSettings m5205clone() {
            try {
                OutputSettings clone = (OutputSettings) super.clone();
                clone.charset(this.charset.name());
                clone.escapeMode = Entities.EscapeMode.valueOf(this.escapeMode.name());
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public OutputSettings outputSettings() {
        return this.outputSettings;
    }

    public Document outputSettings(OutputSettings outputSettings) {
        Validate.notNull(outputSettings);
        this.outputSettings = outputSettings;
        return this;
    }

    public QuirksMode quirksMode() {
        return this.quirksMode;
    }

    public Document quirksMode(QuirksMode quirksMode) {
        this.quirksMode = quirksMode;
        return this;
    }

    public Parser parser() {
        return this.parser;
    }

    public Document parser(Parser parser) {
        this.parser = parser;
        return this;
    }

    public Document connection(Connection connection) {
        Validate.notNull(connection);
        this.connection = connection;
        return this;
    }
}
