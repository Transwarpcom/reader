package io.vertx.core.file.impl;

import io.vertx.core.file.FileSystemProps;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/impl/FileSystemPropsImpl.class */
public class FileSystemPropsImpl implements FileSystemProps {
    private final long totalSpace;
    private final long unallocatedSpace;
    private final long usableSpace;

    public FileSystemPropsImpl(long totalSpace, long unallocatedSpace, long usableSpace) {
        this.totalSpace = totalSpace;
        this.unallocatedSpace = unallocatedSpace;
        this.usableSpace = usableSpace;
    }

    @Override // io.vertx.core.file.FileSystemProps
    public long totalSpace() {
        return this.totalSpace;
    }

    @Override // io.vertx.core.file.FileSystemProps
    public long unallocatedSpace() {
        return this.unallocatedSpace;
    }

    @Override // io.vertx.core.file.FileSystemProps
    public long usableSpace() {
        return this.usableSpace;
    }
}
