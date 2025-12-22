package org.apache.fontbox.type1;

import java.io.IOException;
import org.apache.fontbox.cff.Type1CharString;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/type1/Type1CharStringReader.class */
public interface Type1CharStringReader {
    Type1CharString getType1CharString(String str) throws IOException;
}
