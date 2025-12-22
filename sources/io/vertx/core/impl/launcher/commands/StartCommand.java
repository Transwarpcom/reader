package io.vertx.core.impl.launcher.commands;

import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Hidden;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.impl.launcher.CommandLineUtils;
import io.vertx.core.spi.launcher.DefaultCommand;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Name("start")
@Summary("Start a vert.x application in background")
@Description("Start a vert.x application as a background service. The application is identified with an id that can be set using the `vertx-id` option. If not set a random UUID is generated. The application can be stopped with the `stop` command.")
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/StartCommand.class */
public class StartCommand extends DefaultCommand {
    private String id;
    private String launcher;
    private boolean redirect;
    private String jvmOptions;

    @Option(longName = "vertx-id", shortName = "id", required = false, acceptValue = true)
    @Description("The id of the application, a random UUID by default")
    public void setApplicationId(String id) {
        this.id = id;
    }

    @Option(longName = "java-opts", required = false, acceptValue = true)
    @Description("Java Virtual Machine options to pass to the spawned process such as \"-Xmx1G -Xms256m -XX:MaxPermSize=256m\". If not set the `JAVA_OPTS` environment variable is used.")
    public void setJavaOptions(String options) {
        this.jvmOptions = options;
    }

    @Option(longName = "launcher-class")
    @Hidden
    public void setLauncherClass(String clazz) {
        this.launcher = clazz;
    }

    @Option(longName = "redirect-output", flag = true)
    @Hidden
    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    @Override // io.vertx.core.spi.launcher.Command
    public void run() throws IOException {
        this.out.println("Starting vert.x application...");
        List<String> cmd = new ArrayList<>();
        ProcessBuilder builder = new ProcessBuilder(new String[0]);
        addJavaCommand(cmd);
        List<String> cliArguments = getArguments();
        builder.environment().put("CLASSPATH", System.getProperty("java.class.path"));
        if (this.launcher != null) {
            ExecUtils.addArgument(cmd, this.launcher);
            Optional<String> maybeCommand = cliArguments.stream().filter(arg -> {
                return this.executionContext.launcher().getCommandNames().contains(arg);
            }).findFirst();
            if (!maybeCommand.isPresent()) {
                ExecUtils.addArgument(cmd, "run");
            }
        } else if (isLaunchedAsFatJar()) {
            ExecUtils.addArgument(cmd, "-jar");
            ExecUtils.addArgument(cmd, CommandLineUtils.getJar());
        } else {
            ExecUtils.addArgument(cmd, CommandLineUtils.getFirstSegmentOfCommand());
            ExecUtils.addArgument(cmd, "run");
        }
        cliArguments.forEach(arg2 -> {
            ExecUtils.addArgument(cmd, arg2);
        });
        try {
            builder.command(cmd);
            if (this.redirect) {
                builder.redirectError(ProcessBuilder.Redirect.INHERIT);
                builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            }
            builder.start();
            this.out.println(this.id);
        } catch (Exception e) {
            this.out.println("Cannot create vert.x application process");
            e.printStackTrace(this.out);
            ExecUtils.exitBecauseOfProcessIssue();
        }
    }

    private void addJavaCommand(List<String> cmd) {
        if (ExecUtils.isWindows()) {
            ExecUtils.addArgument(cmd, "cmd.exe");
            ExecUtils.addArgument(cmd, "/C");
            ExecUtils.addArgument(cmd, "start");
            ExecUtils.addArgument(cmd, "vertx-id - " + this.id);
            ExecUtils.addArgument(cmd, "/B");
        }
        ExecUtils.addArgument(cmd, getJava().getAbsolutePath());
        if (this.jvmOptions == null) {
            String opts = System.getenv("JAVA_OPTS");
            if (opts != null) {
                Arrays.stream(opts.split(" ")).forEach(s -> {
                    ExecUtils.addArgument(cmd, s);
                });
                return;
            }
            return;
        }
        Arrays.stream(this.jvmOptions.split(" ")).forEach(s2 -> {
            ExecUtils.addArgument(cmd, s2);
        });
    }

    private File getJava() {
        File java;
        File home = new File(System.getProperty("java.home"));
        if (ExecUtils.isWindows()) {
            java = new File(home, "bin/java.exe");
        } else {
            java = new File(home, "bin/java");
        }
        if (!java.isFile()) {
            this.out.println("Cannot find java executable - " + java.getAbsolutePath() + " does not exist");
            ExecUtils.exitBecauseOfSystemConfigurationIssue();
        }
        return java;
    }

    private boolean isLaunchedAsFatJar() {
        return CommandLineUtils.getJar() != null;
    }

    private List<String> getArguments() {
        List<String> args = this.executionContext.commandLine().allArguments();
        if (this.systemProperties != null) {
            Stream<R> map = this.systemProperties.stream().map(entry -> {
                return "-D" + entry;
            });
            args.getClass();
            map.forEach((v1) -> {
                r1.add(v1);
            });
        }
        args.add("-Dvertx.id=" + getId());
        return args;
    }

    private String getId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        return this.id;
    }
}
