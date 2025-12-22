package org.apache.pdfbox.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.pdfbox.io.IOUtils;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/util/Version.class */
public final class Version {
    private static final String PDFBOX_VERSION_PROPERTIES = "/org/apache/pdfbox/resources/version.properties";

    private Version() {
    }

    public static String getVersion() throws IOException {
        InputStream is = null;
        try {
            is = new BufferedInputStream(Version.class.getResourceAsStream(PDFBOX_VERSION_PROPERTIES));
            Properties properties = new Properties();
            properties.load(is);
            String property = properties.getProperty("pdfbox.version", null);
            IOUtils.closeQuietly(is);
            return property;
        } catch (IOException e) {
            IOUtils.closeQuietly(is);
            return null;
        } catch (Throwable th) {
            IOUtils.closeQuietly(is);
            throw th;
        }
    }
}
