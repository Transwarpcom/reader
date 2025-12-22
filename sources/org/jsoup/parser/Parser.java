package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Parser.class */
public class Parser {
    private TreeBuilder treeBuilder;
    private ParseErrorList errors;
    private ParseSettings settings;

    public Parser(TreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
        this.settings = treeBuilder.defaultSettings();
        this.errors = ParseErrorList.noTracking();
    }

    public Parser newInstance() {
        return new Parser(this);
    }

    private Parser(Parser copy) {
        this.treeBuilder = copy.treeBuilder.newInstance();
        this.errors = new ParseErrorList(copy.errors);
        this.settings = new ParseSettings(copy.settings);
    }

    public Document parseInput(String html, String baseUri) {
        return this.treeBuilder.parse(new StringReader(html), baseUri, this);
    }

    public Document parseInput(Reader inputHtml, String baseUri) {
        return this.treeBuilder.parse(inputHtml, baseUri, this);
    }

    public List<Node> parseFragmentInput(String fragment, Element context, String baseUri) {
        return this.treeBuilder.parseFragment(fragment, context, baseUri, this);
    }

    public TreeBuilder getTreeBuilder() {
        return this.treeBuilder;
    }

    public Parser setTreeBuilder(TreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
        treeBuilder.parser = this;
        return this;
    }

    public boolean isTrackErrors() {
        return this.errors.getMaxSize() > 0;
    }

    public Parser setTrackErrors(int maxErrors) {
        this.errors = maxErrors > 0 ? ParseErrorList.tracking(maxErrors) : ParseErrorList.noTracking();
        return this;
    }

    public ParseErrorList getErrors() {
        return this.errors;
    }

    public Parser settings(ParseSettings settings) {
        this.settings = settings;
        return this;
    }

    public ParseSettings settings() {
        return this.settings;
    }

    public boolean isContentForTagData(String normalName) {
        return getTreeBuilder().isContentForTagData(normalName);
    }

    public static Document parse(String html, String baseUri) {
        TreeBuilder treeBuilder = new HtmlTreeBuilder();
        return treeBuilder.parse(new StringReader(html), baseUri, new Parser(treeBuilder));
    }

    public static List<Node> parseFragment(String fragmentHtml, Element context, String baseUri) {
        HtmlTreeBuilder treeBuilder = new HtmlTreeBuilder();
        return treeBuilder.parseFragment(fragmentHtml, context, baseUri, new Parser(treeBuilder));
    }

    public static List<Node> parseFragment(String fragmentHtml, Element context, String baseUri, ParseErrorList errorList) {
        HtmlTreeBuilder treeBuilder = new HtmlTreeBuilder();
        Parser parser = new Parser(treeBuilder);
        parser.errors = errorList;
        return treeBuilder.parseFragment(fragmentHtml, context, baseUri, parser);
    }

    public static List<Node> parseXmlFragment(String fragmentXml, String baseUri) {
        XmlTreeBuilder treeBuilder = new XmlTreeBuilder();
        return treeBuilder.parseFragment(fragmentXml, baseUri, new Parser(treeBuilder));
    }

    public static Document parseBodyFragment(String bodyHtml, String baseUri) {
        Document doc = Document.createShell(baseUri);
        Element body = doc.body();
        List<Node> nodeList = parseFragment(bodyHtml, body, baseUri);
        Node[] nodes = (Node[]) nodeList.toArray(new Node[0]);
        for (int i = nodes.length - 1; i > 0; i--) {
            nodes[i].remove();
        }
        for (Node node : nodes) {
            body.appendChild(node);
        }
        return doc;
    }

    public static String unescapeEntities(String string, boolean inAttribute) {
        Tokeniser tokeniser = new Tokeniser(new CharacterReader(string), ParseErrorList.noTracking());
        return tokeniser.unescapeEntities(inAttribute);
    }

    public static Parser htmlParser() {
        return new Parser(new HtmlTreeBuilder());
    }

    public static Parser xmlParser() {
        return new Parser(new XmlTreeBuilder());
    }
}
