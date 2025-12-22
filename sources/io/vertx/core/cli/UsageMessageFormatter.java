package io.vertx.core.cli;

import cn.hutool.core.text.StrPool;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/UsageMessageFormatter.class */
public class UsageMessageFormatter {
    public static final int DEFAULT_WIDTH = 80;
    public static final int DEFAULT_LEFT_PAD = 1;
    public static final int DEFAULT_DESC_PAD = 3;
    public static final String DEFAULT_USAGE_PREFIX = "Usage: ";
    public static final String DEFAULT_OPT_PREFIX = "-";
    public static final String DEFAULT_LONG_OPT_PREFIX = "--";
    public static final String DEFAULT_LONG_OPT_SEPARATOR = " ";
    public static final String DEFAULT_ARG_NAME = "arg";
    private int width = 80;
    private int leftPad = 1;
    private int descPad = 3;
    private String usagePrefix = DEFAULT_USAGE_PREFIX;
    private String newLine = System.lineSeparator();
    private String defaultOptionPrefix = "-";
    private String defaultLongOptPrefix = DEFAULT_LONG_OPT_PREFIX;
    private String defaultArgName = DEFAULT_ARG_NAME;
    private String longOptSeparator = " ";
    protected Comparator<Option> optionComparator = (opt1, opt2) -> {
        return opt1.getName().compareToIgnoreCase(opt2.getName());
    };

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public void setLeftPadding(int padding) {
        this.leftPad = padding;
    }

    public int getLeftPadding() {
        return this.leftPad;
    }

    public void setDescPadding(int padding) {
        this.descPad = padding;
    }

    public int getDescPadding() {
        return this.descPad;
    }

    public void setUsagePrefix(String prefix) {
        this.usagePrefix = prefix;
    }

    public String getUsagePrefix() {
        return this.usagePrefix;
    }

    public void setNewLine(String newline) {
        this.newLine = newline;
    }

    public String getNewLine() {
        return this.newLine;
    }

    public void setOptionPrefix(String prefix) {
        this.defaultOptionPrefix = prefix;
    }

    public String getOptionPrefix() {
        return this.defaultOptionPrefix;
    }

    public void setLongOptionPrefix(String prefix) {
        this.defaultLongOptPrefix = prefix;
    }

    public String getLongOptionPrefix() {
        return this.defaultLongOptPrefix;
    }

    public void setLongOptionSeparator(String longOptSeparator) {
        this.longOptSeparator = longOptSeparator;
    }

    public String getLongOptionSeparator() {
        return this.longOptSeparator;
    }

    public void setArgName(String name) {
        this.defaultArgName = name;
    }

    public String getArgName() {
        return this.defaultArgName;
    }

    public Comparator<Option> getOptionComparator() {
        return this.optionComparator;
    }

    public void setOptionComparator(Comparator<Option> comparator) {
        this.optionComparator = comparator;
    }

    protected void appendOption(StringBuilder buff, Option option) {
        if (option.isHidden()) {
            return;
        }
        if (!option.isRequired()) {
            buff.append("[");
        }
        if (!isNullOrEmpty(option.getShortName())) {
            buff.append("-").append(option.getShortName());
        } else {
            buff.append(DEFAULT_LONG_OPT_PREFIX).append(option.getLongName());
        }
        if (!option.getChoices().isEmpty()) {
            buff.append(isNullOrEmpty(option.getShortName()) ? getLongOptionSeparator() : " ");
            buff.append((String) option.getChoices().stream().collect(Collectors.joining(", ", StrPool.DELIM_START, "}")));
        } else if (option.acceptValue() && (option.getArgName() == null || option.getArgName().length() != 0)) {
            buff.append(isNullOrEmpty(option.getShortName()) ? getLongOptionSeparator() : " ");
            buff.append("<").append(option.getArgName() != null ? option.getArgName() : getArgName()).append(">");
        }
        if (!option.isRequired()) {
            buff.append("]");
        }
    }

    protected void appendArgument(StringBuilder buff, Argument argument, boolean required) {
        if (argument.isHidden()) {
            return;
        }
        if (!required) {
            buff.append("[");
        }
        buff.append(argument.getArgName());
        if (argument.isMultiValued()) {
            buff.append("...");
        }
        if (!required) {
            buff.append("]");
        }
    }

    public void usage(StringBuilder builder, CLI cli) {
        usage(builder, null, cli);
    }

    public void usage(StringBuilder builder, String prefix, CLI cli) {
        computeUsageLine(builder, prefix, cli);
        if (cli.getSummary() != null && cli.getSummary().trim().length() > 0) {
            buildWrapped(builder, "\n" + cli.getSummary());
        }
        if (cli.getDescription() != null && cli.getDescription().trim().length() > 0) {
            buildWrapped(builder, "\n" + cli.getDescription());
        }
        builder.append("\n");
        if (cli.getOptions().isEmpty() && cli.getArguments().isEmpty()) {
            return;
        }
        builder.append("Options and Arguments:\n");
        computeOptionsAndArguments(builder, cli.getOptions(), cli.getArguments());
    }

    public void computeUsage(StringBuilder buffer, String cmdLineSyntax) throws IOException {
        int argPos = cmdLineSyntax.indexOf(32) + 1;
        buildWrapped(buffer, getUsagePrefix().length() + argPos, getUsagePrefix() + cmdLineSyntax);
    }

    public void computeUsageLine(StringBuilder buffer, String prefix, CLI cli) throws IOException {
        StringBuilder buff;
        if (prefix == null) {
            buff = new StringBuilder(getUsagePrefix());
        } else {
            buff = new StringBuilder(getUsagePrefix()).append(prefix);
            if (!prefix.endsWith(" ")) {
                buff.append(" ");
            }
        }
        buff.append(cli.getName()).append(" ");
        if (getOptionComparator() != null) {
            Collections.sort(cli.getOptions(), getOptionComparator());
        }
        for (Option option : cli.getOptions()) {
            appendOption(buff, option);
            buff.append(" ");
        }
        for (Argument arg : cli.getArguments()) {
            appendArgument(buff, arg, arg.isRequired());
            buff.append(" ");
        }
        buildWrapped(buffer, buff.toString().indexOf(32) + 1, buff.toString());
    }

    public void computeOptionsAndArguments(StringBuilder buffer, List<Option> options, List<Argument> arguments) {
        renderOptionsAndArguments(buffer, options, arguments);
        buffer.append(this.newLine);
    }

    public void buildWrapped(StringBuilder buffer, String text) throws IOException {
        buildWrapped(buffer, 0, text);
    }

    public void buildWrapped(StringBuilder buffer, int nextLineTabStop, String text) throws IOException {
        renderWrappedTextBlock(buffer, this.width, nextLineTabStop, text);
        buffer.append(this.newLine);
    }

    protected StringBuilder renderCommands(StringBuilder sb, Collection<CLI> commands) {
        String lpad = createPadding(this.leftPad);
        String dpad = createPadding(this.descPad);
        int max = 0;
        List<StringBuilder> prefixList = new ArrayList<>();
        for (CLI command : commands) {
            if (!command.isHidden()) {
                StringBuilder buf = new StringBuilder();
                buf.append(lpad).append("   ").append(command.getName());
                prefixList.add(buf);
                max = buf.length() > max ? buf.length() : max;
            }
        }
        int x = 0;
        Iterator<CLI> it = commands.iterator();
        while (it.hasNext()) {
            CLI command2 = it.next();
            if (!command2.isHidden()) {
                int i = x;
                x++;
                StringBuilder buf2 = new StringBuilder(prefixList.get(i).toString());
                if (buf2.length() < max) {
                    buf2.append(createPadding(max - buf2.length()));
                }
                buf2.append(dpad);
                int nextLineTabStop = max + this.descPad;
                buf2.append(command2.getSummary());
                renderWrappedText(sb, this.width, nextLineTabStop, buf2.toString());
                if (it.hasNext()) {
                    sb.append(getNewLine());
                }
            }
        }
        return sb;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    protected StringBuilder renderOptionsAndArguments(StringBuilder sb, List<Option> options, List<Argument> arguments) {
        String lpad = createPadding(this.leftPad);
        String dpad = createPadding(this.descPad);
        int max = 0;
        List<StringBuilder> prefixList = new ArrayList<>();
        if (getOptionComparator() != null) {
            Collections.sort(options, getOptionComparator());
        }
        for (Option option : options) {
            StringBuilder buf = new StringBuilder();
            if (!option.isHidden()) {
                if (isNullOrEmpty(option.getShortName())) {
                    buf.append(lpad).append("   ").append(getLongOptionPrefix()).append(option.getLongName());
                } else {
                    buf.append(lpad).append(getOptionPrefix()).append(option.getShortName());
                    if (!isNullOrEmpty(option.getLongName())) {
                        buf.append(',').append(getLongOptionPrefix()).append(option.getLongName());
                    }
                }
                if (!option.getChoices().isEmpty()) {
                    buf.append(!isNullOrEmpty(option.getLongName()) ? this.longOptSeparator : " ");
                    buf.append((String) option.getChoices().stream().collect(Collectors.joining(", ", StrPool.DELIM_START, "}")));
                } else if (option.acceptValue()) {
                    String argName = option.getArgName();
                    if (argName != null && argName.length() == 0) {
                        buf.append(' ');
                    } else {
                        buf.append(!isNullOrEmpty(option.getLongName()) ? this.longOptSeparator : " ");
                        buf.append("<").append(argName != null ? option.getArgName() : getArgName()).append(">");
                    }
                }
                prefixList.add(buf);
                max = buf.length() > max ? buf.length() : max;
            }
        }
        for (Argument argument : arguments) {
            StringBuilder buf2 = new StringBuilder();
            if (!argument.isHidden()) {
                buf2.append(lpad).append("<").append(argument.getArgName()).append(">");
                prefixList.add(buf2);
                max = buf2.length() > max ? buf2.length() : max;
            }
        }
        int x = 0;
        Iterator<Option> it = options.iterator();
        while (it.hasNext()) {
            Option option2 = it.next();
            if (!option2.isHidden()) {
                int i = x;
                x++;
                StringBuilder optBuf = new StringBuilder(prefixList.get(i).toString());
                if (optBuf.length() < max) {
                    optBuf.append(createPadding(max - optBuf.length()));
                }
                optBuf.append(dpad);
                int nextLineTabStop = max + this.descPad;
                if (option2.getDescription() != null) {
                    optBuf.append(option2.getDescription());
                }
                renderWrappedText(sb, this.width, nextLineTabStop, optBuf.toString());
                if (it.hasNext()) {
                    sb.append(getNewLine());
                }
            }
        }
        if (!options.isEmpty() && !arguments.isEmpty()) {
            sb.append(getNewLine());
        }
        Iterator<Argument> it2 = arguments.iterator();
        while (it2.hasNext()) {
            Argument argument2 = it2.next();
            if (!argument2.isHidden()) {
                int i2 = x;
                x++;
                StringBuilder argBuf = new StringBuilder(prefixList.get(i2).toString());
                if (argBuf.length() < max) {
                    argBuf.append(createPadding(max - argBuf.length()));
                }
                argBuf.append(dpad);
                int nextLineTabStop2 = max + this.descPad;
                if (argument2.getDescription() != null) {
                    argBuf.append(argument2.getDescription());
                }
                renderWrappedText(sb, this.width, nextLineTabStop2, argBuf.toString());
                if (it2.hasNext()) {
                    sb.append(getNewLine());
                }
            }
        }
        return sb;
    }

    protected StringBuilder renderWrappedText(StringBuilder sb, int width, int nextLineTabStop, String text) {
        int pos = findWrapPos(text, width, 0);
        if (pos == -1) {
            sb.append(rtrim(text));
            return sb;
        }
        sb.append(rtrim(text.substring(0, pos))).append(getNewLine());
        if (nextLineTabStop >= width) {
            nextLineTabStop = 1;
        }
        String padding = createPadding(nextLineTabStop);
        while (true) {
            text = padding + text.substring(pos).trim();
            pos = findWrapPos(text, width, 0);
            if (pos == -1) {
                sb.append(text);
                return sb;
            }
            if (text.length() > width && pos == nextLineTabStop - 1) {
                pos = width;
            }
            sb.append(rtrim(text.substring(0, pos))).append(getNewLine());
        }
    }

    public Appendable renderWrappedTextBlock(StringBuilder sb, int width, int nextLineTabStop, String text) throws IOException {
        try {
            BufferedReader in = new BufferedReader(new StringReader(text));
            boolean firstLine = true;
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                if (!firstLine) {
                    sb.append(getNewLine());
                } else {
                    firstLine = false;
                }
                renderWrappedText(sb, width, nextLineTabStop, line);
            }
        } catch (IOException e) {
        }
        return sb;
    }

    public static int findWrapPos(String text, int width, int startPos) {
        char c;
        int pos = text.indexOf(10, startPos);
        if (pos != -1 && pos <= width) {
            return pos + 1;
        }
        int pos2 = text.indexOf(9, startPos);
        if (pos2 != -1 && pos2 <= width) {
            return pos2 + 1;
        }
        if (startPos + width >= text.length()) {
            return -1;
        }
        int pos3 = startPos + width;
        while (pos3 >= startPos && (c = text.charAt(pos3)) != ' ' && c != '\n' && c != '\r') {
            pos3--;
        }
        if (pos3 > startPos) {
            return pos3;
        }
        int pos4 = startPos + width;
        if (pos4 == text.length()) {
            return -1;
        }
        return pos4;
    }

    public static String createPadding(int len) {
        char[] padding = new char[len];
        Arrays.fill(padding, ' ');
        return new String(padding);
    }

    public static String rtrim(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int pos = s.length();
        while (pos > 0 && Character.isWhitespace(s.charAt(pos - 1))) {
            pos--;
        }
        return s.substring(0, pos);
    }
}
