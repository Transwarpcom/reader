package io.vertx.core.impl.launcher.commands;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.launcher.DefaultCommand;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/ClasspathHandler.class */
public abstract class ClasspathHandler extends DefaultCommand {
    protected static final String PATH_SEP = System.getProperty("path.separator");
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected List<String> classpath;
    protected Object manager;
    private ClassLoader classloader;

    @Option(shortName = "cp", longName = "classpath", argName = "classpath")
    @Description("Provides an extra classpath to be used for the verticle deployment.")
    public void setClasspath(String classpath) {
        if (classpath == null || classpath.isEmpty()) {
            this.classloader = ClasspathHandler.class.getClassLoader();
            this.classpath = Collections.emptyList();
        } else {
            this.classpath = Arrays.asList(classpath.split(PATH_SEP));
            this.classloader = createClassloader();
        }
    }

    protected synchronized ClassLoader createClassloader() {
        URL[] urls = (URL[]) this.classpath.stream().map(path -> {
            File file = new File(path);
            try {
                return file.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new IllegalStateException(e);
            }
        }).toArray(x$0 -> {
            return new URL[x$0];
        });
        return new URLClassLoader(urls, getClass().getClassLoader());
    }

    protected synchronized Object newInstance() throws ClassNotFoundException {
        try {
            this.classloader = (this.classpath == null || this.classpath.isEmpty()) ? ClasspathHandler.class.getClassLoader() : createClassloader();
            Class<?> clazz = this.classloader.loadClass("io.vertx.core.impl.launcher.commands.VertxIsolatedDeployer");
            return clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            this.log.error("Failed to load or instantiate the isolated deployer", e);
            throw new IllegalStateException(e);
        }
    }

    protected synchronized Vertx create(VertxOptions options) {
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            try {
                Thread.currentThread().setContextClassLoader(this.classloader != null ? this.classloader : getClass().getClassLoader());
                Vertx vertx = Vertx.vertx(options);
                Thread.currentThread().setContextClassLoader(originalClassLoader);
                return vertx;
            } catch (Exception e) {
                this.log.error("Failed to create the vert.x instance", e);
                Thread.currentThread().setContextClassLoader(originalClassLoader);
                return null;
            }
        } catch (Throwable th) {
            Thread.currentThread().setContextClassLoader(originalClassLoader);
            throw th;
        }
    }

    protected synchronized void create(VertxOptions options, Handler<AsyncResult<Vertx>> resultHandler) {
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            try {
                Thread.currentThread().setContextClassLoader(this.classloader != null ? this.classloader : getClass().getClassLoader());
                Vertx.clusteredVertx(options, resultHandler);
                Thread.currentThread().setContextClassLoader(originalClassLoader);
            } catch (Exception e) {
                this.log.error("Failed to create the vert.x instance", e);
                Thread.currentThread().setContextClassLoader(originalClassLoader);
            }
        } catch (Throwable th) {
            Thread.currentThread().setContextClassLoader(originalClassLoader);
            throw th;
        }
    }

    public synchronized void deploy(String verticle, Vertx vertx, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler) {
        if (this.manager == null) {
            this.manager = newInstance();
        }
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            try {
                try {
                    Thread.currentThread().setContextClassLoader(this.classloader);
                    Method method = this.manager.getClass().getMethod("deploy", String.class, Vertx.class, DeploymentOptions.class, Handler.class);
                    if (this.executionContext.get("Default-Verticle-Factory") != null && verticle.indexOf(58) == -1) {
                        verticle = this.executionContext.get("Default-Verticle-Factory") + ":" + verticle;
                    }
                    method.invoke(this.manager, verticle, vertx, options, completionHandler);
                    Thread.currentThread().setContextClassLoader(originalClassLoader);
                } catch (Exception e) {
                    this.log.error("Failed to deploy verticle " + verticle, e);
                    Thread.currentThread().setContextClassLoader(originalClassLoader);
                }
            } catch (InvocationTargetException e2) {
                this.log.error("Failed to deploy verticle " + verticle, e2.getCause());
                Thread.currentThread().setContextClassLoader(originalClassLoader);
            }
        } catch (Throwable th) {
            Thread.currentThread().setContextClassLoader(originalClassLoader);
            throw th;
        }
    }
}
