package org.bson.diagnostics;

import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/diagnostics/SLF4JLogger.class */
class SLF4JLogger implements Logger {
    private final org.slf4j.Logger delegate;

    SLF4JLogger(String name) {
        this.delegate = LoggerFactory.getLogger(name);
    }

    @Override // org.bson.diagnostics.Logger
    public String getName() {
        return this.delegate.getName();
    }

    @Override // org.bson.diagnostics.Logger
    public boolean isTraceEnabled() {
        return this.delegate.isTraceEnabled();
    }

    @Override // org.bson.diagnostics.Logger
    public void trace(String msg) {
        this.delegate.trace(msg);
    }

    @Override // org.bson.diagnostics.Logger
    public void trace(String msg, Throwable t) {
        this.delegate.trace(msg, t);
    }

    @Override // org.bson.diagnostics.Logger
    public boolean isDebugEnabled() {
        return this.delegate.isDebugEnabled();
    }

    @Override // org.bson.diagnostics.Logger
    public void debug(String msg) {
        this.delegate.debug(msg);
    }

    @Override // org.bson.diagnostics.Logger
    public void debug(String msg, Throwable t) {
        this.delegate.debug(msg, t);
    }

    @Override // org.bson.diagnostics.Logger
    public boolean isInfoEnabled() {
        return this.delegate.isInfoEnabled();
    }

    @Override // org.bson.diagnostics.Logger
    public void info(String msg) {
        this.delegate.info(msg);
    }

    @Override // org.bson.diagnostics.Logger
    public void info(String msg, Throwable t) {
        this.delegate.info(msg, t);
    }

    @Override // org.bson.diagnostics.Logger
    public boolean isWarnEnabled() {
        return this.delegate.isWarnEnabled();
    }

    @Override // org.bson.diagnostics.Logger
    public void warn(String msg) {
        this.delegate.warn(msg);
    }

    @Override // org.bson.diagnostics.Logger
    public void warn(String msg, Throwable t) {
        this.delegate.warn(msg, t);
    }

    @Override // org.bson.diagnostics.Logger
    public boolean isErrorEnabled() {
        return this.delegate.isErrorEnabled();
    }

    @Override // org.bson.diagnostics.Logger
    public void error(String msg) {
        this.delegate.error(msg);
    }

    @Override // org.bson.diagnostics.Logger
    public void error(String msg, Throwable t) {
        this.delegate.error(msg, t);
    }
}
