package org.apache.logging.log4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/* loaded from: reader.jar:BOOT-INF/lib/log4j-api-2.11.2.jar:org/apache/logging/log4j/util/PropertyFilePropertySource.class */
public class PropertyFilePropertySource extends PropertiesPropertySource {
    public PropertyFilePropertySource(String fileName) {
        super(loadPropertiesFile(fileName));
    }

    private static Properties loadPropertiesFile(String fileName) throws IOException {
        InputStream in;
        Throwable th;
        Properties props = new Properties();
        for (URL url : LoaderUtil.findResources(fileName)) {
            try {
                in = url.openStream();
                th = null;
            } catch (IOException e) {
                LowLevelLogUtil.logException("Unable to read " + url, e);
            }
            try {
                try {
                    props.load(in);
                    if (in != null) {
                        if (0 != 0) {
                            try {
                                in.close();
                            } catch (Throwable x2) {
                                th.addSuppressed(x2);
                            }
                        } else {
                            in.close();
                        }
                    }
                } catch (Throwable th2) {
                    if (in != null) {
                        if (th != null) {
                            try {
                                in.close();
                            } catch (Throwable x22) {
                                th.addSuppressed(x22);
                            }
                        } else {
                            in.close();
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th3;
            }
        }
        return props;
    }

    @Override // org.apache.logging.log4j.util.PropertiesPropertySource, org.apache.logging.log4j.util.PropertySource
    public int getPriority() {
        return 0;
    }
}
