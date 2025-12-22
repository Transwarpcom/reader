package ch.qos.logback.core.util;

import me.ag2s.epublib.epub.NCXDocumentV2;

/* loaded from: reader.jar:BOOT-INF/lib/logback-core-1.2.3.jar:ch/qos/logback/core/util/ContentTypeUtil.class */
public class ContentTypeUtil {
    public static boolean isTextual(String contextType) {
        if (contextType == null) {
            return false;
        }
        return contextType.startsWith(NCXDocumentV2.NCXTags.text);
    }

    public static String getSubType(String contextType) {
        int index;
        int subTypeStartIndex;
        if (contextType != null && (index = contextType.indexOf(47)) != -1 && (subTypeStartIndex = index + 1) < contextType.length()) {
            return contextType.substring(subTypeStartIndex);
        }
        return null;
    }
}
