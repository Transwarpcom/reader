package io.vertx.core.file;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/FileProps.class */
public interface FileProps {
    long creationTime();

    long lastAccessTime();

    long lastModifiedTime();

    boolean isDirectory();

    boolean isOther();

    boolean isRegularFile();

    boolean isSymbolicLink();

    long size();
}
