package org.bson;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BSONDecoder.class */
public interface BSONDecoder {
    BSONObject readObject(byte[] bArr);

    BSONObject readObject(InputStream inputStream) throws IOException;

    int decode(byte[] bArr, BSONCallback bSONCallback);

    int decode(InputStream inputStream, BSONCallback bSONCallback) throws IOException;
}
