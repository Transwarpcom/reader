package org.antlr.v4.runtime.misc;

import java.util.concurrent.CancellationException;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/ParseCancellationException.class */
public class ParseCancellationException extends CancellationException {
    public ParseCancellationException() {
    }

    public ParseCancellationException(String message) {
        super(message);
    }

    public ParseCancellationException(Throwable cause) {
        initCause(cause);
    }

    public ParseCancellationException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}
