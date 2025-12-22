package io.vertx.core.impl.cpu;

import io.vertx.core.impl.launcher.commands.ExecUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.AccessController;
import org.apache.commons.lang3.CharEncoding;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/cpu/CpuCoreSensor.class */
public class CpuCoreSensor {
    private static final String CPUS_ALLOWED = "Cpus_allowed:";
    private static final byte[] BITS = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};
    private static final Charset ASCII = Charset.forName(CharEncoding.US_ASCII);

    public static int availableProcessors() {
        if (System.getSecurityManager() != null) {
            return ((Integer) AccessController.doPrivileged(() -> {
                return Integer.valueOf(determineProcessors());
            })).intValue();
        }
        return determineProcessors();
    }

    private static int determineProcessors() {
        int fromJava = Runtime.getRuntime().availableProcessors();
        int fromProcFile = 0;
        if (!ExecUtils.isLinux()) {
            return fromJava;
        }
        try {
            fromProcFile = readCPUMask(new File("/proc/self/status"));
        } catch (Exception e) {
        }
        return fromProcFile > 0 ? Math.min(fromJava, fromProcFile) : fromJava;
    }

    protected static int readCPUMask(File file) throws IOException {
        String line;
        if (file == null || !file.exists()) {
            return -1;
        }
        FileInputStream stream = new FileInputStream(file);
        InputStreamReader inputReader = new InputStreamReader(stream, ASCII);
        BufferedReader reader = new BufferedReader(inputReader);
        Throwable th = null;
        do {
            try {
                try {
                    line = reader.readLine();
                    if (line == null) {
                        if (reader == null) {
                            return -1;
                        }
                        if (0 != 0) {
                            try {
                                reader.close();
                                return -1;
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                                return -1;
                            }
                        }
                        reader.close();
                        return -1;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    throw th3;
                }
            } catch (Throwable th4) {
                if (reader != null) {
                    if (th != null) {
                        try {
                            reader.close();
                        } catch (Throwable th5) {
                            th.addSuppressed(th5);
                        }
                    } else {
                        reader.close();
                    }
                }
                throw th4;
            }
        } while (!line.startsWith(CPUS_ALLOWED));
        int count = 0;
        int start = CPUS_ALLOWED.length();
        for (int i = start; i < line.length(); i++) {
            char ch2 = line.charAt(i);
            if (ch2 >= '0' && ch2 <= '9') {
                count += BITS[ch2 - '0'];
            } else if (ch2 >= 'a' && ch2 <= 'f') {
                count += BITS[(ch2 - 'a') + 10];
            } else if (ch2 >= 'A' && ch2 <= 'F') {
                count += BITS[(ch2 - 'A') + 10];
            }
        }
        int i2 = count;
        if (reader != null) {
            if (0 != 0) {
                try {
                    reader.close();
                } catch (Throwable th6) {
                    th.addSuppressed(th6);
                }
            } else {
                reader.close();
            }
        }
        return i2;
    }
}
