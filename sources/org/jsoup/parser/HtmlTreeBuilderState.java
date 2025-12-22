package org.jsoup.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import me.ag2s.epublib.epub.NCXDocumentV3;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Token;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/HtmlTreeBuilderState.class */
enum HtmlTreeBuilderState {
    Initial { // from class: org.jsoup.parser.HtmlTreeBuilderState.1
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                return true;
            }
            if (t.isComment()) {
                tb.insert(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                Token.Doctype d = t.asDoctype();
                DocumentType doctype = new DocumentType(tb.settings.normalizeTag(d.getName()), d.getPublicIdentifier(), d.getSystemIdentifier());
                doctype.setPubSysKey(d.getPubSysKey());
                tb.getDocument().appendChild(doctype);
                if (d.isForceQuirks()) {
                    tb.getDocument().quirksMode(Document.QuirksMode.quirks);
                }
                tb.transition(BeforeHtml);
                return true;
            }
            tb.transition(BeforeHtml);
            return tb.process(t);
        }
    },
    BeforeHtml { // from class: org.jsoup.parser.HtmlTreeBuilderState.2
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (!t.isComment()) {
                if (HtmlTreeBuilderState.isWhitespace(t)) {
                    tb.insert(t.asCharacter());
                    return true;
                }
                if (t.isStartTag() && t.asStartTag().normalName().equals("html")) {
                    tb.insert(t.asStartTag());
                    tb.transition(BeforeHead);
                    return true;
                }
                if (t.isEndTag() && StringUtil.inSorted(t.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                    return anythingElse(t, tb);
                }
                if (t.isEndTag()) {
                    tb.error(this);
                    return false;
                }
                return anythingElse(t, tb);
            }
            tb.insert(t.asComment());
            return true;
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.insertStartTag("html");
            tb.transition(BeforeHead);
            return tb.process(t);
        }
    },
    BeforeHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.3
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
                return true;
            }
            if (t.isComment()) {
                tb.insert(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (t.isStartTag() && t.asStartTag().normalName().equals("html")) {
                return InBody.process(t, tb);
            }
            if (t.isStartTag() && t.asStartTag().normalName().equals("head")) {
                Element head = tb.insert(t.asStartTag());
                tb.setHeadElement(head);
                tb.transition(InHead);
                return true;
            }
            if (t.isEndTag() && StringUtil.inSorted(t.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                tb.processStartTag("head");
                return tb.process(t);
            }
            if (t.isEndTag()) {
                tb.error(this);
                return false;
            }
            tb.processStartTag("head");
            return tb.process(t);
        }
    },
    InHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.4
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
                return true;
            }
            switch (t.type) {
                case Comment:
                    tb.insert(t.asComment());
                    break;
                case Doctype:
                    tb.error(this);
                    break;
                case StartTag:
                    Token.StartTag start = t.asStartTag();
                    String name = start.normalName();
                    if (!name.equals("html")) {
                        if (!StringUtil.inSorted(name, Constants.InHeadEmpty)) {
                            if (name.equals("meta")) {
                                tb.insertEmpty(start);
                                break;
                            } else if (name.equals("title")) {
                                HtmlTreeBuilderState.handleRcData(start, tb);
                                break;
                            } else if (StringUtil.inSorted(name, Constants.InHeadRaw)) {
                                HtmlTreeBuilderState.handleRawtext(start, tb);
                                break;
                            } else if (name.equals("noscript")) {
                                tb.insert(start);
                                tb.transition(InHeadNoscript);
                                break;
                            } else if (name.equals("script")) {
                                tb.tokeniser.transition(TokeniserState.ScriptData);
                                tb.markInsertionMode();
                                tb.transition(Text);
                                tb.insert(start);
                                break;
                            } else if (!name.equals("head")) {
                                break;
                            } else {
                                tb.error(this);
                                break;
                            }
                        } else {
                            Element el = tb.insertEmpty(start);
                            if (name.equals("base") && el.hasAttr("href")) {
                                tb.maybeSetBaseUri(el);
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                    break;
                case EndTag:
                    Token.EndTag end = t.asEndTag();
                    String name2 = end.normalName();
                    if (name2.equals("head")) {
                        tb.pop();
                        tb.transition(AfterHead);
                        break;
                    } else if (!StringUtil.inSorted(name2, Constants.InHeadEnd)) {
                        tb.error(this);
                        break;
                    } else {
                        break;
                    }
            }
            return true;
        }

        private boolean anythingElse(Token t, TreeBuilder tb) {
            tb.processEndTag("head");
            return tb.process(t);
        }
    },
    InHeadNoscript { // from class: org.jsoup.parser.HtmlTreeBuilderState.5
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isDoctype()) {
                tb.error(this);
                return true;
            }
            if (t.isStartTag() && t.asStartTag().normalName().equals("html")) {
                return tb.process(t, InBody);
            }
            if (!t.isEndTag() || !t.asEndTag().normalName().equals("noscript")) {
                if (HtmlTreeBuilderState.isWhitespace(t) || t.isComment() || (t.isStartTag() && StringUtil.inSorted(t.asStartTag().normalName(), Constants.InHeadNoScriptHead))) {
                    return tb.process(t, InHead);
                }
                if (t.isEndTag() && t.asEndTag().normalName().equals("br")) {
                    return anythingElse(t, tb);
                }
                if ((t.isStartTag() && StringUtil.inSorted(t.asStartTag().normalName(), Constants.InHeadNoscriptIgnore)) || t.isEndTag()) {
                    tb.error(this);
                    return false;
                }
                return anythingElse(t, tb);
            }
            tb.pop();
            tb.transition(InHead);
            return true;
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.error(this);
            tb.insert(new Token.Character().data(t.toString()));
            return true;
        }
    },
    AfterHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.6
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
                return true;
            }
            if (t.isComment()) {
                tb.insert(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return true;
            }
            if (!t.isStartTag()) {
                if (t.isEndTag()) {
                    if (StringUtil.inSorted(t.asEndTag().normalName(), Constants.AfterHeadBody)) {
                        anythingElse(t, tb);
                        return true;
                    }
                    tb.error(this);
                    return false;
                }
                anythingElse(t, tb);
                return true;
            }
            Token.StartTag startTag = t.asStartTag();
            String name = startTag.normalName();
            if (name.equals("html")) {
                return tb.process(t, InBody);
            }
            if (name.equals(NCXDocumentV3.XHTMLTgs.body)) {
                tb.insert(startTag);
                tb.framesetOk(false);
                tb.transition(InBody);
                return true;
            }
            if (name.equals("frameset")) {
                tb.insert(startTag);
                tb.transition(InFrameset);
                return true;
            }
            if (!StringUtil.inSorted(name, Constants.InBodyStartToHead)) {
                if (name.equals("head")) {
                    tb.error(this);
                    return false;
                }
                anythingElse(t, tb);
                return true;
            }
            tb.error(this);
            Element head = tb.getHeadElement();
            tb.push(head);
            tb.process(t, InHead);
            tb.removeFromStack(head);
            return true;
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.processStartTag(NCXDocumentV3.XHTMLTgs.body);
            tb.framesetOk(true);
            return tb.process(t);
        }
    },
    InBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.7
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            switch (t.type) {
                case Comment:
                    tb.insert(t.asComment());
                    break;
                case Doctype:
                    tb.error(this);
                    break;
                case Character:
                    Token.Character c = t.asCharacter();
                    if (c.getData().equals(HtmlTreeBuilderState.nullString)) {
                        tb.error(this);
                        break;
                    } else if (tb.framesetOk() && HtmlTreeBuilderState.isWhitespace(c)) {
                        tb.reconstructFormattingElements();
                        tb.insert(c);
                        break;
                    } else {
                        tb.reconstructFormattingElements();
                        tb.insert(c);
                        tb.framesetOk(false);
                        break;
                    }
                    break;
            }
            return false;
        }

        private boolean inBodyStartTag(Token t, HtmlTreeBuilder tb) throws IOException {
            Token.StartTag startTag;
            String name;
            String str;
            startTag = t.asStartTag();
            name = startTag.normalName();
            switch (name) {
                case "a":
                    if (tb.getActiveFormattingElement("a") != null) {
                        tb.error(this);
                        tb.processEndTag("a");
                        Element remainingA = tb.getFromStack("a");
                        if (remainingA != null) {
                            tb.removeFromActiveFormattingElements(remainingA);
                            tb.removeFromStack(remainingA);
                        }
                    }
                    tb.reconstructFormattingElements();
                    tb.pushActiveFormattingElements(tb.insert(startTag));
                    return true;
                case "span":
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    return true;
                case "li":
                    tb.framesetOk(false);
                    ArrayList<Element> stack = tb.getStack();
                    int i = stack.size() - 1;
                    while (true) {
                        if (i > 0) {
                            Element el = stack.get(i);
                            if (el.normalName().equals(NCXDocumentV3.XHTMLTgs.li)) {
                                tb.processEndTag(NCXDocumentV3.XHTMLTgs.li);
                            } else if (!tb.isSpecial(el) || StringUtil.inSorted(el.normalName(), Constants.InBodyStartLiBreakers)) {
                                i--;
                            }
                        }
                    }
                    if (tb.inButtonScope("p")) {
                        tb.processEndTag("p");
                    }
                    tb.insert(startTag);
                    return true;
                case "html":
                    tb.error(this);
                    Element html = tb.getStack().get(0);
                    if (startTag.hasAttributes()) {
                        Iterator<Attribute> it = startTag.attributes.iterator();
                        while (it.hasNext()) {
                            Attribute attribute = it.next();
                            if (!html.hasAttr(attribute.getKey())) {
                                html.attributes().put(attribute);
                            }
                        }
                        return true;
                    }
                    return true;
                case "body":
                    tb.error(this);
                    ArrayList<Element> stack2 = tb.getStack();
                    if (stack2.size() == 1) {
                        return false;
                    }
                    if (stack2.size() > 2 && !stack2.get(1).normalName().equals(NCXDocumentV3.XHTMLTgs.body)) {
                        return false;
                    }
                    tb.framesetOk(false);
                    Element body = stack2.get(1);
                    if (startTag.hasAttributes()) {
                        Iterator<Attribute> it2 = startTag.attributes.iterator();
                        while (it2.hasNext()) {
                            Attribute attribute2 = it2.next();
                            if (!body.hasAttr(attribute2.getKey())) {
                                body.attributes().put(attribute2);
                            }
                        }
                        return true;
                    }
                    return true;
                case "frameset":
                    tb.error(this);
                    ArrayList<Element> stack3 = tb.getStack();
                    if (stack3.size() == 1) {
                        return false;
                    }
                    if ((stack3.size() > 2 && !stack3.get(1).normalName().equals(NCXDocumentV3.XHTMLTgs.body)) || !tb.framesetOk()) {
                        return false;
                    }
                    Element second = stack3.get(1);
                    if (second.parent() != null) {
                        second.remove();
                    }
                    while (stack3.size() > 1) {
                        stack3.remove(stack3.size() - 1);
                    }
                    tb.insert(startTag);
                    tb.transition(InFrameset);
                    return true;
                case "form":
                    if (tb.getFormElement() != null) {
                        tb.error(this);
                        return false;
                    }
                    if (tb.inButtonScope("p")) {
                        tb.processEndTag("p");
                    }
                    tb.insertForm(startTag, true);
                    return true;
                case "plaintext":
                    if (tb.inButtonScope("p")) {
                        tb.processEndTag("p");
                    }
                    tb.insert(startTag);
                    tb.tokeniser.transition(TokeniserState.PLAINTEXT);
                    return true;
                case "button":
                    if (tb.inButtonScope("button")) {
                        tb.error(this);
                        tb.processEndTag("button");
                        tb.process(startTag);
                        return true;
                    }
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    tb.framesetOk(false);
                    return true;
                case "nobr":
                    tb.reconstructFormattingElements();
                    if (tb.inScope("nobr")) {
                        tb.error(this);
                        tb.processEndTag("nobr");
                        tb.reconstructFormattingElements();
                    }
                    tb.pushActiveFormattingElements(tb.insert(startTag));
                    return true;
                case "table":
                    if (tb.getDocument().quirksMode() != Document.QuirksMode.quirks && tb.inButtonScope("p")) {
                        tb.processEndTag("p");
                    }
                    tb.insert(startTag);
                    tb.framesetOk(false);
                    tb.transition(InTable);
                    return true;
                case "input":
                    tb.reconstructFormattingElements();
                    if (!tb.insertEmpty(startTag).attr("type").equalsIgnoreCase("hidden")) {
                        tb.framesetOk(false);
                        return true;
                    }
                    return true;
                case "hr":
                    if (tb.inButtonScope("p")) {
                        tb.processEndTag("p");
                    }
                    tb.insertEmpty(startTag);
                    tb.framesetOk(false);
                    return true;
                case "image":
                    if (tb.getFromStack("svg") == null) {
                        return tb.process(startTag.name("img"));
                    }
                    tb.insert(startTag);
                    return true;
                case "isindex":
                    tb.error(this);
                    if (tb.getFormElement() != null) {
                        return false;
                    }
                    tb.processStartTag("form");
                    if (startTag.hasAttribute("action")) {
                        Element form = tb.getFormElement();
                        form.attr("action", startTag.attributes.get("action"));
                    }
                    tb.processStartTag("hr");
                    tb.processStartTag("label");
                    if (startTag.hasAttribute("prompt")) {
                        str = startTag.attributes.get("prompt");
                    } else {
                        str = "This is a searchable index. Enter search keywords: ";
                    }
                    String prompt = str;
                    tb.process(new Token.Character().data(prompt));
                    Attributes inputAttribs = new Attributes();
                    if (startTag.hasAttributes()) {
                        Iterator<Attribute> it3 = startTag.attributes.iterator();
                        while (it3.hasNext()) {
                            Attribute attr = it3.next();
                            if (!StringUtil.inSorted(attr.getKey(), Constants.InBodyStartInputAttribs)) {
                                inputAttribs.put(attr);
                            }
                        }
                    }
                    inputAttribs.put("name", "isindex");
                    tb.processStartTag("input", inputAttribs);
                    tb.processEndTag("label");
                    tb.processStartTag("hr");
                    tb.processEndTag("form");
                    return true;
                case "textarea":
                    tb.insert(startTag);
                    if (!startTag.isSelfClosing()) {
                        tb.tokeniser.transition(TokeniserState.Rcdata);
                        tb.markInsertionMode();
                        tb.framesetOk(false);
                        tb.transition(Text);
                        return true;
                    }
                    return true;
                case "xmp":
                    if (tb.inButtonScope("p")) {
                        tb.processEndTag("p");
                    }
                    tb.reconstructFormattingElements();
                    tb.framesetOk(false);
                    HtmlTreeBuilderState.handleRawtext(startTag, tb);
                    return true;
                case "iframe":
                    tb.framesetOk(false);
                    HtmlTreeBuilderState.handleRawtext(startTag, tb);
                    return true;
                case "noembed":
                    HtmlTreeBuilderState.handleRawtext(startTag, tb);
                    return true;
                case "select":
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    tb.framesetOk(false);
                    HtmlTreeBuilderState state = tb.state();
                    if (state.equals(InTable) || state.equals(InCaption) || state.equals(InTableBody) || state.equals(InRow) || state.equals(InCell)) {
                        tb.transition(InSelectInTable);
                        return true;
                    }
                    tb.transition(InSelect);
                    return true;
                case "math":
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    return true;
                case "svg":
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    return true;
                case "h1":
                case "h2":
                case "h3":
                case "h4":
                case "h5":
                case "h6":
                    if (tb.inButtonScope("p")) {
                        tb.processEndTag("p");
                    }
                    if (StringUtil.inSorted(tb.currentElement().normalName(), Constants.Headings)) {
                        tb.error(this);
                        tb.pop();
                    }
                    tb.insert(startTag);
                    return true;
                case "pre":
                case "listing":
                    if (tb.inButtonScope("p")) {
                        tb.processEndTag("p");
                    }
                    tb.insert(startTag);
                    tb.reader.matchConsume("\n");
                    tb.framesetOk(false);
                    return true;
                case "dd":
                case "dt":
                    tb.framesetOk(false);
                    ArrayList<Element> stack4 = tb.getStack();
                    int i2 = stack4.size() - 1;
                    while (true) {
                        if (i2 > 0) {
                            Element el2 = stack4.get(i2);
                            if (StringUtil.inSorted(el2.normalName(), Constants.DdDt)) {
                                tb.processEndTag(el2.normalName());
                            } else if (!tb.isSpecial(el2) || StringUtil.inSorted(el2.normalName(), Constants.InBodyStartLiBreakers)) {
                                i2--;
                            }
                        }
                    }
                    if (tb.inButtonScope("p")) {
                        tb.processEndTag("p");
                    }
                    tb.insert(startTag);
                    return true;
                case "optgroup":
                case "option":
                    if (tb.currentElement().normalName().equals("option")) {
                        tb.processEndTag("option");
                    }
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    return true;
                case "rp":
                case "rt":
                    if (tb.inScope("ruby")) {
                        tb.generateImpliedEndTags();
                        if (!tb.currentElement().normalName().equals("ruby")) {
                            tb.error(this);
                            tb.popStackToBefore("ruby");
                        }
                        tb.insert(startTag);
                        return true;
                    }
                    return true;
                default:
                    if (StringUtil.inSorted(name, Constants.InBodyStartEmptyFormatters)) {
                        tb.reconstructFormattingElements();
                        tb.insertEmpty(startTag);
                        tb.framesetOk(false);
                        return true;
                    }
                    if (StringUtil.inSorted(name, Constants.InBodyStartPClosers)) {
                        if (tb.inButtonScope("p")) {
                            tb.processEndTag("p");
                        }
                        tb.insert(startTag);
                        return true;
                    }
                    if (StringUtil.inSorted(name, Constants.InBodyStartToHead)) {
                        return tb.process(t, InHead);
                    }
                    if (StringUtil.inSorted(name, Constants.Formatters)) {
                        tb.reconstructFormattingElements();
                        tb.pushActiveFormattingElements(tb.insert(startTag));
                        return true;
                    }
                    if (StringUtil.inSorted(name, Constants.InBodyStartApplets)) {
                        tb.reconstructFormattingElements();
                        tb.insert(startTag);
                        tb.insertMarkerToFormattingElements();
                        tb.framesetOk(false);
                        return true;
                    }
                    if (StringUtil.inSorted(name, Constants.InBodyStartMedia)) {
                        tb.insertEmpty(startTag);
                        return true;
                    }
                    if (StringUtil.inSorted(name, Constants.InBodyStartDrop)) {
                        tb.error(this);
                        return false;
                    }
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    return true;
            }
        }

        private boolean inBodyEndTag(Token t, HtmlTreeBuilder tb) {
            Token.EndTag endTag;
            String name;
            endTag = t.asEndTag();
            name = endTag.normalName();
            switch (name) {
                case "sarcasm":
                case "span":
                    return anyOtherEndTag(t, tb);
                case "li":
                    if (!tb.inListItemScope(name)) {
                        tb.error(this);
                        return false;
                    }
                    tb.generateImpliedEndTags(name);
                    if (!tb.currentElement().normalName().equals(name)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(name);
                    return true;
                case "body":
                    if (!tb.inScope(NCXDocumentV3.XHTMLTgs.body)) {
                        tb.error(this);
                        return false;
                    }
                    tb.transition(AfterBody);
                    return true;
                case "html":
                    boolean notIgnored = tb.processEndTag(NCXDocumentV3.XHTMLTgs.body);
                    if (notIgnored) {
                        return tb.process(endTag);
                    }
                    return true;
                case "form":
                    Element currentForm = tb.getFormElement();
                    tb.setFormElement(null);
                    if (currentForm == null || !tb.inScope(name)) {
                        tb.error(this);
                        return false;
                    }
                    tb.generateImpliedEndTags();
                    if (!tb.currentElement().normalName().equals(name)) {
                        tb.error(this);
                    }
                    tb.removeFromStack(currentForm);
                    return true;
                case "p":
                    if (!tb.inButtonScope(name)) {
                        tb.error(this);
                        tb.processStartTag(name);
                        return tb.process(endTag);
                    }
                    tb.generateImpliedEndTags(name);
                    if (!tb.currentElement().normalName().equals(name)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(name);
                    return true;
                case "dd":
                case "dt":
                    if (!tb.inScope(name)) {
                        tb.error(this);
                        return false;
                    }
                    tb.generateImpliedEndTags(name);
                    if (!tb.currentElement().normalName().equals(name)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(name);
                    return true;
                case "h1":
                case "h2":
                case "h3":
                case "h4":
                case "h5":
                case "h6":
                    if (!tb.inScope(Constants.Headings)) {
                        tb.error(this);
                        return false;
                    }
                    tb.generateImpliedEndTags(name);
                    if (!tb.currentElement().normalName().equals(name)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(Constants.Headings);
                    return true;
                case "br":
                    tb.error(this);
                    tb.processStartTag("br");
                    return false;
                default:
                    if (StringUtil.inSorted(name, Constants.InBodyEndAdoptionFormatters)) {
                        return inBodyEndTagAdoption(t, tb);
                    }
                    if (StringUtil.inSorted(name, Constants.InBodyEndClosers)) {
                        if (!tb.inScope(name)) {
                            tb.error(this);
                            return false;
                        }
                        tb.generateImpliedEndTags();
                        if (!tb.currentElement().normalName().equals(name)) {
                            tb.error(this);
                        }
                        tb.popStackToClose(name);
                        return true;
                    }
                    if (StringUtil.inSorted(name, Constants.InBodyStartApplets)) {
                        if (!tb.inScope("name")) {
                            if (!tb.inScope(name)) {
                                tb.error(this);
                                return false;
                            }
                            tb.generateImpliedEndTags();
                            if (!tb.currentElement().normalName().equals(name)) {
                                tb.error(this);
                            }
                            tb.popStackToClose(name);
                            tb.clearFormattingElementsToLastMarker();
                            return true;
                        }
                        return true;
                    }
                    return anyOtherEndTag(t, tb);
            }
        }

        boolean anyOtherEndTag(Token t, HtmlTreeBuilder tb) {
            String name = t.asEndTag().normalName;
            ArrayList<Element> stack = tb.getStack();
            for (int pos = stack.size() - 1; pos >= 0; pos--) {
                Element node = stack.get(pos);
                if (node.normalName().equals(name)) {
                    tb.generateImpliedEndTags(name);
                    if (!name.equals(tb.currentElement().normalName())) {
                        tb.error(this);
                    }
                    tb.popStackToClose(name);
                    return true;
                }
                if (tb.isSpecial(node)) {
                    tb.error(this);
                    return false;
                }
            }
            return true;
        }

        private boolean inBodyEndTagAdoption(Token t, HtmlTreeBuilder tb) {
            Token.EndTag endTag = t.asEndTag();
            String name = endTag.normalName();
            ArrayList<Element> stack = tb.getStack();
            for (int i = 0; i < 8; i++) {
                Element formatEl = tb.getActiveFormattingElement(name);
                if (formatEl == null) {
                    return anyOtherEndTag(t, tb);
                }
                if (!tb.onStack(formatEl)) {
                    tb.error(this);
                    tb.removeFromActiveFormattingElements(formatEl);
                    return true;
                }
                if (!tb.inScope(formatEl.normalName())) {
                    tb.error(this);
                    return false;
                }
                if (tb.currentElement() != formatEl) {
                    tb.error(this);
                }
                Element furthestBlock = null;
                Element commonAncestor = null;
                boolean seenFormattingElement = false;
                int stackSize = stack.size();
                int bookmark = -1;
                int si = 0;
                while (true) {
                    if (si >= stackSize || si >= 64) {
                        break;
                    }
                    Element el = stack.get(si);
                    if (el == formatEl) {
                        commonAncestor = stack.get(si - 1);
                        seenFormattingElement = true;
                        bookmark = tb.positionOfElement(el);
                    } else if (seenFormattingElement && tb.isSpecial(el)) {
                        furthestBlock = el;
                        break;
                    }
                    si++;
                }
                if (furthestBlock == null) {
                    tb.popStackToClose(formatEl.normalName());
                    tb.removeFromActiveFormattingElements(formatEl);
                    return true;
                }
                Element node = furthestBlock;
                Element lastNode = furthestBlock;
                for (int j = 0; j < 3; j++) {
                    if (tb.onStack(node)) {
                        node = tb.aboveOnStack(node);
                    }
                    if (!tb.isInActiveFormattingElements(node)) {
                        tb.removeFromStack(node);
                    } else {
                        if (node == formatEl) {
                            break;
                        }
                        Element replacement = new Element(Tag.valueOf(node.nodeName(), ParseSettings.preserveCase), tb.getBaseUri());
                        tb.replaceActiveFormattingElement(node, replacement);
                        tb.replaceOnStack(node, replacement);
                        node = replacement;
                        if (lastNode == furthestBlock) {
                            bookmark = tb.positionOfElement(node) + 1;
                        }
                        if (lastNode.parent() != null) {
                            lastNode.remove();
                        }
                        node.appendChild(lastNode);
                        lastNode = node;
                    }
                }
                if (commonAncestor != null) {
                    if (StringUtil.inSorted(commonAncestor.normalName(), Constants.InBodyEndTableFosters)) {
                        if (lastNode.parent() != null) {
                            lastNode.remove();
                        }
                        tb.insertInFosterParent(lastNode);
                    } else {
                        if (lastNode.parent() != null) {
                            lastNode.remove();
                        }
                        commonAncestor.appendChild(lastNode);
                    }
                }
                Element adopter = new Element(formatEl.tag(), tb.getBaseUri());
                adopter.attributes().addAll(formatEl.attributes());
                Node[] childNodes = (Node[]) furthestBlock.childNodes().toArray(new Node[0]);
                for (Node childNode : childNodes) {
                    adopter.appendChild(childNode);
                }
                furthestBlock.appendChild(adopter);
                tb.removeFromActiveFormattingElements(formatEl);
                tb.pushWithBookmark(adopter, bookmark);
                tb.removeFromStack(formatEl);
                tb.insertOnStackAfter(furthestBlock, adopter);
            }
            return true;
        }
    },
    Text { // from class: org.jsoup.parser.HtmlTreeBuilderState.8
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isCharacter()) {
                tb.insert(t.asCharacter());
                return true;
            }
            if (t.isEOF()) {
                tb.error(this);
                tb.pop();
                tb.transition(tb.originalState());
                return tb.process(t);
            }
            if (t.isEndTag()) {
                tb.pop();
                tb.transition(tb.originalState());
                return true;
            }
            return true;
        }
    },
    InTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.9
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isCharacter()) {
                tb.newPendingTableCharacters();
                tb.markInsertionMode();
                tb.transition(InTableText);
                return tb.process(t);
            }
            if (t.isComment()) {
                tb.insert(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (t.isStartTag()) {
                Token.StartTag startTag = t.asStartTag();
                String name = startTag.normalName();
                if (name.equals("caption")) {
                    tb.clearStackToTableContext();
                    tb.insertMarkerToFormattingElements();
                    tb.insert(startTag);
                    tb.transition(InCaption);
                    return true;
                }
                if (name.equals("colgroup")) {
                    tb.clearStackToTableContext();
                    tb.insert(startTag);
                    tb.transition(InColumnGroup);
                    return true;
                }
                if (name.equals("col")) {
                    tb.processStartTag("colgroup");
                    return tb.process(t);
                }
                if (StringUtil.inSorted(name, Constants.InTableToBody)) {
                    tb.clearStackToTableContext();
                    tb.insert(startTag);
                    tb.transition(InTableBody);
                    return true;
                }
                if (StringUtil.inSorted(name, Constants.InTableAddBody)) {
                    tb.processStartTag("tbody");
                    return tb.process(t);
                }
                if (name.equals("table")) {
                    tb.error(this);
                    boolean processed = tb.processEndTag("table");
                    if (processed) {
                        return tb.process(t);
                    }
                    return true;
                }
                if (StringUtil.inSorted(name, Constants.InTableToHead)) {
                    return tb.process(t, InHead);
                }
                if (name.equals("input")) {
                    if (!startTag.hasAttributes() || !startTag.attributes.get("type").equalsIgnoreCase("hidden")) {
                        return anythingElse(t, tb);
                    }
                    tb.insertEmpty(startTag);
                    return true;
                }
                if (name.equals("form")) {
                    tb.error(this);
                    if (tb.getFormElement() != null) {
                        return false;
                    }
                    tb.insertForm(startTag, false);
                    return true;
                }
                return anythingElse(t, tb);
            }
            if (t.isEndTag()) {
                Token.EndTag endTag = t.asEndTag();
                String name2 = endTag.normalName();
                if (name2.equals("table")) {
                    if (!tb.inTableScope(name2)) {
                        tb.error(this);
                        return false;
                    }
                    tb.popStackToClose("table");
                    tb.resetInsertionMode();
                    return true;
                }
                if (StringUtil.inSorted(name2, Constants.InTableEndErr)) {
                    tb.error(this);
                    return false;
                }
                return anythingElse(t, tb);
            }
            if (t.isEOF()) {
                if (tb.currentElement().normalName().equals("html")) {
                    tb.error(this);
                    return true;
                }
                return true;
            }
            return anythingElse(t, tb);
        }

        boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            boolean processed;
            tb.error(this);
            if (StringUtil.inSorted(tb.currentElement().normalName(), Constants.InTableFoster)) {
                tb.setFosterInserts(true);
                processed = tb.process(t, InBody);
                tb.setFosterInserts(false);
            } else {
                processed = tb.process(t, InBody);
            }
            return processed;
        }
    },
    InTableText { // from class: org.jsoup.parser.HtmlTreeBuilderState.10
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.type == Token.TokenType.Character) {
                Token.Character c = t.asCharacter();
                if (c.getData().equals(HtmlTreeBuilderState.nullString)) {
                    tb.error(this);
                    return false;
                }
                tb.getPendingTableCharacters().add(c.getData());
                return true;
            }
            if (tb.getPendingTableCharacters().size() > 0) {
                for (String character : tb.getPendingTableCharacters()) {
                    if (!HtmlTreeBuilderState.isWhitespace(character)) {
                        tb.error(this);
                        if (StringUtil.inSorted(tb.currentElement().normalName(), Constants.InTableFoster)) {
                            tb.setFosterInserts(true);
                            tb.process(new Token.Character().data(character), InBody);
                            tb.setFosterInserts(false);
                        } else {
                            tb.process(new Token.Character().data(character), InBody);
                        }
                    } else {
                        tb.insert(new Token.Character().data(character));
                    }
                }
                tb.newPendingTableCharacters();
            }
            tb.transition(tb.originalState());
            return tb.process(t);
        }
    },
    InCaption { // from class: org.jsoup.parser.HtmlTreeBuilderState.11
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isEndTag() && t.asEndTag().normalName().equals("caption")) {
                Token.EndTag endTag = t.asEndTag();
                String name = endTag.normalName();
                if (!tb.inTableScope(name)) {
                    tb.error(this);
                    return false;
                }
                tb.generateImpliedEndTags();
                if (!tb.currentElement().normalName().equals("caption")) {
                    tb.error(this);
                }
                tb.popStackToClose("caption");
                tb.clearFormattingElementsToLastMarker();
                tb.transition(InTable);
                return true;
            }
            if ((t.isStartTag() && StringUtil.inSorted(t.asStartTag().normalName(), Constants.InCellCol)) || (t.isEndTag() && t.asEndTag().normalName().equals("table"))) {
                tb.error(this);
                boolean processed = tb.processEndTag("caption");
                if (processed) {
                    return tb.process(t);
                }
                return true;
            }
            if (t.isEndTag() && StringUtil.inSorted(t.asEndTag().normalName(), Constants.InCaptionIgnore)) {
                tb.error(this);
                return false;
            }
            return tb.process(t, InBody);
        }
    },
    InColumnGroup { // from class: org.jsoup.parser.HtmlTreeBuilderState.12
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            Token.StartTag startTag;
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
                return true;
            }
            switch (AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
                case 1:
                    tb.insert(t.asComment());
                    break;
                case 2:
                    tb.error(this);
                    break;
                case 3:
                    startTag = t.asStartTag();
                    switch (startTag.normalName()) {
                        case "html":
                            break;
                        case "col":
                            tb.insertEmpty(startTag);
                            break;
                    }
                case 4:
                    Token.EndTag endTag = t.asEndTag();
                    if (!endTag.normalName.equals("colgroup")) {
                        break;
                    } else if (tb.currentElement().normalName().equals("html")) {
                        tb.error(this);
                        break;
                    } else {
                        tb.pop();
                        tb.transition(InTable);
                        break;
                    }
                case 6:
                    if (!tb.currentElement().normalName().equals("html")) {
                        break;
                    }
                    break;
            }
            return true;
        }

        private boolean anythingElse(Token t, TreeBuilder tb) {
            boolean processed = tb.processEndTag("colgroup");
            if (processed) {
                return tb.process(t);
            }
            return true;
        }
    },
    InTableBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.13
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            switch (AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
                case 3:
                    Token.StartTag startTag = t.asStartTag();
                    String name = startTag.normalName();
                    if (name.equals("template")) {
                        tb.insert(startTag);
                        return true;
                    }
                    if (name.equals("tr")) {
                        tb.clearStackToTableBodyContext();
                        tb.insert(startTag);
                        tb.transition(InRow);
                        return true;
                    }
                    if (StringUtil.inSorted(name, Constants.InCellNames)) {
                        tb.error(this);
                        tb.processStartTag("tr");
                        return tb.process(startTag);
                    }
                    if (StringUtil.inSorted(name, Constants.InTableBodyExit)) {
                        return exitTableBody(t, tb);
                    }
                    return anythingElse(t, tb);
                case 4:
                    Token.EndTag endTag = t.asEndTag();
                    String name2 = endTag.normalName();
                    if (StringUtil.inSorted(name2, Constants.InTableEndIgnore)) {
                        if (!tb.inTableScope(name2)) {
                            tb.error(this);
                            return false;
                        }
                        tb.clearStackToTableBodyContext();
                        tb.pop();
                        tb.transition(InTable);
                        return true;
                    }
                    if (name2.equals("table")) {
                        return exitTableBody(t, tb);
                    }
                    if (StringUtil.inSorted(name2, Constants.InTableBodyEndIgnore)) {
                        tb.error(this);
                        return false;
                    }
                    return anythingElse(t, tb);
                default:
                    return anythingElse(t, tb);
            }
        }

        private boolean exitTableBody(Token t, HtmlTreeBuilder tb) {
            if (!tb.inTableScope("tbody") && !tb.inTableScope("thead") && !tb.inScope("tfoot")) {
                tb.error(this);
                return false;
            }
            tb.clearStackToTableBodyContext();
            tb.processEndTag(tb.currentElement().normalName());
            return tb.process(t);
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            return tb.process(t, InTable);
        }
    },
    InRow { // from class: org.jsoup.parser.HtmlTreeBuilderState.14
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isStartTag()) {
                Token.StartTag startTag = t.asStartTag();
                String name = startTag.normalName();
                if (name.equals("template")) {
                    tb.insert(startTag);
                    return true;
                }
                if (StringUtil.inSorted(name, Constants.InCellNames)) {
                    tb.clearStackToTableRowContext();
                    tb.insert(startTag);
                    tb.transition(InCell);
                    tb.insertMarkerToFormattingElements();
                    return true;
                }
                if (StringUtil.inSorted(name, Constants.InRowMissing)) {
                    return handleMissingTr(t, tb);
                }
                return anythingElse(t, tb);
            }
            if (t.isEndTag()) {
                Token.EndTag endTag = t.asEndTag();
                String name2 = endTag.normalName();
                if (name2.equals("tr")) {
                    if (!tb.inTableScope(name2)) {
                        tb.error(this);
                        return false;
                    }
                    tb.clearStackToTableRowContext();
                    tb.pop();
                    tb.transition(InTableBody);
                    return true;
                }
                if (name2.equals("table")) {
                    return handleMissingTr(t, tb);
                }
                if (StringUtil.inSorted(name2, Constants.InTableToBody)) {
                    if (!tb.inTableScope(name2) || !tb.inTableScope("tr")) {
                        tb.error(this);
                        return false;
                    }
                    tb.clearStackToTableRowContext();
                    tb.pop();
                    tb.transition(InTableBody);
                    return true;
                }
                if (StringUtil.inSorted(name2, Constants.InRowIgnore)) {
                    tb.error(this);
                    return false;
                }
                return anythingElse(t, tb);
            }
            return anythingElse(t, tb);
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            return tb.process(t, InTable);
        }

        private boolean handleMissingTr(Token t, TreeBuilder tb) {
            boolean processed = tb.processEndTag("tr");
            if (processed) {
                return tb.process(t);
            }
            return false;
        }
    },
    InCell { // from class: org.jsoup.parser.HtmlTreeBuilderState.15
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isEndTag()) {
                Token.EndTag endTag = t.asEndTag();
                String name = endTag.normalName();
                if (StringUtil.inSorted(name, Constants.InCellNames)) {
                    if (!tb.inTableScope(name)) {
                        tb.error(this);
                        tb.transition(InRow);
                        return false;
                    }
                    tb.generateImpliedEndTags();
                    if (!tb.currentElement().normalName().equals(name)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(name);
                    tb.clearFormattingElementsToLastMarker();
                    tb.transition(InRow);
                    return true;
                }
                if (StringUtil.inSorted(name, Constants.InCellBody)) {
                    tb.error(this);
                    return false;
                }
                if (StringUtil.inSorted(name, Constants.InCellTable)) {
                    if (!tb.inTableScope(name)) {
                        tb.error(this);
                        return false;
                    }
                    closeCell(tb);
                    return tb.process(t);
                }
                return anythingElse(t, tb);
            }
            if (t.isStartTag() && StringUtil.inSorted(t.asStartTag().normalName(), Constants.InCellCol)) {
                if (!tb.inTableScope("td") && !tb.inTableScope("th")) {
                    tb.error(this);
                    return false;
                }
                closeCell(tb);
                return tb.process(t);
            }
            return anythingElse(t, tb);
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            return tb.process(t, InBody);
        }

        private void closeCell(HtmlTreeBuilder tb) {
            if (tb.inTableScope("td")) {
                tb.processEndTag("td");
            } else {
                tb.processEndTag("th");
            }
        }
    },
    InSelect { // from class: org.jsoup.parser.HtmlTreeBuilderState.16
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            String name;
            switch (AnonymousClass24.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
                case 1:
                    tb.insert(t.asComment());
                    return true;
                case 2:
                    tb.error(this);
                    return false;
                case 3:
                    Token.StartTag start = t.asStartTag();
                    String name2 = start.normalName();
                    if (name2.equals("html")) {
                        return tb.process(start, InBody);
                    }
                    if (name2.equals("option")) {
                        if (tb.currentElement().normalName().equals("option")) {
                            tb.processEndTag("option");
                        }
                        tb.insert(start);
                        return true;
                    }
                    if (name2.equals("optgroup")) {
                        if (tb.currentElement().normalName().equals("option")) {
                            tb.processEndTag("option");
                        }
                        if (tb.currentElement().normalName().equals("optgroup")) {
                            tb.processEndTag("optgroup");
                        }
                        tb.insert(start);
                        return true;
                    }
                    if (name2.equals("select")) {
                        tb.error(this);
                        return tb.processEndTag("select");
                    }
                    if (StringUtil.inSorted(name2, Constants.InSelectEnd)) {
                        tb.error(this);
                        if (!tb.inSelectScope("select")) {
                            return false;
                        }
                        tb.processEndTag("select");
                        return tb.process(start);
                    }
                    if (name2.equals("script")) {
                        return tb.process(t, InHead);
                    }
                    return anythingElse(t, tb);
                case 4:
                    Token.EndTag end = t.asEndTag();
                    name = end.normalName();
                    switch (name) {
                        case "optgroup":
                            if (tb.currentElement().normalName().equals("option") && tb.aboveOnStack(tb.currentElement()) != null && tb.aboveOnStack(tb.currentElement()).normalName().equals("optgroup")) {
                                tb.processEndTag("option");
                            }
                            if (tb.currentElement().normalName().equals("optgroup")) {
                                tb.pop();
                                return true;
                            }
                            tb.error(this);
                            return true;
                        case "option":
                            if (tb.currentElement().normalName().equals("option")) {
                                tb.pop();
                                return true;
                            }
                            tb.error(this);
                            return true;
                        case "select":
                            if (!tb.inSelectScope(name)) {
                                tb.error(this);
                                return false;
                            }
                            tb.popStackToClose(name);
                            tb.resetInsertionMode();
                            return true;
                        default:
                            return anythingElse(t, tb);
                    }
                case 5:
                    Token.Character c = t.asCharacter();
                    if (c.getData().equals(HtmlTreeBuilderState.nullString)) {
                        tb.error(this);
                        return false;
                    }
                    tb.insert(c);
                    return true;
                case 6:
                    if (!tb.currentElement().normalName().equals("html")) {
                        tb.error(this);
                        return true;
                    }
                    return true;
                default:
                    return anythingElse(t, tb);
            }
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.error(this);
            return false;
        }
    },
    InSelectInTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.17
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isStartTag() && StringUtil.inSorted(t.asStartTag().normalName(), Constants.InSelecTableEnd)) {
                tb.error(this);
                tb.processEndTag("select");
                return tb.process(t);
            }
            if (t.isEndTag() && StringUtil.inSorted(t.asEndTag().normalName(), Constants.InSelecTableEnd)) {
                tb.error(this);
                if (tb.inTableScope(t.asEndTag().normalName())) {
                    tb.processEndTag("select");
                    return tb.process(t);
                }
                return false;
            }
            return tb.process(t, InSelect);
        }
    },
    AfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.18
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
                return true;
            }
            if (t.isComment()) {
                tb.insert(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (t.isStartTag() && t.asStartTag().normalName().equals("html")) {
                return tb.process(t, InBody);
            }
            if (t.isEndTag() && t.asEndTag().normalName().equals("html")) {
                if (tb.isFragmentParsing()) {
                    tb.error(this);
                    return false;
                }
                tb.transition(AfterAfterBody);
                return true;
            }
            if (!t.isEOF()) {
                tb.error(this);
                tb.transition(InBody);
                return tb.process(t);
            }
            return true;
        }
    },
    InFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.19
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            Token.StartTag start;
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
                return true;
            }
            if (t.isComment()) {
                tb.insert(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (!t.isStartTag()) {
                if (t.isEndTag() && t.asEndTag().normalName().equals("frameset")) {
                    if (tb.currentElement().normalName().equals("html")) {
                        tb.error(this);
                        return false;
                    }
                    tb.pop();
                    if (!tb.isFragmentParsing() && !tb.currentElement().normalName().equals("frameset")) {
                        tb.transition(AfterFrameset);
                        return true;
                    }
                    return true;
                }
                if (t.isEOF()) {
                    if (!tb.currentElement().normalName().equals("html")) {
                        tb.error(this);
                        return true;
                    }
                    return true;
                }
                tb.error(this);
                return false;
            }
            start = t.asStartTag();
            switch (start.normalName()) {
                case "html":
                    return tb.process(start, InBody);
                case "frameset":
                    tb.insert(start);
                    return true;
                case "frame":
                    tb.insertEmpty(start);
                    return true;
                case "noframes":
                    return tb.process(start, InHead);
                default:
                    tb.error(this);
                    return false;
            }
        }
    },
    AfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.20
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
                return true;
            }
            if (t.isComment()) {
                tb.insert(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (t.isStartTag() && t.asStartTag().normalName().equals("html")) {
                return tb.process(t, InBody);
            }
            if (t.isEndTag() && t.asEndTag().normalName().equals("html")) {
                tb.transition(AfterAfterFrameset);
                return true;
            }
            if (t.isStartTag() && t.asStartTag().normalName().equals("noframes")) {
                return tb.process(t, InHead);
            }
            if (!t.isEOF()) {
                tb.error(this);
                return false;
            }
            return true;
        }
    },
    AfterAfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.21
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isComment()) {
                tb.insert(t.asComment());
                return true;
            }
            if (!t.isDoctype() && (!t.isStartTag() || !t.asStartTag().normalName().equals("html"))) {
                if (HtmlTreeBuilderState.isWhitespace(t)) {
                    Element html = tb.popStackToClose("html");
                    tb.insert(t.asCharacter());
                    tb.stack.add(html);
                    tb.stack.add(html.selectFirst(NCXDocumentV3.XHTMLTgs.body));
                    return true;
                }
                if (!t.isEOF()) {
                    tb.error(this);
                    tb.transition(InBody);
                    return tb.process(t);
                }
                return true;
            }
            return tb.process(t, InBody);
        }
    },
    AfterAfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.22
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isComment()) {
                tb.insert(t.asComment());
                return true;
            }
            if (t.isDoctype() || HtmlTreeBuilderState.isWhitespace(t) || (t.isStartTag() && t.asStartTag().normalName().equals("html"))) {
                return tb.process(t, InBody);
            }
            if (!t.isEOF()) {
                if (t.isStartTag() && t.asStartTag().normalName().equals("noframes")) {
                    return tb.process(t, InHead);
                }
                tb.error(this);
                return false;
            }
            return true;
        }
    },
    ForeignContent { // from class: org.jsoup.parser.HtmlTreeBuilderState.23
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            return true;
        }
    };

    private static final String nullString = String.valueOf((char) 0);

    abstract boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder);

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isWhitespace(Token t) {
        if (t.isCharacter()) {
            String data = t.asCharacter().getData();
            return StringUtil.isBlank(data);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isWhitespace(String data) {
        return StringUtil.isBlank(data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRcData(Token.StartTag startTag, HtmlTreeBuilder tb) {
        tb.tokeniser.transition(TokeniserState.Rcdata);
        tb.markInsertionMode();
        tb.transition(Text);
        tb.insert(startTag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
        tb.insert(startTag);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/HtmlTreeBuilderState$Constants.class */
    static final class Constants {
        static final String[] InHeadEmpty = {"base", "basefont", "bgsound", "command", "link"};
        static final String[] InHeadRaw = {"noframes", "style"};
        static final String[] InHeadEnd = {NCXDocumentV3.XHTMLTgs.body, "br", "html"};
        static final String[] AfterHeadBody = {NCXDocumentV3.XHTMLTgs.body, "html"};
        static final String[] BeforeHtmlToHead = {NCXDocumentV3.XHTMLTgs.body, "br", "head", "html"};
        static final String[] InHeadNoScriptHead = {"basefont", "bgsound", "link", "meta", "noframes", "style"};
        static final String[] InBodyStartToHead = {"base", "basefont", "bgsound", "command", "link", "meta", "noframes", "script", "style", "title"};
        static final String[] InBodyStartPClosers = {"address", "article", "aside", "blockquote", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "menu", "nav", NCXDocumentV3.XHTMLTgs.ol, "p", "section", "summary", "ul"};
        static final String[] Headings = {NCXDocumentV3.XHTMLTgs.h1, "h2", "h3", "h4", "h5", "h6"};
        static final String[] InBodyStartLiBreakers = {"address", "div", "p"};
        static final String[] DdDt = {"dd", "dt"};
        static final String[] Formatters = {OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "big", "code", "em", "font", "i", OperatorName.CLOSE_AND_STROKE, "small", "strike", "strong", "tt", "u"};
        static final String[] InBodyStartApplets = {"applet", "marquee", "object"};
        static final String[] InBodyStartEmptyFormatters = {"area", "br", "embed", "img", "keygen", "wbr"};
        static final String[] InBodyStartMedia = {"param", PackageDocumentBase.DCTags.source, "track"};
        static final String[] InBodyStartInputAttribs = {"action", "name", "prompt"};
        static final String[] InBodyStartDrop = {"caption", "col", "colgroup", "frame", "head", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InBodyEndClosers = {"address", "article", "aside", "blockquote", "button", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", "menu", "nav", NCXDocumentV3.XHTMLTgs.ol, "pre", "section", "summary", "ul"};
        static final String[] InBodyEndAdoptionFormatters = {"a", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "big", "code", "em", "font", "i", "nobr", OperatorName.CLOSE_AND_STROKE, "small", "strike", "strong", "tt", "u"};
        static final String[] InBodyEndTableFosters = {"table", "tbody", "tfoot", "thead", "tr"};
        static final String[] InTableToBody = {"tbody", "tfoot", "thead"};
        static final String[] InTableAddBody = {"td", "th", "tr"};
        static final String[] InTableToHead = {"script", "style"};
        static final String[] InCellNames = {"td", "th"};
        static final String[] InCellBody = {NCXDocumentV3.XHTMLTgs.body, "caption", "col", "colgroup", "html"};
        static final String[] InCellTable = {"table", "tbody", "tfoot", "thead", "tr"};
        static final String[] InCellCol = {"caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableEndErr = {NCXDocumentV3.XHTMLTgs.body, "caption", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableFoster = {"table", "tbody", "tfoot", "thead", "tr"};
        static final String[] InTableBodyExit = {"caption", "col", "colgroup", "tbody", "tfoot", "thead"};
        static final String[] InTableBodyEndIgnore = {NCXDocumentV3.XHTMLTgs.body, "caption", "col", "colgroup", "html", "td", "th", "tr"};
        static final String[] InRowMissing = {"caption", "col", "colgroup", "tbody", "tfoot", "thead", "tr"};
        static final String[] InRowIgnore = {NCXDocumentV3.XHTMLTgs.body, "caption", "col", "colgroup", "html", "td", "th"};
        static final String[] InSelectEnd = {"input", "keygen", "textarea"};
        static final String[] InSelecTableEnd = {"caption", "table", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableEndIgnore = {"tbody", "tfoot", "thead"};
        static final String[] InHeadNoscriptIgnore = {"head", "noscript"};
        static final String[] InCaptionIgnore = {NCXDocumentV3.XHTMLTgs.body, "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"};

        Constants() {
        }
    }
}
