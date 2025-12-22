package ch.qos.logback.core.pattern.util;

import cn.hutool.core.text.StrPool;

/* loaded from: reader.jar:BOOT-INF/lib/logback-core-1.2.3.jar:ch/qos/logback/core/pattern/util/RestrictedEscapeUtil.class */
public class RestrictedEscapeUtil implements IEscapeUtil {
    @Override // ch.qos.logback.core.pattern.util.IEscapeUtil
    public void escape(String escapeChars, StringBuffer buf, char next, int pointer) {
        if (escapeChars.indexOf(next) >= 0) {
            buf.append(next);
        } else {
            buf.append(StrPool.BACKSLASH);
            buf.append(next);
        }
    }
}
