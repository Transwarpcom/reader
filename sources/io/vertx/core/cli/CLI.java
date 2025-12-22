package io.vertx.core.cli;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.cli.annotations.CLIConfigurator;
import io.vertx.core.cli.impl.DefaultCLI;
import java.util.List;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/CLI.class */
public interface CLI {
    CommandLine parse(List<String> list);

    CommandLine parse(List<String> list, boolean z);

    String getName();

    @Fluent
    CLI setName(String str);

    String getDescription();

    @Fluent
    CLI setDescription(String str);

    String getSummary();

    @Fluent
    CLI setSummary(String str);

    boolean isHidden();

    @Fluent
    CLI setHidden(boolean z);

    List<Option> getOptions();

    @Fluent
    CLI addOption(Option option);

    @Fluent
    CLI addOptions(List<Option> list);

    @Fluent
    CLI setOptions(List<Option> list);

    List<Argument> getArguments();

    @Fluent
    CLI addArgument(Argument argument);

    @Fluent
    CLI addArguments(List<Argument> list);

    @Fluent
    CLI setArguments(List<Argument> list);

    Option getOption(String str);

    Argument getArgument(String str);

    Argument getArgument(int i);

    @Fluent
    CLI removeOption(String str);

    @Fluent
    CLI removeArgument(int i);

    @GenIgnore
    CLI usage(StringBuilder sb);

    @GenIgnore
    CLI usage(StringBuilder sb, String str);

    int getPriority();

    @Fluent
    CLI setPriority(int i);

    static CLI create(String name) {
        return new DefaultCLI().setName(name);
    }

    @GenIgnore
    static CLI create(Class<?> clazz) {
        return CLIConfigurator.define(clazz);
    }
}
