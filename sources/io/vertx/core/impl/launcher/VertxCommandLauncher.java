package io.vertx.core.impl.launcher;

import ch.qos.logback.classic.spi.CallerData;
import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.InvalidValueException;
import io.vertx.core.cli.MissingOptionException;
import io.vertx.core.cli.MissingValueException;
import io.vertx.core.cli.UsageMessageFormatter;
import io.vertx.core.cli.annotations.CLIConfigurator;
import io.vertx.core.impl.launcher.commands.RunCommand;
import io.vertx.core.spi.launcher.Command;
import io.vertx.core.spi.launcher.CommandFactory;
import io.vertx.core.spi.launcher.CommandFactoryLookup;
import io.vertx.core.spi.launcher.DefaultCommandFactory;
import io.vertx.core.spi.launcher.ExecutionContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/VertxCommandLauncher.class */
public class VertxCommandLauncher extends UsageMessageFormatter {
    protected static List<String> PROCESS_ARGS;
    protected final List<CommandFactoryLookup> lookups;
    protected final Map<String, CommandRegistration> commandByName;
    protected Object main;

    public static List<String> getProcessArguments() {
        return PROCESS_ARGS;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/VertxCommandLauncher$CommandRegistration.class */
    public static class CommandRegistration {
        public final CommandFactory factory;
        public final CLI cli;
        private List<Command> commands;

        public CommandRegistration(CommandFactory factory) {
            this(factory, factory.define());
        }

        public CommandRegistration(CommandFactory factory, CLI cli) {
            this.commands = new ArrayList();
            this.factory = factory;
            this.cli = cli;
        }

        public void addCommand(Command command) {
            this.commands.add(command);
        }

        public Command getCommand() {
            if (!this.commands.isEmpty()) {
                return this.commands.get(0);
            }
            return null;
        }

        public List<Command> getCommands() {
            return this.commands;
        }
    }

    public VertxCommandLauncher() {
        this(Collections.singletonList(new ServiceCommandFactoryLoader()));
    }

    public VertxCommandLauncher(Collection<CommandFactoryLookup> lookups) {
        this.lookups = new ArrayList(lookups);
        this.commandByName = new TreeMap();
        load();
    }

    protected void load() {
        for (CommandFactoryLookup lookup : this.lookups) {
            Collection<CommandFactory<?>> commands = lookup.lookup();
            commands.forEach(factory -> {
                CLI cli = factory.define();
                CommandRegistration previous = this.commandByName.get(cli.getName());
                if (previous == null) {
                    this.commandByName.put(cli.getName(), new CommandRegistration(factory, cli));
                } else if (cli.getPriority() > previous.cli.getPriority()) {
                    this.commandByName.put(cli.getName(), new CommandRegistration(factory, cli));
                }
            });
        }
    }

    public VertxCommandLauncher register(CommandFactory factory) {
        CLI cli = factory.define();
        this.commandByName.put(cli.getName(), new CommandRegistration(factory, cli));
        return this;
    }

    @Deprecated
    public VertxCommandLauncher register(Class<? extends Command> clazz) {
        DefaultCommandFactory factory = new DefaultCommandFactory(clazz);
        CLI cli = factory.define();
        this.commandByName.put(cli.getName(), new CommandRegistration(factory, cli));
        return this;
    }

    public VertxCommandLauncher register(Class<? extends Command> clazz, Supplier<? extends Command> supplier) {
        DefaultCommandFactory factory = new DefaultCommandFactory(clazz, supplier);
        CLI cli = factory.define();
        this.commandByName.put(cli.getName(), new CommandRegistration(factory, cli));
        return this;
    }

    public VertxCommandLauncher unregister(String name) {
        this.commandByName.remove(name);
        return this;
    }

    public Collection<String> getCommandNames() {
        return this.commandByName.keySet();
    }

    protected Command getNewCommandInstance(String name, CommandLine commandLine) {
        CommandRegistration registration = this.commandByName.get(name);
        if (registration != null) {
            Command command = registration.factory.create(commandLine);
            registration.addCommand(command);
            return command;
        }
        return null;
    }

    public Command getExistingCommandInstance(String name) {
        CommandRegistration registration = this.commandByName.get(name);
        if (registration != null) {
            return registration.getCommand();
        }
        return null;
    }

    public void execute(String command, String... cla) {
        if (command != null && isAskingForVersion(command)) {
            execute("version", new String[0]);
            return;
        }
        if (command == null || isAskingForHelp(command)) {
            printGlobalUsage();
            return;
        }
        CommandRegistration registration = this.commandByName.get(command);
        if (registration == null) {
            printCommandNotFound(command);
            return;
        }
        CLI cli = registration.cli;
        try {
            try {
                if (cla.length >= 1 && isAskingForHelp(cla[0])) {
                    printCommandUsage(cli);
                    return;
                }
                CommandLine evaluated = cli.parse(Arrays.asList(cla));
                Command cmd = getNewCommandInstance(command, evaluated);
                ExecutionContext context = new ExecutionContext(cmd, this, evaluated);
                if (this.main != null) {
                    context.put("Main", this.main);
                    context.put("Main-Class", this.main.getClass().getName());
                    context.put("Default-Verticle-Factory", getFromManifest("Default-Verticle-Factory"));
                }
                CLIConfigurator.inject(evaluated, cmd);
                cmd.setUp(context);
                cmd.run();
                cmd.tearDown();
            } catch (CLIException e) {
                printGenericExecutionError(cli, e);
            } catch (RuntimeException e2) {
                if (e2.getCause() instanceof CLIException) {
                    printGenericExecutionError(cli, (CLIException) e2.getCause());
                    return;
                }
                throw e2;
            }
        } catch (InvalidValueException | MissingOptionException | MissingValueException e3) {
            printSpecificException(cli, e3);
        }
    }

    protected void printCommandUsage(CLI cli) {
        StringBuilder builder = new StringBuilder();
        cli.usage(builder, getCommandLinePrefix());
        getPrintStream().println(builder.toString());
    }

    protected void printGenericExecutionError(CLI cli, CLIException e) {
        getPrintStream().println("Error while executing command " + cli.getName() + ": " + e.getMessage() + getNewLine());
        if (e.getCause() != null) {
            e.getCause().printStackTrace(getPrintStream());
        }
    }

    protected void printSpecificException(CLI cli, Exception e) {
        getPrintStream().println(e.getMessage() + getNewLine());
        printCommandUsage(cli);
    }

    protected void printCommandNotFound(String command) {
        StringBuilder builder = new StringBuilder();
        buildWrapped(builder, 0, "The command '" + command + "' is not a valid command." + getNewLine() + "See '" + getCommandLinePrefix() + " --help'");
        getPrintStream().println(builder.toString());
    }

    protected void printGlobalUsage() {
        StringBuilder builder = new StringBuilder();
        computeUsage(builder, getCommandLinePrefix() + " [COMMAND] [OPTIONS] [arg...]");
        builder.append(getNewLine());
        builder.append("Commands:").append(getNewLine());
        renderCommands(builder, (Collection) this.commandByName.values().stream().map(r -> {
            return r.cli;
        }).collect(Collectors.toList()));
        builder.append(getNewLine()).append(getNewLine());
        buildWrapped(builder, 0, "Run '" + getCommandLinePrefix() + " COMMAND --help' for more information on a command.");
        getPrintStream().println(builder.toString());
    }

    protected String getCommandLinePrefix() {
        String sysProp = System.getProperty("vertx.cli.usage.prefix");
        if (sysProp != null) {
            return sysProp;
        }
        String jar = CommandLineUtils.getJar();
        if (jar != null) {
            return "java -jar " + jar;
        }
        String command = CommandLineUtils.getFirstSegmentOfCommand();
        if (command != null) {
            return "java " + command;
        }
        return "vertx";
    }

    protected static boolean isAskingForHelp(String command) {
        return command.equalsIgnoreCase("--help") || command.equalsIgnoreCase("-help") || command.equalsIgnoreCase("-h") || command.equalsIgnoreCase(CallerData.NA) || command.equalsIgnoreCase("/?");
    }

    protected static boolean isAskingForVersion(String command) {
        return command.equalsIgnoreCase("-version") || command.equalsIgnoreCase("--version");
    }

    public void dispatch(String[] args) {
        dispatch(null, args);
    }

    public void dispatch(Object main, String[] args) {
        this.main = main == null ? this : main;
        PROCESS_ARGS = Collections.unmodifiableList(Arrays.asList(args));
        if (args.length >= 1 && isAskingForHelp(args[0])) {
            printGlobalUsage();
            return;
        }
        if (args.length >= 1 && isAskingForVersion(args[0])) {
            execute("version", new String[0]);
            return;
        }
        if (args.length >= 1 && this.commandByName.get(args[0]) != null) {
            execute(args[0], (String[]) Arrays.copyOfRange(args, 1, args.length));
            return;
        }
        if (args.length >= 2 && isAskingForHelp(args[1])) {
            execute(args[0], "--help");
            return;
        }
        String verticle = getMainVerticle();
        String command = getCommandFromManifest();
        if (verticle != null) {
            String[] newArgs = new String[args.length + 1];
            newArgs[0] = verticle;
            System.arraycopy(args, 0, newArgs, 1, args.length);
            execute(getDefaultCommand(), newArgs);
            return;
        }
        if (command != null) {
            execute(command, args);
            return;
        }
        if (args.length == 0) {
            printGlobalUsage();
        } else if (args[0].equalsIgnoreCase("-ha")) {
            execute("bare", (String[]) Arrays.copyOfRange(args, 1, args.length));
        } else {
            printCommandNotFound(args[0]);
        }
    }

    protected String getDefaultCommand() {
        String fromManifest = getCommandFromManifest();
        if (fromManifest == null) {
            return "run";
        }
        return fromManifest;
    }

    protected String getCommandFromManifest() {
        return getFromManifest("Main-Command");
    }

    private String getFromManifest(String key) throws IOException {
        Enumeration<URL> resources;
        String value;
        try {
            resources = RunCommand.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
        while (resources.hasMoreElements()) {
            InputStream stream = resources.nextElement().openStream();
            Throwable th = null;
            try {
                try {
                    Manifest manifest = new Manifest(stream);
                    Attributes attributes = manifest.getMainAttributes();
                    String mainClass = attributes.getValue("Main-Class");
                    if (!this.main.getClass().getName().equals(mainClass) || (value = attributes.getValue(key)) == null) {
                        if (stream != null) {
                            if (0 != 0) {
                                try {
                                    stream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                stream.close();
                            }
                        }
                    } else {
                        if (stream != null) {
                            if (0 != 0) {
                                try {
                                    stream.close();
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                }
                            } else {
                                stream.close();
                            }
                        }
                        return value;
                    }
                } finally {
                }
            } finally {
            }
            throw new IllegalStateException(e.getMessage());
        }
        return null;
    }

    public PrintStream getPrintStream() {
        return System.out;
    }

    protected String getMainVerticle() {
        return getFromManifest("Main-Verticle");
    }

    public static void resetProcessArguments() {
        PROCESS_ARGS = null;
    }
}
