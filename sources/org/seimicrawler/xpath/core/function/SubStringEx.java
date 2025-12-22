package org.seimicrawler.xpath.core.function;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.seimicrawler.xpath.core.Function;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/function/SubStringEx.class */
public class SubStringEx implements Function {
    @Override // org.seimicrawler.xpath.core.Function
    public String name() {
        return "substring-ex";
    }

    @Override // org.seimicrawler.xpath.core.Function
    public XValue call(Scope scope, List<XValue> params) {
        String target = params.get(0).asString();
        int start = params.get(1).asLong().intValue();
        if (params.get(2) != null) {
            int end = params.get(2).asLong().intValue();
            return XValue.create(StringUtils.substring(target, start, end));
        }
        return XValue.create(StringUtils.substring(target, start));
    }
}
