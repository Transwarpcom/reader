package org.apache.commons.logging;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.PrivilegedAction;
import java.util.Properties;

/* JADX WARN: Classes with same name are omitted:
  reader.jar:BOOT-INF/lib/commons-logging-1.2.jar:org/apache/commons/logging/LogFactory.class
 */
/* loaded from: reader.jar:BOOT-INF/lib/spring-jcl-5.1.8.RELEASE.jar:org/apache/commons/logging/LogFactory.class */
public abstract class LogFactory {
    public static Log getLog(Class<?> clazz) {
        return getLog(clazz.getName());
    }

    public static Log getLog(String name) {
        return LogAdapter.createLog(name);
    }

    @Deprecated
    public static LogFactory getFactory() {
        return new LogFactory() { // from class: org.apache.commons.logging.LogFactory.1
        };
    }

    @Deprecated
    public Log getInstance(Class<?> clazz) {
        return getLog(clazz);
    }

    @Deprecated
    public Log getInstance(String name) {
        return getLog(name);
    }

    /* renamed from: org.apache.commons.logging.LogFactory$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/commons-logging-1.2.jar:org/apache/commons/logging/LogFactory$2.class */
    static class AnonymousClass2 implements PrivilegedAction {
        private final String val$factoryClass;
        private final ClassLoader val$classLoader;

        AnonymousClass2(String str, ClassLoader classLoader) {
            this.val$factoryClass = str;
            this.val$classLoader = classLoader;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            return LogFactory.createFactory(this.val$factoryClass, this.val$classLoader);
        }
    }

    /* renamed from: org.apache.commons.logging.LogFactory$3, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/commons-logging-1.2.jar:org/apache/commons/logging/LogFactory$3.class */
    static class AnonymousClass3 implements PrivilegedAction {
        private final ClassLoader val$loader;
        private final String val$name;

        AnonymousClass3(ClassLoader classLoader, String str) {
            this.val$loader = classLoader;
            this.val$name = str;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            if (this.val$loader != null) {
                return this.val$loader.getResourceAsStream(this.val$name);
            }
            return ClassLoader.getSystemResourceAsStream(this.val$name);
        }
    }

    /* renamed from: org.apache.commons.logging.LogFactory$4, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/commons-logging-1.2.jar:org/apache/commons/logging/LogFactory$4.class */
    static class AnonymousClass4 implements PrivilegedAction {
        private final ClassLoader val$loader;
        private final String val$name;

        AnonymousClass4(ClassLoader classLoader, String str) {
            this.val$loader = classLoader;
            this.val$name = str;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            try {
                if (this.val$loader != null) {
                    return this.val$loader.getResources(this.val$name);
                }
                return ClassLoader.getSystemResources(this.val$name);
            } catch (IOException e) {
                if (LogFactory.isDiagnosticsEnabled()) {
                    LogFactory.access$000(new StringBuffer().append("Exception while trying to find configuration file ").append(this.val$name).append(":").append(e.getMessage()).toString());
                    return null;
                }
                return null;
            } catch (NoSuchMethodError e2) {
                return null;
            }
        }
    }

    /* renamed from: org.apache.commons.logging.LogFactory$5, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/commons-logging-1.2.jar:org/apache/commons/logging/LogFactory$5.class */
    static class AnonymousClass5 implements PrivilegedAction {
        private final URL val$url;

        AnonymousClass5(URL url) {
            this.val$url = url;
        }

        @Override // java.security.PrivilegedAction
        public Object run() throws IOException {
            InputStream stream = null;
            try {
                try {
                    URLConnection connection = this.val$url.openConnection();
                    connection.setUseCaches(false);
                    InputStream stream2 = connection.getInputStream();
                    if (stream2 == null) {
                        if (stream2 == null) {
                            return null;
                        }
                        try {
                            stream2.close();
                            return null;
                        } catch (IOException e) {
                            if (!LogFactory.isDiagnosticsEnabled()) {
                                return null;
                            }
                            LogFactory.access$000(new StringBuffer().append("Unable to close stream for URL ").append(this.val$url).toString());
                            return null;
                        }
                    }
                    Properties props = new Properties();
                    props.load(stream2);
                    stream2.close();
                    stream = null;
                    if (0 != 0) {
                        try {
                            stream.close();
                        } catch (IOException e2) {
                            if (LogFactory.isDiagnosticsEnabled()) {
                                LogFactory.access$000(new StringBuffer().append("Unable to close stream for URL ").append(this.val$url).toString());
                            }
                        }
                    }
                    return props;
                } catch (IOException e3) {
                    if (LogFactory.isDiagnosticsEnabled()) {
                        LogFactory.access$000(new StringBuffer().append("Unable to read URL ").append(this.val$url).toString());
                    }
                    if (stream == null) {
                        return null;
                    }
                    try {
                        stream.close();
                        return null;
                    } catch (IOException e4) {
                        if (!LogFactory.isDiagnosticsEnabled()) {
                            return null;
                        }
                        LogFactory.access$000(new StringBuffer().append("Unable to close stream for URL ").append(this.val$url).toString());
                        return null;
                    }
                }
            } catch (Throwable th) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e5) {
                        if (LogFactory.isDiagnosticsEnabled()) {
                            LogFactory.access$000(new StringBuffer().append("Unable to close stream for URL ").append(this.val$url).toString());
                        }
                    }
                }
                throw th;
            }
        }
    }

    /* renamed from: org.apache.commons.logging.LogFactory$6, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/commons-logging-1.2.jar:org/apache/commons/logging/LogFactory$6.class */
    static class AnonymousClass6 implements PrivilegedAction {
        private final String val$key;
        private final String val$def;

        AnonymousClass6(String str, String str2) {
            this.val$key = str;
            this.val$def = str2;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            return System.getProperty(this.val$key, this.val$def);
        }
    }
}
