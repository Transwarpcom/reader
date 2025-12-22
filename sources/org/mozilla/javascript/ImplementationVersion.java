package org.mozilla.javascript;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ImplementationVersion.class */
public class ImplementationVersion {
    private String versionString;
    private static final ImplementationVersion version = new ImplementationVersion();

    public static String get() {
        return version.versionString;
    }

    private ImplementationVersion() throws IOException {
        InputStream is;
        Throwable th;
        Attributes attrs;
        try {
            Enumeration<URL> urls = ImplementationVersion.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (urls.hasMoreElements()) {
                URL metaUrl = urls.nextElement();
                try {
                    is = metaUrl.openStream();
                    th = null;
                    try {
                        try {
                            Manifest mf = new Manifest(is);
                            attrs = mf.getMainAttributes();
                        } finally {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        throw th2;
                    }
                } catch (IOException e) {
                }
                if ("Mozilla Rhino".equals(attrs.getValue("Implementation-Title"))) {
                    this.versionString = "Rhino " + attrs.getValue("Implementation-Version") + " " + attrs.getValue("Built-Date").replaceAll("-", " ");
                    if (is != null) {
                        if (0 == 0) {
                            is.close();
                            return;
                        }
                        try {
                            is.close();
                            return;
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                            return;
                        }
                    }
                    return;
                }
                if (is != null) {
                    if (0 != 0) {
                        try {
                            is.close();
                        } catch (Throwable th4) {
                            th.addSuppressed(th4);
                        }
                    } else {
                        is.close();
                    }
                }
            }
        } catch (IOException e2) {
        }
    }
}
