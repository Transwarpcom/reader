package com.script;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/ScriptException.class */
public class ScriptException extends Exception {
    private int columnNumber;
    private String fileName;
    private int lineNumber;

    public ScriptException(String s) {
        super(s);
        this.fileName = null;
        this.lineNumber = -1;
        this.columnNumber = -1;
    }

    public ScriptException(Exception e) {
        super(e);
        this.fileName = null;
        this.lineNumber = -1;
        this.columnNumber = -1;
    }

    public ScriptException(String message, String fileName2, int lineNumber2) {
        super(message);
        this.fileName = fileName2;
        this.lineNumber = lineNumber2;
        this.columnNumber = -1;
    }

    public ScriptException(String message, String fileName2, int lineNumber2, int columnNumber2) {
        super(message);
        this.fileName = fileName2;
        this.lineNumber = lineNumber2;
        this.columnNumber = columnNumber2;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String ret = super.getMessage();
        if (this.fileName == null) {
            return ret;
        }
        String ret2 = ret + " in " + this.fileName;
        if (this.lineNumber != -1) {
            ret2 = ret2 + " at line number " + this.lineNumber;
        }
        if (this.columnNumber != -1) {
            return ret2 + " at column number " + this.columnNumber;
        }
        return ret2;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public String getFileName() {
        return this.fileName;
    }
}
