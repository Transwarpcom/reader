package me.ag2s.epublib.domain;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: reader.jar:BOOT-INF/classes/me/ag2s/epublib/domain/LazyResourceProvider.class */
public interface LazyResourceProvider {
    InputStream getResourceStream(String href) throws IOException;
}
