package io.vertx.core.file;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/FileSystemProps.class */
public interface FileSystemProps {
    long totalSpace();

    long unallocatedSpace();

    long usableSpace();
}
