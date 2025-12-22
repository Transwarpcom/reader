package cn.hutool.core.compiler;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.URLUtil;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/compiler/JavaSourceFileObject.class */
class JavaSourceFileObject extends SimpleJavaFileObject {
    private InputStream inputStream;

    protected JavaSourceFileObject(URI uri) {
        super(uri, JavaFileObject.Kind.SOURCE);
    }

    protected JavaSourceFileObject(String className, String code, Charset charset) {
        this(className, IoUtil.toStream(code, charset));
    }

    protected JavaSourceFileObject(String name, InputStream inputStream) {
        this(URLUtil.getStringURI(name.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension));
        this.inputStream = inputStream;
    }

    public InputStream openInputStream() throws IOException {
        if (this.inputStream == null) {
            this.inputStream = toUri().toURL().openStream();
        }
        return new BufferedInputStream(this.inputStream);
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        InputStream in = openInputStream();
        Throwable th = null;
        try {
            try {
                String utf8 = IoUtil.readUtf8(in);
                if (in != null) {
                    if (0 != 0) {
                        try {
                            in.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        in.close();
                    }
                }
                return utf8;
            } finally {
            }
        } catch (Throwable th3) {
            if (in != null) {
                if (th != null) {
                    try {
                        in.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                } else {
                    in.close();
                }
            }
            throw th3;
        }
    }
}
