package com.jayway.jsonpath.internal.filter;

import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.internal.CharacterIndex;
import com.jayway.jsonpath.internal.filter.ValueNodes;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/FilterCompiler.class */
public class FilterCompiler {
    private static final Logger logger;
    private static final char DOC_CONTEXT = '$';
    private static final char EVAL_CONTEXT = '@';
    private static final char OPEN_SQUARE_BRACKET = '[';
    private static final char CLOSE_SQUARE_BRACKET = ']';
    private static final char OPEN_PARENTHESIS = '(';
    private static final char CLOSE_PARENTHESIS = ')';
    private static final char OPEN_OBJECT = '{';
    private static final char CLOSE_OBJECT = '}';
    private static final char OPEN_ARRAY = '[';
    private static final char CLOSE_ARRAY = ']';
    private static final char SINGLE_QUOTE = '\'';
    private static final char DOUBLE_QUOTE = '\"';
    private static final char SPACE = ' ';
    private static final char PERIOD = '.';
    private static final char AND = '&';
    private static final char OR = '|';
    private static final char MINUS = '-';
    private static final char LT = '<';
    private static final char GT = '>';
    private static final char EQ = '=';
    private static final char TILDE = '~';
    private static final char TRUE = 't';
    private static final char FALSE = 'f';
    private static final char NULL = 'n';
    private static final char NOT = '!';
    private static final char PATTERN = '/';
    private static final char IGNORE_CASE = 'i';
    private CharacterIndex filter;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !FilterCompiler.class.desiredAssertionStatus();
        logger = LoggerFactory.getLogger((Class<?>) FilterCompiler.class);
    }

    public static Filter compile(String filterString) {
        FilterCompiler compiler = new FilterCompiler(filterString);
        return new CompiledFilter(compiler.compile());
    }

    private FilterCompiler(String filterString) {
        this.filter = new CharacterIndex(filterString);
        this.filter.trim();
        if (!this.filter.currentCharIs('[') || !this.filter.lastCharIs(']')) {
            throw new InvalidPathException("Filter must start with '[' and end with ']'. " + filterString);
        }
        this.filter.incrementPosition(1);
        this.filter.decrementEndPosition(1);
        this.filter.trim();
        if (!this.filter.currentCharIs('?')) {
            throw new InvalidPathException("Filter must start with '[?' and end with ']'. " + filterString);
        }
        this.filter.incrementPosition(1);
        this.filter.trim();
        if (!this.filter.currentCharIs('(') || !this.filter.lastCharIs(')')) {
            throw new InvalidPathException("Filter must start with '[?(' and end with ')]'. " + filterString);
        }
    }

    public Predicate compile() {
        try {
            ExpressionNode result = readLogicalOR();
            this.filter.skipBlanks();
            if (this.filter.inBounds()) {
                throw new InvalidPathException(String.format("Expected end of filter expression instead of: %s", this.filter.subSequence(this.filter.position(), this.filter.length())));
            }
            return result;
        } catch (InvalidPathException e) {
            throw e;
        } catch (Exception e2) {
            throw new InvalidPathException("Failed to parse filter: " + this.filter + ", error on position: " + this.filter.position() + ", char: " + this.filter.currentChar());
        }
    }

    private ValueNode readValueNode() {
        switch (this.filter.skipBlanks().currentChar()) {
            case '!':
                this.filter.incrementPosition(1);
                switch (this.filter.skipBlanks().currentChar()) {
                    case '$':
                        return readPath();
                    case '@':
                        return readPath();
                    default:
                        throw new InvalidPathException(String.format("Unexpected character: %c", '!'));
                }
            case '$':
                return readPath();
            case '@':
                return readPath();
            default:
                return readLiteral();
        }
    }

    private ValueNode readLiteral() {
        switch (this.filter.skipBlanks().currentChar()) {
            case '\"':
                return readStringLiteral('\"');
            case '\'':
                return readStringLiteral('\'');
            case '-':
                return readNumberLiteral();
            case '/':
                return readPattern();
            case '[':
                return readJsonLiteral();
            case 'f':
                return readBooleanLiteral();
            case 'n':
                return readNullLiteral();
            case 't':
                return readBooleanLiteral();
            case '{':
                return readJsonLiteral();
            default:
                return readNumberLiteral();
        }
    }

    private ExpressionNode readLogicalOR() {
        int savepoint;
        List<ExpressionNode> ops = new ArrayList<>();
        ops.add(readLogicalAND());
        while (true) {
            savepoint = this.filter.position();
            if (!this.filter.hasSignificantSubSequence(LogicalOperator.OR.getOperatorString())) {
                break;
            }
            ops.add(readLogicalAND());
        }
        this.filter.setPosition(savepoint);
        return 1 == ops.size() ? ops.get(0) : LogicalExpressionNode.createLogicalOr(ops);
    }

    private ExpressionNode readLogicalAND() {
        int savepoint;
        List<ExpressionNode> ops = new ArrayList<>();
        ops.add(readLogicalANDOperand());
        while (true) {
            savepoint = this.filter.position();
            if (!this.filter.hasSignificantSubSequence(LogicalOperator.AND.getOperatorString())) {
                break;
            }
            ops.add(readLogicalANDOperand());
        }
        this.filter.setPosition(savepoint);
        return 1 == ops.size() ? ops.get(0) : LogicalExpressionNode.createLogicalAnd(ops);
    }

    private ExpressionNode readLogicalANDOperand() {
        int savepoint = this.filter.skipBlanks().position();
        if (this.filter.skipBlanks().currentCharIs('!')) {
            this.filter.readSignificantChar('!');
            switch (this.filter.skipBlanks().currentChar()) {
                case '$':
                case '@':
                    this.filter.setPosition(savepoint);
                    break;
                default:
                    ExpressionNode op = readLogicalANDOperand();
                    return LogicalExpressionNode.createLogicalNot(op);
            }
        }
        if (this.filter.skipBlanks().currentCharIs('(')) {
            this.filter.readSignificantChar('(');
            ExpressionNode op2 = readLogicalOR();
            this.filter.readSignificantChar(')');
            return op2;
        }
        return readExpression();
    }

    private RelationalExpressionNode readExpression() {
        ValueNode left = readValueNode();
        int savepoint = this.filter.position();
        try {
            RelationalOperator operator = readRelationalOperator();
            ValueNode right = readValueNode();
            return new RelationalExpressionNode(left, operator, right);
        } catch (InvalidPathException e) {
            this.filter.setPosition(savepoint);
            ValueNodes.PathNode pathNode = left.asPathNode();
            ValueNode left2 = pathNode.asExistsCheck(pathNode.shouldExists());
            RelationalOperator operator2 = RelationalOperator.EXISTS;
            ValueNode right2 = left2.asPathNode().shouldExists() ? ValueNodes.TRUE : ValueNodes.FALSE;
            return new RelationalExpressionNode(left2, operator2, right2);
        }
    }

    private LogicalOperator readLogicalOperator() {
        int begin = this.filter.skipBlanks().position();
        int end = begin + 1;
        if (!this.filter.inBounds(end)) {
            throw new InvalidPathException("Expected boolean literal");
        }
        CharSequence logicalOperator = this.filter.subSequence(begin, end + 1);
        if (!logicalOperator.equals("||") && !logicalOperator.equals("&&")) {
            throw new InvalidPathException("Expected logical operator");
        }
        this.filter.incrementPosition(logicalOperator.length());
        logger.trace("LogicalOperator from {} to {} -> [{}]", Integer.valueOf(begin), Integer.valueOf(end), logicalOperator);
        return LogicalOperator.fromString(logicalOperator.toString());
    }

    private RelationalOperator readRelationalOperator() {
        int begin = this.filter.skipBlanks().position();
        if (isRelationalOperatorChar(this.filter.currentChar())) {
            while (this.filter.inBounds() && isRelationalOperatorChar(this.filter.currentChar())) {
                this.filter.incrementPosition(1);
            }
        } else {
            while (this.filter.inBounds() && this.filter.currentChar() != ' ') {
                this.filter.incrementPosition(1);
            }
        }
        CharSequence operator = this.filter.subSequence(begin, this.filter.position());
        logger.trace("Operator from {} to {} -> [{}]", Integer.valueOf(begin), Integer.valueOf(this.filter.position() - 1), operator);
        return RelationalOperator.fromString(operator.toString());
    }

    private ValueNodes.NullNode readNullLiteral() {
        int begin = this.filter.position();
        if (this.filter.currentChar() == 'n' && this.filter.inBounds(this.filter.position() + 3)) {
            CharSequence nullValue = this.filter.subSequence(this.filter.position(), this.filter.position() + 4);
            if ("null".equals(nullValue.toString())) {
                logger.trace("NullLiteral from {} to {} -> [{}]", Integer.valueOf(begin), Integer.valueOf(this.filter.position() + 3), nullValue);
                this.filter.incrementPosition(nullValue.length());
                return ValueNode.createNullNode();
            }
        }
        throw new InvalidPathException("Expected <null> value");
    }

    private ValueNodes.JsonNode readJsonLiteral() {
        int begin = this.filter.position();
        char openChar = this.filter.currentChar();
        if (!$assertionsDisabled && openChar != '[' && openChar != '{') {
            throw new AssertionError();
        }
        char closeChar = openChar == '[' ? ']' : '}';
        int closingIndex = this.filter.indexOfMatchingCloseChar(this.filter.position(), openChar, closeChar, true, false);
        if (closingIndex == -1) {
            throw new InvalidPathException("String not closed. Expected ' in " + this.filter);
        }
        this.filter.setPosition(closingIndex + 1);
        CharSequence json = this.filter.subSequence(begin, this.filter.position());
        logger.trace("JsonLiteral from {} to {} -> [{}]", Integer.valueOf(begin), Integer.valueOf(this.filter.position()), json);
        return ValueNode.createJsonNode(json);
    }

    private int endOfFlags(int position) {
        int endIndex = position;
        char[] currentChar = new char[1];
        while (this.filter.inBounds(endIndex)) {
            currentChar[0] = this.filter.charAt(endIndex);
            if (PatternFlag.parseFlags(currentChar) <= 0) {
                break;
            }
            endIndex++;
        }
        return endIndex;
    }

    private ValueNodes.PatternNode readPattern() {
        int endFlagsIndex;
        int begin = this.filter.position();
        int closingIndex = this.filter.nextIndexOfUnescaped('/');
        if (closingIndex == -1) {
            throw new InvalidPathException("Pattern not closed. Expected / in " + this.filter);
        }
        if (this.filter.inBounds(closingIndex + 1) && (endFlagsIndex = endOfFlags(closingIndex + 1)) > closingIndex) {
            CharSequence flags = this.filter.subSequence(closingIndex + 1, endFlagsIndex);
            closingIndex += flags.length();
        }
        this.filter.setPosition(closingIndex + 1);
        CharSequence pattern = this.filter.subSequence(begin, this.filter.position());
        logger.trace("PatternNode from {} to {} -> [{}]", Integer.valueOf(begin), Integer.valueOf(this.filter.position()), pattern);
        return ValueNode.createPatternNode(pattern);
    }

    private ValueNodes.StringNode readStringLiteral(char endChar) {
        int begin = this.filter.position();
        int closingSingleQuoteIndex = this.filter.nextIndexOfUnescaped(endChar);
        if (closingSingleQuoteIndex == -1) {
            throw new InvalidPathException("String literal does not have matching quotes. Expected " + endChar + " in " + this.filter);
        }
        this.filter.setPosition(closingSingleQuoteIndex + 1);
        CharSequence stringLiteral = this.filter.subSequence(begin, this.filter.position());
        logger.trace("StringLiteral from {} to {} -> [{}]", Integer.valueOf(begin), Integer.valueOf(this.filter.position()), stringLiteral);
        return ValueNode.createStringNode(stringLiteral, true);
    }

    private ValueNodes.NumberNode readNumberLiteral() {
        int begin = this.filter.position();
        while (this.filter.inBounds() && this.filter.isNumberCharacter(this.filter.position())) {
            this.filter.incrementPosition(1);
        }
        CharSequence numberLiteral = this.filter.subSequence(begin, this.filter.position());
        logger.trace("NumberLiteral from {} to {} -> [{}]", Integer.valueOf(begin), Integer.valueOf(this.filter.position()), numberLiteral);
        return ValueNode.createNumberNode(numberLiteral);
    }

    private ValueNodes.BooleanNode readBooleanLiteral() {
        int begin = this.filter.position();
        int end = this.filter.currentChar() == 't' ? this.filter.position() + 3 : this.filter.position() + 4;
        if (!this.filter.inBounds(end)) {
            throw new InvalidPathException("Expected boolean literal");
        }
        CharSequence boolValue = this.filter.subSequence(begin, end + 1);
        if (!boolValue.equals("true") && !boolValue.equals("false")) {
            throw new InvalidPathException("Expected boolean literal");
        }
        this.filter.incrementPosition(boolValue.length());
        logger.trace("BooleanLiteral from {} to {} -> [{}]", Integer.valueOf(begin), Integer.valueOf(end), boolValue);
        return ValueNode.createBooleanNode(boolValue);
    }

    private ValueNodes.PathNode readPath() {
        char previousSignificantChar = this.filter.previousSignificantChar();
        int begin = this.filter.position();
        this.filter.incrementPosition(1);
        while (this.filter.inBounds()) {
            if (this.filter.currentChar() == '[') {
                int closingSquareBracketIndex = this.filter.indexOfMatchingCloseChar(this.filter.position(), '[', ']', true, false);
                if (closingSquareBracketIndex == -1) {
                    throw new InvalidPathException("Square brackets does not match in filter " + this.filter);
                }
                this.filter.setPosition(closingSquareBracketIndex + 1);
            }
            boolean closingFunctionBracket = this.filter.currentChar() == ')' && currentCharIsClosingFunctionBracket(begin);
            boolean closingLogicalBracket = this.filter.currentChar() == ')' && !closingFunctionBracket;
            if (!this.filter.inBounds() || isRelationalOperatorChar(this.filter.currentChar()) || this.filter.currentChar() == ' ' || closingLogicalBracket) {
                break;
            }
            this.filter.incrementPosition(1);
        }
        boolean shouldExists = previousSignificantChar != '!';
        CharSequence path = this.filter.subSequence(begin, this.filter.position());
        return ValueNode.createPathNode(path, false, shouldExists);
    }

    private boolean expressionIsTerminated() {
        char c;
        char c2 = this.filter.currentChar();
        if (c2 == ')' || isLogicalOperatorChar(c2) || (c = this.filter.nextSignificantChar()) == ')' || isLogicalOperatorChar(c)) {
            return true;
        }
        return false;
    }

    private boolean currentCharIsClosingFunctionBracket(int lowerBound) {
        if (this.filter.currentChar() != ')') {
            return false;
        }
        int idx = this.filter.indexOfPreviousSignificantChar();
        if (idx == -1 || this.filter.charAt(idx) != '(') {
            return false;
        }
        do {
            idx--;
            if (!this.filter.inBounds(idx) || idx <= lowerBound) {
                return false;
            }
        } while (this.filter.charAt(idx) != '.');
        return true;
    }

    private boolean isLogicalOperatorChar(char c) {
        return c == '&' || c == '|';
    }

    private boolean isRelationalOperatorChar(char c) {
        return c == '<' || c == '>' || c == '=' || c == '~' || c == '!';
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/FilterCompiler$CompiledFilter.class */
    private static final class CompiledFilter extends Filter {
        private final Predicate predicate;

        private CompiledFilter(Predicate predicate) {
            this.predicate = predicate;
        }

        @Override // com.jayway.jsonpath.Filter, com.jayway.jsonpath.Predicate
        public boolean apply(Predicate.PredicateContext ctx) {
            return this.predicate.apply(ctx);
        }

        public String toString() {
            String predicateString = this.predicate.toString();
            if (predicateString.startsWith("(")) {
                return "[?" + predicateString + "]";
            }
            return "[?(" + predicateString + ")]";
        }
    }
}
