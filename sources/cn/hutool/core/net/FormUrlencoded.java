package cn.hutool.core.net;

import cn.hutool.core.codec.PercentCodec;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/net/FormUrlencoded.class */
public class FormUrlencoded {
    public static final PercentCodec ALL = PercentCodec.of(RFC3986.UNRESERVED).removeSafe('~').addSafe('*').setEncodeSpaceAsPlus(true);
}
