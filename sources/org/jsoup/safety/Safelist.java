package org.jsoup.safety;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import me.ag2s.epublib.epub.NCXDocumentV2;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/safety/Safelist.class */
public class Safelist {
    private Set<TagName> tagNames;
    private Map<TagName, Set<AttributeKey>> attributes;
    private Map<TagName, Map<AttributeKey, AttributeValue>> enforcedAttributes;
    private Map<TagName, Map<AttributeKey, Set<Protocol>>> protocols;
    private boolean preserveRelativeLinks;

    public static Safelist none() {
        return new Safelist();
    }

    public static Safelist simpleText() {
        return new Safelist().addTags(OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "em", "i", "strong", "u");
    }

    public static Safelist basic() {
        return new Safelist().addTags("a", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "blockquote", "br", "cite", "code", "dd", "dl", "dt", "em", "i", NCXDocumentV3.XHTMLTgs.li, NCXDocumentV3.XHTMLTgs.ol, "p", "pre", OperatorName.SAVE, "small", NCXDocumentV3.XHTMLTgs.span, "strike", "strong", "sub", "sup", "u", "ul").addAttributes("a", "href").addAttributes("blockquote", "cite").addAttributes(OperatorName.SAVE, "cite").addProtocols("a", "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addEnforcedAttribute("a", NCXDocumentV3.XHTMLAttributes.rel, "nofollow");
    }

    public static Safelist basicWithImages() {
        return basic().addTags("img").addAttributes("img", "align", "alt", "height", NCXDocumentV2.NCXAttributes.src, "title", "width").addProtocols("img", NCXDocumentV2.NCXAttributes.src, "http", "https");
    }

    public static Safelist relaxed() {
        return new Safelist().addTags("a", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "blockquote", "br", "caption", "cite", "code", "col", "colgroup", "dd", "div", "dl", "dt", "em", NCXDocumentV3.XHTMLTgs.h1, "h2", "h3", "h4", "h5", "h6", "i", "img", NCXDocumentV3.XHTMLTgs.li, NCXDocumentV3.XHTMLTgs.ol, "p", "pre", OperatorName.SAVE, "small", NCXDocumentV3.XHTMLTgs.span, "strike", "strong", "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u", "ul").addAttributes("a", "href", "title").addAttributes("blockquote", "cite").addAttributes("col", NCXDocumentV3.XHTMLTgs.span, "width").addAttributes("colgroup", NCXDocumentV3.XHTMLTgs.span, "width").addAttributes("img", "align", "alt", "height", NCXDocumentV2.NCXAttributes.src, "title", "width").addAttributes(NCXDocumentV3.XHTMLTgs.ol, "start", "type").addAttributes(OperatorName.SAVE, "cite").addAttributes("table", "summary", "width").addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width").addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width").addAttributes("ul", "type").addProtocols("a", "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addProtocols("img", NCXDocumentV2.NCXAttributes.src, "http", "https").addProtocols(OperatorName.SAVE, "cite", "http", "https");
    }

    public Safelist() {
        this.tagNames = new HashSet();
        this.attributes = new HashMap();
        this.enforcedAttributes = new HashMap();
        this.protocols = new HashMap();
        this.preserveRelativeLinks = false;
    }

    public Safelist(Safelist copy) {
        this();
        this.tagNames.addAll(copy.tagNames);
        this.attributes.putAll(copy.attributes);
        this.enforcedAttributes.putAll(copy.enforcedAttributes);
        this.protocols.putAll(copy.protocols);
        this.preserveRelativeLinks = copy.preserveRelativeLinks;
    }

    public Safelist addTags(String... tags) {
        Validate.notNull(tags);
        for (String tagName : tags) {
            Validate.notEmpty(tagName);
            this.tagNames.add(TagName.valueOf(tagName));
        }
        return this;
    }

    public Safelist removeTags(String... tags) {
        Validate.notNull(tags);
        for (String tag : tags) {
            Validate.notEmpty(tag);
            TagName tagName = TagName.valueOf(tag);
            if (this.tagNames.remove(tagName)) {
                this.attributes.remove(tagName);
                this.enforcedAttributes.remove(tagName);
                this.protocols.remove(tagName);
            }
        }
        return this;
    }

    public Safelist addAttributes(String tag, String... attributes) {
        Validate.notEmpty(tag);
        Validate.notNull(attributes);
        Validate.isTrue(attributes.length > 0, "No attribute names supplied.");
        TagName tagName = TagName.valueOf(tag);
        this.tagNames.add(tagName);
        Set<AttributeKey> attributeSet = new HashSet<>();
        for (String key : attributes) {
            Validate.notEmpty(key);
            attributeSet.add(AttributeKey.valueOf(key));
        }
        if (this.attributes.containsKey(tagName)) {
            Set<AttributeKey> currentSet = this.attributes.get(tagName);
            currentSet.addAll(attributeSet);
        } else {
            this.attributes.put(tagName, attributeSet);
        }
        return this;
    }

    public Safelist removeAttributes(String tag, String... attributes) {
        Validate.notEmpty(tag);
        Validate.notNull(attributes);
        Validate.isTrue(attributes.length > 0, "No attribute names supplied.");
        TagName tagName = TagName.valueOf(tag);
        Set<AttributeKey> attributeSet = new HashSet<>();
        for (String key : attributes) {
            Validate.notEmpty(key);
            attributeSet.add(AttributeKey.valueOf(key));
        }
        if (this.tagNames.contains(tagName) && this.attributes.containsKey(tagName)) {
            Set<AttributeKey> currentSet = this.attributes.get(tagName);
            currentSet.removeAll(attributeSet);
            if (currentSet.isEmpty()) {
                this.attributes.remove(tagName);
            }
        }
        if (tag.equals(":all")) {
            for (TagName name : this.attributes.keySet()) {
                Set<AttributeKey> currentSet2 = this.attributes.get(name);
                currentSet2.removeAll(attributeSet);
                if (currentSet2.isEmpty()) {
                    this.attributes.remove(name);
                }
            }
        }
        return this;
    }

    public Safelist addEnforcedAttribute(String tag, String attribute, String value) {
        Validate.notEmpty(tag);
        Validate.notEmpty(attribute);
        Validate.notEmpty(value);
        TagName tagName = TagName.valueOf(tag);
        this.tagNames.add(tagName);
        AttributeKey attrKey = AttributeKey.valueOf(attribute);
        AttributeValue attrVal = AttributeValue.valueOf(value);
        if (this.enforcedAttributes.containsKey(tagName)) {
            this.enforcedAttributes.get(tagName).put(attrKey, attrVal);
        } else {
            Map<AttributeKey, AttributeValue> attrMap = new HashMap<>();
            attrMap.put(attrKey, attrVal);
            this.enforcedAttributes.put(tagName, attrMap);
        }
        return this;
    }

    public Safelist removeEnforcedAttribute(String tag, String attribute) {
        Validate.notEmpty(tag);
        Validate.notEmpty(attribute);
        TagName tagName = TagName.valueOf(tag);
        if (this.tagNames.contains(tagName) && this.enforcedAttributes.containsKey(tagName)) {
            AttributeKey attrKey = AttributeKey.valueOf(attribute);
            Map<AttributeKey, AttributeValue> attrMap = this.enforcedAttributes.get(tagName);
            attrMap.remove(attrKey);
            if (attrMap.isEmpty()) {
                this.enforcedAttributes.remove(tagName);
            }
        }
        return this;
    }

    public Safelist preserveRelativeLinks(boolean preserve) {
        this.preserveRelativeLinks = preserve;
        return this;
    }

    public Safelist addProtocols(String tag, String attribute, String... protocols) {
        Map<AttributeKey, Set<Protocol>> attrMap;
        Set<Protocol> protSet;
        Validate.notEmpty(tag);
        Validate.notEmpty(attribute);
        Validate.notNull(protocols);
        TagName tagName = TagName.valueOf(tag);
        AttributeKey attrKey = AttributeKey.valueOf(attribute);
        if (this.protocols.containsKey(tagName)) {
            attrMap = this.protocols.get(tagName);
        } else {
            attrMap = new HashMap<>();
            this.protocols.put(tagName, attrMap);
        }
        if (attrMap.containsKey(attrKey)) {
            protSet = attrMap.get(attrKey);
        } else {
            protSet = new HashSet<>();
            attrMap.put(attrKey, protSet);
        }
        for (String protocol : protocols) {
            Validate.notEmpty(protocol);
            Protocol prot = Protocol.valueOf(protocol);
            protSet.add(prot);
        }
        return this;
    }

    public Safelist removeProtocols(String tag, String attribute, String... removeProtocols) {
        Validate.notEmpty(tag);
        Validate.notEmpty(attribute);
        Validate.notNull(removeProtocols);
        TagName tagName = TagName.valueOf(tag);
        AttributeKey attr = AttributeKey.valueOf(attribute);
        Validate.isTrue(this.protocols.containsKey(tagName), "Cannot remove a protocol that is not set.");
        Map<AttributeKey, Set<Protocol>> tagProtocols = this.protocols.get(tagName);
        Validate.isTrue(tagProtocols.containsKey(attr), "Cannot remove a protocol that is not set.");
        Set<Protocol> attrProtocols = tagProtocols.get(attr);
        for (String protocol : removeProtocols) {
            Validate.notEmpty(protocol);
            attrProtocols.remove(Protocol.valueOf(protocol));
        }
        if (attrProtocols.isEmpty()) {
            tagProtocols.remove(attr);
            if (tagProtocols.isEmpty()) {
                this.protocols.remove(tagName);
            }
        }
        return this;
    }

    protected boolean isSafeTag(String tag) {
        return this.tagNames.contains(TagName.valueOf(tag));
    }

    protected boolean isSafeAttribute(String tagName, Element el, Attribute attr) {
        TagName tag = TagName.valueOf(tagName);
        AttributeKey key = AttributeKey.valueOf(attr.getKey());
        Set<AttributeKey> okSet = this.attributes.get(tag);
        if (okSet != null && okSet.contains(key)) {
            if (this.protocols.containsKey(tag)) {
                Map<AttributeKey, Set<Protocol>> attrProts = this.protocols.get(tag);
                return !attrProts.containsKey(key) || testValidProtocol(el, attr, attrProts.get(key));
            }
            return true;
        }
        Map<AttributeKey, AttributeValue> enforcedSet = this.enforcedAttributes.get(tag);
        if (enforcedSet != null) {
            Attributes expect = getEnforcedAttributes(tagName);
            String attrKey = attr.getKey();
            if (expect.hasKeyIgnoreCase(attrKey)) {
                return expect.getIgnoreCase(attrKey).equals(attr.getValue());
            }
        }
        return !tagName.equals(":all") && isSafeAttribute(":all", el, attr);
    }

    private boolean testValidProtocol(Element el, Attribute attr, Set<Protocol> protocols) {
        String value = el.absUrl(attr.getKey());
        if (value.length() == 0) {
            value = attr.getValue();
        }
        if (!this.preserveRelativeLinks) {
            attr.setValue(value);
        }
        for (Protocol protocol : protocols) {
            String prot = protocol.toString();
            if (prot.equals("#")) {
                if (isValidAnchor(value)) {
                    return true;
                }
            } else {
                if (Normalizer.lowerCase(value).startsWith(prot + ":")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidAnchor(String value) {
        return value.startsWith("#") && !value.matches(".*\\s.*");
    }

    Attributes getEnforcedAttributes(String tagName) {
        Attributes attrs = new Attributes();
        TagName tag = TagName.valueOf(tagName);
        if (this.enforcedAttributes.containsKey(tag)) {
            Map<AttributeKey, AttributeValue> keyVals = this.enforcedAttributes.get(tag);
            for (Map.Entry<AttributeKey, AttributeValue> entry : keyVals.entrySet()) {
                attrs.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        return attrs;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/safety/Safelist$TagName.class */
    static class TagName extends TypedValue {
        TagName(String value) {
            super(value);
        }

        static TagName valueOf(String value) {
            return new TagName(value);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/safety/Safelist$AttributeKey.class */
    static class AttributeKey extends TypedValue {
        AttributeKey(String value) {
            super(value);
        }

        static AttributeKey valueOf(String value) {
            return new AttributeKey(value);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/safety/Safelist$AttributeValue.class */
    static class AttributeValue extends TypedValue {
        AttributeValue(String value) {
            super(value);
        }

        static AttributeValue valueOf(String value) {
            return new AttributeValue(value);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/safety/Safelist$Protocol.class */
    static class Protocol extends TypedValue {
        Protocol(String value) {
            super(value);
        }

        static Protocol valueOf(String value) {
            return new Protocol(value);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/safety/Safelist$TypedValue.class */
    static abstract class TypedValue {
        private String value;

        TypedValue(String value) {
            Validate.notNull(value);
            this.value = value;
        }

        public int hashCode() {
            int result = (31 * 1) + (this.value == null ? 0 : this.value.hashCode());
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            TypedValue other = (TypedValue) obj;
            if (this.value == null) {
                return other.value == null;
            }
            return this.value.equals(other.value);
        }

        public String toString() {
            return this.value;
        }
    }
}
