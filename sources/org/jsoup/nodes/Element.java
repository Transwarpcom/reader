package org.jsoup.nodes;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.annotation.Nullable;
import org.jsoup.helper.ChangeNotifyingArrayList;
import org.jsoup.helper.Validate;
import org.jsoup.internal.NonnullByDefault;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Tag;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.QueryParser;
import org.jsoup.select.Selector;

@NonnullByDefault
/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Element.class */
public class Element extends Node {
    private static final List<Element> EmptyChildren = Collections.emptyList();
    private static final Pattern ClassSplit = Pattern.compile("\\s+");
    private static final String BaseUriKey = Attributes.internalKey("baseUri");
    private Tag tag;

    @Nullable
    private WeakReference<List<Element>> shadowChildrenRef;
    List<Node> childNodes;

    @Nullable
    private Attributes attributes;

    public Element(String tag) {
        this(Tag.valueOf(tag), "", null);
    }

    public Element(Tag tag, @Nullable String baseUri, @Nullable Attributes attributes) {
        Validate.notNull(tag);
        this.childNodes = EmptyNodes;
        this.attributes = attributes;
        this.tag = tag;
        if (baseUri != null) {
            setBaseUri(baseUri);
        }
    }

    public Element(Tag tag, String baseUri) {
        this(tag, baseUri, null);
    }

    protected boolean hasChildNodes() {
        return this.childNodes != EmptyNodes;
    }

    @Override // org.jsoup.nodes.Node
    protected List<Node> ensureChildNodes() {
        if (this.childNodes == EmptyNodes) {
            this.childNodes = new NodeList(this, 4);
        }
        return this.childNodes;
    }

    @Override // org.jsoup.nodes.Node
    protected boolean hasAttributes() {
        return this.attributes != null;
    }

    @Override // org.jsoup.nodes.Node
    public Attributes attributes() {
        if (this.attributes == null) {
            this.attributes = new Attributes();
        }
        return this.attributes;
    }

    @Override // org.jsoup.nodes.Node
    public String baseUri() {
        return searchUpForAttribute(this, BaseUriKey);
    }

    private static String searchUpForAttribute(Element start, String key) {
        Element elementParent = start;
        while (true) {
            Element el = elementParent;
            if (el != null) {
                if (el.attributes != null && el.attributes.hasKey(key)) {
                    return el.attributes.get(key);
                }
                elementParent = el.parent();
            } else {
                return "";
            }
        }
    }

    @Override // org.jsoup.nodes.Node
    protected void doSetBaseUri(String baseUri) {
        attributes().put(BaseUriKey, baseUri);
    }

    @Override // org.jsoup.nodes.Node
    public int childNodeSize() {
        return this.childNodes.size();
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return this.tag.getName();
    }

    public String tagName() {
        return this.tag.getName();
    }

    public String normalName() {
        return this.tag.normalName();
    }

    public Element tagName(String tagName) {
        Validate.notEmpty(tagName, "Tag name must not be empty.");
        this.tag = Tag.valueOf(tagName, NodeUtils.parser(this).settings());
        return this;
    }

    public Tag tag() {
        return this.tag;
    }

    public boolean isBlock() {
        return this.tag.isBlock();
    }

    public String id() {
        return this.attributes != null ? this.attributes.getIgnoreCase("id") : "";
    }

    public Element id(String id) {
        Validate.notNull(id);
        attr("id", id);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element attr(String attributeKey, String attributeValue) {
        super.attr(attributeKey, attributeValue);
        return this;
    }

    public Element attr(String attributeKey, boolean attributeValue) {
        attributes().put(attributeKey, attributeValue);
        return this;
    }

    public Map<String, String> dataset() {
        return attributes().dataset();
    }

    @Override // org.jsoup.nodes.Node
    public final Element parent() {
        return (Element) this.parentNode;
    }

    public Elements parents() {
        Elements parents = new Elements();
        accumulateParents(this, parents);
        return parents;
    }

    private static void accumulateParents(Element el, Elements parents) {
        Element parent = el.parent();
        if (parent != null && !parent.tagName().equals("#root")) {
            parents.add(parent);
            accumulateParents(parent, parents);
        }
    }

    public Element child(int index) {
        return childElementsList().get(index);
    }

    public int childrenSize() {
        return childElementsList().size();
    }

    public Elements children() {
        return new Elements(childElementsList());
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.util.List<org.jsoup.nodes.Element> childElementsList() {
        /*
            r5 = this;
            r0 = r5
            int r0 = r0.childNodeSize()
            if (r0 != 0) goto Lb
            java.util.List<org.jsoup.nodes.Element> r0 = org.jsoup.nodes.Element.EmptyChildren
            return r0
        Lb:
            r0 = r5
            java.lang.ref.WeakReference<java.util.List<org.jsoup.nodes.Element>> r0 = r0.shadowChildrenRef
            if (r0 == 0) goto L21
            r0 = r5
            java.lang.ref.WeakReference<java.util.List<org.jsoup.nodes.Element>> r0 = r0.shadowChildrenRef
            java.lang.Object r0 = r0.get()
            java.util.List r0 = (java.util.List) r0
            r1 = r0
            r6 = r1
            if (r0 != 0) goto L70
        L21:
            r0 = r5
            java.util.List<org.jsoup.nodes.Node> r0 = r0.childNodes
            int r0 = r0.size()
            r7 = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r2 = r7
            r1.<init>(r2)
            r6 = r0
            r0 = 0
            r8 = r0
        L36:
            r0 = r8
            r1 = r7
            if (r0 >= r1) goto L64
            r0 = r5
            java.util.List<org.jsoup.nodes.Node> r0 = r0.childNodes
            r1 = r8
            java.lang.Object r0 = r0.get(r1)
            org.jsoup.nodes.Node r0 = (org.jsoup.nodes.Node) r0
            r9 = r0
            r0 = r9
            boolean r0 = r0 instanceof org.jsoup.nodes.Element
            if (r0 == 0) goto L5e
            r0 = r6
            r1 = r9
            org.jsoup.nodes.Element r1 = (org.jsoup.nodes.Element) r1
            boolean r0 = r0.add(r1)
        L5e:
            int r8 = r8 + 1
            goto L36
        L64:
            r0 = r5
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference
            r2 = r1
            r3 = r6
            r2.<init>(r3)
            r0.shadowChildrenRef = r1
        L70:
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.nodes.Element.childElementsList():java.util.List");
    }

    @Override // org.jsoup.nodes.Node
    void nodelistChanged() {
        super.nodelistChanged();
        this.shadowChildrenRef = null;
    }

    public List<TextNode> textNodes() {
        List<TextNode> textNodes = new ArrayList<>();
        for (Node node : this.childNodes) {
            if (node instanceof TextNode) {
                textNodes.add((TextNode) node);
            }
        }
        return Collections.unmodifiableList(textNodes);
    }

    public List<DataNode> dataNodes() {
        List<DataNode> dataNodes = new ArrayList<>();
        for (Node node : this.childNodes) {
            if (node instanceof DataNode) {
                dataNodes.add((DataNode) node);
            }
        }
        return Collections.unmodifiableList(dataNodes);
    }

    public Elements select(String cssQuery) {
        return Selector.select(cssQuery, this);
    }

    public Elements select(Evaluator evaluator) {
        return Selector.select(evaluator, this);
    }

    @Nullable
    public Element selectFirst(String cssQuery) {
        return Selector.selectFirst(cssQuery, this);
    }

    @Nullable
    public Element selectFirst(Evaluator evaluator) {
        return Collector.findFirst(evaluator, this);
    }

    public boolean is(String cssQuery) {
        return is(QueryParser.parse(cssQuery));
    }

    public boolean is(Evaluator evaluator) {
        return evaluator.matches(root(), this);
    }

    @Nullable
    public Element closest(String cssQuery) {
        return closest(QueryParser.parse(cssQuery));
    }

    @Nullable
    public Element closest(Evaluator evaluator) {
        Validate.notNull(evaluator);
        Element el = this;
        Element root = root();
        while (!evaluator.matches(root, el)) {
            el = el.parent();
            if (el == null) {
                return null;
            }
        }
        return el;
    }

    public Element appendChild(Node child) {
        Validate.notNull(child);
        reparentChild(child);
        ensureChildNodes();
        this.childNodes.add(child);
        child.setSiblingIndex(this.childNodes.size() - 1);
        return this;
    }

    public Element appendChildren(Collection<? extends Node> children) {
        insertChildren(-1, children);
        return this;
    }

    public Element appendTo(Element parent) {
        Validate.notNull(parent);
        parent.appendChild(this);
        return this;
    }

    public Element prependChild(Node child) {
        Validate.notNull(child);
        addChildren(0, child);
        return this;
    }

    public Element prependChildren(Collection<? extends Node> children) {
        insertChildren(0, children);
        return this;
    }

    public Element insertChildren(int index, Collection<? extends Node> children) {
        Validate.notNull(children, "Children collection to be inserted must not be null.");
        int currentSize = childNodeSize();
        if (index < 0) {
            index += currentSize + 1;
        }
        Validate.isTrue(index >= 0 && index <= currentSize, "Insert position out of bounds.");
        ArrayList<Node> nodes = new ArrayList<>(children);
        Node[] nodeArray = (Node[]) nodes.toArray(new Node[0]);
        addChildren(index, nodeArray);
        return this;
    }

    public Element insertChildren(int index, Node... children) {
        Validate.notNull(children, "Children collection to be inserted must not be null.");
        int currentSize = childNodeSize();
        if (index < 0) {
            index += currentSize + 1;
        }
        Validate.isTrue(index >= 0 && index <= currentSize, "Insert position out of bounds.");
        addChildren(index, children);
        return this;
    }

    public Element appendElement(String tagName) {
        Element child = new Element(Tag.valueOf(tagName, NodeUtils.parser(this).settings()), baseUri());
        appendChild(child);
        return child;
    }

    public Element prependElement(String tagName) {
        Element child = new Element(Tag.valueOf(tagName, NodeUtils.parser(this).settings()), baseUri());
        prependChild(child);
        return child;
    }

    public Element appendText(String text) {
        Validate.notNull(text);
        TextNode node = new TextNode(text);
        appendChild(node);
        return this;
    }

    public Element prependText(String text) {
        Validate.notNull(text);
        TextNode node = new TextNode(text);
        prependChild(node);
        return this;
    }

    public Element append(String html) {
        Validate.notNull(html);
        List<Node> nodes = NodeUtils.parser(this).parseFragmentInput(html, this, baseUri());
        addChildren((Node[]) nodes.toArray(new Node[0]));
        return this;
    }

    public Element prepend(String html) {
        Validate.notNull(html);
        List<Node> nodes = NodeUtils.parser(this).parseFragmentInput(html, this, baseUri());
        addChildren(0, (Node[]) nodes.toArray(new Node[0]));
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element before(String html) {
        return (Element) super.before(html);
    }

    @Override // org.jsoup.nodes.Node
    public Element before(Node node) {
        return (Element) super.before(node);
    }

    @Override // org.jsoup.nodes.Node
    public Element after(String html) {
        return (Element) super.after(html);
    }

    @Override // org.jsoup.nodes.Node
    public Element after(Node node) {
        return (Element) super.after(node);
    }

    @Override // org.jsoup.nodes.Node
    public Element empty() {
        this.childNodes.clear();
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element wrap(String html) {
        return (Element) super.wrap(html);
    }

    public String cssSelector() {
        if (id().length() > 0) {
            String idSel = "#" + id();
            Document doc = ownerDocument();
            if (doc != null) {
                Elements els = doc.select(idSel);
                if (els.size() == 1 && els.get(0) == this) {
                    return idSel;
                }
            } else {
                return idSel;
            }
        }
        String tagName = tagName().replace(':', '|');
        StringBuilder selector = new StringBuilder(tagName);
        String classes = StringUtil.join(classNames(), ".");
        if (classes.length() > 0) {
            selector.append('.').append(classes);
        }
        if (parent() == null || (parent() instanceof Document)) {
            return selector.toString();
        }
        selector.insert(0, " > ");
        if (parent().select(selector.toString()).size() > 1) {
            selector.append(String.format(":nth-child(%d)", Integer.valueOf(elementSiblingIndex() + 1)));
        }
        return parent().cssSelector() + selector.toString();
    }

    public Elements siblingElements() {
        if (this.parentNode == null) {
            return new Elements(0);
        }
        List<Element> elements = parent().childElementsList();
        Elements siblings = new Elements(elements.size() - 1);
        for (Element el : elements) {
            if (el != this) {
                siblings.add(el);
            }
        }
        return siblings;
    }

    @Nullable
    public Element nextElementSibling() {
        if (this.parentNode == null) {
            return null;
        }
        List<Element> siblings = parent().childElementsList();
        int index = indexInList(this, siblings);
        if (siblings.size() > index + 1) {
            return siblings.get(index + 1);
        }
        return null;
    }

    public Elements nextElementSiblings() {
        return nextElementSiblings(true);
    }

    @Nullable
    public Element previousElementSibling() {
        List<Element> siblings;
        int index;
        if (this.parentNode != null && (index = indexInList(this, (siblings = parent().childElementsList()))) > 0) {
            return siblings.get(index - 1);
        }
        return null;
    }

    public Elements previousElementSiblings() {
        return nextElementSiblings(false);
    }

    private Elements nextElementSiblings(boolean next) {
        Elements els = new Elements();
        if (this.parentNode == null) {
            return els;
        }
        els.add(this);
        return next ? els.nextAll() : els.prevAll();
    }

    public Element firstElementSibling() {
        if (parent() != null) {
            List<Element> siblings = parent().childElementsList();
            return siblings.size() > 1 ? siblings.get(0) : this;
        }
        return this;
    }

    public int elementSiblingIndex() {
        if (parent() == null) {
            return 0;
        }
        return indexInList(this, parent().childElementsList());
    }

    public Element lastElementSibling() {
        if (parent() != null) {
            List<Element> siblings = parent().childElementsList();
            return siblings.size() > 1 ? siblings.get(siblings.size() - 1) : this;
        }
        return this;
    }

    private static <E extends Element> int indexInList(Element search, List<E> elements) {
        int size = elements.size();
        for (int i = 0; i < size; i++) {
            if (elements.get(i) == search) {
                return i;
            }
        }
        return 0;
    }

    public Elements getElementsByTag(String tagName) {
        Validate.notEmpty(tagName);
        return Collector.collect(new Evaluator.Tag(Normalizer.normalize(tagName)), this);
    }

    @Nullable
    public Element getElementById(String id) {
        Validate.notEmpty(id);
        Elements elements = Collector.collect(new Evaluator.Id(id), this);
        if (elements.size() > 0) {
            return elements.get(0);
        }
        return null;
    }

    public Elements getElementsByClass(String className) {
        Validate.notEmpty(className);
        return Collector.collect(new Evaluator.Class(className), this);
    }

    public Elements getElementsByAttribute(String key) {
        Validate.notEmpty(key);
        return Collector.collect(new Evaluator.Attribute(key.trim()), this);
    }

    public Elements getElementsByAttributeStarting(String keyPrefix) {
        Validate.notEmpty(keyPrefix);
        return Collector.collect(new Evaluator.AttributeStarting(keyPrefix.trim()), this);
    }

    public Elements getElementsByAttributeValue(String key, String value) {
        return Collector.collect(new Evaluator.AttributeWithValue(key, value), this);
    }

    public Elements getElementsByAttributeValueNot(String key, String value) {
        return Collector.collect(new Evaluator.AttributeWithValueNot(key, value), this);
    }

    public Elements getElementsByAttributeValueStarting(String key, String valuePrefix) {
        return Collector.collect(new Evaluator.AttributeWithValueStarting(key, valuePrefix), this);
    }

    public Elements getElementsByAttributeValueEnding(String key, String valueSuffix) {
        return Collector.collect(new Evaluator.AttributeWithValueEnding(key, valueSuffix), this);
    }

    public Elements getElementsByAttributeValueContaining(String key, String match) {
        return Collector.collect(new Evaluator.AttributeWithValueContaining(key, match), this);
    }

    public Elements getElementsByAttributeValueMatching(String key, Pattern pattern) {
        return Collector.collect(new Evaluator.AttributeWithValueMatching(key, pattern), this);
    }

    public Elements getElementsByAttributeValueMatching(String key, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            return getElementsByAttributeValueMatching(key, pattern);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + regex, e);
        }
    }

    public Elements getElementsByIndexLessThan(int index) {
        return Collector.collect(new Evaluator.IndexLessThan(index), this);
    }

    public Elements getElementsByIndexGreaterThan(int index) {
        return Collector.collect(new Evaluator.IndexGreaterThan(index), this);
    }

    public Elements getElementsByIndexEquals(int index) {
        return Collector.collect(new Evaluator.IndexEquals(index), this);
    }

    public Elements getElementsContainingText(String searchText) {
        return Collector.collect(new Evaluator.ContainsText(searchText), this);
    }

    public Elements getElementsContainingOwnText(String searchText) {
        return Collector.collect(new Evaluator.ContainsOwnText(searchText), this);
    }

    public Elements getElementsMatchingText(Pattern pattern) {
        return Collector.collect(new Evaluator.Matches(pattern), this);
    }

    public Elements getElementsMatchingText(String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            return getElementsMatchingText(pattern);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + regex, e);
        }
    }

    public Elements getElementsMatchingOwnText(Pattern pattern) {
        return Collector.collect(new Evaluator.MatchesOwn(pattern), this);
    }

    public Elements getElementsMatchingOwnText(String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            return getElementsMatchingOwnText(pattern);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + regex, e);
        }
    }

    public Elements getAllElements() {
        return Collector.collect(new Evaluator.AllElements(), this);
    }

    public String text() {
        final StringBuilder accum = StringUtil.borrowBuilder();
        NodeTraversor.traverse(new NodeVisitor() { // from class: org.jsoup.nodes.Element.1
            @Override // org.jsoup.select.NodeVisitor
            public void head(Node node, int depth) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    Element.appendNormalisedText(accum, textNode);
                } else if (node instanceof Element) {
                    Element element = (Element) node;
                    if (accum.length() > 0) {
                        if ((element.isBlock() || element.tag.getName().equals("br")) && !TextNode.lastCharIsWhitespace(accum)) {
                            accum.append(' ');
                        }
                    }
                }
            }

            @Override // org.jsoup.select.NodeVisitor
            public void tail(Node node, int depth) {
                if (node instanceof Element) {
                    Element element = (Element) node;
                    if (element.isBlock() && (node.nextSibling() instanceof TextNode) && !TextNode.lastCharIsWhitespace(accum)) {
                        accum.append(' ');
                    }
                }
            }
        }, this);
        return StringUtil.releaseBuilder(accum).trim();
    }

    public String wholeText() {
        final StringBuilder accum = StringUtil.borrowBuilder();
        NodeTraversor.traverse(new NodeVisitor() { // from class: org.jsoup.nodes.Element.2
            @Override // org.jsoup.select.NodeVisitor
            public void head(Node node, int depth) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    accum.append(textNode.getWholeText());
                }
            }

            @Override // org.jsoup.select.NodeVisitor
            public void tail(Node node, int depth) {
            }
        }, this);
        return StringUtil.releaseBuilder(accum);
    }

    public String ownText() {
        StringBuilder sb = StringUtil.borrowBuilder();
        ownText(sb);
        return StringUtil.releaseBuilder(sb).trim();
    }

    private void ownText(StringBuilder accum) {
        for (Node child : this.childNodes) {
            if (child instanceof TextNode) {
                TextNode textNode = (TextNode) child;
                appendNormalisedText(accum, textNode);
            } else if (child instanceof Element) {
                appendWhitespaceIfBr((Element) child, accum);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void appendNormalisedText(StringBuilder accum, TextNode textNode) {
        String text = textNode.getWholeText();
        if (preserveWhitespace(textNode.parentNode) || (textNode instanceof CDataNode)) {
            accum.append(text);
        } else {
            StringUtil.appendNormalisedWhitespace(accum, text, TextNode.lastCharIsWhitespace(accum));
        }
    }

    private static void appendWhitespaceIfBr(Element element, StringBuilder accum) {
        if (element.tag.getName().equals("br") && !TextNode.lastCharIsWhitespace(accum)) {
            accum.append(" ");
        }
    }

    static boolean preserveWhitespace(@Nullable Node node) {
        if (node instanceof Element) {
            Element el = (Element) node;
            int i = 0;
            while (!el.tag.preserveWhitespace()) {
                el = el.parent();
                i++;
                if (i >= 6 || el == null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Element text(String text) {
        Validate.notNull(text);
        empty();
        Document owner = ownerDocument();
        if (owner != null && owner.parser().isContentForTagData(normalName())) {
            appendChild(new DataNode(text));
        } else {
            appendChild(new TextNode(text));
        }
        return this;
    }

    public boolean hasText() {
        for (Node child : this.childNodes) {
            if (child instanceof TextNode) {
                TextNode textNode = (TextNode) child;
                if (!textNode.isBlank()) {
                    return true;
                }
            } else if (child instanceof Element) {
                Element el = (Element) child;
                if (el.hasText()) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public String data() {
        StringBuilder sb = StringUtil.borrowBuilder();
        for (Node childNode : this.childNodes) {
            if (childNode instanceof DataNode) {
                DataNode data = (DataNode) childNode;
                sb.append(data.getWholeData());
            } else if (childNode instanceof Comment) {
                Comment comment = (Comment) childNode;
                sb.append(comment.getData());
            } else if (childNode instanceof Element) {
                Element element = (Element) childNode;
                String elementData = element.data();
                sb.append(elementData);
            } else if (childNode instanceof CDataNode) {
                CDataNode cDataNode = (CDataNode) childNode;
                sb.append(cDataNode.getWholeText());
            }
        }
        return StringUtil.releaseBuilder(sb);
    }

    public String className() {
        return attr("class").trim();
    }

    public Set<String> classNames() {
        String[] names = ClassSplit.split(className());
        Set<String> classNames = new LinkedHashSet<>(Arrays.asList(names));
        classNames.remove("");
        return classNames;
    }

    public Element classNames(Set<String> classNames) {
        Validate.notNull(classNames);
        if (classNames.isEmpty()) {
            attributes().remove("class");
        } else {
            attributes().put("class", StringUtil.join(classNames, " "));
        }
        return this;
    }

    public boolean hasClass(String className) {
        if (this.attributes == null) {
            return false;
        }
        String classAttr = this.attributes.getIgnoreCase("class");
        int len = classAttr.length();
        int wantLen = className.length();
        if (len == 0 || len < wantLen) {
            return false;
        }
        if (len == wantLen) {
            return className.equalsIgnoreCase(classAttr);
        }
        boolean inClass = false;
        int start = 0;
        for (int i = 0; i < len; i++) {
            if (Character.isWhitespace(classAttr.charAt(i))) {
                if (!inClass) {
                    continue;
                } else {
                    if (i - start == wantLen && classAttr.regionMatches(true, start, className, 0, wantLen)) {
                        return true;
                    }
                    inClass = false;
                }
            } else if (!inClass) {
                inClass = true;
                start = i;
            }
        }
        if (inClass && len - start == wantLen) {
            return classAttr.regionMatches(true, start, className, 0, wantLen);
        }
        return false;
    }

    public Element addClass(String className) {
        Validate.notNull(className);
        Set<String> classes = classNames();
        classes.add(className);
        classNames(classes);
        return this;
    }

    public Element removeClass(String className) {
        Validate.notNull(className);
        Set<String> classes = classNames();
        classes.remove(className);
        classNames(classes);
        return this;
    }

    public Element toggleClass(String className) {
        Validate.notNull(className);
        Set<String> classes = classNames();
        if (classes.contains(className)) {
            classes.remove(className);
        } else {
            classes.add(className);
        }
        classNames(classes);
        return this;
    }

    public String val() {
        if (normalName().equals("textarea")) {
            return text();
        }
        return attr("value");
    }

    public Element val(String value) {
        if (normalName().equals("textarea")) {
            text(value);
        } else {
            attr("value", value);
        }
        return this;
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        if (out.prettyPrint() && isFormatAsBlock(out) && !isInlineable(out) && (!(accum instanceof StringBuilder) || ((StringBuilder) accum).length() > 0)) {
            indent(accum, depth, out);
        }
        accum.append('<').append(tagName());
        if (this.attributes != null) {
            this.attributes.html(accum, out);
        }
        if (this.childNodes.isEmpty() && this.tag.isSelfClosing()) {
            if (out.syntax() == Document.OutputSettings.Syntax.html && this.tag.isEmpty()) {
                accum.append('>');
                return;
            } else {
                accum.append(" />");
                return;
            }
        }
        accum.append('>');
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        if (!this.childNodes.isEmpty() || !this.tag.isSelfClosing()) {
            if (out.prettyPrint() && !this.childNodes.isEmpty() && (this.tag.formatAsBlock() || (out.outline() && (this.childNodes.size() > 1 || (this.childNodes.size() == 1 && !(this.childNodes.get(0) instanceof TextNode)))))) {
                indent(accum, depth, out);
            }
            accum.append("</").append(tagName()).append('>');
        }
    }

    public String html() {
        StringBuilder accum = StringUtil.borrowBuilder();
        html((Element) accum);
        String html = StringUtil.releaseBuilder(accum);
        return NodeUtils.outputSettings(this).prettyPrint() ? html.trim() : html;
    }

    @Override // org.jsoup.nodes.Node
    public <T extends Appendable> T html(T appendable) {
        int size = this.childNodes.size();
        for (int i = 0; i < size; i++) {
            this.childNodes.get(i).outerHtml(appendable);
        }
        return appendable;
    }

    public Element html(String html) {
        empty();
        append(html);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Element mo5203clone() {
        return (Element) super.mo5203clone();
    }

    @Override // org.jsoup.nodes.Node
    public Element shallowClone() {
        return new Element(this.tag, baseUri(), this.attributes == null ? null : this.attributes.m5202clone());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.nodes.Node
    public Element doClone(@Nullable Node parent) {
        Element clone = (Element) super.doClone(parent);
        clone.attributes = this.attributes != null ? this.attributes.m5202clone() : null;
        clone.childNodes = new NodeList(clone, this.childNodes.size());
        clone.childNodes.addAll(this.childNodes);
        return clone;
    }

    @Override // org.jsoup.nodes.Node
    public Element clearAttributes() {
        if (this.attributes != null) {
            super.clearAttributes();
            this.attributes = null;
        }
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element removeAttr(String attributeKey) {
        return (Element) super.removeAttr(attributeKey);
    }

    @Override // org.jsoup.nodes.Node
    public Element root() {
        return (Element) super.root();
    }

    @Override // org.jsoup.nodes.Node
    public Element traverse(NodeVisitor nodeVisitor) {
        return (Element) super.traverse(nodeVisitor);
    }

    @Override // org.jsoup.nodes.Node
    public Element filter(NodeFilter nodeFilter) {
        return (Element) super.filter(nodeFilter);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Element$NodeList.class */
    private static final class NodeList extends ChangeNotifyingArrayList<Node> {
        private final Element owner;

        NodeList(Element owner, int initialCapacity) {
            super(initialCapacity);
            this.owner = owner;
        }

        @Override // org.jsoup.helper.ChangeNotifyingArrayList
        public void onContentsChanged() {
            this.owner.nodelistChanged();
        }
    }

    private boolean isFormatAsBlock(Document.OutputSettings out) {
        return this.tag.formatAsBlock() || (parent() != null && parent().tag().formatAsBlock()) || out.outline();
    }

    private boolean isInlineable(Document.OutputSettings out) {
        return (!tag().isInline() || tag().isEmpty() || (parent() != null && !parent().isBlock()) || previousSibling() == null || out.outline()) ? false : true;
    }
}
