package io.vertx.core.cli.impl;

import io.vertx.core.cli.AmbiguousOptionException;
import io.vertx.core.cli.Argument;
import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.InvalidValueException;
import io.vertx.core.cli.MissingOptionException;
import io.vertx.core.cli.MissingValueException;
import io.vertx.core.cli.Option;
import io.vertx.core.cli.UsageMessageFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/impl/DefaultParser.class */
public class DefaultParser {
    protected String token;
    protected Option current;
    protected List<Option> expectedOpts;
    private DefaultCommandLine commandLine;
    private boolean skipParsing;
    private CLI cli;

    static String stripLeadingHyphens(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith(UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX)) {
            return str.substring(2, str.length());
        }
        if (str.startsWith("-")) {
            return str.substring(1, str.length());
        }
        return str;
    }

    static String stripLeadingAndTrailingQuotes(String str) {
        int length = str.length();
        if (length > 1 && str.startsWith(OperatorName.SHOW_TEXT_LINE_AND_SPACE) && str.endsWith(OperatorName.SHOW_TEXT_LINE_AND_SPACE) && str.substring(1, length - 1).indexOf(34) == -1) {
            str = str.substring(1, length - 1);
        }
        return str;
    }

    public CommandLine parse(CLI cli, List<String> cla) throws CLIException {
        return parse(cli, cla, true);
    }

    public CommandLine parse(CLI cli, List<String> cla, boolean validate) throws CLIException {
        this.commandLine = (DefaultCommandLine) CommandLine.create(cli);
        this.current = null;
        this.skipParsing = false;
        this.cli = cli;
        int current = 0;
        for (Argument argument : cli.getArguments()) {
            if (argument.getIndex() == -1) {
                argument.setIndex(current);
                current++;
            } else {
                current = argument.getIndex() + 1;
            }
        }
        cli.getArguments().sort((o1, o2) -> {
            if (o1.getIndex() == o2.getIndex()) {
                return 1;
            }
            return Integer.valueOf(o1.getIndex()).compareTo(Integer.valueOf(o2.getIndex()));
        });
        cli.getOptions().stream().forEach((v0) -> {
            v0.ensureValidity();
        });
        cli.getArguments().stream().forEach((v0) -> {
            v0.ensureValidity();
        });
        this.expectedOpts = getRequiredOptions();
        if (cla != null) {
            cla.forEach(this::visit);
        }
        try {
            checkRequiredValues();
            checkRequiredOptions();
            validate();
            this.commandLine.setValidity(true);
        } catch (CLIException e) {
            if (validate && !this.commandLine.isAskingForHelp()) {
                throw e;
            }
            this.commandLine.setValidity(false);
        }
        return this.commandLine;
    }

    protected void validate() throws CLIException {
        boolean multiValue = false;
        List<Integer> usedIndexes = new ArrayList<>();
        for (Argument argument : this.cli.getArguments()) {
            if (usedIndexes.contains(Integer.valueOf(argument.getIndex()))) {
                throw new CLIException("Only one argument can use the index " + argument.getIndex());
            }
            usedIndexes.add(Integer.valueOf(argument.getIndex()));
            if (multiValue) {
                throw new CLIException("Only the last argument can be multi-valued");
            }
            multiValue = argument.isMultiValued();
        }
        Iterator<Argument> iterator = this.cli.getArguments().iterator();
        Argument current = null;
        if (iterator.hasNext()) {
            current = iterator.next();
        }
        for (String v : this.commandLine.allArguments()) {
            if (current != null) {
                this.commandLine.setRawValue(current, v);
                if (!current.isMultiValued()) {
                    if (iterator.hasNext()) {
                        current = iterator.next();
                    } else {
                        current = null;
                    }
                }
            }
        }
        for (Argument arg : this.cli.getArguments()) {
            if (arg.isRequired() && !this.commandLine.isArgumentAssigned(arg)) {
                throw new MissingValueException(arg);
            }
        }
    }

    private List<Option> getRequiredOptions() {
        return (List) this.cli.getOptions().stream().filter((v0) -> {
            return v0.isRequired();
        }).collect(Collectors.toList());
    }

    private void checkRequiredOptions() throws MissingOptionException {
        if (!this.expectedOpts.isEmpty()) {
            throw new MissingOptionException(this.expectedOpts);
        }
    }

    private void checkRequiredValues() throws MissingValueException {
        if (this.current != null && this.current.acceptValue() && !this.commandLine.isOptionAssigned(this.current) && !this.current.isFlag()) {
            throw new MissingValueException(this.current);
        }
    }

    private void visit(String token) throws CLIException {
        this.token = token;
        if (this.skipParsing) {
            this.commandLine.addArgumentValue(token);
        } else if (token.equals(UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX)) {
            this.skipParsing = true;
        } else if (this.current != null && this.current.acceptValue() && isValue(token)) {
            this.commandLine.addRawValue(this.current, stripLeadingAndTrailingQuotes(token));
        } else if (token.startsWith(UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX)) {
            handleLongOption(token);
        } else if (token.startsWith("-") && !"-".equals(token)) {
            handleShortAndLongOption(token);
        } else {
            handleArgument(token);
        }
        if (this.current != null && !this.commandLine.acceptMoreValues(this.current)) {
            this.current = null;
        }
    }

    private boolean isValue(String token) {
        return !isOption(token) || isNegativeNumber(token);
    }

    private boolean isNegativeNumber(String token) throws NumberFormatException {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOption(String token) {
        return isLongOption(token) || isShortOption(token);
    }

    private boolean isShortOption(String token) {
        return token.startsWith("-") && token.length() >= 2 && hasOptionWithShortName(token.substring(1, 2));
    }

    private boolean isLongOption(String token) {
        if (!token.startsWith("-") || token.length() == 1) {
            return false;
        }
        int pos = token.indexOf("=");
        String t = pos == -1 ? token : token.substring(0, pos);
        if (!getMatchingOptions(t).isEmpty()) {
            return true;
        }
        if (getLongPrefix(token) != null && !token.startsWith(UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX)) {
            return true;
        }
        return false;
    }

    private void handleArgument(String token) {
        this.commandLine.addArgumentValue(token);
    }

    private void handleLongOption(String token) throws CLIException {
        if (token.indexOf(61) == -1) {
            handleLongOptionWithoutEqual(token);
        } else {
            handleLongOptionWithEqual(token);
        }
    }

    private void handleLongOptionWithoutEqual(String token) throws CLIException {
        List<Option> matchingOpts = getMatchingOptions(token);
        if (matchingOpts.isEmpty()) {
            handleArgument(token);
        } else {
            if (matchingOpts.size() > 1) {
                throw new AmbiguousOptionException(token, matchingOpts);
            }
            Option option = matchingOpts.get(0);
            handleOption(option);
        }
    }

    private void handleLongOptionWithEqual(String token) throws CLIException {
        int pos = token.indexOf(61);
        String value = token.substring(pos + 1);
        String opt = token.substring(0, pos);
        List<Option> matchingOpts = getMatchingOptions(opt);
        if (matchingOpts.isEmpty()) {
            handleArgument(token);
            return;
        }
        if (matchingOpts.size() > 1) {
            throw new AmbiguousOptionException(opt, matchingOpts);
        }
        Option option = matchingOpts.get(0);
        if (this.commandLine.acceptMoreValues(option)) {
            handleOption(option);
            this.commandLine.addRawValue(option, value);
            this.current = null;
            return;
        }
        throw new InvalidValueException(option, value);
    }

    private void handleShortAndLongOption(String token) throws CLIException {
        String t = stripLeadingHyphens(token);
        int pos = t.indexOf(61);
        if (t.length() == 1) {
            if (hasOptionWithShortName(t)) {
                handleOption(getOption(t));
                return;
            } else {
                handleArgument(token);
                return;
            }
        }
        if (pos == -1) {
            if (hasOptionWithShortName(t)) {
                handleOption(getOption(t));
                return;
            }
            if (!getMatchingOptions(t).isEmpty()) {
                handleLongOptionWithoutEqual(token);
                return;
            }
            String opt = getLongPrefix(t);
            if (opt != null) {
                if (this.commandLine.acceptMoreValues(getOption(opt))) {
                    handleOption(getOption(opt));
                    this.commandLine.addRawValue(getOption(opt), t.substring(opt.length()));
                    this.current = null;
                    return;
                }
                throw new InvalidValueException(getOption(opt), t.substring(opt.length()));
            }
            if (isAValidShortOption(t)) {
                String strip = t.substring(0, 1);
                handleOption(getOption(strip));
                this.commandLine.addRawValue(this.current, t.substring(1));
                this.current = null;
                return;
            }
            handleConcatenatedOptions(token);
            return;
        }
        String opt2 = t.substring(0, pos);
        String value = t.substring(pos + 1);
        if (opt2.length() != 1) {
            if (isAValidShortOption(opt2) && !hasOptionWithLongName(opt2)) {
                handleOption(getOption(opt2.substring(0, 1)));
                this.commandLine.addRawValue(this.current, opt2.substring(1) + "=" + value);
                this.current = null;
                return;
            }
            handleLongOptionWithEqual(token);
            return;
        }
        Option option = getOption(opt2);
        if (option != null) {
            if (this.commandLine.acceptMoreValues(option)) {
                handleOption(option);
                this.commandLine.addRawValue(option, value);
                this.current = null;
                return;
            }
            throw new InvalidValueException(option, value);
        }
        handleArgument(token);
    }

    private String getLongPrefix(String token) {
        String t = stripLeadingHyphens(token);
        String opt = null;
        int i = t.length() - 2;
        while (true) {
            if (i <= 1) {
                break;
            }
            String prefix = t.substring(0, i);
            if (!hasOptionWithLongName(prefix)) {
                i--;
            } else {
                opt = prefix;
                break;
            }
        }
        return opt;
    }

    private boolean hasOptionWithLongName(String name) {
        for (Option option : this.cli.getOptions()) {
            if (name.equalsIgnoreCase(option.getLongName())) {
                return true;
            }
        }
        return false;
    }

    private boolean hasOptionWithShortName(String name) {
        for (Option option : this.cli.getOptions()) {
            if (name.equalsIgnoreCase(option.getShortName())) {
                return true;
            }
        }
        return false;
    }

    private void handleOption(Option option) throws CLIException {
        checkRequiredValues();
        updateRequiredOptions(option);
        this.commandLine.setSeenInCommandLine(option);
        if (this.commandLine.acceptMoreValues(option)) {
            this.current = option;
        } else {
            this.current = null;
        }
    }

    private void updateRequiredOptions(Option option) {
        if (option.isRequired()) {
            this.expectedOpts.remove(option);
        }
    }

    public Option getOption(String opt) {
        String opt2 = stripLeadingHyphens(opt);
        for (Option option : this.cli.getOptions()) {
            if (opt2.equalsIgnoreCase(option.getShortName()) || opt2.equalsIgnoreCase(option.getLongName())) {
                return option;
            }
        }
        return null;
    }

    private boolean isAValidShortOption(String token) {
        String opt = token.substring(0, 1);
        Option option = getOption(opt);
        return option != null && this.commandLine.acceptMoreValues(option);
    }

    public List<Option> getMatchingOptions(String opt) {
        String opt2 = stripLeadingHyphens(opt);
        List<Option> matching = new ArrayList<>();
        List<Option> options = this.cli.getOptions();
        for (Option option : options) {
            if (opt2.equalsIgnoreCase(option.getLongName())) {
                return Collections.singletonList(option);
            }
        }
        for (Option option2 : options) {
            if (option2.getLongName() != null && option2.getLongName().startsWith(opt2)) {
                matching.add(option2);
            }
        }
        return matching;
    }

    protected void handleConcatenatedOptions(String token) throws CLIException {
        for (int i = 1; i < token.length(); i++) {
            String ch2 = String.valueOf(token.charAt(i));
            if (hasOptionWithShortName(ch2)) {
                handleOption(getOption(ch2));
                if (this.current != null && token.length() != i + 1) {
                    this.commandLine.addRawValue(this.current, token.substring(i + 1));
                    return;
                }
            } else {
                handleArgument(token);
                return;
            }
        }
    }
}
