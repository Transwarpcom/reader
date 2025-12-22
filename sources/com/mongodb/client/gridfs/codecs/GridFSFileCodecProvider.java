package com.mongodb.client.gridfs.codecs;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/gridfs/codecs/GridFSFileCodecProvider.class */
public final class GridFSFileCodecProvider implements CodecProvider {
    @Override // org.bson.codecs.configuration.CodecProvider
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz.equals(GridFSFile.class)) {
            return new GridFSFileCodec(registry);
        }
        return null;
    }
}
