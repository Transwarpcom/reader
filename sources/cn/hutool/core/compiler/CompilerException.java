package cn.hutool.core.compiler;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/compiler/CompilerException.class */
public class CompilerException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public CompilerException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public CompilerException(String message) {
        super(message);
    }

    public CompilerException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public CompilerException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CompilerException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}
