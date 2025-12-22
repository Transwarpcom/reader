package io.vertx.core.file;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import java.util.List;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/FileSystem.class */
public interface FileSystem {
    @Fluent
    FileSystem copy(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem copy(String str, String str2, CopyOptions copyOptions, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem copyBlocking(String str, String str2);

    @Fluent
    FileSystem copyRecursive(String str, String str2, boolean z, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem copyRecursiveBlocking(String str, String str2, boolean z);

    @Fluent
    FileSystem move(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem move(String str, String str2, CopyOptions copyOptions, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem moveBlocking(String str, String str2);

    @Fluent
    FileSystem truncate(String str, long j, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem truncateBlocking(String str, long j);

    @Fluent
    FileSystem chmod(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem chmodBlocking(String str, String str2);

    @Fluent
    FileSystem chmodRecursive(String str, String str2, String str3, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem chmodRecursiveBlocking(String str, String str2, String str3);

    @Fluent
    FileSystem chown(String str, String str2, String str3, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem chownBlocking(String str, String str2, String str3);

    @Fluent
    FileSystem props(String str, Handler<AsyncResult<FileProps>> handler);

    FileProps propsBlocking(String str);

    @Fluent
    FileSystem lprops(String str, Handler<AsyncResult<FileProps>> handler);

    FileProps lpropsBlocking(String str);

    @Fluent
    FileSystem link(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem linkBlocking(String str, String str2);

    @Fluent
    FileSystem symlink(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem symlinkBlocking(String str, String str2);

    @Fluent
    FileSystem unlink(String str, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem unlinkBlocking(String str);

    @Fluent
    FileSystem readSymlink(String str, Handler<AsyncResult<String>> handler);

    String readSymlinkBlocking(String str);

    @Fluent
    FileSystem delete(String str, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem deleteBlocking(String str);

    @Fluent
    FileSystem deleteRecursive(String str, boolean z, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem deleteRecursiveBlocking(String str, boolean z);

    @Fluent
    FileSystem mkdir(String str, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem mkdirBlocking(String str);

    @Fluent
    FileSystem mkdir(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem mkdirBlocking(String str, String str2);

    @Fluent
    FileSystem mkdirs(String str, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem mkdirsBlocking(String str);

    @Fluent
    FileSystem mkdirs(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem mkdirsBlocking(String str, String str2);

    @Fluent
    FileSystem readDir(String str, Handler<AsyncResult<List<String>>> handler);

    List<String> readDirBlocking(String str);

    @Fluent
    FileSystem readDir(String str, String str2, Handler<AsyncResult<List<String>>> handler);

    List<String> readDirBlocking(String str, String str2);

    @Fluent
    FileSystem readFile(String str, Handler<AsyncResult<Buffer>> handler);

    Buffer readFileBlocking(String str);

    @Fluent
    FileSystem writeFile(String str, Buffer buffer, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem writeFileBlocking(String str, Buffer buffer);

    @Fluent
    FileSystem open(String str, OpenOptions openOptions, Handler<AsyncResult<AsyncFile>> handler);

    AsyncFile openBlocking(String str, OpenOptions openOptions);

    @Fluent
    FileSystem createFile(String str, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem createFileBlocking(String str);

    @Fluent
    FileSystem createFile(String str, String str2, Handler<AsyncResult<Void>> handler);

    @Fluent
    FileSystem createFileBlocking(String str, String str2);

    @Fluent
    FileSystem exists(String str, Handler<AsyncResult<Boolean>> handler);

    boolean existsBlocking(String str);

    @Fluent
    FileSystem fsProps(String str, Handler<AsyncResult<FileSystemProps>> handler);

    FileSystemProps fsPropsBlocking(String str);

    @Fluent
    FileSystem createTempDirectory(String str, Handler<AsyncResult<String>> handler);

    String createTempDirectoryBlocking(String str);

    @Fluent
    FileSystem createTempDirectory(String str, String str2, Handler<AsyncResult<String>> handler);

    String createTempDirectoryBlocking(String str, String str2);

    @Fluent
    FileSystem createTempDirectory(String str, String str2, String str3, Handler<AsyncResult<String>> handler);

    String createTempDirectoryBlocking(String str, String str2, String str3);

    @Fluent
    FileSystem createTempFile(String str, String str2, Handler<AsyncResult<String>> handler);

    String createTempFileBlocking(String str, String str2);

    @Fluent
    FileSystem createTempFile(String str, String str2, String str3, Handler<AsyncResult<String>> handler);

    String createTempFileBlocking(String str, String str2, String str3);

    @Fluent
    FileSystem createTempFile(String str, String str2, String str3, String str4, Handler<AsyncResult<String>> handler);

    String createTempFileBlocking(String str, String str2, String str3, String str4);
}
