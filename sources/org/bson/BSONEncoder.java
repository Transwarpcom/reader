package org.bson;

import org.bson.io.OutputBuffer;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BSONEncoder.class */
public interface BSONEncoder {
    byte[] encode(BSONObject bSONObject);

    int putObject(BSONObject bSONObject);

    void done();

    void set(OutputBuffer outputBuffer);
}
