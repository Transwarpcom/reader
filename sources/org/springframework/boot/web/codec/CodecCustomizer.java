package org.springframework.boot.web.codec;

import org.springframework.http.codec.CodecConfigurer;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/codec/CodecCustomizer.class */
public interface CodecCustomizer {
    void customize(CodecConfigurer configurer);
}
