package org.bson.types;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/types/Symbol.class */
public class Symbol implements Serializable {
    private static final long serialVersionUID = 1326269319883146072L;
    private final String symbol;

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Symbol symbol1 = (Symbol) o;
        if (!this.symbol.equals(symbol1.symbol)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.symbol.hashCode();
    }

    public String toString() {
        return this.symbol;
    }
}
