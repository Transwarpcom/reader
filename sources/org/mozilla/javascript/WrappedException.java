package org.mozilla.javascript;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/WrappedException.class */
public class WrappedException extends EvaluatorException {
    private static final long serialVersionUID = -1551979216966520648L;
    private Throwable exception;

    public WrappedException(Throwable exception) {
        super("Wrapped " + exception);
        this.exception = exception;
        initCause(exception);
        int[] linep = {0};
        String sourceName = Context.getSourcePositionFromStack(linep);
        int lineNumber = linep[0];
        if (sourceName != null) {
            initSourceName(sourceName);
        }
        if (lineNumber != 0) {
            initLineNumber(lineNumber);
        }
    }

    public Throwable getWrappedException() {
        return this.exception;
    }

    @Deprecated
    public Object unwrap() {
        return getWrappedException();
    }
}
