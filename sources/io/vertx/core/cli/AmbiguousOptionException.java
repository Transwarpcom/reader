package io.vertx.core.cli;

import java.util.List;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/AmbiguousOptionException.class */
public class AmbiguousOptionException extends CLIException {
    private final List<Option> options;
    private final String token;

    public AmbiguousOptionException(String token, List<Option> matchingOpts) {
        super("Ambiguous argument in command line: '" + token + "' matches " + matchingOpts.stream().map((v0) -> {
            return v0.getName();
        }).collect(Collectors.toList()));
        this.token = token;
        this.options = matchingOpts;
    }

    public List<Option> getOptions() {
        return this.options;
    }

    public String getToken() {
        return this.token;
    }
}
