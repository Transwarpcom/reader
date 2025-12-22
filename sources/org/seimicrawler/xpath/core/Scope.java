package org.seimicrawler.xpath.core;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.exception.XpathParserException;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/Scope.class */
public class Scope {
    private Scope parent;
    private boolean isRecursion = false;
    private Elements context = new Elements();

    private Scope(Elements context) {
        this.context.addAll(context);
    }

    private Scope(Element context) {
        this.context.add(context);
    }

    public static Scope create(Elements elements) {
        return new Scope(elements);
    }

    public static Scope create(Element el) {
        return new Scope(el);
    }

    public static Scope create(Scope scope) {
        return new Scope(scope.context()).setParent(scope);
    }

    public Scope setParent(Scope scope) {
        this.parent = scope;
        return this;
    }

    public Scope getParent() {
        return this.parent;
    }

    public void setContext(Elements context) {
        this.context = context;
    }

    public boolean isRecursion() {
        return this.isRecursion;
    }

    void recursion() {
        this.isRecursion = true;
    }

    public void notRecursion() {
        this.isRecursion = false;
    }

    public Elements context() {
        return this.context;
    }

    public Element singleEl() {
        if (this.context.size() == 1) {
            return this.context.first();
        }
        throw new XpathParserException("current context is more than one el,total = " + this.context.size());
    }
}
