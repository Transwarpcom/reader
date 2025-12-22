package io.netty.handler.codec.http;

import io.netty.util.AsciiString;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/HttpStatusClass.class */
public enum HttpStatusClass {
    INFORMATIONAL(100, 200, "Informational"),
    SUCCESS(200, OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT, "Success"),
    REDIRECTION(OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT, OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL, "Redirection"),
    CLIENT_ERROR(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL, 500, "Client Error"),
    SERVER_ERROR(500, OS2WindowsMetricsTable.WEIGHT_CLASS_SEMI_BOLD, "Server Error"),
    UNKNOWN(0, 0, "Unknown Status") { // from class: io.netty.handler.codec.http.HttpStatusClass.1
        @Override // io.netty.handler.codec.http.HttpStatusClass
        public boolean contains(int code) {
            return code < 100 || code >= 600;
        }
    };

    private final int min;
    private final int max;
    private final AsciiString defaultReasonPhrase;

    public static HttpStatusClass valueOf(int code) {
        if (INFORMATIONAL.contains(code)) {
            return INFORMATIONAL;
        }
        if (SUCCESS.contains(code)) {
            return SUCCESS;
        }
        if (REDIRECTION.contains(code)) {
            return REDIRECTION;
        }
        if (CLIENT_ERROR.contains(code)) {
            return CLIENT_ERROR;
        }
        if (SERVER_ERROR.contains(code)) {
            return SERVER_ERROR;
        }
        return UNKNOWN;
    }

    public static HttpStatusClass valueOf(CharSequence code) {
        if (code != null && code.length() == 3) {
            char c0 = code.charAt(0);
            return (isDigit(c0) && isDigit(code.charAt(1)) && isDigit(code.charAt(2))) ? valueOf(digit(c0) * 100) : UNKNOWN;
        }
        return UNKNOWN;
    }

    private static int digit(char c) {
        return c - '0';
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    HttpStatusClass(int min, int max, String defaultReasonPhrase) {
        this.min = min;
        this.max = max;
        this.defaultReasonPhrase = AsciiString.cached(defaultReasonPhrase);
    }

    public boolean contains(int code) {
        return code >= this.min && code < this.max;
    }

    AsciiString defaultReasonPhrase() {
        return this.defaultReasonPhrase;
    }
}
