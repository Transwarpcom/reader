package ch.qos.logback.core.joran.spi;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareImpl;
import cn.hutool.core.text.StrPool;
import org.xml.sax.Locator;

/* compiled from: Interpreter.java */
/* loaded from: reader.jar:BOOT-INF/lib/logback-core-1.2.3.jar:ch/qos/logback/core/joran/spi/CAI_WithLocatorSupport.class */
class CAI_WithLocatorSupport extends ContextAwareImpl {
    CAI_WithLocatorSupport(Context context, Interpreter interpreter) {
        super(context, interpreter);
    }

    @Override // ch.qos.logback.core.spi.ContextAwareImpl
    protected Object getOrigin() {
        Interpreter i = (Interpreter) super.getOrigin();
        Locator locator = i.locator;
        if (locator != null) {
            return Interpreter.class.getName() + StrPool.AT + locator.getLineNumber() + ":" + locator.getColumnNumber();
        }
        return Interpreter.class.getName() + "@NA:NA";
    }
}
