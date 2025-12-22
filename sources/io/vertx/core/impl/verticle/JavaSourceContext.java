package io.vertx.core.impl.verticle;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.tools.JavaFileObject;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/verticle/JavaSourceContext.class */
public class JavaSourceContext {
    private final String className;
    private final File sourceRoot;

    public JavaSourceContext(File file) {
        String packageName = parsePackage(file);
        File rootDirectory = file.getParentFile();
        if (packageName != null) {
            String[] pathTokens = packageName.split("\\.");
            for (int i = pathTokens.length - 1; i >= 0; i--) {
                String token = pathTokens[i];
                if (!token.equals(rootDirectory.getName())) {
                    throw new RuntimeException("Package structure does not match directory structure: " + token + " != " + rootDirectory.getName());
                }
                rootDirectory = rootDirectory.getParentFile();
            }
        }
        this.sourceRoot = rootDirectory;
        String fileName = file.getName();
        String className = fileName.substring(0, fileName.length() - JavaFileObject.Kind.SOURCE.extension.length());
        this.className = packageName != null ? packageName + '.' + className : className;
    }

    public File getSourceRoot() {
        return this.sourceRoot;
    }

    public String getClassName() {
        return this.className;
    }

    private static String parsePackage(File file) {
        try {
            String source = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            for (String str : source.replaceAll("//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/", "$1 ").split("\\r?\\n")) {
                String line = str.trim();
                if (!line.isEmpty()) {
                    int idx = line.indexOf("package ");
                    if (idx != -1) {
                        return line.substring(line.indexOf(32, idx), line.indexOf(59, idx)).trim();
                    }
                    return null;
                }
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
