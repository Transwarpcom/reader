package org.jsoup.parser;

import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attributes;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/ParseSettings.class */
public class ParseSettings {
    public static final ParseSettings htmlDefault = new ParseSettings(false, false);
    public static final ParseSettings preserveCase = new ParseSettings(true, true);
    private final boolean preserveTagCase;
    private final boolean preserveAttributeCase;

    public boolean preserveTagCase() {
        return this.preserveTagCase;
    }

    public boolean preserveAttributeCase() {
        return this.preserveAttributeCase;
    }

    public ParseSettings(boolean tag, boolean attribute) {
        this.preserveTagCase = tag;
        this.preserveAttributeCase = attribute;
    }

    ParseSettings(ParseSettings copy) {
        this(copy.preserveTagCase, copy.preserveAttributeCase);
    }

    public String normalizeTag(String name) {
        String name2 = name.trim();
        if (!this.preserveTagCase) {
            name2 = Normalizer.lowerCase(name2);
        }
        return name2;
    }

    public String normalizeAttribute(String name) {
        String name2 = name.trim();
        if (!this.preserveAttributeCase) {
            name2 = Normalizer.lowerCase(name2);
        }
        return name2;
    }

    Attributes normalizeAttributes(Attributes attributes) {
        if (attributes != null && !this.preserveAttributeCase) {
            attributes.normalize();
        }
        return attributes;
    }
}
