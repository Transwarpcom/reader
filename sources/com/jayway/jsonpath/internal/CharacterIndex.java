package com.jayway.jsonpath.internal;

import com.jayway.jsonpath.InvalidPathException;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/CharacterIndex.class */
public class CharacterIndex {
    private static final char OPEN_PARENTHESIS = '(';
    private static final char CLOSE_PARENTHESIS = ')';
    private static final char CLOSE_SQUARE_BRACKET = ']';
    private static final char SPACE = ' ';
    private static final char ESCAPE = '\\';
    private static final char SINGLE_QUOTE = '\'';
    private static final char DOUBLE_QUOTE = '\"';
    private static final char MINUS = '-';
    private static final char PERIOD = '.';
    private static final char REGEX = '/';
    private static final char SCI_E = 'E';
    private static final char SCI_e = 'e';
    private final CharSequence charSequence;
    private int position = 0;
    private int endPosition;

    public CharacterIndex(CharSequence charSequence) {
        this.charSequence = charSequence;
        this.endPosition = charSequence.length() - 1;
    }

    public int length() {
        return this.endPosition + 1;
    }

    public char charAt(int idx) {
        return this.charSequence.charAt(idx);
    }

    public char currentChar() {
        return this.charSequence.charAt(this.position);
    }

    public boolean currentCharIs(char c) {
        return this.charSequence.charAt(this.position) == c;
    }

    public boolean lastCharIs(char c) {
        return this.charSequence.charAt(this.endPosition) == c;
    }

    public boolean nextCharIs(char c) {
        return inBounds(this.position + 1) && this.charSequence.charAt(this.position + 1) == c;
    }

    public int incrementPosition(int charCount) {
        return setPosition(this.position + charCount);
    }

    public int decrementEndPosition(int charCount) {
        return setEndPosition(this.endPosition - charCount);
    }

    public int setPosition(int newPosition) {
        this.position = newPosition;
        return this.position;
    }

    private int setEndPosition(int newPosition) {
        this.endPosition = newPosition;
        return this.endPosition;
    }

    public int position() {
        return this.position;
    }

    public int indexOfClosingSquareBracket(int startPosition) {
        for (int readPosition = startPosition; inBounds(readPosition); readPosition++) {
            if (charAt(readPosition) == ']') {
                return readPosition;
            }
        }
        return -1;
    }

    public int indexOfMatchingCloseChar(int startPosition, char openChar, char closeChar, boolean skipStrings, boolean skipRegex) {
        char quoteChar;
        if (charAt(startPosition) != openChar) {
            throw new InvalidPathException("Expected " + openChar + " but found " + charAt(startPosition));
        }
        int opened = 1;
        int readPosition = startPosition + 1;
        while (inBounds(readPosition)) {
            if (skipStrings && ((quoteChar = charAt(readPosition)) == '\'' || quoteChar == '\"')) {
                int readPosition2 = nextIndexOfUnescaped(readPosition, quoteChar);
                if (readPosition2 == -1) {
                    throw new InvalidPathException("Could not find matching close quote for " + quoteChar + " when parsing : " + ((Object) this.charSequence));
                }
                readPosition = readPosition2 + 1;
            }
            if (skipRegex && charAt(readPosition) == '/') {
                int readPosition3 = nextIndexOfUnescaped(readPosition, '/');
                if (readPosition3 == -1) {
                    throw new InvalidPathException("Could not find matching close for / when parsing regex in : " + ((Object) this.charSequence));
                }
                readPosition = readPosition3 + 1;
            }
            if (charAt(readPosition) == openChar) {
                opened++;
            }
            if (charAt(readPosition) == closeChar) {
                opened--;
                if (opened == 0) {
                    return readPosition;
                }
            }
            readPosition++;
        }
        return -1;
    }

    public int indexOfClosingBracket(int startPosition, boolean skipStrings, boolean skipRegex) {
        return indexOfMatchingCloseChar(startPosition, '(', ')', skipStrings, skipRegex);
    }

    public int indexOfNextSignificantChar(char c) {
        return indexOfNextSignificantChar(this.position, c);
    }

    public int indexOfNextSignificantChar(int startPosition, char c) {
        int readPosition = startPosition + 1;
        while (!isOutOfBounds(readPosition) && charAt(readPosition) == ' ') {
            readPosition++;
        }
        if (charAt(readPosition) == c) {
            return readPosition;
        }
        return -1;
    }

    public int nextIndexOf(char c) {
        return nextIndexOf(this.position + 1, c);
    }

    public int nextIndexOf(int startPosition, char c) {
        for (int readPosition = startPosition; !isOutOfBounds(readPosition); readPosition++) {
            if (charAt(readPosition) == c) {
                return readPosition;
            }
        }
        return -1;
    }

    public int nextIndexOfUnescaped(char c) {
        return nextIndexOfUnescaped(this.position, c);
    }

    public int nextIndexOfUnescaped(int startPosition, char c) {
        boolean inEscape = false;
        for (int readPosition = startPosition + 1; !isOutOfBounds(readPosition); readPosition++) {
            if (inEscape) {
                inEscape = false;
            } else if ('\\' == charAt(readPosition)) {
                inEscape = true;
            } else if (c == charAt(readPosition)) {
                return readPosition;
            }
        }
        return -1;
    }

    public char charAtOr(int postition, char defaultChar) {
        return !inBounds(postition) ? defaultChar : charAt(postition);
    }

    public boolean nextSignificantCharIs(int startPosition, char c) {
        int readPosition = startPosition + 1;
        while (!isOutOfBounds(readPosition) && charAt(readPosition) == ' ') {
            readPosition++;
        }
        return !isOutOfBounds(readPosition) && charAt(readPosition) == c;
    }

    public boolean nextSignificantCharIs(char c) {
        return nextSignificantCharIs(this.position, c);
    }

    public char nextSignificantChar() {
        return nextSignificantChar(this.position);
    }

    public char nextSignificantChar(int startPosition) {
        int readPosition = startPosition + 1;
        while (!isOutOfBounds(readPosition) && charAt(readPosition) == ' ') {
            readPosition++;
        }
        if (!isOutOfBounds(readPosition)) {
            return charAt(readPosition);
        }
        return ' ';
    }

    public void readSignificantChar(char c) {
        if (skipBlanks().currentChar() != c) {
            throw new InvalidPathException(String.format("Expected character: %c", Character.valueOf(c)));
        }
        incrementPosition(1);
    }

    public boolean hasSignificantSubSequence(CharSequence s) {
        skipBlanks();
        if (!inBounds((this.position + s.length()) - 1) || !subSequence(this.position, this.position + s.length()).equals(s)) {
            return false;
        }
        incrementPosition(s.length());
        return true;
    }

    public int indexOfPreviousSignificantChar(int startPosition) {
        int readPosition = startPosition - 1;
        while (!isOutOfBounds(readPosition) && charAt(readPosition) == ' ') {
            readPosition--;
        }
        if (!isOutOfBounds(readPosition)) {
            return readPosition;
        }
        return -1;
    }

    public int indexOfPreviousSignificantChar() {
        return indexOfPreviousSignificantChar(this.position);
    }

    public char previousSignificantChar(int startPosition) {
        int previousSignificantCharIndex = indexOfPreviousSignificantChar(startPosition);
        if (previousSignificantCharIndex == -1) {
            return ' ';
        }
        return charAt(previousSignificantCharIndex);
    }

    public char previousSignificantChar() {
        return previousSignificantChar(this.position);
    }

    public boolean currentIsTail() {
        return this.position >= this.endPosition;
    }

    public boolean hasMoreCharacters() {
        return inBounds(this.position + 1);
    }

    public boolean inBounds(int idx) {
        return idx >= 0 && idx <= this.endPosition;
    }

    public boolean inBounds() {
        return inBounds(this.position);
    }

    public boolean isOutOfBounds(int idx) {
        return !inBounds(idx);
    }

    public CharSequence subSequence(int start, int end) {
        return this.charSequence.subSequence(start, end);
    }

    public CharSequence charSequence() {
        return this.charSequence;
    }

    public String toString() {
        return this.charSequence.toString();
    }

    public boolean isNumberCharacter(int readPosition) {
        char c = charAt(readPosition);
        return Character.isDigit(c) || c == '-' || c == '.' || c == 'E' || c == 'e';
    }

    public CharacterIndex skipBlanks() {
        while (inBounds() && this.position < this.endPosition && currentChar() == ' ') {
            incrementPosition(1);
        }
        return this;
    }

    private CharacterIndex skipBlanksAtEnd() {
        while (inBounds() && this.position < this.endPosition && lastCharIs(' ')) {
            decrementEndPosition(1);
        }
        return this;
    }

    public CharacterIndex trim() {
        skipBlanks();
        skipBlanksAtEnd();
        return this;
    }
}
