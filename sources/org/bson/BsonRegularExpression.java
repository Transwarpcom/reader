package org.bson;

import java.util.Arrays;
import org.bson.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BsonRegularExpression.class */
public final class BsonRegularExpression extends BsonValue {
    private final String pattern;
    private final String options;

    public BsonRegularExpression(String pattern, String options) {
        this.pattern = (String) Assertions.notNull("pattern", pattern);
        this.options = options == null ? "" : sortOptionCharacters(options);
    }

    public BsonRegularExpression(String pattern) {
        this(pattern, null);
    }

    @Override // org.bson.BsonValue
    public BsonType getBsonType() {
        return BsonType.REGULAR_EXPRESSION;
    }

    public String getPattern() {
        return this.pattern;
    }

    public String getOptions() {
        return this.options;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BsonRegularExpression that = (BsonRegularExpression) o;
        if (!this.options.equals(that.options) || !this.pattern.equals(that.pattern)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.pattern.hashCode();
        return (31 * result) + this.options.hashCode();
    }

    public String toString() {
        return "BsonRegularExpression{pattern='" + this.pattern + "', options='" + this.options + "'}";
    }

    private String sortOptionCharacters(String options) {
        char[] chars = options.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
