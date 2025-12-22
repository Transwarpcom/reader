package org.jsoup.parser;

import java.util.ArrayList;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/ParseErrorList.class */
public class ParseErrorList extends ArrayList<ParseError> {
    private static final int INITIAL_CAPACITY = 16;
    private final int initialCapacity;
    private final int maxSize;

    ParseErrorList(int initialCapacity, int maxSize) {
        super(initialCapacity);
        this.initialCapacity = initialCapacity;
        this.maxSize = maxSize;
    }

    ParseErrorList(ParseErrorList copy) {
        this(copy.initialCapacity, copy.maxSize);
    }

    boolean canAddError() {
        return size() < this.maxSize;
    }

    int getMaxSize() {
        return this.maxSize;
    }

    public static ParseErrorList noTracking() {
        return new ParseErrorList(0, 0);
    }

    public static ParseErrorList tracking(int maxSize) {
        return new ParseErrorList(16, maxSize);
    }
}
