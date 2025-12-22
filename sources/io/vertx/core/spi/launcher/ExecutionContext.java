package io.vertx.core.spi.launcher;

import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.impl.launcher.VertxCommandLauncher;
import java.io.PrintStream;
import java.util.HashMap;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/launcher/ExecutionContext.class */
public class ExecutionContext extends HashMap<String, Object> {
    private final VertxCommandLauncher launcher;
    private final Command command;
    private final CommandLine commandLine;

    public ExecutionContext(Command command, VertxCommandLauncher launcher, CommandLine commandLine) {
        this.command = command;
        this.commandLine = commandLine;
        this.launcher = launcher;
    }

    public Command command() {
        return this.command;
    }

    public VertxCommandLauncher launcher() {
        return this.launcher;
    }

    public CLI cli() {
        return this.commandLine.cli();
    }

    public CommandLine commandLine() {
        return this.commandLine;
    }

    public void execute(String command, String... args) {
        this.launcher.execute(command, args);
    }

    public Object main() {
        return get("Main");
    }

    public PrintStream getPrintStream() {
        return this.launcher.getPrintStream();
    }
}
