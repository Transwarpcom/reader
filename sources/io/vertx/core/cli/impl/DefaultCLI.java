package io.vertx.core.cli.impl;

import io.vertx.core.cli.Argument;
import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.Option;
import io.vertx.core.cli.UsageMessageFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/impl/DefaultCLI.class */
public class DefaultCLI implements CLI {
    protected String name;
    protected int priority;
    protected String description;
    protected String summary;
    protected boolean hidden;
    protected List<Option> options = new ArrayList();
    private List<Argument> arguments = new ArrayList();

    @Override // io.vertx.core.cli.CLI
    public CommandLine parse(List<String> arguments) {
        return new DefaultParser().parse(this, arguments);
    }

    @Override // io.vertx.core.cli.CLI
    public CommandLine parse(List<String> arguments, boolean validate) {
        return new DefaultParser().parse(this, arguments, validate);
    }

    @Override // io.vertx.core.cli.CLI
    public String getName() {
        return this.name;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI setName(String name) {
        Objects.requireNonNull(name);
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Invalid command name");
        }
        this.name = name;
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public String getDescription() {
        return this.description;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI setDescription(String desc) {
        Objects.requireNonNull(desc);
        this.description = desc;
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public String getSummary() {
        return this.summary;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI setSummary(String summary) {
        Objects.requireNonNull(summary);
        this.summary = summary;
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public boolean isHidden() {
        return this.hidden;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public List<Option> getOptions() {
        return this.options;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI addOption(Option option) {
        Objects.requireNonNull(option);
        this.options.add(option);
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI addOptions(List<Option> options) {
        Objects.requireNonNull(options);
        options.forEach(this::addOption);
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI setOptions(List<Option> options) {
        Objects.requireNonNull(options);
        this.options = new ArrayList();
        return addOptions(options);
    }

    @Override // io.vertx.core.cli.CLI
    public List<Argument> getArguments() {
        return this.arguments;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI addArgument(Argument arg) {
        Objects.requireNonNull(arg);
        this.arguments.add(arg);
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI addArguments(List<Argument> args) {
        Objects.requireNonNull(args);
        args.forEach(this::addArgument);
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI setArguments(List<Argument> args) {
        Objects.requireNonNull(args);
        this.arguments = new ArrayList(args);
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public Option getOption(String name) {
        Objects.requireNonNull(name);
        for (Option option : this.options) {
            if (name.equalsIgnoreCase(option.getLongName())) {
                return option;
            }
        }
        for (Option option2 : this.options) {
            if (name.equalsIgnoreCase(option2.getShortName())) {
                return option2;
            }
        }
        for (Option option3 : this.options) {
            if (name.equalsIgnoreCase(option3.getArgName())) {
                return option3;
            }
        }
        return null;
    }

    @Override // io.vertx.core.cli.CLI
    public Argument getArgument(String name) {
        Objects.requireNonNull(name);
        for (Argument arg : this.arguments) {
            if (name.equalsIgnoreCase(arg.getArgName())) {
                return arg;
            }
        }
        return null;
    }

    @Override // io.vertx.core.cli.CLI
    public Argument getArgument(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Given index cannot be negative");
        }
        for (Argument arg : this.arguments) {
            if (index == arg.getIndex()) {
                return arg;
            }
        }
        return null;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI removeOption(String name) {
        this.options = (List) this.options.stream().filter(o -> {
            return (o.getLongName().equals(name) || o.getShortName().equals(name)) ? false : true;
        }).collect(Collectors.toList());
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI removeArgument(int index) {
        Iterator it = new TreeSet(this.arguments).iterator();
        while (it.hasNext()) {
            Argument arg = (Argument) it.next();
            if (arg.getIndex() == index) {
                this.arguments.remove(arg);
                return this;
            }
        }
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI usage(StringBuilder builder) {
        new UsageMessageFormatter().usage(builder, this);
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI usage(StringBuilder builder, String prefix) {
        new UsageMessageFormatter().usage(builder, prefix, this);
        return this;
    }

    @Override // io.vertx.core.cli.CLI
    public int getPriority() {
        return this.priority;
    }

    @Override // io.vertx.core.cli.CLI
    public CLI setPriority(int priority) {
        this.priority = priority;
        return this;
    }
}
