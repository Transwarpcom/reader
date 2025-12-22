package io.vertx.core.impl.launcher;

import io.vertx.core.ServiceHelper;
import io.vertx.core.spi.launcher.CommandFactory;
import io.vertx.core.spi.launcher.CommandFactoryLookup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/ServiceCommandFactoryLoader.class */
public class ServiceCommandFactoryLoader implements CommandFactoryLookup {
    private Collection<CommandFactory> commands;

    public ServiceCommandFactoryLoader() {
        this.commands = ServiceHelper.loadFactories(CommandFactory.class, getClass().getClassLoader());
    }

    public ServiceCommandFactoryLoader(ClassLoader loader) {
        this.commands = ServiceHelper.loadFactories(CommandFactory.class, loader);
    }

    @Override // io.vertx.core.spi.launcher.CommandFactoryLookup
    public Collection<CommandFactory<?>> lookup() {
        List<CommandFactory<?>> list = new ArrayList<>();
        Stream<CommandFactory> stream = this.commands.stream();
        list.getClass();
        stream.forEach((v1) -> {
            r1.add(v1);
        });
        return list;
    }
}
