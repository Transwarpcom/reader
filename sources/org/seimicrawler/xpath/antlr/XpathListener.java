package org.seimicrawler.xpath.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.seimicrawler.xpath.antlr.XpathParser;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathListener.class */
public interface XpathListener extends ParseTreeListener {
    void enterMain(XpathParser.MainContext mainContext);

    void exitMain(XpathParser.MainContext mainContext);

    void enterLocationPath(XpathParser.LocationPathContext locationPathContext);

    void exitLocationPath(XpathParser.LocationPathContext locationPathContext);

    void enterAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext absoluteLocationPathNorootContext);

    void exitAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext absoluteLocationPathNorootContext);

    void enterRelativeLocationPath(XpathParser.RelativeLocationPathContext relativeLocationPathContext);

    void exitRelativeLocationPath(XpathParser.RelativeLocationPathContext relativeLocationPathContext);

    void enterStep(XpathParser.StepContext stepContext);

    void exitStep(XpathParser.StepContext stepContext);

    void enterAxisSpecifier(XpathParser.AxisSpecifierContext axisSpecifierContext);

    void exitAxisSpecifier(XpathParser.AxisSpecifierContext axisSpecifierContext);

    void enterNodeTest(XpathParser.NodeTestContext nodeTestContext);

    void exitNodeTest(XpathParser.NodeTestContext nodeTestContext);

    void enterPredicate(XpathParser.PredicateContext predicateContext);

    void exitPredicate(XpathParser.PredicateContext predicateContext);

    void enterAbbreviatedStep(XpathParser.AbbreviatedStepContext abbreviatedStepContext);

    void exitAbbreviatedStep(XpathParser.AbbreviatedStepContext abbreviatedStepContext);

    void enterExpr(XpathParser.ExprContext exprContext);

    void exitExpr(XpathParser.ExprContext exprContext);

    void enterPrimaryExpr(XpathParser.PrimaryExprContext primaryExprContext);

    void exitPrimaryExpr(XpathParser.PrimaryExprContext primaryExprContext);

    void enterFunctionCall(XpathParser.FunctionCallContext functionCallContext);

    void exitFunctionCall(XpathParser.FunctionCallContext functionCallContext);

    void enterUnionExprNoRoot(XpathParser.UnionExprNoRootContext unionExprNoRootContext);

    void exitUnionExprNoRoot(XpathParser.UnionExprNoRootContext unionExprNoRootContext);

    void enterPathExprNoRoot(XpathParser.PathExprNoRootContext pathExprNoRootContext);

    void exitPathExprNoRoot(XpathParser.PathExprNoRootContext pathExprNoRootContext);

    void enterFilterExpr(XpathParser.FilterExprContext filterExprContext);

    void exitFilterExpr(XpathParser.FilterExprContext filterExprContext);

    void enterOrExpr(XpathParser.OrExprContext orExprContext);

    void exitOrExpr(XpathParser.OrExprContext orExprContext);

    void enterAndExpr(XpathParser.AndExprContext andExprContext);

    void exitAndExpr(XpathParser.AndExprContext andExprContext);

    void enterEqualityExpr(XpathParser.EqualityExprContext equalityExprContext);

    void exitEqualityExpr(XpathParser.EqualityExprContext equalityExprContext);

    void enterRelationalExpr(XpathParser.RelationalExprContext relationalExprContext);

    void exitRelationalExpr(XpathParser.RelationalExprContext relationalExprContext);

    void enterAdditiveExpr(XpathParser.AdditiveExprContext additiveExprContext);

    void exitAdditiveExpr(XpathParser.AdditiveExprContext additiveExprContext);

    void enterMultiplicativeExpr(XpathParser.MultiplicativeExprContext multiplicativeExprContext);

    void exitMultiplicativeExpr(XpathParser.MultiplicativeExprContext multiplicativeExprContext);

    void enterUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext unaryExprNoRootContext);

    void exitUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext unaryExprNoRootContext);

    void enterQName(XpathParser.QNameContext qNameContext);

    void exitQName(XpathParser.QNameContext qNameContext);

    void enterFunctionName(XpathParser.FunctionNameContext functionNameContext);

    void exitFunctionName(XpathParser.FunctionNameContext functionNameContext);

    void enterVariableReference(XpathParser.VariableReferenceContext variableReferenceContext);

    void exitVariableReference(XpathParser.VariableReferenceContext variableReferenceContext);

    void enterNameTest(XpathParser.NameTestContext nameTestContext);

    void exitNameTest(XpathParser.NameTestContext nameTestContext);

    void enterNCName(XpathParser.NCNameContext nCNameContext);

    void exitNCName(XpathParser.NCNameContext nCNameContext);
}
