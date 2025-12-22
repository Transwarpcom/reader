package io.vertx.core.cli.impl;

import io.vertx.core.cli.Argument;
import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.InvalidValueException;
import io.vertx.core.cli.Option;
import io.vertx.core.cli.TypedArgument;
import io.vertx.core.cli.TypedOption;
import io.vertx.core.cli.converters.Converters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/impl/DefaultCommandLine.class */
public class DefaultCommandLine implements CommandLine {
    protected final CLI cli;
    protected List<String> allArgs = new ArrayList();
    protected Map<Option, List<String>> optionValues = new HashMap();
    protected List<Option> optionsSeenInCommandLine = new ArrayList();
    protected Map<Argument, List<String>> argumentValues = new HashMap();
    protected boolean valid;

    public DefaultCommandLine(CLI cli) {
        this.cli = cli;
    }

    @Override // io.vertx.core.cli.CommandLine
    public CLI cli() {
        return this.cli;
    }

    @Override // io.vertx.core.cli.CommandLine
    public List<String> allArguments() {
        return this.allArgs;
    }

    public CommandLine addArgumentValue(String argument) {
        this.allArgs.add(argument);
        return this;
    }

    @Override // io.vertx.core.cli.CommandLine
    public <T> T getOptionValue(String str) {
        Option option = this.cli.getOption(str);
        if (option == null) {
            return null;
        }
        if (option instanceof TypedOption) {
            return (T) getValue((TypedOption) option);
        }
        return (T) getRawValueForOption(option);
    }

    @Override // io.vertx.core.cli.CommandLine
    public boolean isFlagEnabled(String name) {
        Option option = this.cli.getOption(name);
        if (option == null) {
            throw new IllegalArgumentException("Cannot find the option '" + name + OperatorName.SHOW_TEXT_LINE);
        }
        if (option.isFlag()) {
            return this.optionsSeenInCommandLine.contains(option);
        }
        throw new IllegalStateException("Cannot retrieve the flag value on a non-flag option (" + name + ")");
    }

    @Override // io.vertx.core.cli.CommandLine
    public <T> List<T> getOptionValues(String str) {
        Option option = this.cli.getOption(str);
        if (option == null) {
            return null;
        }
        if (option instanceof TypedOption) {
            TypedOption typedOption = (TypedOption) option;
            if (typedOption.isParsedAsList()) {
                return createFromList(getRawValueForOption(option), typedOption);
            }
            return (List) getRawValuesForOption(option).stream().map(s -> {
                return create(s, typedOption);
            }).collect(Collectors.toList());
        }
        return (List<T>) getRawValuesForOption(option);
    }

    @Override // io.vertx.core.cli.CommandLine
    public <T> List<T> getArgumentValues(int i) {
        Argument argument = this.cli.getArgument(i);
        if (argument == null) {
            return null;
        }
        if (argument instanceof TypedArgument) {
            TypedArgument typedArgument = (TypedArgument) argument;
            return (List) getRawValuesForArgument(typedArgument).stream().map(s -> {
                return create(s, typedArgument);
            }).collect(Collectors.toList());
        }
        return (List<T>) getRawValuesForArgument(argument);
    }

    @Override // io.vertx.core.cli.CommandLine
    public <T> T getArgumentValue(String str) {
        Argument argument = this.cli.getArgument(str);
        if (argument == null) {
            return null;
        }
        return (T) getArgumentValue(argument.getIndex());
    }

    @Override // io.vertx.core.cli.CommandLine
    public <T> T getArgumentValue(int i) {
        Argument argument = this.cli.getArgument(i);
        if (argument == null) {
            return null;
        }
        if (argument instanceof TypedArgument) {
            return (T) create(getRawValueForArgument(argument), (TypedArgument) argument);
        }
        return (T) getRawValueForArgument(argument);
    }

    @Override // io.vertx.core.cli.CommandLine
    public boolean isOptionAssigned(Option option) {
        return !getRawValuesForOption(option).isEmpty();
    }

    @Override // io.vertx.core.cli.CommandLine
    public List<String> getRawValuesForOption(Option option) {
        List<?> list = this.optionValues.get(option);
        if (list != null) {
            return (List) list.stream().map((v0) -> {
                return v0.toString();
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override // io.vertx.core.cli.CommandLine
    public List<String> getRawValuesForArgument(Argument argument) {
        List<?> list = this.argumentValues.get(argument);
        if (list != null) {
            return (List) list.stream().map((v0) -> {
                return v0.toString();
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public DefaultCommandLine addRawValue(Option option, String value) {
        if (!acceptMoreValues(option) && !option.isFlag()) {
            throw new CLIException("The option " + option.getName() + " does not accept value or has already been set");
        }
        if (!option.getChoices().isEmpty() && !option.getChoices().contains(value)) {
            throw new InvalidValueException(option, value);
        }
        List<String> list = this.optionValues.get(option);
        if (list == null) {
            list = new ArrayList();
            this.optionValues.put(option, list);
        }
        list.add(value);
        return this;
    }

    @Override // io.vertx.core.cli.CommandLine
    public String getRawValueForOption(Option option) {
        if (isOptionAssigned(option)) {
            return getRawValuesForOption(option).get(0);
        }
        return option.getDefaultValue();
    }

    @Override // io.vertx.core.cli.CommandLine
    public boolean acceptMoreValues(Option option) {
        return option.isMultiValued() || (option.isSingleValued() && !isOptionAssigned(option));
    }

    @Override // io.vertx.core.cli.CommandLine
    public String getRawValueForArgument(Argument arg) {
        List values = this.argumentValues.get(arg);
        if (values == null || values.isEmpty()) {
            return arg.getDefaultValue();
        }
        return values.get(0).toString();
    }

    public DefaultCommandLine setRawValue(Argument arg, String rawValue) {
        List<String> list = this.argumentValues.get(arg);
        if (list == null) {
            list = new ArrayList();
            this.argumentValues.put(arg, list);
        }
        list.add(rawValue);
        return this;
    }

    @Override // io.vertx.core.cli.CommandLine
    public boolean isArgumentAssigned(Argument arg) {
        return this.argumentValues.get(arg) != null;
    }

    public DefaultCommandLine setSeenInCommandLine(Option option) {
        this.optionsSeenInCommandLine.add(option);
        return this;
    }

    @Override // io.vertx.core.cli.CommandLine
    public boolean isSeenInCommandLine(Option option) {
        return this.optionsSeenInCommandLine.contains(option);
    }

    private <T> T getValue(TypedOption<T> typedOption) {
        if (isOptionAssigned(typedOption)) {
            return (T) create(getRawValueForOption(typedOption), typedOption);
        }
        if (typedOption.getDefaultValue() != null) {
            return (T) create(getRawValueForOption(typedOption), typedOption);
        }
        if (typedOption.isFlag() || isBoolean(typedOption)) {
            try {
                if (isSeenInCommandLine(typedOption)) {
                    return (T) Boolean.TRUE;
                }
                return (T) Boolean.FALSE;
            } catch (InvalidValueException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return null;
    }

    private boolean isBoolean(TypedOption option) {
        Class type = option.getType();
        return type == Boolean.TYPE || type == Boolean.class;
    }

    public static <T> T create(String str, TypedArgument<T> typedArgument) {
        Objects.requireNonNull(typedArgument);
        if (str == null) {
            str = typedArgument.getDefaultValue();
        }
        if (str == null) {
            return null;
        }
        try {
            if (typedArgument.getConverter() != null) {
                return (T) Converters.create(str, typedArgument.getConverter());
            }
            return (T) Converters.create(typedArgument.getType(), str);
        } catch (Exception e) {
            throw new InvalidValueException(typedArgument, str, e);
        }
    }

    public static <T> T create(String str, TypedOption<T> typedOption) {
        Objects.requireNonNull(typedOption);
        if (str == null) {
            str = typedOption.getDefaultValue();
        }
        if (str == null) {
            return null;
        }
        try {
            if (typedOption.getConverter() != null) {
                return (T) Converters.create(str, typedOption.getConverter());
            }
            return (T) Converters.create(typedOption.getType(), str);
        } catch (Exception e) {
            throw new InvalidValueException(typedOption, str, e);
        }
    }

    public static <T> List<T> createFromList(String raw, TypedOption<T> option) {
        if (raw == null) {
            return Collections.emptyList();
        }
        String[] segments = raw.split(option.getListSeparator());
        return (List) Arrays.stream(segments).map(s -> {
            return create(s.trim(), option);
        }).collect(Collectors.toList());
    }

    @Override // io.vertx.core.cli.CommandLine
    public boolean isValid() {
        return this.valid;
    }

    void setValidity(boolean validity) {
        this.valid = validity;
    }

    @Override // io.vertx.core.cli.CommandLine
    public boolean isAskingForHelp() {
        for (Option option : this.cli.getOptions()) {
            if (option.isHelp() && isSeenInCommandLine(option)) {
                return true;
            }
        }
        return false;
    }
}
