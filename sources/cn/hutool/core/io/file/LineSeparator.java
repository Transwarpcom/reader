package cn.hutool.core.io.file;

import cn.hutool.core.text.StrPool;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/file/LineSeparator.class */
public enum LineSeparator {
    MAC("\r"),
    LINUX("\n"),
    WINDOWS(StrPool.CRLF);

    private final String value;

    LineSeparator(String lineSeparator) {
        this.value = lineSeparator;
    }

    public String getValue() {
        return this.value;
    }
}
