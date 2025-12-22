package io.vertx.ext.web.impl;

import io.vertx.ext.web.MIMEHeader;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/ParsableMIMEValue.class */
public class ParsableMIMEValue extends ParsableHeaderValue implements MIMEHeader {
    private String component;
    private String subComponent;
    private int orderWeight;

    public ParsableMIMEValue(String headerContent) {
        super(headerContent);
        this.component = null;
        this.subComponent = null;
    }

    @Override // io.vertx.ext.web.MIMEHeader
    public String component() {
        return this.component;
    }

    @Override // io.vertx.ext.web.MIMEHeader
    public String subComponent() {
        return this.subComponent;
    }

    @Override // io.vertx.ext.web.impl.ParsableHeaderValue
    protected boolean isMatchedBy2(ParsableHeaderValue matchTry) {
        ParsableMIMEValue myMatchTry = (ParsableMIMEValue) matchTry;
        ensureHeaderProcessed();
        if (!"*".equals(this.component) && !"*".equals(myMatchTry.component) && !this.component.equals(myMatchTry.component)) {
            return false;
        }
        if (!"*".equals(this.subComponent) && !"*".equals(myMatchTry.subComponent) && !this.subComponent.equals(myMatchTry.subComponent)) {
            return false;
        }
        if ("*".equals(this.component) && "*".equals(this.subComponent) && parameters().size() == 0) {
            return true;
        }
        return super.isMatchedBy2(myMatchTry);
    }

    @Override // io.vertx.ext.web.impl.ParsableHeaderValue
    protected void ensureHeaderProcessed() {
        super.ensureHeaderProcessed();
        if (this.component == null) {
            HeaderParser.parseMIME(this.value, this::setComponent, this::setSubComponent);
            this.orderWeight = "*".equals(this.component) ? 0 : 1;
            this.orderWeight += "*".equals(this.subComponent) ? 0 : 2;
        }
    }

    @Override // io.vertx.ext.web.impl.ParsableHeaderValue
    public ParsableMIMEValue forceParse() {
        ensureHeaderProcessed();
        return this;
    }

    private void setComponent(String component) {
        this.component = "*".equals(component) ? "*" : component;
    }

    private void setSubComponent(String subComponent) {
        this.subComponent = "*".equals(subComponent) ? "*" : subComponent;
    }

    @Override // io.vertx.ext.web.impl.ParsableHeaderValue
    protected int weightedOrderPart2() {
        return this.orderWeight;
    }
}
