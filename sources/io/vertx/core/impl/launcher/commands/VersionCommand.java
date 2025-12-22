package io.vertx.core.impl.launcher.commands;

import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.launcher.DefaultCommand;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Name("version")
@Summary("Displays the version.")
@Description("Prints the vert.x core version used by the application.")
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/VersionCommand.class */
public class VersionCommand extends DefaultCommand {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) VersionCommand.class);
    private static String version;

    @Override // io.vertx.core.spi.launcher.Command
    public void run() throws CLIException {
        log.info(getVersion());
    }

    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r5v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r6v0 ??
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
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x00c1: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('is' java.io.InputStream)]) A[TRY_LEAVE], block:B:52:0x00c1 */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x00c5: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:54:0x00c5 */
    /* JADX WARN: Type inference failed for: r5v0, names: [is], types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Throwable] */
    public static String getVersion() throws IOException {
        if (version != null) {
            return version;
        }
        try {
            try {
                InputStream resourceAsStream = VersionCommand.class.getClassLoader().getResourceAsStream("META-INF/vertx/vertx-version.txt");
                Throwable th = null;
                if (resourceAsStream == null) {
                    throw new IllegalStateException("Cannot find vertx-version.txt on classpath");
                }
                Scanner scannerUseDelimiter = new Scanner(resourceAsStream, "UTF-8").useDelimiter("\\A");
                Throwable th2 = null;
                try {
                    try {
                        String strTrim = scannerUseDelimiter.hasNext() ? scannerUseDelimiter.next().trim() : "";
                        version = strTrim;
                        String str = strTrim;
                        if (scannerUseDelimiter != null) {
                            if (0 != 0) {
                                try {
                                    scannerUseDelimiter.close();
                                } catch (Throwable th3) {
                                    th2.addSuppressed(th3);
                                }
                            } else {
                                scannerUseDelimiter.close();
                            }
                        }
                        if (resourceAsStream != null) {
                            if (0 != 0) {
                                try {
                                    resourceAsStream.close();
                                } catch (Throwable th4) {
                                    th.addSuppressed(th4);
                                }
                            } else {
                                resourceAsStream.close();
                            }
                        }
                        return str;
                    } catch (Throwable th5) {
                        if (scannerUseDelimiter != null) {
                            if (th2 != null) {
                                try {
                                    scannerUseDelimiter.close();
                                } catch (Throwable th6) {
                                    th2.addSuppressed(th6);
                                }
                            } else {
                                scannerUseDelimiter.close();
                            }
                        }
                        throw th5;
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
