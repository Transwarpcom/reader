package io.vertx.ext.web;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/MIMEHeader.class */
public interface MIMEHeader extends ParsedHeaderValue {
    String component();

    String subComponent();
}
