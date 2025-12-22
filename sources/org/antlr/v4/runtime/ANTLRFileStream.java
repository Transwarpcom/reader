package org.antlr.v4.runtime;

import java.io.IOException;
import org.antlr.v4.runtime.misc.Utils;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/ANTLRFileStream.class */
public class ANTLRFileStream extends ANTLRInputStream {
    protected String fileName;

    public ANTLRFileStream(String fileName) throws IOException {
        this(fileName, null);
    }

    public ANTLRFileStream(String fileName, String encoding) throws IOException {
        this.fileName = fileName;
        load(fileName, encoding);
    }

    public void load(String fileName, String encoding) throws IOException {
        this.data = Utils.readFile(fileName, encoding);
        this.n = this.data.length;
    }

    @Override // org.antlr.v4.runtime.ANTLRInputStream, org.antlr.v4.runtime.IntStream
    public String getSourceName() {
        return this.fileName;
    }
}
