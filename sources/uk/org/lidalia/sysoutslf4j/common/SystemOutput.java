package uk.org.lidalia.sysoutslf4j.common;

import java.io.PrintStream;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/common/SystemOutput.class */
public enum SystemOutput {
    OUT("System.out") { // from class: uk.org.lidalia.sysoutslf4j.common.SystemOutput.1
        @Override // uk.org.lidalia.sysoutslf4j.common.SystemOutput
        public PrintStream get() {
            return System.out;
        }

        @Override // uk.org.lidalia.sysoutslf4j.common.SystemOutput
        public void set(PrintStream newPrintStream) {
            System.setOut(newPrintStream);
        }
    },
    ERR("System.err") { // from class: uk.org.lidalia.sysoutslf4j.common.SystemOutput.2
        @Override // uk.org.lidalia.sysoutslf4j.common.SystemOutput
        public PrintStream get() {
            return System.err;
        }

        @Override // uk.org.lidalia.sysoutslf4j.common.SystemOutput
        public void set(PrintStream newPrintStream) {
            System.setErr(newPrintStream);
        }
    };

    private final String friendlyName;

    public abstract PrintStream get();

    public abstract void set(PrintStream printStream);

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static SystemOutput[] valuesCustom() {
        SystemOutput[] systemOutputArrValuesCustom = values();
        int length = systemOutputArrValuesCustom.length;
        SystemOutput[] systemOutputArr = new SystemOutput[length];
        System.arraycopy(systemOutputArrValuesCustom, 0, systemOutputArr, 0, length);
        return systemOutputArr;
    }

    SystemOutput(String name) {
        this.friendlyName = name;
    }

    /* synthetic */ SystemOutput(String str, SystemOutput systemOutput) {
        this(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.friendlyName;
    }
}
