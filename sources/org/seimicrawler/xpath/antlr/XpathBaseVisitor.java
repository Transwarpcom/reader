package org.seimicrawler.xpath.antlr;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.seimicrawler.xpath.antlr.XpathParser;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathBaseVisitor.class */
public class XpathBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements XpathVisitor<T> {
    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitMain(XpathParser.MainContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitLocationPath(XpathParser.LocationPathContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitRelativeLocationPath(XpathParser.RelativeLocationPathContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitStep(XpathParser.StepContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitAxisSpecifier(XpathParser.AxisSpecifierContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitNodeTest(XpathParser.NodeTestContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitPredicate(XpathParser.PredicateContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitAbbreviatedStep(XpathParser.AbbreviatedStepContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitExpr(XpathParser.ExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitPrimaryExpr(XpathParser.PrimaryExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitFunctionCall(XpathParser.FunctionCallContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitUnionExprNoRoot(XpathParser.UnionExprNoRootContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitPathExprNoRoot(XpathParser.PathExprNoRootContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitFilterExpr(XpathParser.FilterExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitOrExpr(XpathParser.OrExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitAndExpr(XpathParser.AndExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitEqualityExpr(XpathParser.EqualityExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitRelationalExpr(XpathParser.RelationalExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitAdditiveExpr(XpathParser.AdditiveExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitMultiplicativeExpr(XpathParser.MultiplicativeExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitQName(XpathParser.QNameContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitFunctionName(XpathParser.FunctionNameContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitVariableReference(XpathParser.VariableReferenceContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitNameTest(XpathParser.NameTestContext ctx) {
        return visitChildren(ctx);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathVisitor
    public T visitNCName(XpathParser.NCNameContext ctx) {
        return visitChildren(ctx);
    }
}
