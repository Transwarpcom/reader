package io.vertx.core;

import io.vertx.core.impl.Args;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.spi.VertxMetricsFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.ServiceLoader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/Starter.class */
public class Starter {
    public static final String VERTX_OPTIONS_PROP_PREFIX = "vertx.options.";
    public static final String DEPLOYMENT_OPTIONS_PROP_PREFIX = "vertx.deployment.options.";
    public static final String METRICS_OPTIONS_PROP_PREFIX = "vertx.metrics.options.";
    private static final String PATH_SEP = System.getProperty("path.separator");
    private static final Logger log = LoggerFactory.getLogger((Class<?>) Starter.class);
    public static List<String> PROCESS_ARGS;
    protected Vertx vertx;
    protected VertxOptions options;
    protected DeploymentOptions deploymentOptions;

    /* JADX WARN: Finally extract failed */
    public static void main(String[] sargs) throws IllegalAccessException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Args args = new Args(sargs);
        String extraCP = args.map.get("-cp");
        if (extraCP != null) {
            String[] parts = extraCP.split(PATH_SEP);
            URL[] urls = new URL[parts.length];
            for (int p = 0; p < parts.length; p++) {
                String part = parts[p];
                File file = new File(part);
                try {
                    URL url = file.toURI().toURL();
                    urls[p] = url;
                } catch (MalformedURLException e) {
                    throw new IllegalStateException(e);
                }
            }
            ClassLoader icl = new URLClassLoader(urls, Starter.class.getClassLoader());
            ClassLoader oldTCCL = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(icl);
            try {
                try {
                    Class<?> clazz = icl.loadClass(Starter.class.getName());
                    Object instance = clazz.newInstance();
                    Method run = clazz.getMethod("run", Args.class, String[].class);
                    run.invoke(instance, args, sargs);
                    Thread.currentThread().setContextClassLoader(oldTCCL);
                    return;
                } catch (Exception e2) {
                    throw new IllegalStateException(e2);
                }
            } catch (Throwable th) {
                Thread.currentThread().setContextClassLoader(oldTCCL);
                throw th;
            }
        }
        new Starter().run(args, sargs);
    }

    public static void runCommandLine(String commandLine) throws IllegalAccessException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        new Starter().run(commandLine);
    }

    protected void run(String commandLine) throws IllegalAccessException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        String[] sargs = commandLine.split(" ");
        Args args = new Args(sargs);
        run(args, sargs);
    }

    protected void run(String[] sargs) throws IllegalAccessException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        run(new Args(sargs), sargs);
    }

    public void run(Args args, String[] sargs) throws IllegalAccessException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        PROCESS_ARGS = Collections.unmodifiableList(Arrays.asList(sargs));
        String main = readMainVerticleFromManifest();
        if (main != null) {
            runVerticle(main, args);
            return;
        }
        if (sargs.length > 0) {
            String first = sargs[0];
            if (first.equals("-version")) {
                log.info(getVersion());
                return;
            }
            if (first.equals("run")) {
                if (sargs.length < 2) {
                    displaySyntax();
                    return;
                } else {
                    runVerticle(sargs[1], args);
                    return;
                }
            }
            if (first.equals("-ha")) {
                runBare(args);
                return;
            }
        }
        displaySyntax();
    }

    protected void beforeStartingVertx(VertxOptions options) {
    }

    protected void afterStartingVertx() {
    }

    protected void beforeDeployingVerticle(DeploymentOptions deploymentOptions) {
    }

    protected void handleDeployFailed() {
        this.vertx.close();
    }

    private Vertx startVertx(boolean clustered, boolean ha, Args args) throws IllegalAccessException, SocketException, SecurityException, IllegalArgumentException, InvocationTargetException {
        MetricsOptions metricsOptions;
        ServiceLoader<VertxMetricsFactory> factories = ServiceLoader.load(VertxMetricsFactory.class);
        if (factories.iterator().hasNext()) {
            VertxMetricsFactory factory = factories.iterator().next();
            metricsOptions = factory.newOptions();
        } else {
            metricsOptions = new MetricsOptions();
        }
        configureFromSystemProperties(metricsOptions, "vertx.metrics.options.");
        this.options = new VertxOptions().setMetricsOptions(metricsOptions);
        configureFromSystemProperties(this.options, "vertx.options.");
        if (clustered) {
            log.info("Starting clustering...");
            int clusterPort = args.getInt("-cluster-port");
            if (clusterPort == -1) {
                clusterPort = 0;
            }
            String clusterHost = args.map.get("-cluster-host");
            if (clusterHost == null) {
                clusterHost = getDefaultAddress();
                if (clusterHost == null) {
                    log.error("Unable to find a default network interface for clustering. Please specify one using -cluster-host");
                    return null;
                }
                log.info("No cluster-host specified so using address " + clusterHost);
            }
            CountDownLatch latch = new CountDownLatch(1);
            AtomicReference<AsyncResult<Vertx>> result = new AtomicReference<>();
            this.options.getEventBusOptions().setClustered(true).setHost(clusterHost).setPort(clusterPort);
            if (ha) {
                String haGroup = args.map.get("-hagroup");
                int quorumSize = args.getInt("-quorum");
                this.options.setHAEnabled(true);
                if (haGroup != null) {
                    this.options.setHAGroup(haGroup);
                }
                if (quorumSize != -1) {
                    this.options.setQuorumSize(quorumSize);
                }
            }
            beforeStartingVertx(this.options);
            Vertx.clusteredVertx(this.options, ar -> {
                result.set(ar);
                latch.countDown();
            });
            try {
                if (!latch.await(2L, TimeUnit.MINUTES)) {
                    log.error("Timed out in starting clustered Vert.x");
                    return null;
                }
                if (result.get().failed()) {
                    log.error("Failed to form cluster");
                    result.get().cause().printStackTrace();
                    return null;
                }
                this.vertx = result.get().result();
            } catch (InterruptedException e) {
                log.error("Thread interrupted in startup");
                return null;
            }
        } else {
            beforeStartingVertx(this.options);
            this.vertx = Vertx.vertx(this.options);
        }
        addShutdownHook();
        afterStartingVertx();
        return this.vertx;
    }

    private void runBare(Args args) throws IllegalAccessException, SocketException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Vertx vertx = startVertx(true, true, args);
        if (vertx == null) {
        }
    }

    private void runVerticle(String main, Args args) throws IllegalAccessException, SocketException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int instances;
        JsonObject conf;
        boolean ha = args.map.get("-ha") != null;
        boolean clustered = args.map.get("-cluster") != null || ha;
        Vertx vertx = startVertx(clustered, ha, args);
        if (vertx == null) {
            return;
        }
        String sinstances = args.map.get("-instances");
        if (sinstances != null) {
            try {
                instances = Integer.parseInt(sinstances);
                if (instances != -1 && instances < 1) {
                    log.error("Invalid number of instances");
                    displaySyntax();
                    return;
                }
            } catch (NumberFormatException e) {
                displaySyntax();
                return;
            }
        } else {
            instances = 1;
        }
        String confArg = args.map.get("-conf");
        if (confArg != null) {
            try {
                Scanner scanner = new Scanner(new File(confArg)).useDelimiter("\\A");
                Throwable th = null;
                try {
                    try {
                        String sconf = scanner.next();
                        try {
                            conf = new JsonObject(sconf);
                            if (scanner != null) {
                                if (0 != 0) {
                                    try {
                                        scanner.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                } else {
                                    scanner.close();
                                }
                            }
                        } catch (DecodeException e2) {
                            log.error("Configuration file " + sconf + " does not contain a valid JSON object");
                            if (scanner != null) {
                                if (0 == 0) {
                                    scanner.close();
                                    return;
                                }
                                try {
                                    scanner.close();
                                    return;
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                    return;
                                }
                            }
                            return;
                        }
                    } finally {
                    }
                } catch (Throwable th4) {
                    th = th4;
                    throw th4;
                }
            } catch (FileNotFoundException e3) {
                try {
                    conf = new JsonObject(confArg);
                } catch (DecodeException e4) {
                    log.error("-conf option does not point to a file and is not valid JSON: " + confArg);
                    return;
                }
            }
        } else {
            conf = null;
        }
        boolean worker = args.map.get("-worker") != null;
        String message = worker ? "deploying worker verticle" : "deploying verticle";
        this.deploymentOptions = new DeploymentOptions();
        configureFromSystemProperties(this.deploymentOptions, "vertx.deployment.options.");
        this.deploymentOptions.setConfig(conf).setWorker(worker).setHa(ha).setInstances(instances);
        beforeDeployingVerticle(this.deploymentOptions);
        vertx.deployVerticle(main, this.deploymentOptions, createLoggingHandler(message, res -> {
            if (res.failed()) {
                handleDeployFailed();
            }
        }));
    }

    private <T> Handler<AsyncResult<T>> createLoggingHandler(String message, Handler<AsyncResult<T>> completionHandler) {
        return res -> {
            if (res.failed()) {
                Throwable cause = res.cause();
                if (cause instanceof VertxException) {
                    VertxException ve = (VertxException) cause;
                    log.error(ve.getMessage());
                    if (ve.getCause() != null) {
                        log.error(ve.getCause());
                    }
                } else {
                    log.error("Failed in " + message, cause);
                }
            } else {
                log.info("Succeeded in " + message);
            }
            if (completionHandler != null) {
                completionHandler.handle(res);
            }
        };
    }

    private void configureFromSystemProperties(Object options, String prefix) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Object arg;
        Properties props = System.getProperties();
        Enumeration e = props.propertyNames();
        while (e.hasMoreElements()) {
            String propName = (String) e.nextElement();
            String propVal = props.getProperty(propName);
            if (propName.startsWith(prefix)) {
                String fieldName = propName.substring(prefix.length());
                Method setter = getSetter(fieldName, options.getClass());
                if (setter == null) {
                    log.warn("No such property to configure on options: " + options.getClass().getName() + "." + fieldName);
                } else {
                    Class<?> argType = setter.getParameterTypes()[0];
                    try {
                        if (argType.equals(String.class)) {
                            arg = propVal;
                        } else if (argType.equals(Integer.TYPE)) {
                            arg = Integer.valueOf(propVal);
                        } else if (argType.equals(Long.TYPE)) {
                            arg = Long.valueOf(propVal);
                        } else if (argType.equals(Boolean.TYPE)) {
                            arg = Boolean.valueOf(propVal);
                        } else if (argType.isEnum()) {
                            arg = Enum.valueOf(argType, propVal);
                        } else {
                            log.warn("Invalid type for setter: " + argType);
                        }
                        try {
                            setter.invoke(options, arg);
                        } catch (Exception ex) {
                            throw new VertxException("Failed to invoke setter: " + setter, ex);
                        }
                    } catch (IllegalArgumentException e2) {
                        log.warn("Invalid argtype:" + argType + " on options: " + options.getClass().getName() + "." + fieldName);
                    }
                }
            }
        }
    }

    private Method getSetter(String fieldName, Class<?> clazz) throws SecurityException {
        Method[] meths = clazz.getDeclaredMethods();
        for (Method meth : meths) {
            if (("set" + fieldName).toLowerCase().equals(meth.getName().toLowerCase())) {
                return meth;
            }
        }
        return null;
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() { // from class: io.vertx.core.Starter.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                CountDownLatch latch = new CountDownLatch(1);
                Starter.this.vertx.close(ar -> {
                    if (!ar.succeeded()) {
                        Starter.log.error("Failure in stopping Vert.x", ar.cause());
                    }
                    latch.countDown();
                });
                try {
                    if (!latch.await(2L, TimeUnit.MINUTES)) {
                        Starter.log.error("Timed out waiting to undeploy all");
                    }
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        });
    }

    private String getDefaultAddress() throws SocketException {
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            while (nets.hasMoreElements()) {
                NetworkInterface netinf = nets.nextElement();
                Enumeration<InetAddress> addresses = netinf.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (!address.isAnyLocalAddress() && !address.isMulticastAddress() && !(address instanceof Inet6Address)) {
                        return address.getHostAddress();
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            return null;
        }
    }

    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r6v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r7v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x00b8: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('is' java.io.InputStream)]) A[TRY_LEAVE], block:B:48:0x00b8 */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x00bc: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:50:0x00bc */
    /* JADX WARN: Type inference failed for: r6v0, names: [is], types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.Throwable] */
    public String getVersion() throws IOException {
        try {
            try {
                InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("META-INF/vertx/vertx-version.txt");
                Throwable th = null;
                if (resourceAsStream == null) {
                    throw new IllegalStateException("Cannot find vertx-version.txt on classpath");
                }
                Scanner scannerUseDelimiter = new Scanner(resourceAsStream, "UTF-8").useDelimiter("\\A");
                Throwable th2 = null;
                try {
                    try {
                        String next = scannerUseDelimiter.hasNext() ? scannerUseDelimiter.next() : "";
                        if (scannerUseDelimiter != null) {
                            if (0 != 0) {
                                try {
                                    scannerUseDelimiter.close();
                                } catch (Throwable th3) {
                                    th2.addSuppressed(th3);
                                }
                            } else {
                                scannerUseDelimiter.close();
                            }
                        }
                        if (resourceAsStream != null) {
                            if (0 != 0) {
                                try {
                                    resourceAsStream.close();
                                } catch (Throwable th4) {
                                    th.addSuppressed(th4);
                                }
                            } else {
                                resourceAsStream.close();
                            }
                        }
                        return next;
                    } catch (Throwable th5) {
                        if (scannerUseDelimiter != null) {
                            if (th2 != null) {
                                try {
                                    scannerUseDelimiter.close();
                                } catch (Throwable th6) {
                                    th2.addSuppressed(th6);
                                }
                            } else {
                                scannerUseDelimiter.close();
                            }
                        }
                        throw th5;
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private String readMainVerticleFromManifest() throws IOException {
        String theMainVerticle;
        try {
            Enumeration<URL> resources = getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                InputStream stream = null;
                try {
                    stream = resources.nextElement().openStream();
                    Manifest manifest = new Manifest(stream);
                    Attributes attributes = manifest.getMainAttributes();
                    String mainClass = attributes.getValue("Main-Class");
                    if (!Starter.class.getName().equals(mainClass) || (theMainVerticle = attributes.getValue("Main-Verticle")) == null) {
                        closeQuietly(stream);
                    } else {
                        closeQuietly(stream);
                        return theMainVerticle;
                    }
                } catch (Throwable th) {
                    closeQuietly(stream);
                    throw th;
                }
            }
            return null;
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private void closeQuietly(InputStream stream) throws IOException {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
            }
        }
    }

    private void displaySyntax() {
        log.info("    vertx run <main> [-options]                                                \n        runs a verticle called <main> in its own instance of vert.x.         \n\n    valid options are:                                                         \n        -conf <config>         Specifies configuration that should be provided \n                               to the verticle. <config> should reference      \n                               either a text file containing a valid JSON      \n                               object which represents the configuration OR    \n                               be a JSON string.                               \n        -instances <instances> specifies how many instances of the verticle    \n                               will be deployed. Defaults to 1                 \n        -worker                if specified then the verticle is a worker      \n                               verticle.                                       \n        -cp <classpath>        provide an extra classpath to be used for the   \n                               verticle deployment.                            \n        -cluster               if specified then the vert.x instance will form \n                               a cluster with any other vert.x instances on    \n                               the network.                                    \n        -cluster-port          port to use for cluster communication.          \n                               Default is 0 which means choose a spare         \n                               random port.                                    \n        -cluster-host          host to bind to for cluster communication.      \n                               If this is not specified vert.x will attempt    \n                               to choose one from the available interfaces.    \n        -ha                    if specified the verticle will be deployed as a \n                               high availability (HA) deployment.              \n                               This means it can fail over to any other nodes  \n                               in the cluster started with the same HA group   \n        -quorum                used in conjunction with -ha this specifies the \n                               minimum number of nodes in the cluster for any  \n                               HA deploymentIDs to be active. Defaults to 0    \n        -hagroup               used in conjunction with -ha this specifies the \n                               HA group this node will join. There can be      \n                               multiple HA groups in a cluster. Nodes will only\n                               failover to other nodes in the same group.      \n                               Defaults to __DEFAULT__                       \n\n    vertx -version                                                             \n        displays the version");
    }
}
