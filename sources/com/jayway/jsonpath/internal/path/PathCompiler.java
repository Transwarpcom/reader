package com.jayway.jsonpath.internal.path;

import ch.qos.logback.classic.spi.CallerData;
import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.internal.CharacterIndex;
import com.jayway.jsonpath.internal.Path;
import com.jayway.jsonpath.internal.Utils;
import com.jayway.jsonpath.internal.filter.FilterCompiler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/PathCompiler.class */
public class PathCompiler {
    private static final char DOC_CONTEXT = '$';
    private static final char EVAL_CONTEXT = '@';
    private static final char OPEN_SQUARE_BRACKET = '[';
    private static final char CLOSE_SQUARE_BRACKET = ']';
    private static final char OPEN_PARENTHESIS = '(';
    private static final char CLOSE_PARENTHESIS = ')';
    private static final char OPEN_BRACE = '{';
    private static final char CLOSE_BRACE = '}';
    private static final char WILDCARD = '*';
    private static final char PERIOD = '.';
    private static final char SPACE = ' ';
    private static final char TAB = '\t';
    private static final char CR = '\r';
    private static final char LF = '\n';
    private static final char BEGIN_FILTER = '?';
    private static final char COMMA = ',';
    private static final char SPLIT = ':';
    private static final char MINUS = '-';
    private static final char SINGLE_QUOTE = '\'';
    private static final char DOUBLE_QUOTE = '\"';
    private final LinkedList<Predicate> filterStack;
    private final CharacterIndex path;

    private PathCompiler(String path, LinkedList<Predicate> filterStack) {
        this(new CharacterIndex(path), filterStack);
    }

    private PathCompiler(CharacterIndex path, LinkedList<Predicate> filterStack) {
        this.filterStack = filterStack;
        this.path = path;
    }

    private Path compile() {
        RootPathToken root = readContextToken();
        return new CompiledPath(root, root.getPathFragment().equals(PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX));
    }

    public static Path compile(String path, Predicate... filters) {
        InvalidPathException ipe;
        try {
            CharacterIndex ci = new CharacterIndex(path);
            ci.trim();
            if (ci.charAt(0) != '$' && ci.charAt(0) != '@') {
                ci = new CharacterIndex("$." + path);
                ci.trim();
            }
            if (ci.lastCharIs('.')) {
                fail("Path must not end with a '.' or '..'");
            }
            LinkedList<Predicate> filterStack = new LinkedList<>(Arrays.asList(filters));
            Path p = new PathCompiler(ci, filterStack).compile();
            return p;
        } catch (Exception e) {
            if (e instanceof InvalidPathException) {
                ipe = (InvalidPathException) e;
            } else {
                ipe = new InvalidPathException(e);
            }
            throw ipe;
        }
    }

    private void readWhitespace() {
        while (this.path.inBounds()) {
            char c = this.path.currentChar();
            if (isWhitespace(c)) {
                this.path.incrementPosition(1);
            } else {
                return;
            }
        }
    }

    private Boolean isPathContext(char c) {
        return Boolean.valueOf(c == '$' || c == '@');
    }

    private RootPathToken readContextToken() {
        readWhitespace();
        if (!isPathContext(this.path.currentChar()).booleanValue()) {
            throw new InvalidPathException("Path must start with '$' or '@'");
        }
        RootPathToken pathToken = PathTokenFactory.createRootPathToken(this.path.currentChar());
        if (this.path.currentIsTail()) {
            return pathToken;
        }
        this.path.incrementPosition(1);
        if (this.path.currentChar() != '.' && this.path.currentChar() != '[') {
            fail("Illegal character at position " + this.path.position() + " expected '.' or '['");
        }
        PathTokenAppender appender = pathToken.getPathTokenAppender();
        readNextToken(appender);
        return pathToken;
    }

    private boolean readNextToken(PathTokenAppender appender) {
        char c = this.path.currentChar();
        switch (c) {
            case '*':
                if (readWildCardToken(appender) || fail("Could not parse token starting at position " + this.path.position())) {
                }
                break;
            case '.':
                if (readDotToken(appender) || fail("Could not parse token starting at position " + this.path.position())) {
                }
                break;
            case '[':
                if (readBracketPropertyToken(appender) || readArrayToken(appender) || readWildCardToken(appender) || readFilterToken(appender) || readPlaceholderToken(appender) || fail("Could not parse token starting at position " + this.path.position() + ". Expected ?, ', 0-9, * ")) {
                }
                break;
            default:
                if (readPropertyOrFunctionToken(appender) || fail("Could not parse token starting at position " + this.path.position())) {
                }
                break;
        }
        return false;
    }

    private boolean readDotToken(PathTokenAppender appender) {
        if (this.path.currentCharIs('.') && this.path.nextCharIs('.')) {
            appender.appendPathToken(PathTokenFactory.crateScanToken());
            this.path.incrementPosition(2);
        } else {
            if (!this.path.hasMoreCharacters()) {
                throw new InvalidPathException("Path must not end with a '.");
            }
            this.path.incrementPosition(1);
        }
        if (this.path.currentCharIs('.')) {
            throw new InvalidPathException("Character '.' on position " + this.path.position() + " is not valid.");
        }
        return readNextToken(appender);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x008d, code lost:
    
        r9 = r8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean readPropertyOrFunctionToken(com.jayway.jsonpath.internal.path.PathTokenAppender r6) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jayway.jsonpath.internal.path.PathCompiler.readPropertyOrFunctionToken(com.jayway.jsonpath.internal.path.PathTokenAppender):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0302  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.jayway.jsonpath.internal.function.Parameter> parseFunctionParameters(java.lang.String r6) {
        /*
            Method dump skipped, instructions count: 879
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jayway.jsonpath.internal.path.PathCompiler.parseFunctionParameters(java.lang.String):java.util.List");
    }

    private boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private boolean readPlaceholderToken(PathTokenAppender appender) {
        int questionmarkIndex;
        int expressionBeginIndex;
        int expressionEndIndex;
        if (!this.path.currentCharIs('[') || (questionmarkIndex = this.path.indexOfNextSignificantChar('?')) == -1) {
            return false;
        }
        char nextSignificantChar = this.path.nextSignificantChar(questionmarkIndex);
        if ((nextSignificantChar != ']' && nextSignificantChar != ',') || (expressionEndIndex = this.path.nextIndexOf((expressionBeginIndex = this.path.position() + 1), ']')) == -1) {
            return false;
        }
        String expression = this.path.subSequence(expressionBeginIndex, expressionEndIndex).toString();
        String[] tokens = expression.split(",");
        if (this.filterStack.size() < tokens.length) {
            throw new InvalidPathException("Not enough predicates supplied for filter [" + expression + "] at position " + this.path.position());
        }
        Collection<Predicate> predicates = new ArrayList<>();
        int length = tokens.length;
        for (int i = 0; i < length; i++) {
            String token = tokens[i];
            String token2 = token != null ? token.trim() : token;
            if (!CallerData.NA.equals(token2 == null ? "" : token2)) {
                throw new InvalidPathException("Expected '?' but found " + token2);
            }
            predicates.add(this.filterStack.pop());
        }
        appender.appendPathToken(PathTokenFactory.createPredicatePathToken(predicates));
        this.path.setPosition(expressionEndIndex + 1);
        return this.path.currentIsTail() || readNextToken(appender);
    }

    private boolean readFilterToken(PathTokenAppender appender) {
        int openBracketIndex;
        int closeBracketIndex;
        if (!this.path.currentCharIs('[') && !this.path.nextSignificantCharIs('?')) {
            return false;
        }
        int openStatementBracketIndex = this.path.position();
        int questionMarkIndex = this.path.indexOfNextSignificantChar('?');
        if (questionMarkIndex == -1 || (openBracketIndex = this.path.indexOfNextSignificantChar(questionMarkIndex, '(')) == -1 || (closeBracketIndex = this.path.indexOfClosingBracket(openBracketIndex, true, true)) == -1 || !this.path.nextSignificantCharIs(closeBracketIndex, ']')) {
            return false;
        }
        int closeStatementBracketIndex = this.path.indexOfNextSignificantChar(closeBracketIndex, ']');
        String criteria = this.path.subSequence(openStatementBracketIndex, closeStatementBracketIndex + 1).toString();
        Predicate predicate = FilterCompiler.compile(criteria);
        appender.appendPathToken(PathTokenFactory.createPredicatePathToken(predicate));
        this.path.setPosition(closeStatementBracketIndex + 1);
        return this.path.currentIsTail() || readNextToken(appender);
    }

    private boolean readWildCardToken(PathTokenAppender appender) {
        boolean inBracket = this.path.currentCharIs('[');
        if (inBracket && !this.path.nextSignificantCharIs('*')) {
            return false;
        }
        if (!this.path.currentCharIs('*') && this.path.isOutOfBounds(this.path.position() + 1)) {
            return false;
        }
        if (inBracket) {
            int wildCardIndex = this.path.indexOfNextSignificantChar('*');
            if (!this.path.nextSignificantCharIs(wildCardIndex, ']')) {
                int offset = wildCardIndex + 1;
                throw new InvalidPathException("Expected wildcard token to end with ']' on position " + offset);
            }
            int bracketCloseIndex = this.path.indexOfNextSignificantChar(wildCardIndex, ']');
            this.path.setPosition(bracketCloseIndex + 1);
        } else {
            this.path.incrementPosition(1);
        }
        appender.appendPathToken(PathTokenFactory.createWildCardPathToken());
        return this.path.currentIsTail() || readNextToken(appender);
    }

    private boolean readArrayToken(PathTokenAppender appender) {
        int expressionBeginIndex;
        int expressionEndIndex;
        if (!this.path.currentCharIs('[')) {
            return false;
        }
        char nextSignificantChar = this.path.nextSignificantChar();
        if ((!Character.isDigit(nextSignificantChar) && nextSignificantChar != '-' && nextSignificantChar != ':') || (expressionEndIndex = this.path.nextIndexOf((expressionBeginIndex = this.path.position() + 1), ']')) == -1) {
            return false;
        }
        String expression = this.path.subSequence(expressionBeginIndex, expressionEndIndex).toString().trim();
        if ("*".equals(expression)) {
            return false;
        }
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (!Character.isDigit(c) && c != ',' && c != '-' && c != ':' && c != ' ') {
                return false;
            }
        }
        boolean isSliceOperation = expression.contains(":");
        if (isSliceOperation) {
            ArraySliceOperation arraySliceOperation = ArraySliceOperation.parse(expression);
            appender.appendPathToken(PathTokenFactory.createSliceArrayPathToken(arraySliceOperation));
        } else {
            ArrayIndexOperation arrayIndexOperation = ArrayIndexOperation.parse(expression);
            appender.appendPathToken(PathTokenFactory.createIndexArrayPathToken(arrayIndexOperation));
        }
        this.path.setPosition(expressionEndIndex + 1);
        return this.path.currentIsTail() || readNextToken(appender);
    }

    private boolean readBracketPropertyToken(PathTokenAppender appender) {
        if (!this.path.currentCharIs('[')) {
            return false;
        }
        char potentialStringDelimiter = this.path.nextSignificantChar();
        if (potentialStringDelimiter != '\'' && potentialStringDelimiter != '\"') {
            return false;
        }
        List<String> properties = new ArrayList<>();
        int startPosition = this.path.position() + 1;
        int readPosition = startPosition;
        int endPosition = 0;
        boolean inProperty = false;
        boolean inEscape = false;
        boolean lastSignificantWasComma = false;
        while (true) {
            if (!this.path.inBounds(readPosition)) {
                break;
            }
            char c = this.path.charAt(readPosition);
            if (inEscape) {
                inEscape = false;
            } else if ('\\' == c) {
                inEscape = true;
            } else if (c == ']' && !inProperty) {
                if (lastSignificantWasComma) {
                    fail("Found empty property at index " + readPosition);
                }
            } else if (c == potentialStringDelimiter) {
                if (inProperty) {
                    char nextSignificantChar = this.path.nextSignificantChar(readPosition);
                    if (nextSignificantChar != ']' && nextSignificantChar != ',') {
                        fail("Property must be separated by comma or Property must be terminated close square bracket at index " + readPosition);
                    }
                    endPosition = readPosition;
                    String prop = this.path.subSequence(startPosition, endPosition).toString();
                    properties.add(Utils.unescape(prop));
                    inProperty = false;
                } else {
                    startPosition = readPosition + 1;
                    inProperty = true;
                    lastSignificantWasComma = false;
                }
            } else if (c == ',') {
                if (lastSignificantWasComma) {
                    fail("Found empty property at index " + readPosition);
                }
                lastSignificantWasComma = true;
            }
            readPosition++;
        }
        if (inProperty) {
            fail("Property has not been closed - missing closing " + potentialStringDelimiter);
        }
        int endBracketIndex = this.path.indexOfNextSignificantChar(endPosition, ']') + 1;
        this.path.setPosition(endBracketIndex);
        appender.appendPathToken(PathTokenFactory.createPropertyPathToken(properties, potentialStringDelimiter));
        return this.path.currentIsTail() || readNextToken(appender);
    }

    public static boolean fail(String message) {
        throw new InvalidPathException(message);
    }
}
