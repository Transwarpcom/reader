package cn.hutool.core.codec;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/codec/Decoder.class */
public interface Decoder<T, R> {
    R decode(T t);
}
