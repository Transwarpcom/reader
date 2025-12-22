package org.seimicrawler.xpath.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.seimicrawler.xpath.antlr.XpathParser;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathVisitor.class */
public interface XpathVisitor<T> extends ParseTreeVisitor<T> {
    T visitMain(XpathParser.MainContext mainContext);

    T visitLocationPath(XpathParser.LocationPathContext locationPathContext);

    T visitAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext absoluteLocationPathNorootContext);

    T visitRelativeLocationPath(XpathParser.RelativeLocationPathContext relativeLocationPathContext);

    T visitStep(XpathParser.StepContext stepContext);

    T visitAxisSpecifier(XpathParser.AxisSpecifierContext axisSpecifierContext);

    T visitNodeTest(XpathParser.NodeTestContext nodeTestContext);

    T visitPredicate(XpathParser.PredicateContext predicateContext);

    T visitAbbreviatedStep(XpathParser.AbbreviatedStepContext abbreviatedStepContext);

    T visitExpr(XpathParser.ExprContext exprContext);

    T visitPrimaryExpr(XpathParser.PrimaryExprContext primaryExprContext);

    T visitFunctionCall(XpathParser.FunctionCallContext functionCallContext);

    T visitUnionExprNoRoot(XpathParser.UnionExprNoRootContext unionExprNoRootContext);

    T visitPathExprNoRoot(XpathParser.PathExprNoRootContext pathExprNoRootContext);

    T visitFilterExpr(XpathParser.FilterExprContext filterExprContext);

    T visitOrExpr(XpathParser.OrExprContext orExprContext);

    T visitAndExpr(XpathParser.AndExprContext andExprContext);

    T visitEqualityExpr(XpathParser.EqualityExprContext equalityExprContext);

    T visitRelationalExpr(XpathParser.RelationalExprContext relationalExprContext);

    T visitAdditiveExpr(XpathParser.AdditiveExprContext additiveExprContext);

    T visitMultiplicativeExpr(XpathParser.MultiplicativeExprContext multiplicativeExprContext);

    T visitUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext unaryExprNoRootContext);

    T visitQName(XpathParser.QNameContext qNameContext);

    T visitFunctionName(XpathParser.FunctionNameContext functionNameContext);

    T visitVariableReference(XpathParser.VariableReferenceContext variableReferenceContext);

    T visitNameTest(XpathParser.NameTestContext nameTestContext);

    T visitNCName(XpathParser.NCNameContext nCNameContext);
}
