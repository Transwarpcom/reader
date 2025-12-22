package org.seimicrawler.xpath.core.function;

import java.util.List;
import org.seimicrawler.xpath.core.Function;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;
import org.seimicrawler.xpath.util.CommonUtil;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/function/Position.class */
public class Position implements Function {
    @Override // org.seimicrawler.xpath.core.Function
    public String name() {
        return "position";
    }

    @Override // org.seimicrawler.xpath.core.Function
    public XValue call(Scope scope, List<XValue> params) {
        return XValue.create(Integer.valueOf(CommonUtil.getElIndexInSameTags(scope.singleEl(), scope.getParent())));
    }
}
