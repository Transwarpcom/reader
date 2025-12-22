package org.xmlpull.v1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* loaded from: reader.jar:BOOT-INF/lib/xmlpull-1.1.3.1.jar:org/xmlpull/v1/XmlSerializer.class */
public interface XmlSerializer {
    void setFeature(String str, boolean z) throws IllegalStateException, IllegalArgumentException;

    boolean getFeature(String str);

    void setProperty(String str, Object obj) throws IllegalStateException, IllegalArgumentException;

    Object getProperty(String str);

    void setOutput(OutputStream outputStream, String str) throws IllegalStateException, IOException, IllegalArgumentException;

    void setOutput(Writer writer) throws IllegalStateException, IOException, IllegalArgumentException;

    void startDocument(String str, Boolean bool) throws IllegalStateException, IOException, IllegalArgumentException;

    void endDocument() throws IllegalStateException, IOException, IllegalArgumentException;

    void setPrefix(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException;

    String getPrefix(String str, boolean z) throws IllegalArgumentException;

    int getDepth();

    String getNamespace();

    String getName();

    XmlSerializer startTag(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException;

    XmlSerializer attribute(String str, String str2, String str3) throws IllegalStateException, IOException, IllegalArgumentException;

    XmlSerializer endTag(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException;

    XmlSerializer text(String str) throws IllegalStateException, IOException, IllegalArgumentException;

    XmlSerializer text(char[] cArr, int i, int i2) throws IllegalStateException, IOException, IllegalArgumentException;

    void cdsect(String str) throws IllegalStateException, IOException, IllegalArgumentException;

    void entityRef(String str) throws IllegalStateException, IOException, IllegalArgumentException;

    void processingInstruction(String str) throws IllegalStateException, IOException, IllegalArgumentException;

    void comment(String str) throws IllegalStateException, IOException, IllegalArgumentException;

    void docdecl(String str) throws IllegalStateException, IOException, IllegalArgumentException;

    void ignorableWhitespace(String str) throws IllegalStateException, IOException, IllegalArgumentException;

    void flush() throws IOException;
}
