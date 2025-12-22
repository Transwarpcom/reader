package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import java.io.InputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/fdf/XMLUtil.class */
final class XMLUtil {
    private XMLUtil() {
    }

    public static Document parse(InputStream is) throws IOException {
        return org.apache.pdfbox.util.XMLUtil.parse(is);
    }

    public static String getNodeValue(Element node) {
        return org.apache.pdfbox.util.XMLUtil.getNodeValue(node);
    }
}
