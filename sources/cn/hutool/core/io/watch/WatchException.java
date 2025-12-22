package cn.hutool.core.io.watch;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/watch/WatchException.class */
public class WatchException extends RuntimeException {
    private static final long serialVersionUID = 8068509879445395353L;

    public WatchException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public WatchException(String message) {
        super(message);
    }

    public WatchException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public WatchException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public WatchException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}
