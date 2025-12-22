package io.vertx.core.file.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.file.impl.FileSystemImpl;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/impl/WindowsFileSystem.class */
public class WindowsFileSystem extends FileSystemImpl {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) WindowsFileSystem.class);

    public WindowsFileSystem(VertxInternal vertx) {
        super(vertx);
    }

    private static void logInternal(String perms) {
        if (perms != null && log.isDebugEnabled()) {
            log.debug("You are running on Windows and POSIX style file permissions are not supported");
        }
    }

    @Override // io.vertx.core.file.impl.FileSystemImpl
    protected FileSystemImpl.BlockingAction<Void> chmodInternal(String path, String perms, String dirPerms, Handler<AsyncResult<Void>> handler) {
        Objects.requireNonNull(path);
        Objects.requireNonNull(perms);
        logInternal(perms);
        logInternal(dirPerms);
        if (log.isDebugEnabled()) {
            log.debug("You are running on Windows and POSIX style file permissions are not supported!");
        }
        return new FileSystemImpl.BlockingAction<Void>(handler) { // from class: io.vertx.core.file.impl.WindowsFileSystem.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.vertx.core.file.impl.FileSystemImpl.BlockingAction
            public Void perform() {
                return null;
            }
        };
    }

    @Override // io.vertx.core.file.impl.FileSystemImpl
    protected FileSystemImpl.BlockingAction<Void> mkdirInternal(String path, String perms, boolean createParents, Handler<AsyncResult<Void>> handler) {
        logInternal(perms);
        return super.mkdirInternal(path, null, createParents, handler);
    }

    @Override // io.vertx.core.file.impl.FileSystemImpl
    protected AsyncFile doOpen(String path, OpenOptions options, ContextInternal context) {
        logInternal(options.getPerms());
        return new AsyncFileImpl(this.vertx, path, options, context);
    }

    @Override // io.vertx.core.file.impl.FileSystemImpl
    protected FileSystemImpl.BlockingAction<Void> createFileInternal(String p, String perms, Handler<AsyncResult<Void>> handler) {
        logInternal(perms);
        return super.createFileInternal(p, null, handler);
    }

    @Override // io.vertx.core.file.impl.FileSystemImpl
    protected FileSystemImpl.BlockingAction<Void> chownInternal(String path, String user, String group, Handler<AsyncResult<Void>> handler) {
        if (group != null && log.isDebugEnabled()) {
            log.debug("You are running on Windows and POSIX style file ownership is not supported");
        }
        return super.chownInternal(path, user, group, handler);
    }
}
