package io.vertx.core.cli.converters;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/converters/Converter.class */
public interface Converter<T> {
    T fromString(String str);
}
