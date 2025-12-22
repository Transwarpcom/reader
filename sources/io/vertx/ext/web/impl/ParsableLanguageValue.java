package io.vertx.ext.web.impl;

import io.vertx.ext.web.LanguageHeader;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/ParsableLanguageValue.class */
public class ParsableLanguageValue extends ParsableHeaderValue implements LanguageHeader {
    private List<String> parsedValues;
    private boolean processed;

    public ParsableLanguageValue(String headerContent) {
        super(headerContent);
        this.processed = false;
        this.parsedValues = null;
    }

    @Override // io.vertx.ext.web.LanguageHeader
    public String tag() {
        return subtag(0);
    }

    @Override // io.vertx.ext.web.Locale
    public String language() {
        String value = tag();
        if (value == null) {
            return null;
        }
        return value.toLowerCase();
    }

    @Override // io.vertx.ext.web.LanguageHeader
    public String subtag() {
        return subtag(1);
    }

    @Override // io.vertx.ext.web.Locale
    public String country() {
        String value = subtag(1);
        if (value == null) {
            return null;
        }
        return value.toUpperCase();
    }

    @Override // io.vertx.ext.web.Locale
    public String variant() {
        String value = subtag(2);
        if (value == null) {
            return null;
        }
        return value.toUpperCase();
    }

    @Override // io.vertx.ext.web.LanguageHeader
    public String subtag(int level) {
        ensureHeaderProcessed();
        if (level < this.parsedValues.size()) {
            return this.parsedValues.get(level);
        }
        return null;
    }

    @Override // io.vertx.ext.web.LanguageHeader
    public int subtagCount() {
        ensureHeaderProcessed();
        if (this.parsedValues != null) {
            return this.parsedValues.size();
        }
        return 0;
    }

    @Override // io.vertx.ext.web.impl.ParsableHeaderValue
    protected boolean isMatchedBy2(ParsableHeaderValue matchTry) {
        ParsableLanguageValue myMatchTry = (ParsableLanguageValue) matchTry;
        ensureHeaderProcessed();
        for (int i = 0; i < myMatchTry.parsedValues.size(); i++) {
            String match = myMatchTry.parsedValues.get(i);
            String against = this.parsedValues.get(i);
            if (!"*".equals(match) && !match.equalsIgnoreCase(against)) {
                return false;
            }
        }
        return super.isMatchedBy2(myMatchTry);
    }

    @Override // io.vertx.ext.web.impl.ParsableHeaderValue
    protected void ensureHeaderProcessed() {
        if (!this.processed) {
            this.processed = true;
            super.ensureHeaderProcessed();
            this.parsedValues = HeaderParser.parseLanguageValue(this.value);
        }
    }
}
