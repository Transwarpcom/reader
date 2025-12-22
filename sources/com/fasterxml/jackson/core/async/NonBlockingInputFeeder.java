package com.fasterxml.jackson.core.async;

/* loaded from: reader.jar:BOOT-INF/lib/jackson-core-2.9.9.jar:com/fasterxml/jackson/core/async/NonBlockingInputFeeder.class */
public interface NonBlockingInputFeeder {
    boolean needMoreInput();

    void endOfInput();
}
