package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.HttpMethod;
import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/rtsp/RtspMethods.class */
public final class RtspMethods {
    public static final HttpMethod OPTIONS = HttpMethod.OPTIONS;
    public static final HttpMethod DESCRIBE = HttpMethod.valueOf("DESCRIBE");
    public static final HttpMethod ANNOUNCE = HttpMethod.valueOf("ANNOUNCE");
    public static final HttpMethod SETUP = HttpMethod.valueOf("SETUP");
    public static final HttpMethod PLAY = HttpMethod.valueOf("PLAY");
    public static final HttpMethod PAUSE = HttpMethod.valueOf("PAUSE");
    public static final HttpMethod TEARDOWN = HttpMethod.valueOf("TEARDOWN");
    public static final HttpMethod GET_PARAMETER = HttpMethod.valueOf("GET_PARAMETER");
    public static final HttpMethod SET_PARAMETER = HttpMethod.valueOf("SET_PARAMETER");
    public static final HttpMethod REDIRECT = HttpMethod.valueOf("REDIRECT");
    public static final HttpMethod RECORD = HttpMethod.valueOf("RECORD");
    private static final Map<String, HttpMethod> methodMap = new HashMap();

    static {
        methodMap.put(DESCRIBE.toString(), DESCRIBE);
        methodMap.put(ANNOUNCE.toString(), ANNOUNCE);
        methodMap.put(GET_PARAMETER.toString(), GET_PARAMETER);
        methodMap.put(OPTIONS.toString(), OPTIONS);
        methodMap.put(PAUSE.toString(), PAUSE);
        methodMap.put(PLAY.toString(), PLAY);
        methodMap.put(RECORD.toString(), RECORD);
        methodMap.put(REDIRECT.toString(), REDIRECT);
        methodMap.put(SETUP.toString(), SETUP);
        methodMap.put(SET_PARAMETER.toString(), SET_PARAMETER);
        methodMap.put(TEARDOWN.toString(), TEARDOWN);
    }

    public static HttpMethod valueOf(String name) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        String name2 = name.trim().toUpperCase();
        if (name2.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }
        HttpMethod result = methodMap.get(name2);
        if (result != null) {
            return result;
        }
        return HttpMethod.valueOf(name2);
    }

    private RtspMethods() {
    }
}
