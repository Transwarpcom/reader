package org.seimicrawler.xpath.core.function;

import java.util.List;
import org.seimicrawler.xpath.core.Function;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;
import org.seimicrawler.xpath.exception.XpathParserException;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/function/Not.class */
public class Not implements Function {
    @Override // org.seimicrawler.xpath.core.Function
    public String name() {
        return "not";
    }

    @Override // org.seimicrawler.xpath.core.Function
    public XValue call(Scope scope, List<XValue> params) {
        if (params.size() == 1) {
            return XValue.create(Boolean.valueOf(!params.get(0).asBoolean().booleanValue()));
        }
        throw new XpathParserException("error param in not(bool) function.Please check.");
    }
}
