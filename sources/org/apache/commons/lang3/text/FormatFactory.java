package org.apache.commons.lang3.text;

import java.text.Format;
import java.util.Locale;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/text/FormatFactory.class */
public interface FormatFactory {
    Format getFormat(String str, String str2, Locale locale);
}
