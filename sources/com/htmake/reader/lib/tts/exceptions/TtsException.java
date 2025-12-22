package com.htmake.reader.lib.tts.exceptions;

/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/lib/tts/exceptions/TtsException.class */
public class TtsException extends RuntimeException {
    private TtsException(String message) {
        super(message);
    }

    public static TtsException of(String message) {
        return new TtsException(message);
    }
}
