package org.bson;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BSONException.class */
public class BSONException extends RuntimeException {
    private static final long serialVersionUID = -4415279469780082174L;
    private Integer errorCode;

    public BSONException(String msg) {
        super(msg);
        this.errorCode = null;
    }

    public BSONException(int errorCode, String msg) {
        super(msg);
        this.errorCode = null;
        this.errorCode = Integer.valueOf(errorCode);
    }

    public BSONException(String msg, Throwable t) {
        super(msg, t);
        this.errorCode = null;
    }

    public BSONException(int errorCode, String msg, Throwable t) {
        super(msg, t);
        this.errorCode = null;
        this.errorCode = Integer.valueOf(errorCode);
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public boolean hasErrorCode() {
        return this.errorCode != null;
    }
}
