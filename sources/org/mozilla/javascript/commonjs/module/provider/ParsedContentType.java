package org.mozilla.javascript.commonjs.module.provider;

import java.io.Serializable;
import java.util.StringTokenizer;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/commonjs/module/provider/ParsedContentType.class */
public final class ParsedContentType implements Serializable {
    private static final long serialVersionUID = 1;
    private final String contentType;
    private final String encoding;

    public ParsedContentType(String mimeType) {
        String contentType = null;
        String encoding = null;
        if (mimeType != null) {
            StringTokenizer tok = new StringTokenizer(mimeType, ";");
            if (tok.hasMoreTokens()) {
                contentType = tok.nextToken().trim();
                while (true) {
                    if (!tok.hasMoreTokens()) {
                        break;
                    }
                    String param = tok.nextToken().trim();
                    if (param.startsWith("charset=")) {
                        encoding = param.substring(8).trim();
                        int l = encoding.length();
                        if (l > 0) {
                            encoding = encoding.charAt(0) == '\"' ? encoding.substring(1) : encoding;
                            if (encoding.charAt(l - 1) == '\"') {
                                encoding = encoding.substring(0, l - 1);
                            }
                        }
                    }
                }
            }
        }
        this.contentType = contentType;
        this.encoding = encoding;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getEncoding() {
        return this.encoding;
    }
}
