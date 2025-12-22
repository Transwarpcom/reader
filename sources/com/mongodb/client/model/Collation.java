package com.mongodb.client.model;

import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.lang.Nullable;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Collation.class */
public final class Collation {
    private final String locale;
    private final Boolean caseLevel;
    private final CollationCaseFirst caseFirst;
    private final CollationStrength strength;
    private final Boolean numericOrdering;
    private final CollationAlternate alternate;
    private final CollationMaxVariable maxVariable;
    private final Boolean normalization;
    private final Boolean backwards;

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Collation options) {
        return new Builder();
    }

    @NotThreadSafe
    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/Collation$Builder.class */
    public static final class Builder {
        private String locale;
        private Boolean caseLevel;
        private CollationCaseFirst caseFirst;
        private CollationStrength strength;
        private Boolean numericOrdering;
        private CollationAlternate alternate;
        private CollationMaxVariable maxVariable;
        private Boolean normalization;
        private Boolean backwards;

        private Builder() {
        }

        private Builder(Collation options) {
            this.locale = options.getLocale();
            this.caseLevel = options.getCaseLevel();
            this.caseFirst = options.getCaseFirst();
            this.strength = options.getStrength();
            this.numericOrdering = options.getNumericOrdering();
            this.alternate = options.getAlternate();
            this.maxVariable = options.getMaxVariable();
            this.normalization = options.getNormalization();
            this.backwards = options.getBackwards();
        }

        public Builder locale(@Nullable String locale) {
            this.locale = locale;
            return this;
        }

        public Builder caseLevel(@Nullable Boolean caseLevel) {
            this.caseLevel = caseLevel;
            return this;
        }

        public Builder collationCaseFirst(@Nullable CollationCaseFirst caseFirst) {
            this.caseFirst = caseFirst;
            return this;
        }

        public Builder collationStrength(@Nullable CollationStrength strength) {
            this.strength = strength;
            return this;
        }

        public Builder numericOrdering(@Nullable Boolean numericOrdering) {
            this.numericOrdering = numericOrdering;
            return this;
        }

        public Builder collationAlternate(@Nullable CollationAlternate alternate) {
            this.alternate = alternate;
            return this;
        }

        public Builder collationMaxVariable(@Nullable CollationMaxVariable maxVariable) {
            this.maxVariable = maxVariable;
            return this;
        }

        public Builder normalization(@Nullable Boolean normalization) {
            this.normalization = normalization;
            return this;
        }

        public Builder backwards(@Nullable Boolean backwards) {
            this.backwards = backwards;
            return this;
        }

        public Collation build() {
            return new Collation(this);
        }
    }

    @Nullable
    public String getLocale() {
        return this.locale;
    }

    @Nullable
    public Boolean getCaseLevel() {
        return this.caseLevel;
    }

    @Nullable
    public CollationCaseFirst getCaseFirst() {
        return this.caseFirst;
    }

    @Nullable
    public CollationStrength getStrength() {
        return this.strength;
    }

    @Nullable
    public Boolean getNumericOrdering() {
        return this.numericOrdering;
    }

    @Nullable
    public CollationAlternate getAlternate() {
        return this.alternate;
    }

    @Nullable
    public CollationMaxVariable getMaxVariable() {
        return this.maxVariable;
    }

    @Nullable
    public Boolean getNormalization() {
        return this.normalization;
    }

    @Nullable
    public Boolean getBackwards() {
        return this.backwards;
    }

    public BsonDocument asDocument() {
        BsonDocument collation = new BsonDocument();
        if (this.locale != null) {
            collation.put("locale", (BsonValue) new BsonString(this.locale));
        }
        if (this.caseLevel != null) {
            collation.put("caseLevel", (BsonValue) new BsonBoolean(this.caseLevel.booleanValue()));
        }
        if (this.caseFirst != null) {
            collation.put("caseFirst", (BsonValue) new BsonString(this.caseFirst.getValue()));
        }
        if (this.strength != null) {
            collation.put("strength", (BsonValue) new BsonInt32(this.strength.getIntRepresentation()));
        }
        if (this.numericOrdering != null) {
            collation.put("numericOrdering", (BsonValue) new BsonBoolean(this.numericOrdering.booleanValue()));
        }
        if (this.alternate != null) {
            collation.put("alternate", (BsonValue) new BsonString(this.alternate.getValue()));
        }
        if (this.maxVariable != null) {
            collation.put("maxVariable", (BsonValue) new BsonString(this.maxVariable.getValue()));
        }
        if (this.normalization != null) {
            collation.put("normalization", (BsonValue) new BsonBoolean(this.normalization.booleanValue()));
        }
        if (this.backwards != null) {
            collation.put("backwards", (BsonValue) new BsonBoolean(this.backwards.booleanValue()));
        }
        return collation;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Collation that = (Collation) o;
        if (this.locale != null) {
            if (!this.locale.equals(that.getLocale())) {
                return false;
            }
        } else if (that.getLocale() != null) {
            return false;
        }
        if (this.caseLevel != null) {
            if (!this.caseLevel.equals(that.getCaseLevel())) {
                return false;
            }
        } else if (that.getCaseLevel() != null) {
            return false;
        }
        if (getCaseFirst() != that.getCaseFirst() || getStrength() != that.getStrength()) {
            return false;
        }
        if (this.numericOrdering != null) {
            if (!this.numericOrdering.equals(that.getNumericOrdering())) {
                return false;
            }
        } else if (that.getNumericOrdering() != null) {
            return false;
        }
        if (getAlternate() != that.getAlternate() || getMaxVariable() != that.getMaxVariable()) {
            return false;
        }
        if (this.normalization != null) {
            if (!this.normalization.equals(that.getNormalization())) {
                return false;
            }
        } else if (that.getNormalization() != null) {
            return false;
        }
        if (this.backwards != null) {
            if (!this.backwards.equals(that.getBackwards())) {
                return false;
            }
            return true;
        }
        if (that.getBackwards() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.locale != null ? this.locale.hashCode() : 0;
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + (this.caseLevel != null ? this.caseLevel.hashCode() : 0))) + (this.caseFirst != null ? this.caseFirst.hashCode() : 0))) + (this.strength != null ? this.strength.hashCode() : 0))) + (this.numericOrdering != null ? this.numericOrdering.hashCode() : 0))) + (this.alternate != null ? this.alternate.hashCode() : 0))) + (this.maxVariable != null ? this.maxVariable.hashCode() : 0))) + (this.normalization != null ? this.normalization.hashCode() : 0))) + (this.backwards != null ? this.backwards.hashCode() : 0);
    }

    public String toString() {
        return "Collation{locale='" + this.locale + "', caseLevel=" + this.caseLevel + ", caseFirst=" + this.caseFirst + ", strength=" + this.strength + ", numericOrdering=" + this.numericOrdering + ", alternate=" + this.alternate + ", maxVariable=" + this.maxVariable + ", normalization=" + this.normalization + ", backwards=" + this.backwards + "}";
    }

    private Collation(Builder builder) {
        this.locale = builder.locale;
        this.caseLevel = builder.caseLevel;
        this.caseFirst = builder.caseFirst;
        this.strength = builder.strength;
        this.numericOrdering = builder.numericOrdering;
        this.alternate = builder.alternate;
        this.maxVariable = builder.maxVariable;
        this.normalization = builder.normalization;
        this.backwards = builder.backwards;
    }
}
