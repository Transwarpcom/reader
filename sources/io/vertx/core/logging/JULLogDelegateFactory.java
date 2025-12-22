package io.vertx.core.logging;

import io.vertx.core.spi.logging.LogDelegate;
import io.vertx.core.spi.logging.LogDelegateFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/logging/JULLogDelegateFactory.class */
public class JULLogDelegateFactory implements LogDelegateFactory {
    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r3v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r4v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x003d: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('is' java.io.InputStream)]) A[TRY_LEAVE], block:B:18:0x003d */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0041: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:20:0x0041 */
    /* JADX WARN: Type inference failed for: r3v0, names: [is], types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.Throwable] */
    public static void loadConfig() throws IOException {
        try {
            try {
                InputStream resourceAsStream = JULLogDelegateFactory.class.getClassLoader().getResourceAsStream("vertx-default-jul-logging.properties");
                Throwable th = null;
                if (resourceAsStream != null) {
                    LogManager.getLogManager().readConfiguration(resourceAsStream);
                }
                if (resourceAsStream != null) {
                    if (0 != 0) {
                        try {
                            resourceAsStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        resourceAsStream.close();
                    }
                }
            } finally {
            }
        } catch (IOException e) {
        }
    }

    static {
        if (System.getProperty("java.util.logging.config.file") == null) {
            loadConfig();
        }
    }

    @Override // io.vertx.core.spi.logging.LogDelegateFactory
    public LogDelegate createDelegate(String name) {
        return new JULLogDelegate(name);
    }
}
