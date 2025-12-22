package com.jayway.jsonpath;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/ParseContext.class */
public interface ParseContext {
    DocumentContext parse(String str);

    DocumentContext parse(Object obj);

    DocumentContext parse(InputStream inputStream);

    DocumentContext parse(InputStream inputStream, String str);

    DocumentContext parse(File file) throws IOException;

    @Deprecated
    DocumentContext parse(URL url) throws IOException;
}
