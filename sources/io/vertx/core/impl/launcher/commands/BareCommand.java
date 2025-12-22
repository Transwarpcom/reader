package io.vertx.core.impl.launcher.commands;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.ServiceHelper;
import io.vertx.core.Vertx;
import io.vertx.core.VertxException;
import io.vertx.core.VertxOptions;
import io.vertx.core.cli.annotations.DefaultValue;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.impl.launcher.VertxLifecycleHooks;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.spi.VertxMetricsFactory;
import io.vertx.core.spi.launcher.ExecutionContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.pdfbox.contentstream.operator.OperatorName;

@Summary("Creates a bare instance of vert.x.")
@Description("This command launches a vert.x instance but do not deploy any verticles. It will receive a verticle if another node of the cluster dies.")
@Name("bare")
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/BareCommand.class */
public class BareCommand extends ClasspathHandler {
    public static final String VERTX_OPTIONS_PROP_PREFIX = "vertx.options.";
    public static final String DEPLOYMENT_OPTIONS_PROP_PREFIX = "vertx.deployment.options.";
    public static final String METRICS_OPTIONS_PROP_PREFIX = "vertx.metrics.options.";
    protected Vertx vertx;
    protected int clusterPort;
    protected String clusterHost;
    protected int clusterPublicPort;
    protected String clusterPublicHost;
    protected int quorum;
    protected String haGroup;
    protected String vertxOptions;
    protected VertxOptions options;
    protected Runnable finalAction;

    @Option(longName = "quorum", argName = OperatorName.SAVE)
    @Description("Used in conjunction with -ha this specifies the minimum number of nodes in the cluster for any HA deploymentIDs to be active. Defaults to 1.")
    @DefaultValue("-1")
    public void setQuorum(int quorum) {
        this.quorum = quorum;
    }

    @Option(longName = "hagroup", argName = "group")
    @Description("used in conjunction with -ha this specifies the HA group this node will join. There can be multiple HA groups in a cluster. Nodes will only failover to other nodes in the same group. Defaults to '__DEFAULT__'.")
    @DefaultValue(VertxOptions.DEFAULT_HA_GROUP)
    public void setHAGroup(String group) {
        this.haGroup = group;
    }

    @Option(longName = "cluster-port", argName = RtspHeaders.Values.PORT)
    @Description("Port to use for cluster communication. Default is 0 which means choose a spare random port.")
    @DefaultValue("0")
    public void setClusterPort(int port) {
        this.clusterPort = port;
    }

    @Option(longName = "cluster-host", argName = "host")
    @Description("host to bind to for cluster communication. If this is not specified vert.x will attempt to choose one from the available interfaces.")
    public void setClusterHost(String host) {
        this.clusterHost = host;
    }

    @Option(longName = "cluster-public-port", argName = "public-port")
    @Description("Public port to use for cluster communication. Default is -1 which means same as cluster port.")
    @DefaultValue("-1")
    public void setClusterPublicPort(int port) {
        this.clusterPublicPort = port;
    }

    @Option(longName = "cluster-public-host", argName = "public-host")
    @Description("Public host to bind to for cluster communication. If not specified, Vert.x will use the same as cluster host.")
    public void setClusterPublicHost(String host) {
        this.clusterPublicHost = host;
    }

    @Option(longName = "options", argName = "options")
    @Description("Specifies the Vert.x options. It should reference either a JSON file which represents the options OR be a JSON string.")
    public void setVertxOptions(String vertxOptions) {
        if (vertxOptions != null) {
            this.vertxOptions = vertxOptions.trim().replaceAll("^\"|\"$", "").replaceAll("^'|'$", "");
        } else {
            this.vertxOptions = null;
        }
    }

    public boolean isClustered() {
        return true;
    }

    public boolean getHA() {
        return true;
    }

    @Override // io.vertx.core.spi.launcher.Command
    public void run() {
        run(null);
    }

    public void run(Runnable action) {
        this.finalAction = action;
        this.vertx = startVertx();
    }

    protected Vertx startVertx() throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Vertx instance;
        JsonObject optionsJson = getJsonFromFileOrString(this.vertxOptions, "options");
        if (optionsJson == null) {
            MetricsOptions metricsOptions = getMetricsOptions();
            this.options = new VertxOptions().setMetricsOptions(metricsOptions);
        } else {
            MetricsOptions metricsOptions2 = getMetricsOptions(optionsJson.getJsonObject("metricsOptions"));
            this.options = new VertxOptions(optionsJson).setMetricsOptions(metricsOptions2);
        }
        configureFromSystemProperties(this.options, "vertx.options.");
        beforeStartingVertx(this.options);
        if (isClustered()) {
            this.log.info("Starting clustering...");
            EventBusOptions eventBusOptions = this.options.getEventBusOptions();
            if (!Objects.equals(eventBusOptions.getHost(), "localhost")) {
                this.clusterHost = eventBusOptions.getHost();
            }
            if (eventBusOptions.getPort() != 0) {
                this.clusterPort = eventBusOptions.getPort();
            }
            if (!Objects.equals(eventBusOptions.getClusterPublicHost(), EventBusOptions.DEFAULT_CLUSTER_PUBLIC_HOST)) {
                this.clusterPublicHost = eventBusOptions.getClusterPublicHost();
            }
            if (eventBusOptions.getClusterPublicPort() != -1) {
                this.clusterPublicPort = eventBusOptions.getClusterPublicPort();
            }
            if (this.clusterHost == null) {
                this.clusterHost = getDefaultAddress();
                if (this.clusterHost == null) {
                    this.log.error("Unable to find a default network interface for clustering. Please specify one using -cluster-host");
                    return null;
                }
                this.log.info("No cluster-host specified so using address " + this.clusterHost);
            }
            CountDownLatch latch = new CountDownLatch(1);
            AtomicReference<AsyncResult<Vertx>> result = new AtomicReference<>();
            eventBusOptions.setClustered(true).setHost(this.clusterHost).setPort(this.clusterPort).setClusterPublicHost(this.clusterPublicHost);
            if (this.clusterPublicPort != -1) {
                eventBusOptions.setClusterPublicPort(this.clusterPublicPort);
            }
            if (getHA()) {
                this.options.setHAEnabled(true);
                if (this.haGroup != null) {
                    this.options.setHAGroup(this.haGroup);
                }
                if (this.quorum != -1) {
                    this.options.setQuorumSize(this.quorum);
                }
            }
            create(this.options, ar -> {
                result.set(ar);
                latch.countDown();
            });
            try {
                if (!latch.await(2L, TimeUnit.MINUTES)) {
                    this.log.error("Timed out in starting clustered Vert.x");
                    return null;
                }
                if (result.get().failed()) {
                    this.log.error("Failed to form cluster");
                    result.get().cause().printStackTrace();
                    return null;
                }
                instance = result.get().result();
            } catch (InterruptedException e) {
                this.log.error("Thread interrupted in startup");
                Thread.currentThread().interrupt();
                return null;
            }
        } else {
            instance = create(this.options);
        }
        addShutdownHook(instance, this.log, this.finalAction);
        afterStartingVertx(instance);
        return instance;
    }

    protected JsonObject getJsonFromFileOrString(String jsonFileOrString, String argName) {
        JsonObject conf;
        if (jsonFileOrString != null) {
            try {
                Scanner scanner = new Scanner(new File(jsonFileOrString), "UTF-8").useDelimiter("\\A");
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
                        } catch (DecodeException e) {
                            this.log.error("Configuration file " + sconf + " does not contain a valid JSON object");
                            if (scanner != null) {
                                if (0 != 0) {
                                    try {
                                        scanner.close();
                                    } catch (Throwable th3) {
                                        th.addSuppressed(th3);
                                    }
                                } else {
                                    scanner.close();
                                }
                            }
                            return null;
                        }
                    } finally {
                    }
                } finally {
                }
            } catch (FileNotFoundException e2) {
                try {
                    conf = new JsonObject(jsonFileOrString);
                } catch (DecodeException e22) {
                    this.log.error("The -" + argName + " argument does not point to an existing file or is not a valid JSON object");
                    e22.printStackTrace();
                    return null;
                }
            }
        } else {
            conf = null;
        }
        return conf;
    }

    protected void afterStartingVertx(Vertx instance) {
        Object main = this.executionContext.main();
        if (main instanceof VertxLifecycleHooks) {
            ((VertxLifecycleHooks) main).afterStartingVertx(instance);
        }
    }

    protected void beforeStartingVertx(VertxOptions options) {
        Object main = this.executionContext.main();
        if (main instanceof VertxLifecycleHooks) {
            ((VertxLifecycleHooks) main).beforeStartingVertx(options);
        }
    }

    protected MetricsOptions getMetricsOptions() {
        return getMetricsOptions(null);
    }

    protected MetricsOptions getMetricsOptions(JsonObject jsonObject) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        MetricsOptions metricsOptions;
        VertxMetricsFactory factory = (VertxMetricsFactory) ServiceHelper.loadFactoryOrNull(VertxMetricsFactory.class);
        if (factory != null) {
            metricsOptions = jsonObject == null ? factory.newOptions() : factory.newOptions(jsonObject);
        } else {
            metricsOptions = jsonObject == null ? new MetricsOptions() : new MetricsOptions(jsonObject);
        }
        configureFromSystemProperties(metricsOptions, "vertx.metrics.options.");
        return metricsOptions;
    }

    protected void configureFromSystemProperties(Object options, String prefix) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
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
                    this.log.warn("No such property to configure on options: " + options.getClass().getName() + "." + fieldName);
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
                            this.log.warn("Invalid type for setter: " + argType);
                        }
                        try {
                            setter.invoke(options, arg);
                        } catch (Exception ex) {
                            throw new VertxException("Failed to invoke setter: " + setter, ex);
                        }
                    } catch (IllegalArgumentException e2) {
                        this.log.warn("Invalid argtype:" + argType + " on options: " + options.getClass().getName() + "." + fieldName);
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
        Method[] meths2 = clazz.getMethods();
        for (Method meth2 : meths2) {
            if (("set" + fieldName).toLowerCase().equals(meth2.getName().toLowerCase())) {
                return meth2;
            }
        }
        return null;
    }

    protected static void addShutdownHook(Vertx vertx, Logger log, Runnable action) {
        Runtime.getRuntime().addShutdownHook(new Thread(getTerminationRunnable(vertx, log, action)));
    }

    public static Runnable getTerminationRunnable(Vertx vertx, Logger log, Runnable action) {
        return () -> {
            CountDownLatch latch = new CountDownLatch(1);
            if (vertx != null) {
                vertx.close(ar -> {
                    if (!ar.succeeded()) {
                        log.error("Failure in stopping Vert.x", ar.cause());
                    }
                    latch.countDown();
                });
                try {
                    if (!latch.await(2L, TimeUnit.MINUTES)) {
                        log.error("Timed out waiting to undeploy all");
                    }
                    if (action != null) {
                        action.run();
                    }
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }

    protected String getDefaultAddress() throws SocketException {
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

    public void setExecutionContext(ExecutionContext context) {
        this.executionContext = context;
    }

    public synchronized Vertx vertx() {
        return this.vertx;
    }
}
