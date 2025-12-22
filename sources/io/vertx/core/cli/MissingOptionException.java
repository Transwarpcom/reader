package io.vertx.core.cli;

import java.util.Collection;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/MissingOptionException.class */
public class MissingOptionException extends CLIException {
    private final Collection<Option> expected;

    public MissingOptionException(Collection<Option> expected) {
        super("The option" + (expected.size() > 1 ? "s " : " ") + expected.stream().map((v0) -> {
            return v0.getName();
        }).collect(Collectors.toList()) + (expected.size() > 1 ? " are" : " is") + " required");
        this.expected = expected;
    }

    public Collection<Option> getExpected() {
        return this.expected;
    }
}
