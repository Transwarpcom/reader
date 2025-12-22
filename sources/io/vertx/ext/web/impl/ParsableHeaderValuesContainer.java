package io.vertx.ext.web.impl;

import io.vertx.ext.web.LanguageHeader;
import io.vertx.ext.web.MIMEHeader;
import io.vertx.ext.web.ParsedHeaderValue;
import io.vertx.ext.web.ParsedHeaderValues;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/ParsableHeaderValuesContainer.class */
public class ParsableHeaderValuesContainer implements ParsedHeaderValues {
    private List<MIMEHeader> accept;
    private List<ParsedHeaderValue> acceptCharset;
    private List<ParsedHeaderValue> acceptEncoding;
    private List<LanguageHeader> acceptLanguage;
    private ParsableMIMEValue contentType;

    public ParsableHeaderValuesContainer(List<MIMEHeader> accept, List<ParsedHeaderValue> acceptCharset, List<ParsedHeaderValue> acceptEncoding, List<LanguageHeader> acceptLanguage, ParsableMIMEValue contentType) {
        this.accept = accept;
        this.acceptCharset = acceptCharset;
        this.acceptEncoding = acceptEncoding;
        this.acceptLanguage = acceptLanguage;
        this.contentType = contentType;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValues
    public List<MIMEHeader> accept() {
        return this.accept;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValues
    public List<ParsedHeaderValue> acceptCharset() {
        return this.acceptCharset;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValues
    public List<ParsedHeaderValue> acceptEncoding() {
        return this.acceptEncoding;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValues
    public List<LanguageHeader> acceptLanguage() {
        return this.acceptLanguage;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValues
    public ParsableMIMEValue contentType() {
        return this.contentType;
    }

    @Override // io.vertx.ext.web.ParsedHeaderValues
    public <T extends ParsedHeaderValue> T findBestUserAcceptedIn(List<T> list, Collection<T> collection) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T t = (T) it.next().findMatchedBy(collection);
            if (t != null) {
                return t;
            }
        }
        return null;
    }
}
