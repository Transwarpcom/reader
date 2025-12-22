package io.vertx.ext.web.multipart;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.web.multipart.impl.MultipartFormImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/multipart/MultipartForm.class */
public interface MultipartForm extends Iterable<FormDataPart> {
    @Fluent
    MultipartForm attribute(String str, String str2);

    @Fluent
    MultipartForm textFileUpload(String str, String str2, String str3, String str4);

    @Fluent
    MultipartForm binaryFileUpload(String str, String str2, String str3, String str4);

    static MultipartForm create() {
        return new MultipartFormImpl();
    }
}
