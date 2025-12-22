package org.seimicrawler.xpath.core.node;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.seimicrawler.xpath.core.NodeTest;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;
import org.seimicrawler.xpath.util.Scanner;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/node/Num.class */
public class Num implements NodeTest {
    private static Pattern numExt = Pattern.compile("\\d*\\.?\\d+");

    @Override // org.seimicrawler.xpath.core.NodeTest
    public String name() {
        return "num";
    }

    @Override // org.seimicrawler.xpath.core.NodeTest
    public XValue call(Scope scope) {
        NodeTest textFun = Scanner.findNodeTestByName("allText");
        XValue textVal = textFun.call(scope);
        String whole = StringUtils.join(textVal.asList(), "");
        Matcher matcher = numExt.matcher(whole);
        if (matcher.find()) {
            String numStr = matcher.group();
            BigDecimal num = new BigDecimal(numStr);
            return XValue.create(Double.valueOf(num.doubleValue()));
        }
        return XValue.create(null);
    }
}
