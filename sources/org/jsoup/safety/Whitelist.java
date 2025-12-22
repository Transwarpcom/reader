package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/safety/Whitelist.class */
public class Whitelist extends Safelist {
    public Whitelist() {
    }

    public Whitelist(Safelist copy) {
        super(copy);
    }

    public static Whitelist basic() {
        return new Whitelist(Safelist.basic());
    }

    public static Whitelist basicWithImages() {
        return new Whitelist(Safelist.basicWithImages());
    }

    public static Whitelist none() {
        return new Whitelist(Safelist.none());
    }

    public static Whitelist relaxed() {
        return new Whitelist(Safelist.relaxed());
    }

    public static Whitelist simpleText() {
        return new Whitelist(Safelist.simpleText());
    }

    @Override // org.jsoup.safety.Safelist
    public Whitelist addTags(String... tags) {
        super.addTags(tags);
        return this;
    }

    @Override // org.jsoup.safety.Safelist
    public Whitelist removeTags(String... tags) {
        super.removeTags(tags);
        return this;
    }

    @Override // org.jsoup.safety.Safelist
    public Whitelist addAttributes(String tag, String... attributes) {
        super.addAttributes(tag, attributes);
        return this;
    }

    @Override // org.jsoup.safety.Safelist
    public Whitelist removeAttributes(String tag, String... attributes) {
        super.removeAttributes(tag, attributes);
        return this;
    }

    @Override // org.jsoup.safety.Safelist
    public Whitelist addEnforcedAttribute(String tag, String attribute, String value) {
        super.addEnforcedAttribute(tag, attribute, value);
        return this;
    }

    @Override // org.jsoup.safety.Safelist
    public Whitelist removeEnforcedAttribute(String tag, String attribute) {
        super.removeEnforcedAttribute(tag, attribute);
        return this;
    }

    @Override // org.jsoup.safety.Safelist
    public Whitelist preserveRelativeLinks(boolean preserve) {
        super.preserveRelativeLinks(preserve);
        return this;
    }

    @Override // org.jsoup.safety.Safelist
    public Whitelist addProtocols(String tag, String attribute, String... protocols) {
        super.addProtocols(tag, attribute, protocols);
        return this;
    }

    @Override // org.jsoup.safety.Safelist
    public Whitelist removeProtocols(String tag, String attribute, String... removeProtocols) {
        super.removeProtocols(tag, attribute, removeProtocols);
        return this;
    }

    @Override // org.jsoup.safety.Safelist
    protected boolean isSafeTag(String tag) {
        return super.isSafeTag(tag);
    }

    @Override // org.jsoup.safety.Safelist
    protected boolean isSafeAttribute(String tagName, Element el, Attribute attr) {
        return super.isSafeAttribute(tagName, el, attr);
    }

    @Override // org.jsoup.safety.Safelist
    Attributes getEnforcedAttributes(String tagName) {
        return super.getEnforcedAttributes(tagName);
    }
}
