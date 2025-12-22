package cn.hutool.core.compress;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/compress/ZipReader.class */
public class ZipReader implements Closeable {
    private ZipFile zipFile;
    private ZipInputStream in;

    public static ZipReader of(File zipFile, Charset charset) {
        return new ZipReader(zipFile, charset);
    }

    public static ZipReader of(InputStream in, Charset charset) {
        return new ZipReader(in, charset);
    }

    public ZipReader(File zipFile, Charset charset) {
        this.zipFile = ZipUtil.toZipFile(zipFile, charset);
    }

    public ZipReader(ZipFile zipFile) {
        this.zipFile = zipFile;
    }

    public ZipReader(InputStream in, Charset charset) {
        this.in = new ZipInputStream(in, charset);
    }

    public ZipReader(ZipInputStream zin) {
        this.in = zin;
    }

    public InputStream get(String path) throws IOException {
        ZipEntry zipEntry;
        if (null != this.zipFile) {
            ZipFile zipFile = this.zipFile;
            ZipEntry entry = zipFile.getEntry(path);
            if (null != entry) {
                return ZipUtil.getStream(zipFile, entry);
            }
            return null;
        }
        try {
            this.in.reset();
            do {
                zipEntry = this.in.getNextEntry();
                if (null == zipEntry) {
                    return null;
                }
            } while (!zipEntry.getName().equals(path));
            return this.in;
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public File readTo(File outFile) throws IORuntimeException {
        return readTo(outFile, null);
    }

    public File readTo(File outFile, Filter<ZipEntry> entryFilter) throws IOException, IORuntimeException {
        read(zipEntry -> {
            InputStream in;
            if (null == entryFilter || entryFilter.accept(zipEntry)) {
                String path = zipEntry.getName();
                if (FileUtil.isWindows()) {
                    path = StrUtil.replace(path, "*", "_");
                }
                File outItemFile = FileUtil.file(outFile, path);
                if (zipEntry.isDirectory()) {
                    outItemFile.mkdirs();
                    return;
                }
                if (null != this.zipFile) {
                    in = ZipUtil.getStream(this.zipFile, zipEntry);
                } else {
                    in = this.in;
                }
                FileUtil.writeFromStream(in, outItemFile, false);
            }
        });
        return outFile;
    }

    public ZipReader read(Consumer<ZipEntry> consumer) throws IOException, IORuntimeException {
        if (null != this.zipFile) {
            readFromZipFile(consumer);
        } else {
            readFromStream(consumer);
        }
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IORuntimeException {
        if (null != this.zipFile) {
            IoUtil.close((Closeable) this.zipFile);
        } else {
            IoUtil.close((Closeable) this.in);
        }
    }

    private void readFromZipFile(Consumer<ZipEntry> consumer) {
        Enumeration<? extends ZipEntry> em = this.zipFile.entries();
        while (em.hasMoreElements()) {
            consumer.accept(em.nextElement());
        }
    }

    private void readFromStream(Consumer<ZipEntry> consumer) throws IOException, IORuntimeException {
        while (true) {
            try {
                ZipEntry zipEntry = this.in.getNextEntry();
                if (null != zipEntry) {
                    consumer.accept(zipEntry);
                } else {
                    return;
                }
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }
        }
    }
}
