package uk.org.lidalia.sysoutslf4j.system;

import java.io.PrintStream;
import uk.org.lidalia.sysoutslf4j.common.PrintStreamCoordinator;
import uk.org.lidalia.sysoutslf4j.common.SLF4JPrintStream;
import uk.org.lidalia.sysoutslf4j.common.SystemOutput;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/system/PrintStreamCoordinatorImpl.class */
public final class PrintStreamCoordinatorImpl implements PrintStreamCoordinator {
    @Override // uk.org.lidalia.sysoutslf4j.common.PrintStreamCoordinator
    public void replaceSystemOutputsWithSLF4JPrintStreams() {
        for (SystemOutput systemOutput : SystemOutput.valuesCustom()) {
            replaceSystemOutputWithSLF4JPrintStream(systemOutput);
        }
    }

    private static void replaceSystemOutputWithSLF4JPrintStream(SystemOutput systemOutput) {
        SLF4JPrintStreamImpl slf4jPrintStream = buildSLF4JPrintStream(systemOutput.get());
        systemOutput.set(slf4jPrintStream);
    }

    private static SLF4JPrintStreamImpl buildSLF4JPrintStream(PrintStream originalPrintStream) {
        LoggerAppenderStore loggerAppenderStore = new LoggerAppenderStore();
        SLF4JPrintStreamDelegate delegate = new SLF4JPrintStreamDelegate(originalPrintStream, loggerAppenderStore);
        return new SLF4JPrintStreamImpl(originalPrintStream, delegate);
    }

    @Override // uk.org.lidalia.sysoutslf4j.common.PrintStreamCoordinator
    public void restoreOriginalSystemOutputs() {
        for (SystemOutput systemOutput : SystemOutput.valuesCustom()) {
            restoreSystemOutput(systemOutput);
        }
    }

    private static void restoreSystemOutput(SystemOutput systemOutput) {
        SLF4JPrintStream slf4jPrintStream = (SLF4JPrintStream) systemOutput.get();
        systemOutput.set(slf4jPrintStream.getOriginalPrintStream());
    }
}
