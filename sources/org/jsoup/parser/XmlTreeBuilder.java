package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Token;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/XmlTreeBuilder.class */
public class XmlTreeBuilder extends TreeBuilder {
    @Override // org.jsoup.parser.TreeBuilder
    public /* bridge */ /* synthetic */ boolean processStartTag(String str, Attributes attributes) {
        return super.processStartTag(str, attributes);
    }

    @Override // org.jsoup.parser.TreeBuilder
    ParseSettings defaultSettings() {
        return ParseSettings.preserveCase;
    }

    @Override // org.jsoup.parser.TreeBuilder
    @ParametersAreNonnullByDefault
    protected void initialiseParse(Reader input, String baseUri, Parser parser) {
        super.initialiseParse(input, baseUri, parser);
        this.stack.add(this.doc);
        this.doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml).escapeMode(Entities.EscapeMode.xhtml).prettyPrint(false);
    }

    Document parse(Reader input, String baseUri) {
        return parse(input, baseUri, new Parser(this));
    }

    Document parse(String input, String baseUri) {
        return parse(new StringReader(input), baseUri, new Parser(this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jsoup.parser.TreeBuilder
    public XmlTreeBuilder newInstance() {
        return new XmlTreeBuilder();
    }

    @Override // org.jsoup.parser.TreeBuilder
    protected boolean process(Token token) {
        switch (token.type) {
            case StartTag:
                insert(token.asStartTag());
                break;
            case EndTag:
                popStackToClose(token.asEndTag());
                break;
            case Comment:
                insert(token.asComment());
                break;
            case Character:
                insert(token.asCharacter());
                break;
            case Doctype:
                insert(token.asDoctype());
                break;
            case EOF:
                break;
            default:
                Validate.fail("Unexpected token type: " + token.type);
                break;
        }
        return true;
    }

    private void insertNode(Node node) {
        currentElement().appendChild(node);
    }

    Element insert(Token.StartTag startTag) {
        Tag tag = Tag.valueOf(startTag.name(), this.settings);
        if (startTag.hasAttributes()) {
            startTag.attributes.deduplicate(this.settings);
        }
        Element el = new Element(tag, null, this.settings.normalizeAttributes(startTag.attributes));
        insertNode(el);
        if (startTag.isSelfClosing()) {
            if (!tag.isKnownTag()) {
                tag.setSelfClosing();
            }
        } else {
            this.stack.add(el);
        }
        return el;
    }

    void insert(Token.Comment commentToken) {
        XmlDeclaration decl;
        Comment comment = new Comment(commentToken.getData());
        Node insert = comment;
        if (commentToken.bogus && comment.isXmlDeclaration() && (decl = comment.asXmlDeclaration()) != null) {
            insert = decl;
        }
        insertNode(insert);
    }

    void insert(Token.Character token) {
        String data = token.getData();
        insertNode(token.isCData() ? new CDataNode(data) : new TextNode(data));
    }

    void insert(Token.Doctype d) {
        DocumentType doctypeNode = new DocumentType(this.settings.normalizeTag(d.getName()), d.getPublicIdentifier(), d.getSystemIdentifier());
        doctypeNode.setPubSysKey(d.getPubSysKey());
        insertNode(doctypeNode);
    }

    private void popStackToClose(Token.EndTag endTag) {
        String elName = this.settings.normalizeTag(endTag.tagName);
        Element firstFound = null;
        int pos = this.stack.size() - 1;
        while (true) {
            if (pos < 0) {
                break;
            }
            Element next = this.stack.get(pos);
            if (next.nodeName().equals(elName)) {
                firstFound = next;
                break;
            }
            pos--;
        }
        if (firstFound == null) {
            return;
        }
        for (int pos2 = this.stack.size() - 1; pos2 >= 0; pos2--) {
            Element next2 = this.stack.get(pos2);
            this.stack.remove(pos2);
            if (next2 == firstFound) {
                return;
            }
        }
    }

    List<Node> parseFragment(String inputFragment, String baseUri, Parser parser) {
        initialiseParse(new StringReader(inputFragment), baseUri, parser);
        runParser();
        return this.doc.childNodes();
    }

    @Override // org.jsoup.parser.TreeBuilder
    List<Node> parseFragment(String inputFragment, Element context, String baseUri, Parser parser) {
        return parseFragment(inputFragment, baseUri, parser);
    }
}
