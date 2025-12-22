package org.seimicrawler.xpath.core;

import cn.hutool.core.text.StrPool;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.antlr.XpathBaseVisitor;
import org.seimicrawler.xpath.antlr.XpathParser;
import org.seimicrawler.xpath.exception.XpathMergeValueException;
import org.seimicrawler.xpath.exception.XpathParserException;
import org.seimicrawler.xpath.util.CommonUtil;
import org.seimicrawler.xpath.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/XpathProcessor.class */
public class XpathProcessor extends XpathBaseVisitor<XValue> {
    private Logger logger = LoggerFactory.getLogger((Class<?>) XpathProcessor.class);
    private Stack<Scope> scopeStack = new Stack<>();
    private Scope rootScope;

    public XpathProcessor(Elements root) {
        this.rootScope = Scope.create(root);
        this.scopeStack.push(Scope.create(root).setParent(this.rootScope));
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitMain(XpathParser.MainContext ctx) {
        return visit(ctx.expr());
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitLocationPath(XpathParser.LocationPathContext ctx) {
        if (ctx.relativeLocationPath() != null && !ctx.relativeLocationPath().isEmpty()) {
            return visit(ctx.relativeLocationPath());
        }
        return visit(ctx.absoluteLocationPathNoroot());
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext ctx) {
        if (Objects.equals(ctx.op.getText(), "//")) {
            currentScope().recursion();
        }
        return visit(ctx.relativeLocationPath());
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitRelativeLocationPath(XpathParser.RelativeLocationPathContext ctx) {
        XValue finalVal = null;
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree step = ctx.getChild(i);
            if (step instanceof XpathParser.StepContext) {
                finalVal = visit(step);
                if (finalVal.isElements()) {
                    updateCurrentContext(finalVal.asElements());
                }
            } else if ("//".equals(step.getText())) {
                currentScope().recursion();
            } else {
                currentScope().notRecursion();
            }
        }
        return finalVal;
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitStep(XpathParser.StepContext ctx) {
        XValue axis;
        if (ctx.abbreviatedStep() != null && !ctx.abbreviatedStep().isEmpty()) {
            return visit(ctx.abbreviatedStep());
        }
        boolean filterByAttr = false;
        boolean isAxisOk = false;
        if (ctx.axisSpecifier() != null && !ctx.axisSpecifier().isEmpty() && (axis = visit(ctx.axisSpecifier())) != null) {
            isAxisOk = true;
            if (axis.isElements()) {
                updateCurrentContext(axis.asElements());
            } else if (axis.isAttr()) {
                filterByAttr = true;
            }
        }
        if (ctx.nodeTest() != null && !ctx.nodeTest().isEmpty()) {
            XValue nodeTest = visit(ctx.nodeTest());
            if (filterByAttr) {
                Elements context = currentScope().context();
                String attrName = nodeTest.asString();
                if (currentScope().isRecursion()) {
                    if (context.size() == 1) {
                        Elements findRes = currentScope().singleEl().select("[" + attrName + "]");
                        List<String> attrs = new LinkedList<>();
                        Iterator<Element> it = findRes.iterator();
                        while (it.hasNext()) {
                            attrs.add(it.next().attr(attrName));
                        }
                        return XValue.create(attrs);
                    }
                    Elements findRes2 = new Elements();
                    Iterator<Element> it2 = context.iterator();
                    while (it2.hasNext()) {
                        findRes2.addAll(it2.next().select("[" + attrName + "]"));
                    }
                    List<String> attrs2 = new LinkedList<>();
                    Iterator<Element> it3 = findRes2.iterator();
                    while (it3.hasNext()) {
                        attrs2.add(it3.next().attr(attrName));
                    }
                    return XValue.create(attrs2);
                }
                if (context.size() == 1) {
                    return XValue.create(currentScope().singleEl().attr(attrName));
                }
                List<String> attrs3 = new LinkedList<>();
                Iterator<Element> it4 = context.iterator();
                while (it4.hasNext()) {
                    attrs3.add(it4.next().attr(attrName));
                }
                return XValue.create(attrs3);
            }
            if (nodeTest.isExprStr()) {
                String tagName = nodeTest.asString();
                Elements current = currentScope().context();
                if (currentScope().isRecursion()) {
                    updateCurrentContext(current.select(tagName));
                } else {
                    Elements newContext = new Elements();
                    Iterator<Element> it5 = currentScope().context().iterator();
                    while (it5.hasNext()) {
                        Element el = it5.next();
                        if (isAxisOk) {
                            if (el.nodeName().equals(tagName) || "*".equals(tagName)) {
                                newContext.add(el);
                            }
                        } else {
                            Iterator<Element> it6 = el.children().iterator();
                            while (it6.hasNext()) {
                                Element e = it6.next();
                                if (e.nodeName().equals(tagName) || "*".equals(tagName)) {
                                    newContext.add(e);
                                }
                            }
                        }
                    }
                    updateCurrentContext(newContext);
                }
            } else if (nodeTest.isElements()) {
                updateCurrentContext(nodeTest.asElements());
            } else {
                return nodeTest;
            }
        }
        if (ctx.predicate() != null && ctx.predicate().size() > 0) {
            for (XpathParser.PredicateContext predicate : ctx.predicate()) {
                XValue predicateVal = visit(predicate);
                updateCurrentContext(predicateVal.asElements());
            }
        }
        return XValue.create(currentScope().context());
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitAbbreviatedStep(XpathParser.AbbreviatedStepContext ctx) {
        if ("..".equals(ctx.getText())) {
            HashSet hashSet = new HashSet();
            Elements newContext = new Elements();
            Iterator<Element> it = currentScope().context().iterator();
            while (it.hasNext()) {
                Element e = it.next();
                hashSet.add(e.parent());
            }
            newContext.addAll(hashSet);
            return XValue.create(newContext);
        }
        return XValue.create(currentScope().context());
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitAxisSpecifier(XpathParser.AxisSpecifierContext ctx) {
        TerminalNode axisNode = ctx.AxisName();
        if (axisNode != null) {
            String axis = ctx.AxisName().getText();
            AxisSelector axisSelector = Scanner.findSelectorByName(axis);
            return axisSelector.apply(currentScope().context());
        }
        String token = ctx.getText();
        if (StrPool.AT.equals(token)) {
            return XValue.create(null).attr();
        }
        return null;
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitNodeTest(XpathParser.NodeTestContext ctx) {
        if (ctx.nameTest() != null) {
            return visit(ctx.nameTest());
        }
        if (ctx.NodeType() != null) {
            NodeTest nodeTest = Scanner.findNodeTestByName(ctx.NodeType().getText());
            return nodeTest.call(currentScope());
        }
        return null;
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitPredicate(XpathParser.PredicateContext ctx) {
        Elements newContext = new Elements();
        Iterator<Element> it = currentScope().context().iterator();
        while (it.hasNext()) {
            Element e = it.next();
            this.scopeStack.push(Scope.create(e).setParent(currentScope()));
            XValue exprVal = visit(ctx.expr());
            this.scopeStack.pop();
            if (exprVal.isNumber()) {
                long index = exprVal.asLong().longValue();
                if (index < 0) {
                    if (Objects.equals(e.tagName(), Constants.DEF_TEXT_TAG_NAME)) {
                        index = CommonUtil.getJxSameTagIndexInSiblings(e) + index + 1;
                    } else {
                        index = CommonUtil.sameTagElNums(e, currentScope()) + index + 1;
                    }
                    if (index < 0) {
                        index = 1;
                    }
                }
                if (Objects.equals(e.tagName(), Constants.DEF_TEXT_TAG_NAME)) {
                    if (index == CommonUtil.getJxSameTagIndexInSiblings(e)) {
                        newContext.add(e);
                    }
                } else if (index == CommonUtil.getElIndexInSameTags(e, currentScope())) {
                    newContext.add(e);
                }
            } else if (exprVal.isBoolean()) {
                if (exprVal.asBoolean().booleanValue()) {
                    newContext.add(e);
                }
            } else if (exprVal.isString()) {
                if (StringUtils.isNotBlank(exprVal.asString())) {
                    newContext.add(e);
                }
            } else if (exprVal.isElements()) {
                Elements els = exprVal.asElements();
                if (els.size() > 0) {
                    newContext.add(e);
                }
            } else if (exprVal.isList()) {
                List<String> stringList = exprVal.asList();
                if (stringList.size() > 0) {
                    newContext.add(e);
                }
            } else {
                throw new XpathParserException("unknown expr val:" + exprVal);
            }
        }
        return XValue.create(newContext);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitNameTest(XpathParser.NameTestContext ctx) {
        if ("*".equals(ctx.getText())) {
            return XValue.create("*").exprStr();
        }
        if (ctx.qName() != null && !ctx.qName().isEmpty()) {
            return visit(ctx.qName());
        }
        if (ctx.nCName() != null && !ctx.nCName().isEmpty()) {
            return visit(ctx.nCName());
        }
        return null;
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitQName(XpathParser.QNameContext ctx) {
        List<XpathParser.NCNameContext> ncNameContexts = ctx.nCName();
        if (ncNameContexts != null) {
            if (ncNameContexts.size() > 1) {
                List<String> ncNames = new LinkedList<>();
                for (XpathParser.NCNameContext ncNameContext : ncNameContexts) {
                    XValue value = visit(ncNameContext);
                    if (value != null) {
                        ncNames.add(value.asString());
                    }
                }
                return XValue.create(StringUtils.join(ncNames, ":"));
            }
            return visit(ncNameContexts.get(0));
        }
        return null;
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitNCName(XpathParser.NCNameContext ctx) {
        if (ctx.AxisName() != null) {
            return XValue.create(ctx.AxisName().getText()).exprStr();
        }
        return XValue.create(ctx.NCName().getText()).exprStr();
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitExpr(XpathParser.ExprContext ctx) {
        return visit(ctx.orExpr());
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitOrExpr(XpathParser.OrExprContext ctx) {
        List<XpathParser.AndExprContext> andExprContexts = ctx.andExpr();
        if (andExprContexts.size() > 1) {
            Boolean res = visit(andExprContexts.get(0)).asBoolean();
            for (int i = 1; i < andExprContexts.size(); i++) {
                res = Boolean.valueOf(res.booleanValue() | visit(andExprContexts.get(i)).asBoolean().booleanValue());
            }
            return XValue.create(res);
        }
        return visit(andExprContexts.get(0));
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitAndExpr(XpathParser.AndExprContext ctx) {
        List<XpathParser.EqualityExprContext> equalityExprContexts = ctx.equalityExpr();
        if (equalityExprContexts.size() > 1) {
            Boolean res = visit(equalityExprContexts.get(0)).asBoolean();
            for (int i = 1; i < equalityExprContexts.size(); i++) {
                res = Boolean.valueOf(res.booleanValue() & visit(equalityExprContexts.get(i)).asBoolean().booleanValue());
            }
            return XValue.create(res);
        }
        return visit(equalityExprContexts.get(0));
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitEqualityExpr(XpathParser.EqualityExprContext ctx) {
        List<XpathParser.RelationalExprContext> relationalExprContexts = ctx.relationalExpr();
        if (relationalExprContexts.size() == 1) {
            return visit(relationalExprContexts.get(0));
        }
        if (relationalExprContexts.size() == 2) {
            XValue left = visit(relationalExprContexts.get(0));
            XValue right = visit(relationalExprContexts.get(1));
            if ("=".equals(ctx.op.getText())) {
                if (left.valType().equals(right.valType())) {
                    return XValue.create(Boolean.valueOf(Objects.equals(left, right)));
                }
                return XValue.create(Boolean.valueOf(Objects.equals(left.asString(), right.asString())));
            }
            if (left.valType().equals(right.valType())) {
                return XValue.create(Boolean.valueOf(!Objects.equals(left, right)));
            }
            return XValue.create(Boolean.valueOf(!Objects.equals(left.asString(), right.asString())));
        }
        throw new XpathParserException("error equalityExpr near:" + ctx.getText());
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitRelationalExpr(XpathParser.RelationalExprContext ctx) {
        List<XpathParser.AdditiveExprContext> additiveExprContexts = ctx.additiveExpr();
        if (additiveExprContexts.size() == 1) {
            return visit(additiveExprContexts.get(0));
        }
        if (additiveExprContexts.size() == 2) {
            XValue left = visit(additiveExprContexts.get(0));
            XValue right = visit(additiveExprContexts.get(1));
            switch (ctx.op.getType()) {
                case 24:
                    return XValue.create(Boolean.valueOf(left.compareTo(right) < 0));
                case 25:
                    return XValue.create(Boolean.valueOf(left.compareTo(right) > 0));
                case 26:
                    return XValue.create(Boolean.valueOf(left.compareTo(right) <= 0));
                case 27:
                    return XValue.create(Boolean.valueOf(left.compareTo(right) >= 0));
                case 28:
                case 29:
                default:
                    throw new XpathParserException("unknown operator" + ctx.op.getText());
                case 30:
                    return XValue.create(Boolean.valueOf(left.asString().startsWith(right.asString())));
                case 31:
                    return XValue.create(Boolean.valueOf(left.asString().endsWith(right.asString())));
                case 32:
                    return XValue.create(Boolean.valueOf(left.asString().contains(right.asString())));
                case 33:
                    return XValue.create(Boolean.valueOf(left.asString().matches(right.asString())));
                case 34:
                    return XValue.create(Boolean.valueOf(!left.asString().matches(right.asString())));
            }
        }
        throw new XpathParserException("error equalityExpr near:" + ctx.getText());
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitAdditiveExpr(XpathParser.AdditiveExprContext ctx) {
        List<XpathParser.MultiplicativeExprContext> multiplicativeExprContexts = ctx.multiplicativeExpr();
        if (multiplicativeExprContexts.size() == 1) {
            return visit(multiplicativeExprContexts.get(0));
        }
        Double res = visit(multiplicativeExprContexts.get(0)).asDouble();
        String op = null;
        for (int i = 1; i < ctx.getChildCount(); i++) {
            ParseTree chiCtx = ctx.getChild(i);
            if (chiCtx instanceof XpathParser.MultiplicativeExprContext) {
                XValue next = visit(chiCtx);
                if (Marker.ANY_NON_NULL_MARKER.equals(op)) {
                    res = Double.valueOf(res.doubleValue() + next.asDouble().doubleValue());
                } else if ("-".equals(op)) {
                    res = Double.valueOf(res.doubleValue() - next.asDouble().doubleValue());
                } else {
                    throw new XpathParserException("syntax error, " + ctx.getText());
                }
            } else {
                op = chiCtx.getText();
            }
        }
        return XValue.create(res);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitMultiplicativeExpr(XpathParser.MultiplicativeExprContext ctx) {
        if (ctx.multiplicativeExpr() == null || ctx.multiplicativeExpr().isEmpty()) {
            return visit(ctx.unaryExprNoRoot());
        }
        XValue left = visit(ctx.unaryExprNoRoot());
        XValue right = visit(ctx.multiplicativeExpr());
        switch (ctx.op.getType()) {
            case 17:
                return XValue.create(Double.valueOf(left.asDouble().doubleValue() * right.asDouble().doubleValue()));
            case 18:
                return XValue.create(Double.valueOf(left.asDouble().doubleValue() / right.asDouble().doubleValue()));
            case 19:
                return XValue.create(Double.valueOf(left.asDouble().doubleValue() % right.asDouble().doubleValue()));
            default:
                throw new XpathParserException("syntax error, " + ctx.getText());
        }
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext ctx) {
        XValue value = visit(ctx.unionExprNoRoot());
        if (ctx.sign == null) {
            return value;
        }
        return XValue.create(Double.valueOf(-value.asDouble().doubleValue()));
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitUnionExprNoRoot(XpathParser.UnionExprNoRootContext ctx) {
        if (ctx.pathExprNoRoot() == null && !ctx.pathExprNoRoot().isEmpty()) {
            return visit(ctx.unionExprNoRoot());
        }
        XValue pathExprNoRoot = visit(ctx.pathExprNoRoot());
        if (ctx.op == null) {
            return pathExprNoRoot;
        }
        this.scopeStack.push(Scope.create(currentScope().getParent()));
        XValue unionExprNoRoot = visit(ctx.unionExprNoRoot());
        this.scopeStack.pop();
        if (pathExprNoRoot.isElements()) {
            if (unionExprNoRoot.isElements()) {
                pathExprNoRoot.asElements().addAll(unionExprNoRoot.asElements());
            } else {
                Element element = new Element("V");
                element.appendText(unionExprNoRoot.asString());
                pathExprNoRoot.asElements().add(element);
            }
            return pathExprNoRoot;
        }
        if (pathExprNoRoot.isString()) {
            if (unionExprNoRoot.isElements()) {
                Element element2 = new Element("V");
                element2.appendText(pathExprNoRoot.asString());
                unionExprNoRoot.asElements().add(element2);
                return unionExprNoRoot;
            }
            return XValue.create(pathExprNoRoot.asString() + unionExprNoRoot.asString());
        }
        if (pathExprNoRoot.isBoolean()) {
            if (unionExprNoRoot.isBoolean()) {
                return XValue.create(Boolean.valueOf(pathExprNoRoot.asBoolean().booleanValue() | unionExprNoRoot.asBoolean().booleanValue()));
            }
            if (unionExprNoRoot.isElements()) {
                Element element3 = new Element("V");
                element3.appendText(pathExprNoRoot.asString());
                unionExprNoRoot.asElements().add(element3);
                return unionExprNoRoot;
            }
            if (unionExprNoRoot.isString()) {
                return XValue.create(pathExprNoRoot.asBoolean() + unionExprNoRoot.asString());
            }
            throw new XpathMergeValueException("can not merge val1=" + pathExprNoRoot.asBoolean() + ",val2=" + unionExprNoRoot.asString());
        }
        if (pathExprNoRoot.isNumber()) {
            if (unionExprNoRoot.isString()) {
                return XValue.create(pathExprNoRoot.asDouble() + unionExprNoRoot.asString());
            }
            if (unionExprNoRoot.isElements()) {
                Element element4 = new Element("V");
                element4.appendText(pathExprNoRoot.asString());
                unionExprNoRoot.asElements().add(element4);
                return unionExprNoRoot;
            }
            throw new XpathMergeValueException("can not merge val1=" + pathExprNoRoot.asDouble() + ",val2=" + unionExprNoRoot.asString());
        }
        List<String> tmpVal = new LinkedList<>();
        if (StringUtils.isNotBlank(pathExprNoRoot.asString())) {
            tmpVal.add(pathExprNoRoot.asString());
        }
        if (StringUtils.isNotBlank(unionExprNoRoot.asString())) {
            tmpVal.add(unionExprNoRoot.asString());
        }
        return XValue.create(StringUtils.join(tmpVal, ","));
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitPathExprNoRoot(XpathParser.PathExprNoRootContext ctx) {
        if (ctx.locationPath() != null && !ctx.locationPath().isEmpty()) {
            return visit(ctx.locationPath());
        }
        if (ctx.op == null) {
            return visit(ctx.filterExpr());
        }
        if ("//".equals(ctx.op.getText())) {
            currentScope().recursion();
        }
        return visit(ctx.relativeLocationPath());
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitFilterExpr(XpathParser.FilterExprContext ctx) {
        return visit(ctx.primaryExpr());
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitPrimaryExpr(XpathParser.PrimaryExprContext ctx) {
        if (ctx.expr() != null && !ctx.expr().isEmpty()) {
            return visit(ctx.expr());
        }
        if (ctx.functionCall() != null && !ctx.functionCall().isEmpty()) {
            return visit(ctx.functionCall());
        }
        if (ctx.Literal() != null) {
            return XValue.create(ctx.Literal().getText()).exprStr();
        }
        if (ctx.Number() != null) {
            return XValue.create(NumberUtils.createDouble(ctx.Number().getText()));
        }
        throw new XpathParserException("not support variableReference:" + ctx.getText());
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitFunctionCall(XpathParser.FunctionCallContext ctx) {
        List<XValue> params = new LinkedList<>();
        XValue funcName = visit(ctx.functionName());
        for (XpathParser.ExprContext exprContext : ctx.expr()) {
            this.scopeStack.push(Scope.create(currentScope()));
            params.add(visit(exprContext));
            this.scopeStack.pop();
        }
        Function function = Scanner.findFunctionByName(funcName.asString());
        return function.call(currentScope(), params);
    }

    @Override // org.seimicrawler.xpath.antlr.XpathBaseVisitor, org.seimicrawler.xpath.antlr.XpathVisitor
    public XValue visitFunctionName(XpathParser.FunctionNameContext ctx) {
        return visit(ctx.qName());
    }

    private Scope currentScope() {
        return this.scopeStack.peek();
    }

    private void updateCurrentContext(Elements newContext) {
        this.scopeStack.peek().setContext(newContext);
    }
}
