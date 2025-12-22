package com.mongodb.diagnostics.logging;

import java.util.logging.Level;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/diagnostics/logging/JULLogger.class */
class JULLogger implements Logger {
    private final java.util.logging.Logger delegate;

    JULLogger(String name) {
        this.delegate = java.util.logging.Logger.getLogger(name);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public String getName() {
        return this.delegate.getName();
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public boolean isTraceEnabled() {
        return isEnabled(Level.FINER);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void trace(String msg) {
        log(Level.FINER, msg);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void trace(String msg, Throwable t) {
        log(Level.FINER, msg, t);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public boolean isDebugEnabled() {
        return isEnabled(Level.FINE);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void debug(String msg) {
        log(Level.FINE, msg);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void debug(String msg, Throwable t) {
        log(Level.FINE, msg, t);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public boolean isInfoEnabled() {
        return this.delegate.isLoggable(Level.INFO);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void info(String msg) {
        log(Level.INFO, msg);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void info(String msg, Throwable t) {
        log(Level.INFO, msg, t);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public boolean isWarnEnabled() {
        return this.delegate.isLoggable(Level.WARNING);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void warn(String msg) {
        log(Level.WARNING, msg);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void warn(String msg, Throwable t) {
        log(Level.WARNING, msg, t);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public boolean isErrorEnabled() {
        return this.delegate.isLoggable(Level.SEVERE);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void error(String msg) {
        log(Level.SEVERE, msg);
    }

    @Override // com.mongodb.diagnostics.logging.Logger
    public void error(String msg, Throwable t) {
        log(Level.SEVERE, msg, t);
    }

    private boolean isEnabled(Level level) {
        return this.delegate.isLoggable(level);
    }

    private void log(Level level, String msg) {
        this.delegate.log(level, msg);
    }

    public void log(Level level, String msg, Throwable t) {
        this.delegate.log(level, msg, t);
    }
}
