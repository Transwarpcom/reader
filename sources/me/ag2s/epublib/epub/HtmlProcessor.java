package me.ag2s.epublib.epub;

import java.io.OutputStream;
import me.ag2s.epublib.domain.Resource;

/* loaded from: reader.jar:BOOT-INF/classes/me/ag2s/epublib/epub/HtmlProcessor.class */
public interface HtmlProcessor {
    void processHtmlResource(Resource resource, OutputStream out);
}
