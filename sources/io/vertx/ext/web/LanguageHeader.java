package io.vertx.ext.web;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/LanguageHeader.class */
public interface LanguageHeader extends ParsedHeaderValue, Locale {
    String tag();

    String subtag();

    String subtag(int i);

    int subtagCount();
}
