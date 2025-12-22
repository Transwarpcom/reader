package com.jayway.jsonpath.internal.path;

import com.jayway.jsonpath.InvalidPathException;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ArraySliceOperation.class */
public class ArraySliceOperation {
    private final Integer from;
    private final Integer to;
    private final Operation operation;

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/path/ArraySliceOperation$Operation.class */
    public enum Operation {
        SLICE_FROM,
        SLICE_TO,
        SLICE_BETWEEN
    }

    private ArraySliceOperation(Integer from, Integer to, Operation operation) {
        this.from = from;
        this.to = to;
        this.operation = operation;
    }

    public Integer from() {
        return this.from;
    }

    public Integer to() {
        return this.to;
    }

    public Operation operation() {
        return this.operation;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.from == null ? "" : this.from.toString());
        sb.append(":");
        sb.append(this.to == null ? "" : this.to.toString());
        sb.append("]");
        return sb.toString();
    }

    public static ArraySliceOperation parse(String operation) {
        Operation tempOperation;
        for (int i = 0; i < operation.length(); i++) {
            char c = operation.charAt(i);
            if (!Character.isDigit(c) && c != '-' && c != ':') {
                throw new InvalidPathException("Failed to parse SliceOperation: " + operation);
            }
        }
        String[] tokens = operation.split(":");
        Integer tempFrom = tryRead(tokens, 0);
        Integer tempTo = tryRead(tokens, 1);
        if (tempFrom != null && tempTo == null) {
            tempOperation = Operation.SLICE_FROM;
        } else if (tempFrom != null) {
            tempOperation = Operation.SLICE_BETWEEN;
        } else if (tempTo != null) {
            tempOperation = Operation.SLICE_TO;
        } else {
            throw new InvalidPathException("Failed to parse SliceOperation: " + operation);
        }
        return new ArraySliceOperation(tempFrom, tempTo, tempOperation);
    }

    private static Integer tryRead(String[] tokens, int idx) {
        if (tokens.length <= idx || tokens[idx].equals("")) {
            return null;
        }
        return Integer.valueOf(Integer.parseInt(tokens[idx]));
    }
}
