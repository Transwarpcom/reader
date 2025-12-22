package io.vertx.core.spi.launcher;

import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.annotations.CLIConfigurator;
import io.vertx.core.cli.impl.ReflectionUtils;
import io.vertx.core.spi.launcher.Command;
import java.util.function.Supplier;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/launcher/DefaultCommandFactory.class */
public class DefaultCommandFactory<C extends Command> implements CommandFactory<C> {
    private final Class<C> clazz;
    private final Supplier<C> supplier;

    @Deprecated
    public DefaultCommandFactory(Class<C> clazz) {
        this(clazz, () -> {
            return (Command) ReflectionUtils.newInstance(clazz);
        });
    }

    public DefaultCommandFactory(Class<C> clazz, Supplier<C> supplier) {
        this.clazz = clazz;
        this.supplier = supplier;
    }

    @Override // io.vertx.core.spi.launcher.CommandFactory
    public C create(CommandLine cl) {
        return this.supplier.get();
    }

    @Override // io.vertx.core.spi.launcher.CommandFactory
    public CLI define() {
        return CLIConfigurator.define(this.clazz);
    }
}
