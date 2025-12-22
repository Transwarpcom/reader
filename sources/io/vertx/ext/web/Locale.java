package io.vertx.ext.web;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.web.impl.ParsableLanguageValue;

@VertxGen
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/Locale.class */
public interface Locale {
    @Deprecated
    String language();

    @Deprecated
    String country();

    @Deprecated
    String variant();

    static Locale create() {
        java.util.Locale locale = java.util.Locale.getDefault();
        return new ParsableLanguageValue(locale.getLanguage() + "-" + locale.getCountry() + "-" + locale.getVariant());
    }

    static Locale create(String language) {
        return new ParsableLanguageValue(language);
    }

    static Locale create(String language, String country) {
        return new ParsableLanguageValue(language + "-" + country);
    }

    static Locale create(String language, String country, String variant) {
        return new ParsableLanguageValue(language + "-" + country + "-" + variant);
    }
}
