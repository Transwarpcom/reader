package org.jsoup.select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.CombiningEvaluator;
import org.jsoup.select.Evaluator;
import org.jsoup.select.Selector;
import org.jsoup.select.StructuralEvaluator;
import org.slf4j.Marker;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/QueryParser.class */
public class QueryParser {
    private static final String[] combinators;
    private static final String[] AttributeEvals;
    private final TokenQueue tq;
    private final String query;
    private final List<Evaluator> evals = new ArrayList();
    private static final Pattern NTH_AB;
    private static final Pattern NTH_B;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !QueryParser.class.desiredAssertionStatus();
        combinators = new String[]{",", ">", Marker.ANY_NON_NULL_MARKER, "~", " "};
        AttributeEvals = new String[]{"=", "!=", "^=", "$=", "*=", "~="};
        NTH_AB = Pattern.compile("(([+-])?(\\d+)?)n(\\s*([+-])?\\s*\\d+)?", 2);
        NTH_B = Pattern.compile("([+-])?(\\d+)");
    }

    private QueryParser(String query) {
        Validate.notEmpty(query);
        String query2 = query.trim();
        this.query = query2;
        this.tq = new TokenQueue(query2);
    }

    public static Evaluator parse(String query) {
        try {
            QueryParser p = new QueryParser(query);
            return p.parse();
        } catch (IllegalArgumentException e) {
            throw new Selector.SelectorParseException(e.getMessage(), new Object[0]);
        }
    }

    Evaluator parse() throws NumberFormatException {
        this.tq.consumeWhitespace();
        if (this.tq.matchesAny(combinators)) {
            this.evals.add(new StructuralEvaluator.Root());
            combinator(this.tq.consume());
        } else {
            findElements();
        }
        while (!this.tq.isEmpty()) {
            boolean seenWhite = this.tq.consumeWhitespace();
            if (this.tq.matchesAny(combinators)) {
                combinator(this.tq.consume());
            } else if (seenWhite) {
                combinator(' ');
            } else {
                findElements();
            }
        }
        if (this.evals.size() == 1) {
            return this.evals.get(0);
        }
        return new CombiningEvaluator.And(this.evals);
    }

    private void combinator(char combinator) {
        Evaluator currentEval;
        Evaluator rootEval;
        CombiningEvaluator.Or or;
        Evaluator currentEval2;
        this.tq.consumeWhitespace();
        String subQuery = consumeSubQuery();
        Evaluator newEval = parse(subQuery);
        boolean replaceRightMost = false;
        if (this.evals.size() == 1) {
            Evaluator evaluator = this.evals.get(0);
            currentEval = evaluator;
            rootEval = evaluator;
            if ((rootEval instanceof CombiningEvaluator.Or) && combinator != ',') {
                currentEval = ((CombiningEvaluator.Or) currentEval).rightMostEvaluator();
                if (!$assertionsDisabled && currentEval == null) {
                    throw new AssertionError();
                }
                replaceRightMost = true;
            }
        } else {
            Evaluator and = new CombiningEvaluator.And(this.evals);
            currentEval = and;
            rootEval = and;
        }
        this.evals.clear();
        switch (combinator) {
            case ' ':
                currentEval2 = new CombiningEvaluator.And(new StructuralEvaluator.Parent(currentEval), newEval);
                break;
            case '+':
                currentEval2 = new CombiningEvaluator.And(new StructuralEvaluator.ImmediatePreviousSibling(currentEval), newEval);
                break;
            case ',':
                if (currentEval instanceof CombiningEvaluator.Or) {
                    or = (CombiningEvaluator.Or) currentEval;
                } else {
                    or = new CombiningEvaluator.Or();
                    or.add(currentEval);
                }
                or.add(newEval);
                currentEval2 = or;
                break;
            case '>':
                currentEval2 = new CombiningEvaluator.And(new StructuralEvaluator.ImmediateParent(currentEval), newEval);
                break;
            case '~':
                currentEval2 = new CombiningEvaluator.And(new StructuralEvaluator.PreviousSibling(currentEval), newEval);
                break;
            default:
                throw new Selector.SelectorParseException("Unknown combinator: " + combinator, new Object[0]);
        }
        if (replaceRightMost) {
            ((CombiningEvaluator.Or) rootEval).replaceRightMostEvaluator(currentEval2);
        } else {
            rootEval = currentEval2;
        }
        this.evals.add(rootEval);
    }

    private String consumeSubQuery() {
        StringBuilder sq = StringUtil.borrowBuilder();
        while (!this.tq.isEmpty()) {
            if (this.tq.matches("(")) {
                sq.append("(").append(this.tq.chompBalanced('(', ')')).append(")");
            } else if (this.tq.matches("[")) {
                sq.append("[").append(this.tq.chompBalanced('[', ']')).append("]");
            } else {
                if (this.tq.matchesAny(combinators)) {
                    break;
                }
                sq.append(this.tq.consume());
            }
        }
        return StringUtil.releaseBuilder(sq);
    }

    private void findElements() throws NumberFormatException {
        if (this.tq.matchChomp("#")) {
            byId();
            return;
        }
        if (this.tq.matchChomp(".")) {
            byClass();
            return;
        }
        if (this.tq.matchesWord() || this.tq.matches("*|")) {
            byTag();
            return;
        }
        if (this.tq.matches("[")) {
            byAttribute();
            return;
        }
        if (this.tq.matchChomp("*")) {
            allElements();
            return;
        }
        if (this.tq.matchChomp(":lt(")) {
            indexLessThan();
            return;
        }
        if (this.tq.matchChomp(":gt(")) {
            indexGreaterThan();
            return;
        }
        if (this.tq.matchChomp(":eq(")) {
            indexEquals();
            return;
        }
        if (this.tq.matches(":has(")) {
            has();
            return;
        }
        if (this.tq.matches(":contains(")) {
            contains(false);
            return;
        }
        if (this.tq.matches(":containsOwn(")) {
            contains(true);
            return;
        }
        if (this.tq.matches(":containsData(")) {
            containsData();
            return;
        }
        if (this.tq.matches(":matches(")) {
            matches(false);
            return;
        }
        if (this.tq.matches(":matchesOwn(")) {
            matches(true);
            return;
        }
        if (this.tq.matches(":not(")) {
            not();
            return;
        }
        if (this.tq.matchChomp(":nth-child(")) {
            cssNthChild(false, false);
            return;
        }
        if (this.tq.matchChomp(":nth-last-child(")) {
            cssNthChild(true, false);
            return;
        }
        if (this.tq.matchChomp(":nth-of-type(")) {
            cssNthChild(false, true);
            return;
        }
        if (this.tq.matchChomp(":nth-last-of-type(")) {
            cssNthChild(true, true);
            return;
        }
        if (this.tq.matchChomp(":first-child")) {
            this.evals.add(new Evaluator.IsFirstChild());
            return;
        }
        if (this.tq.matchChomp(":last-child")) {
            this.evals.add(new Evaluator.IsLastChild());
            return;
        }
        if (this.tq.matchChomp(":first-of-type")) {
            this.evals.add(new Evaluator.IsFirstOfType());
            return;
        }
        if (this.tq.matchChomp(":last-of-type")) {
            this.evals.add(new Evaluator.IsLastOfType());
            return;
        }
        if (this.tq.matchChomp(":only-child")) {
            this.evals.add(new Evaluator.IsOnlyChild());
            return;
        }
        if (this.tq.matchChomp(":only-of-type")) {
            this.evals.add(new Evaluator.IsOnlyOfType());
            return;
        }
        if (this.tq.matchChomp(":empty")) {
            this.evals.add(new Evaluator.IsEmpty());
        } else if (this.tq.matchChomp(":root")) {
            this.evals.add(new Evaluator.IsRoot());
        } else {
            if (this.tq.matchChomp(":matchText")) {
                this.evals.add(new Evaluator.MatchText());
                return;
            }
            throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.query, this.tq.remainder());
        }
    }

    private void byId() {
        String id = this.tq.consumeCssIdentifier();
        Validate.notEmpty(id);
        this.evals.add(new Evaluator.Id(id));
    }

    private void byClass() {
        String className = this.tq.consumeCssIdentifier();
        Validate.notEmpty(className);
        this.evals.add(new Evaluator.Class(className.trim()));
    }

    private void byTag() {
        String tagName = Normalizer.normalize(this.tq.consumeElementSelector());
        Validate.notEmpty(tagName);
        if (tagName.startsWith("*|")) {
            this.evals.add(new CombiningEvaluator.Or(new Evaluator.Tag(tagName), new Evaluator.TagEndsWith(tagName.replace("*|", ":"))));
            return;
        }
        if (tagName.contains("|")) {
            tagName = tagName.replace("|", ":");
        }
        this.evals.add(new Evaluator.Tag(tagName));
    }

    private void byAttribute() {
        TokenQueue cq = new TokenQueue(this.tq.chompBalanced('[', ']'));
        String key = cq.consumeToAny(AttributeEvals);
        Validate.notEmpty(key);
        cq.consumeWhitespace();
        if (cq.isEmpty()) {
            if (key.startsWith("^")) {
                this.evals.add(new Evaluator.AttributeStarting(key.substring(1)));
                return;
            } else {
                this.evals.add(new Evaluator.Attribute(key));
                return;
            }
        }
        if (cq.matchChomp("=")) {
            this.evals.add(new Evaluator.AttributeWithValue(key, cq.remainder()));
            return;
        }
        if (cq.matchChomp("!=")) {
            this.evals.add(new Evaluator.AttributeWithValueNot(key, cq.remainder()));
            return;
        }
        if (cq.matchChomp("^=")) {
            this.evals.add(new Evaluator.AttributeWithValueStarting(key, cq.remainder()));
            return;
        }
        if (cq.matchChomp("$=")) {
            this.evals.add(new Evaluator.AttributeWithValueEnding(key, cq.remainder()));
        } else if (cq.matchChomp("*=")) {
            this.evals.add(new Evaluator.AttributeWithValueContaining(key, cq.remainder()));
        } else {
            if (cq.matchChomp("~=")) {
                this.evals.add(new Evaluator.AttributeWithValueMatching(key, Pattern.compile(cq.remainder())));
                return;
            }
            throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", this.query, cq.remainder());
        }
    }

    private void allElements() {
        this.evals.add(new Evaluator.AllElements());
    }

    private void indexLessThan() {
        this.evals.add(new Evaluator.IndexLessThan(consumeIndex()));
    }

    private void indexGreaterThan() {
        this.evals.add(new Evaluator.IndexGreaterThan(consumeIndex()));
    }

    private void indexEquals() {
        this.evals.add(new Evaluator.IndexEquals(consumeIndex()));
    }

    private void cssNthChild(boolean backwards, boolean ofType) throws NumberFormatException {
        int a;
        int b;
        String argS = Normalizer.normalize(this.tq.chompTo(")"));
        Matcher mAB = NTH_AB.matcher(argS);
        Matcher mB = NTH_B.matcher(argS);
        if ("odd".equals(argS)) {
            a = 2;
            b = 1;
        } else if ("even".equals(argS)) {
            a = 2;
            b = 0;
        } else if (mAB.matches()) {
            a = mAB.group(3) != null ? Integer.parseInt(mAB.group(1).replaceFirst("^\\+", "")) : 1;
            b = mAB.group(4) != null ? Integer.parseInt(mAB.group(4).replaceFirst("^\\+", "")) : 0;
        } else if (mB.matches()) {
            a = 0;
            b = Integer.parseInt(mB.group().replaceFirst("^\\+", ""));
        } else {
            throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", argS);
        }
        if (ofType) {
            if (backwards) {
                this.evals.add(new Evaluator.IsNthLastOfType(a, b));
                return;
            } else {
                this.evals.add(new Evaluator.IsNthOfType(a, b));
                return;
            }
        }
        if (backwards) {
            this.evals.add(new Evaluator.IsNthLastChild(a, b));
        } else {
            this.evals.add(new Evaluator.IsNthChild(a, b));
        }
    }

    private int consumeIndex() {
        String indexS = this.tq.chompTo(")").trim();
        Validate.isTrue(StringUtil.isNumeric(indexS), "Index must be numeric");
        return Integer.parseInt(indexS);
    }

    private void has() {
        this.tq.consume(":has");
        String subQuery = this.tq.chompBalanced('(', ')');
        Validate.notEmpty(subQuery, ":has(el) subselect must not be empty");
        this.evals.add(new StructuralEvaluator.Has(parse(subQuery)));
    }

    private void contains(boolean own) {
        this.tq.consume(own ? ":containsOwn" : ":contains");
        String searchText = TokenQueue.unescape(this.tq.chompBalanced('(', ')'));
        Validate.notEmpty(searchText, ":contains(text) query must not be empty");
        if (own) {
            this.evals.add(new Evaluator.ContainsOwnText(searchText));
        } else {
            this.evals.add(new Evaluator.ContainsText(searchText));
        }
    }

    private void containsData() {
        this.tq.consume(":containsData");
        String searchText = TokenQueue.unescape(this.tq.chompBalanced('(', ')'));
        Validate.notEmpty(searchText, ":containsData(text) query must not be empty");
        this.evals.add(new Evaluator.ContainsData(searchText));
    }

    private void matches(boolean own) {
        this.tq.consume(own ? ":matchesOwn" : ":matches");
        String regex = this.tq.chompBalanced('(', ')');
        Validate.notEmpty(regex, ":matches(regex) query must not be empty");
        if (own) {
            this.evals.add(new Evaluator.MatchesOwn(Pattern.compile(regex)));
        } else {
            this.evals.add(new Evaluator.Matches(Pattern.compile(regex)));
        }
    }

    private void not() {
        this.tq.consume(":not");
        String subQuery = this.tq.chompBalanced('(', ')');
        Validate.notEmpty(subQuery, ":not(selector) subselect must not be empty");
        this.evals.add(new StructuralEvaluator.Not(parse(subQuery)));
    }

    public String toString() {
        return this.query;
    }
}
