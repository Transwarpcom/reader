package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.ServiceHelper;
import io.vertx.core.Verticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.VerticleFactory;
import io.vertx.core.spi.metrics.VertxMetrics;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/DeploymentManager.class */
public class DeploymentManager {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) DeploymentManager.class);
    private final VertxInternal vertx;
    private final Map<String, Deployment> deployments = new ConcurrentHashMap();
    private final Map<String, IsolatingClassLoader> classloaders = new HashMap();
    private final Map<String, List<VerticleFactory>> verticleFactories = new ConcurrentHashMap();
    private final List<VerticleFactory> defaultFactories = new ArrayList();

    public DeploymentManager(VertxInternal vertx) {
        this.vertx = vertx;
        loadVerticleFactories();
    }

    private void loadVerticleFactories() {
        Collection<VerticleFactory> factories = ServiceHelper.loadFactories(VerticleFactory.class);
        factories.forEach(this::registerVerticleFactory);
        VerticleFactory defaultFactory = new JavaVerticleFactory();
        defaultFactory.init(this.vertx);
        this.defaultFactories.add(defaultFactory);
    }

    private String generateDeploymentID() {
        return UUID.randomUUID().toString();
    }

    public void deployVerticle(Supplier<Verticle> verticleSupplier, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler) {
        if (options.getInstances() < 1) {
            throw new IllegalArgumentException("Can't specify < 1 instances to deploy");
        }
        if (options.isMultiThreaded() && !options.isWorker()) {
            throw new IllegalArgumentException("If multi-threaded then must be worker too");
        }
        if (options.getExtraClasspath() != null) {
            throw new IllegalArgumentException("Can't specify extraClasspath for already created verticle");
        }
        if (options.getIsolationGroup() != null) {
            throw new IllegalArgumentException("Can't specify isolationGroup for already created verticle");
        }
        if (options.getIsolatedClasses() != null) {
            throw new IllegalArgumentException("Can't specify isolatedClasses for already created verticle");
        }
        ContextInternal currentContext = this.vertx.getOrCreateContext();
        ClassLoader cl = getClassLoader(options);
        int nbInstances = options.getInstances();
        Set<Verticle> verticles = Collections.newSetFromMap(new IdentityHashMap());
        for (int i = 0; i < nbInstances; i++) {
            try {
                Verticle verticle = verticleSupplier.get();
                if (verticle == null) {
                    if (completionHandler != null) {
                        completionHandler.handle(Future.failedFuture("Supplied verticle is null"));
                        return;
                    }
                    return;
                }
                verticles.add(verticle);
            } catch (Exception e) {
                if (completionHandler != null) {
                    completionHandler.handle(Future.failedFuture(e));
                    return;
                }
                return;
            }
        }
        if (verticles.size() != nbInstances) {
            if (completionHandler != null) {
                completionHandler.handle(Future.failedFuture("Same verticle supplied more than once"));
            }
        } else {
            Verticle[] verticlesArray = (Verticle[]) verticles.toArray(new Verticle[verticles.size()]);
            String verticleClass = verticlesArray[0].getClass().getName();
            doDeploy("java:" + verticleClass, options, currentContext, currentContext, completionHandler, cl, verticlesArray);
        }
    }

    public void deployVerticle(String identifier, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler) {
        if (options.isMultiThreaded() && !options.isWorker()) {
            throw new IllegalArgumentException("If multi-threaded then must be worker too");
        }
        ContextInternal callingContext = this.vertx.getOrCreateContext();
        ClassLoader cl = getClassLoader(options);
        doDeployVerticle(identifier, options, callingContext, callingContext, cl, completionHandler);
    }

    private void doDeployVerticle(String identifier, DeploymentOptions options, ContextInternal parentContext, ContextInternal callingContext, ClassLoader cl, Handler<AsyncResult<String>> completionHandler) {
        List<VerticleFactory> verticleFactories = resolveFactories(identifier);
        Iterator<VerticleFactory> iter = verticleFactories.iterator();
        doDeployVerticle(iter, null, identifier, options, parentContext, callingContext, cl, completionHandler);
    }

    private void doDeployVerticle(Iterator<VerticleFactory> iter, Throwable prevErr, String identifier, DeploymentOptions options, ContextInternal parentContext, ContextInternal callingContext, ClassLoader cl, Handler<AsyncResult<String>> completionHandler) {
        if (iter.hasNext()) {
            VerticleFactory verticleFactory = iter.next();
            Promise<String> promise = Promise.promise();
            if (verticleFactory.requiresResolve()) {
                try {
                    verticleFactory.resolve(identifier, options, cl, promise);
                } catch (Exception e) {
                    try {
                        promise.fail(e);
                    } catch (Exception e2) {
                    }
                }
            } else {
                promise.complete(identifier);
            }
            promise.future().setHandler2(ar -> {
                Throwable err;
                if (ar.succeeded()) {
                    String resolvedName = (String) ar.result();
                    if (!resolvedName.equals(identifier)) {
                        try {
                            deployVerticle(resolvedName, options, (Handler<AsyncResult<String>>) completionHandler);
                            return;
                        } catch (Exception e3) {
                            if (completionHandler != null) {
                                completionHandler.handle(Future.failedFuture(e3));
                                return;
                            }
                            return;
                        }
                    }
                    if (verticleFactory.blockingCreate()) {
                        this.vertx.executeBlocking(createFut -> {
                            try {
                                Verticle[] verticles = createVerticles(verticleFactory, identifier, options.getInstances(), cl);
                                createFut.complete(verticles);
                            } catch (Exception e4) {
                                createFut.fail(e4);
                            }
                        }, res -> {
                            if (res.succeeded()) {
                                doDeploy(identifier, options, parentContext, callingContext, completionHandler, cl, (Verticle[]) res.result());
                            } else {
                                doDeployVerticle(iter, res.cause(), identifier, options, parentContext, callingContext, cl, completionHandler);
                            }
                        });
                        return;
                    }
                    try {
                        Verticle[] verticles = createVerticles(verticleFactory, identifier, options.getInstances(), cl);
                        doDeploy(identifier, options, parentContext, callingContext, completionHandler, cl, verticles);
                        return;
                    } catch (Exception e4) {
                        err = e4;
                    }
                } else {
                    err = ar.cause();
                }
                doDeployVerticle(iter, err, identifier, options, parentContext, callingContext, cl, completionHandler);
            });
            return;
        }
        if (prevErr != null) {
            reportFailure(prevErr, callingContext, completionHandler);
        }
    }

    private Verticle[] createVerticles(VerticleFactory verticleFactory, String identifier, int instances, ClassLoader cl) throws Exception {
        Verticle[] verticles = new Verticle[instances];
        for (int i = 0; i < instances; i++) {
            verticles[i] = verticleFactory.createVerticle(identifier, cl);
            if (verticles[i] == null) {
                throw new NullPointerException("VerticleFactory::createVerticle returned null");
            }
        }
        return verticles;
    }

    private String getSuffix(int pos, String str) {
        if (pos + 1 >= str.length()) {
            throw new IllegalArgumentException("Invalid name: " + str);
        }
        return str.substring(pos + 1);
    }

    public void undeployVerticle(String deploymentID, Handler<AsyncResult<Void>> completionHandler) {
        Deployment deployment = this.deployments.get(deploymentID);
        Context currentContext = this.vertx.getOrCreateContext();
        if (deployment == null) {
            reportFailure(new IllegalStateException("Unknown deployment"), currentContext, completionHandler);
        } else {
            deployment.undeploy(completionHandler);
        }
    }

    public Set<String> deployments() {
        return Collections.unmodifiableSet(this.deployments.keySet());
    }

    public Deployment getDeployment(String deploymentID) {
        return this.deployments.get(deploymentID);
    }

    public void undeployAll(Handler<AsyncResult<Void>> completionHandler) {
        Set<String> deploymentIDs = new HashSet<>();
        for (Map.Entry<String, Deployment> entry : this.deployments.entrySet()) {
            if (!entry.getValue().isChild()) {
                deploymentIDs.add(entry.getKey());
            }
        }
        if (!deploymentIDs.isEmpty()) {
            AtomicInteger count = new AtomicInteger(0);
            for (String deploymentID : deploymentIDs) {
                undeployVerticle(deploymentID, ar -> {
                    if (ar.failed()) {
                        log.error("Undeploy failed", ar.cause());
                    }
                    if (count.incrementAndGet() == deploymentIDs.size()) {
                        completionHandler.handle(Future.succeededFuture());
                    }
                });
            }
            return;
        }
        Context context = this.vertx.getOrCreateContext();
        context.runOnContext(v -> {
            completionHandler.handle(Future.succeededFuture());
        });
    }

    public void registerVerticleFactory(VerticleFactory factory) {
        String prefix = factory.prefix();
        if (prefix == null) {
            throw new IllegalArgumentException("factory.prefix() cannot be null");
        }
        List<VerticleFactory> facts = this.verticleFactories.get(prefix);
        if (facts == null) {
            facts = new ArrayList();
            this.verticleFactories.put(prefix, facts);
        }
        if (facts.contains(factory)) {
            throw new IllegalArgumentException("Factory already registered");
        }
        facts.add(factory);
        facts.sort((fact1, fact2) -> {
            return fact1.order() - fact2.order();
        });
        factory.init(this.vertx);
    }

    public void unregisterVerticleFactory(VerticleFactory factory) {
        String prefix = factory.prefix();
        if (prefix == null) {
            throw new IllegalArgumentException("factory.prefix() cannot be null");
        }
        List<VerticleFactory> facts = this.verticleFactories.get(prefix);
        boolean removed = false;
        if (facts != null) {
            if (facts.remove(factory)) {
                removed = true;
            }
            if (facts.isEmpty()) {
                this.verticleFactories.remove(prefix);
            }
        }
        if (!removed) {
            throw new IllegalArgumentException("factory isn't registered");
        }
    }

    public Set<VerticleFactory> verticleFactories() {
        Set<VerticleFactory> facts = new HashSet<>();
        for (List<VerticleFactory> list : this.verticleFactories.values()) {
            facts.addAll(list);
        }
        return facts;
    }

    private List<VerticleFactory> resolveFactories(String identifier) {
        List<VerticleFactory> factoryList = null;
        int pos = identifier.indexOf(58);
        String lookup = null;
        if (pos != -1) {
            lookup = identifier.substring(0, pos);
        } else {
            int pos2 = identifier.lastIndexOf(46);
            if (pos2 != -1) {
                lookup = getSuffix(pos2, identifier);
            } else {
                factoryList = this.defaultFactories;
            }
        }
        if (factoryList == null) {
            factoryList = this.verticleFactories.get(lookup);
            if (factoryList == null) {
                factoryList = this.defaultFactories;
            }
        }
        return factoryList;
    }

    private static URL mapToURL(String path) {
        try {
            return new URL(path);
        } catch (MalformedURLException e) {
            try {
                return new File(path).toURI().toURL();
            } catch (MalformedURLException e1) {
                throw new IllegalArgumentException(e1);
            }
        }
    }

    private static List<URL> extractCPFromProperty() {
        List<URL> classpathURLs = new ArrayList<>();
        String classpath = System.getProperty("java.class.path");
        if (Objects.nonNull(classpath)) {
            for (String path : classpath.split(File.pathSeparator)) {
                classpathURLs.add(mapToURL(path));
            }
        }
        return classpathURLs;
    }

    static List<URL> extractCPByManifest(ClassLoader current) throws IOException {
        List<URL> classpathURLs = new ArrayList<>();
        try {
            Enumeration<URL> urls = current.getResources("META-INF/MANIFEST.MF");
            Iterator it = Collections.list(urls).iterator();
            while (it.hasNext()) {
                URL url = (URL) it.next();
                String urlString = url.toExternalForm();
                if ("jar".equals(url.getProtocol().toLowerCase())) {
                    String suffix = "!/META-INF/MANIFEST.MF";
                    urlString = urlString.replace("jar:", "").replace(suffix, "").trim();
                }
                try {
                    classpathURLs.add(new URL(urlString.replace("META-INF/MANIFEST.MF", "").trim()));
                } catch (MalformedURLException e) {
                    throw new IllegalStateException(e);
                }
            }
            return classpathURLs;
        } catch (IOException e2) {
            throw new IllegalStateException(e2);
        }
    }

    static List<URL> extractClasspath(ClassLoader current) {
        if (current instanceof URLClassLoader) {
            URLClassLoader urlc = (URLClassLoader) current;
            return Arrays.asList(urlc.getURLs());
        }
        List<URL> classpathURLs = extractCPFromProperty();
        for (URL url : extractCPByManifest(current)) {
            if (!classpathURLs.contains(url)) {
                classpathURLs.add(url);
            }
        }
        return classpathURLs;
    }

    private ClassLoader getClassLoader(DeploymentOptions options) {
        ClassLoader cl;
        String isolationGroup = options.getIsolationGroup();
        if (isolationGroup == null) {
            cl = getCurrentClassLoader();
        } else {
            synchronized (this) {
                IsolatingClassLoader icl = this.classloaders.get(isolationGroup);
                if (icl == null) {
                    ClassLoader current = getCurrentClassLoader();
                    List<URL> urls = new ArrayList<>();
                    List<String> extraClasspath = options.getExtraClasspath();
                    if (extraClasspath != null) {
                        for (String pathElement : extraClasspath) {
                            urls.add(mapToURL(pathElement));
                        }
                    }
                    urls.addAll(extractClasspath(current));
                    icl = new IsolatingClassLoader((URL[]) urls.toArray(new URL[urls.size()]), getCurrentClassLoader(), options.getIsolatedClasses());
                    this.classloaders.put(isolationGroup, icl);
                }
                icl.refCount += options.getInstances();
                cl = icl;
            }
        }
        return cl;
    }

    private ClassLoader getCurrentClassLoader() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = getClass().getClassLoader();
        }
        return cl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void reportFailure(Throwable t, Context context, Handler<AsyncResult<T>> completionHandler) {
        if (completionHandler != null) {
            reportResult(context, completionHandler, Future.failedFuture(t));
        } else {
            log.error(t.getMessage(), t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void reportSuccess(T result, Context context, Handler<AsyncResult<T>> completionHandler) {
        if (completionHandler != null) {
            reportResult(context, completionHandler, Future.succeededFuture(result));
        }
    }

    private <T> void reportResult(Context context, Handler<AsyncResult<T>> completionHandler, AsyncResult<T> result) {
        context.runOnContext(v -> {
            try {
                completionHandler.handle(result);
            } catch (Throwable t) {
                log.error("Failure in calling handler", t);
                throw t;
            }
        });
    }

    private void doDeploy(String identifier, DeploymentOptions options, ContextInternal parentContext, ContextInternal callingContext, Handler<AsyncResult<String>> completionHandler, ClassLoader tccl, Verticle... verticles) {
        JsonObject conf = options.getConfig() == null ? new JsonObject() : options.getConfig().copy();
        String poolName = options.getWorkerPoolName();
        Deployment parent = parentContext.getDeployment();
        String deploymentID = generateDeploymentID();
        DeploymentImpl deployment = new DeploymentImpl(parent, deploymentID, identifier, options);
        AtomicInteger deployCount = new AtomicInteger();
        AtomicBoolean failureReported = new AtomicBoolean();
        for (Verticle verticle : verticles) {
            WorkerExecutorInternal workerExec = poolName != null ? this.vertx.createSharedWorkerExecutor(poolName, options.getWorkerPoolSize(), options.getMaxWorkerExecuteTime(), options.getMaxWorkerExecuteTimeUnit()) : null;
            WorkerPool pool = workerExec != null ? workerExec.getPool() : null;
            ContextImpl context = (ContextImpl) (options.isWorker() ? this.vertx.createWorkerContext(options.isMultiThreaded(), deploymentID, pool, conf, tccl) : this.vertx.createEventLoopContext(deploymentID, pool, conf, tccl));
            if (workerExec != null) {
                context.addCloseHook(workerExec);
            }
            context.setDeployment(deployment);
            deployment.addVerticle(new VerticleHolder(verticle, context));
            context.runOnContext(v -> {
                try {
                    verticle.init(this.vertx, context);
                    Promise<Void> startPromise = Promise.promise();
                    Future<Void> startFuture = startPromise.future();
                    verticle.start(startPromise);
                    startFuture.setHandler2(ar -> {
                        if (!ar.succeeded()) {
                            if (!failureReported.compareAndSet(false, true)) {
                                return;
                            }
                            deployment.rollback(callingContext, completionHandler, context, ar.cause());
                            return;
                        }
                        if (parent != null) {
                            if (!parent.addChild(deployment)) {
                                deployment.undeploy(event -> {
                                    reportFailure(new NoStackTraceThrowable("Verticle deployment failed.Could not be added as child of parent verticle"), context, completionHandler);
                                });
                                return;
                            }
                            deployment.child = true;
                        }
                        VertxMetrics metrics = this.vertx.metricsSPI();
                        if (metrics != null) {
                            metrics.verticleDeployed(verticle);
                        }
                        this.deployments.put(deploymentID, deployment);
                        if (deployCount.incrementAndGet() == verticles.length) {
                            reportSuccess(deploymentID, callingContext, completionHandler);
                        }
                    });
                } catch (Throwable t) {
                    if (!failureReported.compareAndSet(false, true)) {
                        return;
                    }
                    deployment.rollback(callingContext, completionHandler, context, t);
                }
            });
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/DeploymentManager$VerticleHolder.class */
    static class VerticleHolder {
        final Verticle verticle;
        final ContextImpl context;

        VerticleHolder(Verticle verticle, ContextImpl context) {
            this.verticle = verticle;
            this.context = context;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/DeploymentManager$DeploymentImpl.class */
    private class DeploymentImpl implements Deployment {
        private static final int ST_DEPLOYED = 0;
        private static final int ST_UNDEPLOYING = 1;
        private static final int ST_UNDEPLOYED = 2;
        private final Deployment parent;
        private final String deploymentID;
        private final String verticleIdentifier;
        private final List<VerticleHolder> verticles;
        private final Set<Deployment> children;
        private final DeploymentOptions options;
        private int status;
        private volatile boolean child;

        private DeploymentImpl(Deployment parent, String deploymentID, String verticleIdentifier, DeploymentOptions options) {
            this.verticles = new CopyOnWriteArrayList();
            this.children = new ConcurrentHashSet();
            this.status = 0;
            this.parent = parent;
            this.deploymentID = deploymentID;
            this.verticleIdentifier = verticleIdentifier;
            this.options = options;
        }

        public void addVerticle(VerticleHolder holder) {
            this.verticles.add(holder);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void rollback(ContextInternal callingContext, Handler<AsyncResult<String>> completionHandler, ContextImpl context, Throwable cause) {
            if (this.status == 0) {
                this.status = 1;
                doUndeployChildren(callingContext, childrenResult -> {
                    synchronized (this) {
                        this.status = 2;
                    }
                    if (childrenResult.failed()) {
                        DeploymentManager.this.reportFailure(cause, callingContext, completionHandler);
                    } else {
                        context.runCloseHooks(closeHookAsyncResult -> {
                            DeploymentManager.this.reportFailure(cause, callingContext, completionHandler);
                        });
                    }
                });
            }
        }

        @Override // io.vertx.core.impl.Deployment
        public void undeploy(Handler<AsyncResult<Void>> completionHandler) {
            ContextInternal currentContext = DeploymentManager.this.vertx.getOrCreateContext();
            doUndeploy(currentContext, completionHandler);
        }

        private synchronized void doUndeployChildren(ContextInternal undeployingContext, Handler<AsyncResult<Void>> completionHandler) {
            if (!this.children.isEmpty()) {
                int size = this.children.size();
                AtomicInteger childCount = new AtomicInteger();
                boolean undeployedSome = false;
                Iterator it = new HashSet(this.children).iterator();
                while (it.hasNext()) {
                    Deployment childDeployment = (Deployment) it.next();
                    undeployedSome = true;
                    childDeployment.doUndeploy(undeployingContext, ar -> {
                        this.children.remove(childDeployment);
                        if (ar.failed()) {
                            DeploymentManager.this.reportFailure(ar.cause(), undeployingContext, completionHandler);
                        } else if (childCount.incrementAndGet() == size) {
                            completionHandler.handle(Future.succeededFuture());
                        }
                    });
                }
                if (!undeployedSome) {
                    completionHandler.handle(Future.succeededFuture());
                    return;
                }
                return;
            }
            completionHandler.handle(Future.succeededFuture());
        }

        @Override // io.vertx.core.impl.Deployment
        public synchronized void doUndeploy(ContextInternal undeployingContext, Handler<AsyncResult<Void>> completionHandler) {
            if (this.status == 2) {
                DeploymentManager.this.reportFailure(new IllegalStateException("Already undeployed"), undeployingContext, completionHandler);
                return;
            }
            if (!this.children.isEmpty()) {
                this.status = 1;
                doUndeployChildren(undeployingContext, ar -> {
                    if (ar.failed()) {
                        DeploymentManager.this.reportFailure(ar.cause(), undeployingContext, completionHandler);
                    } else {
                        doUndeploy(undeployingContext, completionHandler);
                    }
                });
                return;
            }
            this.status = 2;
            AtomicInteger undeployCount = new AtomicInteger();
            int numToUndeploy = this.verticles.size();
            if (this.parent != null) {
                this.parent.removeChild(this);
            }
            for (VerticleHolder verticleHolder : this.verticles) {
                ContextImpl context = verticleHolder.context;
                context.runOnContext(v -> {
                    Promise<Void> stopPromise = Promise.promise();
                    Future<Void> stopFuture = stopPromise.future();
                    AtomicBoolean failureReported = new AtomicBoolean();
                    stopFuture.setHandler2(ar2 -> {
                        DeploymentManager.this.deployments.remove(this.deploymentID);
                        VertxMetrics metrics = DeploymentManager.this.vertx.metricsSPI();
                        if (metrics != null) {
                            metrics.verticleUndeployed(verticleHolder.verticle);
                        }
                        context.runCloseHooks(ar2 -> {
                            if (ar2.failed()) {
                                DeploymentManager.log.error("Failed to run close hook", ar2.cause());
                            }
                            String group = this.options.getIsolationGroup();
                            if (group != null) {
                                synchronized (DeploymentManager.this) {
                                    IsolatingClassLoader icl = (IsolatingClassLoader) DeploymentManager.this.classloaders.get(group);
                                    int i = icl.refCount - 1;
                                    icl.refCount = i;
                                    if (i == 0) {
                                        DeploymentManager.this.classloaders.remove(group);
                                        try {
                                            icl.close();
                                        } catch (IOException e) {
                                            DeploymentManager.log.debug("Issue when closing isolation group loader", e);
                                        }
                                    }
                                }
                            }
                            if (ar2.succeeded() && undeployCount.incrementAndGet() == numToUndeploy) {
                                DeploymentManager.this.reportSuccess(null, undeployingContext, completionHandler);
                            } else if (ar2.failed() && !failureReported.get()) {
                                failureReported.set(true);
                                DeploymentManager.this.reportFailure(ar2.cause(), undeployingContext, completionHandler);
                            }
                        });
                    });
                    try {
                        verticleHolder.verticle.stop(stopPromise);
                    } catch (Throwable t) {
                        if (!stopPromise.tryFail(t)) {
                            undeployingContext.reportException(t);
                        }
                    }
                });
            }
        }

        @Override // io.vertx.core.impl.Deployment
        public String verticleIdentifier() {
            return this.verticleIdentifier;
        }

        @Override // io.vertx.core.impl.Deployment
        public DeploymentOptions deploymentOptions() {
            return this.options;
        }

        @Override // io.vertx.core.impl.Deployment
        public synchronized boolean addChild(Deployment deployment) {
            if (this.status == 0) {
                this.children.add(deployment);
                return true;
            }
            return false;
        }

        @Override // io.vertx.core.impl.Deployment
        public void removeChild(Deployment deployment) {
            this.children.remove(deployment);
        }

        @Override // io.vertx.core.impl.Deployment
        public Set<Verticle> getVerticles() {
            Set<Verticle> verts = new HashSet<>();
            for (VerticleHolder holder : this.verticles) {
                verts.add(holder.verticle);
            }
            return verts;
        }

        @Override // io.vertx.core.impl.Deployment
        public boolean isChild() {
            return this.child;
        }

        @Override // io.vertx.core.impl.Deployment
        public String deploymentID() {
            return this.deploymentID;
        }
    }
}
