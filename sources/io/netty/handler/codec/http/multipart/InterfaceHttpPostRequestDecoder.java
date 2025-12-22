package io.netty.handler.codec.http.multipart;

import io.netty.handler.codec.http.HttpContent;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/multipart/InterfaceHttpPostRequestDecoder.class */
public interface InterfaceHttpPostRequestDecoder {
    boolean isMultipart();

    void setDiscardThreshold(int i);

    int getDiscardThreshold();

    List<InterfaceHttpData> getBodyHttpDatas();

    List<InterfaceHttpData> getBodyHttpDatas(String str);

    InterfaceHttpData getBodyHttpData(String str);

    InterfaceHttpPostRequestDecoder offer(HttpContent httpContent);

    boolean hasNext();

    InterfaceHttpData next();

    InterfaceHttpData currentPartialHttpData();

    void destroy();

    void cleanFiles();

    void removeHttpDataFromClean(InterfaceHttpData interfaceHttpData);
}
