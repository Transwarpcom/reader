package io.vertx.core.impl.verticle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardLocation;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/verticle/MemoryFileManager.class */
public class MemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {
    private final Map<String, ByteArrayOutputStream> compiledClasses;
    private final PackageHelper helper;

    public MemoryFileManager(ClassLoader classLoader, JavaFileManager fileManager) {
        super(fileManager);
        this.compiledClasses = new HashMap();
        this.helper = new PackageHelper(classLoader);
    }

    public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, final String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        try {
            return new SimpleJavaFileObject(new URI(""), kind) { // from class: io.vertx.core.impl.verticle.MemoryFileManager.1
                public OutputStream openOutputStream() throws IOException {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    MemoryFileManager.this.compiledClasses.put(className, outputStream);
                    return outputStream;
                }
            };
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getCompiledClass(String name) {
        ByteArrayOutputStream bytes = this.compiledClasses.get(name);
        if (bytes == null) {
            return null;
        }
        return bytes.toByteArray();
    }

    public String inferBinaryName(JavaFileManager.Location location, JavaFileObject file) {
        if (file instanceof CustomJavaFileObject) {
            return ((CustomJavaFileObject) file).binaryName();
        }
        return super.inferBinaryName(location, file);
    }

    public Iterable<JavaFileObject> list(JavaFileManager.Location location, String packageName, Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
        if (location == StandardLocation.CLASS_PATH && kinds.contains(JavaFileObject.Kind.CLASS)) {
            return this.helper.find(packageName);
        }
        return super.list(location, packageName, kinds, recurse);
    }
}
