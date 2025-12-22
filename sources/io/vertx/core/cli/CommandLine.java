package io.vertx.core.cli;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.cli.impl.DefaultCommandLine;
import java.util.List;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/CommandLine.class */
public interface CommandLine {
    CLI cli();

    List<String> allArguments();

    <T> T getOptionValue(String str);

    <T> T getArgumentValue(String str);

    <T> T getArgumentValue(int i);

    @GenIgnore
    <T> List<T> getOptionValues(String str);

    @GenIgnore
    <T> List<T> getArgumentValues(int i);

    boolean isFlagEnabled(String str);

    boolean isOptionAssigned(Option option);

    List<String> getRawValuesForOption(Option option);

    List<String> getRawValuesForArgument(Argument argument);

    String getRawValueForOption(Option option);

    boolean acceptMoreValues(Option option);

    String getRawValueForArgument(Argument argument);

    boolean isArgumentAssigned(Argument argument);

    boolean isSeenInCommandLine(Option option);

    boolean isValid();

    boolean isAskingForHelp();

    static CommandLine create(CLI cli) {
        return new DefaultCommandLine(cli);
    }

    @Deprecated
    default List<String> getRawValues(Option option) {
        return getRawValuesForOption(option);
    }
}
