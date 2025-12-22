package org.jsoup.parser;

import java.util.HashMap;
import java.util.Map;
import me.ag2s.epublib.epub.NCXDocumentV3;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Tag.class */
public class Tag implements Cloneable {
    private String tagName;
    private String normalName;
    private boolean isBlock = true;
    private boolean formatAsBlock = true;
    private boolean empty = false;
    private boolean selfClosing = false;
    private boolean preserveWhitespace = false;
    private boolean formList = false;
    private boolean formSubmit = false;
    private static final Map<String, Tag> tags = new HashMap();
    private static final String[] blockTags = {"html", "head", NCXDocumentV3.XHTMLTgs.body, "frameset", "script", "noscript", "style", "meta", "link", "title", "frame", "noframes", "section", "nav", "aside", "hgroup", "header", "footer", "p", NCXDocumentV3.XHTMLTgs.h1, "h2", "h3", "h4", "h5", "h6", "ul", NCXDocumentV3.XHTMLTgs.ol, "pre", "div", "blockquote", "hr", "address", "figure", "figcaption", "form", "fieldset", "ins", "del", "dl", "dt", "dd", NCXDocumentV3.XHTMLTgs.li, "table", "caption", "thead", "tfoot", "tbody", "colgroup", "col", "tr", "th", "td", "video", "audio", "canvas", "details", "menu", "plaintext", "template", "article", "main", "svg", "math", "center"};
    private static final String[] inlineTags = {"object", "base", "font", "tt", "i", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "u", "big", "small", "em", "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", "time", "acronym", "mark", "ruby", "rt", "rp", "a", "img", "br", "wbr", BeanDefinitionParserDelegate.MAP_ELEMENT, OperatorName.SAVE, "sub", "sup", "bdo", "iframe", "embed", NCXDocumentV3.XHTMLTgs.span, "input", "select", "textarea", "label", "button", "optgroup", "option", "legend", "datalist", "keygen", "output", "progress", "meter", "area", "param", PackageDocumentBase.DCTags.source, "track", "summary", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", PackageDocumentBase.DCTags.source, "track", "data", "bdi", OperatorName.CLOSE_AND_STROKE};
    private static final String[] emptyTags = {"meta", "link", "base", "frame", "img", "br", "wbr", "embed", "hr", "input", "keygen", "col", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", PackageDocumentBase.DCTags.source, "track"};
    private static final String[] formatAsInlineTags = {"title", "a", "p", NCXDocumentV3.XHTMLTgs.h1, "h2", "h3", "h4", "h5", "h6", "pre", "address", NCXDocumentV3.XHTMLTgs.li, "th", "td", "script", "style", "ins", "del", OperatorName.CLOSE_AND_STROKE};
    private static final String[] preserveWhitespaceTags = {"pre", "plaintext", "title", "textarea"};
    private static final String[] formListedTags = {"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"};
    private static final String[] formSubmitTags = {"input", "keygen", "object", "select", "textarea"};

    static {
        for (String tagName : blockTags) {
            register(new Tag(tagName));
        }
        for (String tagName2 : inlineTags) {
            Tag tag = new Tag(tagName2);
            tag.isBlock = false;
            tag.formatAsBlock = false;
            register(tag);
        }
        for (String tagName3 : emptyTags) {
            Tag tag2 = tags.get(tagName3);
            Validate.notNull(tag2);
            tag2.empty = true;
        }
        for (String tagName4 : formatAsInlineTags) {
            Tag tag3 = tags.get(tagName4);
            Validate.notNull(tag3);
            tag3.formatAsBlock = false;
        }
        for (String tagName5 : preserveWhitespaceTags) {
            Tag tag4 = tags.get(tagName5);
            Validate.notNull(tag4);
            tag4.preserveWhitespace = true;
        }
        for (String tagName6 : formListedTags) {
            Tag tag5 = tags.get(tagName6);
            Validate.notNull(tag5);
            tag5.formList = true;
        }
        for (String tagName7 : formSubmitTags) {
            Tag tag6 = tags.get(tagName7);
            Validate.notNull(tag6);
            tag6.formSubmit = true;
        }
    }

    private Tag(String tagName) {
        this.tagName = tagName;
        this.normalName = Normalizer.lowerCase(tagName);
    }

    public String getName() {
        return this.tagName;
    }

    public String normalName() {
        return this.normalName;
    }

    public static Tag valueOf(String tagName, ParseSettings settings) {
        Validate.notNull(tagName);
        Tag tag = tags.get(tagName);
        if (tag == null) {
            String tagName2 = settings.normalizeTag(tagName);
            Validate.notEmpty(tagName2);
            String normalName = Normalizer.lowerCase(tagName2);
            tag = tags.get(normalName);
            if (tag == null) {
                tag = new Tag(tagName2);
                tag.isBlock = false;
            } else if (settings.preserveTagCase() && !tagName2.equals(normalName)) {
                tag = tag.m5221clone();
                tag.tagName = tagName2;
            }
        }
        return tag;
    }

    public static Tag valueOf(String tagName) {
        return valueOf(tagName, ParseSettings.preserveCase);
    }

    public boolean isBlock() {
        return this.isBlock;
    }

    public boolean formatAsBlock() {
        return this.formatAsBlock;
    }

    public boolean isInline() {
        return !this.isBlock;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isSelfClosing() {
        return this.empty || this.selfClosing;
    }

    public boolean isKnownTag() {
        return tags.containsKey(this.tagName);
    }

    public static boolean isKnownTag(String tagName) {
        return tags.containsKey(tagName);
    }

    public boolean preserveWhitespace() {
        return this.preserveWhitespace;
    }

    public boolean isFormListed() {
        return this.formList;
    }

    public boolean isFormSubmittable() {
        return this.formSubmit;
    }

    Tag setSelfClosing() {
        this.selfClosing = true;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) o;
        return this.tagName.equals(tag.tagName) && this.empty == tag.empty && this.formatAsBlock == tag.formatAsBlock && this.isBlock == tag.isBlock && this.preserveWhitespace == tag.preserveWhitespace && this.selfClosing == tag.selfClosing && this.formList == tag.formList && this.formSubmit == tag.formSubmit;
    }

    public int hashCode() {
        int result = this.tagName.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + (this.isBlock ? 1 : 0))) + (this.formatAsBlock ? 1 : 0))) + (this.empty ? 1 : 0))) + (this.selfClosing ? 1 : 0))) + (this.preserveWhitespace ? 1 : 0))) + (this.formList ? 1 : 0))) + (this.formSubmit ? 1 : 0);
    }

    public String toString() {
        return this.tagName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Tag m5221clone() {
        try {
            return (Tag) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void register(Tag tag) {
        tags.put(tag.tagName, tag);
    }
}
