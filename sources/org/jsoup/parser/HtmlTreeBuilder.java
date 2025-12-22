package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Token;
import org.jsoup.select.Elements;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/HtmlTreeBuilder.class */
public class HtmlTreeBuilder extends TreeBuilder {
    static final String[] TagsSearchInScope;
    static final String[] TagSearchList;
    static final String[] TagSearchButton;
    static final String[] TagSearchTableScope;
    static final String[] TagSearchSelectScope;
    static final String[] TagSearchEndTags;
    static final String[] TagSearchSpecial;
    public static final int MaxScopeSearchDepth = 100;
    private HtmlTreeBuilderState state;
    private HtmlTreeBuilderState originalState;
    private boolean baseUriSetFromDoc;

    @Nullable
    private Element headElement;

    @Nullable
    private FormElement formElement;

    @Nullable
    private Element contextElement;
    private ArrayList<Element> formattingElements;
    private List<String> pendingTableCharacters;
    private Token.EndTag emptyEnd;
    private boolean framesetOk;
    private boolean fosterInserts;
    private boolean fragmentParsing;
    private static final int maxQueueDepth = 256;
    private String[] specificScopeTarget = {null};
    static final /* synthetic */ boolean $assertionsDisabled;

    @Override // org.jsoup.parser.TreeBuilder
    public /* bridge */ /* synthetic */ boolean processStartTag(String str, Attributes attributes) {
        return super.processStartTag(str, attributes);
    }

    static {
        $assertionsDisabled = !HtmlTreeBuilder.class.desiredAssertionStatus();
        TagsSearchInScope = new String[]{"applet", "caption", "html", "marquee", "object", "table", "td", "th"};
        TagSearchList = new String[]{NCXDocumentV3.XHTMLTgs.ol, "ul"};
        TagSearchButton = new String[]{"button"};
        TagSearchTableScope = new String[]{"html", "table"};
        TagSearchSelectScope = new String[]{"optgroup", "option"};
        TagSearchEndTags = new String[]{"dd", "dt", NCXDocumentV3.XHTMLTgs.li, "optgroup", "option", "p", "rp", "rt"};
        TagSearchSpecial = new String[]{"address", "applet", "area", "article", "aside", "base", "basefont", "bgsound", "blockquote", NCXDocumentV3.XHTMLTgs.body, "br", "button", "caption", "center", "col", "colgroup", "command", "dd", "details", "dir", "div", "dl", "dt", "embed", "fieldset", "figcaption", "figure", "footer", "form", "frame", "frameset", NCXDocumentV3.XHTMLTgs.h1, "h2", "h3", "h4", "h5", "h6", "head", "header", "hgroup", "hr", "html", "iframe", "img", "input", "isindex", NCXDocumentV3.XHTMLTgs.li, "link", "listing", "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", "object", NCXDocumentV3.XHTMLTgs.ol, "p", "param", "plaintext", "pre", "script", "section", "select", "style", "summary", "table", "tbody", "td", "textarea", "tfoot", "th", "thead", "title", "tr", "ul", "wbr", "xmp"};
    }

    @Override // org.jsoup.parser.TreeBuilder
    ParseSettings defaultSettings() {
        return ParseSettings.htmlDefault;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jsoup.parser.TreeBuilder
    public HtmlTreeBuilder newInstance() {
        return new HtmlTreeBuilder();
    }

    @Override // org.jsoup.parser.TreeBuilder
    @ParametersAreNonnullByDefault
    protected void initialiseParse(Reader input, String baseUri, Parser parser) {
        super.initialiseParse(input, baseUri, parser);
        this.state = HtmlTreeBuilderState.Initial;
        this.originalState = null;
        this.baseUriSetFromDoc = false;
        this.headElement = null;
        this.formElement = null;
        this.contextElement = null;
        this.formattingElements = new ArrayList<>();
        this.pendingTableCharacters = new ArrayList();
        this.emptyEnd = new Token.EndTag();
        this.framesetOk = true;
        this.fosterInserts = false;
        this.fragmentParsing = false;
    }

    @Override // org.jsoup.parser.TreeBuilder
    List<Node> parseFragment(String inputFragment, @Nullable Element context, String baseUri, Parser parser) {
        this.state = HtmlTreeBuilderState.Initial;
        initialiseParse(new StringReader(inputFragment), baseUri, parser);
        this.contextElement = context;
        this.fragmentParsing = true;
        Element root = null;
        if (context != null) {
            if (context.ownerDocument() != null) {
                this.doc.quirksMode(context.ownerDocument().quirksMode());
            }
            String contextTag = context.normalName();
            if (StringUtil.in(contextTag, "title", "textarea")) {
                this.tokeniser.transition(TokeniserState.Rcdata);
            } else if (StringUtil.in(contextTag, "iframe", "noembed", "noframes", "style", "xmp")) {
                this.tokeniser.transition(TokeniserState.Rawtext);
            } else if (contextTag.equals("script")) {
                this.tokeniser.transition(TokeniserState.ScriptData);
            } else if (!contextTag.equals("noscript") && contextTag.equals("plaintext")) {
                this.tokeniser.transition(TokeniserState.Data);
            } else {
                this.tokeniser.transition(TokeniserState.Data);
            }
            root = new Element(Tag.valueOf(contextTag, this.settings), baseUri);
            this.doc.appendChild(root);
            this.stack.add(root);
            resetInsertionMode();
            Elements contextChain = context.parents();
            contextChain.add(0, context);
            Iterator<Element> it = contextChain.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Element parent = it.next();
                if (parent instanceof FormElement) {
                    this.formElement = (FormElement) parent;
                    break;
                }
            }
        }
        runParser();
        if (context != null) {
            List<Node> nodes = root.siblingNodes();
            if (!nodes.isEmpty()) {
                root.insertChildren(-1, nodes);
            }
            return root.childNodes();
        }
        return this.doc.childNodes();
    }

    @Override // org.jsoup.parser.TreeBuilder
    protected boolean process(Token token) {
        this.currentToken = token;
        return this.state.process(token, this);
    }

    boolean process(Token token, HtmlTreeBuilderState state) {
        this.currentToken = token;
        return state.process(token, this);
    }

    void transition(HtmlTreeBuilderState state) {
        this.state = state;
    }

    HtmlTreeBuilderState state() {
        return this.state;
    }

    void markInsertionMode() {
        this.originalState = this.state;
    }

    HtmlTreeBuilderState originalState() {
        return this.originalState;
    }

    void framesetOk(boolean framesetOk) {
        this.framesetOk = framesetOk;
    }

    boolean framesetOk() {
        return this.framesetOk;
    }

    Document getDocument() {
        return this.doc;
    }

    String getBaseUri() {
        return this.baseUri;
    }

    void maybeSetBaseUri(Element base) {
        if (this.baseUriSetFromDoc) {
            return;
        }
        String href = base.absUrl("href");
        if (href.length() != 0) {
            this.baseUri = href;
            this.baseUriSetFromDoc = true;
            this.doc.setBaseUri(href);
        }
    }

    boolean isFragmentParsing() {
        return this.fragmentParsing;
    }

    void error(HtmlTreeBuilderState state) {
        if (this.parser.getErrors().canAddError()) {
            this.parser.getErrors().add(new ParseError(this.reader.pos(), "Unexpected token [%s] when in state [%s]", this.currentToken.tokenType(), state));
        }
    }

    Element insert(Token.StartTag startTag) {
        if (startTag.hasAttributes() && !startTag.attributes.isEmpty()) {
            int dupes = startTag.attributes.deduplicate(this.settings);
            if (dupes > 0) {
                error("Duplicate attribute");
            }
        }
        if (startTag.isSelfClosing()) {
            Element el = insertEmpty(startTag);
            this.stack.add(el);
            this.tokeniser.transition(TokeniserState.Data);
            this.tokeniser.emit(this.emptyEnd.reset().name(el.tagName()));
            return el;
        }
        Element el2 = new Element(Tag.valueOf(startTag.name(), this.settings), null, this.settings.normalizeAttributes(startTag.attributes));
        insert(el2);
        return el2;
    }

    Element insertStartTag(String startTagName) {
        Element el = new Element(Tag.valueOf(startTagName, this.settings), null);
        insert(el);
        return el;
    }

    void insert(Element el) {
        insertNode(el);
        this.stack.add(el);
    }

    Element insertEmpty(Token.StartTag startTag) {
        Tag tag = Tag.valueOf(startTag.name(), this.settings);
        Element el = new Element(tag, null, this.settings.normalizeAttributes(startTag.attributes));
        insertNode(el);
        if (startTag.isSelfClosing()) {
            if (tag.isKnownTag()) {
                if (!tag.isEmpty()) {
                    this.tokeniser.error("Tag cannot be self closing; not a void tag");
                }
            } else {
                tag.setSelfClosing();
            }
        }
        return el;
    }

    FormElement insertForm(Token.StartTag startTag, boolean onStack) {
        Tag tag = Tag.valueOf(startTag.name(), this.settings);
        FormElement el = new FormElement(tag, null, this.settings.normalizeAttributes(startTag.attributes));
        setFormElement(el);
        insertNode(el);
        if (onStack) {
            this.stack.add(el);
        }
        return el;
    }

    void insert(Token.Comment commentToken) {
        Comment comment = new Comment(commentToken.getData());
        insertNode(comment);
    }

    void insert(Token.Character characterToken) {
        Node node;
        Element el = currentElement();
        if (el == null) {
            el = this.doc;
        }
        String tagName = el.normalName();
        String data = characterToken.getData();
        if (characterToken.isCData()) {
            node = new CDataNode(data);
        } else if (isContentForTagData(tagName)) {
            node = new DataNode(data);
        } else {
            node = new TextNode(data);
        }
        el.appendChild(node);
    }

    private void insertNode(Node node) {
        if (this.stack.isEmpty()) {
            this.doc.appendChild(node);
        } else if (isFosterInserts()) {
            insertInFosterParent(node);
        } else {
            currentElement().appendChild(node);
        }
        if ((node instanceof Element) && ((Element) node).tag().isFormListed() && this.formElement != null) {
            this.formElement.addElement((Element) node);
        }
    }

    Element pop() {
        int size = this.stack.size();
        return this.stack.remove(size - 1);
    }

    void push(Element element) {
        this.stack.add(element);
    }

    ArrayList<Element> getStack() {
        return this.stack;
    }

    boolean onStack(Element el) {
        return isElementInQueue(this.stack, el);
    }

    private boolean isElementInQueue(ArrayList<Element> queue, Element element) {
        int bottom = queue.size() - 1;
        int upper = bottom >= 256 ? bottom - 256 : 0;
        for (int pos = bottom; pos >= upper; pos--) {
            Element next = queue.get(pos);
            if (next == element) {
                return true;
            }
        }
        return false;
    }

    Element getFromStack(String elName) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element next = this.stack.get(pos);
            if (next.normalName().equals(elName)) {
                return next;
            }
        }
        return null;
    }

    boolean removeFromStack(Element el) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element next = this.stack.get(pos);
            if (next == el) {
                this.stack.remove(pos);
                return true;
            }
        }
        return false;
    }

    Element popStackToClose(String elName) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element el = this.stack.get(pos);
            this.stack.remove(pos);
            if (el.normalName().equals(elName)) {
                return el;
            }
        }
        return null;
    }

    void popStackToClose(String... elNames) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element next = this.stack.get(pos);
            this.stack.remove(pos);
            if (StringUtil.inSorted(next.normalName(), elNames)) {
                return;
            }
        }
    }

    void popStackToBefore(String elName) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element next = this.stack.get(pos);
            if (!next.normalName().equals(elName)) {
                this.stack.remove(pos);
            } else {
                return;
            }
        }
    }

    void clearStackToTableContext() {
        clearStackToContext("table");
    }

    void clearStackToTableBodyContext() {
        clearStackToContext("tbody", "tfoot", "thead", "template");
    }

    void clearStackToTableRowContext() {
        clearStackToContext("tr", "template");
    }

    private void clearStackToContext(String... nodeNames) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element next = this.stack.get(pos);
            if (!StringUtil.in(next.normalName(), nodeNames) && !next.normalName().equals("html")) {
                this.stack.remove(pos);
            } else {
                return;
            }
        }
    }

    Element aboveOnStack(Element el) {
        if (!$assertionsDisabled && !onStack(el)) {
            throw new AssertionError();
        }
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element next = this.stack.get(pos);
            if (next == el) {
                return this.stack.get(pos - 1);
            }
        }
        return null;
    }

    void insertOnStackAfter(Element after, Element in) {
        int i = this.stack.lastIndexOf(after);
        Validate.isTrue(i != -1);
        this.stack.add(i + 1, in);
    }

    void replaceOnStack(Element out, Element in) {
        replaceInQueue(this.stack, out, in);
    }

    private void replaceInQueue(ArrayList<Element> queue, Element out, Element in) {
        int i = queue.lastIndexOf(out);
        Validate.isTrue(i != -1);
        queue.set(i, in);
    }

    void resetInsertionMode() {
        boolean last = false;
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element node = this.stack.get(pos);
            if (pos == 0) {
                last = true;
                node = this.contextElement;
            }
            String name = node != null ? node.normalName() : "";
            if ("select".equals(name)) {
                transition(HtmlTreeBuilderState.InSelect);
                return;
            }
            if ("td".equals(name) || ("th".equals(name) && !last)) {
                transition(HtmlTreeBuilderState.InCell);
                return;
            }
            if ("tr".equals(name)) {
                transition(HtmlTreeBuilderState.InRow);
                return;
            }
            if ("tbody".equals(name) || "thead".equals(name) || "tfoot".equals(name)) {
                transition(HtmlTreeBuilderState.InTableBody);
                return;
            }
            if ("caption".equals(name)) {
                transition(HtmlTreeBuilderState.InCaption);
                return;
            }
            if ("colgroup".equals(name)) {
                transition(HtmlTreeBuilderState.InColumnGroup);
                return;
            }
            if ("table".equals(name)) {
                transition(HtmlTreeBuilderState.InTable);
                return;
            }
            if ("head".equals(name)) {
                transition(HtmlTreeBuilderState.InBody);
                return;
            }
            if (NCXDocumentV3.XHTMLTgs.body.equals(name)) {
                transition(HtmlTreeBuilderState.InBody);
                return;
            }
            if ("frameset".equals(name)) {
                transition(HtmlTreeBuilderState.InFrameset);
                return;
            } else if ("html".equals(name)) {
                transition(HtmlTreeBuilderState.BeforeHead);
                return;
            } else {
                if (last) {
                    transition(HtmlTreeBuilderState.InBody);
                    return;
                }
            }
        }
    }

    private boolean inSpecificScope(String targetName, String[] baseTypes, String[] extraTypes) {
        this.specificScopeTarget[0] = targetName;
        return inSpecificScope(this.specificScopeTarget, baseTypes, extraTypes);
    }

    private boolean inSpecificScope(String[] targetNames, String[] baseTypes, String[] extraTypes) {
        int bottom = this.stack.size() - 1;
        int top = bottom > 100 ? bottom - 100 : 0;
        for (int pos = bottom; pos >= top; pos--) {
            String elName = this.stack.get(pos).normalName();
            if (StringUtil.inSorted(elName, targetNames)) {
                return true;
            }
            if (StringUtil.inSorted(elName, baseTypes)) {
                return false;
            }
            if (extraTypes != null && StringUtil.inSorted(elName, extraTypes)) {
                return false;
            }
        }
        return false;
    }

    boolean inScope(String[] targetNames) {
        return inSpecificScope(targetNames, TagsSearchInScope, (String[]) null);
    }

    boolean inScope(String targetName) {
        return inScope(targetName, null);
    }

    boolean inScope(String targetName, String[] extras) {
        return inSpecificScope(targetName, TagsSearchInScope, extras);
    }

    boolean inListItemScope(String targetName) {
        return inScope(targetName, TagSearchList);
    }

    boolean inButtonScope(String targetName) {
        return inScope(targetName, TagSearchButton);
    }

    boolean inTableScope(String targetName) {
        return inSpecificScope(targetName, TagSearchTableScope, (String[]) null);
    }

    boolean inSelectScope(String targetName) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element el = this.stack.get(pos);
            String elName = el.normalName();
            if (elName.equals(targetName)) {
                return true;
            }
            if (!StringUtil.inSorted(elName, TagSearchSelectScope)) {
                return false;
            }
        }
        Validate.fail("Should not be reachable");
        return false;
    }

    void setHeadElement(Element headElement) {
        this.headElement = headElement;
    }

    Element getHeadElement() {
        return this.headElement;
    }

    boolean isFosterInserts() {
        return this.fosterInserts;
    }

    void setFosterInserts(boolean fosterInserts) {
        this.fosterInserts = fosterInserts;
    }

    FormElement getFormElement() {
        return this.formElement;
    }

    void setFormElement(FormElement formElement) {
        this.formElement = formElement;
    }

    void newPendingTableCharacters() {
        this.pendingTableCharacters = new ArrayList();
    }

    List<String> getPendingTableCharacters() {
        return this.pendingTableCharacters;
    }

    void generateImpliedEndTags(String excludeTag) {
        while (excludeTag != null && !currentElement().normalName().equals(excludeTag) && StringUtil.inSorted(currentElement().normalName(), TagSearchEndTags)) {
            pop();
        }
    }

    void generateImpliedEndTags() {
        generateImpliedEndTags(null);
    }

    boolean isSpecial(Element el) {
        String name = el.normalName();
        return StringUtil.inSorted(name, TagSearchSpecial);
    }

    Element lastFormattingElement() {
        if (this.formattingElements.size() > 0) {
            return this.formattingElements.get(this.formattingElements.size() - 1);
        }
        return null;
    }

    int positionOfElement(Element el) {
        for (int i = 0; i < this.formattingElements.size(); i++) {
            if (el == this.formattingElements.get(i)) {
                return i;
            }
        }
        return -1;
    }

    Element removeLastFormattingElement() {
        int size = this.formattingElements.size();
        if (size > 0) {
            return this.formattingElements.remove(size - 1);
        }
        return null;
    }

    void pushActiveFormattingElements(Element in) {
        checkActiveFormattingElements(in);
        this.formattingElements.add(in);
    }

    void pushWithBookmark(Element in, int bookmark) {
        checkActiveFormattingElements(in);
        this.formattingElements.add(bookmark, in);
    }

    void checkActiveFormattingElements(Element in) {
        Element el;
        int numSeen = 0;
        for (int pos = this.formattingElements.size() - 1; pos >= 0 && (el = this.formattingElements.get(pos)) != null; pos--) {
            if (isSameFormattingElement(in, el)) {
                numSeen++;
            }
            if (numSeen == 3) {
                this.formattingElements.remove(pos);
                return;
            }
        }
    }

    private boolean isSameFormattingElement(Element a, Element b) {
        return a.normalName().equals(b.normalName()) && a.attributes().equals(b.attributes());
    }

    void reconstructFormattingElements() {
        Element last = lastFormattingElement();
        if (last == null || onStack(last)) {
            return;
        }
        Element entry = last;
        int size = this.formattingElements.size();
        int pos = size - 1;
        boolean skip = false;
        while (true) {
            if (pos == 0) {
                skip = true;
                break;
            }
            pos--;
            entry = this.formattingElements.get(pos);
            if (entry == null || onStack(entry)) {
                break;
            }
        }
        do {
            if (!skip) {
                pos++;
                entry = this.formattingElements.get(pos);
            }
            Validate.notNull(entry);
            skip = false;
            Element newEl = insertStartTag(entry.normalName());
            newEl.attributes().addAll(entry.attributes());
            this.formattingElements.set(pos, newEl);
        } while (pos != size - 1);
    }

    void clearFormattingElementsToLastMarker() {
        while (!this.formattingElements.isEmpty()) {
            Element el = removeLastFormattingElement();
            if (el == null) {
                return;
            }
        }
    }

    void removeFromActiveFormattingElements(Element el) {
        for (int pos = this.formattingElements.size() - 1; pos >= 0; pos--) {
            Element next = this.formattingElements.get(pos);
            if (next == el) {
                this.formattingElements.remove(pos);
                return;
            }
        }
    }

    boolean isInActiveFormattingElements(Element el) {
        return isElementInQueue(this.formattingElements, el);
    }

    Element getActiveFormattingElement(String nodeName) {
        Element next;
        for (int pos = this.formattingElements.size() - 1; pos >= 0 && (next = this.formattingElements.get(pos)) != null; pos--) {
            if (next.normalName().equals(nodeName)) {
                return next;
            }
        }
        return null;
    }

    void replaceActiveFormattingElement(Element out, Element in) {
        replaceInQueue(this.formattingElements, out, in);
    }

    void insertMarkerToFormattingElements() {
        this.formattingElements.add(null);
    }

    void insertInFosterParent(Node in) {
        Element fosterParent;
        Element lastTable = getFromStack("table");
        boolean isLastTableParent = false;
        if (lastTable != null) {
            if (lastTable.parent() != null) {
                fosterParent = lastTable.parent();
                isLastTableParent = true;
            } else {
                fosterParent = aboveOnStack(lastTable);
            }
        } else {
            fosterParent = this.stack.get(0);
        }
        if (isLastTableParent) {
            Validate.notNull(lastTable);
            lastTable.before(in);
        } else {
            fosterParent.appendChild(in);
        }
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + this.currentToken + ", state=" + this.state + ", currentElement=" + currentElement() + '}';
    }

    @Override // org.jsoup.parser.TreeBuilder
    protected boolean isContentForTagData(String normalName) {
        return normalName.equals("script") || normalName.equals("style");
    }
}
