package com.mongodb.client.model;

import com.mongodb.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/TextSearchOptions.class */
public final class TextSearchOptions {
    private String language;
    private Boolean caseSensitive;
    private Boolean diacriticSensitive;

    @Nullable
    public String getLanguage() {
        return this.language;
    }

    public TextSearchOptions language(@Nullable String language) {
        this.language = language;
        return this;
    }

    @Nullable
    public Boolean getCaseSensitive() {
        return this.caseSensitive;
    }

    public TextSearchOptions caseSensitive(@Nullable Boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        return this;
    }

    @Nullable
    public Boolean getDiacriticSensitive() {
        return this.diacriticSensitive;
    }

    public TextSearchOptions diacriticSensitive(@Nullable Boolean diacriticSensitive) {
        this.diacriticSensitive = diacriticSensitive;
        return this;
    }

    public String toString() {
        return "Text Search Options{language='" + this.language + "', caseSensitive=" + this.caseSensitive + ", diacriticSensitive=" + this.diacriticSensitive + '}';
    }
}
