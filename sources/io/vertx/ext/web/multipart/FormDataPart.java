package io.vertx.ext.web.multipart;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.VertxGen;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/multipart/FormDataPart.class */
public interface FormDataPart {
    @CacheReturn
    String name();

    @CacheReturn
    boolean isAttribute();

    @CacheReturn
    boolean isFileUpload();

    @CacheReturn
    String value();

    @CacheReturn
    String filename();

    @CacheReturn
    String pathname();

    @CacheReturn
    String mediaType();

    @CacheReturn
    Boolean isText();
}
