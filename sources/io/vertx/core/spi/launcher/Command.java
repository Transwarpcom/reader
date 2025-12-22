package io.vertx.core.spi.launcher;

import io.vertx.core.cli.CLIException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/launcher/Command.class */
public interface Command {
    void setUp(ExecutionContext executionContext) throws CLIException;

    void run() throws CLIException;

    void tearDown() throws CLIException;
}
