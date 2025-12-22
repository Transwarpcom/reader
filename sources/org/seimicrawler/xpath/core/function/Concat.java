package org.seimicrawler.xpath.core.function;

import java.util.List;
import org.seimicrawler.xpath.core.Function;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/function/Concat.class */
public class Concat implements Function {
    @Override // org.seimicrawler.xpath.core.Function
    public String name() {
        return "concat";
    }

    @Override // org.seimicrawler.xpath.core.Function
    public XValue call(Scope scope, List<XValue> params) {
        StringBuilder accum = new StringBuilder();
        for (XValue v : params) {
            accum.append(v.asString());
        }
        return XValue.create(accum.toString());
    }
}
