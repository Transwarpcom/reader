package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.internal.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ArrayIndexOperation.class */
public class ArrayIndexOperation {
    private static final Pattern COMMA = Pattern.compile("\\s*,\\s*");
    private final List<Integer> indexes;

    private ArrayIndexOperation(List<Integer> indexes) {
        this.indexes = Collections.unmodifiableList(indexes);
    }

    public List<Integer> indexes() {
        return this.indexes;
    }

    public boolean isSingleIndexOperation() {
        return this.indexes.size() == 1;
    }

    public String toString() {
        return "[" + Utils.join(",", this.indexes) + "]";
    }

    public static ArrayIndexOperation parse(String operation) {
        for (int i = 0; i < operation.length(); i++) {
            char c = operation.charAt(i);
            if (!Character.isDigit(c) && c != ',' && c != ' ' && c != '-') {
                throw new InvalidPathException("Failed to parse ArrayIndexOperation: " + operation);
            }
        }
        String[] tokens = COMMA.split(operation, -1);
        List<Integer> tempIndexes = new ArrayList<>(tokens.length);
        for (String token : tokens) {
            tempIndexes.add(parseInteger(token));
        }
        return new ArrayIndexOperation(tempIndexes);
    }

    private static Integer parseInteger(String token) {
        try {
            return Integer.valueOf(Integer.parseInt(token));
        } catch (Exception e) {
            throw new InvalidPathException("Failed to parse token in ArrayIndexOperation: " + token, e);
        }
    }
}
