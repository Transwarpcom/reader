package io.vertx.core.cli;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/MissingValueException.class */
public class MissingValueException extends CLIException {
    private final Option option;
    private final Argument argument;

    public MissingValueException(Option option) {
        super("The option '" + option.getName() + "' requires a value");
        this.argument = null;
        this.option = option;
    }

    public MissingValueException(Argument argument) {
        super("The argument '" + (argument.getArgName() != null ? argument.getArgName() : Integer.valueOf(argument.getIndex())) + "' is required");
        this.option = null;
        this.argument = argument;
    }

    public Option getOption() {
        return this.option;
    }

    public Argument getArgument() {
        return this.argument;
    }
}
