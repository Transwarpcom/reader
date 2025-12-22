package io.vertx.ext.web.impl;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.ParsedHeaderValue;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/HeaderParser.class */
public class HeaderParser {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HeaderParser.class);
    private static final Comparator<ParsedHeaderValue> HEADER_SORTER = (left, right) -> {
        return right.weightedOrder() - left.weightedOrder();
    };

    public static <T extends ParsedHeaderValue> List<T> convertToParsedHeaderValues(String unparsedHeaderValue, Function<String, T> objectCreator) {
        return split(unparsedHeaderValue, ',', objectCreator);
    }

    public static <T extends ParsedHeaderValue> List<T> sort(List<T> headers) {
        headers.sort(HEADER_SORTER);
        return headers;
    }

    public static void parseHeaderValue(String headerContent, Consumer<String> valueCallback, Consumer<Float> weightCallback, BiConsumer<String, String> parameterCallback) {
        int paramIndex = headerContent.indexOf(59);
        if (paramIndex < 0) {
            valueCallback.accept(headerContent);
            return;
        }
        valueCallback.accept(headerContent.substring(0, paramIndex));
        if (paramIndex < headerContent.length()) {
            split(headerContent.substring(paramIndex + 1), ';', part -> {
                int idx = part.indexOf(61);
                if (idx != -1) {
                    String key = part.substring(0, idx);
                    String val = part.substring(idx + 1);
                    if (OperatorName.SAVE.equalsIgnoreCase(key)) {
                        try {
                            weightCallback.accept(Float.valueOf(Float.parseFloat(val)));
                            return null;
                        } catch (NumberFormatException e) {
                            log.info("Found a \"q\" parameter with value \"{}\" which was unparsable", val);
                            return null;
                        }
                    }
                    parameterCallback.accept(key, unquote(val));
                    return null;
                }
                parameterCallback.accept(part, null);
                return null;
            });
        }
    }

    public static void parseMIME(String headerContent, Consumer<String> componentCallback, Consumer<String> subcomponentCallback) {
        int slashIndex = headerContent.indexOf(47);
        int paramIndex = headerContent.indexOf(59, slashIndex + 1);
        if (slashIndex < 0) {
            componentCallback.accept("*");
        } else {
            componentCallback.accept(headerContent.substring(0, slashIndex).toLowerCase());
        }
        if (paramIndex < 0) {
            subcomponentCallback.accept(headerContent.substring(slashIndex + 1));
        } else {
            subcomponentCallback.accept(headerContent.substring(slashIndex + 1, paramIndex).toLowerCase());
        }
    }

    public static List<String> parseLanguageValue(String value) {
        if (value == null || value.length() == 0) {
            return Collections.emptyList();
        }
        List<String> parts = new LinkedList<>();
        int start = 0;
        for (int i = 0; i < value.length(); i++) {
            char ch2 = value.charAt(i);
            if (start == i && ch2 == ' ') {
                start++;
            } else if (ch2 == '-' || ch2 == '_') {
                int end = i;
                for (int j = i - 1; j >= start && value.charAt(j) == ' '; j--) {
                    end--;
                }
                if (end - start > 0) {
                    parts.add(value.substring(start, end));
                    if (parts.size() == 3) {
                        return parts;
                    }
                }
                start = i + 1;
            }
        }
        if (start < value.length()) {
            int end2 = value.length();
            for (int j2 = value.length() - 1; j2 >= start && value.charAt(j2) == ' '; j2--) {
                end2--;
            }
            if (end2 - start > 0) {
                parts.add(value.substring(start, end2));
            }
        }
        return parts;
    }

    private static <T> List<T> split(String header, char split, Function<String, T> factory) {
        if (header == null || header.length() == 0) {
            return Collections.emptyList();
        }
        List<T> parts = new LinkedList<>();
        boolean quote = false;
        int start = 0;
        char last = 0;
        for (int i = 0; i < header.length(); i++) {
            char ch2 = header.charAt(i);
            if (start == i && ch2 == ' ') {
                start++;
            } else {
                if (ch2 == '\"' && last != '\\') {
                    quote = !quote;
                }
                last = ch2;
                if (!quote && ch2 == split) {
                    int end = i;
                    for (int j = i - 1; j >= start && header.charAt(j) == ' '; j--) {
                        end--;
                    }
                    if (end - start > 0) {
                        parts.add(factory.apply(header.substring(start, end)));
                    }
                    start = i + 1;
                }
            }
        }
        if (start < header.length()) {
            int end2 = header.length();
            for (int j2 = header.length() - 1; j2 >= start && header.charAt(j2) == ' '; j2--) {
                end2--;
            }
            if (end2 - start > 0) {
                parts.add(factory.apply(header.substring(start, end2)));
            }
        }
        return parts;
    }

    private static String unquote(String value) {
        if (value == null || value.length() == 0) {
            return value;
        }
        StringBuilder sb = null;
        int start = 0;
        int end = value.length();
        if (value.charAt(0) == '\"') {
            start = 0 + 1;
        }
        if (value.charAt(end - 1) == '\"') {
            end--;
        }
        for (int i = start; i < end; i++) {
            if (value.charAt(i) == '\\') {
                if (sb == null) {
                    sb = new StringBuilder(value.substring(start, i));
                }
            } else if (sb != null) {
                sb.append(value.charAt(i));
            }
        }
        if (sb != null) {
            return sb.toString();
        }
        if (end - start != value.length()) {
            return value.substring(start, end);
        }
        return value;
    }
}
