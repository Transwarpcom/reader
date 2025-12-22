package cn.hutool.core.lang.generator;

import cn.hutool.core.util.IdUtil;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/generator/UUIDGenerator.class */
public class UUIDGenerator implements Generator<String> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.hutool.core.lang.generator.Generator
    public String next() {
        return IdUtil.fastUUID();
    }
}
