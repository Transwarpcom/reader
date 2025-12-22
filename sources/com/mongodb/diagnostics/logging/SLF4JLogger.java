package com.mongodb.diagnostics.logging;

import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/diagnostics/logging/SLF4JLogger.class */
class SLF4JLogger implements Logger {
    private final org.slf4j.Logger delegate;

    SLF4JLogger(String name) {
        this.delegate = LoggerFactory.getLogger(name);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public String getName() {
        return this.delegate.getName();
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public boolean isTraceEnabled() {
        return this.delegate.isTraceEnabled();
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void trace(String msg) {
        this.delegate.trace(msg);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void trace(String msg, Throwable t) {
        this.delegate.trace(msg, t);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public boolean isDebugEnabled() {
        return this.delegate.isDebugEnabled();
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void debug(String msg) {
        this.delegate.debug(msg);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void debug(String msg, Throwable t) {
        this.delegate.debug(msg, t);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public boolean isInfoEnabled() {
        return this.delegate.isInfoEnabled();
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void info(String msg) {
        this.delegate.info(msg);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void info(String msg, Throwable t) {
        this.delegate.info(msg, t);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public boolean isWarnEnabled() {
        return this.delegate.isWarnEnabled();
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void warn(String msg) {
        this.delegate.warn(msg);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void warn(String msg, Throwable t) {
        this.delegate.warn(msg, t);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public boolean isErrorEnabled() {
        return this.delegate.isErrorEnabled();
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void error(String msg) {
        this.delegate.error(msg);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void error(String msg, Throwable t) {
        this.delegate.error(msg, t);
    }
}
