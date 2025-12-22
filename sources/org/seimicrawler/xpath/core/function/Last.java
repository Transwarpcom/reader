package org.seimicrawler.xpath.core.function;

import java.util.List;
import org.seimicrawler.xpath.core.Function;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/function/Last.class */
public class Last implements Function {
    @Override // org.seimicrawler.xpath.core.Function
    public String name() {
        return "last";
    }

    @Override // org.seimicrawler.xpath.core.Function
    public XValue call(Scope scope, List<XValue> params) {
        return XValue.create(-1);
    }
}
