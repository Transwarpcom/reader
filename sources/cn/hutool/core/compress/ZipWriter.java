package cn.hutool.core.compress;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/compress/ZipWriter.class */
public class ZipWriter implements Closeable {
    private final ZipOutputStream out;

    public static ZipWriter of(File zipFile, Charset charset) {
        return new ZipWriter(zipFile, charset);
    }

    public static ZipWriter of(OutputStream out, Charset charset) {
        return new ZipWriter(out, charset);
    }

    public ZipWriter(File zipFile, Charset charset) {
        this.out = getZipOutputStream(zipFile, charset);
    }

    public ZipWriter(OutputStream out, Charset charset) {
        this.out = getZipOutputStream(out, charset);
    }

    public ZipWriter(ZipOutputStream out) {
        this.out = out;
    }

    public ZipWriter setLevel(int level) {
        this.out.setLevel(level);
        return this;
    }

    public ZipWriter setComment(String comment) {
        this.out.setComment(comment);
        return this;
    }

    public ZipOutputStream getOut() {
        return this.out;
    }

    public ZipWriter add(boolean withSrcDir, FileFilter filter, File... files) throws IOException, IORuntimeException {
        for (File file : files) {
            try {
                String srcRootDir = file.getCanonicalPath();
                if (false == file.isDirectory() || withSrcDir) {
                    srcRootDir = file.getCanonicalFile().getParentFile().getCanonicalPath();
                }
                _add(file, srcRootDir, filter);
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }
        }
        return this;
    }

    public ZipWriter add(Resource... resources) throws IORuntimeException {
        for (Resource resource : resources) {
            if (null != resource) {
                add(resource.getName(), resource.getStream());
            }
        }
        return this;
    }

    public ZipWriter add(String path, InputStream in) throws IORuntimeException {
        String path2 = StrUtil.nullToEmpty(path);
        if (null == in) {
            path2 = StrUtil.addSuffixIfNot(path2, "/");
            if (StrUtil.isBlank(path2)) {
                return this;
            }
        }
        return putEntry(path2, in);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IORuntimeException {
        try {
            try {
                this.out.finish();
                IoUtil.close((Closeable) this.out);
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }
        } catch (Throwable th) {
            IoUtil.close((Closeable) this.out);
            throw th;
        }
    }

    private static ZipOutputStream getZipOutputStream(File zipFile, Charset charset) {
        return getZipOutputStream(FileUtil.getOutputStream(zipFile), charset);
    }

    private static ZipOutputStream getZipOutputStream(OutputStream out, Charset charset) {
        if (out instanceof ZipOutputStream) {
            return (ZipOutputStream) out;
        }
        return new ZipOutputStream(out, charset);
    }

    private ZipWriter _add(File file, String srcRootDir, FileFilter filter) throws IOException, IORuntimeException {
        if (null == file || (null != filter && false == filter.accept(file))) {
            return this;
        }
        String subPath = FileUtil.subPath(srcRootDir, file);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (ArrayUtil.isEmpty((Object[]) files)) {
                add(subPath, null);
            } else {
                for (File childFile : files) {
                    _add(childFile, srcRootDir, filter);
                }
            }
        } else {
            putEntry(subPath, FileUtil.getInputStream(file));
        }
        return this;
    }

    private ZipWriter putEntry(String path, InputStream in) throws IOException, IORuntimeException {
        try {
            try {
                this.out.putNextEntry(new ZipEntry(path));
                if (null != in) {
                    IoUtil.copy(in, this.out);
                }
                this.out.closeEntry();
                IoUtil.close((Closeable) in);
                IoUtil.flush(this.out);
                return this;
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }
        } catch (Throwable th) {
            IoUtil.close((Closeable) in);
            throw th;
        }
    }
}
