package uk.org.lidalia.sysoutslf4j.system;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import uk.org.lidalia.sysoutslf4j.common.LoggerAppender;
import uk.org.lidalia.sysoutslf4j.common.SLF4JPrintStream;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/system/SLF4JPrintStreamImpl.class */
public final class SLF4JPrintStreamImpl extends PrintStream implements SLF4JPrintStream {
    private final PrintStream originalPrintStream;
    private final SLF4JPrintStreamDelegate delegate;

    SLF4JPrintStreamImpl(PrintStream originalPrintStream, SLF4JPrintStreamDelegate delegate) {
        super(new ByteArrayOutputStream());
        this.originalPrintStream = originalPrintStream;
        this.delegate = delegate;
    }

    @Override // java.io.PrintStream
    public synchronized void println(String string) {
        this.delegate.delegatePrintln(string);
    }

    @Override // java.io.PrintStream
    public synchronized void println(Object object) {
        this.delegate.delegatePrintln(String.valueOf(object));
    }

    @Override // java.io.PrintStream
    public synchronized void println() {
        this.delegate.delegatePrintln("");
    }

    @Override // java.io.PrintStream
    public synchronized void println(boolean bool) {
        this.delegate.delegatePrintln(String.valueOf(bool));
    }

    @Override // java.io.PrintStream
    public synchronized void println(char character) {
        this.delegate.delegatePrintln(String.valueOf(character));
    }

    @Override // java.io.PrintStream
    public synchronized void println(char[] charArray) {
        this.delegate.delegatePrintln(String.valueOf(charArray));
    }

    @Override // java.io.PrintStream
    public synchronized void println(double doub) {
        this.delegate.delegatePrintln(String.valueOf(doub));
    }

    @Override // java.io.PrintStream
    public synchronized void println(float floa) {
        this.delegate.delegatePrintln(String.valueOf(floa));
    }

    @Override // java.io.PrintStream
    public synchronized void println(int integer) {
        this.delegate.delegatePrintln(String.valueOf(integer));
    }

    @Override // java.io.PrintStream
    public synchronized void println(long lon) {
        this.delegate.delegatePrintln(String.valueOf(lon));
    }

    @Override // java.io.PrintStream, java.lang.Appendable
    public synchronized PrintStream append(char character) {
        this.delegate.delegatePrint(String.valueOf(character));
        return this;
    }

    @Override // java.io.PrintStream, java.lang.Appendable
    public synchronized PrintStream append(CharSequence csq, int start, int end) {
        this.delegate.delegatePrint(csq.subSequence(start, end).toString());
        return this;
    }

    @Override // java.io.PrintStream, java.lang.Appendable
    public synchronized PrintStream append(CharSequence csq) {
        this.delegate.delegatePrint(csq.toString());
        return this;
    }

    @Override // java.io.PrintStream
    public boolean checkError() {
        return this.originalPrintStream.checkError();
    }

    @Override // java.io.PrintStream
    protected void setError() {
        this.originalPrintStream.println("WARNING - calling setError on SLFJPrintStream does nothing");
    }

    @Override // java.io.PrintStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.originalPrintStream.close();
    }

    @Override // java.io.PrintStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() {
        this.originalPrintStream.flush();
    }

    @Override // java.io.PrintStream
    public synchronized PrintStream format(Locale locale, String format, Object... args) {
        String string = String.format(locale, format, args);
        this.delegate.delegatePrint(string);
        return this;
    }

    @Override // java.io.PrintStream
    public synchronized PrintStream format(String format, Object... args) {
        return format(Locale.getDefault(), format, args);
    }

    @Override // java.io.PrintStream
    public synchronized void print(boolean bool) {
        this.delegate.delegatePrint(String.valueOf(bool));
    }

    @Override // java.io.PrintStream
    public synchronized void print(char character) {
        this.delegate.delegatePrint(String.valueOf(character));
    }

    @Override // java.io.PrintStream
    public synchronized void print(char[] charArray) {
        this.delegate.delegatePrint(String.valueOf(charArray));
    }

    @Override // java.io.PrintStream
    public synchronized void print(double doubl) {
        this.delegate.delegatePrint(String.valueOf(doubl));
    }

    @Override // java.io.PrintStream
    public synchronized void print(float floa) {
        this.delegate.delegatePrint(String.valueOf(floa));
    }

    @Override // java.io.PrintStream
    public synchronized void print(int integer) {
        this.delegate.delegatePrint(String.valueOf(integer));
    }

    @Override // java.io.PrintStream
    public synchronized void print(long lon) {
        this.delegate.delegatePrint(String.valueOf(lon));
    }

    @Override // java.io.PrintStream
    public synchronized void print(Object object) {
        this.delegate.delegatePrint(String.valueOf(object));
    }

    @Override // java.io.PrintStream
    public synchronized void print(String string) {
        this.delegate.delegatePrint(String.valueOf(string));
    }

    @Override // java.io.PrintStream
    public synchronized PrintStream printf(Locale locale, String format, Object... args) {
        return format(locale, format, args);
    }

    @Override // java.io.PrintStream
    public synchronized PrintStream printf(String format, Object... args) {
        return format(format, args);
    }

    @Override // java.io.PrintStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] buf, int off, int len) {
        this.originalPrintStream.write(buf, off, len);
    }

    @Override // java.io.PrintStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(int integer) {
        this.originalPrintStream.write(integer);
    }

    @Override // java.io.PrintStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bytes) throws IOException {
        this.originalPrintStream.write(bytes);
    }

    @Override // uk.org.lidalia.sysoutslf4j.common.SLF4JPrintStream
    public void registerLoggerAppender(Object loggerAppenderObject) {
        LoggerAppender loggerAppender = LoggerAppenderProxy.wrap(loggerAppenderObject);
        this.delegate.registerLoggerAppender(loggerAppender);
    }

    @Override // uk.org.lidalia.sysoutslf4j.common.SLF4JPrintStream
    public void deregisterLoggerAppender() {
        this.delegate.deregisterLoggerAppender();
    }

    @Override // uk.org.lidalia.sysoutslf4j.common.SLF4JPrintStream
    public PrintStream getOriginalPrintStream() {
        return this.originalPrintStream;
    }
}
