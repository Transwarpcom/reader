package io.vertx.core.impl.launcher.commands;

import io.legado.app.constant.Action;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.annotations.Argument;
import io.vertx.core.cli.annotations.DefaultValue;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Hidden;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.cli.annotations.ParsedAsList;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.launcher.VertxLifecycleHooks;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.launcher.ExecutionContext;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;

@Name("run")
@Summary("Runs a verticle called <main-verticle> in its own instance of vert.x.")
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/RunCommand.class */
public class RunCommand extends BareCommand {
    protected DeploymentOptions deploymentOptions;
    protected boolean cluster;
    protected boolean ha;
    protected int instances;
    protected String config;
    protected boolean worker;
    protected String mainVerticle;
    protected List<String> redeploy;
    protected String vertxApplicationBackgroundId;
    protected String onRedeployCommand;
    protected Watcher watcher;
    private long redeployScanPeriod;
    private long redeployGracePeriod;
    private long redeployTerminationPeriod;

    @Option(longName = "ha", acceptValue = false, flag = true)
    @Description("If specified the verticle will be deployed as a high availability (HA) deployment. This means it can fail over to any other nodes in the cluster started with the same HA group.")
    public void setHighAvailability(boolean ha) {
        this.ha = ha;
    }

    @Option(longName = "cluster", acceptValue = false, flag = true)
    @Description("If specified then the vert.x instance will form a cluster with any other vert.x instances on the network.")
    public void setCluster(boolean cluster) {
        this.cluster = cluster;
    }

    @Option(longName = "worker", acceptValue = false)
    @Description("If specified then the verticle is a worker verticle.")
    public void setWorker(boolean worker) {
        this.worker = worker;
    }

    @Option(longName = "instances", argName = "instances")
    @Description("Specifies how many instances of the verticle will be deployed. Defaults to 1.")
    @DefaultValue(CustomBooleanEditor.VALUE_1)
    public void setInstances(int instances) {
        this.instances = instances;
    }

    @Option(longName = "conf", argName = "config")
    @Description("Specifies configuration that should be provided to the verticle. <config> should reference either a text file containing a valid JSON object which represents the configuration OR be a JSON string.")
    public void setConfig(String configuration) {
        if (configuration != null) {
            this.config = configuration.trim().replaceAll("^\"|\"$", "").replaceAll("^'|'$", "");
        } else {
            this.config = null;
        }
    }

    @Argument(index = 0, argName = "main-verticle", required = true)
    @Description("The main verticle to deploy, it can be a fully qualified class name or a file.")
    public void setMainVerticle(String verticle) {
        this.mainVerticle = verticle;
    }

    @Option(longName = "redeploy", argName = "includes")
    @Description("Enable automatic redeployment of the application. This option takes a set on includes as parameter indicating which files need to be watched. Patterns are separated by a comma.")
    @ParsedAsList
    public void setRedeploy(List<String> redeploy) {
        this.redeploy = redeploy;
    }

    @Deprecated
    @Option(longName = "onRedeploy", argName = "cmd")
    @Description("Optional shell command executed when a redeployment is triggered (deprecated - will be removed in 3.3, use 'on-redeploy' instead")
    @Hidden
    public void setOnRedeployCommandOld(String command) {
        this.out.println("[WARNING] the 'onRedeploy' option is deprecated, and will be removed in vert.x 3.3. Use 'on-redeploy' instead.");
        setOnRedeployCommand(command);
    }

    @Option(longName = "on-redeploy", argName = "cmd")
    @Description("Optional shell command executed when a redeployment is triggered")
    public void setOnRedeployCommand(String command) {
        this.onRedeployCommand = command;
    }

    @Option(longName = "redeploy-scan-period", argName = "period")
    @Description("When redeploy is enabled, this option configures the file system scanning period to detect file changes. The time is given in milliseconds. 250 ms by default.")
    @DefaultValue("250")
    public void setRedeployScanPeriod(long period) {
        this.redeployScanPeriod = period;
    }

    @Option(longName = "redeploy-grace-period", argName = "period")
    @Description("When redeploy is enabled, this option configures the grace period between 2 redeployments. The time is given in milliseconds. 1000 ms by default.")
    @DefaultValue("1000")
    public void setRedeployGracePeriod(long period) {
        this.redeployGracePeriod = period;
    }

    @Option(longName = "redeploy-termination-period", argName = "period")
    @Description("When redeploy is enabled, this option configures the time waited to be sure that the previous version of the application has been stopped. It is useful on Windows, where the 'terminate' command may take time to be executed.The time is given in milliseconds. 0 ms by default.")
    @DefaultValue("0")
    public void setRedeployStopWaitingTime(long period) {
        this.redeployTerminationPeriod = period;
    }

    @Override // io.vertx.core.spi.launcher.DefaultCommand, io.vertx.core.spi.launcher.Command
    public void setUp(ExecutionContext context) throws CLIException {
        super.setUp(context);
        CommandLine commandLine = this.executionContext.commandLine();
        if (!isClustered() && (commandLine.isOptionAssigned(this.executionContext.cli().getOption("cluster-host")) || commandLine.isOptionAssigned(this.executionContext.cli().getOption("cluster-port")) || commandLine.isOptionAssigned(this.executionContext.cli().getOption("cluster-public-host")) || commandLine.isOptionAssigned(this.executionContext.cli().getOption("cluster-public-port")))) {
            throw new CLIException("The -cluster-xxx options require -cluster to be enabled");
        }
        io.vertx.core.cli.Option haGroupOption = this.executionContext.cli().getOption("hagroup");
        io.vertx.core.cli.Option quorumOption = this.executionContext.cli().getOption("quorum");
        if (!this.ha) {
            if (commandLine.isOptionAssigned(haGroupOption) || commandLine.isOptionAssigned(quorumOption)) {
                throw new CLIException("The option -hagroup and -quorum requires -ha to be enabled");
            }
        }
    }

    @Override // io.vertx.core.impl.launcher.commands.BareCommand
    public boolean isClustered() {
        return this.cluster || this.ha || (this.options != null && this.options.getEventBusOptions().isClustered());
    }

    @Override // io.vertx.core.impl.launcher.commands.BareCommand
    public boolean getHA() {
        return this.ha;
    }

    @Override // io.vertx.core.impl.launcher.commands.BareCommand, io.vertx.core.spi.launcher.Command
    public void run() {
        if (this.redeploy == null || this.redeploy.isEmpty()) {
            JsonObject conf = getConfiguration();
            if (conf == null) {
                conf = new JsonObject();
            }
            afterConfigParsed(conf);
            super.run(this::afterStoppingVertx);
            if (this.vertx == null) {
                ExecUtils.exitBecauseOfVertxInitializationIssue();
            }
            if (this.vertx instanceof VertxInternal) {
                ((VertxInternal) this.vertx).addCloseHook(completionHandler -> {
                    try {
                        beforeStoppingVertx(this.vertx);
                        completionHandler.handle(Future.succeededFuture());
                    } catch (Exception e) {
                        completionHandler.handle(Future.failedFuture(e));
                    }
                });
            }
            this.deploymentOptions = new DeploymentOptions();
            configureFromSystemProperties(this.deploymentOptions, "vertx.deployment.options.");
            this.deploymentOptions.setConfig(conf).setWorker(this.worker).setHa(this.ha).setInstances(this.instances);
            beforeDeployingVerticle(this.deploymentOptions);
            deploy();
            return;
        }
        initializeRedeployment();
    }

    protected synchronized void initializeRedeployment() {
        if (this.watcher != null) {
            throw new IllegalStateException("Redeployment already started ? The watcher already exists");
        }
        this.vertxApplicationBackgroundId = UUID.randomUUID().toString() + "-redeploy";
        this.watcher = new Watcher(getCwd(), this.redeploy, this::startAsBackgroundApplication, this::stopBackgroundApplication, this.onRedeployCommand, this.redeployGracePeriod, this.redeployScanPeriod);
        Runtime.getRuntime().addShutdownHook(new Thread() { // from class: io.vertx.core.impl.launcher.commands.RunCommand.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                RunCommand.this.shutdownRedeployment();
            }
        });
        this.watcher.watch();
    }

    protected synchronized void shutdownRedeployment() {
        if (this.watcher != null) {
            this.watcher.close();
            this.watcher = null;
        }
    }

    protected synchronized void stopBackgroundApplication(Handler<Void> onCompletion) throws InterruptedException {
        this.executionContext.execute(Action.stop, this.vertxApplicationBackgroundId, "--redeploy");
        if (this.redeployTerminationPeriod > 0) {
            try {
                Thread.sleep(this.redeployTerminationPeriod);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (onCompletion != null) {
            onCompletion.handle(null);
        }
    }

    protected void startAsBackgroundApplication(Handler<Void> onCompletion) {
        List<String> args = new ArrayList<>();
        args.add("run");
        args.add("--vertx-id=" + this.vertxApplicationBackgroundId);
        args.addAll(this.executionContext.commandLine().allArguments());
        if (this.cluster) {
            args.add("--cluster");
        }
        if (this.clusterHost != null) {
            args.add("--cluster-host=" + this.clusterHost);
        }
        if (this.clusterPort != 0) {
            args.add("--cluster-port=" + this.clusterPort);
        }
        if (this.clusterPublicHost != null) {
            args.add("--cluster-public-host=" + this.clusterPublicHost);
        }
        if (this.clusterPublicPort != -1) {
            args.add("--cluster-public-port=" + this.clusterPublicPort);
        }
        if (this.ha) {
            args.add("--ha");
        }
        if (this.haGroup != null && !this.haGroup.equals(VertxOptions.DEFAULT_HA_GROUP)) {
            args.add("--hagroup=" + this.haGroup);
        }
        if (this.quorum != -1) {
            args.add("--quorum=" + this.quorum);
        }
        if (this.classpath != null && !this.classpath.isEmpty()) {
            args.add("--classpath=" + ((String) this.classpath.stream().collect(Collectors.joining(File.pathSeparator))));
        }
        if (this.config != null) {
            args.add("--conf");
            args.add(this.config);
        }
        if (this.instances != 1) {
            args.add("--instances=" + this.instances);
        }
        if (this.worker) {
            args.add("--worker");
        }
        if (this.systemProperties != null) {
            args.addAll((Collection) this.systemProperties.stream().map(s -> {
                return "-D" + s;
            }).collect(Collectors.toList()));
        }
        args.add("--redirect-output");
        this.executionContext.execute("start", (String[]) args.toArray(new String[args.size()]));
        if (onCompletion != null) {
            onCompletion.handle(null);
        }
    }

    protected void deploy() {
        deploy(this.mainVerticle, this.vertx, this.deploymentOptions, res -> {
            if (res.failed()) {
                res.cause().printStackTrace();
                handleDeployFailed(res.cause());
            }
        });
    }

    private void handleDeployFailed(Throwable cause) {
        if (this.executionContext.main() instanceof VertxLifecycleHooks) {
            ((VertxLifecycleHooks) this.executionContext.main()).handleDeployFailed(this.vertx, this.mainVerticle, this.deploymentOptions, cause);
        } else {
            ExecUtils.exitBecauseOfVertxDeploymentIssue();
        }
    }

    protected JsonObject getConfiguration() {
        return getJsonFromFileOrString(this.config, "conf");
    }

    protected void beforeDeployingVerticle(DeploymentOptions deploymentOptions) {
        Object main = this.executionContext.main();
        if (main instanceof VertxLifecycleHooks) {
            ((VertxLifecycleHooks) main).beforeDeployingVerticle(deploymentOptions);
        }
    }

    protected void afterConfigParsed(JsonObject config) {
        Object main = this.executionContext.main();
        if (main instanceof VertxLifecycleHooks) {
            ((VertxLifecycleHooks) main).afterConfigParsed(config);
        }
    }

    protected void beforeStoppingVertx(Vertx vertx) {
        Object main = this.executionContext.main();
        if (main instanceof VertxLifecycleHooks) {
            ((VertxLifecycleHooks) main).beforeStoppingVertx(vertx);
        }
    }

    protected void afterStoppingVertx() {
        Object main = this.executionContext.main();
        if (main instanceof VertxLifecycleHooks) {
            ((VertxLifecycleHooks) main).afterStoppingVertx();
        }
    }
}
