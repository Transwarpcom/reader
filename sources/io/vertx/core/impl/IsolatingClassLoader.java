package io.vertx.core.impl;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/IsolatingClassLoader.class */
public class IsolatingClassLoader extends URLClassLoader {
    private volatile boolean closed;
    private List<String> isolatedClasses;
    int refCount;

    public IsolatingClassLoader(URL[] urls, ClassLoader parent, List<String> isolatedClasses) {
        super(urls, parent);
        this.isolatedClasses = isolatedClasses;
        this.refCount = 0;
    }

    @Override // java.lang.ClassLoader
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> cls;
        synchronized (getClassLoadingLock(name)) {
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                if (isIsolatedClass(name)) {
                    if (isVertxOrSystemClass(name)) {
                        try {
                            c = getParent().loadClass(name);
                        } catch (ClassNotFoundException e) {
                        }
                    }
                    if (c == null) {
                        try {
                            c = findClass(name);
                        } catch (ClassNotFoundException e2) {
                            c = getParent().loadClass(name);
                        }
                    }
                    if (resolve) {
                        resolveClass(c);
                    }
                } else {
                    c = super.loadClass(name, resolve);
                }
                cls = c;
            } else {
                cls = c;
            }
        }
        return cls;
    }

    private boolean isIsolatedClass(String name) {
        if (this.isolatedClasses != null) {
            for (String isolated : this.isolatedClasses) {
                if (isolated.endsWith(".*")) {
                    String isolatedPackage = isolated.substring(0, isolated.length() - 1);
                    String paramPackage = name.substring(0, name.lastIndexOf(46) + 1);
                    if (paramPackage.startsWith(isolatedPackage)) {
                        return true;
                    }
                } else if (isolated.equals(name)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String name) {
        URL url = findResource(name);
        if (url == null) {
            url = super.getResource(name);
        }
        return url;
    }

    @Override // java.lang.ClassLoader
    public Enumeration<URL> getResources(String name) throws IOException {
        List<URL> resources = Collections.list(findResources(name));
        if (getParent() != null) {
            Enumeration<URL> parentResources = getParent().getResources(name);
            if (parentResources.hasMoreElements()) {
                resources.addAll(Collections.list(parentResources));
            }
        }
        return Collections.enumeration(resources);
    }

    @Override // java.net.URLClassLoader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
        super.close();
    }

    public boolean isClosed() {
        return this.closed;
    }

    private boolean isVertxOrSystemClass(String name) {
        return name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("sun.*") || name.startsWith("com.sun.") || name.startsWith("io.vertx.core") || name.startsWith("io.netty.") || name.startsWith("com.fasterxml.jackson");
    }
}
