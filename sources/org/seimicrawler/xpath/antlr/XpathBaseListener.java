package org.seimicrawler.xpath.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.seimicrawler.xpath.antlr.XpathParser;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathBaseListener.class */
public class XpathBaseListener implements XpathListener {
    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterMain(XpathParser.MainContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitMain(XpathParser.MainContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterLocationPath(XpathParser.LocationPathContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitLocationPath(XpathParser.LocationPathContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterRelativeLocationPath(XpathParser.RelativeLocationPathContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitRelativeLocationPath(XpathParser.RelativeLocationPathContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterStep(XpathParser.StepContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitStep(XpathParser.StepContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterAxisSpecifier(XpathParser.AxisSpecifierContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitAxisSpecifier(XpathParser.AxisSpecifierContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterNodeTest(XpathParser.NodeTestContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitNodeTest(XpathParser.NodeTestContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterPredicate(XpathParser.PredicateContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitPredicate(XpathParser.PredicateContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterAbbreviatedStep(XpathParser.AbbreviatedStepContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitAbbreviatedStep(XpathParser.AbbreviatedStepContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterExpr(XpathParser.ExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitExpr(XpathParser.ExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterPrimaryExpr(XpathParser.PrimaryExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitPrimaryExpr(XpathParser.PrimaryExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterFunctionCall(XpathParser.FunctionCallContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitFunctionCall(XpathParser.FunctionCallContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterUnionExprNoRoot(XpathParser.UnionExprNoRootContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitUnionExprNoRoot(XpathParser.UnionExprNoRootContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterPathExprNoRoot(XpathParser.PathExprNoRootContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitPathExprNoRoot(XpathParser.PathExprNoRootContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterFilterExpr(XpathParser.FilterExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitFilterExpr(XpathParser.FilterExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterOrExpr(XpathParser.OrExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitOrExpr(XpathParser.OrExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterAndExpr(XpathParser.AndExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitAndExpr(XpathParser.AndExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterEqualityExpr(XpathParser.EqualityExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitEqualityExpr(XpathParser.EqualityExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterRelationalExpr(XpathParser.RelationalExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitRelationalExpr(XpathParser.RelationalExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterAdditiveExpr(XpathParser.AdditiveExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitAdditiveExpr(XpathParser.AdditiveExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterMultiplicativeExpr(XpathParser.MultiplicativeExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitMultiplicativeExpr(XpathParser.MultiplicativeExprContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterQName(XpathParser.QNameContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitQName(XpathParser.QNameContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterFunctionName(XpathParser.FunctionNameContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitFunctionName(XpathParser.FunctionNameContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterVariableReference(XpathParser.VariableReferenceContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitVariableReference(XpathParser.VariableReferenceContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterNameTest(XpathParser.NameTestContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitNameTest(XpathParser.NameTestContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void enterNCName(XpathParser.NCNameContext ctx) {
    }

    @Override // org.seimicrawler.xpath.antlr.XpathListener
    public void exitNCName(XpathParser.NCNameContext ctx) {
    }

    @Override // org.antlr.v4.runtime.tree.ParseTreeListener
    public void enterEveryRule(ParserRuleContext ctx) {
    }

    @Override // org.antlr.v4.runtime.tree.ParseTreeListener
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    @Override // org.antlr.v4.runtime.tree.ParseTreeListener
    public void visitTerminal(TerminalNode node) {
    }

    @Override // org.antlr.v4.runtime.tree.ParseTreeListener
    public void visitErrorNode(ErrorNode node) {
    }
}
