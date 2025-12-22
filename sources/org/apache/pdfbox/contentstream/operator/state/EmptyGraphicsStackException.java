package org.apache.pdfbox.contentstream.operator.state;

import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/contentstream/operator/state/EmptyGraphicsStackException.class */
public final class EmptyGraphicsStackException extends IOException {
    private static final long serialVersionUID = 1;

    EmptyGraphicsStackException() {
        super("Cannot execute restore, the graphics stack is empty");
    }
}
