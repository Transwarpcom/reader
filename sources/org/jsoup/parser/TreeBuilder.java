package org.jsoup.parser;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Token;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/TreeBuilder.class */
public abstract class TreeBuilder {
    protected Parser parser;
    CharacterReader reader;
    Tokeniser tokeniser;
    protected Document doc;
    protected ArrayList<Element> stack;
    protected String baseUri;
    protected Token currentToken;
    protected ParseSettings settings;
    private Token.StartTag start = new Token.StartTag();
    private Token.EndTag end = new Token.EndTag();

    abstract ParseSettings defaultSettings();

    abstract TreeBuilder newInstance();

    abstract List<Node> parseFragment(String str, Element element, String str2, Parser parser);

    protected abstract boolean process(Token token);

    TreeBuilder() {
    }

    @ParametersAreNonnullByDefault
    protected void initialiseParse(Reader input, String baseUri, Parser parser) {
        Validate.notNull(input, "String input must not be null");
        Validate.notNull(baseUri, "BaseURI must not be null");
        Validate.notNull(parser);
        this.doc = new Document(baseUri);
        this.doc.parser(parser);
        this.parser = parser;
        this.settings = parser.settings();
        this.reader = new CharacterReader(input);
        this.currentToken = null;
        this.tokeniser = new Tokeniser(this.reader, parser.getErrors());
        this.stack = new ArrayList<>(32);
        this.baseUri = baseUri;
    }

    @ParametersAreNonnullByDefault
    Document parse(Reader input, String baseUri, Parser parser) {
        initialiseParse(input, baseUri, parser);
        runParser();
        this.reader.close();
        this.reader = null;
        this.tokeniser = null;
        this.stack = null;
        return this.doc;
    }

    protected void runParser() {
        Token token;
        Tokeniser tokeniser = this.tokeniser;
        Token.TokenType eof = Token.TokenType.EOF;
        do {
            token = tokeniser.read();
            process(token);
            token.reset();
        } while (token.type != eof);
    }

    protected boolean processStartTag(String name) {
        Token.StartTag start = this.start;
        if (this.currentToken == start) {
            return process(new Token.StartTag().name(name));
        }
        return process(start.reset().name(name));
    }

    public boolean processStartTag(String name, Attributes attrs) {
        Token.StartTag start = this.start;
        if (this.currentToken == start) {
            return process(new Token.StartTag().nameAttr(name, attrs));
        }
        start.reset();
        start.nameAttr(name, attrs);
        return process(start);
    }

    protected boolean processEndTag(String name) {
        if (this.currentToken == this.end) {
            return process(new Token.EndTag().name(name));
        }
        return process(this.end.reset().name(name));
    }

    protected Element currentElement() {
        int size = this.stack.size();
        if (size > 0) {
            return this.stack.get(size - 1);
        }
        return null;
    }

    protected void error(String msg) {
        ParseErrorList errors = this.parser.getErrors();
        if (errors.canAddError()) {
            errors.add(new ParseError(this.reader.pos(), msg));
        }
    }

    protected boolean isContentForTagData(String normalName) {
        return false;
    }
}
