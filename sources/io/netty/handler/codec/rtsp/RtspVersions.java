package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.HttpVersion;
import me.ag2s.epublib.epub.NCXDocumentV2;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/rtsp/RtspVersions.class */
public final class RtspVersions {
    public static final HttpVersion RTSP_1_0 = new HttpVersion("RTSP", 1, 0, true);

    public static HttpVersion valueOf(String text) {
        if (text == null) {
            throw new NullPointerException(NCXDocumentV2.NCXTags.text);
        }
        String text2 = text.trim().toUpperCase();
        if ("RTSP/1.0".equals(text2)) {
            return RTSP_1_0;
        }
        return new HttpVersion(text2, true);
    }

    private RtspVersions() {
    }
}
