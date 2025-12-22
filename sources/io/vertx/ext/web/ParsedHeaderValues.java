package io.vertx.ext.web;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import java.util.Collection;
import java.util.List;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/ParsedHeaderValues.class */
public interface ParsedHeaderValues {
    List<MIMEHeader> accept();

    List<ParsedHeaderValue> acceptCharset();

    List<ParsedHeaderValue> acceptEncoding();

    List<LanguageHeader> acceptLanguage();

    MIMEHeader contentType();

    @GenIgnore
    <T extends ParsedHeaderValue> T findBestUserAcceptedIn(List<T> list, Collection<T> collection);
}
