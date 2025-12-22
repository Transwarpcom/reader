package org.seimicrawler.xpath.core.function;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.seimicrawler.xpath.core.Function;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/function/SubStringAfterLast.class */
public class SubStringAfterLast implements Function {
    @Override // org.seimicrawler.xpath.core.Function
    public String name() {
        return "substring-after-last";
    }

    @Override // org.seimicrawler.xpath.core.Function
    public XValue call(Scope scope, List<XValue> params) {
        String target = params.get(0).asString();
        String sep = params.get(1).asString();
        return XValue.create(StringUtils.substringAfterLast(target, sep));
    }
}
