package io.vertx.core.file.impl;

import io.vertx.core.file.FileProps;
import java.nio.file.attribute.BasicFileAttributes;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/impl/FilePropsImpl.class */
public class FilePropsImpl implements FileProps {
    private final long creationTime;
    private final long lastAccessTime;
    private final long lastModifiedTime;
    private final boolean isDirectory;
    private final boolean isOther;
    private final boolean isRegularFile;
    private final boolean isSymbolicLink;
    private final long size;

    public FilePropsImpl(BasicFileAttributes attrs) {
        this.creationTime = attrs.creationTime().toMillis();
        this.lastModifiedTime = attrs.lastModifiedTime().toMillis();
        this.lastAccessTime = attrs.lastAccessTime().toMillis();
        this.isDirectory = attrs.isDirectory();
        this.isOther = attrs.isOther();
        this.isRegularFile = attrs.isRegularFile();
        this.isSymbolicLink = attrs.isSymbolicLink();
        this.size = attrs.size();
    }

    @Override // io.vertx.core.file.FileProps
    public long creationTime() {
        return this.creationTime;
    }

    @Override // io.vertx.core.file.FileProps
    public long lastAccessTime() {
        return this.lastAccessTime;
    }

    @Override // io.vertx.core.file.FileProps
    public long lastModifiedTime() {
        return this.lastModifiedTime;
    }

    @Override // io.vertx.core.file.FileProps
    public boolean isDirectory() {
        return this.isDirectory;
    }

    @Override // io.vertx.core.file.FileProps
    public boolean isOther() {
        return this.isOther;
    }

    @Override // io.vertx.core.file.FileProps
    public boolean isRegularFile() {
        return this.isRegularFile;
    }

    @Override // io.vertx.core.file.FileProps
    public boolean isSymbolicLink() {
        return this.isSymbolicLink;
    }

    @Override // io.vertx.core.file.FileProps
    public long size() {
        return this.size;
    }
}
