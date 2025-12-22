package org.antlr.v4.runtime.tree.xpath;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/xpath/XPath.class */
public class XPath {
    public static final String WILDCARD = "*";
    public static final String NOT = "!";
    protected String path;
    protected XPathElement[] elements;
    protected Parser parser;

    public XPath(Parser parser, String path) {
        this.parser = parser;
        this.path = path;
        this.elements = split(path);
    }

    public XPathElement[] split(String path) {
        try {
            ANTLRInputStream in = new ANTLRInputStream(new StringReader(path));
            XPathLexer lexer = new XPathLexer(in) { // from class: org.antlr.v4.runtime.tree.xpath.XPath.1
                @Override // org.antlr.v4.runtime.Lexer
                public void recover(LexerNoViableAltException e) {
                    throw e;
                }
            };
            lexer.removeErrorListeners();
            lexer.addErrorListener(new XPathLexerErrorListener());
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            try {
                tokenStream.fill();
                List<Token> tokens = tokenStream.getTokens();
                List<XPathElement> elements = new ArrayList<>();
                int n = tokens.size();
                int i = 0;
                while (i < n) {
                    Token el = tokens.get(i);
                    switch (el.getType()) {
                        case -1:
                            break;
                        case 0:
                        default:
                            throw new IllegalArgumentException("Unknowth path element " + el);
                        case 1:
                        case 2:
                        case 5:
                            elements.add(getXPathElement(el, false));
                            i++;
                            continue;
                        case 3:
                        case 4:
                            boolean anywhere = el.getType() == 3;
                            int i2 = i + 1;
                            Token next = tokens.get(i2);
                            boolean invert = next.getType() == 6;
                            if (invert) {
                                i2++;
                                next = tokens.get(i2);
                            }
                            XPathElement pathElement = getXPathElement(next, anywhere);
                            pathElement.invert = invert;
                            elements.add(pathElement);
                            i = i2 + 1;
                            continue;
                    }
                    return (XPathElement[]) elements.toArray(new XPathElement[0]);
                }
                return (XPathElement[]) elements.toArray(new XPathElement[0]);
            } catch (LexerNoViableAltException e) {
                int pos = lexer.getCharPositionInLine();
                String msg = "Invalid tokens or characters at index " + pos + " in path '" + path + OperatorName.SHOW_TEXT_LINE;
                throw new IllegalArgumentException(msg, e);
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not read path: " + path, ioe);
        }
    }

    protected XPathElement getXPathElement(Token wordToken, boolean anywhere) {
        if (wordToken.getType() == -1) {
            throw new IllegalArgumentException("Missing path element at end of path");
        }
        String word = wordToken.getText();
        int ttype = this.parser.getTokenType(word);
        int ruleIndex = this.parser.getRuleIndex(word);
        switch (wordToken.getType()) {
            case 1:
            case 8:
                if (ttype == 0) {
                    throw new IllegalArgumentException(word + " at index " + wordToken.getStartIndex() + " isn't a valid token name");
                }
                return anywhere ? new XPathTokenAnywhereElement(word, ttype) : new XPathTokenElement(word, ttype);
            case 5:
                return anywhere ? new XPathWildcardAnywhereElement() : new XPathWildcardElement();
            default:
                if (ruleIndex == -1) {
                    throw new IllegalArgumentException(word + " at index " + wordToken.getStartIndex() + " isn't a valid rule name");
                }
                return anywhere ? new XPathRuleAnywhereElement(word, ruleIndex) : new XPathRuleElement(word, ruleIndex);
        }
    }

    public static Collection<ParseTree> findAll(ParseTree tree, String xpath, Parser parser) {
        XPath p = new XPath(parser, xpath);
        return p.evaluate(tree);
    }

    public Collection<ParseTree> evaluate(ParseTree t) {
        ParserRuleContext dummyRoot = new ParserRuleContext();
        dummyRoot.children = Collections.singletonList(t);
        Collection<ParseTree> work = Collections.singleton(dummyRoot);
        int i = 0;
        while (i < this.elements.length) {
            Collection<ParseTree> next = new LinkedHashSet<>();
            for (ParseTree node : work) {
                if (node.getChildCount() > 0) {
                    Collection<? extends ParseTree> matching = this.elements[i].evaluate(node);
                    next.addAll(matching);
                }
            }
            i++;
            work = next;
        }
        return work;
    }
}
